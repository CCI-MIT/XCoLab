SET FOREIGN_KEY_CHECKS = 0;
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
) ENGINE=InnoDB AUTO_INCREMENT=1722454 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalVote` (
  `proposalId` bigint(20) DEFAULT NULL,
  `contestPhaseId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `isValid` tinyint(4) DEFAULT NULL,
  `confirmationEmailSendDate` datetime DEFAULT NULL,
  `confirmationToken` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`contestPhaseId`,`userId`),
  KEY `IX_A4D26028` (`contestPhaseId`,`userId`),
  KEY `IX_EA28CF99` (`proposalId`),
  KEY `IX_43559ACF` (`proposalId`,`contestPhaseId`),
  KEY `IX_562EB409` (`proposalId`,`contestPhaseId`,`userId`),
  KEY `IX_5E8D7ED3` (`proposalId`,`userId`),
  KEY `IX_497348F2` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestTeamMember` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `contestId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_E1468F04` (`contestId`)
) ENGINE=InnoDB AUTO_INCREMENT=11726 DEFAULT CHARSET=utf8;

CREATE TABLE `sharedcolab_SharedMember` (
  `sharedMemberId` bigint(20) NOT NULL AUTO_INCREMENT,
  `screenName` varchar(255) DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `colabOrigin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sharedMemberId`)
) ENGINE=InnoDB AUTO_INCREMENT=2651615 DEFAULT CHARSET=utf8;

CREATE TABLE `flagging_ReportTarget` (
  `reportTargetId` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `reason` varchar(50) NOT NULL,
  `notificationThreshold` int(11) NOT NULL DEFAULT '0',
  `screeningThreshold` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`reportTargetId`),
  UNIQUE KEY `flagging_ReportTarget__typeReason` (`type`,`reason`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_BalloonLink` (
  `uuid_` varchar(75) NOT NULL,
  `targetUrl` varchar(75) DEFAULT NULL,
  `visits` int(11) DEFAULT NULL,
  `balloonUserUuid` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid_`),
  KEY `IX_1AD9FFEC` (`balloonUserUuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_FocusArea` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `order_` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_B61888D4` (`name`(50))
) ENGINE=InnoDB AUTO_INCREMENT=1313722 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_Message` (
  `messageId` bigint(20) NOT NULL AUTO_INCREMENT,
  `fromId` bigint(20) DEFAULT NULL,
  `repliesTo` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `subject` varchar(2048) DEFAULT NULL,
  `content` longtext,
  PRIMARY KEY (`messageId`),
  KEY `IX_9DF5C6F0` (`fromId`)
) ENGINE=InnoDB AUTO_INCREMENT=1389862 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestDiscussion` (
  `DiscussionId` bigint(20) NOT NULL,
  `ContestId` bigint(20) DEFAULT NULL,
  `Tab` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`DiscussionId`),
  KEY `IX_DD06DA92` (`ContestId`,`Tab`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalMoveHistory` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `sourceProposalId` bigint(20) DEFAULT NULL,
  `sourceContestId` bigint(20) DEFAULT NULL,
  `sourcePhaseId` bigint(20) DEFAULT NULL,
  `targetProposalId` bigint(20) DEFAULT NULL,
  `targetContestId` bigint(20) DEFAULT NULL,
  `targetPhaseId` bigint(20) DEFAULT NULL,
  `movingUserId` bigint(20) DEFAULT NULL,
  `moveDate` datetime DEFAULT NULL,
  `moveType` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_101B90C3` (`sourceContestId`),
  KEY `IX_A0D3722A` (`sourcePhaseId`),
  KEY `IX_A98333DD` (`sourceProposalId`),
  KEY `IX_E78B9567` (`sourceProposalId`,`sourceContestId`),
  KEY `IX_9920218D` (`targetContestId`),
  KEY `IX_FA79AD74` (`targetPhaseId`),
  KEY `IX_4110BC53` (`targetProposalId`),
  KEY `IX_6001D87B` (`targetProposalId`,`targetContestId`)
) ENGINE=InnoDB AUTO_INCREMENT=1923 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalContestPhaseAttribute` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `proposalId` bigint(20) DEFAULT NULL,
  `contestPhaseId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `additionalId` bigint(20) DEFAULT NULL,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` longtext,
  `realValue` double DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_159C0FC9` (`contestPhaseId`),
  KEY `IX_EAA7A52A` (`contestPhaseId`,`proposalId`),
  KEY `IX_68DFE42A` (`proposalId`,`contestPhaseId`),
  KEY `IX_8F351DBF` (`proposalId`,`contestPhaseId`,`name`,`additionalId`)
) ENGINE=InnoDB AUTO_INCREMENT=50223 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContentArticle` (
  `contentArticleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `authorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `maxVersionId` bigint(20) DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `editRoleGroupId` bigint(20) DEFAULT NULL,
  `viewRoleGroupId` bigint(20) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`contentArticleId`)
) ENGINE=InnoDB AUTO_INCREMENT=1758 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_MemberCategory` (
  `roleId` bigint(20) NOT NULL,
  `displayName` varchar(75) DEFAULT NULL,
  `categoryName` varchar(75) DEFAULT NULL,
  `sortOrder` bigint(20) DEFAULT NULL,
  `showInList` tinyint(4) DEFAULT NULL,
  `imageName` varchar(75) DEFAULT NULL,
  `description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  KEY `IX_B3858EE9` (`displayName`),
  KEY `IX_8336AE28` (`showInList`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_StaffMember` (
  `id_` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `firstNames` varchar(75) DEFAULT NULL,
  `lastName` varchar(75) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `photoUrl` varchar(255) DEFAULT NULL,
  `role` varchar(75) DEFAULT NULL,
  `organization` varchar(75) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_9C5CE364` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_BalloonUserTracking` (
  `uuid_` varchar(75) NOT NULL,
  `email` varchar(200) DEFAULT NULL,
  `parent` varchar(75) DEFAULT NULL,
  `ip` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `registrationDate` datetime DEFAULT NULL,
  `formFiledDate` datetime DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `balloonTextId` bigint(20) DEFAULT NULL,
  `referrer` varchar(500) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `country` varchar(75) DEFAULT NULL,
  `extraData` longtext,
  `balloonLinkUuid` varchar(75) DEFAULT NULL,
  `balloonLinkContext` varchar(75) DEFAULT NULL,
  `userAgent` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`uuid_`),
  KEY `IX_AFDD82EB` (`email`(50))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestPhase` (
  `ContestPhasePK` bigint(20) NOT NULL AUTO_INCREMENT,
  `ContestPK` bigint(20) DEFAULT NULL,
  `ContestPhaseType` bigint(20) DEFAULT NULL,
  `contestScheduleId` bigint(20) DEFAULT NULL,
  `fellowScreeningActive` tinyint(4) DEFAULT NULL,
  `contestPhaseAutopromote` varchar(75) DEFAULT NULL,
  `ContestPhaseDescriptionOverride` longtext,
  `phaseActiveOverride` tinyint(4) DEFAULT NULL,
  `phaseInactiveOverride` tinyint(4) DEFAULT NULL,
  `PhaseStartDate` datetime DEFAULT NULL,
  `PhaseEndDate` datetime DEFAULT NULL,
  `PhaseBufferEndDated` datetime DEFAULT NULL,
  `nextStatus` varchar(75) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ContestPhasePK`),
  KEY `IX_ED61C03C` (`ContestPK`),
  KEY `IX_2BA2B787` (`ContestPK`,`PhaseStartDate`,`PhaseEndDate`),
  KEY `IX_9F1D3B81` (`ContestPK`,`phaseActiveOverride`),
  KEY `IX_4F735B66` (`ContestPK`,`phaseInactiveOverride`),
  KEY `IX_1BB9EC37` (`contestPhaseAutopromote`),
  KEY `IX_D9B6142C` (`contestScheduleId`,`ContestPK`)
) ENGINE=InnoDB AUTO_INCREMENT=1318675 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestPhaseRibbonType` (
  `id_` bigint(20) NOT NULL,
  `ribbon` int(11) DEFAULT NULL,
  `hoverText` varchar(75) DEFAULT NULL,
  `description` varchar(75) DEFAULT NULL,
  `copyOnPromote` tinyint(4) DEFAULT NULL,
  `sortOrder` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ImpactTemplateFocusAreaList` (
  `focusAreaListId` bigint(20) NOT NULL,
  `name` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`focusAreaListId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_OntologySpace` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `description` longtext,
  `order_` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_18FC4546` (`name`(50))
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ModelOutputItem` (
  `modelOutputItemModifierPK` bigint(20) NOT NULL,
  `modelId` bigint(20) DEFAULT NULL,
  `modelOutputItemId` bigint(20) DEFAULT NULL,
  `modelOutputItemOrder` int(11) DEFAULT NULL,
  `modelItemRangePolicy` varchar(2048) DEFAULT NULL,
  `modelItemRangeMessage` varchar(2048) DEFAULT NULL,
  `modelItemErrorPolicy` varchar(2048) DEFAULT NULL,
  `modelItemErrorMessage` varchar(2048) DEFAULT NULL,
  `modelItemLabelFormat` varchar(2048) DEFAULT NULL,
  `modelItemIsVisible` tinyint(4) DEFAULT NULL,
  `itemType` varchar(256) DEFAULT NULL,
  `relatedOutputItem` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`modelOutputItemModifierPK`),
  KEY `IX_A17AABB` (`modelOutputItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_FocusAreaOntologyTerm` (
  `focusAreaId` bigint(20) NOT NULL,
  `ontologyTermId` bigint(20) NOT NULL,
  `order_` int(11) DEFAULT NULL,
  PRIMARY KEY (`focusAreaId`,`ontologyTermId`),
  KEY `IX_CE67B1A0` (`focusAreaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalReference` (
  `proposalId` bigint(20) NOT NULL,
  `subProposalId` bigint(20) NOT NULL,
  `sectionAttributeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`proposalId`,`subProposalId`),
  KEY `IX_3C82622A` (`proposalId`),
  KEY `IX_AABA9B94` (`subProposalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ImpactTemplateSeries` (
  `seriesId` bigint(20) NOT NULL,
  `iterationId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`seriesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `UserGroupRole` (
  `userId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`groupId`,`roleId`),
  KEY `IX_1B988D7A` (`groupId`),
  KEY `IX_871412DF` (`groupId`,`roleId`),
  KEY `IX_887A2C95` (`roleId`),
  KEY `IX_887BE56A` (`userId`),
  KEY `IX_4D040680` (`userId`,`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `googleId` varchar(50) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `loginIP` varchar(75) DEFAULT NULL,
  `loginDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `forgotPasswordToken` varchar(255) DEFAULT NULL,
  `forgotPasswordTokenExpireTime` datetime DEFAULT NULL,
  `portraitFileEntryId` bigint(20) DEFAULT NULL,
  `reportKarma` int(11) DEFAULT '100',
  `autoRegisteredMemberStatus` int(4) DEFAULT '0',
  `uuid` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `IX_XCOLAB_MEMBERS_SCREEN_NAME` (`screenName`),
  UNIQUE KEY `IX_XCOLAB_MEMBERS_EMAIL_ADDRESS` (`emailAddress`),
  KEY `IX_XCOLAB_MEMBERS_CREATE_DATE` (`createDate`,`modifiedDate`),
  KEY `IX_XCOLAB_MEMBERS_MODIFIED_DATE` (`modifiedDate`),
  KEY `IX_XCOLAB_MEMBERS_FACEBOOK_ID` (`facebookId`),
  KEY `IX_XCOLAB_MEMBERS_OPEN_ID` (`openId`)
  /*, FULLTEXT KEY `members_Member_names_bio` (`firstName`,`lastName`,`shortBio`,`screenName`)*/
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalVersion` (
  `proposalId` bigint(20) NOT NULL,
  `version` int(11) NOT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateType` varchar(75) DEFAULT NULL,
  `updateAdditionalId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`proposalId`,`version`),
  KEY `IX_59E3C2F7` (`proposalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_PointsDistributionConfiguration` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `proposalId` bigint(20) DEFAULT NULL,
  `pointTypeId` bigint(20) DEFAULT NULL,
  `targetUserId` bigint(20) DEFAULT NULL,
  `targetSubProposalId` bigint(20) DEFAULT NULL,
  `targetPlanSectionDefinitionId` bigint(20) DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_7113A2E0` (`proposalId`),
  KEY `IX_1FDF3BB5` (`proposalId`,`pointTypeId`),
  KEY `IX_27247AAA` (`targetPlanSectionDefinitionId`),
  KEY `IX_7D0AD60D` (`targetSubProposalId`),
  KEY `IX_D44477AA` (`targetUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=2502 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContentArticleVersion` (
  `contentArticleVersionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `contentArticleId` bigint(20) DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `title` varchar(555) DEFAULT NULL,
  `content` longtext,
  PRIMARY KEY (`contentArticleVersionId`)
) ENGINE=InnoDB AUTO_INCREMENT=4059 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_TrackedVisit` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid_` varchar(36) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(2) DEFAULT NULL,
  `url` varchar(2048) DEFAULT NULL,
  `browser` longtext,
  `headers` longtext,
  `referer` varchar(2048) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_21569857` (`uuid_`)
) ENGINE=InnoDB AUTO_INCREMENT=4381468 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestSchedule` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(75) DEFAULT NULL,
  `description` varchar(75) DEFAULT NULL,
  `status` varchar(75) DEFAULT NULL,
  `baseScheduleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2014 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestCollectionCard` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent` bigint(20) DEFAULT NULL,
  `big_ontology_term` bigint(20) DEFAULT NULL,
  `small_ontology_term` bigint(20) DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `short_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `visible` tinyint(4) DEFAULT '1',
  `order` int(11) DEFAULT '0',
  `ontology_term_to_load` bigint(20) DEFAULT NULL,
  `only_featured` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id_`),
  KEY `big_ontology_term_idx` (`big_ontology_term`),
  KEY `small_ontology_term_idx` (`small_ontology_term`),
  KEY `ontology_term_to_load_idx` (`ontology_term_to_load`),
  KEY `FK_parent` (`parent`),
  CONSTRAINT `FK_big_ontology_term` FOREIGN KEY (`big_ontology_term`) REFERENCES `xcolab_OntologyTerm` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ontology_term_to_load` FOREIGN KEY (`ontology_term_to_load`) REFERENCES `xcolab_OntologyTerm` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_parent` FOREIGN KEY (`parent`) REFERENCES `xcolab_ContestCollectionCard` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_small_ontology_term` FOREIGN KEY (`small_ontology_term`) REFERENCES `xcolab_OntologyTerm` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

CREATE TABLE `files_FileEntry` (
  `fileEntryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `fileEntryExtension` varchar(10) DEFAULT NULL,
  `fileEntryName` varchar(255) DEFAULT NULL,
  `fileEntrySize` int(11) DEFAULT NULL,
  PRIMARY KEY (`fileEntryId`)
) ENGINE=InnoDB AUTO_INCREMENT=2513729 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ImpactIteration` (
  `iterationId` bigint(20) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`iterationId`,`year`),
  KEY `IX_512E56E1` (`iterationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ModelCategory` (
  `modelCategoryPK` bigint(20) NOT NULL,
  `modelCategoryName` varchar(256) DEFAULT NULL,
  `modelCategoryDescription` varchar(2048) DEFAULT NULL,
  `modelCategoryDisplayWeight` int(11) DEFAULT NULL,
  PRIMARY KEY (`modelCategoryPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sharedcolab_SharedContest` (
  `sharedContestId` bigint(20) NOT NULL AUTO_INCREMENT,
  `contestName` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `colabOrigin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sharedContestId`)
) ENGINE=InnoDB AUTO_INCREMENT=11305023 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=1451707 DEFAULT CHARSET=utf8;

CREATE TABLE `SocialActivity` (
  `activityId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` bigint(20) DEFAULT NULL,
  `mirrorActivityId` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `extraData` longtext,
  `receiverUserId` bigint(20) DEFAULT NULL,
  `activitySetId` bigint(20) DEFAULT NULL,
  `parentClassNameId` bigint(20) DEFAULT NULL,
  `parentClassPK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`activityId`),
  UNIQUE KEY `IX_8F32DEC9` (`groupId`,`userId`,`createDate`,`classNameId`,`classPK`,`type_`,`receiverUserId`),
  KEY `IX_82E39A0C` (`classNameId`),
  KEY `IX_A853C757` (`classNameId`,`classPK`),
  KEY `IX_64B1BC66` (`companyId`),
  KEY `IX_2A2468` (`groupId`),
  KEY `IX_1271F25F` (`mirrorActivityId`),
  KEY `IX_1F00C374` (`mirrorActivityId`,`classNameId`,`classPK`),
  KEY `IX_121CA3CB` (`receiverUserId`),
  KEY `IX_3504B8BC` (`userId`),
  KEY `IX_FB604DC7` (`groupId`,`userId`,`classNameId`,`classPK`,`type_`,`receiverUserId`),
  KEY `IX_F542E9BC` (`activitySetId`),
  KEY `IX_D0E9029E` (`classNameId`,`classPK`,`type_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalRating` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `proposalId` bigint(20) DEFAULT NULL,
  `contestPhaseId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `ratingValueId` bigint(20) DEFAULT NULL,
  `comment_` longtext,
  `commentEnabled` tinyint(4) DEFAULT NULL,
  `otherDataString` varchar(75) DEFAULT NULL,
  `onlyForInternalUsage` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=23406 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestPhaseType` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) DEFAULT NULL,
  `description` longtext,
  `status` varchar(75) DEFAULT NULL,
  `fellowScreeningActiveDefault` tinyint(4) DEFAULT NULL,
  `contestPhaseAutopromoteDefault` varchar(75) DEFAULT NULL,
  `invisible` tinyint(4) DEFAULT NULL,
  `pointsAccessible` int(11) DEFAULT NULL,
  `defaultPromotionType` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=402 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalAttributeType` (
  `name` varchar(75) NOT NULL,
  `visibleInVersionHistory` tinyint(4) DEFAULT NULL,
  `copyOnPromote` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `User_` (
  `uuid_` varchar(75) DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `defaultUser` tinyint(4) DEFAULT NULL,
  `contactId` bigint(20) DEFAULT NULL,
  `password_` varchar(75) DEFAULT NULL,
  `passwordEncrypted` tinyint(4) DEFAULT NULL,
  `passwordReset` tinyint(4) DEFAULT NULL,
  `passwordModifiedDate` datetime DEFAULT NULL,
  `reminderQueryQuestion` varchar(75) DEFAULT NULL,
  `reminderQueryAnswer` varchar(75) DEFAULT NULL,
  `graceLoginCount` int(11) DEFAULT NULL,
  `screenName` varchar(75) DEFAULT NULL,
  `emailAddress` varchar(75) DEFAULT NULL,
  `openId` varchar(1024) DEFAULT NULL,
  `portraitId` bigint(20) DEFAULT NULL,
  `languageId` varchar(75) DEFAULT NULL,
  `timeZoneId` varchar(75) DEFAULT NULL,
  `greeting` varchar(255) DEFAULT NULL,
  `comments` longtext,
  `firstName` varchar(75) DEFAULT NULL,
  `middleName` varchar(75) DEFAULT NULL,
  `lastName` varchar(75) DEFAULT NULL,
  `jobTitle` varchar(75) DEFAULT NULL,
  `loginDate` datetime DEFAULT NULL,
  `loginIP` varchar(75) DEFAULT NULL,
  `lastLoginDate` datetime DEFAULT NULL,
  `lastLoginIP` varchar(75) DEFAULT NULL,
  `lastFailedLoginDate` datetime DEFAULT NULL,
  `failedLoginAttempts` int(11) DEFAULT NULL,
  `lockout` tinyint(4) DEFAULT NULL,
  `lockoutDate` datetime DEFAULT NULL,
  `agreedToTermsOfUse` tinyint(4) DEFAULT NULL,
  `socialContributionEquity` double DEFAULT NULL,
  `socialParticipationEquity` double DEFAULT NULL,
  `socialPersonalEquity` double DEFAULT NULL,
  `facebookId` bigint(20) DEFAULT NULL,
  `digest` varchar(256) DEFAULT NULL,
  `emailAddressVerified` tinyint(4) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `ldapServerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `IX_615E9F7A` (`companyId`,`emailAddress`),
  UNIQUE KEY `IX_C5806019` (`companyId`,`screenName`),
  UNIQUE KEY `IX_9782AD88` (`companyId`,`userId`),
  UNIQUE KEY `IX_5ADBE171` (`contactId`),
  KEY `IX_3A1E834E` (`companyId`),
  KEY `IX_6EF03E4E` (`companyId`,`defaultUser`),
  KEY `IX_762F63C6` (`emailAddress`),
  KEY `IX_A18034A4` (`portraitId`),
  KEY `IX_E0422BDA` (`uuid_`),
  KEY `IX_1D731F03` (`companyId`,`facebookId`),
  KEY `IX_89509087` (`companyId`,`openId`(255)),
  KEY `IX_F6039434` (`companyId`,`status`),
  KEY `IX_740C4D0C` (`companyId`,`createDate`),
  KEY `IX_BCFDA257` (`companyId`,`createDate`,`modifiedDate`),
  KEY `IX_EE8ABD19` (`companyId`,`modifiedDate`),
  KEY `IX_405CC0E` (`uuid_`,`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `members_RoleGroupRoles` (
  `roleGroupId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleGroupId`,`roleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `comment_Thread` (
  `threadId` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoryId` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `createDate` datetime NOT NULL,
  `deletedDate` datetime DEFAULT NULL,
  `isQuiet` tinyint(4) DEFAULT '0',
  `sharedColabThreadId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`threadId`),
  KEY `comment_Thread__categoryId` (`categoryId`,`createDate`)
) ENGINE=InnoDB AUTO_INCREMENT=1365984 DEFAULT CHARSET=utf8;

CREATE TABLE `Role_` (
  `roleId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `title` longtext,
  `description` longtext,
  `type_` int(11) DEFAULT NULL,
  `subtype` varchar(75) DEFAULT NULL,
  `uuid_` varchar(75) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `IX_A88E424E` (`companyId`,`classNameId`,`classPK`),
  UNIQUE KEY `IX_EBC931B8` (`companyId`,`name`),
  KEY `IX_449A10B9` (`companyId`),
  KEY `IX_CBE204` (`type_`,`subtype`),
  KEY `IX_F436EC8E` (`name`),
  KEY `IX_5EB4E2FB` (`subtype`),
  KEY `IX_F3E1C6FC` (`companyId`,`type_`),
  KEY `IX_F92B66E6` (`type_`),
  KEY `IX_26DB26C5` (`uuid_`),
  KEY `IX_B9FF6043` (`uuid_`,`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_OntologyTerm` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentId` bigint(20) DEFAULT NULL,
  `ontologySpaceId` bigint(20) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `descriptionUrl` varchar(1024) DEFAULT NULL,
  `order_` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_50DE58D6` (`name`(50)),
  KEY `IX_3E2347AB` (`ontologySpaceId`),
  KEY `IX_FEB70AB0` (`parentId`),
  KEY `IX_A6AC072` (`parentId`,`ontologySpaceId`)
) ENGINE=InnoDB AUTO_INCREMENT=1311495 DEFAULT CHARSET=utf8;

CREATE TABLE `Group_` (
  `groupId` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyId` bigint(20) DEFAULT NULL,
  `creatorUserId` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `parentGroupId` bigint(20) DEFAULT NULL,
  `liveGroupId` bigint(20) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `description` longtext,
  `type_` int(11) DEFAULT NULL,
  `typeSettings` longtext,
  `friendlyURL` varchar(255) DEFAULT NULL,
  `active_` tinyint(4) DEFAULT NULL,
  `site` tinyint(4) DEFAULT NULL,
  `uuid_` varchar(75) DEFAULT NULL,
  `treePath` longtext,
  `manualMembership` tinyint(4) DEFAULT NULL,
  `membershipRestriction` int(11) DEFAULT NULL,
  `remoteStagingGroupCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`groupId`),
  KEY `IX_16218A38` (`liveGroupId`),
  KEY `IX_7B590A7A` (`type_`,`active_`),
  KEY `IX_ABA5CEC2` (`companyId`),
  KEY `IX_B584B5CC` (`companyId`,`classNameId`),
  KEY `IX_ABE2D54` (`companyId`,`classNameId`,`parentGroupId`),
  KEY `IX_5D75499E` (`companyId`,`parentGroupId`),
  KEY `IX_6C499099` (`companyId`,`parentGroupId`,`site`),
  KEY `IX_63A2AABD` (`companyId`,`site`),
  KEY `IX_F981514E` (`uuid_`),
  KEY `IX_26CC761A` (`uuid_`,`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=2854011 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestTeamMemberRole` (
  `id_` bigint(20) NOT NULL,
  `role` varchar(75) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContentPage` (
  `pageId` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `menuArticleId` bigint(11) DEFAULT NULL,
  `contentArticleId` bigint(11) NOT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `modifiedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pageId`),
  UNIQUE KEY `xcolab_ContentPage_title_uindex` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

CREATE TABLE `members_RoleGroup` (
  `roleGroupId` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleGroupId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `comment_CategoryGroup` (
  `groupId` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `url` varchar(200) DEFAULT NULL,
  `isQuiet` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=702 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ImpactDefaultSeriesData` (
  `seriesId` bigint(20) NOT NULL,
  `year` int(11) NOT NULL,
  `value` double DEFAULT NULL,
  PRIMARY KEY (`seriesId`,`year`),
  KEY `IX_E8941CB2` (`seriesId`),
  KEY `IX_97B70823` (`seriesId`,`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalRatingValue` (
  `id_` bigint(20) NOT NULL,
  `ratingTypeId` bigint(20) DEFAULT NULL,
  `value` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `MembershipRequest` (
  `membershipRequestId` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `comments` longtext,
  `replyComments` longtext,
  `replyDate` datetime DEFAULT NULL,
  `replierUserId` bigint(20) DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  PRIMARY KEY (`membershipRequestId`),
  KEY `IX_8A1CC4B` (`groupId`),
  KEY `IX_C28C72EC` (`groupId`,`statusId`),
  KEY `IX_66D70879` (`userId`),
  KEY `IX_35AA8FA6` (`groupId`,`userId`,`statusId`)
) ENGINE=InnoDB AUTO_INCREMENT=2851664 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ModelDiscussion` (
  `modelDiscussionId` bigint(20) NOT NULL,
  `modelId` bigint(20) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`modelDiscussionId`),
  KEY `IX_C4F6226E` (`categoryId`),
  KEY `IX_72D6F073` (`modelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ModelInputGroup` (
  `modelInputGroupPK` bigint(20) NOT NULL,
  `modelId` bigint(20) DEFAULT NULL,
  `nameAndDescriptionMetaDataId` bigint(20) DEFAULT NULL,
  `name` varchar(1024) DEFAULT NULL,
  `description` longtext,
  `displayItemOrder` int(11) DEFAULT NULL,
  `groupType` varchar(256) DEFAULT NULL,
  `parentGroupPK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`modelInputGroupPK`),
  KEY `IX_23506DA6` (`modelId`),
  KEY `IX_CC01932` (`parentGroupPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_PlanTemplate` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) DEFAULT NULL,
  `baseTemplateId` bigint(20) DEFAULT NULL,
  `impactSeriesTemplateId` bigint(20) DEFAULT NULL,
  `focusAreaListTemplateId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=1303316 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ConfigurationAttribute` (
  `name` varchar(75) NOT NULL,
  `additionalId` bigint(20) NOT NULL,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` longtext,
  `realValue` double DEFAULT NULL,
  PRIMARY KEY (`name`,`additionalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_TrackedVisitor2User` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid_` varchar(36) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=109063 DEFAULT CHARSET=utf8;

CREATE TABLE `Users_Groups` (
  `userId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`groupId`),
  KEY `IX_C4F9E699` (`groupId`),
  KEY `IX_F10B6C6B` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_PointType` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentPointTypeId` bigint(20) DEFAULT NULL,
  `percentageOfParent` double DEFAULT NULL,
  `distributionStrategy` varchar(75) DEFAULT NULL,
  `receiverLimitationStrategy` varchar(75) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `sort` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_8DD75651` (`parentPointTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_Proposal2Phase` (
  `proposalId` bigint(20) NOT NULL,
  `contestPhaseId` bigint(20) NOT NULL,
  `versionFrom` int(11) DEFAULT NULL,
  `versionTo` int(11) DEFAULT NULL,
  `sortWeight` int(11) DEFAULT NULL,
  `autopromoteCandidate` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`proposalId`,`contestPhaseId`),
  KEY `IX_DBA8038D` (`contestPhaseId`),
  KEY `IX_D273A4B8` (`proposalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

CREATE TABLE `Contact_` (
  `contactId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `accountId` bigint(20) DEFAULT NULL,
  `parentContactId` bigint(20) DEFAULT NULL,
  `firstName` varchar(75) DEFAULT NULL,
  `middleName` varchar(75) DEFAULT NULL,
  `lastName` varchar(75) DEFAULT NULL,
  `prefixId` int(11) DEFAULT NULL,
  `suffixId` int(11) DEFAULT NULL,
  `male` tinyint(4) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `smsSn` varchar(75) DEFAULT NULL,
  `aimSn` varchar(75) DEFAULT NULL,
  `facebookSn` varchar(75) DEFAULT NULL,
  `icqSn` varchar(75) DEFAULT NULL,
  `jabberSn` varchar(75) DEFAULT NULL,
  `msnSn` varchar(75) DEFAULT NULL,
  `mySpaceSn` varchar(75) DEFAULT NULL,
  `skypeSn` varchar(75) DEFAULT NULL,
  `twitterSn` varchar(75) DEFAULT NULL,
  `ymSn` varchar(75) DEFAULT NULL,
  `employeeStatusId` varchar(75) DEFAULT NULL,
  `employeeNumber` varchar(75) DEFAULT NULL,
  `jobTitle` varchar(100) DEFAULT NULL,
  `jobClass` varchar(75) DEFAULT NULL,
  `hoursOfOperation` varchar(75) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `emailAddress` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`contactId`),
  KEY `IX_66D496A3` (`companyId`),
  KEY `IX_B8C28C53` (`accountId`),
  KEY `IX_791914FA` (`classNameId`,`classPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_BalloonText` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(75) DEFAULT NULL,
  `textBeforeForm` longtext,
  `textAfterForm` longtext,
  `textBeforeShareButtons` longtext,
  `textAfterShareButtons` longtext,
  `acceptTosText` longtext,
  `emailTemplate` longtext,
  `emailSubjectTemplate` longtext,
  `twitterDescription` longtext,
  `twitterSubject` longtext,
  `facebookDescription` longtext,
  `facebookSubject` longtext,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_CE6BAAA5` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ImpactDefaultSeries` (
  `seriesId` bigint(20) NOT NULL,
  `name` varchar(75) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `focusAreaId` bigint(20) DEFAULT NULL,
  `visible` tinyint(4) DEFAULT NULL,
  `editable` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`seriesId`,`name`),
  KEY `IX_7343D8EE` (`focusAreaId`),
  KEY `IX_89A73E2D` (`focusAreaId`,`name`),
  KEY `IX_6E3A15E8` (`seriesId`),
  KEY `IX_D1CD440` (`seriesId`,`editable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalContestPhaseAttributeType` (
  `name` varchar(75) NOT NULL,
  `copyOnPromote` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_Proposal` (
  `proposalId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `currentVersion` int(11) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `visible` tinyint(4) DEFAULT NULL,
  `discussionId` bigint(20) DEFAULT NULL,
  `resultsDiscussionId` bigint(20) DEFAULT NULL,
  `judgeDiscussionId` bigint(20) DEFAULT NULL,
  `fellowDiscussionId` bigint(20) DEFAULT NULL,
  `advisorDiscussionId` bigint(20) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`proposalId`),
  KEY `IX_BBC99B8B` (`updatedDate`)
) ENGINE=InnoDB AUTO_INCREMENT=1333836 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalAttribute` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `proposalId` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `versionWhenCreated` int(11) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `additionalId` bigint(20) DEFAULT NULL,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` longtext,
  `realValue` double DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_8FF24CAD` (`proposalId`,`version`),
  KEY `IX_F4926C2` (`proposalId`,`version`,`name`,`additionalId`),
  KEY `IX_4941177` (`proposalId`,`version`,`versionWhenCreated`),
  KEY `IX_F612A28C` (`proposalId`,`version`,`versionWhenCreated`,`name`,`additionalId`)
  /*,
  FULLTEXT KEY `stringValue_ProposalAtribute` (`stringValue`)*/
) ENGINE=InnoDB AUTO_INCREMENT=387726 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalUnversionedAttribute` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `proposalId` bigint(20) DEFAULT NULL,
  `createAuthorId` bigint(20) DEFAULT NULL,
  `lastAuthorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `addtionalId` int(11) DEFAULT NULL,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` varchar(75) DEFAULT NULL,
  `realValue` double DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_2FC1B0ED` (`proposalId`),
  KEY `IX_417CDAEC` (`proposalId`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=802 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalSupporter` (
  `proposalId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`proposalId`,`userId`),
  KEY `IX_2AAA1DDB` (`proposalId`),
  KEY `IX_1DCA0834` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ModelOutputChartOrder` (
  `modelOutputChartOrderPK` bigint(20) NOT NULL,
  `modelId` bigint(20) DEFAULT NULL,
  `modelOutputLabel` varchar(1024) DEFAULT NULL,
  `modelOutputChartOrder` int(11) DEFAULT NULL,
  `modelIndexRangePolicy` varchar(2048) DEFAULT NULL,
  `modelIndexRangeMessage` varchar(2048) DEFAULT NULL,
  `modelIndexErrorPolicy` varchar(2048) DEFAULT NULL,
  `modelIndexErrorMessage` varchar(2048) DEFAULT NULL,
  `modelChartIsVisible` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`modelOutputChartOrderPK`),
  KEY `IX_6D3808C8` (`modelId`,`modelOutputLabel`(50))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_LoginLog` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `ipAddress` varchar(75) DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `country` varchar(75) DEFAULT NULL,
  `entryUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=146643 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_PlanSectionDefinition` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_` varchar(75) DEFAULT NULL,
  `adminTitle` varchar(1024) DEFAULT NULL,
  `title` varchar(1024) DEFAULT NULL,
  `defaultText` longtext,
  `helpText` longtext,
  `characterLimit` int(11) DEFAULT NULL,
  `focusAreaId` bigint(20) DEFAULT NULL,
  `tier` bigint(20) DEFAULT NULL,
  `allowedContestTypeIds` varchar(75) DEFAULT NULL,
  `allowedValues` varchar(75) DEFAULT NULL,
  `additionalIds` varchar(75) DEFAULT NULL,
  `locked` tinyint(4) DEFAULT NULL,
  `contestIntegrationRelevance` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=1303330 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ModelInputItem` (
  `modelInputItemPK` bigint(20) NOT NULL,
  `modelId` bigint(20) DEFAULT NULL,
  `modelInputItemID` bigint(20) DEFAULT NULL,
  `modelGroupId` bigint(20) DEFAULT NULL,
  `displayItemOrder` int(11) DEFAULT NULL,
  `type_` varchar(256) DEFAULT NULL,
  `properties` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`modelInputItemPK`),
  KEY `IX_39326F55` (`modelGroupId`),
  KEY `IX_13790D44` (`modelId`),
  KEY `IX_EF979667` (`modelId`,`modelInputItemID`),
  KEY `IX_CCEFE733` (`modelInputItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestEmailTemplate` (
  `type_` varchar(75) NOT NULL,
  `subject` longtext,
  `header` longtext,
  `footer` longtext,
  PRIMARY KEY (`type_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_RolesCategory` (
  `roleId` bigint(20) NOT NULL,
  `categoryName` varchar(75) DEFAULT NULL,
  `roleOrdinal` int(11) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ImpactTemplateMaxFocusArea` (
  `focusAreaListId` bigint(20) NOT NULL,
  `focusAreaId` bigint(20) NOT NULL,
  PRIMARY KEY (`focusAreaListId`,`focusAreaId`),
  KEY `IX_E0F07F11` (`focusAreaListId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_MessagingUserPreferences` (
  `messagingPreferencesId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `emailOnSend` tinyint(4) DEFAULT NULL,
  `emailOnReceipt` tinyint(4) DEFAULT NULL,
  `emailOnActivity` tinyint(4) DEFAULT NULL,
  `emailActivityDailyDigest` tinyint(4) DEFAULT NULL,
  `dailyMessageLimit` int(11) DEFAULT NULL,
  PRIMARY KEY (`messagingPreferencesId`),
  KEY `IX_F504493F` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1315290 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_MessageRecipientStatus` (
  `messageRecipientId` bigint(20) NOT NULL AUTO_INCREMENT,
  `messageId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `opened` tinyint(4) DEFAULT NULL,
  `archived` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`messageRecipientId`),
  KEY `IX_E4B60412` (`messageId`),
  KEY `IX_76FF2A4C` (`messageId`,`userId`),
  KEY `IX_74DCC2DA` (`userId`),
  KEY `IX_88CD5CB0` (`userId`,`archived`)
) ENGINE=InnoDB AUTO_INCREMENT=1401892 DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_AnalyticsUserEvent` (
  `userId` bigint(20) NOT NULL,
  `idString` varchar(75) NOT NULL,
  `category` varchar(75) DEFAULT NULL,
  `action` varchar(75) DEFAULT NULL,
  `label` varchar(75) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`,`idString`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ProposalRatingType` (
  `id_` bigint(20) NOT NULL,
  `label` varchar(75) DEFAULT NULL,
  `description` longtext,
  `judgeType` int(11) DEFAULT NULL,
  `isActive` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  KEY `comment_Comment__authorId` (`authorId`)/*,
  FULLTEXT KEY `content_comment_Comment` (`content`)*/
) ENGINE=InnoDB AUTO_INCREMENT=1363204 DEFAULT CHARSET=utf8;

CREATE TABLE `Users_Roles` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `IX_C19E5F31` (`roleId`),
  KEY `IX_C1A01806` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_Contest` (
  `ContestPK` bigint(20) NOT NULL,
  `contestTypeId` bigint(20) DEFAULT NULL,
  `ContestName` varchar(1024) DEFAULT NULL,
  `ContestShortName` varchar(512) DEFAULT NULL,
  `ContestUrlName` varchar(75) DEFAULT NULL,
  `ContestYear` bigint(20) DEFAULT NULL,
  `ContestDescription` longtext,
  `ContestModelDescription` longtext,
  `ContestPositionsDescription` longtext,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `contestActive` tinyint(4) DEFAULT NULL,
  `planTemplateId` bigint(20) DEFAULT NULL,
  `contestScheduleId` bigint(20) DEFAULT NULL,
  `proposalCreationTemplateString` varchar(75) DEFAULT NULL,
  `voteTemplateString` varchar(75) DEFAULT NULL,
  `proposalVoteTemplateString` varchar(75) DEFAULT NULL,
  `proposalVoteConfirmationTemplateString` varchar(75) DEFAULT NULL,
  `voteQuestionTemplateString` varchar(75) DEFAULT NULL,
  `focusAreaId` bigint(20) DEFAULT NULL,
  `contestTier` bigint(20) DEFAULT NULL,
  `contestLogoId` bigint(20) DEFAULT NULL,
  `featured_` tinyint(4) DEFAULT NULL,
  `plansOpenByDefault` tinyint(4) DEFAULT NULL,
  `sponsorLogoId` bigint(20) DEFAULT NULL,
  `sponsorText` varchar(500) DEFAULT NULL,
  `sponsorLink` varchar(75) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `flagText` varchar(256) DEFAULT NULL,
  `flagTooltip` varchar(512) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `discussionGroupId` bigint(20) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `resourcesUrl` varchar(1024) DEFAULT NULL,
  `contestPrivate` tinyint(4) DEFAULT NULL,
  `usePermissions` tinyint(4) DEFAULT NULL,
  `contestCreationStatus` varchar(75) DEFAULT NULL,
  `defaultModelId` bigint(20) DEFAULT NULL,
  `otherModels` varchar(75) DEFAULT NULL,
  `defaultModelSettings` varchar(75) DEFAULT NULL,
  `points` double DEFAULT NULL,
  `defaultParentPointType` bigint(20) DEFAULT NULL,
  `pointDistributionStrategy` varchar(75) DEFAULT NULL,
  `emailTemplateUrl` varchar(500) DEFAULT NULL,
  `show_in_tile_view` tinyint(4) DEFAULT NULL,
  `show_in_list_view` tinyint(4) DEFAULT NULL,
  `show_in_outline_view` tinyint(4) DEFAULT NULL,
  `hideRibbons` tinyint(4) DEFAULT NULL,
  `resourceArticleId` bigint(20) DEFAULT NULL,
  `isSharedContest` tinyint(4) DEFAULT '0',
  `sharedOrigin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ContestPK`),
  KEY `IX_3CD643E3` (`ContestUrlName`(50),`ContestYear`),
  KEY `IX_4E7AA29D` (`ContestYear`),
  KEY `IX_CEF1EFC6` (`contestActive`),
  KEY `IX_9AB21749` (`contestActive`,`contestPrivate`),
  KEY `IX_2DC0D430` (`contestActive`,`contestPrivate`,`contestTypeId`),
  KEY `IX_4B0F5213` (`contestActive`,`contestTypeId`),
  KEY `IX_D29429DB` (`contestActive`,`featured_`),
  KEY `IX_348F875E` (`contestActive`,`featured_`,`contestPrivate`),
  KEY `IX_504C977B` (`contestActive`,`featured_`,`contestPrivate`,`contestTypeId`),
  KEY `IX_DC690B5E` (`contestActive`,`featured_`,`contestTypeId`),
  KEY `IX_491DA3A6` (`contestActive`,`flag`),
  KEY `IX_B9BA0B29` (`contestActive`,`flag`,`contestPrivate`),
  KEY `IX_1516A450` (`contestActive`,`flag`,`contestPrivate`,`contestTypeId`),
  KEY `IX_33496233` (`contestActive`,`flag`,`contestTypeId`),
  KEY `IX_168D6722` (`contestTier`),
  KEY `IX_58A2B737` (`contestTier`,`contestTypeId`),
  KEY `IX_95122F5` (`contestTypeId`)/*,
  FULLTEXT KEY `ContestDescription_xcolab_Contest` (`ContestDescription`)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_PlanTemplateSection` (
  `planTemplateId` bigint(20) NOT NULL,
  `planSectionId` bigint(20) NOT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`planTemplateId`,`planSectionId`),
  KEY `IX_CAECF835` (`planSectionId`),
  KEY `IX_32F8E764` (`planTemplateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContestType` (
  `id_` bigint(20) NOT NULL,
  `contestName` varchar(75) DEFAULT NULL,
  `contestNamePlural` varchar(75) DEFAULT NULL,
  `proposalName` varchar(75) DEFAULT NULL,
  `proposalNamePlural` varchar(75) DEFAULT NULL,
  `portletName` varchar(75) DEFAULT NULL,
  `portletUrl` varchar(75) DEFAULT NULL,
  `friendlyUrlStringContests` varchar(75) DEFAULT NULL,
  `friendlyUrlStringProposal` varchar(75) DEFAULT NULL,
  `menuItemName` varchar(75) DEFAULT NULL,
  `hasDiscussion` tinyint(4) DEFAULT NULL,
  `suggestionContestId` bigint(20) DEFAULT NULL,
  `rulesPageName` varchar(75) DEFAULT NULL,
  `rulesPageUrl` varchar(75) DEFAULT NULL,
  `showProposalSummary` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ModelGlobalPreference` (
  `modelGlobalPreferencePK` bigint(20) NOT NULL,
  `modelId` bigint(20) DEFAULT NULL,
  `visible` tinyint(4) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `expertEvaluationPageId` bigint(20) DEFAULT NULL,
  `modelCategoryId` bigint(20) DEFAULT NULL,
  `usesCustomInputs` tinyint(4) DEFAULT NULL,
  `customInputsDefinition` longtext,
  PRIMARY KEY (`modelGlobalPreferencePK`),
  KEY `IX_16CBB25B` (`modelCategoryId`),
  KEY `IX_E9B5E03D` (`modelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ModelPosition` (
  `id_` bigint(20) NOT NULL,
  `positionId` bigint(20) DEFAULT NULL,
  `modelId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_E7C0C412` (`modelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_Points` (
  `id_` bigint(20) NOT NULL,
  `proposalId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `materializedPoints` double DEFAULT NULL,
  `hypotheticalPoints` double DEFAULT NULL,
  `pointsSourceId` bigint(20) DEFAULT NULL,
  `originatingContestPK` bigint(20) DEFAULT NULL,
  `originatingProposalId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `IX_AD696139` (`originatingContestPK`),
  KEY `IX_6EC5952C` (`pointsSourceId`),
  KEY `IX_5A8893C0` (`proposalId`),
  KEY `IX_AF313899` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xcolab_ContentFolder` (
  `contentFolderId` bigint(20) NOT NULL AUTO_INCREMENT,
  `contentFolderName` varchar(255) DEFAULT NULL,
  `contentFolderDescription` text,
  `parentFolderId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`contentFolderId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1