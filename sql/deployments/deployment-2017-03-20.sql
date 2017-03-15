
ALTER TABLE members_Member
  ADD COLUMN isEmailConfirmed TINYINT(4) NOT NULL DEFAULT 0 AFTER emailAddress;

-- COLAB-1816 email
CREATE TABLE IF NOT EXISTS `xcolab_ColabEmail` (
  `colabEmailId` BIGINT(20) NOT NULL,
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

-- COLAB-1808 embedded microservices

-- Execute on Climate CoLab
-- INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('PARTNER_COLAB_NAMESPACE', 0, null, 'solve-colab', null);

-- Execute on Solve CoLab
-- INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('PARTNER_COLAB_NAMESPACE', 0, null, 'climate-colab', null);