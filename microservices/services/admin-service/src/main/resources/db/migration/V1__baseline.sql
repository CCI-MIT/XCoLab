CREATE TABLE IF NOT EXISTS `xcolab_ContestEmailTemplate` (
  `type_` varchar(75) NOT NULL PRIMARY KEY,
  `subject` longtext,
  `header` longtext,
  `footer` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ConfigurationAttribute` (
  `name` varchar(75) NOT NULL,
  `additionalId` bigint(20) NOT NULL,
  `locale` VARCHAR(5) DEFAULT '' NOT NULL,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` longtext,
  `realValue` double DEFAULT NULL,
  PRIMARY KEY (`name`, `additionalId`, `locale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `admin_ContestTypeAttribute` (
	name varchar(75) not null,
	additionalId bigint not null,
	locale varchar(5) default '' not null,
	numericValue bigint null,
	stringValue longtext null,
	realValue double null,
	primary key (name, additionalId, locale)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
