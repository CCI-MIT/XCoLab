create index IX_FD6093DD on xcolab_ActivitySubscription (classNameId, classPK);
create index IX_61FA63BB on xcolab_ActivitySubscription (classNameId, classPK, receiverId);
create index IX_20164EB2 on xcolab_ActivitySubscription (classNameId, classPK, type_, extraData);
create index IX_C2ED8710 on xcolab_ActivitySubscription (classNameId, classPK, type_, extraData, receiverId);
create index IX_1413A2B6 on xcolab_ActivitySubscription (classNameId, classPK, type_, receiverId);
create index IX_33049EE6 on xcolab_ActivitySubscription (receiverId);

create index IX_1AD9FFEC on xcolab_BalloonLink (balloonUserUuid);

create index IX_CE6BAAA5 on xcolab_BalloonText (enabled);

create index IX_AFDD82EB on xcolab_BalloonUserTracking (email);

create index IX_5192E6B6 on xcolab_Contest (PlanTypeId);
create index IX_CEF1EFC6 on xcolab_Contest (contestActive);
create index IX_9AB21749 on xcolab_Contest (contestActive, contestPrivate);
create index IX_D29429DB on xcolab_Contest (contestActive, featured_);
create index IX_348F875E on xcolab_Contest (contestActive, featured_, contestPrivate);
create index IX_963C1320 on xcolab_Contest (contestActive, featured_, privateContest);
create index IX_491DA3A6 on xcolab_Contest (contestActive, flag);
create index IX_B9BA0B29 on xcolab_Contest (contestActive, flag, contestPrivate);
create index IX_1B6696EB on xcolab_Contest (contestActive, flag, privateContest);
create index IX_C09FE3B3 on xcolab_Contest (contestActive, flagText);
create index IX_7D5C9136 on xcolab_Contest (contestActive, flagText, contestPrivate);
create index IX_DF091CF8 on xcolab_Contest (contestActive, flagText, privateContest);
create index IX_FC5EA30B on xcolab_Contest (contestActive, privateContest);
create index IX_168D6722 on xcolab_Contest (contestTier);

create index IX_379DF74A on xcolab_ContestDebate (ContestPK);

create index IX_DD06DA92 on xcolab_ContestDiscussion (ContestId, Tab);

create index IX_4DF8F0B9 on xcolab_ContestDiscussions (ContestId, Tab);

create index IX_ED61C03C on xcolab_ContestPhase (ContestPK);
create index IX_2BA2B787 on xcolab_ContestPhase (ContestPK, PhaseStartDate, PhaseEndDate);
create index IX_19E93261 on xcolab_ContestPhase (ContestPK, invisible);
create index IX_9F1D3B81 on xcolab_ContestPhase (ContestPK, phaseActiveOverride);
create index IX_4F735B66 on xcolab_ContestPhase (ContestPK, phaseInactiveOverride);
create index IX_1BB9EC37 on xcolab_ContestPhase (contestPhaseAutopromote);
create index IX_D9B6142C on xcolab_ContestPhase (contestScheduleId, ContestPK);

create index IX_D97B920F on xcolab_ContestPhaseColumn (ContestPhasePK);

create index IX_E1468F04 on xcolab_ContestTeamMember (contestId);

create index IX_8526458A on xcolab_DiscussionCategory (categoryGroupId);
create index IX_306B69EF on xcolab_DiscussionCategory (categoryId);

create index IX_EB07D049 on xcolab_DiscussionMessage (authorId);
create index IX_23E0BE87 on xcolab_DiscussionMessage (body, categoryGroupId);
create index IX_39430975 on xcolab_DiscussionMessage (categoryId, threadId);
create index IX_189EA1C3 on xcolab_DiscussionMessage (messageId);
create index IX_1A115E3F on xcolab_DiscussionMessage (subject, categoryGroupId);
create index IX_FDE36548 on xcolab_DiscussionMessage (threadId);

create index IX_63652E37 on xcolab_DiscussionMessageFlag (messageId);
create unique index IX_BEA683B1 on xcolab_DiscussionMessageFlag (messageId, flagType);

create index IX_4849B8A9 on xcolab_EmailList (name);
create index IX_55E95283 on xcolab_EmailList (name, email);

create index IX_B61888D4 on xcolab_FocusArea (name);

