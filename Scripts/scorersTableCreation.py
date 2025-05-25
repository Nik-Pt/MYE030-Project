import mysql.connector
import pandas as pd
from unidecode import unidecode

def create_scorers_table(connection):
    cursor = connection.cursor()
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS scorers (
            date DATE NOT NULL,
            home_team INT NOT NULL,
            away_team INT NOT NULL,
            team VARCHAR(50),
            scorer VARCHAR(100) NOT NULL,
            minute VARCHAR(10) NOT NULL,
            own_goal BOOLEAN,
            penalty BOOLEAN,
            PRIMARY KEY (date, home_team, away_team, scorer, minute),
            FOREIGN KEY (date, home_team, away_team) REFERENCES results(date, home_team, away_team)
        ) ENGINE=InnoDB DEFAULT CHARSET= utf8mb4 COLLATE= utf8mb4_unicode_ci;
    """)

    print("Scorers table created!")

def process_df(dataframe):
    boolean_columns = ["own_goal", "penalty"]

    for column in boolean_columns:
        dataframe[column] = dataframe[column].apply(lambda x: 1 if str(x).strip().upper() == "TRUE" else 0)
    return dataframe

def check_for_different_named_countries(scorers_dataframe):
    countries_dataframe = pd.read_csv("Outputs/countries_utf8.csv")
    existing_countries = countries_dataframe["Official_Name"].tolist()

    replacement_dict = {}
    unique_teams = set(scorers_dataframe["home_team"].unique()).union(set(scorers_dataframe["away_team"].unique()))
    
    for country_team in unique_teams:
        for country in existing_countries:
            if unidecode(country_team).strip().lower() == unidecode(country).strip().lower():
                replacement_dict[country_team] = country
                break 
                
    scorers_dataframe[['home_team', 'away_team']] = scorers_dataframe[['home_team', 'away_team']].replace(replacement_dict)

    return scorers_dataframe

def replace_names_with_ISO(scorers_dataframe):
    countries_dataframe = pd.read_csv("Outputs/countries_utf8.csv")
    former_names_dataframe = pd.read_csv("Data/former_names.csv")

    official_name_country_to_iso = dict(zip(countries_dataframe['Official_Name'], countries_dataframe['ISO_Code']))
    scorers_dataframe[['home_team', 'away_team']] = scorers_dataframe[['home_team', 'away_team']].replace(official_name_country_to_iso)
    
    display_name_country_to_iso = dict(zip(countries_dataframe['Display_Name'], countries_dataframe['ISO_Code']))
    scorers_dataframe[['home_team', 'away_team']] = scorers_dataframe[['home_team', 'away_team']].replace(display_name_country_to_iso)

    former_to_current = dict(zip(former_names_dataframe['former'], former_names_dataframe['current']))
    scorers_dataframe[['home_team', 'away_team']] = scorers_dataframe[['home_team', 'away_team']].replace(former_to_current)

    return scorers_dataframe

def load_data_into_mysql(connection, file_path):
    try:
        cursor = connection.cursor()
        cursor.execute("""
            LOAD DATA LOCAL INFILE %s
            INTO TABLE scorers
            FIELDS TERMINATED BY ','
            ENCLOSED BY '"'
            LINES TERMINATED BY '\n'
            IGNORE 1 ROWS
            (date, home_team, away_team, team, scorer, minute, own_goal, penalty)
        """, (file_path,))
        connection.commit()
        cursor.close()
        print(f"Data loaded into MySQL from {file_path}")
    except mysql.connector.Error as err:
        print(f"Something went wrong: {err}")

def initiate_scorers_table_creation(connection):
    scorers_csv = "Data/goalscorers.csv"
    altered_scorests_csv = "Outputs/goalscorers_altered.csv"

    create_scorers_table(connection)

    dataframe = pd.read_csv(scorers_csv)
    dataframe = process_df(dataframe)
    dataframe = check_for_different_named_countries(dataframe)
    dataframe = replace_names_with_ISO(dataframe)

    dataframe.to_csv(altered_scorests_csv, index=False)

    load_data_into_mysql(connection, altered_scorests_csv)
    print("Scorers table populated with data!")