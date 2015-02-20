create table xcolab_ActivitySubscription (
	pk LONG not null primary key,
	classNameId LONG,
	classPK LONG,
	type_ INTEGER,
	automaticSubscriptionCounter INTEGER,
	extraData TEXT null,
	receiverId LONG,
	createDate DATE null,
	modifiedDate DATE null
);

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

create table xcolab_BalloonLink (
	uuid_ VARCHAR(75) not null primary key,
	targetUrl VARCHAR(75) null,
	visits INTEGER,
	balloonUserUuid VARCHAR(75) null,
	createDate DATE null
);

create table xcolab_BalloonStatsEntry (
	id_ LONG not null primary key,
	firstContestId LONG,
	secondContestId LONG,
	choosenContest INTEGER,
	cookie VARCHAR(256) null,
	ip VARCHAR(75) null,
	extraData VARCHAR(256) null
);

create table xcolab_BalloonText (
	id_ LONG not null primary key,
	name VARCHAR(75) null,
	textBeforeForm TEXT null,
	textAfterForm TEXT null,
	textBeforeShareButtons TEXT null,
	textAfterShareButtons TEXT null,
	acceptTosText TEXT null,
	emailTemplate TEXT null,
	emailSubjectTemplate TEXT null,
	twitterDescription TEXT null,
	twitterSubject TEXT null,
	facebookDescription TEXT null,
	facebookSubject TEXT null,
	enabled BOOLEAN
);

create table xcolab_BalloonUserTracking (
	uuid_ VARCHAR(75) not null primary key,
	email VARCHAR(200) null,
	parent VARCHAR(75) null,
	ip VARCHAR(75) null,
	createDate DATE null,
	registrationDate DATE null,
	formFiledDate DATE null,
	userId LONG,
	balloonTextId LONG,
	referrer VARCHAR(500) null,
	latitude DOUBLE,
	longitude DOUBLE,
	city VARCHAR(75) null,
	country VARCHAR(75) null,
	extraData TEXT null,
	balloonLinkUuid VARCHAR(75) null,
	balloonLinkContext VARCHAR(75) null,
	userAgent VARCHAR(500) null
);

