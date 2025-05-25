import mysql.connector
from countriesTableCreation import initiate_countries_table_creation
from resultsTableCreation import initiate_results_table_creation
from scorersTableCreation import initiate_scorers_table_creation
from shootoutsTableCreation import initiate_shootouts_table_creation
from Views.countryViews import createCountryViews
from Views.yearViews import createYearViews
from Views.statsViews import createStatsViews
from Views.playerViews import createPlayerViews

def create_db():
    connection = mysql.connector.connect(
        host="localhost",
        user="root",
        password="root",
    )
    cursor = connection.cursor()
    cursor.execute("CREATE DATABASE IF NOT EXISTS mye030 DEFAULT CHARSET= utf8mb4 COLLATE= utf8mb4_unicode_ci;")
    cursor.close()
    connection.close()

def connect_to_db():
    connection = mysql.connector.connect(
        host="localhost",
        user="root",
        password="root",
        database="mye030",
        allow_local_infile=True
    )
    return connection

if __name__ == "__main__":
    create_db()
    connection = connect_to_db()
    print("Connection: ", connection)

    initiate_countries_table_creation(connection)
    initiate_results_table_creation(connection)
    initiate_scorers_table_creation(connection)
    initiate_shootouts_table_creation(connection)

    createCountryViews(connection)
    createYearViews(connection)
    createStatsViews(connection)
    createPlayerViews(connection)