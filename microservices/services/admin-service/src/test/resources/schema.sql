create table admin__configuration_attribute
(
  name          varchar(75)           not null,
  additional_id bigint                not null,
  numeric_value bigint                null,
  string_value  longtext              null,
  real_value    double                null,
  locale        varchar(5) default '' not null,
  primary key (name, additional_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  create table admin__contest_type_attribute
(
  name          varchar(75)           not null,
  additional_id bigint                not null,
  locale        varchar(5) default '' not null,
  numeric_value bigint                null,
  string_value  longtext              null,
  real_value    double                null,
  primary key (name, additional_id, locale)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  create table admin__email_template
(
  name    varchar(75) not null
    primary key,
  subject longtext    null,
  header  longtext    null,
  footer  longtext    null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
