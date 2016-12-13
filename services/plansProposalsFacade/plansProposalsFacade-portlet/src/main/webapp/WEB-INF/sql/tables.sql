create table xcolab_AnalyticsUserEvent (
	userId LONG not null,
	idString VARCHAR(75) not null,
	category VARCHAR(75) null,
	action VARCHAR(75) null,
	label VARCHAR(75) null,
	value INTEGER,
	created DATE null,
	primary key (userId, idString)
);

create table xcolab_Contest (
	ContestPK LONG not null primary key,
	contestTypeId LONG,
	ContestName VARCHAR(75) null,
	ContestShortName VARCHAR(75) null,
	ContestUrlName VARCHAR(75) null,
	ContestYear LONG,
	ContestDescription VARCHAR(75) null,
	ContestModelDescription VARCHAR(75) null,
	ContestPositionsDescription VARCHAR(75) null,
	created DATE null,
	updated DATE null,
	authorId LONG,
	contestActive BOOLEAN,
	planTemplateId LONG,
	contestScheduleId LONG,
	proposalCreationTemplateString VARCHAR(75) null,
	voteTemplateString VARCHAR(75) null,
	proposalVoteTemplateString VARCHAR(75) null,
	proposalVoteConfirmationTemplateString VARCHAR(75) null,
	voteQuestionTemplateString VARCHAR(75) null,
	focusAreaId LONG,
	contestTier LONG,
	contestLogoId LONG,
	featured_ BOOLEAN,
	plansOpenByDefault BOOLEAN,
	sponsorLogoId LONG,
	sponsorText VARCHAR(75) null,
	sponsorLink VARCHAR(75) null,
	flag INTEGER,
	flagText VARCHAR(75) null,
	flagTooltip VARCHAR(75) null,
	groupId LONG,
	discussionGroupId LONG,
	weight INTEGER,
	resourcesUrl VARCHAR(75) null,
	contestPrivate BOOLEAN,
	usePermissions BOOLEAN,
	contestCreationStatus VARCHAR(75) null,
	defaultModelId LONG,
	otherModels VARCHAR(75) null,
	defaultModelSettings VARCHAR(75) null,
	points DOUBLE,
	defaultParentPointType LONG,
	pointDistributionStrategy VARCHAR(75) null,
	emailTemplateUrl VARCHAR(75) null,
	show_in_tile_view BOOLEAN,
	show_in_list_view BOOLEAN,
	show_in_outline_view BOOLEAN,
	hideRibbons BOOLEAN,
	resourceArticleId LONG
);

create table xcolab_ContestDebate (
	id_ LONG not null primary key,
	debateId LONG,
	ContestPK LONG
);

create table xcolab_ContestDiscussion (
	DiscussionId LONG not null primary key,
	ContestId LONG,
	Tab VARCHAR(75) null
);

create table xcolab_ContestEmailTemplate (
	type_ VARCHAR(75) not null primary key,
	subject VARCHAR(75) null,
	header VARCHAR(75) null,
	footer VARCHAR(75) null
);

create table xcolab_ContestPhase (
	ContestPhasePK LONG not null primary key,
	ContestPK LONG,
	ContestPhaseType LONG,
	contestScheduleId LONG,
	fellowScreeningActive BOOLEAN,
	contestPhaseAutopromote VARCHAR(75) null,
	ContestPhaseDescriptionOverride VARCHAR(75) null,
	phaseActiveOverride BOOLEAN,
	phaseInactiveOverride BOOLEAN,
	PhaseStartDate DATE null,
	PhaseEndDate DATE null,
	PhaseBufferEndDated DATE null,
	nextStatus VARCHAR(75) null,
	created DATE null,
	updated DATE null,
	authorId LONG
);

create table xcolab_ContestPhaseColumn (
	id_ LONG not null primary key,
	ContestPhasePK LONG,
	columnName VARCHAR(75) null,
	columnWeight INTEGER,
	defaultSort BOOLEAN
);

create table xcolab_ContestPhaseRibbonType (
	id_ LONG not null primary key,
	ribbon INTEGER,
	hoverText VARCHAR(75) null,
	description VARCHAR(75) null,
	copyOnPromote BOOLEAN,
	sortOrder INTEGER
);

create table xcolab_ContestPhaseType (
	id_ LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	status VARCHAR(75) null,
	fellowScreeningActiveDefault BOOLEAN,
	contestPhaseAutopromoteDefault VARCHAR(75) null,
	invisible BOOLEAN,
	pointsAccessible INTEGER,
	defaultPromotionType VARCHAR(75) null
);

create table xcolab_ContestSchedule (
	id_ LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	status VARCHAR(75) null,
	baseScheduleId LONG
);

