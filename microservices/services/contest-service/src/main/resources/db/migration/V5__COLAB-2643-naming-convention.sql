ALTER TABLE xcolab_Contest CHANGE ContestPK                              id                              bigint auto_increment;
ALTER TABLE xcolab_Contest CHANGE contestTypeId                          contest_type_id                          bigint        null;
ALTER TABLE xcolab_Contest CHANGE ContestName                            question                            varchar(1024) null;
ALTER TABLE xcolab_Contest CHANGE ContestShortName                       title                       varchar(512)  null;
ALTER TABLE xcolab_Contest CHANGE ContestUrlName                         contest_url_name                         varchar(75)   null;
ALTER TABLE xcolab_Contest CHANGE ContestYear                            contest_year                            bigint        null;
ALTER TABLE xcolab_Contest CHANGE ContestDescription                     description                     longtext      null;
ALTER TABLE xcolab_Contest CHANGE ContestModelDescription                contest_model_description                longtext      null;
ALTER TABLE xcolab_Contest CHANGE ContestPositionsDescription            contest_positions_description            longtext      null;
ALTER TABLE xcolab_Contest CHANGE created                                created_at                                datetime      null;
ALTER TABLE xcolab_Contest CHANGE updated                                updated_at                                datetime      null;
ALTER TABLE xcolab_Contest CHANGE authorId                               author_user_id                               bigint        null;
ALTER TABLE xcolab_Contest CHANGE contestActive                          contest_active                          tinyint       null;
ALTER TABLE xcolab_Contest CHANGE planTemplateId                         proposal_template_id                         bigint        null;
ALTER TABLE xcolab_Contest CHANGE contestScheduleId                      contest_schedule_id                      bigint        null;
ALTER TABLE xcolab_Contest CHANGE proposalCreationTemplateString         proposal_creation_template_string         varchar(75)   null;
ALTER TABLE xcolab_Contest CHANGE voteTemplateString                     vote_template_string                     varchar(75)   null;
ALTER TABLE xcolab_Contest CHANGE proposalVoteTemplateString             proposal_vote_template_string             varchar(75)   null;
ALTER TABLE xcolab_Contest CHANGE proposalVoteConfirmationTemplateString proposal_vote_confirmation_template_string varchar(75)   null;
ALTER TABLE xcolab_Contest CHANGE voteQuestionTemplateString             vote_question_template_string             varchar(75)   null;
ALTER TABLE xcolab_Contest CHANGE focusAreaId                            focus_area_id                            bigint        null;
ALTER TABLE xcolab_Contest CHANGE contestTier                            contest_tier                            bigint        null;
ALTER TABLE xcolab_Contest CHANGE contestLogoId                          contest_logo_id                          bigint        null;
ALTER TABLE xcolab_Contest CHANGE featured_                              featured                              tinyint       null;
ALTER TABLE xcolab_Contest CHANGE plansOpenByDefault                     plans_open_by_default                     tinyint       null;
ALTER TABLE xcolab_Contest CHANGE sponsorLogoId                          sponsor_logo_id                          bigint        null;
ALTER TABLE xcolab_Contest CHANGE defaultProposalLogoId                  default_proposal_logo_id                  bigint        null;
ALTER TABLE xcolab_Contest CHANGE sponsorText                            sponsor_text                            varchar(500)  null;
ALTER TABLE xcolab_Contest CHANGE sponsorLink                            sponsor_link                            varchar(500)  null;
ALTER TABLE xcolab_Contest CHANGE flag                                   flag                                   int           null;
ALTER TABLE xcolab_Contest CHANGE flagText                               flag_text                               varchar(256)  null;
ALTER TABLE xcolab_Contest CHANGE flagTooltip                            flag_tooltip                            varchar(512)  null;
ALTER TABLE xcolab_Contest CHANGE discussionGroupId                      discussion_group_id                      bigint        null;
ALTER TABLE xcolab_Contest CHANGE weight                                 weight                                 int           null;
ALTER TABLE xcolab_Contest CHANGE resourcesUrl                           resources_url                           varchar(1024) null;
ALTER TABLE xcolab_Contest CHANGE contestPrivate                         contest_private                         tinyint       null;
ALTER TABLE xcolab_Contest CHANGE usePermissions                         use_permissions                         tinyint       null;
ALTER TABLE xcolab_Contest CHANGE contestCreationStatus                  contest_creation_status                  varchar(75)   null;
ALTER TABLE xcolab_Contest CHANGE defaultModelId                         default_model_id                         bigint        null;
ALTER TABLE xcolab_Contest CHANGE otherModels                            other_models                            varchar(75)   null;
ALTER TABLE xcolab_Contest CHANGE defaultModelSettings                   default_model_settings                   varchar(75)   null;
ALTER TABLE xcolab_Contest CHANGE points                                 points                                 double        null;
ALTER TABLE xcolab_Contest CHANGE defaultParentPointType                 default_parent_point_type                 bigint        null;
ALTER TABLE xcolab_Contest CHANGE pointDistributionStrategy              point_distribution_strategy              varchar(75)   null;
ALTER TABLE xcolab_Contest CHANGE emailTemplateUrl                       email_template_url                       varchar(500)  null;
ALTER TABLE xcolab_Contest CHANGE show_in_tile_view                      show_in_tile_view                      tinyint       null;
ALTER TABLE xcolab_Contest CHANGE show_in_list_view                      show_in_list_view                      tinyint       null;
ALTER TABLE xcolab_Contest CHANGE show_in_outline_view                   show_in_outline_view                   tinyint       null;
ALTER TABLE xcolab_Contest CHANGE hideRibbons                            hide_ribbons                            tinyint       null;
ALTER TABLE xcolab_Contest CHANGE resourceArticleId                      resource_article_id                      bigint        null;
rename table xcolab_Contest to contest__contest;

