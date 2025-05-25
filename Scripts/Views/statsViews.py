
def createStatsViews(connection):
        cursor = connection.cursor()
        cursor.execute("""
                CREATE VIEW GlobalStats AS
                SELECT 
                    x.ISO_Code, x.Display_Name, x.total_wins,
                    ((x.total_wins * 3) + total_ties) AS score,
                    (YEAR(x.end_year) - YEAR(x.start_year)) AS years_playing
                FROM xores x
        """)