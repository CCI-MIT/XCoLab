create table comment__category
(
  id             bigint auto_increment
    primary key,
  group_id       bigint              null,
  author_user_id bigint              null,
  name           varchar(75)         null,
  description    text                null,
  created_at     datetime            not null,
  deleted_at     datetime            null,
  sort           int                 null,
  is_quiet       tinyint default '0' null
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table comment__category_group
(
  id          bigint auto_increment
    primary key,
  description text                null,
  url         varchar(200)        null,
  is_quiet    tinyint default '0' null
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table comment__comment
(
  id             bigint auto_increment
    primary key,
  thread_id      bigint   not null,
  author_user_id bigint   null,
  created_at     datetime null,
  updated_at     datetime null,
  deleted_at     datetime null,
  content        text     null
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table comment__thread
(
  id             bigint auto_increment
    primary key,
  category_id    bigint              null,
  author_user_id bigint              not null,
  title          varchar(255)        not null,
  created_at     datetime            not null,
  deleted_at     datetime            null,
  is_quiet       tinyint default '0' null
) ENGINE=InnoDB  CHARSET=utf8mb4;