ALTER TABLE xcolab_ContestCollectionCard CHANGE id_                   id                   bigint auto_increment;
ALTER TABLE xcolab_ContestCollectionCard CHANGE `order`               sort_order              int default '0'                     null;
rename table xcolab_ContestCollectionCard to contest__contest_collection_card;

ALTER TABLE xcolab_ContestDiscussion CHANGE DiscussionId  id bigint      not null;
ALTER TABLE xcolab_ContestDiscussion CHANGE ContestId     contest_id    bigint      null;
ALTER TABLE xcolab_ContestDiscussion CHANGE Tab           tab          varchar(75) null;
rename table xcolab_ContestDiscussion to contest__contest_discussion;

ALTER TABLE xcolab_ContestPhase CHANGE ContestPhasePK          id          bigint auto_increment;
ALTER TABLE xcolab_ContestPhase CHANGE ContestPK               contest_id               bigint      null;
ALTER TABLE xcolab_ContestPhase CHANGE ContestPhaseType        contest_phase_type_id        bigint      null;
ALTER TABLE xcolab_ContestPhase CHANGE contestScheduleId       contest_schedule_id       bigint      null;
ALTER TABLE xcolab_ContestPhase CHANGE contestPhaseAutopromote contest_phase_autopromote varchar(75) null;
ALTER TABLE xcolab_ContestPhase CHANGE PhaseStartDate          phase_start_date          datetime    null;
ALTER TABLE xcolab_ContestPhase CHANGE PhaseEndDate            phase_end_date            datetime    null;
ALTER TABLE xcolab_ContestPhase CHANGE created                 created_at                 datetime    null;
ALTER TABLE xcolab_ContestPhase CHANGE updated                 updated_at                 datetime    null;
rename table xcolab_ContestPhase to contest__contest_phase;

