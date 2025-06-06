CREATE DATABASE  IF NOT EXISTS `mye030` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mye030`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: mye030
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `ISO` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ISO3` varchar(3) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ISO_Code` int NOT NULL,
  `FIPS` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Display_Name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Official_Name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Capital` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Continent` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CurrencyCode` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CurrencyName` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Phone` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Region_Code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Region_Name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Subregion_Code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Subregion_Name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Intermediate_Region_Code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Intermediate_Region_Name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Developed_or_Developing` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Small_Island_Developing_States` tinyint(1) DEFAULT NULL,
  `Land_Locked_Developing_Countries` tinyint(1) DEFAULT NULL,
  `Least_Developed_Countries` tinyint(1) DEFAULT NULL,
  `Area_SqKm` decimal(10,0) DEFAULT NULL,
  `Population` int DEFAULT NULL,
  PRIMARY KEY (`ISO_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `country_years`
--

DROP TABLE IF EXISTS `country_years`;
/*!50001 DROP VIEW IF EXISTS `country_years`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `country_years` AS SELECT 
 1 AS `ISO_Code`,
 1 AS `Display_Name`,
 1 AS `Official_Name`,
 1 AS `start_year`,
 1 AS `end_year`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `final`
--

DROP TABLE IF EXISTS `final`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `final` (
  `scorer` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ISO_Code` int NOT NULL,
  `team` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `starting_year` bigint DEFAULT NULL,
  `ending_year` bigint DEFAULT NULL,
  `total_goals` bigint NOT NULL DEFAULT '0',
  `max_goals_per_match` bigint DEFAULT NULL,
  `goals_ratio` decimal(24,4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `globalstats`
--

DROP TABLE IF EXISTS `globalstats`;
/*!50001 DROP VIEW IF EXISTS `globalstats`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `globalstats` AS SELECT 
 1 AS `ISO_Code`,
 1 AS `Display_Name`,
 1 AS `total_wins`,
 1 AS `score`,
 1 AS `years_playing`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `players`
--

DROP TABLE IF EXISTS `players`;
/*!50001 DROP VIEW IF EXISTS `players`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `players` AS SELECT 
 1 AS `scorer`,
 1 AS `team`,
 1 AS `starting_year`,
 1 AS `ending_year`,
 1 AS `total_goals`,
 1 AS `max_goals_per_match`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `results` (
  `date` date NOT NULL,
  `home_team` int NOT NULL,
  `away_team` int NOT NULL,
  `home_score` int DEFAULT NULL,
  `away_score` int DEFAULT NULL,
  `tournament` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `city` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `neutral` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`date`,`home_team`,`away_team`,`tournament`),
  KEY `home_team` (`home_team`),
  KEY `away_team` (`away_team`),
  CONSTRAINT `results_ibfk_1` FOREIGN KEY (`home_team`) REFERENCES `countries` (`ISO_Code`),
  CONSTRAINT `results_ibfk_2` FOREIGN KEY (`away_team`) REFERENCES `countries` (`ISO_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scorers`
--

DROP TABLE IF EXISTS `scorers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scorers` (
  `date` date NOT NULL,
  `home_team` int NOT NULL,
  `away_team` int NOT NULL,
  `team` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `scorer` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `minute` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `own_goal` tinyint(1) DEFAULT NULL,
  `penalty` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`date`,`home_team`,`away_team`,`scorer`,`minute`),
  CONSTRAINT `scorers_ibfk_1` FOREIGN KEY (`date`, `home_team`, `away_team`) REFERENCES `results` (`date`, `home_team`, `away_team`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shootouts`
--

DROP TABLE IF EXISTS `shootouts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shootouts` (
  `date` date NOT NULL,
  `home_team` int NOT NULL,
  `away_team` int NOT NULL,
  `winner` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_shooter` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`date`,`home_team`,`away_team`,`winner`),
  CONSTRAINT `shootouts_ibfk_1` FOREIGN KEY (`date`, `home_team`, `away_team`) REFERENCES `results` (`date`, `home_team`, `away_team`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `stats`
--

DROP TABLE IF EXISTS `stats`;
/*!50001 DROP VIEW IF EXISTS `stats`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `stats` AS SELECT 
 1 AS `ISO_Code`,
 1 AS `home_wins`,
 1 AS `away_wins`,
 1 AS `total_wins`,
 1 AS `home_losses`,
 1 AS `away_losses`,
 1 AS `total_losses`,
 1 AS `home_ties`,
 1 AS `away_ties`,
 1 AS `total_ties`,
 1 AS `home_matches`,
 1 AS `away_matches`,
 1 AS `total_matches`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `xores`
--

DROP TABLE IF EXISTS `xores`;
/*!50001 DROP VIEW IF EXISTS `xores`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `xores` AS SELECT 
 1 AS `Display_Name`,
 1 AS `Official_Name`,
 1 AS `start_year`,
 1 AS `end_year`,
 1 AS `ISO_Code`,
 1 AS `home_wins`,
 1 AS `away_wins`,
 1 AS `total_wins`,
 1 AS `home_losses`,
 1 AS `away_losses`,
 1 AS `total_losses`,
 1 AS `home_ties`,
 1 AS `away_ties`,
 1 AS `total_ties`,
 1 AS `home_matches`,
 1 AS `away_matches`,
 1 AS `total_matches`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `yearly_goals_matches`
--

DROP TABLE IF EXISTS `yearly_goals_matches`;
/*!50001 DROP VIEW IF EXISTS `yearly_goals_matches`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `yearly_goals_matches` AS SELECT 
 1 AS `scorer`,
 1 AS `ISO_Code`,
 1 AS `year`,
 1 AS `total_year_matches`,
 1 AS `total_year_goals`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `yearlymatchstats`
--

DROP TABLE IF EXISTS `yearlymatchstats`;
/*!50001 DROP VIEW IF EXISTS `yearlymatchstats`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `yearlymatchstats` AS SELECT 
 1 AS `ISO_Code`,
 1 AS `year`,
 1 AS `total_year_matches`,
 1 AS `total_year_draws`,
 1 AS `total_year_wins`,
 1 AS `total_year_losses`,
 1 AS `had_shootouts`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `country_years`
--

/*!50001 DROP VIEW IF EXISTS `country_years`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `country_years` AS select `c`.`ISO_Code` AS `ISO_Code`,`c`.`Display_Name` AS `Display_Name`,`c`.`Official_Name` AS `Official_Name`,min(`m`.`date`) AS `start_year`,max(`m`.`date`) AS `end_year` from (`countries` `c` join `results` `m` on(((`m`.`home_team` = `c`.`ISO_Code`) or (`m`.`away_team` = `c`.`ISO_Code`)))) group by `c`.`ISO_Code` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `globalstats`
--

/*!50001 DROP VIEW IF EXISTS `globalstats`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `globalstats` AS select `x`.`ISO_Code` AS `ISO_Code`,`x`.`Display_Name` AS `Display_Name`,`x`.`total_wins` AS `total_wins`,((`x`.`total_wins` * 3) + `x`.`total_ties`) AS `score`,(year(`x`.`end_year`) - year(`x`.`start_year`)) AS `years_playing` from `xores` `x` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `players`
--

/*!50001 DROP VIEW IF EXISTS `players`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `players` AS select `s`.`scorer` AS `scorer`,`s`.`team` AS `team`,min(year(`s`.`date`)) AS `starting_year`,max(year(`s`.`date`)) AS `ending_year`,count(0) AS `total_goals`,max(`match_goals`.`max_goals`) AS `max_goals_per_match` from (`scorers` `s` join (select `scorers`.`date` AS `date`,`scorers`.`home_team` AS `home_team`,`scorers`.`away_team` AS `away_team`,`scorers`.`scorer` AS `scorer`,count(0) AS `max_goals` from `scorers` group by `scorers`.`date`,`scorers`.`home_team`,`scorers`.`away_team`,`scorers`.`scorer`) `match_goals` on(((`s`.`date` = `match_goals`.`date`) and (`s`.`home_team` = `match_goals`.`home_team`) and (`s`.`away_team` = `match_goals`.`away_team`) and (`s`.`scorer` = `match_goals`.`scorer`)))) group by `s`.`team`,`s`.`scorer` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `stats`
--

/*!50001 DROP VIEW IF EXISTS `stats`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `stats` AS select `c`.`ISO_Code` AS `ISO_Code`,sum((case when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` > `m`.`away_score`)) then 1 else 0 end)) AS `home_wins`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` > `m`.`home_score`)) then 1 else 0 end)) AS `away_wins`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` > `m`.`home_score`)) then 1 when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` > `m`.`away_score`)) then 1 else 0 end)) AS `total_wins`,sum((case when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` < `m`.`away_score`)) then 1 else 0 end)) AS `home_losses`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` < `m`.`home_score`)) then 1 else 0 end)) AS `away_losses`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` < `m`.`home_score`)) then 1 when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` < `m`.`away_score`)) then 1 else 0 end)) AS `total_losses`,sum((case when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` = `m`.`away_score`)) then 1 else 0 end)) AS `home_ties`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` = `m`.`home_score`)) then 1 else 0 end)) AS `away_ties`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` = `m`.`home_score`)) then 1 when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` = `m`.`away_score`)) then 1 else 0 end)) AS `total_ties`,sum((case when (`m`.`home_team` = `c`.`ISO_Code`) then 1 else 0 end)) AS `home_matches`,sum((case when (`m`.`away_team` = `c`.`ISO_Code`) then 1 else 0 end)) AS `away_matches`,sum((case when (`m`.`away_team` = `c`.`ISO_Code`) then 1 when (`m`.`home_team` = `c`.`ISO_Code`) then 1 else 0 end)) AS `total_matches` from (`countries` `c` join `results` `m` on(((`m`.`home_team` = `c`.`ISO_Code`) or (`m`.`away_team` = `c`.`ISO_Code`)))) group by `c`.`ISO_Code` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `xores`
--

/*!50001 DROP VIEW IF EXISTS `xores`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `xores` AS select `c`.`Display_Name` AS `Display_Name`,`c`.`Official_Name` AS `Official_Name`,`c`.`start_year` AS `start_year`,`c`.`end_year` AS `end_year`,`s`.`ISO_Code` AS `ISO_Code`,`s`.`home_wins` AS `home_wins`,`s`.`away_wins` AS `away_wins`,`s`.`total_wins` AS `total_wins`,`s`.`home_losses` AS `home_losses`,`s`.`away_losses` AS `away_losses`,`s`.`total_losses` AS `total_losses`,`s`.`home_ties` AS `home_ties`,`s`.`away_ties` AS `away_ties`,`s`.`total_ties` AS `total_ties`,`s`.`home_matches` AS `home_matches`,`s`.`away_matches` AS `away_matches`,`s`.`total_matches` AS `total_matches` from (`country_years` `c` join `stats` `s` on((`c`.`ISO_Code` = `s`.`ISO_Code`))) group by `c`.`ISO_Code` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `yearly_goals_matches`
--

/*!50001 DROP VIEW IF EXISTS `yearly_goals_matches`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `yearly_goals_matches` AS select `s`.`scorer` AS `scorer`,`y`.`ISO_Code` AS `ISO_Code`,`y`.`year` AS `year`,`y`.`total_year_matches` AS `total_year_matches`,count(0) AS `total_year_goals` from ((`scorers` `s` join `countries` `c` on(((`c`.`Official_Name` = `s`.`team`) or (`c`.`Display_Name` = `s`.`team`)))) join `yearlymatchstats` `y` on(((year(`s`.`date`) = `y`.`year`) and (`c`.`ISO_Code` = `y`.`ISO_Code`)))) group by `s`.`scorer`,`y`.`ISO_Code`,`y`.`year`,`y`.`total_year_matches` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `yearlymatchstats`
--

/*!50001 DROP VIEW IF EXISTS `yearlymatchstats`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `yearlymatchstats` AS select `c`.`ISO_Code` AS `ISO_Code`,year(`r`.`date`) AS `year`,sum((case when ((`r`.`away_team` = `c`.`ISO_Code`) or (`r`.`home_team` = `c`.`ISO_Code`)) then 1 else 0 end)) AS `total_year_matches`,sum((case when ((`r`.`away_team` = `c`.`ISO_Code`) and (`r`.`away_score` = `r`.`home_score`)) then 1 when ((`r`.`home_team` = `c`.`ISO_Code`) and (`r`.`home_score` = `r`.`away_score`)) then 1 else 0 end)) AS `total_year_draws`,sum((case when ((`r`.`away_team` = `c`.`ISO_Code`) and (`r`.`away_score` > `r`.`home_score`)) then 1 when ((`r`.`home_team` = `c`.`ISO_Code`) and (`r`.`away_score` < `r`.`home_score`)) then 1 else 0 end)) AS `total_year_wins`,sum((case when ((`r`.`away_team` = `c`.`ISO_Code`) and (`r`.`away_score` < `r`.`home_score`)) then 1 when ((`r`.`home_team` = `c`.`ISO_Code`) and (`r`.`away_score` > `r`.`home_score`)) then 1 else 0 end)) AS `total_year_losses`,sum((case when ((`r`.`date` = `s`.`date`) and (`r`.`away_team` = `s`.`away_team`) and (`r`.`home_team` = `s`.`home_team`) and ((`r`.`away_team` = `c`.`ISO_Code`) or (`r`.`home_team` = `c`.`ISO_Code`))) then 1 else 0 end)) AS `had_shootouts` from ((`countries` `c` join `results` `r` on(((`r`.`home_team` = `c`.`ISO_Code`) or (`r`.`away_team` = `c`.`ISO_Code`)))) left join `shootouts` `s` on(((`s`.`date` = `r`.`date`) and (`s`.`home_team` = `r`.`home_team`) and (`s`.`away_team` = `s`.`away_team`)))) group by `c`.`ISO_Code`,`year` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-25 18:55:56
