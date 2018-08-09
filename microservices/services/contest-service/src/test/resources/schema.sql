create table contest__contest
(
  id                                         bigint auto_increment
    primary key,
  contest_type_id                            bigint        null,
  question                                   varchar(1024) null,
  title                                      varchar(512)  null,
  contest_url_name                           varchar(75)   null,
  contest_year                               bigint        null,
  description                                longtext      null,
  contest_model_description                  longtext      null,
  contest_positions_description              longtext      null,
  created_at                                 datetime      null,
  updated_at                                 datetime      null,
  author_user_id                             bigint        null,
  contest_active                             tinyint       null,
  proposal_template_id                       bigint        null,
  contest_schedule_id                        bigint        null,
  proposal_creation_template_string          varchar(75)   null,
  vote_template_string                       varchar(75)   null,
  proposal_vote_template_string              varchar(75)   null,
  proposal_vote_confirmation_template_string varchar(75)   null,
  vote_question_template_string              varchar(75)   null,
  focus_area_id                              bigint        null,
  contest_tier                               bigint        null,
  contest_logo_id                            bigint        null,
  featured                                   tinyint       null,
  plans_open_by_default                      tinyint       null,
  sponsor_logo_id                            bigint        null,
  default_proposal_logo_id                   bigint        null,
  sponsor_text                               varchar(500)  null,
  sponsor_link                               varchar(500)  null,
  flag                                       int           null,
  flag_text                                  varchar(256)  null,
  flag_tooltip                               varchar(512)  null,
  discussion_group_id                        bigint        null,
  weight                                     int           null,
  resources_url                              varchar(1024) null,
  contest_private                            tinyint       null,
  use_permissions                            tinyint       null,
  contest_creation_status                    varchar(75)   null,
  default_model_id                           bigint        null,
  other_models                               varchar(75)   null,
  default_model_settings                     varchar(75)   null,
  points                                     double        null,
  default_parent_point_type                  bigint        null,
  point_distribution_strategy                varchar(75)   null,
  email_template_url                         varchar(500)  null,
  show_in_tile_view                          tinyint       null,
  show_in_list_view                          tinyint       null,
  show_in_outline_view                       tinyint       null,
  hide_ribbons                               tinyint       null,
  resource_article_id                        bigint        null,
  constraint unique_index_contest_name_year
  unique (contest_url_name, contest_year)
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table contest__contest_phase
(
  id                        bigint auto_increment
    primary key,
  contest_id                bigint      null,
  contest_phase_type_id     bigint      null,
  contest_schedule_id       bigint      null,
  contest_phase_autopromote varchar(75) null,
  phase_start_date          datetime    null,
  phase_end_date            datetime    null,
  created_at                datetime    null,
  updated_at                datetime    null
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table contest__contest_translation
(
  contest_id  bigint                              not null,
  lang        varchar(5)                          not null,
  question    varchar(255)                        null,
  title       varchar(128)                        null,
  description longtext                            null,
  created_at  timestamp default CURRENT_TIMESTAMP not null,
  updated_at  timestamp default CURRENT_TIMESTAMP not null,
  author_id   bigint                              null,
  primary key (contest_id, lang)
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table contest__proposal
(
  id                    bigint auto_increment
    primary key,
  created_at            datetime null,
  updated_at            datetime null,
  author_id             bigint   null,
  visible               tinyint  null,
  discussion_id         bigint   null,
  results_discussion_id bigint   null,
  group_id              bigint   null
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table contest__proposal2_phase
(
  proposal_id           bigint  not null,
  contest_phase_id      bigint  not null,
  version_from          int     null,
  version_to            int     null,
  sort_weight           int     null,
  autopromote_candidate tinyint null,
  primary key (proposal_id, contest_phase_id)
) ENGINE=InnoDB  CHARSET=utf8mb4;

create table contest__proposal_contest_phase_attribute
(
  id               bigint auto_increment
    primary key,
  proposal_id      bigint      null,
  contest_phase_id bigint      null,
  name             varchar(75) null,
  additional_id    bigint      null,
  numeric_value    bigint      null,
  string_value     longtext    null,
  real_value       double      null
) ENGINE=InnoDB  CHARSET=utf8mb4;
