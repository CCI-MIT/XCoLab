create table activity__activity_entry
(
  id                bigint auto_increment primary key,
  user_id           bigint      not null,
  created_at        datetime    not null,
  activity_category varchar(30) null,
  activity_type     varchar(75) null,
  category_id       bigint      not null,
  additional_id     bigint      null,
  primary_type      bigint      null,
  secondary_type    bigint      null,
  class_primary_key bigint      null,
  extra_data        varchar(75) null
) ENGINE=InnoDB charset = utf8;

create table activity__activity_subscription
(
  id                             bigint auto_increment
    primary key,
  receiver_user_id               bigint       null,
  activity_category              varchar(30)  not null,
  category_id                    bigint       not null,
  class_name_id                  bigint       null,
  class_pk                       bigint       null,
  automatic_subscription_counter int          null,
  extra_data                     varchar(256) null,
  created_at                     datetime     null,
  updated_at                     datetime     null
) ENGINE=InnoDB charset = utf8;
