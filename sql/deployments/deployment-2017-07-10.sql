ALTER TABLE xcolab_ConfigurationAttribute ADD locale VARCHAR(5) DEFAULT '' NOT NULL;

create table admin_ContestTypeAttribute
(
	name varchar(75) not null,
	additionalId bigint not null,
	locale varchar(5) default '' not null,
	numericValue bigint null,
	stringValue longtext null,
	realValue double null,
	primary key (name, additionalId, locale)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
