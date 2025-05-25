import pandas as pd
import mysql.connector
from unidecode import unidecode

def create_results_table(connection):
    cursor = connection.cursor()
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS results (
            date DATE NOT NULL,
            home_team INT NOT NULL,
            away_team INT NOT NULL,
            home_score INT NULL,
            away_score INT NULL,
            tournament VARCHAR(100),
            city VARCHAR(100) NULL,
            country VARCHAR(100) NULL,
            neutral BOOLEAN NULL,
            PRIMARY KEY (date, home_team, away_team, tournament),
            FOREIGN KEY (home_team) REFERENCES countries(ISO_CODE),
            FOREIGN KEY (away_team) REFERENCES countries(ISO_CODE)
        ) ENGINE=InnoDB DEFAULT CHARSET= utf8mb4 COLLATE= utf8mb4_unicode_ci;
    """)

    print("Results table created!")

def process_df(dataframe):
    boolean_column = "neutral"
    dataframe[boolean_column] = dataframe[boolean_column].apply(lambda x: 1 if str(x).strip().upper() == "TRUE" else 0)

    dataframe["home_score"] = dataframe["home_score"].apply(lambda x: None if pd.isna(x) or x == '' else int(x))
    dataframe["away_score"] = dataframe["away_score"].apply(lambda x: None if pd.isna(x) or x == '' else int(x))

    return dataframe

def check_missing_matches(results_dataframe):
    scorers_dataframe = pd.read_csv("Data/goalscorers.csv")
    shootouts_dataframe = pd.read_csv("Data/shootouts.csv")

    results_dataframe = add_missing_matches(results_dataframe, scorers_dataframe)
    results_dataframe = add_missing_matches(results_dataframe, shootouts_dataframe)

    return results_dataframe

def add_missing_matches(results_dataframe, dataframe):

    new_entries = []

    for index, row in dataframe.iterrows():
        date = row["date"]
        home_team = row["home_team"]
        away_team = row["away_team"]

        match_exists = results_dataframe[(results_dataframe["date"] == date) & (results_dataframe["home_team"] == home_team) & (results_dataframe["away_team"] == away_team)].empty

        if match_exists:
            new_entry = {
                "date": date,
                "home_team": home_team,
                "away_team": away_team,
                "home_score": None,
                "away_score": None,
                "tournament": None,
                "city": None,
                "country": None,
                "neutral": None
            }
            print(match_exists, date, home_team, away_team)
            new_entries.append(new_entry)

    if new_entries:
        new_matches_dataframe = pd.DataFrame(new_entries)
        results_dataframe = pd.concat([results_dataframe, new_matches_dataframe], ignore_index=True)
        print("New matches added to the matches table!")
    else:
        print("No new matches to add to the matches table!")
    
    return results_dataframe

def check_for_different_named_countries(results_dataframe):
    countries_dataframe = pd.read_csv("Outputs/countries_utf8.csv")
    existing_countries = countries_dataframe["Official_Name"].tolist()

    replacement_dict = {}
    unique_teams = set(results_dataframe["home_team"].unique()).union(set(results_dataframe["away_team"].unique()))
    
    for country_team in unique_teams:
        for country in existing_countries:
            if unidecode(country_team).strip().lower() == unidecode(country).strip().lower():
                replacement_dict[country_team] = country
                break 
                
    results_dataframe[['home_team', 'away_team']] = results_dataframe[['home_team', 'away_team']].replace(replacement_dict)

    return results_dataframe


def replace_names_with_ISO(results_dataframe):
    countries_dataframe = pd.read_csv("Outputs/countries_utf8.csv")
    former_names_dataframe = pd.read_csv("Data/former_names.csv")

    display_name_country_to_iso = dict(zip(countries_dataframe['Display_Name'], countries_dataframe['ISO_Code']))
    results_dataframe[['home_team', 'away_team']] = results_dataframe[['home_team', 'away_team']].replace(display_name_country_to_iso)

    official_name_country_to_iso = dict(zip(countries_dataframe['Official_Name'], countries_dataframe['ISO_Code']))
    results_dataframe[['home_team', 'away_team']] = results_dataframe[['home_team', 'away_team']].replace(official_name_country_to_iso)

    former_to_current = dict(zip(former_names_dataframe['former'], former_names_dataframe['current']))
    results_dataframe[['home_team', 'away_team']] = results_dataframe[['home_team', 'away_team']].replace(former_to_current)

    return results_dataframe

def load_data_into_mysql(connection, file_path):
    try:
        cursor = connection.cursor()
        cursor.execute("""
            LOAD DATA LOCAL INFILE %s
            INTO TABLE results
            FIELDS TERMINATED BY ','
            ENCLOSED BY '"'
            LINES TERMINATED BY '\n'
            IGNORE 1 ROWS
            (date, home_team, away_team, home_score, away_score, tournament, city, country, neutral)
        """, (file_path,))
        connection.commit()
        cursor.close()
        print(f"Data loaded into MySQL from {file_path}")
    except mysql.connector.Error as err:
        print(f"Error loading data into MySQL: {err}")
        with open(file_path, 'r') as file:
            lines = file.readlines()
            print(f"Problematic line: {lines[47981]}")


def initiate_results_table_creation(connection):
    results_csv = "Data/results.csv"
    altered_results_csv = "Outputs/results_altered.csv"

    create_results_table(connection)

    dataframe = pd.read_csv(results_csv)
    dataframe = process_df(dataframe)
    #dataframe = check_missing_matches(dataframe)
    dataframe = check_for_different_named_countries(dataframe)
    dataframe = replace_names_with_ISO(dataframe)

    dataframe.to_csv(altered_results_csv, index=False)

    load_data_into_mysql(connection, altered_results_csv)
    print("Results table populated with data!")