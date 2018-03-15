INSERT INTO `xcolab_Contest` (`ContestPK`,`contestTypeId`,`ContestName`,`ContestShortName`,`ContestUrlName`,`ContestYear`,`ContestDescription`,`ContestModelDescription`,`ContestPositionsDescription`,`created`,`updated`,`authorId`,`contestActive`,`planTemplateId`,`contestScheduleId`,`proposalCreationTemplateString`,`voteTemplateString`,`proposalVoteTemplateString`,`proposalVoteConfirmationTemplateString`,`voteQuestionTemplateString`,`focusAreaId`,`contestTier`,`contestLogoId`,`featured_`,`plansOpenByDefault`,`sponsorLogoId`,`defaultProposalLogoId`,`sponsorText`,`sponsorLink`,`flag`,`flagText`,`flagTooltip`,`groupId`,`discussionGroupId`,`weight`,`resourcesUrl`,`contestPrivate`,`usePermissions`,`contestCreationStatus`,`defaultModelId`,`otherModels`,`defaultModelSettings`,`points`,`defaultParentPointType`,`pointDistributionStrategy`,`emailTemplateUrl`,`show_in_tile_view`,`show_in_list_view`,`show_in_outline_view`,`hideRibbons`,`resourceArticleId`,`isSharedContest`,`sharedOrigin`) VALUES (1304501,0,'How can vulnerable communities best prepare for climate-related hazards, and what new tools can be used to incentivize early action?','Anticipating Climate Hazards 2017','A2R-Anticipating-Climate-Hazards',2017,'<p>\r\n	In collaboration with the <a href=\"http://www.a2rinitiative.org/\">UN Secretary-General&rsquo;s Climate Resilience Initiative: Anticipate, Absorb, Reshape</a> (A2R), this contest invites solutions on how the world should prepare and respond to climate extremes and climate hazards. A2R&nbsp;is a global, multi-stakeholder initiative, launched in November 2015 to accelerate action on the ground to enhance climate resilience for the most vulnerable countries and people by 2020.&nbsp;<br />\r\n	&nbsp;</p>\r\n<p>\r\n	Building resilience and capacity to understand and effectively respond to climate extremes and climate-induced disasters is necessary to adapt to climate change. A first step is to increase the ability to adequately anticipate and respond to climate hazards and extremes. There are many actors working on building climate resilience, including national governments, UN agencies and other international organizations, local authorities, researchers, the private sector,&nbsp;and civil society, among others. Many of these actions must be scaled-up, reinforcing synergies and reducing overlaps, while ensuring that together, these different actions are having the positive impacts needed.&nbsp;</p>\r\n<p>\r\n	We are calling for a wide range of innovative and practical solutions &ndash; on the local, national and international levels &ndash; that can help strengthen early action in vulnerable communities around the world.</p>\r\n','','',NULL,'2017-05-10 14:16:17',10144,1,1301106,2006,'','','','','',1313201,1,2513099,1,0,2513113,NULL,'','',2,'Finalist selection','',2469955,1364375,5,'',0,0,'',0,'','',0,0,'','/resources/-/wiki/Main/Judging+Mail+Templates',1,1,1,1,1584,0,'Climate CoLab');

INSERT INTO `xcolab_ContestPhase` (`ContestPhasePK`,`ContestPK`,`ContestPhaseType`,`contestScheduleId`,`fellowScreeningActive`,`contestPhaseAutopromote`,`ContestPhaseDescriptionOverride`,`phaseActiveOverride`,`phaseInactiveOverride`,`PhaseStartDate`,`PhaseEndDate`,`PhaseBufferEndDated`,`nextStatus`,`created`,`updated`,`authorId`) VALUES (1318611,1304501,1,2006,0,'PROMOTE_DONE',NULL,NULL,NULL,'2016-09-22 12:00:00','2017-02-10 18:00:00',NULL,NULL,'2016-09-22 09:49:14','2017-02-10 18:00:10',NULL);
INSERT INTO `xcolab_ContestPhase` (`ContestPhasePK`,`ContestPK`,`ContestPhaseType`,`contestScheduleId`,`fellowScreeningActive`,`contestPhaseAutopromote`,`ContestPhaseDescriptionOverride`,`phaseActiveOverride`,`phaseInactiveOverride`,`PhaseStartDate`,`PhaseEndDate`,`PhaseBufferEndDated`,`nextStatus`,`created`,`updated`,`authorId`) VALUES (1318612,1304501,16,2006,1,'PROMOTE_DONE',NULL,NULL,NULL,'2017-02-10 18:01:00','2017-03-28 13:00:00',NULL,NULL,'2016-09-22 09:49:14','2017-03-28 13:00:09',NULL);
INSERT INTO `xcolab_ContestPhase` (`ContestPhasePK`,`ContestPK`,`ContestPhaseType`,`contestScheduleId`,`fellowScreeningActive`,`contestPhaseAutopromote`,`ContestPhaseDescriptionOverride`,`phaseActiveOverride`,`phaseInactiveOverride`,`PhaseStartDate`,`PhaseEndDate`,`PhaseBufferEndDated`,`nextStatus`,`created`,`updated`,`authorId`) VALUES (1318613,1304501,18,2006,0,'PROMOTE_DONE',NULL,NULL,NULL,'2017-03-28 13:01:00','2017-04-18 18:00:00',NULL,NULL,'2016-09-22 09:49:14','2017-04-18 18:00:06',NULL);
INSERT INTO `xcolab_ContestPhase` (`ContestPhasePK`,`ContestPK`,`ContestPhaseType`,`contestScheduleId`,`fellowScreeningActive`,`contestPhaseAutopromote`,`ContestPhaseDescriptionOverride`,`phaseActiveOverride`,`phaseInactiveOverride`,`PhaseStartDate`,`PhaseEndDate`,`PhaseBufferEndDated`,`nextStatus`,`created`,`updated`,`authorId`) VALUES (1318614,1304501,19,2006,1,'PROMOTE_JUDGED',NULL,NULL,NULL,'2017-04-18 18:01:00','2017-06-01 12:00:00',NULL,NULL,'2016-09-22 09:49:14','2016-09-22 09:49:14',NULL);