ALTER TABLE xcolab_ContestPhaseRibbonType CHANGE id_           id           bigint      not null;
ALTER TABLE xcolab_ContestPhaseRibbonType CHANGE ribbon        ribbon        int         null;
ALTER TABLE xcolab_ContestPhaseRibbonType CHANGE title         title         varchar(50) not null;
ALTER TABLE xcolab_ContestPhaseRibbonType CHANGE hoverText     hover_text     varchar(75) null;
ALTER TABLE xcolab_ContestPhaseRibbonType CHANGE showText      show_text      tinyint     not null;
ALTER TABLE xcolab_ContestPhaseRibbonType CHANGE description   description   varchar(75) null;
ALTER TABLE xcolab_ContestPhaseRibbonType CHANGE copyOnPromote copy_on_promote tinyint     null;
ALTER TABLE xcolab_ContestPhaseRibbonType CHANGE sortOrder     sort_order     int         null;
rename table xcolab_ContestPhaseRibbonType to contest__contest_phase_ribbon_type;

ALTER TABLE xcolab_ContestPhaseType CHANGE id_                            id                            bigint auto_increment;
ALTER TABLE xcolab_ContestPhaseType CHANGE name                           name                           varchar(1024)       null;
ALTER TABLE xcolab_ContestPhaseType CHANGE description                    description                    longtext            null;
ALTER TABLE xcolab_ContestPhaseType CHANGE status                         status                         varchar(75)         null;
ALTER TABLE xcolab_ContestPhaseType CHANGE fellowScreeningActiveDefault   fellow_screening_active_default   tinyint             null;
ALTER TABLE xcolab_ContestPhaseType CHANGE contestPhaseAutopromoteDefault contest_phase_autopromote_default varchar(75)         null;
ALTER TABLE xcolab_ContestPhaseType CHANGE invisible                      invisible                      tinyint             null;
ALTER TABLE xcolab_ContestPhaseType CHANGE pointsAccessible               points_accessible               int                 null;
ALTER TABLE xcolab_ContestPhaseType CHANGE defaultPromotionType           default_promotion_type           varchar(75)         null;
ALTER TABLE xcolab_ContestPhaseType CHANGE defaultFlagText                default_flag_text                varchar(60)         null;
ALTER TABLE xcolab_ContestPhaseType CHANGE isDeprecated                   is_deprecated                   tinyint default '0' not null;
rename table xcolab_ContestPhaseType to contest__contest_phase_type;

ALTER TABLE xcolab_ContestSchedule CHANGE id_            id            bigint auto_increment;
ALTER TABLE xcolab_ContestSchedule CHANGE name           name           varchar(75) null;
ALTER TABLE xcolab_ContestSchedule CHANGE description    description    varchar(75) null;
ALTER TABLE xcolab_ContestSchedule CHANGE status         status         varchar(75) null;
ALTER TABLE xcolab_ContestSchedule CHANGE baseScheduleId base_schedule_id bigint      null;
rename table xcolab_ContestSchedule to contest__contest_schedule;

ALTER TABLE xcolab_ContestTeamMember CHANGE id_       id       bigint auto_increment;
ALTER TABLE xcolab_ContestTeamMember CHANGE contestId contest_id bigint null;
ALTER TABLE xcolab_ContestTeamMember CHANGE userId    user_id    bigint null;
ALTER TABLE xcolab_ContestTeamMember CHANGE roleId    role_id    bigint null;
rename table xcolab_ContestTeamMember to contest__contest_team_member;

ALTER TABLE xcolab_ContestTeamMemberRole CHANGE id_  id  bigint      not null;
rename table xcolab_ContestTeamMemberRole to contest__contest_team_member_role;

