-- =======================================
-- new and changed tables (before Singapore migration)
-- =======================================

DROP TABLE IF EXISTS `members_Member`;
CREATE TABLE `members_Member` (
  `id_` bigint(20) NOT NULL,
  `screenName` varchar(42) DEFAULT NULL,
  `emailAddress` varchar(75) DEFAULT NULL,
  `firstName` varchar(75) DEFAULT NULL,
  `lastName` varchar(75) DEFAULT NULL,
  `hashedPassword` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `passwordModifiedDate` datetime DEFAULT NULL,
  `country` varchar(75) DEFAULT NULL,
  `shortBio` text,
  `facebookId` bigint(20) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `loginIP` varchar(75) DEFAULT NULL,
  `loginDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `forgotPasswordToken` varchar(255) DEFAULT NULL,
  `forgotPasswordTokenExpireTime` datetime DEFAULT NULL,
  `portraitFileEntryId` bigint(20) DEFAULT NULL,
  `reportKarma` int(11) DEFAULT '100',
  `autoRegisteredMemberStatus` int(4) DEFAULT '0',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `IX_XCOLAB_MEMBERS_SCREEN_NAME` (`screenName`),
  UNIQUE KEY `IX_XCOLAB_MEMBERS_EMAIL_ADDRESS` (`emailAddress`),
  KEY `IX_XCOLAB_MEMBERS_CREATE_DATE` (`createDate`,`modifiedDate`),
  KEY `IX_XCOLAB_MEMBERS_MODIFIED_DATE` (`modifiedDate`),
  KEY `IX_XCOLAB_MEMBERS_FACEBOOK_ID` (`facebookId`),
  KEY `IX_XCOLAB_MEMBERS_OPEN_ID` (`openId`),
  FULLTEXT KEY `members_Member_names_bio` (`firstName`,`lastName`,`shortBio`,`screenName`)
);

INSERT INTO `members_Member` (id_, screenName, emailAddress, firstName, lastName, hashedPassword, createDate, modifiedDate,
  passwordModifiedDate, facebookId, openId, loginIP, loginDate, status)
    SELECT userId, screenName, emailAddress, firstName, lastName, password_, createDate, modifiedDate,
    passwordModifiedDate, facebookId, openId, loginIP, loginDate, status from User_;

ALTER TABLE `xcolab_Contest` ADD COLUMN `resourceArticleId` bigint(20) DEFAULT NULL;

DROP TABLE IF EXISTS `members_Member`;
CREATE TABLE `xcolab_ContestTeamMemberRole` (
  `id_` bigint(20) NOT NULL,
  `role` varchar(75) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`)
);

INSERT INTO xcolab_ContestTeamMemberRole (id_, role, sort) VALUES (193260, 'Advisor', 0);
INSERT INTO xcolab_ContestTeamMemberRole (id_, role, sort) VALUES (193261, 'Fellow', 2);
INSERT INTO xcolab_ContestTeamMemberRole (id_, role, sort) VALUES (1251483, 'Judge', 1);

ALTER TABLE `xcolab_ContestTeamMember` ADD COLUMN roleId bigint(20) DEFAULT NULL;

UPDATE `xcolab_ContestTeamMember` set roleId = 193260 where role = 'Advisor';
UPDATE `xcolab_ContestTeamMember` set roleId = 193261 where role = 'Fellow';
UPDATE `xcolab_ContestTeamMember` set roleId = 1251483 where role = 'Judge';

ALTER TABLE `xcolab_ContestTeamMember` DROP COLUMN role;

ALTER TABLE `xcolab_PlanSectionDefinition` ADD COLUMN `allowedValues` varchar(75) DEFAULT NULL AFTER `allowedContestTypeIds`;


ALTER TABLE `xcolab_Contest` ADD COLUMN `ContestUrlName` varchar(75) DEFAULT NULL AFTER `ContestShortName`;
ALTER TABLE `xcolab_Contest` ADD COLUMN `ContestYear` bigint(20) DEFAULT NULL AFTER `ContestUrlName`;

ALTER TABLE `xcolab_MessagingUserPreferences` ADD COLUMN `dailyMessageLimit` int(11) DEFAULT NULL;

-- =======================================
-- new and changed tables (from Singapore migration)
-- =======================================

