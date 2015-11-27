create index IX_61FA63BB on xcolab_ActivitySubscription (classNameId, classPK, receiverId);
create index IX_C2ED8710 on xcolab_ActivitySubscription (classNameId, classPK, type_, extraData(50), receiverId);
create index IX_1413A2B6 on xcolab_ActivitySubscription (classNameId, classPK, type_, receiverId);
create index IX_33049EE6 on xcolab_ActivitySubscription (receiverId);

create index IX_1AD9FFEC on xcolab_BalloonLink (balloonUserUuid);

create index IX_CE6BAAA5 on xcolab_BalloonText (enabled);

create index IX_AFDD82EB on xcolab_BalloonUserTracking (email(50));

create index IX_CEF1EFC6 on xcolab_Contest (contestActive);
create index IX_9AB21749 on xcolab_Contest (contestActive, contestPrivate);
create index IX_2DC0D430 on xcolab_Contest (contestActive, contestPrivate, contestTypeId);
create index IX_4B0F5213 on xcolab_Contest (contestActive, contestTypeId);
create index IX_D29429DB on xcolab_Contest (contestActive, featured_);
create index IX_348F875E on xcolab_Contest (contestActive, featured_, contestPrivate);
create index IX_504C977B on xcolab_Contest (contestActive, featured_, contestPrivate, contestTypeId);
create index IX_DC690B5E on xcolab_Contest (contestActive, featured_, contestTypeId);
create index IX_491DA3A6 on xcolab_Contest (contestActive, flag);
create index IX_B9BA0B29 on xcolab_Contest (contestActive, flag, contestPrivate);
create index IX_1516A450 on xcolab_Contest (contestActive, flag, contestPrivate, contestTypeId);
create index IX_33496233 on xcolab_Contest (contestActive, flag, contestTypeId);
create index IX_168D6722 on xcolab_Contest (contestTier);
create index IX_58A2B737 on xcolab_Contest (contestTier, contestTypeId);
create index IX_95122F5 on xcolab_Contest (contestTypeId);

create index IX_379DF74A on xcolab_ContestDebate (ContestPK);

create index IX_DD06DA92 on xcolab_ContestDiscussion (ContestId, Tab);

create index IX_ED61C03C on xcolab_ContestPhase (ContestPK);
create index IX_2BA2B787 on xcolab_ContestPhase (ContestPK, PhaseStartDate, PhaseEndDate);
create index IX_9F1D3B81 on xcolab_ContestPhase (ContestPK, phaseActiveOverride);
create index IX_4F735B66 on xcolab_ContestPhase (ContestPK, phaseInactiveOverride);
create index IX_1BB9EC37 on xcolab_ContestPhase (contestPhaseAutopromote);
create index IX_D9B6142C on xcolab_ContestPhase (contestScheduleId, ContestPK);

create index IX_D97B920F on xcolab_ContestPhaseColumn (ContestPhasePK);

create index IX_E1468F04 on xcolab_ContestTeamMember (contestId);

create index IX_8526458A on xcolab_DiscussionCategory (categoryGroupId);
create index IX_306B69EF on xcolab_DiscussionCategory (categoryId);

create index IX_EB07D049 on xcolab_DiscussionMessage (authorId);
create index IX_XCOLAB_DM_CATEGORY_GROUP_ID on xcolab_DiscussionMessage (categoryGroupId);
create index IX_XCOLAB_DM_CATEGORY_GROUP_ID_DELETED on xcolab_DiscussionMessage (categoryGroupId, deleted);
create index IX_39430975 on xcolab_DiscussionMessage (categoryId, threadId);
create index IX_189EA1C3 on xcolab_DiscussionMessage (messageId);
create index IX_FDE36548 on xcolab_DiscussionMessage (threadId);

create index IX_63652E37 on xcolab_DiscussionMessageFlag (messageId);
create unique index IX_BEA683B1 on xcolab_DiscussionMessageFlag (messageId, flagType);

create index IX_55E95283 on xcolab_EmailList (name(50), email(50));

create index IX_B61888D4 on xcolab_FocusArea (name(50));

create index IX_CE67B1A0 on xcolab_FocusAreaOntologyTerm (focusAreaId);

create index IX_7343D8EE on xcolab_ImpactDefaultSeries (focusAreaId);
create index IX_89A73E2D on xcolab_ImpactDefaultSeries (focusAreaId, name);
create index IX_6E3A15E8 on xcolab_ImpactDefaultSeries (seriesId);
create index IX_D1CD440 on xcolab_ImpactDefaultSeries (seriesId, editable);

create index IX_E8941CB2 on xcolab_ImpactDefaultSeriesData (seriesId);
create index IX_97B70823 on xcolab_ImpactDefaultSeriesData (seriesId, year);