ALTER TABLE xcolab_ContestTranslation CHANGE contestId          contest_id          bigint                                  not null;
ALTER TABLE xcolab_ContestTranslation CHANGE lang               lang               varchar(5)                              not null;
ALTER TABLE xcolab_ContestTranslation CHANGE contestName        question        varchar(255)                            null;
ALTER TABLE xcolab_ContestTranslation CHANGE contestShortName   title   varchar(128)                            null;
ALTER TABLE xcolab_ContestTranslation CHANGE contestDescription description longtext                                null;
ALTER TABLE xcolab_ContestTranslation CHANGE createDate         created_at         timestamp default CURRENT_TIMESTAMP     not null;
ALTER TABLE xcolab_ContestTranslation CHANGE modifiedDate       updated_at       timestamp default CURRENT_TIMESTAMP not null;
ALTER TABLE xcolab_ContestTranslation CHANGE authorId           author_user_id           bigint                                  null;
rename table xcolab_ContestTranslation to contest__contest_translation;

ALTER TABLE xcolab_FocusArea CHANGE id_    id    bigint auto_increment;
ALTER TABLE xcolab_FocusArea CHANGE order_ sort_order int          null;
rename table xcolab_FocusArea to contest__focus_area;

ALTER TABLE xcolab_FocusAreaOntologyTerm CHANGE focusAreaId    focus_area_id    bigint not null;
ALTER TABLE xcolab_FocusAreaOntologyTerm CHANGE ontologyTermId ontology_term_id bigint not null;
ALTER TABLE xcolab_FocusAreaOntologyTerm CHANGE order_         sort_order         int    null;
rename table xcolab_FocusAreaOntologyTerm to contest__focus_area_ontology_term;

ALTER TABLE xcolab_ImpactDefaultSeries CHANGE seriesId    series_id    bigint        not null;
ALTER TABLE xcolab_ImpactDefaultSeries CHANGE focusAreaId focus_area_id bigint        null;
rename table xcolab_ImpactDefaultSeries to contest__impact_default_series;

ALTER TABLE xcolab_ImpactDefaultSeriesData CHANGE seriesId series_id bigint not null;
rename table xcolab_ImpactDefaultSeriesData to contest__impact_default_series_data;

ALTER TABLE xcolab_ImpactIteration CHANGE iterationId iteration_id bigint not null;
rename table xcolab_ImpactIteration to contest__impact_iteration;

ALTER TABLE xcolab_ImpactTemplateFocusAreaList CHANGE focusAreaListId id bigint not null;
rename table xcolab_ImpactTemplateFocusAreaList to contest__impact_template_focus_area_list;

ALTER TABLE xcolab_ImpactTemplateMaxFocusArea CHANGE focusAreaListId focus_area_list_id bigint not null;
ALTER TABLE xcolab_ImpactTemplateMaxFocusArea CHANGE focusAreaId     focus_area_id     bigint not null;
rename table xcolab_ImpactTemplateMaxFocusArea to contest__impact_template_max_focus_area;

ALTER TABLE xcolab_ImpactTemplateSeries CHANGE seriesId    series_id    bigint      not null;
ALTER TABLE xcolab_ImpactTemplateSeries CHANGE iterationId iteration_id bigint      null;
rename table xcolab_ImpactTemplateSeries to contest__impact_template_series;

ALTER TABLE xcolab_OntologySpace CHANGE id_         id         bigint auto_increment;
ALTER TABLE xcolab_OntologySpace CHANGE order_      sort_order      int          null;
rename table xcolab_OntologySpace to contest__ontology_space;

ALTER TABLE xcolab_OntologyTerm CHANGE id_             id             bigint auto_increment;
ALTER TABLE xcolab_OntologyTerm CHANGE parentId        parent_id        bigint        null;
ALTER TABLE xcolab_OntologyTerm CHANGE ontologySpaceId ontology_space_id bigint        null;
ALTER TABLE xcolab_OntologyTerm CHANGE descriptionUrl  description_url  varchar(1024) null;
ALTER TABLE xcolab_OntologyTerm CHANGE order_          sort_order          int           null;
rename table xcolab_OntologyTerm to contest__ontology_term;

