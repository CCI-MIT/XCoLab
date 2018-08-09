alter table members_Member change id_                           id                           bigint auto_increment;
alter table members_Member change screenName                    screen_name                    varchar(42)              null;
alter table members_Member change emailAddress                  email_address                  varchar(75)              null;
alter table members_Member change isEmailConfirmed              is_email_confirmed              tinyint default '0'      not null;
alter table members_Member change isEmailBounced                is_email_bounced                tinyint default '0'      null;
alter table members_Member change firstName                     first_name                     varchar(75)              null;
alter table members_Member change lastName                      last_name                      varchar(75)              null;
alter table members_Member change hashedPassword                hashed_password                varchar(75)              null;
alter table members_Member change createDate                    created_at                    datetime                 null;
alter table members_Member change modifiedDate                  updated_at                  datetime                 null;
alter table members_Member change passwordModifiedDate          password_updated_at          datetime                 null;
alter table members_Member change shortBio                      short_bio                      text                     null;
alter table members_Member change facebookId                    facebook_id                    bigint                   null;
alter table members_Member change googleId                      google_id                      varchar(50)              null;
alter table members_Member change openId                        open_id                        varchar(255)             null;
alter table members_Member change loginIP                       login_ip                       varchar(75)              null;
alter table members_Member change loginDate                     login_date                     datetime                 null;
alter table members_Member change forgotPasswordToken           forgot_password_token           varchar(255)             null;
alter table members_Member change forgotPasswordTokenExpireTime forgot_password_token_expire_time datetime                 null;
alter table members_Member change portraitFileEntryId           portrait_file_entry_id           bigint                   null;
alter table members_Member change reportKarma                   report_karma                   int default '100'        null;
alter table members_Member change defaultLocale                 default_locale                 varchar(45) default 'en' null;
alter table members_Member change autoRegisteredMemberStatus    auto_registered_member_status    int(4) default '0'       null;
alter table members_Member change loginTokenId                  login_token_id                  varchar(75)              null;
alter table members_Member change loginTokenKey                 login_token_key                 varchar(75)              null;
alter table members_Member change loginTokenExpirationDate      login_token_expiration_date      datetime                 null;
alter table members_Member change colabSsoId                    colab_sso_id                    varchar(50)              null;
alter table members_Member change climateXId                    climate_x_id                    varchar(75)              null;
rename table members_Member to user__user;

alter table members_RoleGroup change roleGroupId id bigint auto_increment;
rename table members_RoleGroup to user__role_group;

alter table members_RoleGroupRoles change roleGroupId role_group_id bigint not null;
alter table members_RoleGroupRoles change roleId      role_id      bigint not null;
rename table members_RoleGroupRoles to user__role_group_role;

alter table Role_ change roleId       id       bigint      not null;
alter table Role_ change createDate   created_at   datetime    null;
rename table Role_ to user__role;

alter table Users_Roles change userId user_id bigint not null;
alter table Users_Roles change roleId role_id bigint not null;
rename table Users_Roles to user__user_role;

alter table xcolab_AnalyticsUserEvent change userId   user_id   bigint      not null;
alter table xcolab_AnalyticsUserEvent change idString id_string varchar(75) not null;
alter table xcolab_AnalyticsUserEvent change created  created_at  datetime    null;
rename table xcolab_AnalyticsUserEvent to user__analytics_user_event;

alter table xcolab_LoginLog change pk         id         bigint auto_increment;
alter table xcolab_LoginLog change userId     user_id     bigint       null;
alter table xcolab_LoginLog change createDate created_at datetime     null;
alter table xcolab_LoginLog change ipAddress  ip_address  varchar(75)  null;
alter table xcolab_LoginLog change entryUrl   entry_url   varchar(255) null;
rename table xcolab_LoginLog to user__login_log;

alter table xcolab_MemberCategory change roleId       role_id       bigint        not null;
alter table xcolab_MemberCategory change displayName  display_name  varchar(75)   null;
alter table xcolab_MemberCategory change categoryName category_name varchar(75)   null;
alter table xcolab_MemberCategory change sortOrder    sort_order    bigint        null;
alter table xcolab_MemberCategory change showInList   show_in_list   tinyint       null;
alter table xcolab_MemberCategory change imageName    image_name    varchar(75)   null;
rename table xcolab_MemberCategory to user__member_category;

alter table xcolab_Message change messageId  id  bigint auto_increment;
alter table xcolab_Message change fromId     from_id     bigint        null;
alter table xcolab_Message change repliesTo  replies_to  bigint        null;
alter table xcolab_Message change createDate created_at datetime      null;
rename table xcolab_Message to user__message;

alter table xcolab_MessageRecipientStatus change messageRecipientId id bigint auto_increment;
alter table xcolab_MessageRecipientStatus change messageId          message_id          bigint      null;
alter table xcolab_MessageRecipientStatus change userId             user_id             bigint      null;
alter table xcolab_MessageRecipientStatus change threadId           thread_id           varchar(75) null;
rename table xcolab_MessageRecipientStatus to user__message_recipient_status;

alter table xcolab_MessagingUserPreferences change messagingPreferencesId   id   bigint auto_increment;
alter table xcolab_MessagingUserPreferences change userId                   user_id                   bigint  null;
alter table xcolab_MessagingUserPreferences change emailOnSend              email_on_send              tinyint null;
alter table xcolab_MessagingUserPreferences change emailOnReceipt           email_on_receipt           tinyint null;
alter table xcolab_MessagingUserPreferences change emailOnActivity          email_on_activity          tinyint null;
alter table xcolab_MessagingUserPreferences change emailActivityDailyDigest email_activity_daily_digest tinyint null;
alter table xcolab_MessagingUserPreferences change dailyMessageLimit        daily_message_limit        int     null;
rename table xcolab_MessagingUserPreferences to user__messaging_user_preference;

alter table xcolab_PlatformTeam change id_  id bigint auto_increment;
rename table xcolab_PlatformTeam to user__platform_team;

alter table xcolab_PlatformTeamMember change userId user_id bigint not null;
alter table xcolab_PlatformTeamMember change teamId team_id bigint not null;
rename table xcolab_PlatformTeamMember to user__platform_team_member;

alter table xcolab_StaffMember change id_          id          bigint       not null;
alter table xcolab_StaffMember change userId       user_id       bigint       null;
alter table xcolab_StaffMember change categoryId   category_id   bigint       null;
alter table xcolab_StaffMember change firstNames   first_names   varchar(75)  null;
alter table xcolab_StaffMember change lastName     last_name     varchar(75)  null;
alter table xcolab_StaffMember change photoUrl     photo_url     varchar(255) null;
alter table xcolab_StaffMember change sort         sort_order         int          null;
rename table xcolab_StaffMember to user__staff_member;
