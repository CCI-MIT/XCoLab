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

ALTER TABLE `members_Member` ADD COLUMN `portraitFileEntryId` bigint(20) DEFAULT NULL;
ALTER TABLE `members_Member` ADD COLUMN `reportKarma` int(11) DEFAULT '100';
ALTER TABLE `members_Member` ADD FULLTEXT INDEX `members_Member_names_bio` (`firstName`,`lastName`,`shortBio`,`screenName`);

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

# TODO: change this to a change table
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

ALTER TABLE `xcolab_Contest` DROP COLUMN `fellowDiscussionGroupId`;

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
-- new configuration attributes
-- =======================================
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('BETA_RIBBON_SHOW', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('FACEBOOK_APPLICATION_ID', 0, 0, '1730936890460087', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('FACEBOOK_APPLICATION_SECRET', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('FACEBOOK_VERIFIED_REQUIRED', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('FILTER_PROFANITY', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('FLAGGING_ALLOW_MEMBERS', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('GOOGLE_ANALYTICS_KEY', 0, 0, 'UA-11944310-1', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('GOOGLE_AUTH_CLIENT_ID', 0, 0, '668105823094-kts12sd1rkb899tkb1qvf204qt9a9051.apps.googleusercontent.com', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('GOOGLE_AUTH_CLIENT_SECRET', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('IS_MY_EMMA_ACTIVE', 0, 0, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('IS_POINTS_ACTIVE', 0, 1, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('PUBLISH_JUDGING_RESULTS', 0, 1, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('SHARED_COLAB_PORT', 0, 0, '8080', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('SHOW_CONTEST_COUNTDOWN', 0, 1, '', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('SHARED_COLAB_LOCATION', 0, 0, 'localhost', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('OPEN_GRAPH_SHARE_DESCRIPTION', 0, 0, 'In Crowdsensor, brainstorm with the community about what trends could impact Singapore''s plans to become a Smart Nation by 2030', 0);
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('OPEN_GRAPH_SHARE_TITLE', 0, 0, 'Crowdsensor', 0);


-- TODO: insert message for this colab
UPDATE xcolab_ContestEmailTemplate SET subject = 'Welcome to Crowdsensor!', header = '<p>Dear <sender-name/> <sender-lastname/>,</p>  <p>Welcome! You recently created an account with us under the username <span style="font-size:14px;"><strong><sender-screenname/></strong></span>.</p> Sincerely, <br />The Crowdsensor Team<br />admin@crowdsensor.org<br/>http://crowdsensor.org', footer = '' WHERE type_ = 'MEMBER_REGISTERED_DEFAULT';
UPDATE xcolab_ContestEmailTemplate SET subject = '<system-link/>: Reset Your Password', header = 'Dear <sender-name/> <sender-lastname/>,<br /> <br /> You can reset your password for crowdsensor.org at <password-reset-link/> <br /> <br /> The request for a new password was made from <sender-ip/><br /> <br /> Sincerely,<br /> The Crowdsensor Team<br /> admin@crowdsensor.org<br /> http://crowdsensor.org', footer = '' WHERE type_ = 'MEMBER_RESET_PASSWORD_DEFAULT';


