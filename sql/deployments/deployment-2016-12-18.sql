#Configuration Attribute Blog URL
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('BLOG_URL', '0', '0', 'news.climatecolab.com', '0');
ALTER TABLE `xcolab`.`xcolab_BalloonText` 
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;