DROP TABLE IF EXISTS `activities_ActivityEntry`;
CREATE TABLE `activities_ActivityEntry` (
  `activityEntryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `memberId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `primaryType` bigint(20) DEFAULT NULL,
  `secondaryType` bigint(20) DEFAULT NULL,
  `classPrimaryKey` bigint(20) DEFAULT NULL,
  `extraData` text,
  `activityEntryTitle` varchar(255) DEFAULT NULL,
  `activityEntryBody` text,
  `activityEntryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`activityEntryId`)
) ENGINE=InnoDB AUTO_INCREMENT=1691086 DEFAULT CHARSET=utf8;

--
-- Table structure for table `comment_Category`
--

DROP TABLE IF EXISTS `comment_Category`;
CREATE TABLE `comment_Category` (
  `categoryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `groupId` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` text,
  `createDate` datetime NOT NULL,
  `deletedDate` datetime DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `isQuiet` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`categoryId`),
  KEY `comment_Category__groupId` (`groupId`,`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=1305702 DEFAULT CHARSET=utf8;

--
-- Table structure for table `comment_CategoryGroup`
--

DROP TABLE IF EXISTS `comment_CategoryGroup`;
CREATE TABLE `comment_CategoryGroup` (
  `groupId` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `url` varchar(200) DEFAULT NULL,
  `isQuiet` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=702 DEFAULT CHARSET=utf8;

--
-- Table structure for table `comment_Comment`
--

DROP TABLE IF EXISTS `comment_Comment`;
CREATE TABLE `comment_Comment` (
  `commentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `threadId` bigint(20) NOT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `deletedDate` datetime DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`commentId`),
  KEY `comment_Comment__threadId` (`threadId`,`createDate`),
  KEY `comment_Comment__authorId` (`authorId`),
  FULLTEXT KEY `content_comment_Comment` (`content`)
) ENGINE=InnoDB AUTO_INCREMENT=1362765 DEFAULT CHARSET=utf8;

--
-- Table structure for table `comment_Thread`
--

DROP TABLE IF EXISTS `comment_Thread`;
CREATE TABLE `comment_Thread` (
  `threadId` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoryId` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `createDate` datetime NOT NULL,
  `deletedDate` datetime DEFAULT NULL,
  `isQuiet` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`threadId`),
  KEY `comment_Thread__categoryId` (`categoryId`,`createDate`)
) ENGINE=InnoDB AUTO_INCREMENT=1364744 DEFAULT CHARSET=utf8;

--
-- Table structure for table `files_FileEntry`
--

DROP TABLE IF EXISTS `files_FileEntry`;
CREATE TABLE `files_FileEntry` (
  `fileEntryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `fileEntryExtension` varchar(10) DEFAULT NULL,
  `fileEntryName` varchar(255) DEFAULT NULL,
  `fileEntrySize` int(11) DEFAULT NULL,
  PRIMARY KEY (`fileEntryId`)
) ENGINE=InnoDB AUTO_INCREMENT=2509733 DEFAULT CHARSET=utf8;

--
-- Table structure for table `filtering_FilteredEntry`
--

DROP TABLE IF EXISTS `filtering_FilteredEntry`;
CREATE TABLE `filtering_FilteredEntry` (
  `filteredEntryId` bigint(10) NOT NULL AUTO_INCREMENT,
  `source` bigint(10) DEFAULT NULL,
  `sourceId` bigint(10) DEFAULT NULL,
  `authorId` bigint(10) DEFAULT NULL,
  `fullText` text,
  `status` int(11) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `answeredAt` datetime DEFAULT NULL,
  `responseFullText` text,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`filteredEntryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `flagging_Report`
--

DROP TABLE IF EXISTS `flagging_Report`;
CREATE TABLE `flagging_Report` (
  `reportId` bigint(20) NOT NULL AUTO_INCREMENT,
  `reporterMemberId` bigint(20) NOT NULL,
  `targetType` varchar(50) NOT NULL,
  `targetId` bigint(20) NOT NULL,
  `targetAdditionalId` bigint(20) NOT NULL DEFAULT '0',
  `reason` varchar(50) NOT NULL,
  `comment` text,
  `weight` int(11) DEFAULT NULL,
  `managerAction` varchar(50) NOT NULL DEFAULT 'PENDING',
  `managerMemberId` bigint(20) DEFAULT NULL,
  `managerActionDate` datetime DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`reportId`),
  UNIQUE KEY `flagging_Report__target` (`targetType`,`targetId`,`targetAdditionalId`,`reporterMemberId`),
  KEY `flagging_Report__createDate` (`createDate`),
  KEY `flagging_Report__reporter` (`reporterMemberId`),
  KEY `flagging_Report__manager` (`managerMemberId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Table structure for table `flagging_ReportTarget`
--

DROP TABLE IF EXISTS `flagging_ReportTarget`;
CREATE TABLE `flagging_ReportTarget` (
  `reportTargetId` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `reason` varchar(50) NOT NULL,
  `notificationThreshold` int(11) NOT NULL DEFAULT '0',
  `screeningThreshold` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`reportTargetId`),
  UNIQUE KEY `flagging_ReportTarget__typeReason` (`type`,`reason`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Table structure for table `members_RoleGroup`
--

DROP TABLE IF EXISTS `members_RoleGroup`;
CREATE TABLE `members_RoleGroup` (
  `roleGroupId` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleGroupId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
INSERT INTO members_RoleGroup (name) VALUES ('Members');
INSERT INTO members_RoleGroup (name) VALUES ('Admins');
INSERT INTO members_RoleGroup (name) VALUES ('Privileged users');

--
-- Table structure for table `members_RoleGroupRoles`
--

DROP TABLE IF EXISTS `members_RoleGroupRoles`;
CREATE TABLE `members_RoleGroupRoles` (
  `roleGroupId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleGroupId`,`roleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sharedcolab_SharedMember`;
CREATE TABLE `sharedcolab_SharedMember` (
  `sharedMemberId` bigint(20) NOT NULL AUTO_INCREMENT,
  `screenName` varchar(255) DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `colabOrigin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sharedMemberId`)
) ENGINE=InnoDB AUTO_INCREMENT=2632613 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `xcolab_ActivitySubscription`;
CREATE TABLE `xcolab_ActivitySubscription` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `automaticSubscriptionCounter` int(11) DEFAULT NULL,
  `extraData` varchar(256) DEFAULT NULL,
  `receiverId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`pk`),
  KEY `IX_61FA63BB` (`classNameId`,`classPK`,`receiverId`),
  KEY `IX_C2ED8710` (`classNameId`,`classPK`,`type_`,`extraData`(50),`receiverId`),
  KEY `IX_1413A2B6` (`classNameId`,`classPK`,`type_`,`receiverId`),
  KEY `IX_33049EE6` (`receiverId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `xcolab_Contest` ADD FULLTEXT KEY `ContestDescription_xcolab_Contest` (`ContestDescription`);
ALTER TABLE `xcolab_ProposalAttribute` ADD FULLTEXT INDEX `stringValue_ProposalAtribute` (`stringValue`);

ALTER TABLE `xcolab_MemberCategory` ADD COLUMN `description` varchar(2048) DEFAULT NULL;


ALTER TABLE `xcolab_Message` MODIFY COLUMN `messageId` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `xcolab_Message` AUTO_INCREMENT = 1360850;
ALTER TABLE `xcolab_MessageRecipientStatus` MODIFY COLUMN `messageRecipientId` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `xcolab_Message` AUTO_INCREMENT = 1369351;

-- =======================================
-- Comment migration
-- =======================================
-- threads
insert into comment_Thread (threadId, categoryId, authorId, createDate, title)
  select messageId, categoryId, authorId, createDate, subject from xcolab_DiscussionMessage where threadId = 0 and deleted is NULL;
insert into comment_Comment (commentId, threadId, content, authorId, createDate)
  select messageId, messageId, body, authorId, createDate from xcolab_DiscussionMessage where deleted is NULL and threadId = 0;

-- groups
insert into comment_CategoryGroup (groupId, description, url, isQuiet)
  select id_, description, url, isQuiet from xcolab_DiscussionCategoryGroup where id_ = 701;

-- categories
insert into comment_Category (categoryId, groupId, authorId, name, description, createDate)
  select categoryId, categoryGroupId, authorId, name, description, createDate from xcolab_DiscussionCategory where categoryGroupId = 701 and deleted is NULL;

-- comments
insert into comment_Comment (commentId, threadId, content, authorId, createDate)
  select messageId, threadId, body, authorId, createDate from xcolab_DiscussionMessage where deleted is NULL and threadId > 0;

-- proposal discussions
update xcolab_Proposal as p
  left outer join xcolab_DiscussionMessage as thread
    on p.discussionId = thread.categoryGroupId
       and thread.threadId = 0
  left outer join xcolab_DiscussionMessage as fellowThread
    on p.fellowDiscussionId = fellowThread.categoryGroupId
       and fellowThread.threadId = 0
  left outer join xcolab_DiscussionMessage as resultsThread
    on p.resultsDiscussionId = resultsThread.categoryGroupId
       and resultsThread.threadId = 0
set p.discussionId = thread.messageId,
  p.fellowDiscussionid = fellowThread.messageId,
  p.resultsDiscussionId = resultsThread.messageId;

-- contest discussion
update xcolab_Contest as c
  left outer join xcolab_DiscussionMessage as thread
    on c.discussionGroupId = thread.categoryGroupId
       and thread.threadId = 0
set c.discussionGroupId = thread.messageId;

-- CMS discussions
delete cd
from xcolab_ContestDiscussion as cd
  left outer JOIN xcolab_DiscussionMessage AS thread
    ON cd.DiscussionId = thread.categoryGroupId
       AND thread.threadId = 0
where thread.messageId is NULL;

update xcolab_ContestDiscussion as cd
  join xcolab_DiscussionMessage as thread
    on cd.DiscussionId = thread.categoryGroupId
       and thread.threadId = 0
set cd.DiscussionId = thread.messageId;

-- set thread titles to discussion category for all non-discussion threads
update comment_Thread as thread
  join xcolab_DiscussionMessage as dm
    on dm.messageId = thread.threadId and dm.threadId = 0
  join xcolab_DiscussionCategoryGroup as dcg
    on dcg.id_ = dm.categoryGroupId
set thread.title = dcg.description,
  thread.isQuiet = dcg.isQuiet
where dcg.id_ not in (select groupId from comment_CategoryGroup);

-- =======================================
-- Configuration Attributes (new table)
-- =======================================
DROP TABLE IF EXISTS `xcolab_ConfigurationAttribute`;
CREATE TABLE `xcolab_ConfigurationAttribute` (
  `name` varchar(75) COLLATE utf8mb4_unicode_ci NOT NULL,
  `additionalId` bigint(20) NOT NULL,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` longtext COLLATE utf8mb4_unicode_ci,
  `realValue` double DEFAULT NULL,
  PRIMARY KEY (`name`,`additionalId`)
);

# TODO: what's the admin email?
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('ADMIN_EMAIL', 0, 0, 'admin@resiliencedialogues.org', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('ADMIN_FROM_EMAIL', 0, 0, 'no-reply@resiliencedialogues.org', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('BETA_RIBBON_SHOW', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('COLAB_NAME', 0, 0, 'Resilience Dialogues', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('COLAB_SHORT_NAME', 0, 0, 'Resilience Dialogues', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('COLAB_URL', 0, 0, 'http://resiliencedialogues.org', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('DEFAULT_CONTEST_TYPE_ID', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('DEFAULT_TIME_ZONE_ID', 0, 0, 'America/New_York', 0);
# TODO: FB SSO accounts
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('FACEBOOK_APPLICATION_ID', 0, 0, '1730936890460087', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('FACEBOOK_APPLICATION_SECRET', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('FACEBOOK_VERIFIED_REQUIRED', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('FILTER_PROFANITY', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('FLAGGING_ALLOW_MEMBERS', 0, 0, '', 0);
# TODO: GA key
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('GOOGLE_ANALYTICS_KEY', 0, 0, 'UA-11944310-1', 0);
# TODO: Google SSO account
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('GOOGLE_AUTH_CLIENT_ID', 0, 0, '668105823094-kts12sd1rkb899tkb1qvf204qt9a9051.apps.googleusercontent.com', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('GOOGLE_AUTH_CLIENT_SECRET', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('IS_MY_EMMA_ACTIVE', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('IS_POINTS_ACTIVE', 0, 1, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('PUBLISH_JUDGING_RESULTS', 0, 1, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('SHARED_COLAB_PORT', 0, 0, '8080', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('SHOW_CONTEST_COUNTDOWN', 0, 1, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('SHARED_COLAB_LOCATION', 0, 0, 'localhost', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('OPEN_GRAPH_SHARE_DESCRIPTION', 0, 0, 'In the Resilience Dialogues, you can explore and discuss key resilience issues facing your community.', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('OPEN_GRAPH_SHARE_TITLE', 0, 0, 'Resilience Dialogues', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('PARTNER_COLAB_LOCATION', 0, 0, 'localhost', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('PARTNER_COLAB_NAME', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('PARTNER_COLAB_PORT', 0, 0, '8080', 0);

-- =======================================
-- Member email templates
-- =======================================

 INTO xcolab_ContestEmailTemplate (subject, header, footer, type_) VALUES ('<system-link/>: Reset Your Password', 'Dear <sender-name/> <sender-lastname/>,<br /> <br /> You can reset your password for resiliencedialogues.mit.edu at <password-reset-link/> <br /> <br /> The request for a new password was made from <sender-ip/><br /> <br /> Sincerely,<br /> The Resilience Dialogues Team<br /> admin@resiliencedialogues.org/<br /> http://resiliencedialogues.org/', '', 'MEMBER_RESET_PASSWORD_DEFAULT');

-- =======================================
-- deployment migrations (since Singapore migration)
-- =======================================

-- --------------------------------
-- deployment-2016-09-04.sql (partial)
-- --------------------------------
-- [COLAB-953] add new auto increment keys
ALTER TABLE xcolab_TrackedVisit MODIFY id_ BIGINT(20) AUTO_INCREMENT;
ALTER TABLE xcolab_TrackedVisitor2User MODIFY id_ BIGINT(20) AUTO_INCREMENT;

-- [COLAB-1333] add new auto increment key
ALTER TABLE xcolab_LoginLog MODIFY pk BIGINT(20) AUTO_INCREMENT;

-- [COLAB-1348] Shared colab
ALTER TABLE `xcolab_Contest` ADD COLUMN `isSharedContest` TINYINT(4) NULL DEFAULT 0;

-- --------------------------------
-- x deployment-2016-09-11.sql (not relevant)
-- deployment-2016-09-18.sql
-- --------------------------------
-- COLAB-1375
ALTER TABLE xcolab_ContestPhase MODIFY ContestPhasePK BIGINT(20) AUTO_INCREMENT;
ALTER TABLE xcolab_ContestSchedule MODIFY id_ BIGINT(20) AUTO_INCREMENT;

-- delete empty contest schedules
delete from xcolab_ContestSchedule where not exists (select * from xcolab_ContestPhase where contestScheduleId = id_);

-- --------------------------------
-- deployment-2016-09-25.sql
-- --------------------------------
-- COLAB-1387
ALTER TABLE comment_Thread ADD sharedColabThreadId BIGINT(20) NULL;

-- --------------------------------
-- deployment-2016-10-09.sql
-- --------------------------------
ALTER TABLE `MembershipRequest`
  CHANGE COLUMN `membershipRequestId` `membershipRequestId` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_PointsDistributionConfiguration`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_PointType`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_Proposal`
  CHANGE COLUMN `proposalId` `proposalId` BIGINT(20) NOT NULL AUTO_INCREMENT ;

DELETE FROM `xcolab_ProposalAttribute` WHERE `id_`='0';

ALTER TABLE `xcolab_ProposalAttribute`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

DELETE FROM `xcolab_ProposalContestPhaseAttribute` WHERE `id_`='0';

ALTER TABLE `xcolab_ProposalContestPhaseAttribute`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_ProposalMoveHistory`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_ProposalRating`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_ProposalUnversionedAttribute`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_ContestPhaseType`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

UPDATE `xcolab_FocusArea` set id_ = 2  WHERE `id_`='0';
UPDATE `xcolab_FocusAreaOntologyTerm` set focusAreaId = 2 where focusAreaId = 0;


ALTER TABLE `xcolab_FocusArea`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_OntologySpace`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_PlanSectionDefinition`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_PlanTemplate`
  CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `Group_`
  CHANGE COLUMN `groupId` `groupId` BIGINT(20) NOT NULL AUTO_INCREMENT ;

-- --------------------------------
-- x deployment-2016-10-30_2.sql (not relevant)
-- deployment-2016-11-08.sql
-- --------------------------------
-- create shared table
CREATE TABLE IF NOT EXISTS `sharedcolab_SharedContest` (
  `sharedContestId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `contestName` VARCHAR(255) NULL,
  `createDate` DATETIME NULL,
  `colabOrigin` VARCHAR(45) NULL,
  PRIMARY KEY (`sharedContestId`));

-- taking auto increment from table
ALTER TABLE `xcolab_Contest`
  CHANGE COLUMN `ContestPK` `ContestPK` BIGINT(20) NOT NULL;

-- adding shared origin column
ALTER TABLE `xcolab_Contest`
  ADD COLUMN `sharedOrigin` VARCHAR(45) NULL AFTER `isSharedContest`;

-- updating all contests to current colab of origin
update xcolab_Contest set sharedOrigin = (select stringValue from xcolab_ConfigurationAttribute where name = "COLAB_NAME");