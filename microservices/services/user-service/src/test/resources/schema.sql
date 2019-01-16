create table user__user
(
  id                                bigint auto_increment
    primary key,
  screen_name                       varchar(42)              null,
  email_address                     varchar(75)              null,
  is_email_confirmed                tinyint default '0'      not null,
  is_email_bounced                  tinyint default '0'      null,
  first_name                        varchar(75)              null,
  last_name                         varchar(75)              null,
  hashed_password                   varchar(75)              null,
  created_at                        datetime                 null,
  updated_at                        datetime                 null,
  password_updated_at               datetime                 null,
  country                           varchar(75)              null,
  state                             varchar(50)              null,
  short_bio                         text                     null,
  facebook_id                       bigint                   null,
  google_id                         varchar(50)              null,
  open_id                           varchar(255)             null,
  login_ip                          varchar(75)              null,
  login_date                        datetime                 null,
  status                            int default '0'          null,
  forgot_password_token             varchar(255)             null,
  forgot_password_token_expire_time datetime                 null,
  portrait_file_entry_id            bigint                   null,
  report_karma                      int default '100'        null,
  default_locale                    varchar(45) default 'en' null,
  auto_registered_member_status     int(4) default '0'       null,
  uuid                              varchar(40)              null,
  login_token_id                    varchar(75)              null,
  login_token_key                   varchar(75)              null,
  login_token_expiration_date       datetime                 null,
  colab_sso_id                      varchar(50)              null,
  climate_x_id                      varchar(75)              null
) ENGINE=InnoDB charset = utf8mb4;

create table user__user_role
(
  user_id bigint not null,
  role_id bigint not null,
  primary key (user_id, role_id)
) ENGINE=InnoDB charset = utf8mb4;

create table user__member_category
(
  role_id       bigint        not null
    primary key,
  display_name  varchar(75)   null,
  category_name varchar(75)   null,
  sort_order    bigint        null,
  show_in_list  tinyint       null,
  image_name    varchar(75)   null,
  description   varchar(2048) null
) ENGINE=InnoDB charset = utf8mb4;