ALTER TABLE xcolab_PlanSectionDefinition CHANGE id_                         id                         bigint auto_increment;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE type_                       type                       varchar(75)         null;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE adminTitle                  admin_title                  varchar(1024)       null;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE defaultText                 default_text                 longtext            null;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE helpText                    help_text                    longtext            null;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE characterLimit              character_limit              int                 null;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE focusAreaId                 focus_area_id                 bigint              null;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE allowedContestTypeIds       allowed_contest_type_ids       varchar(75)         null;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE allowedValues               allowed_values               longtext            null;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE additionalIds               additional_ids               varchar(75)         null;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE contestIntegrationRelevance contest_integration_relevance tinyint             null;
ALTER TABLE xcolab_PlanSectionDefinition CHANGE includeInJudgingReport      include_in_judging_report      tinyint default '0' null;
rename table xcolab_PlanSectionDefinition to contest__proposal_template_section_definition;

ALTER TABLE xcolab_PlanTemplate CHANGE id_                     id                     bigint auto_increment;
ALTER TABLE xcolab_PlanTemplate CHANGE baseTemplateId          base_template_id          bigint        null;
ALTER TABLE xcolab_PlanTemplate CHANGE impactSeriesTemplateId  impact_series_template_id  bigint        null;
ALTER TABLE xcolab_PlanTemplate CHANGE focusAreaListTemplateId focus_area_list_template_id bigint        null;
rename table xcolab_PlanTemplate to contest__proposal_template;

ALTER TABLE xcolab_PlanTemplateSection CHANGE planTemplateId id bigint not null;
ALTER TABLE xcolab_PlanTemplateSection CHANGE planSectionId  section_definition_id  bigint not null;
rename table xcolab_PlanTemplateSection to contest__proposal_template_section;

ALTER TABLE xcolab_PointDistributionTarget CHANGE id_               id               bigint not null;
ALTER TABLE xcolab_PointDistributionTarget CHANGE contestId         contest_id         bigint null;
ALTER TABLE xcolab_PointDistributionTarget CHANGE proposalId        proposal_id        bigint null;
ALTER TABLE xcolab_PointDistributionTarget CHANGE numberOfPoints    number_of_points    double null;
ALTER TABLE xcolab_PointDistributionTarget CHANGE pointTypeOverride point_type_override bigint null;
rename table xcolab_PointDistributionTarget to contest__points_distribution_target;

ALTER TABLE xcolab_Points CHANGE id_                   id                   bigint not null;
ALTER TABLE xcolab_Points CHANGE proposalId            proposal_id            bigint null;
ALTER TABLE xcolab_Points CHANGE userId                user_id                bigint null;
ALTER TABLE xcolab_Points CHANGE materializedPoints    materialized_points    double null;
ALTER TABLE xcolab_Points CHANGE hypotheticalPoints    hypothetical_points    double null;
ALTER TABLE xcolab_Points CHANGE pointsSourceId        points_source_id        bigint null;
ALTER TABLE xcolab_Points CHANGE originatingContestPK  originating_contest_p_k  bigint null;
ALTER TABLE xcolab_Points CHANGE originatingProposalId originating_proposal_id bigint null;
rename table xcolab_Points to contest__points;

ALTER TABLE xcolab_PointsDistributionConfiguration CHANGE id_                           id                           bigint auto_increment;
ALTER TABLE xcolab_PointsDistributionConfiguration CHANGE proposalId                    proposal_id                    bigint   null;
ALTER TABLE xcolab_PointsDistributionConfiguration CHANGE pointTypeId                   point_type_id                   bigint   null;
ALTER TABLE xcolab_PointsDistributionConfiguration CHANGE targetUserId                  target_user_id                  bigint   null;
ALTER TABLE xcolab_PointsDistributionConfiguration CHANGE targetSubProposalId           target_sub_proposal_id           bigint   null;
ALTER TABLE xcolab_PointsDistributionConfiguration CHANGE targetPlanSectionDefinitionId target_plan_section_definition_id bigint   null;
ALTER TABLE xcolab_PointsDistributionConfiguration CHANGE creator                       author_user_id                       bigint   null;
ALTER TABLE xcolab_PointsDistributionConfiguration CHANGE createDate                    created_at                    datetime null;
rename table xcolab_PointsDistributionConfiguration to contest__points_distribution_configuration;

