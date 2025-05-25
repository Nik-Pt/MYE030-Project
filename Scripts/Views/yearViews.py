import mysql.connector

def createYearViews(connection):
        cursor = connection.cursor()
        cursor.execute("""
                CREATE VIEW YearlyMatchStats AS
                SELECT c.ISO_Code, YEAR(r.date) AS year,
                SUM(CASE
                        WHEN (r.away_team = c.ISO_Code OR r.home_team = c.ISO_Code) THEN 1
                        ELSE 0
                        END) AS total_year_matches,
                SUM(CASE
                        WHEN (r.away_team = c.ISO_Code AND r.away_score = r.home_score) THEN 1
                        WHEN (r.home_team = c.ISO_Code AND r.home_score = r.away_score) THEN 1
                        ELSE 0
                        END) AS total_year_draws,
                SUM(CASE
                        WHEN (r.away_team = c.ISO_Code AND r.away_score > r.home_score) THEN 1
                        WHEN (r.home_team = c.ISO_Code AND r.away_score < r.home_score) THEN 1
                        ELSE 0
                        END) AS total_year_wins,
                SUM(CASE
                        WHEN (r.away_team = c.ISO_Code AND r.away_score < r.home_score) THEN 1
                        WHEN (r.home_team = c.ISO_Code AND r.away_score > r.home_score) THEN 1
                        ELSE 0
                        END) AS total_year_losses,
                SUM(CASE
                        WHEN ((r.date = s.date AND r.away_team = s.away_team AND r.home_team = s.home_team) AND (r.away_team = c.ISO_Code OR r.home_team = c.ISO_Code)) THEN 1
                        ELSE 0
                        END) AS had_shootouts
                FROM countries c
                JOIN
                results r ON (r.home_team = c.ISO_Code OR r.away_team = c.ISO_Code)
                LEFT JOIN
                shootouts s ON (s.date = r.date AND s.home_team = r.home_team AND s.away_team = s.away_team)
                GROUP BY c.ISO_Code, year;
        """)