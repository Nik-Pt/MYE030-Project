def createCountryViews(connection):
    cursor = connection.cursor()
    cursor.execute("""
        CREATE VIEW country_years AS
        SELECT
            c.ISO_Code, c.Display_Name, c.Official_Name, MIN(m.date) AS start_year, MAX(m.date) AS end_year
        FROM
            countries c
        JOIN
            results m ON (m.home_team = c.ISO_Code OR m.away_team = c.ISO_Code)
        GROUP BY
            c.ISO_Code;
    """)
    
    cursor.execute("""
        CREATE VIEW stats AS
        SELECT 
            c.ISO_Code, 
            SUM(CASE
                WHEN m.home_team = c.ISO_Code AND m.home_score > m.away_score THEN 1
                ELSE 0
            END) AS home_wins,
            SUM(CASE
                WHEN m.away_team = c.ISO_Code AND m.away_score > m.home_score THEN 1
                ELSE 0
            END) AS away_wins,
            SUM(CASE
                WHEN m.away_team = c.ISO_Code AND m.away_score > m.home_score THEN 1
                WHEN m.home_team = c.ISO_Code AND m.home_score > m.away_score THEN 1
                ELSE 0
            END) AS total_wins,
            SUM(CASE
                WHEN m.home_team = c.ISO_Code AND m.home_score < m.away_score THEN 1
                ELSE 0
            END) AS home_losses,
            SUM(CASE
                WHEN m.away_team = c.ISO_Code AND m.away_score < m.home_score THEN 1
                ELSE 0
            END) AS away_losses,
            SUM(CASE
                WHEN m.away_team = c.ISO_Code AND m.away_score < m.home_score THEN 1
                WHEN m.home_team = c.ISO_Code AND m.home_score < m.away_score THEN 1
                ELSE 0
            END) AS total_losses,
            SUM(CASE
                WHEN m.home_team = c.ISO_Code AND m.home_score =  m.away_score THEN 1
                ELSE 0
            END) AS home_ties,
            SUM(CASE
                WHEN m.away_team = c.ISO_Code AND m.away_score = m.home_score THEN 1
                ELSE 0
            END) AS away_ties,
            SUM(CASE
                WHEN m.away_team = c.ISO_Code AND m.away_score = m.home_score THEN 1
                WHEN m.home_team = c.ISO_Code AND m.home_score = m.away_score THEN 1
                ELSE 0
            END) AS total_ties,
            SUM(CASE
                WHEN m.home_team = c.ISO_Code THEN 1
                ELSE 0
            END) AS home_matches,
            SUM(CASE
                WHEN m.away_team = c.ISO_Code THEN 1
                ELSE 0
            END) AS away_matches,
            SUM(CASE
                WHEN m.away_team = c.ISO_Code THEN 1
                WHEN m.home_team = c.ISO_Code THEN 1
                ELSE 0
            END) AS total_matches
        FROM 
            countries c
        JOIN  
            results m ON (m.home_team = c.ISO_Code OR m.away_team = c.ISO_Code)
        GROUP BY
            c.ISO_Code;
    """)

    cursor.execute("""
        CREATE VIEW Xores AS
        SELECT
           c.Display_Name, c.Official_Name, c.start_year, c.end_year, s.*
        FROM
            country_years c
        JOIN
            stats s ON (c.ISO_Code = s.ISO_Code)
        GROUP BY
            c.ISO_Code;
    """)