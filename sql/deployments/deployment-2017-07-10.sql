-- COLAB-2087
ALTER TABLE xcolab_ConfigurationAttribute ADD locale VARCHAR(5) DEFAULT '' NOT NULL;

CREATE INDEX xcolab_ConfigurationAttribute_name_locale_index
    ON xcolab_ConfigurationAttribute (name, locale);

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

-- COLAB-2086
ALTER TABLE xcolab_ContentPage ADD metaDescription VARCHAR(255) AFTER title;

-- i18n changes for profile
ALTER TABLE `members_Member`
ADD COLUMN `defaultLocale` VARCHAR(45) NULL DEFAULT 'en' AFTER `reportKarma`;

-- i18n changes for content article
ALTER TABLE xcolab_ContentArticleVersion
ADD COLUMN `lang` VARCHAR(4) NULL DEFAULT 'en' AFTER `content`;

