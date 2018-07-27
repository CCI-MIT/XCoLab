CREATE TABLE IF NOT EXISTS `xcolab_ProposalVote` (
  `proposalId` bigint(20) NOT NULL,
  `contestPhaseId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `value` INT DEFAULT '1' NULL,
  `createDate` datetime DEFAULT NULL,
  `voterIp` varchar(75) NULL,
  `voterUserAgent` varchar(500) NULL,
  `isValid` tinyint(4) DEFAULT NULL,
  `confirmationEmailSendDate` datetime DEFAULT NULL,
  `confirmationToken` varchar(75) DEFAULT NULL,
  `initialValidationResult` VARCHAR(25) NULL,
  `lastValidationResult` varchar(25) NULL,
  `isValidOverride` tinyint(4) NULL,
  `manualValidationResult` varchar(25) NULL,
  PRIMARY KEY (`proposalId`, `contestPhaseId`,`userId`),
  INDEX `IX_A4D26028` (`contestPhaseId`,`userId`),
  INDEX `IX_5E8D7ED3` (`proposalId`,`userId`),
  INDEX `IX_497348F2` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ContestTeamMember` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `contestId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  INDEX `IX_E1468F04` (`contestId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ContestTeamMemberRole` (
  `id_` bigint(20) NOT NULL PRIMARY KEY,
  `role` varchar(75) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_FocusArea` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(256) DEFAULT NULL,
  `order_` int(11) DEFAULT NULL,
  INDEX `IX_B61888D4` (`name`(50))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ContestDiscussion` (
  `DiscussionId` bigint(20) NOT NULL PRIMARY KEY,
  `ContestId` bigint(20) DEFAULT NULL,
  `Tab` varchar(75) DEFAULT NULL,
  INDEX `IX_DD06DA92` (`ContestId`,`Tab`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalMoveHistory` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `sourceProposalId` bigint(20) DEFAULT NULL,
  `sourceContestId` bigint(20) DEFAULT NULL,
  `sourcePhaseId` bigint(20) DEFAULT NULL,
  `targetProposalId` bigint(20) DEFAULT NULL,
  `targetContestId` bigint(20) DEFAULT NULL,
  `targetPhaseId` bigint(20) DEFAULT NULL,
  `movingUserId` bigint(20) DEFAULT NULL,
  `moveDate` datetime DEFAULT NULL,
  `moveType` varchar(75) DEFAULT NULL,
  INDEX `IX_101B90C3` (`sourceContestId`),
  INDEX `IX_A0D3722A` (`sourcePhaseId`),
  INDEX `IX_A98333DD` (`sourceProposalId`),
  INDEX `IX_E78B9567` (`sourceProposalId`,`sourceContestId`),
  INDEX `IX_9920218D` (`targetContestId`),
  INDEX `IX_FA79AD74` (`targetPhaseId`),
  INDEX `IX_4110BC53` (`targetProposalId`),
  INDEX `IX_6001D87B` (`targetProposalId`,`targetContestId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalContestPhaseAttribute` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `proposalId` bigint(20) DEFAULT NULL,
  `contestPhaseId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `additionalId` bigint(20) DEFAULT NULL,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` longtext,
  `realValue` double DEFAULT NULL,
  INDEX `IX_159C0FC9` (`contestPhaseId`),
  INDEX `IX_EAA7A52A` (`contestPhaseId`,`proposalId`),
  INDEX `IX_68DFE42A` (`proposalId`,`contestPhaseId`),
  INDEX `IX_8F351DBF` (`proposalId`,`contestPhaseId`,`name`,`additionalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ContestPhase` (
  `ContestPhasePK` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `ContestPK` bigint(20) DEFAULT NULL,
  `ContestPhaseType` bigint(20) DEFAULT NULL,
  `contestScheduleId` bigint(20) DEFAULT NULL,
  `contestPhaseAutopromote` varchar(75) DEFAULT NULL,
  `PhaseStartDate` datetime DEFAULT NULL,
  `PhaseEndDate` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  INDEX `IX_ED61C03C` (`ContestPK`),
  INDEX `IX_2BA2B787` (`ContestPK`,`PhaseStartDate`,`PhaseEndDate`),
  INDEX `IX_1BB9EC37` (`contestPhaseAutopromote`),
  INDEX `IX_D9B6142C` (`contestScheduleId`,`ContestPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ContestPhaseRibbonType` (
  `id_` bigint(20) NOT NULL PRIMARY KEY,
  `ribbon` int(11) DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  `hoverText` varchar(75) DEFAULT NULL,
  `showText` tinyint(4) NOT NULL,
  `description` varchar(75) DEFAULT NULL,
  `copyOnPromote` tinyint(4) DEFAULT NULL,
  `sortOrder` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ImpactTemplateFocusAreaList` (
  `focusAreaListId` bigint(20) NOT NULL PRIMARY KEY,
  `name` varchar(75) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_OntologySpace` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(256) DEFAULT NULL,
  `description` longtext,
  `order_` int(11) DEFAULT NULL,
  INDEX `IX_18FC4546` (`name`(50))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_FocusAreaOntologyTerm` (
  `focusAreaId` bigint(20) NOT NULL,
  `ontologyTermId` bigint(20) NOT NULL,
  `order_` int(11) DEFAULT NULL,
  PRIMARY KEY (`focusAreaId`,`ontologyTermId`),
  INDEX `IX_CE67B1A0` (`focusAreaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalReference` (
  `proposalId` bigint(20) NOT NULL,
  `subProposalId` bigint(20) NOT NULL,
  `sectionAttributeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`proposalId`,`subProposalId`),
  INDEX `IX_3C82622A` (`proposalId`),
  INDEX `IX_AABA9B94` (`subProposalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ImpactTemplateSeries` (
  `seriesId` bigint(20) NOT NULL PRIMARY KEY,
  `iterationId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalVersion` (
  `proposalId` bigint(20) NOT NULL,
  `version` int(11) NOT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateType` varchar(75) DEFAULT NULL,
  `updateAdditionalId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`proposalId`,`version`),
  INDEX `IX_59E3C2F7` (`proposalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_Points` (
  `id_` bigint(20) NOT NULL PRIMARY KEY,
  `proposalId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `materializedPoints` double DEFAULT NULL,
  `hypotheticalPoints` double DEFAULT NULL,
  `pointsSourceId` bigint(20) DEFAULT NULL,
  `originatingContestPK` bigint(20) DEFAULT NULL,
  `originatingProposalId` bigint(20) DEFAULT NULL,
  INDEX `IX_AD696139` (`originatingContestPK`),
  INDEX `IX_6EC5952C` (`pointsSourceId`),
  INDEX `IX_5A8893C0` (`proposalId`),
  INDEX `IX_AF313899` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_PointsDistributionConfiguration` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `proposalId` bigint(20) DEFAULT NULL,
  `pointTypeId` bigint(20) DEFAULT NULL,
  `targetUserId` bigint(20) DEFAULT NULL,
  `targetSubProposalId` bigint(20) DEFAULT NULL,
  `targetPlanSectionDefinitionId` bigint(20) DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  INDEX `IX_7113A2E0` (`proposalId`),
  INDEX `IX_1FDF3BB5` (`proposalId`,`pointTypeId`),
  INDEX `IX_27247AAA` (`targetPlanSectionDefinitionId`),
  INDEX `IX_7D0AD60D` (`targetSubProposalId`),
  INDEX `IX_D44477AA` (`targetUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `xcolab_ContestSchedule` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(75) DEFAULT NULL,
  `description` varchar(75) DEFAULT NULL,
  `status` varchar(75) DEFAULT NULL,
  `baseScheduleId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ContestCollectionCard` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `parent` bigint(20) DEFAULT NULL,
  `big_ontology_term` bigint(20) DEFAULT NULL,
  `small_ontology_term` bigint(20) DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `short_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `visible` tinyint(4) DEFAULT '1',
  `order` int(11) DEFAULT '0',
  `ontology_term_to_load` bigint(20) DEFAULT NULL,
  `only_featured` tinyint(4) DEFAULT '0',
  INDEX `big_ontology_term_idx` (`big_ontology_term`),
  INDEX `small_ontology_term_idx` (`small_ontology_term`),
  INDEX `ontology_term_to_load_idx` (`ontology_term_to_load`),
  INDEX `FK_parent` (`parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ImpactIteration` (
  `iterationId` bigint(20) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`iterationId`,`year`),
  INDEX `IX_512E56E1` (`iterationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalRating` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `proposalId` bigint(20) DEFAULT NULL,
  `contestPhaseId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `ratingValueId` bigint(20) DEFAULT NULL,
  `comment_` longtext,
  `commentEnabled` tinyint(4) DEFAULT NULL,
  `otherDataString` varchar(75) DEFAULT NULL,
  `onlyForInternalUsage` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ContestPhaseType` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(1024) DEFAULT NULL,
  `description` longtext,
  `status` varchar(75) DEFAULT NULL,
  `fellowScreeningActiveDefault` tinyint(4) DEFAULT NULL,
  `contestPhaseAutopromoteDefault` varchar(75) DEFAULT NULL,
  `invisible` tinyint(4) DEFAULT NULL,
  `pointsAccessible` int(11) DEFAULT NULL,
  `defaultPromotionType` varchar(75) DEFAULT NULL,
  `defaultFlagText` varchar(60) DEFAULT NULL,
  `isDeprecated` tinyint(4) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_OntologyTerm` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `parentId` bigint(20) DEFAULT NULL,
  `ontologySpaceId` bigint(20) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `descriptionUrl` varchar(1024) DEFAULT NULL,
  `order_` int(11) DEFAULT NULL,
  INDEX `IX_50DE58D6` (`name`(50)),
  INDEX `IX_3E2347AB` (`ontologySpaceId`),
  INDEX `IX_FEB70AB0` (`parentId`),
  INDEX `IX_A6AC072` (`parentId`,`ontologySpaceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ImpactDefaultSeriesData` (
  `seriesId` bigint(20) NOT NULL,
  `year` int(11) NOT NULL,
  `value` double DEFAULT NULL,
  PRIMARY KEY (`seriesId`,`year`),
  INDEX `IX_E8941CB2` (`seriesId`),
  INDEX `IX_97B70823` (`seriesId`,`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalRatingValue` (
  `id_` bigint(20) NOT NULL PRIMARY KEY,
  `ratingTypeId` bigint(20) DEFAULT NULL,
  `value` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `MembershipRequest` (
  `membershipRequestId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `comments` longtext,
  `replyComments` longtext,
  `replyDate` datetime DEFAULT NULL,
  `replierUserId` bigint(20) DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  INDEX `IX_8A1CC4B` (`groupId`),
  INDEX `IX_C28C72EC` (`groupId`,`statusId`),
  INDEX `IX_66D70879` (`userId`),
  INDEX `IX_35AA8FA6` (`groupId`,`userId`,`statusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_PlanTemplate` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(1024) DEFAULT NULL,
  `baseTemplateId` bigint(20) DEFAULT NULL,
  `impactSeriesTemplateId` bigint(20) DEFAULT NULL,
  `focusAreaListTemplateId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `xcolab_PointType` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `parentPointTypeId` bigint(20) DEFAULT NULL,
  `percentageOfParent` double DEFAULT NULL,
  `distributionStrategy` varchar(75) DEFAULT NULL,
  `receiverLimitationStrategy` varchar(75) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `sort` bigint(20) DEFAULT NULL,
  INDEX `IX_8DD75651` (`parentPointTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_Proposal2Phase` (
  `proposalId` bigint(20) NOT NULL,
  `contestPhaseId` bigint(20) NOT NULL,
  `versionFrom` int(11) DEFAULT NULL,
  `versionTo` int(11) DEFAULT NULL,
  `sortWeight` int(11) DEFAULT NULL,
  `autopromoteCandidate` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`proposalId`,`contestPhaseId`),
  INDEX `IX_DBA8038D` (`contestPhaseId`),
  INDEX `IX_D273A4B8` (`proposalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ImpactDefaultSeries` (
  `seriesId` bigint(20) NOT NULL,
  `name` varchar(75) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `focusAreaId` bigint(20) DEFAULT NULL,
  `visible` tinyint(4) DEFAULT NULL,
  `editable` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`seriesId`,`name`),
  INDEX `IX_7343D8EE` (`focusAreaId`),
  INDEX `IX_89A73E2D` (`focusAreaId`,`name`),
  INDEX `IX_6E3A15E8` (`seriesId`),
  INDEX `IX_D1CD440` (`seriesId`,`editable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalContestPhaseAttributeType` (
  `name` varchar(75) NOT NULL PRIMARY KEY,
  `copyOnPromote` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_Proposal` (
  `proposalId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `createDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `visible` tinyint(4) DEFAULT NULL,
  `discussionId` bigint(20) DEFAULT NULL,
  `resultsDiscussionId` bigint(20) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  INDEX `IX_BBC99B8B` (`updatedDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalAttribute` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `proposalId` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `additionalId` bigint(20) DEFAULT NULL,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` longtext,
  `realValue` double DEFAULT NULL,
  INDEX `IX_8FF24CAD` (`proposalId`,`version`),
  INDEX `IX_F4926C2` (`proposalId`,`version`,`name`,`additionalId`)
  /*,
  FULLTEXT INDEX `stringValue_ProposalAtribute` (`stringValue`)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalUnversionedAttribute` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `proposalId` bigint(20) DEFAULT NULL,
  `createAuthorId` bigint(20) DEFAULT NULL,
  `lastAuthorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `additionalId` bigint(20) DEFAULT NULL,
  `numericValue` bigint(20) DEFAULT NULL,
  `stringValue` varchar(75) DEFAULT NULL,
  `realValue` double DEFAULT NULL,
  INDEX `IX_2FC1B0ED` (`proposalId`),
  INDEX `IX_417CDAEC` (`proposalId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalSupporter` (
  `proposalId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`proposalId`,`userId`),
  INDEX `IX_2AAA1DDB` (`proposalId`),
  INDEX `IX_1DCA0834` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_PlanSectionDefinition` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `type_` varchar(75) DEFAULT NULL,
  `adminTitle` varchar(1024) DEFAULT NULL,
  `title` varchar(1024) DEFAULT NULL,
  `defaultText` longtext,
  `helpText` longtext,
  `characterLimit` int(11) DEFAULT NULL,
  `focusAreaId` bigint(20) DEFAULT NULL,
  `tier` bigint(20) DEFAULT NULL,
  `allowedContestTypeIds` varchar(75) DEFAULT NULL,
  `allowedValues` longtext,
  `additionalIds` varchar(75) DEFAULT NULL,
  `locked` tinyint(4) DEFAULT NULL,
  `contestIntegrationRelevance` tinyint(4) DEFAULT NULL,
  includeInJudgingReport TINYINT(4) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ImpactTemplateMaxFocusArea` (
  `focusAreaListId` bigint(20) NOT NULL,
  `focusAreaId` bigint(20) NOT NULL,
  PRIMARY KEY (`focusAreaListId`,`focusAreaId`),
  INDEX `IX_E0F07F11` (`focusAreaListId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ProposalRatingType` (
  `id_` bigint(20) NOT NULL PRIMARY KEY,
  `label` varchar(75) DEFAULT NULL,
  `description` longtext,
  `judgeType` int(11) DEFAULT NULL,
  `isActive` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_Contest` (
  `ContestPK` bigint(20) NOT NULL PRIMARY KEY,
  `contestTypeId` bigint(20) DEFAULT NULL,
  `ContestName` varchar(255) DEFAULT NULL,
  `ContestShortName` varchar(128) DEFAULT NULL,
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
  `defaultProposalLogoId` BIGINT(20) NULL DEFAULT NULL,
  `sponsorText` varchar(500) DEFAULT NULL,
  `sponsorLink` varchar(500) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `flagText` varchar(256) DEFAULT NULL,
  `flagTooltip` varchar(512) DEFAULT NULL,
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
  INDEX `IX_3CD643E3` (`ContestUrlName`(50),`ContestYear`),
  INDEX `IX_4E7AA29D` (`ContestYear`),
  INDEX `IX_CEF1EFC6` (`contestActive`),
  INDEX `IX_9AB21749` (`contestActive`,`contestPrivate`),
  INDEX `IX_2DC0D430` (`contestActive`,`contestPrivate`,`contestTypeId`),
  INDEX `IX_4B0F5213` (`contestActive`,`contestTypeId`),
  INDEX `IX_D29429DB` (`contestActive`,`featured_`),
  INDEX `IX_348F875E` (`contestActive`,`featured_`,`contestPrivate`),
  INDEX `IX_504C977B` (`contestActive`,`featured_`,`contestPrivate`,`contestTypeId`),
  INDEX `IX_DC690B5E` (`contestActive`,`featured_`,`contestTypeId`),
  INDEX `IX_491DA3A6` (`contestActive`,`flag`),
  INDEX `IX_B9BA0B29` (`contestActive`,`flag`,`contestPrivate`),
  INDEX `IX_1516A450` (`contestActive`,`flag`,`contestPrivate`,`contestTypeId`),
  INDEX `IX_33496233` (`contestActive`,`flag`,`contestTypeId`),
  INDEX `IX_168D6722` (`contestTier`),
  INDEX `IX_58A2B737` (`contestTier`,`contestTypeId`),
  INDEX `IX_95122F5` (`contestTypeId`)/*,
  FULLTEXT INDEX `ContestDescription_xcolab_Contest` (`ContestDescription`)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_ContestTranslation` (
	contestId bigint(20) not null,
	lang varchar(5) not null,
	contestName varchar(255) null,
	contestShortName varchar(128) null,
	contestDescription longtext null,
	createDate timestamp not null,
	modifiedDate timestamp not null,
	authorId bigint(20) null,
	primary key (contestId, lang)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_PlanTemplateSection` (
  `planTemplateId` bigint(20) NOT NULL,
  `planSectionId` bigint(20) NOT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`planTemplateId`,`planSectionId`),
  INDEX `IX_CAECF835` (`planSectionId`),
  INDEX `IX_32F8E764` (`planTemplateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- To be gutted:
CREATE TABLE IF NOT EXISTS `Group_` (
  `groupId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
  INDEX `IX_16218A38` (`liveGroupId`),
  INDEX `IX_7B590A7A` (`type_`,`active_`),
  INDEX `IX_ABA5CEC2` (`companyId`),
  INDEX `IX_B584B5CC` (`companyId`,`classNameId`),
  INDEX `IX_ABE2D54` (`companyId`,`classNameId`,`parentGroupId`),
  INDEX `IX_5D75499E` (`companyId`,`parentGroupId`),
  INDEX `IX_6C499099` (`companyId`,`parentGroupId`,`site`),
  INDEX `IX_63A2AABD` (`companyId`,`site`),
  INDEX `IX_F981514E` (`uuid_`),
  INDEX `IX_26CC761A` (`uuid_`,`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- To be removed with sharedcolab service

CREATE TABLE IF NOT EXISTS `sharedcolab_SharedContest` (
  `sharedContestId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `contestName` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `colabOrigin` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