create table xcolab_Contest (
	ContestPK LONG not null primary key,
	ContestName VARCHAR(2048) null,
	ContestShortName VARCHAR(1024) null,
	ContestDescription VARCHAR(3072) null,
	ContestModelDescription VARCHAR(2048) null,
	ContestPositionsDescription VARCHAR(2048) null,
	defaultPlanDescription TEXT null,
	PlanTypeId LONG,
	created DATE null,
	updated DATE null,
	authorId LONG,
	contestActive BOOLEAN,
	planTemplateId LONG,
	contestScheduleId LONG,
	focusAreaId LONG,
	contestTier LONG,
	contestLogoId LONG,
	featured_ BOOLEAN,
	plansOpenByDefault BOOLEAN,
	sponsorLogoId LONG,
	sponsorText VARCHAR(2048) null,
	sponsorLink VARCHAR(75) null,
	flag INTEGER,
	flagText VARCHAR(1024) null,
	flagTooltip VARCHAR(1024) null,
	groupId LONG,
	discussionGroupId LONG,
	weight INTEGER,
	resourcesUrl VARCHAR(1024) null,
	contestPrivate BOOLEAN,
	usePermissions BOOLEAN,
	contestCreationStatus VARCHAR(75) null,
	defaultModelId LONG,
	otherModels VARCHAR(75) null,
	points DOUBLE,
	defaultParentPointType LONG,
	pointDistributionStrategy VARCHAR(75) null
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

create table xcolab_ContestDiscussions (
	DiscussionId LONG not null primary key,
	ContestId LONG,
	Tab VARCHAR(75) null
);

create table xcolab_ContestEmailTemplate (
	type_ VARCHAR(75) not null primary key,
	subject TEXT null,
	header TEXT null,
	footer TEXT null
);

create table xcolab_ContestPhase (
	ContestPhasePK LONG not null primary key,
	ContestPK LONG,
	ContestPhaseType LONG,
	contestScheduleId LONG,
	fellowScreeningActive BOOLEAN,
	contestPhaseAutopromote VARCHAR(75) null,
	ContestPhaseDescriptionOverride TEXT null,
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
	copyOnPromote BOOLEAN
);

create table xcolab_ContestPhaseType (
	id_ LONG not null primary key,
	name VARCHAR(1024) null,
	description TEXT null,
	status VARCHAR(75) null,
	fellowScreeningActiveDefault BOOLEAN,
	contestPhaseAutopromoteDefault VARCHAR(75) null,
	invisible BOOLEAN,
	pointsAccessible INTEGER
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
	role VARCHAR(75) null
);

create table xcolab_DiscussionCategory (
	pk LONG not null primary key,
	categoryId LONG,
	categoryGroupId LONG,
	authorId LONG,
	name VARCHAR(1024) null,
	description TEXT null,
	createDate DATE null,
	deleted DATE null,
	threadsCount INTEGER,
	lastActivityDate DATE null,
	lastActivityAuthorId LONG
);

create table xcolab_DiscussionCategoryGroup (
	id_ LONG not null primary key,
	description TEXT null,
	url VARCHAR(1024) null,
	commentsThread LONG,
	isQuiet BOOLEAN
);

create table xcolab_DiscussionMessage (
	pk LONG not null primary key,
	messageId LONG,
	subject VARCHAR(1024) null,
	body TEXT null,
	threadId LONG,
	categoryId LONG,
	categoryGroupId LONG,
	authorId LONG,
	createDate DATE null,
	version LONG,
	deleted DATE null,
	responsesCount INTEGER,
	lastActivityDate DATE null,
	lastActivityAuthorId LONG
);

create table xcolab_DiscussionMessageFlag (
	pk LONG not null primary key,
	messageId LONG,
	flagType VARCHAR(75) null,
	data_ VARCHAR(2048) null,
	created DATE null,
	userId LONG
);

create table xcolab_EmailList (
	id_ LONG not null primary key,
	name VARCHAR(256) null,
	email VARCHAR(256) null
);

create table xcolab_FocusArea (
	id_ LONG not null primary key,
	name VARCHAR(256) null,
	order_ INTEGER
);

create table xcolab_FocusAreaOntologyTerm (
	focusAreaId LONG not null,
	ontologyTermId LONG not null,
	order_ INTEGER,
	primary key (focusAreaId, ontologyTermId)
);

create table xcolab_LandingPage (
	id_ LONG not null primary key,
	baseUrl VARCHAR(1024) null,
	targetUrl VARCHAR(1024) null,
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

create table xcolab_Message (
	messageId LONG not null primary key,
	fromId LONG,
	repliesTo LONG,
	createDate DATE null,
	subject VARCHAR(2048) null,
	content TEXT null
);

create table xcolab_MessageRecipientStatus (
	messageRecipientId LONG not null primary key,
	messageId LONG,
	userId LONG,
	opened BOOLEAN,
	archived BOOLEAN
);

create table xcolab_MessagingIgnoredRecipients (
	ignoredRecipientId LONG not null primary key,
	email VARCHAR(512) null,
	name VARCHAR(512) null,
	userId LONG,
	createDate DATE null
);

create table xcolab_MessagingMessage (
	messageId LONG not null primary key,
	name VARCHAR(1024) null,
	description TEXT null,
	subject VARCHAR(2048) null,
	body TEXT null,
	replyTo VARCHAR(1024) null,
	sendToAll BOOLEAN,
	conversionCount LONG,
	redirectURL VARCHAR(1024) null,
	creatorId LONG,
	createDate DATE null,
	modifiedDate DATE null
);

create table xcolab_MessagingMessageConversion (
	conversionId LONG not null primary key,
	conversionTypeId LONG,
	messageId LONG,
	ipAddress VARCHAR(75) null,
	extraData VARCHAR(2048) null,
	extraData2 VARCHAR(2048) null,
	createDate DATE null
);

create table xcolab_MessagingMessageConversionType (
	typeId LONG not null primary key,
	name VARCHAR(1024) null,
	description VARCHAR(2048) null
);

create table xcolab_MessagingMessageRecipient (
	recipientId LONG not null primary key,
	messageId LONG,
	userId LONG,
	emailAddress VARCHAR(512) null
);

create table xcolab_MessagingRedirectLink (
	redirectId LONG not null primary key,
	link VARCHAR(1024) null,
	messageId LONG,
	createDate DATE null
);

create table xcolab_MessagingUserPreferences (
	messagingPreferencesId LONG not null primary key,
	userId LONG,
	emailOnSend BOOLEAN,
	emailOnReceipt BOOLEAN,
	emailOnActivity BOOLEAN,
	emailActivityDailyDigest BOOLEAN
);

create table xcolab_ModelCategory (
	modelCategoryPK LONG not null primary key,
	modelCategoryName VARCHAR(256) null,
	modelCategoryDescription VARCHAR(2048) null,
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
	customInputsDefinition TEXT null
);

create table xcolab_ModelInputGroup (
	modelInputGroupPK LONG not null primary key,
	modelId LONG,
	nameAndDescriptionMetaDataId LONG,
	name VARCHAR(1024) null,
	description TEXT null,
	displayItemOrder INTEGER,
	groupType VARCHAR(256) null,
	parentGroupPK LONG
);

create table xcolab_ModelInputItem (
	modelInputItemPK LONG not null primary key,
	modelId LONG,
	modelInputItemID LONG,
	modelGroupId LONG,
	displayItemOrder INTEGER,
	type_ VARCHAR(256) null,
	properties VARCHAR(2048) null
);

create table xcolab_ModelOutputChartOrder (
	modelOutputChartOrderPK LONG not null primary key,
	modelId LONG,
	modelOutputLabel VARCHAR(1024) null,
	modelOutputChartOrder INTEGER,
	modelIndexRangePolicy VARCHAR(2048) null,
	modelIndexRangeMessage VARCHAR(2048) null,
	modelIndexErrorPolicy VARCHAR(2048) null,
	modelIndexErrorMessage VARCHAR(2048) null,
	modelChartIsVisible BOOLEAN
);

create table xcolab_ModelOutputItem (
	modelOutputItemModifierPK LONG not null primary key,
	modelId LONG,
	modelOutputItemId LONG,
	modelOutputItemOrder INTEGER,
	modelItemRangePolicy VARCHAR(2048) null,
	modelItemRangeMessage VARCHAR(2048) null,
	modelItemErrorPolicy VARCHAR(2048) null,
	modelItemErrorMessage VARCHAR(2048) null,
	modelItemLabelFormat VARCHAR(2048) null,
	modelItemIsVisible BOOLEAN,
	itemType VARCHAR(256) null,
	relatedOutputItem LONG
);

create table xcolab_ModelPosition (
	id_ LONG not null primary key,
	positionId LONG,
	modelId LONG
);

create table xcolab_OntologySpace (
	id_ LONG not null primary key,
	name VARCHAR(256) null,
	description TEXT null,
	order_ INTEGER
);

create table xcolab_OntologyTerm (
	id_ LONG not null primary key,
	parentId LONG,
	ontologySpaceId LONG,
	name VARCHAR(256) null,
	descriptionUrl VARCHAR(1024) null,
	order_ INTEGER
);

create table xcolab_OntologyTermEntity (
	id_ LONG not null primary key,
	termId LONG,
	classNameId LONG,
	classPK LONG
);

create table xcolab_Plan2Proposal (
	planId LONG not null primary key,
	proposalId LONG
);

create table xcolab_PlanAttribute (
	attributeId LONG not null primary key,
	planId LONG,
	attributeName VARCHAR(256) null,
	attributeValue VARCHAR(2048) null
);

create table xcolab_PlanAttributeFilter (
	planAttributeFilterId LONG not null primary key,
	attributeName VARCHAR(75) null,
	planUserSettingsId LONG,
	max DOUBLE,
	min DOUBLE,
	stringVal VARCHAR(2048) null
);

create table xcolab_PlanColumnSettings (
	planColumnSettingsId LONG not null primary key,
	columnName VARCHAR(75) null,
	planUserSettingsId LONG,
	visible BOOLEAN
);

create table xcolab_PlanDescription (
	id_ LONG not null primary key,
	planId LONG,
	name VARCHAR(1024) null,
	description TEXT null,
	version LONG,
	planVersion LONG,
	created DATE null,
	updateAuthorId LONG,
	image LONG,
	pitch VARCHAR(2048) null
);

create table xcolab_PlanFan (
	id_ LONG not null primary key,
	userId LONG,
	planId LONG,
	created DATE null,
	deleted DATE null
);

create table xcolab_PlanItem (
	id_ LONG not null primary key,
	planId LONG,
	state_ VARCHAR(75) null,
	updated DATE null,
	updateAuthorId LONG,
	updateType VARCHAR(75) null,
	version LONG
);

create table xcolab_PlanItemGroup (
	planId LONG not null primary key,
	groupId LONG
);

create table xcolab_PlanMeta (
	id_ LONG not null primary key,
	planId LONG,
	planTypeId LONG,
	planCreated LONG,
	authorId LONG,
	votes INTEGER,
	planGroupId LONG,
	open BOOLEAN,
	status VARCHAR(75) null,
	mbCategoryId LONG,
	categoryGroupId LONG,
	version LONG,
	planVersion LONG,
	created DATE null,
	updateAuthorId LONG,
	modelId LONG,
	promoted BOOLEAN,
	previousContestPhase LONG,
	contestPhase LONG
);

create table xcolab_PlanModelRun (
	id_ LONG not null primary key,
	planId LONG,
	scenarioId LONG,
	planVersion LONG,
	version LONG,
	created DATE null,
	updateAuthorId LONG
);

create table xcolab_PlanPosition (
	planId LONG not null,
	positionId LONG not null,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (planId, positionId)
);

create table xcolab_PlanPositionItem (
	planPositionsId LONG not null,
	positionId LONG not null,
	primary key (planPositionsId, positionId)
);

create table xcolab_PlanPositions (
	id_ LONG not null primary key,
	planId LONG,
	planVersion LONG,
	version LONG,
	created DATE null,
	updateAuthorId LONG
);

create table xcolab_PlanPropertyFilter (
	planPropertyFilterId LONG not null primary key,
	propertyName VARCHAR(75) null,
	planUserSettingsId LONG,
	value VARCHAR(75) null
);

create table xcolab_PlanRelated (
	sectionId LONG not null,
	relatedPlanId LONG not null,
	primary key (sectionId, relatedPlanId)
);

create table xcolab_PlanSection (
	id_ LONG not null primary key,
	planSectionDefinitionId LONG,
	planId LONG,
	content TEXT null,
	numericValue LONG,
	created DATE null,
	version LONG,
	planVersion LONG,
	updateAuthorId LONG
);

create table xcolab_PlanSectionDefinition (
	id_ LONG not null primary key,
	type_ VARCHAR(75) null,
	adminTitle VARCHAR(1024) null,
	title VARCHAR(1024) null,
	defaultText TEXT null,
	helpText TEXT null,
	characterLimit INTEGER,
	focusAreaId LONG,
	tier LONG,
	locked BOOLEAN
);

create table xcolab_PlanSectionPlanMap (
	sectionId LONG not null,
	relatedPlanId LONG not null,
	primary key (sectionId, relatedPlanId)
);

create table xcolab_PlanTeamHistory (
	id_ LONG not null primary key,
	planId LONG,
	userId LONG,
	action VARCHAR(75) null,
	payload VARCHAR(75) null,
	created DATE null,
	updateAuthorId LONG
);

create table xcolab_PlanTemplate (
	id_ LONG not null primary key,
	name VARCHAR(1024) null,
	baseTemplateId LONG
);

create table xcolab_PlanTemplateSection (
	planTemplateId LONG not null,
	planSectionId LONG not null,
	weight INTEGER,
	primary key (planTemplateId, planSectionId)
);

create table xcolab_PlanType (
	planTypeId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(2048) null,
	modelId LONG,
	modelTypeName VARCHAR(75) null,
	published BOOLEAN,
	publishedCounterpartId LONG,
	isDefault BOOLEAN,
	defaultModelId LONG,
	defaultScenarioId LONG
);

create table xcolab_PlanTypeAttribute (
	planTypeAttributeId LONG not null primary key,
	planTypeId LONG,
	attributeName VARCHAR(75) null
);

create table xcolab_PlanTypeColumn (
	planTypeColumnId LONG not null primary key,
	planTypeId LONG,
	weight INTEGER,
	columnName VARCHAR(75) null,
	visibleByDefault BOOLEAN
);

create table xcolab_PlanVote (
	userId LONG not null,
	contestId LONG not null,
	planId LONG,
	createDate DATE null,
	primary key (userId, contestId)
);

create table xcolab_PlansFilter (
	userId LONG not null,
	planTypeId LONG not null,
	name VARCHAR(75) null,
	creator VARCHAR(75) null,
	description VARCHAR(75) null,
	CO2From DOUBLE,
	CO2To DOUBLE,
	votesFrom DOUBLE,
	votesTo DOUBLE,
	damageFrom DOUBLE,
	damageTo DOUBLE,
	mitigationFrom DOUBLE,
	mitigationTo DOUBLE,
	dateFrom DATE null,
	dateTo DATE null,
	filterPositionsAll BOOLEAN,
	enabled BOOLEAN,
	primary key (userId, planTypeId)
);

create table xcolab_PlansFilterPosition (
	userId LONG not null,
	planTypeId LONG not null,
	positionId LONG not null,
	primary key (userId, planTypeId, positionId)
);

create table xcolab_PlansUserSettings (
	planUserSettingsId LONG not null primary key,
	userId LONG,
	planTypeId LONG,
	sortColumn VARCHAR(75) null,
	sortDirection VARCHAR(75) null,
	filterEnabled BOOLEAN,
	filterPositionsAll BOOLEAN
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
	stringValue TEXT null,
	realValue DOUBLE
);

create table xcolab_ProposalAttributeType (
	name VARCHAR(75) not null primary key,
	visibleInVersionHistory BOOLEAN,
	copyOnPromote BOOLEAN
);

create table xcolab_ProposalContestPhaseAttribute (
	id_ LONG not null primary key,
	proposalId LONG,
	contestPhaseId LONG,
	name VARCHAR(75) null,
	additionalId LONG,
	numericValue LONG,
	stringValue TEXT null,
	realValue DOUBLE
);

create table xcolab_ProposalContestPhaseAttributeType (
	name VARCHAR(75) not null primary key,
	copyOnPromote BOOLEAN
);

create table xcolab_ProposalRating (
	id_ LONG not null primary key,
	proposalId LONG,
	contestPhaseId LONG,
	userId LONG,
	ratingValueId LONG,
	comment_ TEXT null,
	commentEnabled BOOLEAN,
	otherDataString VARCHAR(75) null
);

create table xcolab_ProposalRatingType (
	id_ LONG not null primary key,
	label VARCHAR(75) null,
	description TEXT null,
	judgeType INTEGER
);

create table xcolab_ProposalRatingValue (
	id_ LONG not null primary key,
	ratingTypeId LONG,
	value LONG,
	name VARCHAR(75) null,
	description TEXT null
);

create table xcolab_ProposalSupporter (
	proposalId LONG not null,
	userId LONG not null,
	createDate DATE null,
	primary key (proposalId, userId)
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
	primary key (contestPhaseId, userId)
);

create table xcolab_StaffMember (
	id_ LONG not null primary key,
	userId LONG,
	categoryId LONG,
	firstNames VARCHAR(75) null,
	lastName VARCHAR(75) null,
	url VARCHAR(255) null,
	photoUrl VARCHAR(255) null,
	role VARCHAR(75) null,
	organization VARCHAR(75) null,
	sort INTEGER
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