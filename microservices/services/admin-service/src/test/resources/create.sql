CREATE TABLE `xcolab_ConfigurationAttribute` (
  `name` varchar(75) NOT NULL,
  `additionalId` bigint(20) NOT NULL,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` longtext,
  `realValue` double DEFAULT NULL,
  PRIMARY KEY (`name`,`additionalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