ALTER TABLE xcolab_PointType CHANGE id_                        id                        bigint auto_increment;
ALTER TABLE xcolab_PointType CHANGE parentPointTypeId          parent_point_type_id          bigint      null;
ALTER TABLE xcolab_PointType CHANGE percentageOfParent         percentage_of_parent         double      null;
ALTER TABLE xcolab_PointType CHANGE distributionStrategy       distribution_strategy       varchar(75) null;
ALTER TABLE xcolab_PointType CHANGE receiverLimitationStrategy receiver_limitation_strategy varchar(75) null;
ALTER TABLE xcolab_PointType CHANGE sort                       sort_order                       bigint      null;
rename table xcolab_PointType to contest__point_type;

ALTER TABLE xcolab_Proposal CHANGE proposalId          id          bigint auto_increment;
ALTER TABLE xcolab_Proposal CHANGE createDate          created_at          datetime null;
ALTER TABLE xcolab_Proposal CHANGE updatedDate         updated_at         datetime null;
ALTER TABLE xcolab_Proposal CHANGE authorId            author_user_id            bigint   null;
ALTER TABLE xcolab_Proposal CHANGE discussionId        discussion_id        bigint   null;
ALTER TABLE xcolab_Proposal CHANGE resultsDiscussionId results_discussion_id bigint   null;
ALTER TABLE xcolab_Proposal CHANGE groupId             group_id             bigint   null;
rename table xcolab_Proposal to contest__proposal;

ALTER TABLE xcolab_Proposal2Phase CHANGE proposalId           proposal_id           bigint  not null;
ALTER TABLE xcolab_Proposal2Phase CHANGE contestPhaseId       contest_phase_id       bigint  not null;
ALTER TABLE xcolab_Proposal2Phase CHANGE versionFrom          version_from          int     null;
ALTER TABLE xcolab_Proposal2Phase CHANGE versionTo            version_to            int     null;
ALTER TABLE xcolab_Proposal2Phase CHANGE sortWeight           sort_weight           int     null;
ALTER TABLE xcolab_Proposal2Phase CHANGE autopromoteCandidate autopromote_candidate tinyint null;
rename table xcolab_Proposal2Phase to contest__proposal2_phase;

ALTER TABLE xcolab_ProposalAttribute CHANGE id_          id          bigint auto_increment;
ALTER TABLE xcolab_ProposalAttribute CHANGE proposalId   proposal_id   bigint             null;
ALTER TABLE xcolab_ProposalAttribute CHANGE version      version      int                null;
ALTER TABLE xcolab_ProposalAttribute CHANGE additionalId additional_id bigint default '0' not null;
ALTER TABLE xcolab_ProposalAttribute CHANGE numericValue numeric_value bigint             null;
ALTER TABLE xcolab_ProposalAttribute CHANGE stringValue  string_value  longtext           null;
ALTER TABLE xcolab_ProposalAttribute CHANGE realValue    real_value    double             null;
rename table xcolab_ProposalAttribute to contest__proposal_attribute;

ALTER TABLE xcolab_ProposalContestPhaseAttribute CHANGE id_            id            bigint auto_increment;
ALTER TABLE xcolab_ProposalContestPhaseAttribute CHANGE proposalId     proposal_id     bigint      null;
ALTER TABLE xcolab_ProposalContestPhaseAttribute CHANGE contestPhaseId contest_phase_id bigint      null;
ALTER TABLE xcolab_ProposalContestPhaseAttribute CHANGE additionalId   additional_id   bigint      null;
ALTER TABLE xcolab_ProposalContestPhaseAttribute CHANGE numericValue   numeric_value   bigint      null;
ALTER TABLE xcolab_ProposalContestPhaseAttribute CHANGE stringValue    string_value    longtext    null;
ALTER TABLE xcolab_ProposalContestPhaseAttribute CHANGE realValue      real_value      double      null;
rename table xcolab_ProposalContestPhaseAttribute to contest__proposal_contest_phase_attribute;