create table xcolab_ContestTeamMember (
	id_ LONG not null primary key,
	contestId LONG,
	userId LONG,
	roleId LONG
);

create table xcolab_ContestTeamMemberRole (
	id_ LONG not null primary key,
	role VARCHAR(75) null,
	sort INTEGER
);

create table xcolab_ContestType (
	id_ LONG not null primary key,
	contestName VARCHAR(75) null,
	contestNamePlural VARCHAR(75) null,
	proposalName VARCHAR(75) null,
	proposalNamePlural VARCHAR(75) null,
	portletName VARCHAR(75) null,
	portletUrl VARCHAR(75) null,
	friendlyUrlStringContests VARCHAR(75) null,
	friendlyUrlStringProposal VARCHAR(75) null,
	menuItemName VARCHAR(75) null,
	hasDiscussion BOOLEAN,
	suggestionContestId LONG,
	rulesPageName VARCHAR(75) null,
	rulesPageUrl VARCHAR(75) null
);

create table xcolab_EmailList (
	id_ LONG not null primary key,
	name VARCHAR(75) null,
	email VARCHAR(75) null
);

create table xcolab_FocusArea (
	id_ LONG not null primary key,
	name VARCHAR(75) null,
	order_ INTEGER
);

create table xcolab_FocusAreaOntologyTerm (
	focusAreaId LONG not null,
	ontologyTermId LONG not null,
	order_ INTEGER,
	primary key (focusAreaId, ontologyTermId)
);

create table xcolab_ImpactDefaultSeries (
	seriesId LONG not null,
	name VARCHAR(75) not null,
	description VARCHAR(75) null,
	focusAreaId LONG,
	visible BOOLEAN,
	editable BOOLEAN,
	primary key (seriesId, name)
);

create table xcolab_ImpactDefaultSeriesData (
	seriesId LONG not null,
	year INTEGER not null,
	value DOUBLE,
	primary key (seriesId, year)
);

create table xcolab_ImpactIteration (
	iterationId LONG not null,
	year INTEGER not null,
	primary key (iterationId, year)
);

create table xcolab_ImpactTemplateFocusAreaList (
	focusAreaListId LONG not null primary key,
	name VARCHAR(75) null
);

create table xcolab_ImpactTemplateMaxFocusArea (
	focusAreaListId LONG not null,
	focusAreaId LONG not null,
	primary key (focusAreaListId, focusAreaId)
);

create table xcolab_ImpactTemplateSeries (
	seriesId LONG not null primary key,
	iterationId LONG,
	name VARCHAR(75) null
);

create table xcolab_LandingPage (
	id_ LONG not null primary key,
	baseUrl VARCHAR(75) null,
	targetUrl VARCHAR(75) null,
	updated DATE null
);

create table xcolab_LoginLog (
	pk LONG not null primary key,
	userId LONG,
	createDate DATE null,
	ipAddress VARCHAR(75) null,
	city VARCHAR(75) null,
	country VARCHAR(75) null,
	entryUrl VARCHAR(255) null
);

create table xcolab_MessagingIgnoredRecipients (
	ignoredRecipientId LONG not null primary key,
	email VARCHAR(75) null,
	name VARCHAR(75) null,
	userId LONG,
	createDate DATE null
);

create table xcolab_MessagingMessage (
	messageId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	subject VARCHAR(75) null,
	body VARCHAR(75) null,
	replyTo VARCHAR(75) null,
	sendToAll BOOLEAN,
	conversionCount LONG,
	redirectURL VARCHAR(75) null,
	creatorId LONG,
	createDate DATE null,
	modifiedDate DATE null
);

create table xcolab_MessagingMessageConversion (
	conversionId LONG not null primary key,
	conversionTypeId LONG,
	messageId LONG,
	ipAddress VARCHAR(75) null,
	extraData VARCHAR(75) null,
	extraData2 VARCHAR(75) null,
	createDate DATE null
);