create index IX_CE67B1A0 on xcolab_FocusAreaOntologyTerm (focusAreaId);

create index IX_9DF5C6F0 on xcolab_Message (fromId);

create index IX_E4B60412 on xcolab_MessageRecipientStatus (messageId);
create index IX_76FF2A4C on xcolab_MessageRecipientStatus (messageId, userId);
create index IX_74DCC2DA on xcolab_MessageRecipientStatus (userId);
create index IX_88CD5CB0 on xcolab_MessageRecipientStatus (userId, archived);

create index IX_2073B48 on xcolab_MessagingIgnoredRecipients (email);
create index IX_19B87BE on xcolab_MessagingIgnoredRecipients (userId);

create index IX_F1E7F5C on xcolab_MessagingMessageConversion (messageId, conversionTypeId);

create index IX_15CF71AE on xcolab_MessagingMessageConversionType (name);

create index IX_F504493F on xcolab_MessagingUserPreferences (userId);

create index IX_C4F6226E on xcolab_ModelDiscussion (categoryId);
create index IX_72D6F073 on xcolab_ModelDiscussion (modelId);

create index IX_16CBB25B on xcolab_ModelGlobalPreference (modelCategoryId);
create index IX_E9B5E03D on xcolab_ModelGlobalPreference (modelId);

create index IX_23506DA6 on xcolab_ModelInputGroup (modelId);
create index IX_CC01932 on xcolab_ModelInputGroup (parentGroupPK);

create index IX_39326F55 on xcolab_ModelInputItem (modelGroupId);
create index IX_13790D44 on xcolab_ModelInputItem (modelId);
create index IX_EF979667 on xcolab_ModelInputItem (modelId, modelInputItemID);
create index IX_CCEFE733 on xcolab_ModelInputItem (modelInputItemID);

create index IX_6D3808C8 on xcolab_ModelOutputChartOrder (modelId, modelOutputLabel);

create index IX_A17AABB on xcolab_ModelOutputItem (modelOutputItemId);

create index IX_E7C0C412 on xcolab_ModelPosition (modelId);

create index IX_18FC4546 on xcolab_OntologySpace (name);

create index IX_50DE58D6 on xcolab_OntologyTerm (name);
create index IX_3E2347AB on xcolab_OntologyTerm (ontologySpaceId);
create index IX_FEB70AB0 on xcolab_OntologyTerm (parentId);
create index IX_A6AC072 on xcolab_OntologyTerm (parentId, ontologySpaceId);

create index IX_4AE95AB4 on xcolab_OntologyTermEntity (classNameId);
create index IX_7AF843AF on xcolab_OntologyTermEntity (classNameId, classPK);
create index IX_EF334F35 on xcolab_OntologyTermEntity (termId);
create index IX_BA88C839 on xcolab_OntologyTermEntity (termId, classNameId);

create index IX_84EB676C on xcolab_Plan2Proposal (proposalId);

create index IX_FAAC13FD on xcolab_PlanAttribute (attributeName, attributeValue);
create index IX_EF85A9DB on xcolab_PlanAttribute (planId);
create index IX_9A8772FC on xcolab_PlanAttribute (planId, attributeName);

create index IX_F9CFF936 on xcolab_PlanAttributeFilter (planUserSettingsId, attributeName);

create index IX_E94D3159 on xcolab_PlanColumnSettings (planUserSettingsId, columnName);

create index IX_92C3D6FB on xcolab_PlanDescription (planId);
create index IX_7658F2C4 on xcolab_PlanDescription (planId, planVersion);

create index IX_9FCD5E52 on xcolab_PlanFan (planId);
create index IX_4B38448C on xcolab_PlanFan (planId, userId);
create index IX_208A9DF4 on xcolab_PlanFan (userId);

create index IX_B269B130 on xcolab_PlanItem (planId);

create index IX_F22CBD37 on xcolab_PlanItemGroup (groupId);

create index IX_B8E3C5A2 on xcolab_PlanMeta (planId);

create index IX_B093827F on xcolab_PlanModelRun (planId);
create index IX_1CF31DC0 on xcolab_PlanModelRun (planId, planVersion);

create index IX_D1556A46 on xcolab_PlanPosition (positionId);

create index IX_B1AEB06B on xcolab_PlanPositionItem (planPositionsId);