ALTER TABLE xcolab_ProposalMoveHistory CHANGE id_              id              bigint auto_increment;
ALTER TABLE xcolab_ProposalMoveHistory CHANGE sourceProposalId source_proposal_id bigint      null;
ALTER TABLE xcolab_ProposalMoveHistory CHANGE sourceContestId  source_contest_id  bigint      null;
ALTER TABLE xcolab_ProposalMoveHistory CHANGE sourcePhaseId    source_phase_id    bigint      null;
ALTER TABLE xcolab_ProposalMoveHistory CHANGE targetProposalId target_proposal_id bigint      null;
ALTER TABLE xcolab_ProposalMoveHistory CHANGE targetContestId  target_contest_id  bigint      null;
ALTER TABLE xcolab_ProposalMoveHistory CHANGE targetPhaseId    target_phase_id    bigint      null;
ALTER TABLE xcolab_ProposalMoveHistory CHANGE movingUserId     moving_user_id     bigint      null;
ALTER TABLE xcolab_ProposalMoveHistory CHANGE moveDate         moved_at         datetime    null;
ALTER TABLE xcolab_ProposalMoveHistory CHANGE moveType         move_type         varchar(75) null;
rename table xcolab_ProposalMoveHistory to contest__proposal_move_history;

ALTER TABLE xcolab_ProposalRating CHANGE id_                  id                  bigint auto_increment;
ALTER TABLE xcolab_ProposalRating CHANGE proposalId           proposal_id           bigint      null;
ALTER TABLE xcolab_ProposalRating CHANGE contestPhaseId       contest_phase_id       bigint      null;
ALTER TABLE xcolab_ProposalRating CHANGE userId               user_id               bigint      null;
ALTER TABLE xcolab_ProposalRating CHANGE ratingValueId        rating_value_id        bigint      null;
ALTER TABLE xcolab_ProposalRating CHANGE comment_             comment             longtext    null;
ALTER TABLE xcolab_ProposalRating CHANGE commentEnabled       comment_enabled       tinyint     null;
ALTER TABLE xcolab_ProposalRating CHANGE otherDataString      other_data_string      varchar(75) null;
ALTER TABLE xcolab_ProposalRating CHANGE onlyForInternalUsage only_for_internal_usage tinyint     null;
rename table xcolab_ProposalRating to contest__proposal_rating;

ALTER TABLE xcolab_ProposalRatingType CHANGE id_         id         bigint      not null;
ALTER TABLE xcolab_ProposalRatingType CHANGE judgeType   judge_type   int         null;
ALTER TABLE xcolab_ProposalRatingType CHANGE isActive    is_active    tinyint     null;
rename table xcolab_ProposalRatingType to contest__proposal_rating_type;

ALTER TABLE xcolab_ProposalRatingValue CHANGE id_          id          bigint      not null;
ALTER TABLE xcolab_ProposalRatingValue CHANGE ratingTypeId rating_type_id bigint      null;
rename table xcolab_ProposalRatingValue to contest__proposal_rating_value;

ALTER TABLE xcolab_ProposalReference CHANGE proposalId         proposal_id         bigint not null;
ALTER TABLE xcolab_ProposalReference CHANGE subProposalId      sub_proposal_id      bigint not null;
ALTER TABLE xcolab_ProposalReference CHANGE sectionAttributeId section_attribute_id bigint null;
rename table xcolab_ProposalReference to contest__proposal_reference;

