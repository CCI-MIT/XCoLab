DROP TABLE `xcolab_ConfigurationAttribute` IF EXISTS;
CREATE TABLE  `xcolab_ConfigurationAttribute`  (
  `name` varchar(75) NOT NULL,
  `additionalId` bigint(20) NOT NULL,
  locale varchar(5) default '' not null,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` longtext,
  `realValue` double DEFAULT NULL,
  PRIMARY KEY (`name`,`additionalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestEmailTemplate` (
  `type_` varchar(75) NOT NULL,
  `subject` longtext,
  `header` longtext,
  `footer` longtext,
  PRIMARY KEY (`type_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
