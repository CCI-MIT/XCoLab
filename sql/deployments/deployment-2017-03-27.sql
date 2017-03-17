-- COLAB-1816 email
CREATE TABLE IF NOT EXISTS `xcolab_ColabEmail` (
  `colabEmailId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `emailSubject` VARCHAR(255) NOT NULL COMMENT '	',
  `emailBody` TEXT NOT NULL COMMENT '			',
  `emailTo` VARCHAR(255) NOT NULL,
  `emailFrom` VARCHAR(255) NOT NULL,
  `dateSent` DATETIME NOT NULL,
  `referenceId` BIGINT(20) NULL,
  `emailBodyHash` VARCHAR(255) NOT NULL,
  `sent` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`colabEmailId`),
  INDEX `index2` (`emailSubject` ASC, `emailTo` ASC, `dateSent` ASC, `emailBodyHash` ASC))
ENGINE = InnoDB;



INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('DAILY_DIGEST_TRIGGER_HOUR', '0', '1', ' ', '1');
