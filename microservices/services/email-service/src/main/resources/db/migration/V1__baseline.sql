CREATE TABLE IF NOT EXISTS `xcolab_ColabEmail` (
  `colabEmailId` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `emailSubject` VARCHAR(191) NOT NULL,
  `emailBody` TEXT NOT NULL,
  `emailTo` VARCHAR(191) NOT NULL,
  `emailFrom` VARCHAR(191) NOT NULL,
  `dateSent` DATETIME NOT NULL,
  `referenceId` BIGINT(20) NULL,
  `emailBodyHash` VARCHAR(64) NOT NULL,
  `sent` TINYINT(1) NULL DEFAULT 0,
  INDEX `index_xcolab_ColabEmail_emailSubject` (`emailSubject`),
  INDEX `index_xcolab_ColabEmail_emailTo` (`emailTo`),
  INDEX `index_xcolab_ColabEmail_dateSent` (`dateSent`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;
