def createPlayerViews(connection):
        cursor = connection.cursor()
        cursor.execute("""
                CREATE VIEW players AS
                SELECT
                        s.scorer, s.team, 
                        MIN(YEAR(s.date)) AS starting_year, 
                        MAX(YEAR(s.date)) AS ending_year,
                        COUNT(*) AS total_goals,
                        MAX(match_goals.max_goals) AS max_goals_per_match
                FROM 
                        scorers s
                JOIN
                        (SELECT date, home_team, away_team, scorer, COUNT(*) AS max_goals
                        FROM scorers
                        GROUP BY date, home_team, away_team, scorer) AS match_goals
                ON s.date = match_goals.date
                        AND s.home_team = match_goals.home_team
                        AND s.away_team = match_goals.away_team
                        AND s.scorer = match_goals.scorer
                GROUP BY 
                        s.team, s.scorer
        """)

        cursor.execute("""
                CREATE VIEW yearly_goals_matches AS
                SELECT 
                       s.scorer,
                       y.ISO_Code,
                       y.year,
                       y.total_year_matches,
                       COUNT(*) AS total_year_goals
                FROM scorers s
                JOIN countries c
                       ON (c.Official_Name = s.team OR c.Display_Name = s.team)
                JOIN yearlymatchstats y
                       ON (YEAR(s.date) = y.year AND c.ISO_Code = y.ISO_Code)
                GROUP BY s.scorer, y.ISO_Code, y.year, y.total_year_matches
        """)

        cursor.execute("""
                CREATE TABLE final AS
                SELECT 
                        p.scorer,
                        c.ISO_Code,
                        p.team,
                        p.starting_year,
                        p.ending_year,
                        p.total_goals,
                        p.max_goals_per_match,
                        (p.total_goals / SUM(y.total_year_matches)) AS goals_ratio
                FROM players p
                JOIN countries c
                        ON (c.Official_Name = p.team OR c.Display_Name = p.team)
                JOIN yearlymatchstats y
                        ON y.ISO_Code = c.ISO_Code
                        AND y.year BETWEEN p.starting_year AND p.ending_year
                GROUP BY
                        p.scorer,
                        c.ISO_Code,
                        p.team,
                        p.starting_year,
                        p.ending_year,
                        p.total_goals,
                        p.max_goals_per_match
        """)