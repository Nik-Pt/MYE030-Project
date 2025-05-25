import pandas as pd
import mysql.connector
from unidecode import unidecode

def create_countries_table(connection):
    cursor = connection.cursor()
    print("Creating Countries table...")

    cursor.execute("""
        CREATE TABLE IF NOT EXISTS Countries (
            ISO VARCHAR(2),
            ISO3 VARCHAR(3),
            ISO_Code INT PRIMARY KEY,
            FIPS VARCHAR(50),
            Display_Name VARCHAR(50),
            Official_Name VARCHAR(100),
            Capital VARCHAR(50),
            Continent VARCHAR(50),
            CurrencyCode VARCHAR(50),
            CurrencyName VARCHAR(50),
            Phone VARCHAR(50),
            Region_Code VARCHAR(50),
            Region_Name VARCHAR(50),
            Subregion_Code VARCHAR(50),
            Subregion_Name VARCHAR(50),
            Intermediate_Region_Code VARCHAR(50),
            Intermediate_Region_Name VARCHAR(50),
            Status VARCHAR(50),
            Developed_or_Developing VARCHAR(50),
            Small_Island_Developing_States BOOLEAN,
            Land_Locked_Developing_Countries BOOLEAN,
            Least_Developed_Countries BOOLEAN,
            Area_SqKm NUMERIC,
            Population INTEGER
        ) ENGINE=InnoDB DEFAULT CHARSET= utf8mb4 COLLATE= utf8mb4_unicode_ci;
    """)

    print("Countries table created!")

def convert_csv_to_utf8(input_file, output_file):
    df = pd.read_csv(input_file, encoding='ISO-8859-1')
    df.to_csv(output_file, encoding="utf8", index=False)

def process_df(dataframe):
    boolean_columns = [
        "Small Island Developing States (SIDS)",
        "Land Locked Developing Countries (LLDC)",
        "Least Developed Countries (LDC)"
    ]
    for column in boolean_columns:
        dataframe[column] = dataframe[column].apply(lambda x: 1 if str(x).strip().upper() == "TRUE" else 0)
    return dataframe

def check_for_missing_countries(countries_dataframe):
    results_dataframe = pd.read_csv("Data/results.csv")
    former_names_dataframe = pd.read_csv("Data/former_names.csv")
    shootouts_dataframe = pd.read_csv("Data/shootouts.csv")
    goalscorers_dataframe = pd.read_csv("Data/goalscorers.csv")

    home_teams_results = set(results_dataframe["home_team"].unique())
    away_teams_results = set(results_dataframe["away_team"].unique())
    home_teams_shootouts = set(shootouts_dataframe["home_team"].unique())
    away_teams_shootouts = set(shootouts_dataframe["away_team"].unique())
    home_teams_goalscorers = set(goalscorers_dataframe["home_team"].unique())
    away_teams_goalscorers = set(goalscorers_dataframe["away_team"].unique())

    countries_in_results = (home_teams_results.union(away_teams_results)
                            .union(home_teams_shootouts)
                            .union(away_teams_shootouts)
                            .union(home_teams_goalscorers)
                            .union(away_teams_goalscorers))  
    
    former_names_to_current = dict(zip(former_names_dataframe["former"], former_names_dataframe["current"]))

    existing_countries = countries_dataframe["Display_Name"]
    existing_official_names = countries_dataframe["Official_Name"]

    countries_dataframe["Official_Name"] = countries_dataframe["Official_Name"].fillna(countries_dataframe["Display_Name"])
    countries_dataframe["Display_Name"] = countries_dataframe["Display_Name"].fillna(countries_dataframe["Official_Name"])

    existing_iso_codes = set(countries_dataframe["ISO_Code"])
    iso_code_counter = max(existing_iso_codes) + 1 if existing_iso_codes else 1
    new_entries = []

    for country in countries_in_results:
        current_name = former_names_to_current.get(country, country)
        if (check_team_in_countries_table(current_name, unidecode(current_name), country, existing_countries, existing_official_names)):
            new_entry ={
                "ISO": "",
                "ISO3": "",
                "ISO_Code": iso_code_counter,
                "FIPS": "",
                "Display_Name": current_name,
                "Official_Name": current_name,
                "Capital": "",
                "Continent": "",
                "CurrencyCode": "",
                "CurrencyName": "",
                "Phone": "",
                "Region_Code": "",
                "Region_Name": "",
                "Subregion_Code": "",
                "Subregion_Name": "",
                "Intermediate_Region_Code": "",
                "Intermediate_Region_Name": "",
                "Status": "",
                "Developed_or_Developing": "",
                "Small_Island_Developing_States": 0,
                "Land_Locked_Developing_Countries": 0,
                "Least_Developed_Countries": 0,
                "Area_SqKm": 0,
                "Population": 0
            }
            new_entries.append(new_entry)
            iso_code_counter += 1

    if new_entries:
        new_countries_dataframe = pd.DataFrame(new_entries)
        countries_dataframe = pd.concat([countries_dataframe, new_countries_dataframe], ignore_index=True)
        print("New countries added to the countries table!")
    else:
        print("No new countries to add to the countries table!")
    
    return countries_dataframe

def check_team_in_countries_table(country_name, country_name_normalized, country, existing_countries, existing_official_names):
    if not existing_countries.str.contains(country_name, case = False, na = False).any(): 
        if not existing_countries.str.contains(country_name, case = False, na = False).any() and not existing_countries.str.contains(country_name_normalized, case = False, na = False).any():
            return True
    return False

def load_data_into_mysql(connection, file_path):
    try:
        cursor = connection.cursor()
        cursor.execute("""
            LOAD DATA LOCAL INFILE %s
            INTO TABLE Countries
            FIELDS TERMINATED BY ','
            ENCLOSED BY '"'
            LINES TERMINATED BY '\n'
            IGNORE 1 ROWS
            (ISO, ISO3, ISO_CODE, FIPS, Display_Name, Official_Name,
            Capital, Continent, CurrencyCode, CurrencyName, Phone, Region_Code,
            Region_Name, Subregion_Code, Subregion_Name, Intermediate_Region_Code, 
            Intermediate_Region_Name, Status, Developed_or_Developing, Small_Island_Developing_States, 
            Land_Locked_Developing_Countries, Least_Developed_Countries, Area_SqKm, Population)
        """, (file_path,))
        connection.commit()
        cursor.close()
        print(f"Data loaded into MySQL from {file_path}")
    except mysql.connector.Error as err:
        print(f"Error loading data into MySQL: {err}")

def initiate_countries_table_creation(connection):
    countries_input_csv = "Data/countries.csv"
    countries_output_csv = "Outputs/countries_utf8.csv"

    create_countries_table(connection)
    convert_csv_to_utf8(countries_input_csv, countries_output_csv)

    dataframe = pd.read_csv(countries_output_csv)
    dataframe = process_df(dataframe)
    dataframe = check_for_missing_countries(dataframe)
    dataframe.to_csv(countries_output_csv, index=False)

    load_data_into_mysql(connection, countries_output_csv)
    print("Countries table populated with data!")