ALTER TABLE xcolab_ProposalSupporter CHANGE proposalId proposal_id bigint   not null;
ALTER TABLE xcolab_ProposalSupporter CHANGE userId     user_id     bigint   not null;
ALTER TABLE xcolab_ProposalSupporter CHANGE createDate created_at datetime null;
rename table xcolab_ProposalSupporter to contest__proposal_supporter;

ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE id_            id            bigint auto_increment;
ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE proposalId     proposal_id     bigint             null;
ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE createAuthorId first_author_user_id bigint             null;
ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE lastAuthorId   last_author_user_id   bigint             null;
ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE createDate     created_at     datetime           null;
ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE lastUpdateDate updated_at datetime           null;
ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE additionalId   additional_id   bigint default '0' not null;
ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE numericValue   numeric_value   bigint             null;
ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE stringValue    string_value    varchar(75)        null;
ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE realValue      real_value      double             null;
rename table xcolab_ProposalUnversionedAttribute to contest__proposal_unversioned_attribute;

ALTER TABLE xcolab_ProposalVersion CHANGE proposalId         proposal_id         bigint      not null;
ALTER TABLE xcolab_ProposalVersion CHANGE authorId           author_user_id           bigint      null;
ALTER TABLE xcolab_ProposalVersion CHANGE createDate         created_at         datetime    null;
ALTER TABLE xcolab_ProposalVersion CHANGE updateType         update_type         varchar(75) null;
ALTER TABLE xcolab_ProposalVersion CHANGE updateAdditionalId update_additional_id bigint      null;
rename table xcolab_ProposalVersion to contest__proposal_version;

ALTER TABLE xcolab_ProposalVote CHANGE proposalId                proposal_id                bigint default '0' not null;
ALTER TABLE xcolab_ProposalVote CHANGE contestPhaseId            contest_phase_id            bigint             not null;
ALTER TABLE xcolab_ProposalVote CHANGE userId                    user_id                    bigint             not null;
ALTER TABLE xcolab_ProposalVote CHANGE value                     value                     int default '1'    null;
ALTER TABLE xcolab_ProposalVote CHANGE createDate                created_at                datetime           null;
ALTER TABLE xcolab_ProposalVote CHANGE voterIp                   voter_ip                   varchar(75)        null;
ALTER TABLE xcolab_ProposalVote CHANGE voterUserAgent            voter_user_agent            varchar(500)       null;
ALTER TABLE xcolab_ProposalVote CHANGE isValid                   is_valid                   tinyint            null;
ALTER TABLE xcolab_ProposalVote CHANGE confirmationEmailSendDate confirmation_email_send_date datetime           null;
ALTER TABLE xcolab_ProposalVote CHANGE confirmationToken         confirmation_token         varchar(75)        null;
ALTER TABLE xcolab_ProposalVote CHANGE initialValidationResult   initial_validation_result   varchar(25)        null;
ALTER TABLE xcolab_ProposalVote CHANGE lastValidationResult      last_validation_result      varchar(25)        null;
ALTER TABLE xcolab_ProposalVote CHANGE manualValidationResult    manual_validation_result    varchar(25)        null;
ALTER TABLE xcolab_ProposalVote CHANGE isValidOverride           is_valid_override           tinyint            null;
rename table xcolab_ProposalVote to contest__proposal_vote;

ALTER TABLE MembershipRequest CHANGE membershipRequestId id bigint auto_increment;
ALTER TABLE MembershipRequest CHANGE userId              user_id              bigint   null;
ALTER TABLE MembershipRequest CHANGE createDate          created_at          datetime null;
ALTER TABLE MembershipRequest CHANGE replyComments       reply_comments       longtext null;
ALTER TABLE MembershipRequest CHANGE replyDate           reply_date           datetime null;
ALTER TABLE MembershipRequest CHANGE replierUserId       replier_user_id       bigint   null;
ALTER TABLE MembershipRequest CHANGE statusId            status_id            int      null;
rename table MembershipRequest to contest__proposal_team_membership_request;
