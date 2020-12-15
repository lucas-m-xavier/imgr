-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`image` (
  `idimage` INT NOT NULL AUTO_INCREMENT,
  `source` VARCHAR(200) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idimage`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(21) NOT NULL,
  `password` VARCHAR(21) NOT NULL,
  `state` VARCHAR(21) NOT NULL DEFAULT 'Comum',
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`notification` (
  `idnotification` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(200) NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idnotification`, `idUser`),
  INDEX `fk_notification_user1_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_notification_user1`
    FOREIGN KEY (`idUser`)
    REFERENCES `mydb`.`user` (`idUser`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`permission` (
  `idUser` INT NOT NULL,
  `idImage` INT NOT NULL,
  `shared` TINYINT NOT NULL DEFAULT '0',
  `viewer` TINYINT NOT NULL DEFAULT '0',
  `deleted` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUser`, `idImage`),
  INDEX `fk_permission_user_idx` (`idUser` ASC) VISIBLE,
  INDEX `fk_permission_image1_idx` (`idImage` ASC) VISIBLE,
  CONSTRAINT `fk_permission_image1`
    FOREIGN KEY (`idImage`)
    REFERENCES `mydb`.`image` (`idimage`),
  CONSTRAINT `fk_permission_user`
    FOREIGN KEY (`idUser`)
    REFERENCES `mydb`.`user` (`idUser`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`solicitation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`solicitation` (
  `idSolicitation` INT NOT NULL AUTO_INCREMENT,
  `idImage` INT NOT NULL,
  `idAdmin` INT NOT NULL,
  `idUser` INT NOT NULL,
  `viewS` TINYINT NOT NULL DEFAULT '0',
  `deleteS` TINYINT NOT NULL DEFAULT '0',
  `shareS` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSolicitation`, `idImage`, `idAdmin`, `idUser`),
  INDEX `fk_solicitation_image1_idx` (`idImage` ASC) VISIBLE,
  INDEX `fk_solicitation_user1_idx` (`idAdmin` ASC) VISIBLE,
  INDEX `fk_solicitation_user2_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_solicitation_image1`
    FOREIGN KEY (`idImage`)
    REFERENCES `mydb`.`image` (`idimage`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitation_user1`
    FOREIGN KEY (`idAdmin`)
    REFERENCES `mydb`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitation_user2`
    FOREIGN KEY (`idUser`)
    REFERENCES `mydb`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