INSERT INTO `xcolab_Proposal2Phase` (`proposalId`,`contestPhaseId`,`versionFrom`,`versionTo`,`sortWeight`,`autopromoteCandidate`) VALUES (1333850,1318611,1,13,NULL,NULL);
INSERT INTO `xcolab_Proposal2Phase` (`proposalId`,`contestPhaseId`,`versionFrom`,`versionTo`,`sortWeight`,`autopromoteCandidate`) VALUES (1333850,1318612,13,13,NULL,NULL);
INSERT INTO `xcolab_Proposal2Phase` (`proposalId`,`contestPhaseId`,`versionFrom`,`versionTo`,`sortWeight`,`autopromoteCandidate`) VALUES (1333850,1318613,13,53,NULL,NULL);
INSERT INTO `xcolab_Proposal2Phase` (`proposalId`,`contestPhaseId`,`versionFrom`,`versionTo`,`sortWeight`,`autopromoteCandidate`) VALUES (1333850,1318614,13,53,NULL,NULL);

INSERT INTO `xcolab_Proposal` (`proposalId`,`createDate`,`updatedDate`,`currentVersion`,`authorId`,`visible`,`discussionId`,`resultsDiscussionId`,`groupId`) VALUES (1333850,'2017-02-10 17:44:06','2017-04-18 17:40:20',54,2651783,1,1366064,1366065,2854024);


INSERT INTO `xcolab_Proposal` (`proposalId`,`createDate`,`updatedDate`,`currentVersion`,`authorId`,`visible`,`discussionId`,`resultsDiscussionId`,`groupId`) VALUES (1333851,'2017-02-10 17:44:06','2017-04-18 17:40:20',54,2651783,1,1366064,1366065,2854024);
INSERT INTO `xcolab_Proposal2Phase` (`proposalId`,`contestPhaseId`,`versionFrom`,`versionTo`,`sortWeight`,`autopromoteCandidate`) VALUES (1333851,1318611,1,13,NULL,NULL);
INSERT INTO `xcolab_Proposal2Phase` (`proposalId`,`contestPhaseId`,`versionFrom`,`versionTo`,`sortWeight`,`autopromoteCandidate`) VALUES (1333851,1318612,13,13,NULL,NULL);
INSERT INTO `xcolab_Proposal2Phase` (`proposalId`,`contestPhaseId`,`versionFrom`,`versionTo`,`sortWeight`,`autopromoteCandidate`) VALUES (1333851,1318613,13,53,NULL,NULL);



INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50351,1333850,1318611,'PROMOTE_DONE',0,NULL,'true',NULL);
INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50452,1333850,1318612,'SELECTED_JUDGES',0,0,'1354233;1981759',0);
INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50573,1333850,1318612,'FELLOW_ACTION',0,3,NULL,0);
INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50625,1333850,1318612,'FELLOW_ACTION_COMMENT',0,0,'Your proposal is well thought out and articulated however it is not relevant to the contest theme, we would encourage you to resubmit on the contest on the Reshape pillar',0);
INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50768,1333850,1318612,'JUDGE_DECISION',0,2,NULL,0);
INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50769,1333850,1318612,'PROPOSAL_REVIEW',0,0,'The proposal would ideally be better focused on how the proposed actions can help communities anticipate climate hazards, and early warning and action responses.',0);
INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50911,1333850,1318612,'PROMOTE_DONE',0,NULL,'true',NULL);
INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50912,1333850,1318612,'PROMOTE_DONE',0,0,'true',0);
INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50964,1333850,1318613,'PROMOTE_DONE',0,NULL,'true',NULL);
-- INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50965,1333850,1318614,'FELLOW_ACTION',0,4,NULL,0);
-- INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (50984,1333850,1318614,'SELECTED_JUDGES',0,0,'',0);
-- INSERT INTO `xcolab_ProposalContestPhaseAttribute` (`id_`,`proposalId`,`contestPhaseId`,`name`,`additionalId`,`numericValue`,`stringValue`,`realValue`) VALUES (51003,1333850,1318614,'FELLOW_ACTION_COMMENT',0,0,'No improvements have been made to the proposal since the semifinalist round, the judges comments were also not addressed',0);
