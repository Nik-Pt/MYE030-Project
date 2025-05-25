import pandas as pd
import mysql.connector
from unidecode import unidecode


def create_shootouts_table(connection):
    cursor = connection.cursor()
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS shootouts (
            date DATE NOT NULL,
            home_team INT NOT NULL,
            away_team INT NOT NULL,
            winner VARCHAR(50),
            first_shooter VARCHAR(50),
            PRIMARY KEY (date, home_team, away_team, winner),
            FOREIGN KEY (date, home_team, away_team) REFERENCES results(date, home_team, away_team)
        ) ENGINE=InnoDB DEFAULT CHARSET= utf8mb4 COLLATE= utf8mb4_unicode_ci;
    """)

def check_for_different_named_countries(shootouts_dataframe):
    countries_dataframe = pd.read_csv("Outputs/countries_utf8.csv")
    existing_countries = countries_dataframe["Official_Name"].tolist()

    replacement_dict = {}
    unique_teams = set(shootouts_dataframe["home_team"].unique()).union(set(shootouts_dataframe["away_team"].unique()))
    
    for country_team in unique_teams:
        for country in existing_countries:
            if unidecode(country_team).strip().lower() == unidecode(country).strip().lower():
                replacement_dict[country_team] = country
                break 
                
    shootouts_dataframe[['home_team', 'away_team']] = shootouts_dataframe[['home_team', 'away_team']].replace(replacement_dict)

    return shootouts_dataframe

def replace_names_with_ISO(shootouts_dataframe):
    countries_dataframe = pd.read_csv("Outputs/countries_utf8.csv")
    former_names_dataframe = pd.read_csv("Data/former_names.csv")

    official_name_country_to_iso = dict(zip(countries_dataframe['Official_Name'], countries_dataframe['ISO_Code']))
    shootouts_dataframe[['home_team', 'away_team']] = shootouts_dataframe[['home_team', 'away_team']].replace(official_name_country_to_iso)
    
    display_name_country_to_iso = dict(zip(countries_dataframe['Display_Name'], countries_dataframe['ISO_Code']))
    shootouts_dataframe[['home_team', 'away_team']] = shootouts_dataframe[['home_team', 'away_team']].replace(display_name_country_to_iso)

    former_to_current = dict(zip(former_names_dataframe['former'], former_names_dataframe['current']))
    shootouts_dataframe[['home_team', 'away_team']] = shootouts_dataframe[['home_team', 'away_team']].replace(former_to_current)

    return shootouts_dataframe

def load_data_into_mysql(connection, file_path):
    try:
        cursor = connection.cursor()
        cursor.execute("""
            LOAD DATA LOCAL INFILE %s
            INTO TABLE shootouts
            FIELDS TERMINATED BY ','
            ENCLOSED BY '"'
            LINES TERMINATED BY '\n'
            IGNORE 1 ROWS
            (date, home_team, away_team, winner, first_shooter)
        """, (file_path,))
        connection.commit()
        cursor.close()
        print(f"Data loaded into MySQL from {file_path}")
    except mysql.connector.Error as err:
        print(f"Something went wrong: {err}")

def initiate_shootouts_table_creation(connection):
    shootouts_csv = "Data/shootouts.csv"
    altered_shootouts_csv = "Outputs/shootouts_altered.csv"

    create_shootouts_table(connection)

    dataframe = pd.read_csv(shootouts_csv)
    dataframe = check_for_different_named_countries(dataframe)
    dataframe = replace_names_with_ISO(dataframe)

    dataframe.to_csv(altered_shootouts_csv, index=False)

    load_data_into_mysql(connection, altered_shootouts_csv)

    print("Shootouts table created!")