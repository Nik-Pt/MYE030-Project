-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mye030
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mye030
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mye030` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `mye030` ;

-- -----------------------------------------------------
-- Table `mye030`.`countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`countries` (
  `ISO` VARCHAR(2) NULL DEFAULT NULL,
  `ISO3` VARCHAR(3) NULL DEFAULT NULL,
  `ISO_Code` INT NOT NULL,
  `FIPS` VARCHAR(50) NULL DEFAULT NULL,
  `Display_Name` VARCHAR(50) NULL DEFAULT NULL,
  `Official_Name` VARCHAR(100) NULL DEFAULT NULL,
  `Capital` VARCHAR(50) NULL DEFAULT NULL,
  `Continent` VARCHAR(50) NULL DEFAULT NULL,
  `CurrencyCode` VARCHAR(50) NULL DEFAULT NULL,
  `CurrencyName` VARCHAR(50) NULL DEFAULT NULL,
  `Phone` VARCHAR(50) NULL DEFAULT NULL,
  `Region_Code` VARCHAR(50) NULL DEFAULT NULL,
  `Region_Name` VARCHAR(50) NULL DEFAULT NULL,
  `Subregion_Code` VARCHAR(50) NULL DEFAULT NULL,
  `Subregion_Name` VARCHAR(50) NULL DEFAULT NULL,
  `Intermediate_Region_Code` VARCHAR(50) NULL DEFAULT NULL,
  `Intermediate_Region_Name` VARCHAR(50) NULL DEFAULT NULL,
  `Status` VARCHAR(50) NULL DEFAULT NULL,
  `Developed_or_Developing` VARCHAR(50) NULL DEFAULT NULL,
  `Small_Island_Developing_States` TINYINT(1) NULL DEFAULT NULL,
  `Land_Locked_Developing_Countries` TINYINT(1) NULL DEFAULT NULL,
  `Least_Developed_Countries` TINYINT(1) NULL DEFAULT NULL,
  `Area_SqKm` DECIMAL(10,0) NULL DEFAULT NULL,
  `Population` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ISO_Code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `mye030`.`final`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`final` (
  `scorer` VARCHAR(100) NOT NULL,
  `ISO_Code` INT NOT NULL,
  `team` VARCHAR(50) NULL DEFAULT NULL,
  `starting_year` BIGINT NULL DEFAULT NULL,
  `ending_year` BIGINT NULL DEFAULT NULL,
  `total_goals` BIGINT NOT NULL DEFAULT '0',
  `max_goals_per_match` BIGINT NULL DEFAULT NULL,
  `goals_ratio` DECIMAL(24,4) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `mye030`.`results`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`results` (
  `date` DATE NOT NULL,
  `home_team` INT NOT NULL,
  `away_team` INT NOT NULL,
  `home_score` INT NULL DEFAULT NULL,
  `away_score` INT NULL DEFAULT NULL,
  `tournament` VARCHAR(100) NOT NULL,
  `city` VARCHAR(100) NULL DEFAULT NULL,
  `country` VARCHAR(100) NULL DEFAULT NULL,
  `neutral` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`date`, `home_team`, `away_team`, `tournament`),
  INDEX `home_team` (`home_team` ASC) VISIBLE,
  INDEX `away_team` (`away_team` ASC) VISIBLE,
  CONSTRAINT `results_ibfk_1`
    FOREIGN KEY (`home_team`)
    REFERENCES `mye030`.`countries` (`ISO_Code`),
  CONSTRAINT `results_ibfk_2`
    FOREIGN KEY (`away_team`)
    REFERENCES `mye030`.`countries` (`ISO_Code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `mye030`.`scorers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`scorers` (
  `date` DATE NOT NULL,
  `home_team` INT NOT NULL,
  `away_team` INT NOT NULL,
  `team` VARCHAR(50) NULL DEFAULT NULL,
  `scorer` VARCHAR(100) NOT NULL,
  `minute` VARCHAR(10) NOT NULL,
  `own_goal` TINYINT(1) NULL DEFAULT NULL,
  `penalty` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`date`, `home_team`, `away_team`, `scorer`, `minute`),
  CONSTRAINT `scorers_ibfk_1`
    FOREIGN KEY (`date` , `home_team` , `away_team`)
    REFERENCES `mye030`.`results` (`date` , `home_team` , `away_team`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `mye030`.`shootouts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`shootouts` (
  `date` DATE NOT NULL,
  `home_team` INT NOT NULL,
  `away_team` INT NOT NULL,
  `winner` VARCHAR(50) NOT NULL,
  `first_shooter` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`date`, `home_team`, `away_team`, `winner`),
  CONSTRAINT `shootouts_ibfk_1`
    FOREIGN KEY (`date` , `home_team` , `away_team`)
    REFERENCES `mye030`.`results` (`date` , `home_team` , `away_team`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

USE `mye030` ;

-- -----------------------------------------------------
-- Placeholder table for view `mye030`.`country_years`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`country_years` (`ISO_Code` INT, `Display_Name` INT, `Official_Name` INT, `start_year` INT, `end_year` INT);

-- -----------------------------------------------------
-- Placeholder table for view `mye030`.`globalstats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`globalstats` (`ISO_Code` INT, `Display_Name` INT, `total_wins` INT, `score` INT, `years_playing` INT);

-- -----------------------------------------------------
-- Placeholder table for view `mye030`.`players`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`players` (`scorer` INT, `team` INT, `starting_year` INT, `ending_year` INT, `total_goals` INT, `max_goals_per_match` INT);

-- -----------------------------------------------------
-- Placeholder table for view `mye030`.`stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`stats` (`ISO_Code` INT, `home_wins` INT, `away_wins` INT, `total_wins` INT, `home_losses` INT, `away_losses` INT, `total_losses` INT, `home_ties` INT, `away_ties` INT, `total_ties` INT, `home_matches` INT, `away_matches` INT, `total_matches` INT);

-- -----------------------------------------------------
-- Placeholder table for view `mye030`.`xores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`xores` (`Display_Name` INT, `Official_Name` INT, `start_year` INT, `end_year` INT, `ISO_Code` INT, `home_wins` INT, `away_wins` INT, `total_wins` INT, `home_losses` INT, `away_losses` INT, `total_losses` INT, `home_ties` INT, `away_ties` INT, `total_ties` INT, `home_matches` INT, `away_matches` INT, `total_matches` INT);

-- -----------------------------------------------------
-- Placeholder table for view `mye030`.`yearly_goals_matches`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`yearly_goals_matches` (`scorer` INT, `ISO_Code` INT, `year` INT, `total_year_matches` INT, `total_year_goals` INT);

-- -----------------------------------------------------
-- Placeholder table for view `mye030`.`yearlymatchstats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mye030`.`yearlymatchstats` (`ISO_Code` INT, `year` INT, `total_year_matches` INT, `total_year_draws` INT, `total_year_wins` INT, `total_year_losses` INT, `had_shootouts` INT);

-- -----------------------------------------------------
-- View `mye030`.`country_years`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mye030`.`country_years`;
USE `mye030`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mye030`.`country_years` AS select `c`.`ISO_Code` AS `ISO_Code`,`c`.`Display_Name` AS `Display_Name`,`c`.`Official_Name` AS `Official_Name`,min(`m`.`date`) AS `start_year`,max(`m`.`date`) AS `end_year` from (`mye030`.`countries` `c` join `mye030`.`results` `m` on(((`m`.`home_team` = `c`.`ISO_Code`) or (`m`.`away_team` = `c`.`ISO_Code`)))) group by `c`.`ISO_Code`;

-- -----------------------------------------------------
-- View `mye030`.`globalstats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mye030`.`globalstats`;
USE `mye030`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mye030`.`globalstats` AS select `mye030`.`x`.`ISO_Code` AS `ISO_Code`,`mye030`.`x`.`Display_Name` AS `Display_Name`,`mye030`.`x`.`total_wins` AS `total_wins`,((`mye030`.`x`.`total_wins` * 3) + `mye030`.`x`.`total_ties`) AS `score`,(year(`mye030`.`x`.`end_year`) - year(`mye030`.`x`.`start_year`)) AS `years_playing` from `mye030`.`xores` `x`;

-- -----------------------------------------------------
-- View `mye030`.`players`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mye030`.`players`;
USE `mye030`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mye030`.`players` AS select `s`.`scorer` AS `scorer`,`s`.`team` AS `team`,min(year(`s`.`date`)) AS `starting_year`,max(year(`s`.`date`)) AS `ending_year`,count(0) AS `total_goals`,max(`match_goals`.`max_goals`) AS `max_goals_per_match` from (`mye030`.`scorers` `s` join (select `mye030`.`scorers`.`date` AS `date`,`mye030`.`scorers`.`home_team` AS `home_team`,`mye030`.`scorers`.`away_team` AS `away_team`,`mye030`.`scorers`.`scorer` AS `scorer`,count(0) AS `max_goals` from `mye030`.`scorers` group by `mye030`.`scorers`.`date`,`mye030`.`scorers`.`home_team`,`mye030`.`scorers`.`away_team`,`mye030`.`scorers`.`scorer`) `match_goals` on(((`s`.`date` = `match_goals`.`date`) and (`s`.`home_team` = `match_goals`.`home_team`) and (`s`.`away_team` = `match_goals`.`away_team`) and (`s`.`scorer` = `match_goals`.`scorer`)))) group by `s`.`team`,`s`.`scorer`;

-- -----------------------------------------------------
-- View `mye030`.`stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mye030`.`stats`;
USE `mye030`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mye030`.`stats` AS select `c`.`ISO_Code` AS `ISO_Code`,sum((case when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` > `m`.`away_score`)) then 1 else 0 end)) AS `home_wins`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` > `m`.`home_score`)) then 1 else 0 end)) AS `away_wins`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` > `m`.`home_score`)) then 1 when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` > `m`.`away_score`)) then 1 else 0 end)) AS `total_wins`,sum((case when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` < `m`.`away_score`)) then 1 else 0 end)) AS `home_losses`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` < `m`.`home_score`)) then 1 else 0 end)) AS `away_losses`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` < `m`.`home_score`)) then 1 when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` < `m`.`away_score`)) then 1 else 0 end)) AS `total_losses`,sum((case when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` = `m`.`away_score`)) then 1 else 0 end)) AS `home_ties`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` = `m`.`home_score`)) then 1 else 0 end)) AS `away_ties`,sum((case when ((`m`.`away_team` = `c`.`ISO_Code`) and (`m`.`away_score` = `m`.`home_score`)) then 1 when ((`m`.`home_team` = `c`.`ISO_Code`) and (`m`.`home_score` = `m`.`away_score`)) then 1 else 0 end)) AS `total_ties`,sum((case when (`m`.`home_team` = `c`.`ISO_Code`) then 1 else 0 end)) AS `home_matches`,sum((case when (`m`.`away_team` = `c`.`ISO_Code`) then 1 else 0 end)) AS `away_matches`,sum((case when (`m`.`away_team` = `c`.`ISO_Code`) then 1 when (`m`.`home_team` = `c`.`ISO_Code`) then 1 else 0 end)) AS `total_matches` from (`mye030`.`countries` `c` join `mye030`.`results` `m` on(((`m`.`home_team` = `c`.`ISO_Code`) or (`m`.`away_team` = `c`.`ISO_Code`)))) group by `c`.`ISO_Code`;