create table xcolab_MessagingMessageConversionType (
	typeId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table xcolab_MessagingMessageRecipient (
	recipientId LONG not null primary key,
	messageId LONG,
	userId LONG,
	emailAddress VARCHAR(75) null
);

create table xcolab_MessagingRedirectLink (
	redirectId LONG not null primary key,
	link VARCHAR(75) null,
	messageId LONG,
	createDate DATE null
);

create table xcolab_ModelCategory (
	modelCategoryPK LONG not null primary key,
	modelCategoryName VARCHAR(75) null,
	modelCategoryDescription VARCHAR(75) null,
	modelCategoryDisplayWeight INTEGER
);

create table xcolab_ModelDiscussion (
	modelDiscussionId LONG not null primary key,
	modelId LONG,
	categoryId LONG
);

create table xcolab_ModelGlobalPreference (
	modelGlobalPreferencePK LONG not null primary key,
	modelId LONG,
	visible BOOLEAN,
	weight INTEGER,
	expertEvaluationPageId LONG,
	modelCategoryId LONG,
	usesCustomInputs BOOLEAN,
	customInputsDefinition VARCHAR(75) null
);

create table xcolab_ModelInputGroup (
	modelInputGroupPK LONG not null primary key,
	modelId LONG,
	nameAndDescriptionMetaDataId LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	displayItemOrder INTEGER,
	groupType VARCHAR(75) null,
	parentGroupPK LONG
);

create table xcolab_ModelInputItem (
	modelInputItemPK LONG not null primary key,
	modelId LONG,
	modelInputItemID LONG,
	modelGroupId LONG,
	displayItemOrder INTEGER,
	type_ VARCHAR(75) null,
	properties VARCHAR(75) null
);

create table xcolab_ModelOutputChartOrder (
	modelOutputChartOrderPK LONG not null primary key,
	modelId LONG,
	modelOutputLabel VARCHAR(75) null,
	modelOutputChartOrder INTEGER,
	modelIndexRangePolicy VARCHAR(75) null,
	modelIndexRangeMessage VARCHAR(75) null,
	modelIndexErrorPolicy VARCHAR(75) null,
	modelIndexErrorMessage VARCHAR(75) null,
	modelChartIsVisible BOOLEAN
);

create table xcolab_ModelOutputItem (
	modelOutputItemModifierPK LONG not null primary key,
	modelId LONG,
	modelOutputItemId LONG,
	modelOutputItemOrder INTEGER,
	modelItemRangePolicy VARCHAR(75) null,
	modelItemRangeMessage VARCHAR(75) null,
	modelItemErrorPolicy VARCHAR(75) null,
	modelItemErrorMessage VARCHAR(75) null,
	modelItemLabelFormat VARCHAR(75) null,
	modelItemIsVisible BOOLEAN,
	itemType VARCHAR(75) null,
	relatedOutputItem LONG
);

create table xcolab_ModelPosition (
	id_ LONG not null primary key,
	positionId LONG,
	modelId LONG
);

create table xcolab_OntologySpace (
	id_ LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	order_ INTEGER
);

create table xcolab_OntologyTerm (
	id_ LONG not null primary key,
	parentId LONG,
	ontologySpaceId LONG,
	name VARCHAR(75) null,
	descriptionUrl VARCHAR(75) null,
	order_ INTEGER
);

create table xcolab_OntologyTermEntity (
	id_ LONG not null primary key,
	termId LONG,
	classNameId LONG,
	classPK LONG
);

create table xcolab_PlanSectionDefinition (
	id_ LONG not null primary key,
	type_ VARCHAR(75) null,
	adminTitle VARCHAR(75) null,
	title VARCHAR(75) null,
	defaultText VARCHAR(75) null,
	helpText VARCHAR(75) null,
	characterLimit INTEGER,
	focusAreaId LONG,
	tier LONG,
	allowedContestTypeIds VARCHAR(75) null,
	allowedValues VARCHAR(75) null,
	additionalIds VARCHAR(75) null,
	locked BOOLEAN,
	contestIntegrationRelevance BOOLEAN
);

create table xcolab_PlanTemplate (
	id_ LONG not null primary key,
	name VARCHAR(75) null,
	baseTemplateId LONG,
	impactSeriesTemplateId LONG,
	focusAreaListTemplateId LONG
);

create table xcolab_PlanTemplateSection (
	planTemplateId LONG not null,
	planSectionId LONG not null,
	weight INTEGER,
	primary key (planTemplateId, planSectionId)
);

create table xcolab_PointDistributionTarget (
	id_ LONG not null primary key,
	contestId LONG,
	proposalId LONG,
	numberOfPoints DOUBLE,
	pointTypeOverride LONG
);

create table xcolab_PointType (
	id_ LONG not null primary key,
	parentPointTypeId LONG,
	percentageOfParent DOUBLE,
	distributionStrategy VARCHAR(75) null,
	receiverLimitationStrategy VARCHAR(75) null,
	name VARCHAR(75) null,
	sort LONG
);

create table xcolab_Points (
	id_ LONG not null primary key,
	proposalId LONG,
	userId LONG,
	materializedPoints DOUBLE,
	hypotheticalPoints DOUBLE,
	pointsSourceId LONG,
	originatingContestPK LONG,
	originatingProposalId LONG
);

create table xcolab_PointsDistributionConfiguration (
	id_ LONG not null primary key,
	proposalId LONG,
	pointTypeId LONG,
	targetUserId LONG,
	targetSubProposalId LONG,
	targetPlanSectionDefinitionId LONG,
	percentage DOUBLE,
	creator LONG,
	createDate DATE null
);

create table xcolab_Proposal (
	proposalId LONG not null primary key,
	createDate DATE null,
	updatedDate DATE null,
	currentVersion INTEGER,
	authorId LONG,
	visible BOOLEAN,
	discussionId LONG,
	resultsDiscussionId LONG,
	judgeDiscussionId LONG,
	fellowDiscussionId LONG,
	advisorDiscussionId LONG,
	groupId LONG
);

create table xcolab_Proposal2Phase (
	proposalId LONG not null,
	contestPhaseId LONG not null,
	versionFrom INTEGER,
	versionTo INTEGER,
	sortWeight INTEGER,
	autopromoteCandidate BOOLEAN,
	primary key (proposalId, contestPhaseId)
);

create table xcolab_ProposalAttribute (
	id_ LONG not null primary key,
	proposalId LONG,
	version INTEGER,
	versionWhenCreated INTEGER,
	name VARCHAR(75) null,
	additionalId LONG,
	numericValue LONG,
	stringValue VARCHAR(75) null,
	realValue DOUBLE
);

create table xcolab_ProposalContestPhaseAttribute (
	id_ LONG not null primary key,
	proposalId LONG,
	contestPhaseId LONG,
	name VARCHAR(75) null,
	additionalId LONG,
	numericValue LONG,
	stringValue VARCHAR(75) null,
	realValue DOUBLE
);

create table xcolab_ProposalMoveHistory (
	id_ LONG not null primary key,
	sourceProposalId LONG,
	sourceContestId LONG,
	sourcePhaseId LONG,
	targetProposalId LONG,
	targetContestId LONG,
	targetPhaseId LONG,
	movingUserId LONG,
	moveDate DATE null,
	moveType VARCHAR(75) null
);

create table xcolab_ProposalRating (
	id_ LONG not null primary key,
	proposalId LONG,
	contestPhaseId LONG,
	userId LONG,
	ratingValueId LONG,
	comment_ VARCHAR(75) null,
	commentEnabled BOOLEAN,
	otherDataString VARCHAR(75) null,
	onlyForInternalUsage BOOLEAN
);

create table xcolab_ProposalRatingType (
	id_ LONG not null primary key,
	label VARCHAR(75) null,
	description VARCHAR(75) null,
	judgeType INTEGER,
	isActive BOOLEAN
);

create table xcolab_ProposalRatingValue (
	id_ LONG not null primary key,
	ratingTypeId LONG,
	value LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table xcolab_ProposalReference (
	proposalId LONG not null,
	subProposalId LONG not null,
	sectionAttributeId LONG,
	primary key (proposalId, subProposalId)
);

create table xcolab_ProposalSupporter (
	proposalId LONG not null,
	userId LONG not null,
	createDate DATE null,
	primary key (proposalId, userId)
);

create table xcolab_ProposalUnversionedAttribute (
	id_ LONG not null primary key,
	proposalId LONG,
	createAuthorId LONG,
	lastAuthorId LONG,
	createDate DATE null,
	lastUpdateDate DATE null,
	name VARCHAR(75) null,
	addtionalId INTEGER,
	numericValue LONG,
	stringValue VARCHAR(75) null,
	realValue DOUBLE
);

create table xcolab_ProposalVersion (
	proposalId LONG not null,
	version INTEGER not null,
	authorId LONG,
	createDate DATE null,
	updateType VARCHAR(75) null,
	updateAdditionalId LONG,
	primary key (proposalId, version)
);

create table xcolab_ProposalVote (
	proposalId LONG,
	contestPhaseId LONG not null,
	userId LONG not null,
	createDate DATE null,
	isValid BOOLEAN,
	confirmationEmailSendDate DATE null,
	confirmationToken VARCHAR(75) null,
	primary key (contestPhaseId, userId)
);

create table xcolab_Role_ (
	roleId LONG not null primary key,
	name VARCHAR(75) null
);

create table xcolab_RolesCategory (
	roleId LONG not null primary key,
	categoryName VARCHAR(75) null,
	roleOrdinal INTEGER
);

create table xcolab_SocialActivity (
	activityId LONG not null primary key,
	userId LONG
);

create table xcolab_TrackedVisit (
	id_ LONG not null primary key,
	uuid_ VARCHAR(36) null,
	ip VARCHAR(45) null,
	city VARCHAR(255) null,
	country VARCHAR(2) null,
	url VARCHAR(2048) null,
	browser TEXT null,
	headers TEXT null,
	referer VARCHAR(2048) null,
	createDate DATE null
);

create table xcolab_TrackedVisitor2User (
	id_ LONG not null primary key,
	uuid_ VARCHAR(36) null,
	userId LONG,
	createDate DATE null
);