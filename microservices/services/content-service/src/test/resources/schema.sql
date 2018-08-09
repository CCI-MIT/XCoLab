create table content__content_article
(
  id                 bigint auto_increment
    primary key,
  author_user_id     bigint     null,
  created_at         datetime   null,
  max_version_id     bigint     null,
  folder_id          bigint     null,
  edit_role_group_id bigint     null,
  view_role_group_id bigint     null,
  visible            tinyint(1) null
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table content__content_article_version
(
  id             bigint auto_increment
    primary key,
  article_id     bigint                  null,
  folder_id      bigint                  null,
  author_user_id bigint                  null,
  created_at     datetime                null,
  title          varchar(555)            null,
  content        longtext                null,
  lang           varchar(4) default 'en' null
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table content__content_folder
(
  id               bigint auto_increment
    primary key,
  name             varchar(255) null,
  description      text         null,
  parent_folder_id bigint       null
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table content__content_page
(
  id                 bigint(11) auto_increment
    primary key,
  title              varchar(100) null,
  meta_description   varchar(255)                            null,
  menu_article_id    bigint(11)                              null,
  content_article_id bigint(11)                              not null,
  created_at         timestamp null,
  updated_at         timestamp null
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table content__file_entry
(
  id             bigint auto_increment
    primary key,
  created_at     datetime     null,
  file_extension varchar(10)  null,
  file_name      varchar(255) null,
  file_size      int          null
) ENGINE=InnoDB  CHARSET=utf8mb4;