create index IX_512E56E1 on xcolab_ImpactIteration (iterationId);

create index IX_E0F07F11 on xcolab_ImpactTemplateMaxFocusArea (focusAreaListId);

create index IX_B3858EE9 on xcolab_MemberCategory (displayName);
create index IX_8336AE28 on xcolab_MemberCategory (showInList);

create index IX_9DF5C6F0 on xcolab_Message (fromId);

create index IX_E4B60412 on xcolab_MessageRecipientStatus (messageId);
create index IX_76FF2A4C on xcolab_MessageRecipientStatus (messageId, userId);
create index IX_74DCC2DA on xcolab_MessageRecipientStatus (userId);
create index IX_88CD5CB0 on xcolab_MessageRecipientStatus (userId, archived);

create index IX_2073B48 on xcolab_MessagingIgnoredRecipients (email(50));
create index IX_19B87BE on xcolab_MessagingIgnoredRecipients (userId);

create index IX_F1E7F5C on xcolab_MessagingMessageConversion (messageId, conversionTypeId);

create index IX_15CF71AE on xcolab_MessagingMessageConversionType (name(50));

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

create index IX_6D3808C8 on xcolab_ModelOutputChartOrder (modelId, modelOutputLabel(50));

create index IX_A17AABB on xcolab_ModelOutputItem (modelOutputItemId);

create index IX_E7C0C412 on xcolab_ModelPosition (modelId);

create index IX_18FC4546 on xcolab_OntologySpace (name(50));

create index IX_50DE58D6 on xcolab_OntologyTerm (name(50));
create index IX_3E2347AB on xcolab_OntologyTerm (ontologySpaceId);
create index IX_FEB70AB0 on xcolab_OntologyTerm (parentId);
create index IX_A6AC072 on xcolab_OntologyTerm (parentId, ontologySpaceId);

create index IX_4AE95AB4 on xcolab_OntologyTermEntity (classNameId);
create index IX_7AF843AF on xcolab_OntologyTermEntity (classNameId, classPK);
create index IX_EF334F35 on xcolab_OntologyTermEntity (termId);
create index IX_BA88C839 on xcolab_OntologyTermEntity (termId, classNameId);

create index IX_CAECF835 on xcolab_PlanTemplateSection (planSectionId);
create index IX_32F8E764 on xcolab_PlanTemplateSection (planTemplateId);

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
create index IX_F4926C2 on xcolab_ProposalAttribute (proposalId, version, name, additionalId);
create index IX_4941177 on xcolab_ProposalAttribute (proposalId, version, versionWhenCreated);
create index IX_F612A28C on xcolab_ProposalAttribute (proposalId, version, versionWhenCreated, name, additionalId);

create index IX_159C0FC9 on xcolab_ProposalContestPhaseAttribute (contestPhaseId);
create index IX_EAA7A52A on xcolab_ProposalContestPhaseAttribute (contestPhaseId, proposalId);
create index IX_68DFE42A on xcolab_ProposalContestPhaseAttribute (proposalId, contestPhaseId);
create index IX_8F351DBF on xcolab_ProposalContestPhaseAttribute (proposalId, contestPhaseId, name, additionalId);

create index IX_3C82622A on xcolab_ProposalReference (proposalId);
create index IX_AABA9B94 on xcolab_ProposalReference (subProposalId);

create index IX_2AAA1DDB on xcolab_ProposalSupporter (proposalId);
create index IX_1DCA0834 on xcolab_ProposalSupporter (userId);

create index IX_59E3C2F7 on xcolab_ProposalVersion (proposalId);

create index IX_A4D26028 on xcolab_ProposalVote (contestPhaseId, userId);
create index IX_EA28CF99 on xcolab_ProposalVote (proposalId);
create index IX_43559ACF on xcolab_ProposalVote (proposalId, contestPhaseId);
create index IX_562EB409 on xcolab_ProposalVote (proposalId, contestPhaseId, userId);
create index IX_5E8D7ED3 on xcolab_ProposalVote (proposalId, userId);
create index IX_497348F2 on xcolab_ProposalVote (userId);

create index IX_A9DC76B1 on xcolab_SpamReport (discussionMessageId);
create index IX_B9542B14 on xcolab_SpamReport (reporterUserId);
create index IX_3BB6FD56 on xcolab_SpamReport (reporterUserId, discussionMessageId);
create index IX_C4701AFC on xcolab_SpamReport (spamUserId);
create index IX_81E78E6E on xcolab_SpamReport (spamUserId, discussionMessageId);

create index IX_9C5CE364 on xcolab_StaffMember (userId);

create index IX_21569857 on xcolab_TrackedVisit (uuid_);