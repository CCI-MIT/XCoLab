ALTER TABLE `xcolab_Contest`
ADD COLUMN `defaultProposalLogoId` BIGINT(20) NULL DEFAULT NULL AFTER `sponsorLogoId`;


-- COLAB-1980
INSERT INTO xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('DISPLAY_FIRST_NAME_LAST_NAME', '0', '0', '0', '0');