create index IX_4FA12229 on xcolab_PlanPositions (planId);

create index IX_EC8EC92C on xcolab_PlanPropertyFilter (planUserSettingsId, propertyName);

create index IX_EBB4128D on xcolab_PlanRelated (relatedPlanId);

create index IX_8D33E176 on xcolab_PlanSection (planId, planSectionDefinitionId);
create index IX_21590829 on xcolab_PlanSection (planId, planSectionDefinitionId, planVersion);

create index IX_9411AFEC on xcolab_PlanSectionPlanMap (relatedPlanId);
create index IX_C8499DDD on xcolab_PlanSectionPlanMap (sectionId);

create index IX_79B9BCB6 on xcolab_PlanTeamHistory (planId);
create index IX_84A1FEF0 on xcolab_PlanTeamHistory (planId, userId);

create index IX_32F8E764 on xcolab_PlanTemplateSection (planTemplateId);

create index IX_EC7AD748 on xcolab_PlanType (isDefault);

create index IX_F5C3FCBC on xcolab_PlanTypeAttribute (planTypeId, attributeName);

create index IX_2A263730 on xcolab_PlanVote (contestId);
create index IX_4E915F6A on xcolab_PlanVote (contestId, userId);
create index IX_4E60E847 on xcolab_PlanVote (planId);

create index IX_C1242E47 on xcolab_PlansFilterPosition (userId, planTypeId);

create index IX_95AABD4 on xcolab_PlansUserSettings (userId, planTypeId);

create index IX_4A67416A on xcolab_PointDistributionTarget (contestId);
create index IX_B8AD9816 on xcolab_PointDistributionTarget (proposalId);

create index IX_8DD75651 on xcolab_PointType (parentPointTypeId);

create index IX_AD696139 on xcolab_Points (originatingContestPK);
create index IX_6EC5952C on xcolab_Points (pointsSourceId);
create index IX_5A8893C0 on xcolab_Points (proposalId);
create index IX_AF313899 on xcolab_Points (userId);

create index IX_7113A2E0 on xcolab_PointsDistributionConfiguration (proposalId);
create index IX_1FDF3BB5 on xcolab_PointsDistributionConfiguration (proposalId, pointTypeId);
create index IX_7D0AD60D on xcolab_PointsDistributionConfiguration (targetSubProposalId);
create index IX_D44477AA on xcolab_PointsDistributionConfiguration (targetUserId);

create index IX_BBC99B8B on xcolab_Proposal (updatedDate);

create index IX_DBA8038D on xcolab_Proposal2Phase (contestPhaseId);
create index IX_D273A4B8 on xcolab_Proposal2Phase (proposalId);

create index IX_8FF24CAD on xcolab_ProposalAttribute (proposalId, version);
create unique index IX_F4926C2 on xcolab_ProposalAttribute (proposalId, version, name, additionalId);
create index IX_4941177 on xcolab_ProposalAttribute (proposalId, version, versionWhenCreated);
create index IX_F612A28C on xcolab_ProposalAttribute (proposalId, version, versionWhenCreated, name, additionalId);

create index IX_159C0FC9 on xcolab_ProposalContestPhaseAttribute (contestPhaseId);
create index IX_68DFE42A on xcolab_ProposalContestPhaseAttribute (proposalId, contestPhaseId);
create index IX_8F351DBF on xcolab_ProposalContestPhaseAttribute (proposalId, contestPhaseId, name, additionalId);

create index IX_2AAA1DDB on xcolab_ProposalSupporter (proposalId);
create index IX_1DCA0834 on xcolab_ProposalSupporter (userId);

create index IX_59E3C2F7 on xcolab_ProposalVersion (proposalId);

create index IX_A4D26028 on xcolab_ProposalVote (contestPhaseId, userId);
create index IX_EA28CF99 on xcolab_ProposalVote (proposalId);
create index IX_43559ACF on xcolab_ProposalVote (proposalId, contestPhaseId);
create index IX_562EB409 on xcolab_ProposalVote (proposalId, contestPhaseId, userId);
create index IX_497348F2 on xcolab_ProposalVote (userId);

create index IX_9C5CE364 on xcolab_StaffMember (userId);

create index IX_21569857 on xcolab_TrackedVisit (uuid_);