-- -----------------------------------------------------
-- View `mye030`.`xores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mye030`.`xores`;
USE `mye030`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mye030`.`xores` AS select `mye030`.`c`.`Display_Name` AS `Display_Name`,`mye030`.`c`.`Official_Name` AS `Official_Name`,`mye030`.`c`.`start_year` AS `start_year`,`mye030`.`c`.`end_year` AS `end_year`,`mye030`.`s`.`ISO_Code` AS `ISO_Code`,`mye030`.`s`.`home_wins` AS `home_wins`,`mye030`.`s`.`away_wins` AS `away_wins`,`mye030`.`s`.`total_wins` AS `total_wins`,`mye030`.`s`.`home_losses` AS `home_losses`,`mye030`.`s`.`away_losses` AS `away_losses`,`mye030`.`s`.`total_losses` AS `total_losses`,`mye030`.`s`.`home_ties` AS `home_ties`,`mye030`.`s`.`away_ties` AS `away_ties`,`mye030`.`s`.`total_ties` AS `total_ties`,`mye030`.`s`.`home_matches` AS `home_matches`,`mye030`.`s`.`away_matches` AS `away_matches`,`mye030`.`s`.`total_matches` AS `total_matches` from (`mye030`.`country_years` `c` join `mye030`.`stats` `s` on((`mye030`.`c`.`ISO_Code` = `mye030`.`s`.`ISO_Code`))) group by `mye030`.`c`.`ISO_Code`;

-- -----------------------------------------------------
-- View `mye030`.`yearly_goals_matches`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mye030`.`yearly_goals_matches`;
USE `mye030`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mye030`.`yearly_goals_matches` AS select `s`.`scorer` AS `scorer`,`mye030`.`y`.`ISO_Code` AS `ISO_Code`,`mye030`.`y`.`year` AS `year`,`mye030`.`y`.`total_year_matches` AS `total_year_matches`,count(0) AS `total_year_goals` from ((`mye030`.`scorers` `s` join `mye030`.`countries` `c` on(((`c`.`Official_Name` = `s`.`team`) or (`c`.`Display_Name` = `s`.`team`)))) join `mye030`.`yearlymatchstats` `y` on(((year(`s`.`date`) = `mye030`.`y`.`year`) and (`c`.`ISO_Code` = `mye030`.`y`.`ISO_Code`)))) group by `s`.`scorer`,`mye030`.`y`.`ISO_Code`,`mye030`.`y`.`year`,`mye030`.`y`.`total_year_matches`;

-- -----------------------------------------------------
-- View `mye030`.`yearlymatchstats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mye030`.`yearlymatchstats`;
USE `mye030`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mye030`.`yearlymatchstats` AS select `c`.`ISO_Code` AS `ISO_Code`,year(`r`.`date`) AS `year`,sum((case when ((`r`.`away_team` = `c`.`ISO_Code`) or (`r`.`home_team` = `c`.`ISO_Code`)) then 1 else 0 end)) AS `total_year_matches`,sum((case when ((`r`.`away_team` = `c`.`ISO_Code`) and (`r`.`away_score` = `r`.`home_score`)) then 1 when ((`r`.`home_team` = `c`.`ISO_Code`) and (`r`.`home_score` = `r`.`away_score`)) then 1 else 0 end)) AS `total_year_draws`,sum((case when ((`r`.`away_team` = `c`.`ISO_Code`) and (`r`.`away_score` > `r`.`home_score`)) then 1 when ((`r`.`home_team` = `c`.`ISO_Code`) and (`r`.`away_score` < `r`.`home_score`)) then 1 else 0 end)) AS `total_year_wins`,sum((case when ((`r`.`away_team` = `c`.`ISO_Code`) and (`r`.`away_score` < `r`.`home_score`)) then 1 when ((`r`.`home_team` = `c`.`ISO_Code`) and (`r`.`away_score` > `r`.`home_score`)) then 1 else 0 end)) AS `total_year_losses`,sum((case when ((`r`.`date` = `s`.`date`) and (`r`.`away_team` = `s`.`away_team`) and (`r`.`home_team` = `s`.`home_team`) and ((`r`.`away_team` = `c`.`ISO_Code`) or (`r`.`home_team` = `c`.`ISO_Code`))) then 1 else 0 end)) AS `had_shootouts` from ((`mye030`.`countries` `c` join `mye030`.`results` `r` on(((`r`.`home_team` = `c`.`ISO_Code`) or (`r`.`away_team` = `c`.`ISO_Code`)))) left join `mye030`.`shootouts` `s` on(((`s`.`date` = `r`.`date`) and (`s`.`home_team` = `r`.`home_team`) and (`s`.`away_team` = `s`.`away_team`)))) group by `c`.`ISO_Code`,`year`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
