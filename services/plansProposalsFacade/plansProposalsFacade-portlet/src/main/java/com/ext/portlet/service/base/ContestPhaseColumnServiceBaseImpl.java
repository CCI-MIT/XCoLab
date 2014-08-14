package com.ext.portlet.service.base;

import com.ext.portlet.model.ContestPhaseColumn;
import com.ext.portlet.service.ContestPhaseColumnService;
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
import com.ext.portlet.service.persistence.AnalyticsUserEventPersistence;
import com.ext.portlet.service.persistence.BalloonLinkPersistence;
import com.ext.portlet.service.persistence.BalloonStatsEntryPersistence;
import com.ext.portlet.service.persistence.BalloonTextPersistence;
import com.ext.portlet.service.persistence.BalloonUserTrackingPersistence;
import com.ext.portlet.service.persistence.ContestDebatePersistence;
import com.ext.portlet.service.persistence.ContestEmailTemplatePersistence;
import com.ext.portlet.service.persistence.ContestPersistence;
import com.ext.portlet.service.persistence.ContestPhaseColumnPersistence;
import com.ext.portlet.service.persistence.ContestPhasePersistence;
import com.ext.portlet.service.persistence.ContestPhaseRibbonTypePersistence;
import com.ext.portlet.service.persistence.ContestPhaseTypePersistence;
import com.ext.portlet.service.persistence.ContestTeamMemberPersistence;
import com.ext.portlet.service.persistence.DiscussionCategoryGroupPersistence;
import com.ext.portlet.service.persistence.DiscussionCategoryPersistence;
import com.ext.portlet.service.persistence.DiscussionMessageFlagPersistence;
import com.ext.portlet.service.persistence.DiscussionMessagePersistence;
import com.ext.portlet.service.persistence.EmailListPersistence;
import com.ext.portlet.service.persistence.FocusAreaOntologyTermPersistence;
import com.ext.portlet.service.persistence.FocusAreaPersistence;
import com.ext.portlet.service.persistence.LandingPagePersistence;
import com.ext.portlet.service.persistence.LoginLogPersistence;
import com.ext.portlet.service.persistence.MessagePersistence;
import com.ext.portlet.service.persistence.MessageRecipientStatusPersistence;
import com.ext.portlet.service.persistence.MessagingIgnoredRecipientsPersistence;
import com.ext.portlet.service.persistence.MessagingMessageConversionPersistence;
import com.ext.portlet.service.persistence.MessagingMessageConversionTypePersistence;
import com.ext.portlet.service.persistence.MessagingMessagePersistence;
import com.ext.portlet.service.persistence.MessagingMessageRecipientPersistence;
import com.ext.portlet.service.persistence.MessagingRedirectLinkPersistence;
import com.ext.portlet.service.persistence.MessagingUserPreferencesPersistence;
import com.ext.portlet.service.persistence.ModelCategoryPersistence;
import com.ext.portlet.service.persistence.ModelDiscussionPersistence;
import com.ext.portlet.service.persistence.ModelGlobalPreferencePersistence;
import com.ext.portlet.service.persistence.ModelInputGroupPersistence;
import com.ext.portlet.service.persistence.ModelInputItemPersistence;
import com.ext.portlet.service.persistence.ModelOutputChartOrderPersistence;
import com.ext.portlet.service.persistence.ModelOutputItemPersistence;
import com.ext.portlet.service.persistence.ModelPositionPersistence;
import com.ext.portlet.service.persistence.OntologySpacePersistence;
import com.ext.portlet.service.persistence.OntologyTermEntityPersistence;
import com.ext.portlet.service.persistence.OntologyTermPersistence;
import com.ext.portlet.service.persistence.Plan2ProposalPersistence;
import com.ext.portlet.service.persistence.PlanAttributeFilterPersistence;
import com.ext.portlet.service.persistence.PlanAttributePersistence;
import com.ext.portlet.service.persistence.PlanColumnSettingsPersistence;
import com.ext.portlet.service.persistence.PlanDescriptionPersistence;
import com.ext.portlet.service.persistence.PlanFanPersistence;
import com.ext.portlet.service.persistence.PlanItemFinder;
import com.ext.portlet.service.persistence.PlanItemGroupPersistence;
import com.ext.portlet.service.persistence.PlanItemPersistence;
import com.ext.portlet.service.persistence.PlanMetaPersistence;
import com.ext.portlet.service.persistence.PlanModelRunPersistence;
import com.ext.portlet.service.persistence.PlanPositionItemPersistence;
import com.ext.portlet.service.persistence.PlanPositionPersistence;
import com.ext.portlet.service.persistence.PlanPositionsPersistence;
import com.ext.portlet.service.persistence.PlanPropertyFilterPersistence;
import com.ext.portlet.service.persistence.PlanRelatedPersistence;
import com.ext.portlet.service.persistence.PlanSectionDefinitionPersistence;
import com.ext.portlet.service.persistence.PlanSectionPersistence;
import com.ext.portlet.service.persistence.PlanSectionPlanMapPersistence;
import com.ext.portlet.service.persistence.PlanTeamHistoryPersistence;
import com.ext.portlet.service.persistence.PlanTemplatePersistence;
import com.ext.portlet.service.persistence.PlanTemplateSectionPersistence;
import com.ext.portlet.service.persistence.PlanTypeAttributePersistence;
import com.ext.portlet.service.persistence.PlanTypeColumnPersistence;
import com.ext.portlet.service.persistence.PlanTypePersistence;
import com.ext.portlet.service.persistence.PlanVotePersistence;
import com.ext.portlet.service.persistence.PlansFilterPersistence;
import com.ext.portlet.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.service.persistence.PlansUserSettingsPersistence;
import com.ext.portlet.service.persistence.Proposal2PhasePersistence;
import com.ext.portlet.service.persistence.ProposalAttributePersistence;
import com.ext.portlet.service.persistence.ProposalAttributeTypePersistence;
import com.ext.portlet.service.persistence.ProposalContestPhaseAttributePersistence;
import com.ext.portlet.service.persistence.ProposalContestPhaseAttributeTypePersistence;
import com.ext.portlet.service.persistence.ProposalPersistence;
import com.ext.portlet.service.persistence.ProposalRatingFinder;
import com.ext.portlet.service.persistence.ProposalRatingPersistence;
import com.ext.portlet.service.persistence.ProposalRatingTypePersistence;
import com.ext.portlet.service.persistence.ProposalRatingValuePersistence;
import com.ext.portlet.service.persistence.ProposalSupporterPersistence;
import com.ext.portlet.service.persistence.ProposalVersionPersistence;
import com.ext.portlet.service.persistence.ProposalVotePersistence;
import com.ext.portlet.service.persistence.StaffMemberPersistence;
import com.ext.portlet.service.persistence.TrackedVisitPersistence;
import com.ext.portlet.service.persistence.TrackedVisitor2UserPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.persistence.UserPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the contest phase column remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ext.portlet.service.impl.ContestPhaseColumnServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.impl.ContestPhaseColumnServiceImpl
 * @see com.ext.portlet.service.ContestPhaseColumnServiceUtil
 * @generated
 */
public abstract class ContestPhaseColumnServiceBaseImpl extends BaseServiceImpl
    implements ContestPhaseColumnService, IdentifiableBean {
    @BeanReference(type = com.ext.portlet.service.ActivitySubscriptionLocalService.class)
    protected com.ext.portlet.service.ActivitySubscriptionLocalService activitySubscriptionLocalService;
    @BeanReference(type = com.ext.portlet.service.ActivitySubscriptionService.class)
    protected com.ext.portlet.service.ActivitySubscriptionService activitySubscriptionService;
    @BeanReference(type = ActivitySubscriptionPersistence.class)
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;
    @BeanReference(type = com.ext.portlet.service.AnalyticsUserEventLocalService.class)
    protected com.ext.portlet.service.AnalyticsUserEventLocalService analyticsUserEventLocalService;
    @BeanReference(type = com.ext.portlet.service.AnalyticsUserEventService.class)
    protected com.ext.portlet.service.AnalyticsUserEventService analyticsUserEventService;
    @BeanReference(type = AnalyticsUserEventPersistence.class)
    protected AnalyticsUserEventPersistence analyticsUserEventPersistence;
    @BeanReference(type = com.ext.portlet.service.BalloonLinkLocalService.class)
    protected com.ext.portlet.service.BalloonLinkLocalService balloonLinkLocalService;
    @BeanReference(type = com.ext.portlet.service.BalloonLinkService.class)
    protected com.ext.portlet.service.BalloonLinkService balloonLinkService;
    @BeanReference(type = BalloonLinkPersistence.class)
    protected BalloonLinkPersistence balloonLinkPersistence;
    @BeanReference(type = com.ext.portlet.service.BalloonStatsEntryLocalService.class)
    protected com.ext.portlet.service.BalloonStatsEntryLocalService balloonStatsEntryLocalService;
    @BeanReference(type = com.ext.portlet.service.BalloonStatsEntryService.class)
    protected com.ext.portlet.service.BalloonStatsEntryService balloonStatsEntryService;
    @BeanReference(type = BalloonStatsEntryPersistence.class)
    protected BalloonStatsEntryPersistence balloonStatsEntryPersistence;
    @BeanReference(type = com.ext.portlet.service.BalloonTextLocalService.class)
    protected com.ext.portlet.service.BalloonTextLocalService balloonTextLocalService;
    @BeanReference(type = com.ext.portlet.service.BalloonTextService.class)
    protected com.ext.portlet.service.BalloonTextService balloonTextService;
    @BeanReference(type = BalloonTextPersistence.class)
    protected BalloonTextPersistence balloonTextPersistence;
    @BeanReference(type = com.ext.portlet.service.BalloonUserTrackingLocalService.class)
    protected com.ext.portlet.service.BalloonUserTrackingLocalService balloonUserTrackingLocalService;
    @BeanReference(type = com.ext.portlet.service.BalloonUserTrackingService.class)
    protected com.ext.portlet.service.BalloonUserTrackingService balloonUserTrackingService;
    @BeanReference(type = BalloonUserTrackingPersistence.class)
    protected BalloonUserTrackingPersistence balloonUserTrackingPersistence;
    @BeanReference(type = com.ext.portlet.service.ContestLocalService.class)
    protected com.ext.portlet.service.ContestLocalService contestLocalService;
    @BeanReference(type = com.ext.portlet.service.ContestService.class)
    protected com.ext.portlet.service.ContestService contestService;
    @BeanReference(type = ContestPersistence.class)
    protected ContestPersistence contestPersistence;
    @BeanReference(type = com.ext.portlet.service.ContestDebateLocalService.class)
    protected com.ext.portlet.service.ContestDebateLocalService contestDebateLocalService;
    @BeanReference(type = com.ext.portlet.service.ContestDebateService.class)
    protected com.ext.portlet.service.ContestDebateService contestDebateService;
    @BeanReference(type = ContestDebatePersistence.class)
    protected ContestDebatePersistence contestDebatePersistence;
    @BeanReference(type = com.ext.portlet.service.ContestEmailTemplateLocalService.class)
    protected com.ext.portlet.service.ContestEmailTemplateLocalService contestEmailTemplateLocalService;
    @BeanReference(type = com.ext.portlet.service.ContestEmailTemplateService.class)
    protected com.ext.portlet.service.ContestEmailTemplateService contestEmailTemplateService;
    @BeanReference(type = ContestEmailTemplatePersistence.class)
    protected ContestEmailTemplatePersistence contestEmailTemplatePersistence;
    @BeanReference(type = com.ext.portlet.service.ContestPhaseLocalService.class)
    protected com.ext.portlet.service.ContestPhaseLocalService contestPhaseLocalService;
    @BeanReference(type = com.ext.portlet.service.ContestPhaseService.class)
    protected com.ext.portlet.service.ContestPhaseService contestPhaseService;
    @BeanReference(type = ContestPhasePersistence.class)
    protected ContestPhasePersistence contestPhasePersistence;
    @BeanReference(type = com.ext.portlet.service.ContestPhaseColumnLocalService.class)
    protected com.ext.portlet.service.ContestPhaseColumnLocalService contestPhaseColumnLocalService;
    @BeanReference(type = com.ext.portlet.service.ContestPhaseColumnService.class)
    protected com.ext.portlet.service.ContestPhaseColumnService contestPhaseColumnService;
    @BeanReference(type = ContestPhaseColumnPersistence.class)
    protected ContestPhaseColumnPersistence contestPhaseColumnPersistence;
    @BeanReference(type = com.ext.portlet.service.ContestPhaseRibbonTypeLocalService.class)
    protected com.ext.portlet.service.ContestPhaseRibbonTypeLocalService contestPhaseRibbonTypeLocalService;
    @BeanReference(type = com.ext.portlet.service.ContestPhaseRibbonTypeService.class)
    protected com.ext.portlet.service.ContestPhaseRibbonTypeService contestPhaseRibbonTypeService;
    @BeanReference(type = ContestPhaseRibbonTypePersistence.class)
    protected ContestPhaseRibbonTypePersistence contestPhaseRibbonTypePersistence;
    @BeanReference(type = com.ext.portlet.service.ContestPhaseTypeLocalService.class)
    protected com.ext.portlet.service.ContestPhaseTypeLocalService contestPhaseTypeLocalService;
    @BeanReference(type = com.ext.portlet.service.ContestPhaseTypeService.class)
    protected com.ext.portlet.service.ContestPhaseTypeService contestPhaseTypeService;
    @BeanReference(type = ContestPhaseTypePersistence.class)
    protected ContestPhaseTypePersistence contestPhaseTypePersistence;
    @BeanReference(type = com.ext.portlet.service.ContestTeamMemberLocalService.class)
    protected com.ext.portlet.service.ContestTeamMemberLocalService contestTeamMemberLocalService;
    @BeanReference(type = com.ext.portlet.service.ContestTeamMemberService.class)
    protected com.ext.portlet.service.ContestTeamMemberService contestTeamMemberService;
    @BeanReference(type = ContestTeamMemberPersistence.class)
    protected ContestTeamMemberPersistence contestTeamMemberPersistence;
    @BeanReference(type = com.ext.portlet.service.DiscussionCategoryLocalService.class)
    protected com.ext.portlet.service.DiscussionCategoryLocalService discussionCategoryLocalService;
    @BeanReference(type = com.ext.portlet.service.DiscussionCategoryService.class)
    protected com.ext.portlet.service.DiscussionCategoryService discussionCategoryService;
    @BeanReference(type = DiscussionCategoryPersistence.class)
    protected DiscussionCategoryPersistence discussionCategoryPersistence;
    @BeanReference(type = com.ext.portlet.service.DiscussionCategoryGroupLocalService.class)
    protected com.ext.portlet.service.DiscussionCategoryGroupLocalService discussionCategoryGroupLocalService;
    @BeanReference(type = com.ext.portlet.service.DiscussionCategoryGroupService.class)
    protected com.ext.portlet.service.DiscussionCategoryGroupService discussionCategoryGroupService;
    @BeanReference(type = DiscussionCategoryGroupPersistence.class)
    protected DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence;
    @BeanReference(type = com.ext.portlet.service.DiscussionMessageLocalService.class)
    protected com.ext.portlet.service.DiscussionMessageLocalService discussionMessageLocalService;
    @BeanReference(type = com.ext.portlet.service.DiscussionMessageService.class)
    protected com.ext.portlet.service.DiscussionMessageService discussionMessageService;
    @BeanReference(type = DiscussionMessagePersistence.class)
    protected DiscussionMessagePersistence discussionMessagePersistence;
    @BeanReference(type = com.ext.portlet.service.DiscussionMessageFlagLocalService.class)
    protected com.ext.portlet.service.DiscussionMessageFlagLocalService discussionMessageFlagLocalService;
    @BeanReference(type = com.ext.portlet.service.DiscussionMessageFlagService.class)
    protected com.ext.portlet.service.DiscussionMessageFlagService discussionMessageFlagService;
    @BeanReference(type = DiscussionMessageFlagPersistence.class)
    protected DiscussionMessageFlagPersistence discussionMessageFlagPersistence;
    @BeanReference(type = com.ext.portlet.service.EmailListLocalService.class)
    protected com.ext.portlet.service.EmailListLocalService emailListLocalService;
    @BeanReference(type = com.ext.portlet.service.EmailListService.class)
    protected com.ext.portlet.service.EmailListService emailListService;
    @BeanReference(type = EmailListPersistence.class)
    protected EmailListPersistence emailListPersistence;
    @BeanReference(type = com.ext.portlet.service.FocusAreaLocalService.class)
    protected com.ext.portlet.service.FocusAreaLocalService focusAreaLocalService;
    @BeanReference(type = com.ext.portlet.service.FocusAreaService.class)
    protected com.ext.portlet.service.FocusAreaService focusAreaService;
    @BeanReference(type = FocusAreaPersistence.class)
    protected FocusAreaPersistence focusAreaPersistence;
    @BeanReference(type = com.ext.portlet.service.FocusAreaOntologyTermLocalService.class)
    protected com.ext.portlet.service.FocusAreaOntologyTermLocalService focusAreaOntologyTermLocalService;
    @BeanReference(type = com.ext.portlet.service.FocusAreaOntologyTermService.class)
    protected com.ext.portlet.service.FocusAreaOntologyTermService focusAreaOntologyTermService;
    @BeanReference(type = FocusAreaOntologyTermPersistence.class)
    protected FocusAreaOntologyTermPersistence focusAreaOntologyTermPersistence;
    @BeanReference(type = com.ext.portlet.service.LandingPageLocalService.class)
    protected com.ext.portlet.service.LandingPageLocalService landingPageLocalService;
    @BeanReference(type = com.ext.portlet.service.LandingPageService.class)
    protected com.ext.portlet.service.LandingPageService landingPageService;
    @BeanReference(type = LandingPagePersistence.class)
    protected LandingPagePersistence landingPagePersistence;
    @BeanReference(type = com.ext.portlet.service.LoginLogLocalService.class)
    protected com.ext.portlet.service.LoginLogLocalService loginLogLocalService;
    @BeanReference(type = com.ext.portlet.service.LoginLogService.class)
    protected com.ext.portlet.service.LoginLogService loginLogService;
    @BeanReference(type = LoginLogPersistence.class)
    protected LoginLogPersistence loginLogPersistence;
    @BeanReference(type = com.ext.portlet.service.MessageLocalService.class)
    protected com.ext.portlet.service.MessageLocalService messageLocalService;
    @BeanReference(type = com.ext.portlet.service.MessageService.class)
    protected com.ext.portlet.service.MessageService messageService;
    @BeanReference(type = MessagePersistence.class)
    protected MessagePersistence messagePersistence;
    @BeanReference(type = com.ext.portlet.service.MessageRecipientStatusLocalService.class)
    protected com.ext.portlet.service.MessageRecipientStatusLocalService messageRecipientStatusLocalService;
    @BeanReference(type = com.ext.portlet.service.MessageRecipientStatusService.class)
    protected com.ext.portlet.service.MessageRecipientStatusService messageRecipientStatusService;
    @BeanReference(type = MessageRecipientStatusPersistence.class)
    protected MessageRecipientStatusPersistence messageRecipientStatusPersistence;
    @BeanReference(type = com.ext.portlet.service.MessagingIgnoredRecipientsLocalService.class)
    protected com.ext.portlet.service.MessagingIgnoredRecipientsLocalService messagingIgnoredRecipientsLocalService;
    @BeanReference(type = com.ext.portlet.service.MessagingIgnoredRecipientsService.class)
    protected com.ext.portlet.service.MessagingIgnoredRecipientsService messagingIgnoredRecipientsService;
    @BeanReference(type = MessagingIgnoredRecipientsPersistence.class)
    protected MessagingIgnoredRecipientsPersistence messagingIgnoredRecipientsPersistence;
    @BeanReference(type = com.ext.portlet.service.MessagingMessageLocalService.class)
    protected com.ext.portlet.service.MessagingMessageLocalService messagingMessageLocalService;
    @BeanReference(type = com.ext.portlet.service.MessagingMessageService.class)
    protected com.ext.portlet.service.MessagingMessageService messagingMessageService;
    @BeanReference(type = MessagingMessagePersistence.class)
    protected MessagingMessagePersistence messagingMessagePersistence;
    @BeanReference(type = com.ext.portlet.service.MessagingMessageConversionLocalService.class)
    protected com.ext.portlet.service.MessagingMessageConversionLocalService messagingMessageConversionLocalService;
    @BeanReference(type = com.ext.portlet.service.MessagingMessageConversionService.class)
    protected com.ext.portlet.service.MessagingMessageConversionService messagingMessageConversionService;
    @BeanReference(type = MessagingMessageConversionPersistence.class)
    protected MessagingMessageConversionPersistence messagingMessageConversionPersistence;
    @BeanReference(type = com.ext.portlet.service.MessagingMessageConversionTypeLocalService.class)
    protected com.ext.portlet.service.MessagingMessageConversionTypeLocalService messagingMessageConversionTypeLocalService;
    @BeanReference(type = com.ext.portlet.service.MessagingMessageConversionTypeService.class)
    protected com.ext.portlet.service.MessagingMessageConversionTypeService messagingMessageConversionTypeService;
    @BeanReference(type = MessagingMessageConversionTypePersistence.class)
    protected MessagingMessageConversionTypePersistence messagingMessageConversionTypePersistence;
    @BeanReference(type = com.ext.portlet.service.MessagingMessageRecipientLocalService.class)
    protected com.ext.portlet.service.MessagingMessageRecipientLocalService messagingMessageRecipientLocalService;
    @BeanReference(type = com.ext.portlet.service.MessagingMessageRecipientService.class)
    protected com.ext.portlet.service.MessagingMessageRecipientService messagingMessageRecipientService;
    @BeanReference(type = MessagingMessageRecipientPersistence.class)
    protected MessagingMessageRecipientPersistence messagingMessageRecipientPersistence;
    @BeanReference(type = com.ext.portlet.service.MessagingRedirectLinkLocalService.class)
    protected com.ext.portlet.service.MessagingRedirectLinkLocalService messagingRedirectLinkLocalService;
    @BeanReference(type = com.ext.portlet.service.MessagingRedirectLinkService.class)
    protected com.ext.portlet.service.MessagingRedirectLinkService messagingRedirectLinkService;
    @BeanReference(type = MessagingRedirectLinkPersistence.class)
    protected MessagingRedirectLinkPersistence messagingRedirectLinkPersistence;
    @BeanReference(type = com.ext.portlet.service.MessagingUserPreferencesLocalService.class)
    protected com.ext.portlet.service.MessagingUserPreferencesLocalService messagingUserPreferencesLocalService;
    @BeanReference(type = com.ext.portlet.service.MessagingUserPreferencesService.class)
    protected com.ext.portlet.service.MessagingUserPreferencesService messagingUserPreferencesService;
    @BeanReference(type = MessagingUserPreferencesPersistence.class)
    protected MessagingUserPreferencesPersistence messagingUserPreferencesPersistence;
    @BeanReference(type = com.ext.portlet.service.ModelCategoryLocalService.class)
    protected com.ext.portlet.service.ModelCategoryLocalService modelCategoryLocalService;
    @BeanReference(type = com.ext.portlet.service.ModelCategoryService.class)
    protected com.ext.portlet.service.ModelCategoryService modelCategoryService;
    @BeanReference(type = ModelCategoryPersistence.class)
    protected ModelCategoryPersistence modelCategoryPersistence;
    @BeanReference(type = com.ext.portlet.service.ModelDiscussionLocalService.class)
    protected com.ext.portlet.service.ModelDiscussionLocalService modelDiscussionLocalService;
    @BeanReference(type = com.ext.portlet.service.ModelDiscussionService.class)
    protected com.ext.portlet.service.ModelDiscussionService modelDiscussionService;
    @BeanReference(type = ModelDiscussionPersistence.class)
    protected ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(type = com.ext.portlet.service.ModelGlobalPreferenceLocalService.class)
    protected com.ext.portlet.service.ModelGlobalPreferenceLocalService modelGlobalPreferenceLocalService;
    @BeanReference(type = com.ext.portlet.service.ModelGlobalPreferenceService.class)
    protected com.ext.portlet.service.ModelGlobalPreferenceService modelGlobalPreferenceService;
    @BeanReference(type = ModelGlobalPreferencePersistence.class)
    protected ModelGlobalPreferencePersistence modelGlobalPreferencePersistence;
    @BeanReference(type = com.ext.portlet.service.ModelInputGroupLocalService.class)
    protected com.ext.portlet.service.ModelInputGroupLocalService modelInputGroupLocalService;
    @BeanReference(type = com.ext.portlet.service.ModelInputGroupService.class)
    protected com.ext.portlet.service.ModelInputGroupService modelInputGroupService;
    @BeanReference(type = ModelInputGroupPersistence.class)
    protected ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(type = com.ext.portlet.service.ModelInputItemLocalService.class)
    protected com.ext.portlet.service.ModelInputItemLocalService modelInputItemLocalService;
    @BeanReference(type = com.ext.portlet.service.ModelInputItemService.class)
    protected com.ext.portlet.service.ModelInputItemService modelInputItemService;
    @BeanReference(type = ModelInputItemPersistence.class)
    protected ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(type = com.ext.portlet.service.ModelOutputChartOrderLocalService.class)
    protected com.ext.portlet.service.ModelOutputChartOrderLocalService modelOutputChartOrderLocalService;
    @BeanReference(type = com.ext.portlet.service.ModelOutputChartOrderService.class)
    protected com.ext.portlet.service.ModelOutputChartOrderService modelOutputChartOrderService;
    @BeanReference(type = ModelOutputChartOrderPersistence.class)
    protected ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(type = com.ext.portlet.service.ModelOutputItemLocalService.class)
    protected com.ext.portlet.service.ModelOutputItemLocalService modelOutputItemLocalService;
    @BeanReference(type = com.ext.portlet.service.ModelOutputItemService.class)
    protected com.ext.portlet.service.ModelOutputItemService modelOutputItemService;
    @BeanReference(type = ModelOutputItemPersistence.class)
    protected ModelOutputItemPersistence modelOutputItemPersistence;
    @BeanReference(type = com.ext.portlet.service.ModelPositionLocalService.class)
    protected com.ext.portlet.service.ModelPositionLocalService modelPositionLocalService;
    @BeanReference(type = com.ext.portlet.service.ModelPositionService.class)
    protected com.ext.portlet.service.ModelPositionService modelPositionService;
    @BeanReference(type = ModelPositionPersistence.class)
    protected ModelPositionPersistence modelPositionPersistence;
    @BeanReference(type = com.ext.portlet.service.ModelRunnerLocalService.class)
    protected com.ext.portlet.service.ModelRunnerLocalService modelRunnerLocalService;
    @BeanReference(type = com.ext.portlet.service.ModelRunnerService.class)
    protected com.ext.portlet.service.ModelRunnerService modelRunnerService;
    @BeanReference(type = com.ext.portlet.service.OntologySpaceLocalService.class)
    protected com.ext.portlet.service.OntologySpaceLocalService ontologySpaceLocalService;
    @BeanReference(type = com.ext.portlet.service.OntologySpaceService.class)
    protected com.ext.portlet.service.OntologySpaceService ontologySpaceService;
    @BeanReference(type = OntologySpacePersistence.class)
    protected OntologySpacePersistence ontologySpacePersistence;
    @BeanReference(type = com.ext.portlet.service.OntologyTermLocalService.class)
    protected com.ext.portlet.service.OntologyTermLocalService ontologyTermLocalService;
    @BeanReference(type = com.ext.portlet.service.OntologyTermService.class)
    protected com.ext.portlet.service.OntologyTermService ontologyTermService;
    @BeanReference(type = OntologyTermPersistence.class)
    protected OntologyTermPersistence ontologyTermPersistence;
    @BeanReference(type = com.ext.portlet.service.OntologyTermEntityLocalService.class)
    protected com.ext.portlet.service.OntologyTermEntityLocalService ontologyTermEntityLocalService;
    @BeanReference(type = com.ext.portlet.service.OntologyTermEntityService.class)
    protected com.ext.portlet.service.OntologyTermEntityService ontologyTermEntityService;
    @BeanReference(type = OntologyTermEntityPersistence.class)
    protected OntologyTermEntityPersistence ontologyTermEntityPersistence;
    @BeanReference(type = com.ext.portlet.service.Plan2ProposalLocalService.class)
    protected com.ext.portlet.service.Plan2ProposalLocalService plan2ProposalLocalService;
    @BeanReference(type = com.ext.portlet.service.Plan2ProposalService.class)
    protected com.ext.portlet.service.Plan2ProposalService plan2ProposalService;
    @BeanReference(type = Plan2ProposalPersistence.class)
    protected Plan2ProposalPersistence plan2ProposalPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanAttributeLocalService.class)
    protected com.ext.portlet.service.PlanAttributeLocalService planAttributeLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanAttributeService.class)
    protected com.ext.portlet.service.PlanAttributeService planAttributeService;
    @BeanReference(type = PlanAttributePersistence.class)
    protected PlanAttributePersistence planAttributePersistence;
    @BeanReference(type = com.ext.portlet.service.PlanAttributeFilterLocalService.class)
    protected com.ext.portlet.service.PlanAttributeFilterLocalService planAttributeFilterLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanAttributeFilterService.class)
    protected com.ext.portlet.service.PlanAttributeFilterService planAttributeFilterService;
    @BeanReference(type = PlanAttributeFilterPersistence.class)
    protected PlanAttributeFilterPersistence planAttributeFilterPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanColumnSettingsLocalService.class)
    protected com.ext.portlet.service.PlanColumnSettingsLocalService planColumnSettingsLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanColumnSettingsService.class)
    protected com.ext.portlet.service.PlanColumnSettingsService planColumnSettingsService;
    @BeanReference(type = PlanColumnSettingsPersistence.class)
    protected PlanColumnSettingsPersistence planColumnSettingsPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanDescriptionLocalService.class)
    protected com.ext.portlet.service.PlanDescriptionLocalService planDescriptionLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanDescriptionService.class)
    protected com.ext.portlet.service.PlanDescriptionService planDescriptionService;
    @BeanReference(type = PlanDescriptionPersistence.class)
    protected PlanDescriptionPersistence planDescriptionPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanFanLocalService.class)
    protected com.ext.portlet.service.PlanFanLocalService planFanLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanFanService.class)
    protected com.ext.portlet.service.PlanFanService planFanService;
    @BeanReference(type = PlanFanPersistence.class)
    protected PlanFanPersistence planFanPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanItemLocalService.class)
    protected com.ext.portlet.service.PlanItemLocalService planItemLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanItemService.class)
    protected com.ext.portlet.service.PlanItemService planItemService;
    @BeanReference(type = PlanItemPersistence.class)
    protected PlanItemPersistence planItemPersistence;
    @BeanReference(type = PlanItemFinder.class)
    protected PlanItemFinder planItemFinder;
    @BeanReference(type = com.ext.portlet.service.PlanItemGroupLocalService.class)
    protected com.ext.portlet.service.PlanItemGroupLocalService planItemGroupLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanItemGroupService.class)
    protected com.ext.portlet.service.PlanItemGroupService planItemGroupService;
    @BeanReference(type = PlanItemGroupPersistence.class)
    protected PlanItemGroupPersistence planItemGroupPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanMetaLocalService.class)
    protected com.ext.portlet.service.PlanMetaLocalService planMetaLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanMetaService.class)
    protected com.ext.portlet.service.PlanMetaService planMetaService;
    @BeanReference(type = PlanMetaPersistence.class)
    protected PlanMetaPersistence planMetaPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanModelRunLocalService.class)
    protected com.ext.portlet.service.PlanModelRunLocalService planModelRunLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanModelRunService.class)
    protected com.ext.portlet.service.PlanModelRunService planModelRunService;
    @BeanReference(type = PlanModelRunPersistence.class)
    protected PlanModelRunPersistence planModelRunPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanPositionLocalService.class)
    protected com.ext.portlet.service.PlanPositionLocalService planPositionLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanPositionService.class)
    protected com.ext.portlet.service.PlanPositionService planPositionService;
    @BeanReference(type = PlanPositionPersistence.class)
    protected PlanPositionPersistence planPositionPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanPositionItemLocalService.class)
    protected com.ext.portlet.service.PlanPositionItemLocalService planPositionItemLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanPositionItemService.class)
    protected com.ext.portlet.service.PlanPositionItemService planPositionItemService;
    @BeanReference(type = PlanPositionItemPersistence.class)
    protected PlanPositionItemPersistence planPositionItemPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanPositionsLocalService.class)
    protected com.ext.portlet.service.PlanPositionsLocalService planPositionsLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanPositionsService.class)
    protected com.ext.portlet.service.PlanPositionsService planPositionsService;
    @BeanReference(type = PlanPositionsPersistence.class)
    protected PlanPositionsPersistence planPositionsPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanPropertyFilterLocalService.class)
    protected com.ext.portlet.service.PlanPropertyFilterLocalService planPropertyFilterLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanPropertyFilterService.class)
    protected com.ext.portlet.service.PlanPropertyFilterService planPropertyFilterService;
    @BeanReference(type = PlanPropertyFilterPersistence.class)
    protected PlanPropertyFilterPersistence planPropertyFilterPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanRelatedLocalService.class)
    protected com.ext.portlet.service.PlanRelatedLocalService planRelatedLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanRelatedService.class)
    protected com.ext.portlet.service.PlanRelatedService planRelatedService;
    @BeanReference(type = PlanRelatedPersistence.class)
    protected PlanRelatedPersistence planRelatedPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanSectionLocalService.class)
    protected com.ext.portlet.service.PlanSectionLocalService planSectionLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanSectionService.class)
    protected com.ext.portlet.service.PlanSectionService planSectionService;
    @BeanReference(type = PlanSectionPersistence.class)
    protected PlanSectionPersistence planSectionPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanSectionDefinitionLocalService.class)
    protected com.ext.portlet.service.PlanSectionDefinitionLocalService planSectionDefinitionLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanSectionDefinitionService.class)
    protected com.ext.portlet.service.PlanSectionDefinitionService planSectionDefinitionService;
    @BeanReference(type = PlanSectionDefinitionPersistence.class)
    protected PlanSectionDefinitionPersistence planSectionDefinitionPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanSectionPlanMapLocalService.class)
    protected com.ext.portlet.service.PlanSectionPlanMapLocalService planSectionPlanMapLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanSectionPlanMapService.class)
    protected com.ext.portlet.service.PlanSectionPlanMapService planSectionPlanMapService;
    @BeanReference(type = PlanSectionPlanMapPersistence.class)
    protected PlanSectionPlanMapPersistence planSectionPlanMapPersistence;
    @BeanReference(type = com.ext.portlet.service.PlansFilterLocalService.class)
    protected com.ext.portlet.service.PlansFilterLocalService plansFilterLocalService;
    @BeanReference(type = com.ext.portlet.service.PlansFilterService.class)
    protected com.ext.portlet.service.PlansFilterService plansFilterService;
    @BeanReference(type = PlansFilterPersistence.class)
    protected PlansFilterPersistence plansFilterPersistence;
    @BeanReference(type = com.ext.portlet.service.PlansFilterPositionLocalService.class)
    protected com.ext.portlet.service.PlansFilterPositionLocalService plansFilterPositionLocalService;
    @BeanReference(type = com.ext.portlet.service.PlansFilterPositionService.class)
    protected com.ext.portlet.service.PlansFilterPositionService plansFilterPositionService;
    @BeanReference(type = PlansFilterPositionPersistence.class)
    protected PlansFilterPositionPersistence plansFilterPositionPersistence;
    @BeanReference(type = com.ext.portlet.service.PlansUserSettingsLocalService.class)
    protected com.ext.portlet.service.PlansUserSettingsLocalService plansUserSettingsLocalService;
    @BeanReference(type = com.ext.portlet.service.PlansUserSettingsService.class)
    protected com.ext.portlet.service.PlansUserSettingsService plansUserSettingsService;
    @BeanReference(type = PlansUserSettingsPersistence.class)
    protected PlansUserSettingsPersistence plansUserSettingsPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanTeamHistoryLocalService.class)
    protected com.ext.portlet.service.PlanTeamHistoryLocalService planTeamHistoryLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanTeamHistoryService.class)
    protected com.ext.portlet.service.PlanTeamHistoryService planTeamHistoryService;
    @BeanReference(type = PlanTeamHistoryPersistence.class)
    protected PlanTeamHistoryPersistence planTeamHistoryPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanTemplateLocalService.class)
    protected com.ext.portlet.service.PlanTemplateLocalService planTemplateLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanTemplateService.class)
    protected com.ext.portlet.service.PlanTemplateService planTemplateService;
    @BeanReference(type = PlanTemplatePersistence.class)
    protected PlanTemplatePersistence planTemplatePersistence;
    @BeanReference(type = com.ext.portlet.service.PlanTemplateSectionLocalService.class)
    protected com.ext.portlet.service.PlanTemplateSectionLocalService planTemplateSectionLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanTemplateSectionService.class)
    protected com.ext.portlet.service.PlanTemplateSectionService planTemplateSectionService;
    @BeanReference(type = PlanTemplateSectionPersistence.class)
    protected PlanTemplateSectionPersistence planTemplateSectionPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanTypeLocalService.class)
    protected com.ext.portlet.service.PlanTypeLocalService planTypeLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanTypeService.class)
    protected com.ext.portlet.service.PlanTypeService planTypeService;
    @BeanReference(type = PlanTypePersistence.class)
    protected PlanTypePersistence planTypePersistence;
    @BeanReference(type = com.ext.portlet.service.PlanTypeAttributeLocalService.class)
    protected com.ext.portlet.service.PlanTypeAttributeLocalService planTypeAttributeLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanTypeAttributeService.class)
    protected com.ext.portlet.service.PlanTypeAttributeService planTypeAttributeService;
    @BeanReference(type = PlanTypeAttributePersistence.class)
    protected PlanTypeAttributePersistence planTypeAttributePersistence;
    @BeanReference(type = com.ext.portlet.service.PlanTypeColumnLocalService.class)
    protected com.ext.portlet.service.PlanTypeColumnLocalService planTypeColumnLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanTypeColumnService.class)
    protected com.ext.portlet.service.PlanTypeColumnService planTypeColumnService;
    @BeanReference(type = PlanTypeColumnPersistence.class)
    protected PlanTypeColumnPersistence planTypeColumnPersistence;
    @BeanReference(type = com.ext.portlet.service.PlanVoteLocalService.class)
    protected com.ext.portlet.service.PlanVoteLocalService planVoteLocalService;
    @BeanReference(type = com.ext.portlet.service.PlanVoteService.class)
    protected com.ext.portlet.service.PlanVoteService planVoteService;
    @BeanReference(type = PlanVotePersistence.class)
    protected PlanVotePersistence planVotePersistence;
    @BeanReference(type = com.ext.portlet.service.ProposalLocalService.class)
    protected com.ext.portlet.service.ProposalLocalService proposalLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalService.class)
    protected com.ext.portlet.service.ProposalService proposalService;
    @BeanReference(type = ProposalPersistence.class)
    protected ProposalPersistence proposalPersistence;
    @BeanReference(type = com.ext.portlet.service.Proposal2PhaseLocalService.class)
    protected com.ext.portlet.service.Proposal2PhaseLocalService proposal2PhaseLocalService;
    @BeanReference(type = com.ext.portlet.service.Proposal2PhaseService.class)
    protected com.ext.portlet.service.Proposal2PhaseService proposal2PhaseService;
    @BeanReference(type = Proposal2PhasePersistence.class)
    protected Proposal2PhasePersistence proposal2PhasePersistence;
    @BeanReference(type = com.ext.portlet.service.ProposalAttributeLocalService.class)
    protected com.ext.portlet.service.ProposalAttributeLocalService proposalAttributeLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalAttributeService.class)
    protected com.ext.portlet.service.ProposalAttributeService proposalAttributeService;
    @BeanReference(type = ProposalAttributePersistence.class)
    protected ProposalAttributePersistence proposalAttributePersistence;
    @BeanReference(type = com.ext.portlet.service.ProposalAttributeTypeLocalService.class)
    protected com.ext.portlet.service.ProposalAttributeTypeLocalService proposalAttributeTypeLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalAttributeTypeService.class)
    protected com.ext.portlet.service.ProposalAttributeTypeService proposalAttributeTypeService;
    @BeanReference(type = ProposalAttributeTypePersistence.class)
    protected ProposalAttributeTypePersistence proposalAttributeTypePersistence;
    @BeanReference(type = com.ext.portlet.service.ProposalContestPhaseAttributeLocalService.class)
    protected com.ext.portlet.service.ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalContestPhaseAttributeService.class)
    protected com.ext.portlet.service.ProposalContestPhaseAttributeService proposalContestPhaseAttributeService;
    @BeanReference(type = ProposalContestPhaseAttributePersistence.class)
    protected ProposalContestPhaseAttributePersistence proposalContestPhaseAttributePersistence;
    @BeanReference(type = com.ext.portlet.service.ProposalContestPhaseAttributeTypeLocalService.class)
    protected com.ext.portlet.service.ProposalContestPhaseAttributeTypeLocalService proposalContestPhaseAttributeTypeLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalContestPhaseAttributeTypeService.class)
    protected com.ext.portlet.service.ProposalContestPhaseAttributeTypeService proposalContestPhaseAttributeTypeService;
    @BeanReference(type = ProposalContestPhaseAttributeTypePersistence.class)
    protected ProposalContestPhaseAttributeTypePersistence proposalContestPhaseAttributeTypePersistence;
    @BeanReference(type = com.ext.portlet.service.ProposalRatingLocalService.class)
    protected com.ext.portlet.service.ProposalRatingLocalService proposalRatingLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalRatingService.class)
    protected com.ext.portlet.service.ProposalRatingService proposalRatingService;
    @BeanReference(type = ProposalRatingPersistence.class)
    protected ProposalRatingPersistence proposalRatingPersistence;
    @BeanReference(type = ProposalRatingFinder.class)
    protected ProposalRatingFinder proposalRatingFinder;
    @BeanReference(type = com.ext.portlet.service.ProposalRatingTypeLocalService.class)
    protected com.ext.portlet.service.ProposalRatingTypeLocalService proposalRatingTypeLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalRatingTypeService.class)
    protected com.ext.portlet.service.ProposalRatingTypeService proposalRatingTypeService;
    @BeanReference(type = ProposalRatingTypePersistence.class)
    protected ProposalRatingTypePersistence proposalRatingTypePersistence;
    @BeanReference(type = com.ext.portlet.service.ProposalRatingValueLocalService.class)
    protected com.ext.portlet.service.ProposalRatingValueLocalService proposalRatingValueLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalRatingValueService.class)
    protected com.ext.portlet.service.ProposalRatingValueService proposalRatingValueService;
    @BeanReference(type = ProposalRatingValuePersistence.class)
    protected ProposalRatingValuePersistence proposalRatingValuePersistence;
    @BeanReference(type = com.ext.portlet.service.ProposalSupporterLocalService.class)
    protected com.ext.portlet.service.ProposalSupporterLocalService proposalSupporterLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalSupporterService.class)
    protected com.ext.portlet.service.ProposalSupporterService proposalSupporterService;
    @BeanReference(type = ProposalSupporterPersistence.class)
    protected ProposalSupporterPersistence proposalSupporterPersistence;
    @BeanReference(type = com.ext.portlet.service.ProposalVersionLocalService.class)
    protected com.ext.portlet.service.ProposalVersionLocalService proposalVersionLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalVersionService.class)
    protected com.ext.portlet.service.ProposalVersionService proposalVersionService;
    @BeanReference(type = ProposalVersionPersistence.class)
    protected ProposalVersionPersistence proposalVersionPersistence;
    @BeanReference(type = com.ext.portlet.service.ProposalVoteLocalService.class)
    protected com.ext.portlet.service.ProposalVoteLocalService proposalVoteLocalService;
    @BeanReference(type = com.ext.portlet.service.ProposalVoteService.class)
    protected com.ext.portlet.service.ProposalVoteService proposalVoteService;
    @BeanReference(type = ProposalVotePersistence.class)
    protected ProposalVotePersistence proposalVotePersistence;
    @BeanReference(type = com.ext.portlet.service.StaffMemberLocalService.class)
    protected com.ext.portlet.service.StaffMemberLocalService staffMemberLocalService;
    @BeanReference(type = com.ext.portlet.service.StaffMemberService.class)
    protected com.ext.portlet.service.StaffMemberService staffMemberService;
    @BeanReference(type = StaffMemberPersistence.class)
    protected StaffMemberPersistence staffMemberPersistence;
    @BeanReference(type = com.ext.portlet.service.TrackedVisitLocalService.class)
    protected com.ext.portlet.service.TrackedVisitLocalService trackedVisitLocalService;
    @BeanReference(type = com.ext.portlet.service.TrackedVisitService.class)
    protected com.ext.portlet.service.TrackedVisitService trackedVisitService;
    @BeanReference(type = TrackedVisitPersistence.class)
    protected TrackedVisitPersistence trackedVisitPersistence;
    @BeanReference(type = com.ext.portlet.service.TrackedVisitor2UserLocalService.class)
    protected com.ext.portlet.service.TrackedVisitor2UserLocalService trackedVisitor2UserLocalService;
    @BeanReference(type = com.ext.portlet.service.TrackedVisitor2UserService.class)
    protected com.ext.portlet.service.TrackedVisitor2UserService trackedVisitor2UserService;
    @BeanReference(type = TrackedVisitor2UserPersistence.class)
    protected TrackedVisitor2UserPersistence trackedVisitor2UserPersistence;
    @BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
    protected com.liferay.counter.service.CounterLocalService counterLocalService;
    @BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
    protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
    @BeanReference(type = com.liferay.portal.service.UserLocalService.class)
    protected com.liferay.portal.service.UserLocalService userLocalService;
    @BeanReference(type = com.liferay.portal.service.UserService.class)
    protected com.liferay.portal.service.UserService userService;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;
    private String _beanIdentifier;
    private ClassLoader _classLoader;
    private ContestPhaseColumnServiceClpInvoker _clpInvoker = new ContestPhaseColumnServiceClpInvoker();

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link com.ext.portlet.service.ContestPhaseColumnServiceUtil} to access the contest phase column remote service.
     */

    /**
     * Returns the activity subscription local service.
     *
     * @return the activity subscription local service
     */
    public com.ext.portlet.service.ActivitySubscriptionLocalService getActivitySubscriptionLocalService() {
        return activitySubscriptionLocalService;
    }

    /**
     * Sets the activity subscription local service.
     *
     * @param activitySubscriptionLocalService the activity subscription local service
     */
    public void setActivitySubscriptionLocalService(
        com.ext.portlet.service.ActivitySubscriptionLocalService activitySubscriptionLocalService) {
        this.activitySubscriptionLocalService = activitySubscriptionLocalService;
    }

    /**
     * Returns the activity subscription remote service.
     *
     * @return the activity subscription remote service
     */
    public com.ext.portlet.service.ActivitySubscriptionService getActivitySubscriptionService() {
        return activitySubscriptionService;
    }

    /**
     * Sets the activity subscription remote service.
     *
     * @param activitySubscriptionService the activity subscription remote service
     */
    public void setActivitySubscriptionService(
        com.ext.portlet.service.ActivitySubscriptionService activitySubscriptionService) {
        this.activitySubscriptionService = activitySubscriptionService;
    }

    /**
     * Returns the activity subscription persistence.
     *
     * @return the activity subscription persistence
     */
    public ActivitySubscriptionPersistence getActivitySubscriptionPersistence() {
        return activitySubscriptionPersistence;
    }

    /**
     * Sets the activity subscription persistence.
     *
     * @param activitySubscriptionPersistence the activity subscription persistence
     */
    public void setActivitySubscriptionPersistence(
        ActivitySubscriptionPersistence activitySubscriptionPersistence) {
        this.activitySubscriptionPersistence = activitySubscriptionPersistence;
    }

    /**
     * Returns the analytics user event local service.
     *
     * @return the analytics user event local service
     */
    public com.ext.portlet.service.AnalyticsUserEventLocalService getAnalyticsUserEventLocalService() {
        return analyticsUserEventLocalService;
    }

    /**
     * Sets the analytics user event local service.
     *
     * @param analyticsUserEventLocalService the analytics user event local service
     */
    public void setAnalyticsUserEventLocalService(
        com.ext.portlet.service.AnalyticsUserEventLocalService analyticsUserEventLocalService) {
        this.analyticsUserEventLocalService = analyticsUserEventLocalService;
    }

    /**
     * Returns the analytics user event remote service.
     *
     * @return the analytics user event remote service
     */
    public com.ext.portlet.service.AnalyticsUserEventService getAnalyticsUserEventService() {
        return analyticsUserEventService;
    }

    /**
     * Sets the analytics user event remote service.
     *
     * @param analyticsUserEventService the analytics user event remote service
     */
    public void setAnalyticsUserEventService(
        com.ext.portlet.service.AnalyticsUserEventService analyticsUserEventService) {
        this.analyticsUserEventService = analyticsUserEventService;
    }

    /**
     * Returns the analytics user event persistence.
     *
     * @return the analytics user event persistence
     */
    public AnalyticsUserEventPersistence getAnalyticsUserEventPersistence() {
        return analyticsUserEventPersistence;
    }

    /**
     * Sets the analytics user event persistence.
     *
     * @param analyticsUserEventPersistence the analytics user event persistence
     */
    public void setAnalyticsUserEventPersistence(
        AnalyticsUserEventPersistence analyticsUserEventPersistence) {
        this.analyticsUserEventPersistence = analyticsUserEventPersistence;
    }

    /**
     * Returns the balloon link local service.
     *
     * @return the balloon link local service
     */
    public com.ext.portlet.service.BalloonLinkLocalService getBalloonLinkLocalService() {
        return balloonLinkLocalService;
    }

    /**
     * Sets the balloon link local service.
     *
     * @param balloonLinkLocalService the balloon link local service
     */
    public void setBalloonLinkLocalService(
        com.ext.portlet.service.BalloonLinkLocalService balloonLinkLocalService) {
        this.balloonLinkLocalService = balloonLinkLocalService;
    }

    /**
     * Returns the balloon link remote service.
     *
     * @return the balloon link remote service
     */
    public com.ext.portlet.service.BalloonLinkService getBalloonLinkService() {
        return balloonLinkService;
    }

    /**
     * Sets the balloon link remote service.
     *
     * @param balloonLinkService the balloon link remote service
     */
    public void setBalloonLinkService(
        com.ext.portlet.service.BalloonLinkService balloonLinkService) {
        this.balloonLinkService = balloonLinkService;
    }

    /**
     * Returns the balloon link persistence.
     *
     * @return the balloon link persistence
     */
    public BalloonLinkPersistence getBalloonLinkPersistence() {
        return balloonLinkPersistence;
    }

    /**
     * Sets the balloon link persistence.
     *
     * @param balloonLinkPersistence the balloon link persistence
     */
    public void setBalloonLinkPersistence(
        BalloonLinkPersistence balloonLinkPersistence) {
        this.balloonLinkPersistence = balloonLinkPersistence;
    }

    /**
     * Returns the balloon stats entry local service.
     *
     * @return the balloon stats entry local service
     */
    public com.ext.portlet.service.BalloonStatsEntryLocalService getBalloonStatsEntryLocalService() {
        return balloonStatsEntryLocalService;
    }

    /**
     * Sets the balloon stats entry local service.
     *
     * @param balloonStatsEntryLocalService the balloon stats entry local service
     */
    public void setBalloonStatsEntryLocalService(
        com.ext.portlet.service.BalloonStatsEntryLocalService balloonStatsEntryLocalService) {
        this.balloonStatsEntryLocalService = balloonStatsEntryLocalService;
    }

    /**
     * Returns the balloon stats entry remote service.
     *
     * @return the balloon stats entry remote service
     */
    public com.ext.portlet.service.BalloonStatsEntryService getBalloonStatsEntryService() {
        return balloonStatsEntryService;
    }

    /**
     * Sets the balloon stats entry remote service.
     *
     * @param balloonStatsEntryService the balloon stats entry remote service
     */
    public void setBalloonStatsEntryService(
        com.ext.portlet.service.BalloonStatsEntryService balloonStatsEntryService) {
        this.balloonStatsEntryService = balloonStatsEntryService;
    }

    /**
     * Returns the balloon stats entry persistence.
     *
     * @return the balloon stats entry persistence
     */
    public BalloonStatsEntryPersistence getBalloonStatsEntryPersistence() {
        return balloonStatsEntryPersistence;
    }

    /**
     * Sets the balloon stats entry persistence.
     *
     * @param balloonStatsEntryPersistence the balloon stats entry persistence
     */
    public void setBalloonStatsEntryPersistence(
        BalloonStatsEntryPersistence balloonStatsEntryPersistence) {
        this.balloonStatsEntryPersistence = balloonStatsEntryPersistence;
    }

    /**
     * Returns the balloon text local service.
     *
     * @return the balloon text local service
     */
    public com.ext.portlet.service.BalloonTextLocalService getBalloonTextLocalService() {
        return balloonTextLocalService;
    }

    /**
     * Sets the balloon text local service.
     *
     * @param balloonTextLocalService the balloon text local service
     */
    public void setBalloonTextLocalService(
        com.ext.portlet.service.BalloonTextLocalService balloonTextLocalService) {
        this.balloonTextLocalService = balloonTextLocalService;
    }

    /**
     * Returns the balloon text remote service.
     *
     * @return the balloon text remote service
     */
    public com.ext.portlet.service.BalloonTextService getBalloonTextService() {
        return balloonTextService;
    }

    /**
     * Sets the balloon text remote service.
     *
     * @param balloonTextService the balloon text remote service
     */
    public void setBalloonTextService(
        com.ext.portlet.service.BalloonTextService balloonTextService) {
        this.balloonTextService = balloonTextService;
    }

    /**
     * Returns the balloon text persistence.
     *
     * @return the balloon text persistence
     */
    public BalloonTextPersistence getBalloonTextPersistence() {
        return balloonTextPersistence;
    }

    /**
     * Sets the balloon text persistence.
     *
     * @param balloonTextPersistence the balloon text persistence
     */
    public void setBalloonTextPersistence(
        BalloonTextPersistence balloonTextPersistence) {
        this.balloonTextPersistence = balloonTextPersistence;
    }

    /**
     * Returns the balloon user tracking local service.
     *
     * @return the balloon user tracking local service
     */
    public com.ext.portlet.service.BalloonUserTrackingLocalService getBalloonUserTrackingLocalService() {
        return balloonUserTrackingLocalService;
    }

    /**
     * Sets the balloon user tracking local service.
     *
     * @param balloonUserTrackingLocalService the balloon user tracking local service
     */
    public void setBalloonUserTrackingLocalService(
        com.ext.portlet.service.BalloonUserTrackingLocalService balloonUserTrackingLocalService) {
        this.balloonUserTrackingLocalService = balloonUserTrackingLocalService;
    }

    /**
     * Returns the balloon user tracking remote service.
     *
     * @return the balloon user tracking remote service
     */
    public com.ext.portlet.service.BalloonUserTrackingService getBalloonUserTrackingService() {
        return balloonUserTrackingService;
    }

    /**
     * Sets the balloon user tracking remote service.
     *
     * @param balloonUserTrackingService the balloon user tracking remote service
     */
    public void setBalloonUserTrackingService(
        com.ext.portlet.service.BalloonUserTrackingService balloonUserTrackingService) {
        this.balloonUserTrackingService = balloonUserTrackingService;
    }

    /**
     * Returns the balloon user tracking persistence.
     *
     * @return the balloon user tracking persistence
     */
    public BalloonUserTrackingPersistence getBalloonUserTrackingPersistence() {
        return balloonUserTrackingPersistence;
    }

    /**
     * Sets the balloon user tracking persistence.
     *
     * @param balloonUserTrackingPersistence the balloon user tracking persistence
     */
    public void setBalloonUserTrackingPersistence(
        BalloonUserTrackingPersistence balloonUserTrackingPersistence) {
        this.balloonUserTrackingPersistence = balloonUserTrackingPersistence;
    }

    /**
     * Returns the contest local service.
     *
     * @return the contest local service
     */
    public com.ext.portlet.service.ContestLocalService getContestLocalService() {
        return contestLocalService;
    }

    /**
     * Sets the contest local service.
     *
     * @param contestLocalService the contest local service
     */
    public void setContestLocalService(
        com.ext.portlet.service.ContestLocalService contestLocalService) {
        this.contestLocalService = contestLocalService;
    }

    /**
     * Returns the contest remote service.
     *
     * @return the contest remote service
     */
    public com.ext.portlet.service.ContestService getContestService() {
        return contestService;
    }

    /**
     * Sets the contest remote service.
     *
     * @param contestService the contest remote service
     */
    public void setContestService(
        com.ext.portlet.service.ContestService contestService) {
        this.contestService = contestService;
    }

    /**
     * Returns the contest persistence.
     *
     * @return the contest persistence
     */
    public ContestPersistence getContestPersistence() {
        return contestPersistence;
    }

    /**
     * Sets the contest persistence.
     *
     * @param contestPersistence the contest persistence
     */
    public void setContestPersistence(ContestPersistence contestPersistence) {
        this.contestPersistence = contestPersistence;
    }

    /**
     * Returns the contest debate local service.
     *
     * @return the contest debate local service
     */
    public com.ext.portlet.service.ContestDebateLocalService getContestDebateLocalService() {
        return contestDebateLocalService;
    }

    /**
     * Sets the contest debate local service.
     *
     * @param contestDebateLocalService the contest debate local service
     */
    public void setContestDebateLocalService(
        com.ext.portlet.service.ContestDebateLocalService contestDebateLocalService) {
        this.contestDebateLocalService = contestDebateLocalService;
    }

    /**
     * Returns the contest debate remote service.
     *
     * @return the contest debate remote service
     */
    public com.ext.portlet.service.ContestDebateService getContestDebateService() {
        return contestDebateService;
    }

    /**
     * Sets the contest debate remote service.
     *
     * @param contestDebateService the contest debate remote service
     */
    public void setContestDebateService(
        com.ext.portlet.service.ContestDebateService contestDebateService) {
        this.contestDebateService = contestDebateService;
    }

    /**
     * Returns the contest debate persistence.
     *
     * @return the contest debate persistence
     */
    public ContestDebatePersistence getContestDebatePersistence() {
        return contestDebatePersistence;
    }

    /**
     * Sets the contest debate persistence.
     *
     * @param contestDebatePersistence the contest debate persistence
     */
    public void setContestDebatePersistence(
        ContestDebatePersistence contestDebatePersistence) {
        this.contestDebatePersistence = contestDebatePersistence;
    }

    /**
     * Returns the contest email template local service.
     *
     * @return the contest email template local service
     */
    public com.ext.portlet.service.ContestEmailTemplateLocalService getContestEmailTemplateLocalService() {
        return contestEmailTemplateLocalService;
    }

    /**
     * Sets the contest email template local service.
     *
     * @param contestEmailTemplateLocalService the contest email template local service
     */
    public void setContestEmailTemplateLocalService(
        com.ext.portlet.service.ContestEmailTemplateLocalService contestEmailTemplateLocalService) {
        this.contestEmailTemplateLocalService = contestEmailTemplateLocalService;
    }

    /**
     * Returns the contest email template remote service.
     *
     * @return the contest email template remote service
     */
    public com.ext.portlet.service.ContestEmailTemplateService getContestEmailTemplateService() {
        return contestEmailTemplateService;
    }

    /**
     * Sets the contest email template remote service.
     *
     * @param contestEmailTemplateService the contest email template remote service
     */
    public void setContestEmailTemplateService(
        com.ext.portlet.service.ContestEmailTemplateService contestEmailTemplateService) {
        this.contestEmailTemplateService = contestEmailTemplateService;
    }

    /**
     * Returns the contest email template persistence.
     *
     * @return the contest email template persistence
     */
    public ContestEmailTemplatePersistence getContestEmailTemplatePersistence() {
        return contestEmailTemplatePersistence;
    }

    /**
     * Sets the contest email template persistence.
     *
     * @param contestEmailTemplatePersistence the contest email template persistence
     */
    public void setContestEmailTemplatePersistence(
        ContestEmailTemplatePersistence contestEmailTemplatePersistence) {
        this.contestEmailTemplatePersistence = contestEmailTemplatePersistence;
    }

    /**
     * Returns the contest phase local service.
     *
     * @return the contest phase local service
     */
    public com.ext.portlet.service.ContestPhaseLocalService getContestPhaseLocalService() {
        return contestPhaseLocalService;
    }

    /**
     * Sets the contest phase local service.
     *
     * @param contestPhaseLocalService the contest phase local service
     */
    public void setContestPhaseLocalService(
        com.ext.portlet.service.ContestPhaseLocalService contestPhaseLocalService) {
        this.contestPhaseLocalService = contestPhaseLocalService;
    }

    /**
     * Returns the contest phase remote service.
     *
     * @return the contest phase remote service
     */
    public com.ext.portlet.service.ContestPhaseService getContestPhaseService() {
        return contestPhaseService;
    }

    /**
     * Sets the contest phase remote service.
     *
     * @param contestPhaseService the contest phase remote service
     */
    public void setContestPhaseService(
        com.ext.portlet.service.ContestPhaseService contestPhaseService) {
        this.contestPhaseService = contestPhaseService;
    }

    /**
     * Returns the contest phase persistence.
     *
     * @return the contest phase persistence
     */
    public ContestPhasePersistence getContestPhasePersistence() {
        return contestPhasePersistence;
    }

    /**
     * Sets the contest phase persistence.
     *
     * @param contestPhasePersistence the contest phase persistence
     */
    public void setContestPhasePersistence(
        ContestPhasePersistence contestPhasePersistence) {
        this.contestPhasePersistence = contestPhasePersistence;
    }

    /**
     * Returns the contest phase column local service.
     *
     * @return the contest phase column local service
     */
    public com.ext.portlet.service.ContestPhaseColumnLocalService getContestPhaseColumnLocalService() {
        return contestPhaseColumnLocalService;
    }

    /**
     * Sets the contest phase column local service.
     *
     * @param contestPhaseColumnLocalService the contest phase column local service
     */
    public void setContestPhaseColumnLocalService(
        com.ext.portlet.service.ContestPhaseColumnLocalService contestPhaseColumnLocalService) {
        this.contestPhaseColumnLocalService = contestPhaseColumnLocalService;
    }

    /**
     * Returns the contest phase column remote service.
     *
     * @return the contest phase column remote service
     */
    public com.ext.portlet.service.ContestPhaseColumnService getContestPhaseColumnService() {
        return contestPhaseColumnService;
    }

    /**
     * Sets the contest phase column remote service.
     *
     * @param contestPhaseColumnService the contest phase column remote service
     */
    public void setContestPhaseColumnService(
        com.ext.portlet.service.ContestPhaseColumnService contestPhaseColumnService) {
        this.contestPhaseColumnService = contestPhaseColumnService;
    }

    /**
     * Returns the contest phase column persistence.
     *
     * @return the contest phase column persistence
     */
    public ContestPhaseColumnPersistence getContestPhaseColumnPersistence() {
        return contestPhaseColumnPersistence;
    }

    /**
     * Sets the contest phase column persistence.
     *
     * @param contestPhaseColumnPersistence the contest phase column persistence
     */
    public void setContestPhaseColumnPersistence(
        ContestPhaseColumnPersistence contestPhaseColumnPersistence) {
        this.contestPhaseColumnPersistence = contestPhaseColumnPersistence;
    }

    /**
     * Returns the contest phase ribbon type local service.
     *
     * @return the contest phase ribbon type local service
     */
    public com.ext.portlet.service.ContestPhaseRibbonTypeLocalService getContestPhaseRibbonTypeLocalService() {
        return contestPhaseRibbonTypeLocalService;
    }

    /**
     * Sets the contest phase ribbon type local service.
     *
     * @param contestPhaseRibbonTypeLocalService the contest phase ribbon type local service
     */
    public void setContestPhaseRibbonTypeLocalService(
        com.ext.portlet.service.ContestPhaseRibbonTypeLocalService contestPhaseRibbonTypeLocalService) {
        this.contestPhaseRibbonTypeLocalService = contestPhaseRibbonTypeLocalService;
    }

    /**
     * Returns the contest phase ribbon type remote service.
     *
     * @return the contest phase ribbon type remote service
     */
    public com.ext.portlet.service.ContestPhaseRibbonTypeService getContestPhaseRibbonTypeService() {
        return contestPhaseRibbonTypeService;
    }

    /**
     * Sets the contest phase ribbon type remote service.
     *
     * @param contestPhaseRibbonTypeService the contest phase ribbon type remote service
     */
    public void setContestPhaseRibbonTypeService(
        com.ext.portlet.service.ContestPhaseRibbonTypeService contestPhaseRibbonTypeService) {
        this.contestPhaseRibbonTypeService = contestPhaseRibbonTypeService;
    }

    /**
     * Returns the contest phase ribbon type persistence.
     *
     * @return the contest phase ribbon type persistence
     */
    public ContestPhaseRibbonTypePersistence getContestPhaseRibbonTypePersistence() {
        return contestPhaseRibbonTypePersistence;
    }

    /**
     * Sets the contest phase ribbon type persistence.
     *
     * @param contestPhaseRibbonTypePersistence the contest phase ribbon type persistence
     */
    public void setContestPhaseRibbonTypePersistence(
        ContestPhaseRibbonTypePersistence contestPhaseRibbonTypePersistence) {
        this.contestPhaseRibbonTypePersistence = contestPhaseRibbonTypePersistence;
    }

    /**
     * Returns the contest phase type local service.
     *
     * @return the contest phase type local service
     */
    public com.ext.portlet.service.ContestPhaseTypeLocalService getContestPhaseTypeLocalService() {
        return contestPhaseTypeLocalService;
    }

    /**
     * Sets the contest phase type local service.
     *
     * @param contestPhaseTypeLocalService the contest phase type local service
     */
    public void setContestPhaseTypeLocalService(
        com.ext.portlet.service.ContestPhaseTypeLocalService contestPhaseTypeLocalService) {
        this.contestPhaseTypeLocalService = contestPhaseTypeLocalService;
    }

    /**
     * Returns the contest phase type remote service.
     *
     * @return the contest phase type remote service
     */
    public com.ext.portlet.service.ContestPhaseTypeService getContestPhaseTypeService() {
        return contestPhaseTypeService;
    }

    /**
     * Sets the contest phase type remote service.
     *
     * @param contestPhaseTypeService the contest phase type remote service
     */
    public void setContestPhaseTypeService(
        com.ext.portlet.service.ContestPhaseTypeService contestPhaseTypeService) {
        this.contestPhaseTypeService = contestPhaseTypeService;
    }

    /**
     * Returns the contest phase type persistence.
     *
     * @return the contest phase type persistence
     */
    public ContestPhaseTypePersistence getContestPhaseTypePersistence() {
        return contestPhaseTypePersistence;
    }

    /**
     * Sets the contest phase type persistence.
     *
     * @param contestPhaseTypePersistence the contest phase type persistence
     */
    public void setContestPhaseTypePersistence(
        ContestPhaseTypePersistence contestPhaseTypePersistence) {
        this.contestPhaseTypePersistence = contestPhaseTypePersistence;
    }

    /**
     * Returns the contest team member local service.
     *
     * @return the contest team member local service
     */
    public com.ext.portlet.service.ContestTeamMemberLocalService getContestTeamMemberLocalService() {
        return contestTeamMemberLocalService;
    }

    /**
     * Sets the contest team member local service.
     *
     * @param contestTeamMemberLocalService the contest team member local service
     */
    public void setContestTeamMemberLocalService(
        com.ext.portlet.service.ContestTeamMemberLocalService contestTeamMemberLocalService) {
        this.contestTeamMemberLocalService = contestTeamMemberLocalService;
    }

    /**
     * Returns the contest team member remote service.
     *
     * @return the contest team member remote service
     */
    public com.ext.portlet.service.ContestTeamMemberService getContestTeamMemberService() {
        return contestTeamMemberService;
    }

    /**
     * Sets the contest team member remote service.
     *
     * @param contestTeamMemberService the contest team member remote service
     */
    public void setContestTeamMemberService(
        com.ext.portlet.service.ContestTeamMemberService contestTeamMemberService) {
        this.contestTeamMemberService = contestTeamMemberService;
    }

    /**
     * Returns the contest team member persistence.
     *
     * @return the contest team member persistence
     */
    public ContestTeamMemberPersistence getContestTeamMemberPersistence() {
        return contestTeamMemberPersistence;
    }

    /**
     * Sets the contest team member persistence.
     *
     * @param contestTeamMemberPersistence the contest team member persistence
     */
    public void setContestTeamMemberPersistence(
        ContestTeamMemberPersistence contestTeamMemberPersistence) {
        this.contestTeamMemberPersistence = contestTeamMemberPersistence;
    }

    /**
     * Returns the discussion category local service.
     *
     * @return the discussion category local service
     */
    public com.ext.portlet.service.DiscussionCategoryLocalService getDiscussionCategoryLocalService() {
        return discussionCategoryLocalService;
    }

    /**
     * Sets the discussion category local service.
     *
     * @param discussionCategoryLocalService the discussion category local service
     */
    public void setDiscussionCategoryLocalService(
        com.ext.portlet.service.DiscussionCategoryLocalService discussionCategoryLocalService) {
        this.discussionCategoryLocalService = discussionCategoryLocalService;
    }

    /**
     * Returns the discussion category remote service.
     *
     * @return the discussion category remote service
     */
    public com.ext.portlet.service.DiscussionCategoryService getDiscussionCategoryService() {
        return discussionCategoryService;
    }

    /**
     * Sets the discussion category remote service.
     *
     * @param discussionCategoryService the discussion category remote service
     */
    public void setDiscussionCategoryService(
        com.ext.portlet.service.DiscussionCategoryService discussionCategoryService) {
        this.discussionCategoryService = discussionCategoryService;
    }

    /**
     * Returns the discussion category persistence.
     *
     * @return the discussion category persistence
     */
    public DiscussionCategoryPersistence getDiscussionCategoryPersistence() {
        return discussionCategoryPersistence;
    }

    /**
     * Sets the discussion category persistence.
     *
     * @param discussionCategoryPersistence the discussion category persistence
     */
    public void setDiscussionCategoryPersistence(
        DiscussionCategoryPersistence discussionCategoryPersistence) {
        this.discussionCategoryPersistence = discussionCategoryPersistence;
    }

    /**
     * Returns the discussion category group local service.
     *
     * @return the discussion category group local service
     */
    public com.ext.portlet.service.DiscussionCategoryGroupLocalService getDiscussionCategoryGroupLocalService() {
        return discussionCategoryGroupLocalService;
    }

    /**
     * Sets the discussion category group local service.
     *
     * @param discussionCategoryGroupLocalService the discussion category group local service
     */
    public void setDiscussionCategoryGroupLocalService(
        com.ext.portlet.service.DiscussionCategoryGroupLocalService discussionCategoryGroupLocalService) {
        this.discussionCategoryGroupLocalService = discussionCategoryGroupLocalService;
    }

    /**
     * Returns the discussion category group remote service.
     *
     * @return the discussion category group remote service
     */
    public com.ext.portlet.service.DiscussionCategoryGroupService getDiscussionCategoryGroupService() {
        return discussionCategoryGroupService;
    }

    /**
     * Sets the discussion category group remote service.
     *
     * @param discussionCategoryGroupService the discussion category group remote service
     */
    public void setDiscussionCategoryGroupService(
        com.ext.portlet.service.DiscussionCategoryGroupService discussionCategoryGroupService) {
        this.discussionCategoryGroupService = discussionCategoryGroupService;
    }

    /**
     * Returns the discussion category group persistence.
     *
     * @return the discussion category group persistence
     */
    public DiscussionCategoryGroupPersistence getDiscussionCategoryGroupPersistence() {
        return discussionCategoryGroupPersistence;
    }

    /**
     * Sets the discussion category group persistence.
     *
     * @param discussionCategoryGroupPersistence the discussion category group persistence
     */
    public void setDiscussionCategoryGroupPersistence(
        DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence) {
        this.discussionCategoryGroupPersistence = discussionCategoryGroupPersistence;
    }

    /**
     * Returns the discussion message local service.
     *
     * @return the discussion message local service
     */
    public com.ext.portlet.service.DiscussionMessageLocalService getDiscussionMessageLocalService() {
        return discussionMessageLocalService;
    }

    /**
     * Sets the discussion message local service.
     *
     * @param discussionMessageLocalService the discussion message local service
     */
    public void setDiscussionMessageLocalService(
        com.ext.portlet.service.DiscussionMessageLocalService discussionMessageLocalService) {
        this.discussionMessageLocalService = discussionMessageLocalService;
    }

    /**
     * Returns the discussion message remote service.
     *
     * @return the discussion message remote service
     */
    public com.ext.portlet.service.DiscussionMessageService getDiscussionMessageService() {
        return discussionMessageService;
    }

    /**
     * Sets the discussion message remote service.
     *
     * @param discussionMessageService the discussion message remote service
     */
    public void setDiscussionMessageService(
        com.ext.portlet.service.DiscussionMessageService discussionMessageService) {
        this.discussionMessageService = discussionMessageService;
    }

    /**
     * Returns the discussion message persistence.
     *
     * @return the discussion message persistence
     */
    public DiscussionMessagePersistence getDiscussionMessagePersistence() {
        return discussionMessagePersistence;
    }

    /**
     * Sets the discussion message persistence.
     *
     * @param discussionMessagePersistence the discussion message persistence
     */
    public void setDiscussionMessagePersistence(
        DiscussionMessagePersistence discussionMessagePersistence) {
        this.discussionMessagePersistence = discussionMessagePersistence;
    }

    /**
     * Returns the discussion message flag local service.
     *
     * @return the discussion message flag local service
     */
    public com.ext.portlet.service.DiscussionMessageFlagLocalService getDiscussionMessageFlagLocalService() {
        return discussionMessageFlagLocalService;
    }

    /**
     * Sets the discussion message flag local service.
     *
     * @param discussionMessageFlagLocalService the discussion message flag local service
     */
    public void setDiscussionMessageFlagLocalService(
        com.ext.portlet.service.DiscussionMessageFlagLocalService discussionMessageFlagLocalService) {
        this.discussionMessageFlagLocalService = discussionMessageFlagLocalService;
    }

    /**
     * Returns the discussion message flag remote service.
     *
     * @return the discussion message flag remote service
     */
    public com.ext.portlet.service.DiscussionMessageFlagService getDiscussionMessageFlagService() {
        return discussionMessageFlagService;
    }

    /**
     * Sets the discussion message flag remote service.
     *
     * @param discussionMessageFlagService the discussion message flag remote service
     */
    public void setDiscussionMessageFlagService(
        com.ext.portlet.service.DiscussionMessageFlagService discussionMessageFlagService) {
        this.discussionMessageFlagService = discussionMessageFlagService;
    }

    /**
     * Returns the discussion message flag persistence.
     *
     * @return the discussion message flag persistence
     */
    public DiscussionMessageFlagPersistence getDiscussionMessageFlagPersistence() {
        return discussionMessageFlagPersistence;
    }

    /**
     * Sets the discussion message flag persistence.
     *
     * @param discussionMessageFlagPersistence the discussion message flag persistence
     */
    public void setDiscussionMessageFlagPersistence(
        DiscussionMessageFlagPersistence discussionMessageFlagPersistence) {
        this.discussionMessageFlagPersistence = discussionMessageFlagPersistence;
    }

    /**
     * Returns the email list local service.
     *
     * @return the email list local service
     */
    public com.ext.portlet.service.EmailListLocalService getEmailListLocalService() {
        return emailListLocalService;
    }

    /**
     * Sets the email list local service.
     *
     * @param emailListLocalService the email list local service
     */
    public void setEmailListLocalService(
        com.ext.portlet.service.EmailListLocalService emailListLocalService) {
        this.emailListLocalService = emailListLocalService;
    }

    /**
     * Returns the email list remote service.
     *
     * @return the email list remote service
     */
    public com.ext.portlet.service.EmailListService getEmailListService() {
        return emailListService;
    }

    /**
     * Sets the email list remote service.
     *
     * @param emailListService the email list remote service
     */
    public void setEmailListService(
        com.ext.portlet.service.EmailListService emailListService) {
        this.emailListService = emailListService;
    }

    /**
     * Returns the email list persistence.
     *
     * @return the email list persistence
     */
    public EmailListPersistence getEmailListPersistence() {
        return emailListPersistence;
    }

    /**
     * Sets the email list persistence.
     *
     * @param emailListPersistence the email list persistence
     */
    public void setEmailListPersistence(
        EmailListPersistence emailListPersistence) {
        this.emailListPersistence = emailListPersistence;
    }

    /**
     * Returns the focus area local service.
     *
     * @return the focus area local service
     */
    public com.ext.portlet.service.FocusAreaLocalService getFocusAreaLocalService() {
        return focusAreaLocalService;
    }

    /**
     * Sets the focus area local service.
     *
     * @param focusAreaLocalService the focus area local service
     */
    public void setFocusAreaLocalService(
        com.ext.portlet.service.FocusAreaLocalService focusAreaLocalService) {
        this.focusAreaLocalService = focusAreaLocalService;
    }

    /**
     * Returns the focus area remote service.
     *
     * @return the focus area remote service
     */
    public com.ext.portlet.service.FocusAreaService getFocusAreaService() {
        return focusAreaService;
    }

    /**
     * Sets the focus area remote service.
     *
     * @param focusAreaService the focus area remote service
     */
    public void setFocusAreaService(
        com.ext.portlet.service.FocusAreaService focusAreaService) {
        this.focusAreaService = focusAreaService;
    }

    /**
     * Returns the focus area persistence.
     *
     * @return the focus area persistence
     */
    public FocusAreaPersistence getFocusAreaPersistence() {
        return focusAreaPersistence;
    }

    /**
     * Sets the focus area persistence.
     *
     * @param focusAreaPersistence the focus area persistence
     */
    public void setFocusAreaPersistence(
        FocusAreaPersistence focusAreaPersistence) {
        this.focusAreaPersistence = focusAreaPersistence;
    }

    /**
     * Returns the focus area ontology term local service.
     *
     * @return the focus area ontology term local service
     */
    public com.ext.portlet.service.FocusAreaOntologyTermLocalService getFocusAreaOntologyTermLocalService() {
        return focusAreaOntologyTermLocalService;
    }

    /**
     * Sets the focus area ontology term local service.
     *
     * @param focusAreaOntologyTermLocalService the focus area ontology term local service
     */
    public void setFocusAreaOntologyTermLocalService(
        com.ext.portlet.service.FocusAreaOntologyTermLocalService focusAreaOntologyTermLocalService) {
        this.focusAreaOntologyTermLocalService = focusAreaOntologyTermLocalService;
    }

    /**
     * Returns the focus area ontology term remote service.
     *
     * @return the focus area ontology term remote service
     */
    public com.ext.portlet.service.FocusAreaOntologyTermService getFocusAreaOntologyTermService() {
        return focusAreaOntologyTermService;
    }

    /**
     * Sets the focus area ontology term remote service.
     *
     * @param focusAreaOntologyTermService the focus area ontology term remote service
     */
    public void setFocusAreaOntologyTermService(
        com.ext.portlet.service.FocusAreaOntologyTermService focusAreaOntologyTermService) {
        this.focusAreaOntologyTermService = focusAreaOntologyTermService;
    }

    /**
     * Returns the focus area ontology term persistence.
     *
     * @return the focus area ontology term persistence
     */
    public FocusAreaOntologyTermPersistence getFocusAreaOntologyTermPersistence() {
        return focusAreaOntologyTermPersistence;
    }

    /**
     * Sets the focus area ontology term persistence.
     *
     * @param focusAreaOntologyTermPersistence the focus area ontology term persistence
     */
    public void setFocusAreaOntologyTermPersistence(
        FocusAreaOntologyTermPersistence focusAreaOntologyTermPersistence) {
        this.focusAreaOntologyTermPersistence = focusAreaOntologyTermPersistence;
    }

    /**
     * Returns the landing page local service.
     *
     * @return the landing page local service
     */
    public com.ext.portlet.service.LandingPageLocalService getLandingPageLocalService() {
        return landingPageLocalService;
    }

    /**
     * Sets the landing page local service.
     *
     * @param landingPageLocalService the landing page local service
     */
    public void setLandingPageLocalService(
        com.ext.portlet.service.LandingPageLocalService landingPageLocalService) {
        this.landingPageLocalService = landingPageLocalService;
    }

    /**
     * Returns the landing page remote service.
     *
     * @return the landing page remote service
     */
    public com.ext.portlet.service.LandingPageService getLandingPageService() {
        return landingPageService;
    }

    /**
     * Sets the landing page remote service.
     *
     * @param landingPageService the landing page remote service
     */
    public void setLandingPageService(
        com.ext.portlet.service.LandingPageService landingPageService) {
        this.landingPageService = landingPageService;
    }

    /**
     * Returns the landing page persistence.
     *
     * @return the landing page persistence
     */
    public LandingPagePersistence getLandingPagePersistence() {
        return landingPagePersistence;
    }

    /**
     * Sets the landing page persistence.
     *
     * @param landingPagePersistence the landing page persistence
     */
    public void setLandingPagePersistence(
        LandingPagePersistence landingPagePersistence) {
        this.landingPagePersistence = landingPagePersistence;
    }

    /**
     * Returns the login log local service.
     *
     * @return the login log local service
     */
    public com.ext.portlet.service.LoginLogLocalService getLoginLogLocalService() {
        return loginLogLocalService;
    }

    /**
     * Sets the login log local service.
     *
     * @param loginLogLocalService the login log local service
     */
    public void setLoginLogLocalService(
        com.ext.portlet.service.LoginLogLocalService loginLogLocalService) {
        this.loginLogLocalService = loginLogLocalService;
    }

    /**
     * Returns the login log remote service.
     *
     * @return the login log remote service
     */
    public com.ext.portlet.service.LoginLogService getLoginLogService() {
        return loginLogService;
    }

    /**
     * Sets the login log remote service.
     *
     * @param loginLogService the login log remote service
     */
    public void setLoginLogService(
        com.ext.portlet.service.LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    /**
     * Returns the login log persistence.
     *
     * @return the login log persistence
     */
    public LoginLogPersistence getLoginLogPersistence() {
        return loginLogPersistence;
    }

    /**
     * Sets the login log persistence.
     *
     * @param loginLogPersistence the login log persistence
     */
    public void setLoginLogPersistence(LoginLogPersistence loginLogPersistence) {
        this.loginLogPersistence = loginLogPersistence;
    }

    /**
     * Returns the message local service.
     *
     * @return the message local service
     */
    public com.ext.portlet.service.MessageLocalService getMessageLocalService() {
        return messageLocalService;
    }

    /**
     * Sets the message local service.
     *
     * @param messageLocalService the message local service
     */
    public void setMessageLocalService(
        com.ext.portlet.service.MessageLocalService messageLocalService) {
        this.messageLocalService = messageLocalService;
    }

    /**
     * Returns the message remote service.
     *
     * @return the message remote service
     */
    public com.ext.portlet.service.MessageService getMessageService() {
        return messageService;
    }

    /**
     * Sets the message remote service.
     *
     * @param messageService the message remote service
     */
    public void setMessageService(
        com.ext.portlet.service.MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Returns the message persistence.
     *
     * @return the message persistence
     */
    public MessagePersistence getMessagePersistence() {
        return messagePersistence;
    }

    /**
     * Sets the message persistence.
     *
     * @param messagePersistence the message persistence
     */
    public void setMessagePersistence(MessagePersistence messagePersistence) {
        this.messagePersistence = messagePersistence;
    }

    /**
     * Returns the message recipient status local service.
     *
     * @return the message recipient status local service
     */
    public com.ext.portlet.service.MessageRecipientStatusLocalService getMessageRecipientStatusLocalService() {
        return messageRecipientStatusLocalService;
    }

    /**
     * Sets the message recipient status local service.
     *
     * @param messageRecipientStatusLocalService the message recipient status local service
     */
    public void setMessageRecipientStatusLocalService(
        com.ext.portlet.service.MessageRecipientStatusLocalService messageRecipientStatusLocalService) {
        this.messageRecipientStatusLocalService = messageRecipientStatusLocalService;
    }

    /**
     * Returns the message recipient status remote service.
     *
     * @return the message recipient status remote service
     */
    public com.ext.portlet.service.MessageRecipientStatusService getMessageRecipientStatusService() {
        return messageRecipientStatusService;
    }

    /**
     * Sets the message recipient status remote service.
     *
     * @param messageRecipientStatusService the message recipient status remote service
     */
    public void setMessageRecipientStatusService(
        com.ext.portlet.service.MessageRecipientStatusService messageRecipientStatusService) {
        this.messageRecipientStatusService = messageRecipientStatusService;
    }

    /**
     * Returns the message recipient status persistence.
     *
     * @return the message recipient status persistence
     */
    public MessageRecipientStatusPersistence getMessageRecipientStatusPersistence() {
        return messageRecipientStatusPersistence;
    }

    /**
     * Sets the message recipient status persistence.
     *
     * @param messageRecipientStatusPersistence the message recipient status persistence
     */
    public void setMessageRecipientStatusPersistence(
        MessageRecipientStatusPersistence messageRecipientStatusPersistence) {
        this.messageRecipientStatusPersistence = messageRecipientStatusPersistence;
    }

    /**
     * Returns the messaging ignored recipients local service.
     *
     * @return the messaging ignored recipients local service
     */
    public com.ext.portlet.service.MessagingIgnoredRecipientsLocalService getMessagingIgnoredRecipientsLocalService() {
        return messagingIgnoredRecipientsLocalService;
    }

    /**
     * Sets the messaging ignored recipients local service.
     *
     * @param messagingIgnoredRecipientsLocalService the messaging ignored recipients local service
     */
    public void setMessagingIgnoredRecipientsLocalService(
        com.ext.portlet.service.MessagingIgnoredRecipientsLocalService messagingIgnoredRecipientsLocalService) {
        this.messagingIgnoredRecipientsLocalService = messagingIgnoredRecipientsLocalService;
    }

    /**
     * Returns the messaging ignored recipients remote service.
     *
     * @return the messaging ignored recipients remote service
     */
    public com.ext.portlet.service.MessagingIgnoredRecipientsService getMessagingIgnoredRecipientsService() {
        return messagingIgnoredRecipientsService;
    }

    /**
     * Sets the messaging ignored recipients remote service.
     *
     * @param messagingIgnoredRecipientsService the messaging ignored recipients remote service
     */
    public void setMessagingIgnoredRecipientsService(
        com.ext.portlet.service.MessagingIgnoredRecipientsService messagingIgnoredRecipientsService) {
        this.messagingIgnoredRecipientsService = messagingIgnoredRecipientsService;
    }

    /**
     * Returns the messaging ignored recipients persistence.
     *
     * @return the messaging ignored recipients persistence
     */
    public MessagingIgnoredRecipientsPersistence getMessagingIgnoredRecipientsPersistence() {
        return messagingIgnoredRecipientsPersistence;
    }

    /**
     * Sets the messaging ignored recipients persistence.
     *
     * @param messagingIgnoredRecipientsPersistence the messaging ignored recipients persistence
     */
    public void setMessagingIgnoredRecipientsPersistence(
        MessagingIgnoredRecipientsPersistence messagingIgnoredRecipientsPersistence) {
        this.messagingIgnoredRecipientsPersistence = messagingIgnoredRecipientsPersistence;
    }

    /**
     * Returns the messaging message local service.
     *
     * @return the messaging message local service
     */
    public com.ext.portlet.service.MessagingMessageLocalService getMessagingMessageLocalService() {
        return messagingMessageLocalService;
    }

    /**
     * Sets the messaging message local service.
     *
     * @param messagingMessageLocalService the messaging message local service
     */
    public void setMessagingMessageLocalService(
        com.ext.portlet.service.MessagingMessageLocalService messagingMessageLocalService) {
        this.messagingMessageLocalService = messagingMessageLocalService;
    }

    /**
     * Returns the messaging message remote service.
     *
     * @return the messaging message remote service
     */
    public com.ext.portlet.service.MessagingMessageService getMessagingMessageService() {
        return messagingMessageService;
    }

    /**
     * Sets the messaging message remote service.
     *
     * @param messagingMessageService the messaging message remote service
     */
    public void setMessagingMessageService(
        com.ext.portlet.service.MessagingMessageService messagingMessageService) {
        this.messagingMessageService = messagingMessageService;
    }

    /**
     * Returns the messaging message persistence.
     *
     * @return the messaging message persistence
     */
    public MessagingMessagePersistence getMessagingMessagePersistence() {
        return messagingMessagePersistence;
    }

    /**
     * Sets the messaging message persistence.
     *
     * @param messagingMessagePersistence the messaging message persistence
     */
    public void setMessagingMessagePersistence(
        MessagingMessagePersistence messagingMessagePersistence) {
        this.messagingMessagePersistence = messagingMessagePersistence;
    }

    /**
     * Returns the messaging message conversion local service.
     *
     * @return the messaging message conversion local service
     */
    public com.ext.portlet.service.MessagingMessageConversionLocalService getMessagingMessageConversionLocalService() {
        return messagingMessageConversionLocalService;
    }

    /**
     * Sets the messaging message conversion local service.
     *
     * @param messagingMessageConversionLocalService the messaging message conversion local service
     */
    public void setMessagingMessageConversionLocalService(
        com.ext.portlet.service.MessagingMessageConversionLocalService messagingMessageConversionLocalService) {
        this.messagingMessageConversionLocalService = messagingMessageConversionLocalService;
    }

    /**
     * Returns the messaging message conversion remote service.
     *
     * @return the messaging message conversion remote service
     */
    public com.ext.portlet.service.MessagingMessageConversionService getMessagingMessageConversionService() {
        return messagingMessageConversionService;
    }

    /**
     * Sets the messaging message conversion remote service.
     *
     * @param messagingMessageConversionService the messaging message conversion remote service
     */
    public void setMessagingMessageConversionService(
        com.ext.portlet.service.MessagingMessageConversionService messagingMessageConversionService) {
        this.messagingMessageConversionService = messagingMessageConversionService;
    }

    /**
     * Returns the messaging message conversion persistence.
     *
     * @return the messaging message conversion persistence
     */
    public MessagingMessageConversionPersistence getMessagingMessageConversionPersistence() {
        return messagingMessageConversionPersistence;
    }

    /**
     * Sets the messaging message conversion persistence.
     *
     * @param messagingMessageConversionPersistence the messaging message conversion persistence
     */
    public void setMessagingMessageConversionPersistence(
        MessagingMessageConversionPersistence messagingMessageConversionPersistence) {
        this.messagingMessageConversionPersistence = messagingMessageConversionPersistence;
    }

    /**
     * Returns the messaging message conversion type local service.
     *
     * @return the messaging message conversion type local service
     */
    public com.ext.portlet.service.MessagingMessageConversionTypeLocalService getMessagingMessageConversionTypeLocalService() {
        return messagingMessageConversionTypeLocalService;
    }

    /**
     * Sets the messaging message conversion type local service.
     *
     * @param messagingMessageConversionTypeLocalService the messaging message conversion type local service
     */
    public void setMessagingMessageConversionTypeLocalService(
        com.ext.portlet.service.MessagingMessageConversionTypeLocalService messagingMessageConversionTypeLocalService) {
        this.messagingMessageConversionTypeLocalService = messagingMessageConversionTypeLocalService;
    }

    /**
     * Returns the messaging message conversion type remote service.
     *
     * @return the messaging message conversion type remote service
     */
    public com.ext.portlet.service.MessagingMessageConversionTypeService getMessagingMessageConversionTypeService() {
        return messagingMessageConversionTypeService;
    }

    /**
     * Sets the messaging message conversion type remote service.
     *
     * @param messagingMessageConversionTypeService the messaging message conversion type remote service
     */
    public void setMessagingMessageConversionTypeService(
        com.ext.portlet.service.MessagingMessageConversionTypeService messagingMessageConversionTypeService) {
        this.messagingMessageConversionTypeService = messagingMessageConversionTypeService;
    }

    /**
     * Returns the messaging message conversion type persistence.
     *
     * @return the messaging message conversion type persistence
     */
    public MessagingMessageConversionTypePersistence getMessagingMessageConversionTypePersistence() {
        return messagingMessageConversionTypePersistence;
    }

    /**
     * Sets the messaging message conversion type persistence.
     *
     * @param messagingMessageConversionTypePersistence the messaging message conversion type persistence
     */
    public void setMessagingMessageConversionTypePersistence(
        MessagingMessageConversionTypePersistence messagingMessageConversionTypePersistence) {
        this.messagingMessageConversionTypePersistence = messagingMessageConversionTypePersistence;
    }

    /**
     * Returns the messaging message recipient local service.
     *
     * @return the messaging message recipient local service
     */
    public com.ext.portlet.service.MessagingMessageRecipientLocalService getMessagingMessageRecipientLocalService() {
        return messagingMessageRecipientLocalService;
    }

    /**
     * Sets the messaging message recipient local service.
     *
     * @param messagingMessageRecipientLocalService the messaging message recipient local service
     */
    public void setMessagingMessageRecipientLocalService(
        com.ext.portlet.service.MessagingMessageRecipientLocalService messagingMessageRecipientLocalService) {
        this.messagingMessageRecipientLocalService = messagingMessageRecipientLocalService;
    }

    /**
     * Returns the messaging message recipient remote service.
     *
     * @return the messaging message recipient remote service
     */
    public com.ext.portlet.service.MessagingMessageRecipientService getMessagingMessageRecipientService() {
        return messagingMessageRecipientService;
    }

    /**
     * Sets the messaging message recipient remote service.
     *
     * @param messagingMessageRecipientService the messaging message recipient remote service
     */
    public void setMessagingMessageRecipientService(
        com.ext.portlet.service.MessagingMessageRecipientService messagingMessageRecipientService) {
        this.messagingMessageRecipientService = messagingMessageRecipientService;
    }

    /**
     * Returns the messaging message recipient persistence.
     *
     * @return the messaging message recipient persistence
     */
    public MessagingMessageRecipientPersistence getMessagingMessageRecipientPersistence() {
        return messagingMessageRecipientPersistence;
    }

    /**
     * Sets the messaging message recipient persistence.
     *
     * @param messagingMessageRecipientPersistence the messaging message recipient persistence
     */
    public void setMessagingMessageRecipientPersistence(
        MessagingMessageRecipientPersistence messagingMessageRecipientPersistence) {
        this.messagingMessageRecipientPersistence = messagingMessageRecipientPersistence;
    }

    /**
     * Returns the messaging redirect link local service.
     *
     * @return the messaging redirect link local service
     */
    public com.ext.portlet.service.MessagingRedirectLinkLocalService getMessagingRedirectLinkLocalService() {
        return messagingRedirectLinkLocalService;
    }

    /**
     * Sets the messaging redirect link local service.
     *
     * @param messagingRedirectLinkLocalService the messaging redirect link local service
     */
    public void setMessagingRedirectLinkLocalService(
        com.ext.portlet.service.MessagingRedirectLinkLocalService messagingRedirectLinkLocalService) {
        this.messagingRedirectLinkLocalService = messagingRedirectLinkLocalService;
    }

    /**
     * Returns the messaging redirect link remote service.
     *
     * @return the messaging redirect link remote service
     */
    public com.ext.portlet.service.MessagingRedirectLinkService getMessagingRedirectLinkService() {
        return messagingRedirectLinkService;
    }

    /**
     * Sets the messaging redirect link remote service.
     *
     * @param messagingRedirectLinkService the messaging redirect link remote service
     */
    public void setMessagingRedirectLinkService(
        com.ext.portlet.service.MessagingRedirectLinkService messagingRedirectLinkService) {
        this.messagingRedirectLinkService = messagingRedirectLinkService;
    }

    /**
     * Returns the messaging redirect link persistence.
     *
     * @return the messaging redirect link persistence
     */
    public MessagingRedirectLinkPersistence getMessagingRedirectLinkPersistence() {
        return messagingRedirectLinkPersistence;
    }

    /**
     * Sets the messaging redirect link persistence.
     *
     * @param messagingRedirectLinkPersistence the messaging redirect link persistence
     */
    public void setMessagingRedirectLinkPersistence(
        MessagingRedirectLinkPersistence messagingRedirectLinkPersistence) {
        this.messagingRedirectLinkPersistence = messagingRedirectLinkPersistence;
    }

    /**
     * Returns the messaging user preferences local service.
     *
     * @return the messaging user preferences local service
     */
    public com.ext.portlet.service.MessagingUserPreferencesLocalService getMessagingUserPreferencesLocalService() {
        return messagingUserPreferencesLocalService;
    }

    /**
     * Sets the messaging user preferences local service.
     *
     * @param messagingUserPreferencesLocalService the messaging user preferences local service
     */
    public void setMessagingUserPreferencesLocalService(
        com.ext.portlet.service.MessagingUserPreferencesLocalService messagingUserPreferencesLocalService) {
        this.messagingUserPreferencesLocalService = messagingUserPreferencesLocalService;
    }

    /**
     * Returns the messaging user preferences remote service.
     *
     * @return the messaging user preferences remote service
     */
    public com.ext.portlet.service.MessagingUserPreferencesService getMessagingUserPreferencesService() {
        return messagingUserPreferencesService;
    }

    /**
     * Sets the messaging user preferences remote service.
     *
     * @param messagingUserPreferencesService the messaging user preferences remote service
     */
    public void setMessagingUserPreferencesService(
        com.ext.portlet.service.MessagingUserPreferencesService messagingUserPreferencesService) {
        this.messagingUserPreferencesService = messagingUserPreferencesService;
    }

    /**
     * Returns the messaging user preferences persistence.
     *
     * @return the messaging user preferences persistence
     */
    public MessagingUserPreferencesPersistence getMessagingUserPreferencesPersistence() {
        return messagingUserPreferencesPersistence;
    }

    /**
     * Sets the messaging user preferences persistence.
     *
     * @param messagingUserPreferencesPersistence the messaging user preferences persistence
     */
    public void setMessagingUserPreferencesPersistence(
        MessagingUserPreferencesPersistence messagingUserPreferencesPersistence) {
        this.messagingUserPreferencesPersistence = messagingUserPreferencesPersistence;
    }

    /**
     * Returns the model category local service.
     *
     * @return the model category local service
     */
    public com.ext.portlet.service.ModelCategoryLocalService getModelCategoryLocalService() {
        return modelCategoryLocalService;
    }

    /**
     * Sets the model category local service.
     *
     * @param modelCategoryLocalService the model category local service
     */
    public void setModelCategoryLocalService(
        com.ext.portlet.service.ModelCategoryLocalService modelCategoryLocalService) {
        this.modelCategoryLocalService = modelCategoryLocalService;
    }

    /**
     * Returns the model category remote service.
     *
     * @return the model category remote service
     */
    public com.ext.portlet.service.ModelCategoryService getModelCategoryService() {
        return modelCategoryService;
    }

    /**
     * Sets the model category remote service.
     *
     * @param modelCategoryService the model category remote service
     */
    public void setModelCategoryService(
        com.ext.portlet.service.ModelCategoryService modelCategoryService) {
        this.modelCategoryService = modelCategoryService;
    }

    /**
     * Returns the model category persistence.
     *
     * @return the model category persistence
     */
    public ModelCategoryPersistence getModelCategoryPersistence() {
        return modelCategoryPersistence;
    }

    /**
     * Sets the model category persistence.
     *
     * @param modelCategoryPersistence the model category persistence
     */
    public void setModelCategoryPersistence(
        ModelCategoryPersistence modelCategoryPersistence) {
        this.modelCategoryPersistence = modelCategoryPersistence;
    }

    /**
     * Returns the model discussion local service.
     *
     * @return the model discussion local service
     */
    public com.ext.portlet.service.ModelDiscussionLocalService getModelDiscussionLocalService() {
        return modelDiscussionLocalService;
    }

    /**
     * Sets the model discussion local service.
     *
     * @param modelDiscussionLocalService the model discussion local service
     */
    public void setModelDiscussionLocalService(
        com.ext.portlet.service.ModelDiscussionLocalService modelDiscussionLocalService) {
        this.modelDiscussionLocalService = modelDiscussionLocalService;
    }

    /**
     * Returns the model discussion remote service.
     *
     * @return the model discussion remote service
     */
    public com.ext.portlet.service.ModelDiscussionService getModelDiscussionService() {
        return modelDiscussionService;
    }

    /**
     * Sets the model discussion remote service.
     *
     * @param modelDiscussionService the model discussion remote service
     */
    public void setModelDiscussionService(
        com.ext.portlet.service.ModelDiscussionService modelDiscussionService) {
        this.modelDiscussionService = modelDiscussionService;
    }

    /**
     * Returns the model discussion persistence.
     *
     * @return the model discussion persistence
     */
    public ModelDiscussionPersistence getModelDiscussionPersistence() {
        return modelDiscussionPersistence;
    }

    /**
     * Sets the model discussion persistence.
     *
     * @param modelDiscussionPersistence the model discussion persistence
     */
    public void setModelDiscussionPersistence(
        ModelDiscussionPersistence modelDiscussionPersistence) {
        this.modelDiscussionPersistence = modelDiscussionPersistence;
    }

    /**
     * Returns the model global preference local service.
     *
     * @return the model global preference local service
     */
    public com.ext.portlet.service.ModelGlobalPreferenceLocalService getModelGlobalPreferenceLocalService() {
        return modelGlobalPreferenceLocalService;
    }

    /**
     * Sets the model global preference local service.
     *
     * @param modelGlobalPreferenceLocalService the model global preference local service
     */
    public void setModelGlobalPreferenceLocalService(
        com.ext.portlet.service.ModelGlobalPreferenceLocalService modelGlobalPreferenceLocalService) {
        this.modelGlobalPreferenceLocalService = modelGlobalPreferenceLocalService;
    }

    /**
     * Returns the model global preference remote service.
     *
     * @return the model global preference remote service
     */
    public com.ext.portlet.service.ModelGlobalPreferenceService getModelGlobalPreferenceService() {
        return modelGlobalPreferenceService;
    }

    /**
     * Sets the model global preference remote service.
     *
     * @param modelGlobalPreferenceService the model global preference remote service
     */
    public void setModelGlobalPreferenceService(
        com.ext.portlet.service.ModelGlobalPreferenceService modelGlobalPreferenceService) {
        this.modelGlobalPreferenceService = modelGlobalPreferenceService;
    }

    /**
     * Returns the model global preference persistence.
     *
     * @return the model global preference persistence
     */
    public ModelGlobalPreferencePersistence getModelGlobalPreferencePersistence() {
        return modelGlobalPreferencePersistence;
    }

    /**
     * Sets the model global preference persistence.
     *
     * @param modelGlobalPreferencePersistence the model global preference persistence
     */
    public void setModelGlobalPreferencePersistence(
        ModelGlobalPreferencePersistence modelGlobalPreferencePersistence) {
        this.modelGlobalPreferencePersistence = modelGlobalPreferencePersistence;
    }

    /**
     * Returns the model input group local service.
     *
     * @return the model input group local service
     */
    public com.ext.portlet.service.ModelInputGroupLocalService getModelInputGroupLocalService() {
        return modelInputGroupLocalService;
    }

    /**
     * Sets the model input group local service.
     *
     * @param modelInputGroupLocalService the model input group local service
     */
    public void setModelInputGroupLocalService(
        com.ext.portlet.service.ModelInputGroupLocalService modelInputGroupLocalService) {
        this.modelInputGroupLocalService = modelInputGroupLocalService;
    }

    /**
     * Returns the model input group remote service.
     *
     * @return the model input group remote service
     */
    public com.ext.portlet.service.ModelInputGroupService getModelInputGroupService() {
        return modelInputGroupService;
    }

    /**
     * Sets the model input group remote service.
     *
     * @param modelInputGroupService the model input group remote service
     */
    public void setModelInputGroupService(
        com.ext.portlet.service.ModelInputGroupService modelInputGroupService) {
        this.modelInputGroupService = modelInputGroupService;
    }

    /**
     * Returns the model input group persistence.
     *
     * @return the model input group persistence
     */
    public ModelInputGroupPersistence getModelInputGroupPersistence() {
        return modelInputGroupPersistence;
    }

    /**
     * Sets the model input group persistence.
     *
     * @param modelInputGroupPersistence the model input group persistence
     */
    public void setModelInputGroupPersistence(
        ModelInputGroupPersistence modelInputGroupPersistence) {
        this.modelInputGroupPersistence = modelInputGroupPersistence;
    }

    /**
     * Returns the model input item local service.
     *
     * @return the model input item local service
     */
    public com.ext.portlet.service.ModelInputItemLocalService getModelInputItemLocalService() {
        return modelInputItemLocalService;
    }

    /**
     * Sets the model input item local service.
     *
     * @param modelInputItemLocalService the model input item local service
     */
    public void setModelInputItemLocalService(
        com.ext.portlet.service.ModelInputItemLocalService modelInputItemLocalService) {
        this.modelInputItemLocalService = modelInputItemLocalService;
    }

    /**
     * Returns the model input item remote service.
     *
     * @return the model input item remote service
     */
    public com.ext.portlet.service.ModelInputItemService getModelInputItemService() {
        return modelInputItemService;
    }

    /**
     * Sets the model input item remote service.
     *
     * @param modelInputItemService the model input item remote service
     */
    public void setModelInputItemService(
        com.ext.portlet.service.ModelInputItemService modelInputItemService) {
        this.modelInputItemService = modelInputItemService;
    }

    /**
     * Returns the model input item persistence.
     *
     * @return the model input item persistence
     */
    public ModelInputItemPersistence getModelInputItemPersistence() {
        return modelInputItemPersistence;
    }

    /**
     * Sets the model input item persistence.
     *
     * @param modelInputItemPersistence the model input item persistence
     */
    public void setModelInputItemPersistence(
        ModelInputItemPersistence modelInputItemPersistence) {
        this.modelInputItemPersistence = modelInputItemPersistence;
    }

    /**
     * Returns the model output chart order local service.
     *
     * @return the model output chart order local service
     */
    public com.ext.portlet.service.ModelOutputChartOrderLocalService getModelOutputChartOrderLocalService() {
        return modelOutputChartOrderLocalService;
    }

    /**
     * Sets the model output chart order local service.
     *
     * @param modelOutputChartOrderLocalService the model output chart order local service
     */
    public void setModelOutputChartOrderLocalService(
        com.ext.portlet.service.ModelOutputChartOrderLocalService modelOutputChartOrderLocalService) {
        this.modelOutputChartOrderLocalService = modelOutputChartOrderLocalService;
    }

    /**
     * Returns the model output chart order remote service.
     *
     * @return the model output chart order remote service
     */
    public com.ext.portlet.service.ModelOutputChartOrderService getModelOutputChartOrderService() {
        return modelOutputChartOrderService;
    }

    /**
     * Sets the model output chart order remote service.
     *
     * @param modelOutputChartOrderService the model output chart order remote service
     */
    public void setModelOutputChartOrderService(
        com.ext.portlet.service.ModelOutputChartOrderService modelOutputChartOrderService) {
        this.modelOutputChartOrderService = modelOutputChartOrderService;
    }

    /**
     * Returns the model output chart order persistence.
     *
     * @return the model output chart order persistence
     */
    public ModelOutputChartOrderPersistence getModelOutputChartOrderPersistence() {
        return modelOutputChartOrderPersistence;
    }

    /**
     * Sets the model output chart order persistence.
     *
     * @param modelOutputChartOrderPersistence the model output chart order persistence
     */
    public void setModelOutputChartOrderPersistence(
        ModelOutputChartOrderPersistence modelOutputChartOrderPersistence) {
        this.modelOutputChartOrderPersistence = modelOutputChartOrderPersistence;
    }

    /**
     * Returns the model output item local service.
     *
     * @return the model output item local service
     */
    public com.ext.portlet.service.ModelOutputItemLocalService getModelOutputItemLocalService() {
        return modelOutputItemLocalService;
    }

    /**
     * Sets the model output item local service.
     *
     * @param modelOutputItemLocalService the model output item local service
     */
    public void setModelOutputItemLocalService(
        com.ext.portlet.service.ModelOutputItemLocalService modelOutputItemLocalService) {
        this.modelOutputItemLocalService = modelOutputItemLocalService;
    }

    /**
     * Returns the model output item remote service.
     *
     * @return the model output item remote service
     */
    public com.ext.portlet.service.ModelOutputItemService getModelOutputItemService() {
        return modelOutputItemService;
    }

    /**
     * Sets the model output item remote service.
     *
     * @param modelOutputItemService the model output item remote service
     */
    public void setModelOutputItemService(
        com.ext.portlet.service.ModelOutputItemService modelOutputItemService) {
        this.modelOutputItemService = modelOutputItemService;
    }

    /**
     * Returns the model output item persistence.
     *
     * @return the model output item persistence
     */
    public ModelOutputItemPersistence getModelOutputItemPersistence() {
        return modelOutputItemPersistence;
    }

    /**
     * Sets the model output item persistence.
     *
     * @param modelOutputItemPersistence the model output item persistence
     */
    public void setModelOutputItemPersistence(
        ModelOutputItemPersistence modelOutputItemPersistence) {
        this.modelOutputItemPersistence = modelOutputItemPersistence;
    }

    /**
     * Returns the model position local service.
     *
     * @return the model position local service
     */
    public com.ext.portlet.service.ModelPositionLocalService getModelPositionLocalService() {
        return modelPositionLocalService;
    }

    /**
     * Sets the model position local service.
     *
     * @param modelPositionLocalService the model position local service
     */
    public void setModelPositionLocalService(
        com.ext.portlet.service.ModelPositionLocalService modelPositionLocalService) {
        this.modelPositionLocalService = modelPositionLocalService;
    }

    /**
     * Returns the model position remote service.
     *
     * @return the model position remote service
     */
    public com.ext.portlet.service.ModelPositionService getModelPositionService() {
        return modelPositionService;
    }

    /**
     * Sets the model position remote service.
     *
     * @param modelPositionService the model position remote service
     */
    public void setModelPositionService(
        com.ext.portlet.service.ModelPositionService modelPositionService) {
        this.modelPositionService = modelPositionService;
    }

    /**
     * Returns the model position persistence.
     *
     * @return the model position persistence
     */
    public ModelPositionPersistence getModelPositionPersistence() {
        return modelPositionPersistence;
    }

    /**
     * Sets the model position persistence.
     *
     * @param modelPositionPersistence the model position persistence
     */
    public void setModelPositionPersistence(
        ModelPositionPersistence modelPositionPersistence) {
        this.modelPositionPersistence = modelPositionPersistence;
    }

    /**
     * Returns the model runner local service.
     *
     * @return the model runner local service
     */
    public com.ext.portlet.service.ModelRunnerLocalService getModelRunnerLocalService() {
        return modelRunnerLocalService;
    }

    /**
     * Sets the model runner local service.
     *
     * @param modelRunnerLocalService the model runner local service
     */
    public void setModelRunnerLocalService(
        com.ext.portlet.service.ModelRunnerLocalService modelRunnerLocalService) {
        this.modelRunnerLocalService = modelRunnerLocalService;
    }

    /**
     * Returns the model runner remote service.
     *
     * @return the model runner remote service
     */
    public com.ext.portlet.service.ModelRunnerService getModelRunnerService() {
        return modelRunnerService;
    }

    /**
     * Sets the model runner remote service.
     *
     * @param modelRunnerService the model runner remote service
     */
    public void setModelRunnerService(
        com.ext.portlet.service.ModelRunnerService modelRunnerService) {
        this.modelRunnerService = modelRunnerService;
    }

    /**
     * Returns the ontology space local service.
     *
     * @return the ontology space local service
     */
    public com.ext.portlet.service.OntologySpaceLocalService getOntologySpaceLocalService() {
        return ontologySpaceLocalService;
    }

    /**
     * Sets the ontology space local service.
     *
     * @param ontologySpaceLocalService the ontology space local service
     */
    public void setOntologySpaceLocalService(
        com.ext.portlet.service.OntologySpaceLocalService ontologySpaceLocalService) {
        this.ontologySpaceLocalService = ontologySpaceLocalService;
    }

    /**
     * Returns the ontology space remote service.
     *
     * @return the ontology space remote service
     */
    public com.ext.portlet.service.OntologySpaceService getOntologySpaceService() {
        return ontologySpaceService;
    }

    /**
     * Sets the ontology space remote service.
     *
     * @param ontologySpaceService the ontology space remote service
     */
    public void setOntologySpaceService(
        com.ext.portlet.service.OntologySpaceService ontologySpaceService) {
        this.ontologySpaceService = ontologySpaceService;
    }

    /**
     * Returns the ontology space persistence.
     *
     * @return the ontology space persistence
     */
    public OntologySpacePersistence getOntologySpacePersistence() {
        return ontologySpacePersistence;
    }

    /**
     * Sets the ontology space persistence.
     *
     * @param ontologySpacePersistence the ontology space persistence
     */
    public void setOntologySpacePersistence(
        OntologySpacePersistence ontologySpacePersistence) {
        this.ontologySpacePersistence = ontologySpacePersistence;
    }

    /**
     * Returns the ontology term local service.
     *
     * @return the ontology term local service
     */
    public com.ext.portlet.service.OntologyTermLocalService getOntologyTermLocalService() {
        return ontologyTermLocalService;
    }

    /**
     * Sets the ontology term local service.
     *
     * @param ontologyTermLocalService the ontology term local service
     */
    public void setOntologyTermLocalService(
        com.ext.portlet.service.OntologyTermLocalService ontologyTermLocalService) {
        this.ontologyTermLocalService = ontologyTermLocalService;
    }

    /**
     * Returns the ontology term remote service.
     *
     * @return the ontology term remote service
     */
    public com.ext.portlet.service.OntologyTermService getOntologyTermService() {
        return ontologyTermService;
    }

    /**
     * Sets the ontology term remote service.
     *
     * @param ontologyTermService the ontology term remote service
     */
    public void setOntologyTermService(
        com.ext.portlet.service.OntologyTermService ontologyTermService) {
        this.ontologyTermService = ontologyTermService;
    }

    /**
     * Returns the ontology term persistence.
     *
     * @return the ontology term persistence
     */
    public OntologyTermPersistence getOntologyTermPersistence() {
        return ontologyTermPersistence;
    }

    /**
     * Sets the ontology term persistence.
     *
     * @param ontologyTermPersistence the ontology term persistence
     */
    public void setOntologyTermPersistence(
        OntologyTermPersistence ontologyTermPersistence) {
        this.ontologyTermPersistence = ontologyTermPersistence;
    }

    /**
     * Returns the ontology term entity local service.
     *
     * @return the ontology term entity local service
     */
    public com.ext.portlet.service.OntologyTermEntityLocalService getOntologyTermEntityLocalService() {
        return ontologyTermEntityLocalService;
    }

    /**
     * Sets the ontology term entity local service.
     *
     * @param ontologyTermEntityLocalService the ontology term entity local service
     */
    public void setOntologyTermEntityLocalService(
        com.ext.portlet.service.OntologyTermEntityLocalService ontologyTermEntityLocalService) {
        this.ontologyTermEntityLocalService = ontologyTermEntityLocalService;
    }

    /**
     * Returns the ontology term entity remote service.
     *
     * @return the ontology term entity remote service
     */
    public com.ext.portlet.service.OntologyTermEntityService getOntologyTermEntityService() {
        return ontologyTermEntityService;
    }

    /**
     * Sets the ontology term entity remote service.
     *
     * @param ontologyTermEntityService the ontology term entity remote service
     */
    public void setOntologyTermEntityService(
        com.ext.portlet.service.OntologyTermEntityService ontologyTermEntityService) {
        this.ontologyTermEntityService = ontologyTermEntityService;
    }

    /**
     * Returns the ontology term entity persistence.
     *
     * @return the ontology term entity persistence
     */
    public OntologyTermEntityPersistence getOntologyTermEntityPersistence() {
        return ontologyTermEntityPersistence;
    }

    /**
     * Sets the ontology term entity persistence.
     *
     * @param ontologyTermEntityPersistence the ontology term entity persistence
     */
    public void setOntologyTermEntityPersistence(
        OntologyTermEntityPersistence ontologyTermEntityPersistence) {
        this.ontologyTermEntityPersistence = ontologyTermEntityPersistence;
    }

    /**
     * Returns the plan2 proposal local service.
     *
     * @return the plan2 proposal local service
     */
    public com.ext.portlet.service.Plan2ProposalLocalService getPlan2ProposalLocalService() {
        return plan2ProposalLocalService;
    }

    /**
     * Sets the plan2 proposal local service.
     *
     * @param plan2ProposalLocalService the plan2 proposal local service
     */
    public void setPlan2ProposalLocalService(
        com.ext.portlet.service.Plan2ProposalLocalService plan2ProposalLocalService) {
        this.plan2ProposalLocalService = plan2ProposalLocalService;
    }

    /**
     * Returns the plan2 proposal remote service.
     *
     * @return the plan2 proposal remote service
     */
    public com.ext.portlet.service.Plan2ProposalService getPlan2ProposalService() {
        return plan2ProposalService;
    }

    /**
     * Sets the plan2 proposal remote service.
     *
     * @param plan2ProposalService the plan2 proposal remote service
     */
    public void setPlan2ProposalService(
        com.ext.portlet.service.Plan2ProposalService plan2ProposalService) {
        this.plan2ProposalService = plan2ProposalService;
    }

    /**
     * Returns the plan2 proposal persistence.
     *
     * @return the plan2 proposal persistence
     */
    public Plan2ProposalPersistence getPlan2ProposalPersistence() {
        return plan2ProposalPersistence;
    }

    /**
     * Sets the plan2 proposal persistence.
     *
     * @param plan2ProposalPersistence the plan2 proposal persistence
     */
    public void setPlan2ProposalPersistence(
        Plan2ProposalPersistence plan2ProposalPersistence) {
        this.plan2ProposalPersistence = plan2ProposalPersistence;
    }

    /**
     * Returns the plan attribute local service.
     *
     * @return the plan attribute local service
     */
    public com.ext.portlet.service.PlanAttributeLocalService getPlanAttributeLocalService() {
        return planAttributeLocalService;
    }

    /**
     * Sets the plan attribute local service.
     *
     * @param planAttributeLocalService the plan attribute local service
     */
    public void setPlanAttributeLocalService(
        com.ext.portlet.service.PlanAttributeLocalService planAttributeLocalService) {
        this.planAttributeLocalService = planAttributeLocalService;
    }

    /**
     * Returns the plan attribute remote service.
     *
     * @return the plan attribute remote service
     */
    public com.ext.portlet.service.PlanAttributeService getPlanAttributeService() {
        return planAttributeService;
    }

    /**
     * Sets the plan attribute remote service.
     *
     * @param planAttributeService the plan attribute remote service
     */
    public void setPlanAttributeService(
        com.ext.portlet.service.PlanAttributeService planAttributeService) {
        this.planAttributeService = planAttributeService;
    }

    /**
     * Returns the plan attribute persistence.
     *
     * @return the plan attribute persistence
     */
    public PlanAttributePersistence getPlanAttributePersistence() {
        return planAttributePersistence;
    }

    /**
     * Sets the plan attribute persistence.
     *
     * @param planAttributePersistence the plan attribute persistence
     */
    public void setPlanAttributePersistence(
        PlanAttributePersistence planAttributePersistence) {
        this.planAttributePersistence = planAttributePersistence;
    }

    /**
     * Returns the plan attribute filter local service.
     *
     * @return the plan attribute filter local service
     */
    public com.ext.portlet.service.PlanAttributeFilterLocalService getPlanAttributeFilterLocalService() {
        return planAttributeFilterLocalService;
    }

    /**
     * Sets the plan attribute filter local service.
     *
     * @param planAttributeFilterLocalService the plan attribute filter local service
     */
    public void setPlanAttributeFilterLocalService(
        com.ext.portlet.service.PlanAttributeFilterLocalService planAttributeFilterLocalService) {
        this.planAttributeFilterLocalService = planAttributeFilterLocalService;
    }

    /**
     * Returns the plan attribute filter remote service.
     *
     * @return the plan attribute filter remote service
     */
    public com.ext.portlet.service.PlanAttributeFilterService getPlanAttributeFilterService() {
        return planAttributeFilterService;
    }

    /**
     * Sets the plan attribute filter remote service.
     *
     * @param planAttributeFilterService the plan attribute filter remote service
     */
    public void setPlanAttributeFilterService(
        com.ext.portlet.service.PlanAttributeFilterService planAttributeFilterService) {
        this.planAttributeFilterService = planAttributeFilterService;
    }

    /**
     * Returns the plan attribute filter persistence.
     *
     * @return the plan attribute filter persistence
     */
    public PlanAttributeFilterPersistence getPlanAttributeFilterPersistence() {
        return planAttributeFilterPersistence;
    }

    /**
     * Sets the plan attribute filter persistence.
     *
     * @param planAttributeFilterPersistence the plan attribute filter persistence
     */
    public void setPlanAttributeFilterPersistence(
        PlanAttributeFilterPersistence planAttributeFilterPersistence) {
        this.planAttributeFilterPersistence = planAttributeFilterPersistence;
    }

    /**
     * Returns the plan column settings local service.
     *
     * @return the plan column settings local service
     */
    public com.ext.portlet.service.PlanColumnSettingsLocalService getPlanColumnSettingsLocalService() {
        return planColumnSettingsLocalService;
    }

    /**
     * Sets the plan column settings local service.
     *
     * @param planColumnSettingsLocalService the plan column settings local service
     */
    public void setPlanColumnSettingsLocalService(
        com.ext.portlet.service.PlanColumnSettingsLocalService planColumnSettingsLocalService) {
        this.planColumnSettingsLocalService = planColumnSettingsLocalService;
    }

    /**
     * Returns the plan column settings remote service.
     *
     * @return the plan column settings remote service
     */
    public com.ext.portlet.service.PlanColumnSettingsService getPlanColumnSettingsService() {
        return planColumnSettingsService;
    }

    /**
     * Sets the plan column settings remote service.
     *
     * @param planColumnSettingsService the plan column settings remote service
     */
    public void setPlanColumnSettingsService(
        com.ext.portlet.service.PlanColumnSettingsService planColumnSettingsService) {
        this.planColumnSettingsService = planColumnSettingsService;
    }

    /**
     * Returns the plan column settings persistence.
     *
     * @return the plan column settings persistence
     */
    public PlanColumnSettingsPersistence getPlanColumnSettingsPersistence() {
        return planColumnSettingsPersistence;
    }

    /**
     * Sets the plan column settings persistence.
     *
     * @param planColumnSettingsPersistence the plan column settings persistence
     */
    public void setPlanColumnSettingsPersistence(
        PlanColumnSettingsPersistence planColumnSettingsPersistence) {
        this.planColumnSettingsPersistence = planColumnSettingsPersistence;
    }

    /**
     * Returns the plan description local service.
     *
     * @return the plan description local service
     */
    public com.ext.portlet.service.PlanDescriptionLocalService getPlanDescriptionLocalService() {
        return planDescriptionLocalService;
    }

    /**
     * Sets the plan description local service.
     *
     * @param planDescriptionLocalService the plan description local service
     */
    public void setPlanDescriptionLocalService(
        com.ext.portlet.service.PlanDescriptionLocalService planDescriptionLocalService) {
        this.planDescriptionLocalService = planDescriptionLocalService;
    }

    /**
     * Returns the plan description remote service.
     *
     * @return the plan description remote service
     */
    public com.ext.portlet.service.PlanDescriptionService getPlanDescriptionService() {
        return planDescriptionService;
    }

    /**
     * Sets the plan description remote service.
     *
     * @param planDescriptionService the plan description remote service
     */
    public void setPlanDescriptionService(
        com.ext.portlet.service.PlanDescriptionService planDescriptionService) {
        this.planDescriptionService = planDescriptionService;
    }

    /**
     * Returns the plan description persistence.
     *
     * @return the plan description persistence
     */
    public PlanDescriptionPersistence getPlanDescriptionPersistence() {
        return planDescriptionPersistence;
    }

    /**
     * Sets the plan description persistence.
     *
     * @param planDescriptionPersistence the plan description persistence
     */
    public void setPlanDescriptionPersistence(
        PlanDescriptionPersistence planDescriptionPersistence) {
        this.planDescriptionPersistence = planDescriptionPersistence;
    }

    /**
     * Returns the plan fan local service.
     *
     * @return the plan fan local service
     */
    public com.ext.portlet.service.PlanFanLocalService getPlanFanLocalService() {
        return planFanLocalService;
    }

    /**
     * Sets the plan fan local service.
     *
     * @param planFanLocalService the plan fan local service
     */
    public void setPlanFanLocalService(
        com.ext.portlet.service.PlanFanLocalService planFanLocalService) {
        this.planFanLocalService = planFanLocalService;
    }

    /**
     * Returns the plan fan remote service.
     *
     * @return the plan fan remote service
     */
    public com.ext.portlet.service.PlanFanService getPlanFanService() {
        return planFanService;
    }

    /**
     * Sets the plan fan remote service.
     *
     * @param planFanService the plan fan remote service
     */
    public void setPlanFanService(
        com.ext.portlet.service.PlanFanService planFanService) {
        this.planFanService = planFanService;
    }

    /**
     * Returns the plan fan persistence.
     *
     * @return the plan fan persistence
     */
    public PlanFanPersistence getPlanFanPersistence() {
        return planFanPersistence;
    }

    /**
     * Sets the plan fan persistence.
     *
     * @param planFanPersistence the plan fan persistence
     */
    public void setPlanFanPersistence(PlanFanPersistence planFanPersistence) {
        this.planFanPersistence = planFanPersistence;
    }

    /**
     * Returns the plan item local service.
     *
     * @return the plan item local service
     */
    public com.ext.portlet.service.PlanItemLocalService getPlanItemLocalService() {
        return planItemLocalService;
    }

    /**
     * Sets the plan item local service.
     *
     * @param planItemLocalService the plan item local service
     */
    public void setPlanItemLocalService(
        com.ext.portlet.service.PlanItemLocalService planItemLocalService) {
        this.planItemLocalService = planItemLocalService;
    }

    /**
     * Returns the plan item remote service.
     *
     * @return the plan item remote service
     */
    public com.ext.portlet.service.PlanItemService getPlanItemService() {
        return planItemService;
    }

    /**
     * Sets the plan item remote service.
     *
     * @param planItemService the plan item remote service
     */
    public void setPlanItemService(
        com.ext.portlet.service.PlanItemService planItemService) {
        this.planItemService = planItemService;
    }

    /**
     * Returns the plan item persistence.
     *
     * @return the plan item persistence
     */
    public PlanItemPersistence getPlanItemPersistence() {
        return planItemPersistence;
    }

    /**
     * Sets the plan item persistence.
     *
     * @param planItemPersistence the plan item persistence
     */
    public void setPlanItemPersistence(PlanItemPersistence planItemPersistence) {
        this.planItemPersistence = planItemPersistence;
    }

    /**
     * Returns the plan item finder.
     *
     * @return the plan item finder
     */
    public PlanItemFinder getPlanItemFinder() {
        return planItemFinder;
    }

    /**
     * Sets the plan item finder.
     *
     * @param planItemFinder the plan item finder
     */
    public void setPlanItemFinder(PlanItemFinder planItemFinder) {
        this.planItemFinder = planItemFinder;
    }

    /**
     * Returns the plan item group local service.
     *
     * @return the plan item group local service
     */
    public com.ext.portlet.service.PlanItemGroupLocalService getPlanItemGroupLocalService() {
        return planItemGroupLocalService;
    }

    /**
     * Sets the plan item group local service.
     *
     * @param planItemGroupLocalService the plan item group local service
     */
    public void setPlanItemGroupLocalService(
        com.ext.portlet.service.PlanItemGroupLocalService planItemGroupLocalService) {
        this.planItemGroupLocalService = planItemGroupLocalService;
    }

    /**
     * Returns the plan item group remote service.
     *
     * @return the plan item group remote service
     */
    public com.ext.portlet.service.PlanItemGroupService getPlanItemGroupService() {
        return planItemGroupService;
    }

    /**
     * Sets the plan item group remote service.
     *
     * @param planItemGroupService the plan item group remote service
     */
    public void setPlanItemGroupService(
        com.ext.portlet.service.PlanItemGroupService planItemGroupService) {
        this.planItemGroupService = planItemGroupService;
    }

    /**
     * Returns the plan item group persistence.
     *
     * @return the plan item group persistence
     */
    public PlanItemGroupPersistence getPlanItemGroupPersistence() {
        return planItemGroupPersistence;
    }

    /**
     * Sets the plan item group persistence.
     *
     * @param planItemGroupPersistence the plan item group persistence
     */
    public void setPlanItemGroupPersistence(
        PlanItemGroupPersistence planItemGroupPersistence) {
        this.planItemGroupPersistence = planItemGroupPersistence;
    }

    /**
     * Returns the plan meta local service.
     *
     * @return the plan meta local service
     */
    public com.ext.portlet.service.PlanMetaLocalService getPlanMetaLocalService() {
        return planMetaLocalService;
    }

    /**
     * Sets the plan meta local service.
     *
     * @param planMetaLocalService the plan meta local service
     */
    public void setPlanMetaLocalService(
        com.ext.portlet.service.PlanMetaLocalService planMetaLocalService) {
        this.planMetaLocalService = planMetaLocalService;
    }

    /**
     * Returns the plan meta remote service.
     *
     * @return the plan meta remote service
     */
    public com.ext.portlet.service.PlanMetaService getPlanMetaService() {
        return planMetaService;
    }

    /**
     * Sets the plan meta remote service.
     *
     * @param planMetaService the plan meta remote service
     */
    public void setPlanMetaService(
        com.ext.portlet.service.PlanMetaService planMetaService) {
        this.planMetaService = planMetaService;
    }

    /**
     * Returns the plan meta persistence.
     *
     * @return the plan meta persistence
     */
    public PlanMetaPersistence getPlanMetaPersistence() {
        return planMetaPersistence;
    }

    /**
     * Sets the plan meta persistence.
     *
     * @param planMetaPersistence the plan meta persistence
     */
    public void setPlanMetaPersistence(PlanMetaPersistence planMetaPersistence) {
        this.planMetaPersistence = planMetaPersistence;
    }

    /**
     * Returns the plan model run local service.
     *
     * @return the plan model run local service
     */
    public com.ext.portlet.service.PlanModelRunLocalService getPlanModelRunLocalService() {
        return planModelRunLocalService;
    }

    /**
     * Sets the plan model run local service.
     *
     * @param planModelRunLocalService the plan model run local service
     */
    public void setPlanModelRunLocalService(
        com.ext.portlet.service.PlanModelRunLocalService planModelRunLocalService) {
        this.planModelRunLocalService = planModelRunLocalService;
    }

    /**
     * Returns the plan model run remote service.
     *
     * @return the plan model run remote service
     */
    public com.ext.portlet.service.PlanModelRunService getPlanModelRunService() {
        return planModelRunService;
    }

    /**
     * Sets the plan model run remote service.
     *
     * @param planModelRunService the plan model run remote service
     */
    public void setPlanModelRunService(
        com.ext.portlet.service.PlanModelRunService planModelRunService) {
        this.planModelRunService = planModelRunService;
    }

    /**
     * Returns the plan model run persistence.
     *
     * @return the plan model run persistence
     */
    public PlanModelRunPersistence getPlanModelRunPersistence() {
        return planModelRunPersistence;
    }

    /**
     * Sets the plan model run persistence.
     *
     * @param planModelRunPersistence the plan model run persistence
     */
    public void setPlanModelRunPersistence(
        PlanModelRunPersistence planModelRunPersistence) {
        this.planModelRunPersistence = planModelRunPersistence;
    }

    /**
     * Returns the plan position local service.
     *
     * @return the plan position local service
     */
    public com.ext.portlet.service.PlanPositionLocalService getPlanPositionLocalService() {
        return planPositionLocalService;
    }

    /**
     * Sets the plan position local service.
     *
     * @param planPositionLocalService the plan position local service
     */
    public void setPlanPositionLocalService(
        com.ext.portlet.service.PlanPositionLocalService planPositionLocalService) {
        this.planPositionLocalService = planPositionLocalService;
    }

    /**
     * Returns the plan position remote service.
     *
     * @return the plan position remote service
     */
    public com.ext.portlet.service.PlanPositionService getPlanPositionService() {
        return planPositionService;
    }

    /**
     * Sets the plan position remote service.
     *
     * @param planPositionService the plan position remote service
     */
    public void setPlanPositionService(
        com.ext.portlet.service.PlanPositionService planPositionService) {
        this.planPositionService = planPositionService;
    }

    /**
     * Returns the plan position persistence.
     *
     * @return the plan position persistence
     */
    public PlanPositionPersistence getPlanPositionPersistence() {
        return planPositionPersistence;
    }

    /**
     * Sets the plan position persistence.
     *
     * @param planPositionPersistence the plan position persistence
     */
    public void setPlanPositionPersistence(
        PlanPositionPersistence planPositionPersistence) {
        this.planPositionPersistence = planPositionPersistence;
    }

    /**
     * Returns the plan position item local service.
     *
     * @return the plan position item local service
     */
    public com.ext.portlet.service.PlanPositionItemLocalService getPlanPositionItemLocalService() {
        return planPositionItemLocalService;
    }

    /**
     * Sets the plan position item local service.
     *
     * @param planPositionItemLocalService the plan position item local service
     */
    public void setPlanPositionItemLocalService(
        com.ext.portlet.service.PlanPositionItemLocalService planPositionItemLocalService) {
        this.planPositionItemLocalService = planPositionItemLocalService;
    }

    /**
     * Returns the plan position item remote service.
     *
     * @return the plan position item remote service
     */
    public com.ext.portlet.service.PlanPositionItemService getPlanPositionItemService() {
        return planPositionItemService;
    }

    /**
     * Sets the plan position item remote service.
     *
     * @param planPositionItemService the plan position item remote service
     */
    public void setPlanPositionItemService(
        com.ext.portlet.service.PlanPositionItemService planPositionItemService) {
        this.planPositionItemService = planPositionItemService;
    }

    /**
     * Returns the plan position item persistence.
     *
     * @return the plan position item persistence
     */
    public PlanPositionItemPersistence getPlanPositionItemPersistence() {
        return planPositionItemPersistence;
    }

    /**
     * Sets the plan position item persistence.
     *
     * @param planPositionItemPersistence the plan position item persistence
     */
    public void setPlanPositionItemPersistence(
        PlanPositionItemPersistence planPositionItemPersistence) {
        this.planPositionItemPersistence = planPositionItemPersistence;
    }

    /**
     * Returns the plan positions local service.
     *
     * @return the plan positions local service
     */
    public com.ext.portlet.service.PlanPositionsLocalService getPlanPositionsLocalService() {
        return planPositionsLocalService;
    }

    /**
     * Sets the plan positions local service.
     *
     * @param planPositionsLocalService the plan positions local service
     */
    public void setPlanPositionsLocalService(
        com.ext.portlet.service.PlanPositionsLocalService planPositionsLocalService) {
        this.planPositionsLocalService = planPositionsLocalService;
    }

    /**
     * Returns the plan positions remote service.
     *
     * @return the plan positions remote service
     */
    public com.ext.portlet.service.PlanPositionsService getPlanPositionsService() {
        return planPositionsService;
    }

    /**
     * Sets the plan positions remote service.
     *
     * @param planPositionsService the plan positions remote service
     */
    public void setPlanPositionsService(
        com.ext.portlet.service.PlanPositionsService planPositionsService) {
        this.planPositionsService = planPositionsService;
    }

    /**
     * Returns the plan positions persistence.
     *
     * @return the plan positions persistence
     */
    public PlanPositionsPersistence getPlanPositionsPersistence() {
        return planPositionsPersistence;
    }

    /**
     * Sets the plan positions persistence.
     *
     * @param planPositionsPersistence the plan positions persistence
     */
    public void setPlanPositionsPersistence(
        PlanPositionsPersistence planPositionsPersistence) {
        this.planPositionsPersistence = planPositionsPersistence;
    }

    /**
     * Returns the plan property filter local service.
     *
     * @return the plan property filter local service
     */
    public com.ext.portlet.service.PlanPropertyFilterLocalService getPlanPropertyFilterLocalService() {
        return planPropertyFilterLocalService;
    }

    /**
     * Sets the plan property filter local service.
     *
     * @param planPropertyFilterLocalService the plan property filter local service
     */
    public void setPlanPropertyFilterLocalService(
        com.ext.portlet.service.PlanPropertyFilterLocalService planPropertyFilterLocalService) {
        this.planPropertyFilterLocalService = planPropertyFilterLocalService;
    }

    /**
     * Returns the plan property filter remote service.
     *
     * @return the plan property filter remote service
     */
    public com.ext.portlet.service.PlanPropertyFilterService getPlanPropertyFilterService() {
        return planPropertyFilterService;
    }

    /**
     * Sets the plan property filter remote service.
     *
     * @param planPropertyFilterService the plan property filter remote service
     */
    public void setPlanPropertyFilterService(
        com.ext.portlet.service.PlanPropertyFilterService planPropertyFilterService) {
        this.planPropertyFilterService = planPropertyFilterService;
    }

    /**
     * Returns the plan property filter persistence.
     *
     * @return the plan property filter persistence
     */
    public PlanPropertyFilterPersistence getPlanPropertyFilterPersistence() {
        return planPropertyFilterPersistence;
    }

    /**
     * Sets the plan property filter persistence.
     *
     * @param planPropertyFilterPersistence the plan property filter persistence
     */
    public void setPlanPropertyFilterPersistence(
        PlanPropertyFilterPersistence planPropertyFilterPersistence) {
        this.planPropertyFilterPersistence = planPropertyFilterPersistence;
    }

    /**
     * Returns the plan related local service.
     *
     * @return the plan related local service
     */
    public com.ext.portlet.service.PlanRelatedLocalService getPlanRelatedLocalService() {
        return planRelatedLocalService;
    }

    /**
     * Sets the plan related local service.
     *
     * @param planRelatedLocalService the plan related local service
     */
    public void setPlanRelatedLocalService(
        com.ext.portlet.service.PlanRelatedLocalService planRelatedLocalService) {
        this.planRelatedLocalService = planRelatedLocalService;
    }

    /**
     * Returns the plan related remote service.
     *
     * @return the plan related remote service
     */
    public com.ext.portlet.service.PlanRelatedService getPlanRelatedService() {
        return planRelatedService;
    }

    /**
     * Sets the plan related remote service.
     *
     * @param planRelatedService the plan related remote service
     */
    public void setPlanRelatedService(
        com.ext.portlet.service.PlanRelatedService planRelatedService) {
        this.planRelatedService = planRelatedService;
    }

    /**
     * Returns the plan related persistence.
     *
     * @return the plan related persistence
     */
    public PlanRelatedPersistence getPlanRelatedPersistence() {
        return planRelatedPersistence;
    }

    /**
     * Sets the plan related persistence.
     *
     * @param planRelatedPersistence the plan related persistence
     */
    public void setPlanRelatedPersistence(
        PlanRelatedPersistence planRelatedPersistence) {
        this.planRelatedPersistence = planRelatedPersistence;
    }

    /**
     * Returns the plan section local service.
     *
     * @return the plan section local service
     */
    public com.ext.portlet.service.PlanSectionLocalService getPlanSectionLocalService() {
        return planSectionLocalService;
    }

    /**
     * Sets the plan section local service.
     *
     * @param planSectionLocalService the plan section local service
     */
    public void setPlanSectionLocalService(
        com.ext.portlet.service.PlanSectionLocalService planSectionLocalService) {
        this.planSectionLocalService = planSectionLocalService;
    }

    /**
     * Returns the plan section remote service.
     *
     * @return the plan section remote service
     */
    public com.ext.portlet.service.PlanSectionService getPlanSectionService() {
        return planSectionService;
    }

    /**
     * Sets the plan section remote service.
     *
     * @param planSectionService the plan section remote service
     */
    public void setPlanSectionService(
        com.ext.portlet.service.PlanSectionService planSectionService) {
        this.planSectionService = planSectionService;
    }

    /**
     * Returns the plan section persistence.
     *
     * @return the plan section persistence
     */
    public PlanSectionPersistence getPlanSectionPersistence() {
        return planSectionPersistence;
    }

    /**
     * Sets the plan section persistence.
     *
     * @param planSectionPersistence the plan section persistence
     */
    public void setPlanSectionPersistence(
        PlanSectionPersistence planSectionPersistence) {
        this.planSectionPersistence = planSectionPersistence;
    }

    /**
     * Returns the plan section definition local service.
     *
     * @return the plan section definition local service
     */
    public com.ext.portlet.service.PlanSectionDefinitionLocalService getPlanSectionDefinitionLocalService() {
        return planSectionDefinitionLocalService;
    }

    /**
     * Sets the plan section definition local service.
     *
     * @param planSectionDefinitionLocalService the plan section definition local service
     */
    public void setPlanSectionDefinitionLocalService(
        com.ext.portlet.service.PlanSectionDefinitionLocalService planSectionDefinitionLocalService) {
        this.planSectionDefinitionLocalService = planSectionDefinitionLocalService;
    }

    /**
     * Returns the plan section definition remote service.
     *
     * @return the plan section definition remote service
     */
    public com.ext.portlet.service.PlanSectionDefinitionService getPlanSectionDefinitionService() {
        return planSectionDefinitionService;
    }

    /**
     * Sets the plan section definition remote service.
     *
     * @param planSectionDefinitionService the plan section definition remote service
     */
    public void setPlanSectionDefinitionService(
        com.ext.portlet.service.PlanSectionDefinitionService planSectionDefinitionService) {
        this.planSectionDefinitionService = planSectionDefinitionService;
    }

    /**
     * Returns the plan section definition persistence.
     *
     * @return the plan section definition persistence
     */
    public PlanSectionDefinitionPersistence getPlanSectionDefinitionPersistence() {
        return planSectionDefinitionPersistence;
    }

    /**
     * Sets the plan section definition persistence.
     *
     * @param planSectionDefinitionPersistence the plan section definition persistence
     */
    public void setPlanSectionDefinitionPersistence(
        PlanSectionDefinitionPersistence planSectionDefinitionPersistence) {
        this.planSectionDefinitionPersistence = planSectionDefinitionPersistence;
    }

    /**
     * Returns the plan section plan map local service.
     *
     * @return the plan section plan map local service
     */
    public com.ext.portlet.service.PlanSectionPlanMapLocalService getPlanSectionPlanMapLocalService() {
        return planSectionPlanMapLocalService;
    }

    /**
     * Sets the plan section plan map local service.
     *
     * @param planSectionPlanMapLocalService the plan section plan map local service
     */
    public void setPlanSectionPlanMapLocalService(
        com.ext.portlet.service.PlanSectionPlanMapLocalService planSectionPlanMapLocalService) {
        this.planSectionPlanMapLocalService = planSectionPlanMapLocalService;
    }

    /**
     * Returns the plan section plan map remote service.
     *
     * @return the plan section plan map remote service
     */
    public com.ext.portlet.service.PlanSectionPlanMapService getPlanSectionPlanMapService() {
        return planSectionPlanMapService;
    }

    /**
     * Sets the plan section plan map remote service.
     *
     * @param planSectionPlanMapService the plan section plan map remote service
     */
    public void setPlanSectionPlanMapService(
        com.ext.portlet.service.PlanSectionPlanMapService planSectionPlanMapService) {
        this.planSectionPlanMapService = planSectionPlanMapService;
    }

    /**
     * Returns the plan section plan map persistence.
     *
     * @return the plan section plan map persistence
     */
    public PlanSectionPlanMapPersistence getPlanSectionPlanMapPersistence() {
        return planSectionPlanMapPersistence;
    }

    /**
     * Sets the plan section plan map persistence.
     *
     * @param planSectionPlanMapPersistence the plan section plan map persistence
     */
    public void setPlanSectionPlanMapPersistence(
        PlanSectionPlanMapPersistence planSectionPlanMapPersistence) {
        this.planSectionPlanMapPersistence = planSectionPlanMapPersistence;
    }

    /**
     * Returns the plans filter local service.
     *
     * @return the plans filter local service
     */
    public com.ext.portlet.service.PlansFilterLocalService getPlansFilterLocalService() {
        return plansFilterLocalService;
    }

    /**
     * Sets the plans filter local service.
     *
     * @param plansFilterLocalService the plans filter local service
     */
    public void setPlansFilterLocalService(
        com.ext.portlet.service.PlansFilterLocalService plansFilterLocalService) {
        this.plansFilterLocalService = plansFilterLocalService;
    }

    /**
     * Returns the plans filter remote service.
     *
     * @return the plans filter remote service
     */
    public com.ext.portlet.service.PlansFilterService getPlansFilterService() {
        return plansFilterService;
    }

    /**
     * Sets the plans filter remote service.
     *
     * @param plansFilterService the plans filter remote service
     */
    public void setPlansFilterService(
        com.ext.portlet.service.PlansFilterService plansFilterService) {
        this.plansFilterService = plansFilterService;
    }

    /**
     * Returns the plans filter persistence.
     *
     * @return the plans filter persistence
     */
    public PlansFilterPersistence getPlansFilterPersistence() {
        return plansFilterPersistence;
    }

    /**
     * Sets the plans filter persistence.
     *
     * @param plansFilterPersistence the plans filter persistence
     */
    public void setPlansFilterPersistence(
        PlansFilterPersistence plansFilterPersistence) {
        this.plansFilterPersistence = plansFilterPersistence;
    }

    /**
     * Returns the plans filter position local service.
     *
     * @return the plans filter position local service
     */
    public com.ext.portlet.service.PlansFilterPositionLocalService getPlansFilterPositionLocalService() {
        return plansFilterPositionLocalService;
    }

    /**
     * Sets the plans filter position local service.
     *
     * @param plansFilterPositionLocalService the plans filter position local service
     */
    public void setPlansFilterPositionLocalService(
        com.ext.portlet.service.PlansFilterPositionLocalService plansFilterPositionLocalService) {
        this.plansFilterPositionLocalService = plansFilterPositionLocalService;
    }

    /**
     * Returns the plans filter position remote service.
     *
     * @return the plans filter position remote service
     */
    public com.ext.portlet.service.PlansFilterPositionService getPlansFilterPositionService() {
        return plansFilterPositionService;
    }

    /**
     * Sets the plans filter position remote service.
     *
     * @param plansFilterPositionService the plans filter position remote service
     */
    public void setPlansFilterPositionService(
        com.ext.portlet.service.PlansFilterPositionService plansFilterPositionService) {
        this.plansFilterPositionService = plansFilterPositionService;
    }

    /**
     * Returns the plans filter position persistence.
     *
     * @return the plans filter position persistence
     */
    public PlansFilterPositionPersistence getPlansFilterPositionPersistence() {
        return plansFilterPositionPersistence;
    }

    /**
     * Sets the plans filter position persistence.
     *
     * @param plansFilterPositionPersistence the plans filter position persistence
     */
    public void setPlansFilterPositionPersistence(
        PlansFilterPositionPersistence plansFilterPositionPersistence) {
        this.plansFilterPositionPersistence = plansFilterPositionPersistence;
    }

    /**
     * Returns the plans user settings local service.
     *
     * @return the plans user settings local service
     */
    public com.ext.portlet.service.PlansUserSettingsLocalService getPlansUserSettingsLocalService() {
        return plansUserSettingsLocalService;
    }

    /**
     * Sets the plans user settings local service.
     *
     * @param plansUserSettingsLocalService the plans user settings local service
     */
    public void setPlansUserSettingsLocalService(
        com.ext.portlet.service.PlansUserSettingsLocalService plansUserSettingsLocalService) {
        this.plansUserSettingsLocalService = plansUserSettingsLocalService;
    }

    /**
     * Returns the plans user settings remote service.
     *
     * @return the plans user settings remote service
     */
    public com.ext.portlet.service.PlansUserSettingsService getPlansUserSettingsService() {
        return plansUserSettingsService;
    }

    /**
     * Sets the plans user settings remote service.
     *
     * @param plansUserSettingsService the plans user settings remote service
     */
    public void setPlansUserSettingsService(
        com.ext.portlet.service.PlansUserSettingsService plansUserSettingsService) {
        this.plansUserSettingsService = plansUserSettingsService;
    }

    /**
     * Returns the plans user settings persistence.
     *
     * @return the plans user settings persistence
     */
    public PlansUserSettingsPersistence getPlansUserSettingsPersistence() {
        return plansUserSettingsPersistence;
    }

    /**
     * Sets the plans user settings persistence.
     *
     * @param plansUserSettingsPersistence the plans user settings persistence
     */
    public void setPlansUserSettingsPersistence(
        PlansUserSettingsPersistence plansUserSettingsPersistence) {
        this.plansUserSettingsPersistence = plansUserSettingsPersistence;
    }

    /**
     * Returns the plan team history local service.
     *
     * @return the plan team history local service
     */
    public com.ext.portlet.service.PlanTeamHistoryLocalService getPlanTeamHistoryLocalService() {
        return planTeamHistoryLocalService;
    }

    /**
     * Sets the plan team history local service.
     *
     * @param planTeamHistoryLocalService the plan team history local service
     */
    public void setPlanTeamHistoryLocalService(
        com.ext.portlet.service.PlanTeamHistoryLocalService planTeamHistoryLocalService) {
        this.planTeamHistoryLocalService = planTeamHistoryLocalService;
    }

    /**
     * Returns the plan team history remote service.
     *
     * @return the plan team history remote service
     */
    public com.ext.portlet.service.PlanTeamHistoryService getPlanTeamHistoryService() {
        return planTeamHistoryService;
    }

    /**
     * Sets the plan team history remote service.
     *
     * @param planTeamHistoryService the plan team history remote service
     */
    public void setPlanTeamHistoryService(
        com.ext.portlet.service.PlanTeamHistoryService planTeamHistoryService) {
        this.planTeamHistoryService = planTeamHistoryService;
    }

    /**
     * Returns the plan team history persistence.
     *
     * @return the plan team history persistence
     */
    public PlanTeamHistoryPersistence getPlanTeamHistoryPersistence() {
        return planTeamHistoryPersistence;
    }

    /**
     * Sets the plan team history persistence.
     *
     * @param planTeamHistoryPersistence the plan team history persistence
     */
    public void setPlanTeamHistoryPersistence(
        PlanTeamHistoryPersistence planTeamHistoryPersistence) {
        this.planTeamHistoryPersistence = planTeamHistoryPersistence;
    }

    /**
     * Returns the plan template local service.
     *
     * @return the plan template local service
     */
    public com.ext.portlet.service.PlanTemplateLocalService getPlanTemplateLocalService() {
        return planTemplateLocalService;
    }

    /**
     * Sets the plan template local service.
     *
     * @param planTemplateLocalService the plan template local service
     */
    public void setPlanTemplateLocalService(
        com.ext.portlet.service.PlanTemplateLocalService planTemplateLocalService) {
        this.planTemplateLocalService = planTemplateLocalService;
    }

    /**
     * Returns the plan template remote service.
     *
     * @return the plan template remote service
     */
    public com.ext.portlet.service.PlanTemplateService getPlanTemplateService() {
        return planTemplateService;
    }

    /**
     * Sets the plan template remote service.
     *
     * @param planTemplateService the plan template remote service
     */
    public void setPlanTemplateService(
        com.ext.portlet.service.PlanTemplateService planTemplateService) {
        this.planTemplateService = planTemplateService;
    }

    /**
     * Returns the plan template persistence.
     *
     * @return the plan template persistence
     */
    public PlanTemplatePersistence getPlanTemplatePersistence() {
        return planTemplatePersistence;
    }

    /**
     * Sets the plan template persistence.
     *
     * @param planTemplatePersistence the plan template persistence
     */
    public void setPlanTemplatePersistence(
        PlanTemplatePersistence planTemplatePersistence) {
        this.planTemplatePersistence = planTemplatePersistence;
    }

    /**
     * Returns the plan template section local service.
     *
     * @return the plan template section local service
     */
    public com.ext.portlet.service.PlanTemplateSectionLocalService getPlanTemplateSectionLocalService() {
        return planTemplateSectionLocalService;
    }

    /**
     * Sets the plan template section local service.
     *
     * @param planTemplateSectionLocalService the plan template section local service
     */
    public void setPlanTemplateSectionLocalService(
        com.ext.portlet.service.PlanTemplateSectionLocalService planTemplateSectionLocalService) {
        this.planTemplateSectionLocalService = planTemplateSectionLocalService;
    }

    /**
     * Returns the plan template section remote service.
     *
     * @return the plan template section remote service
     */
    public com.ext.portlet.service.PlanTemplateSectionService getPlanTemplateSectionService() {
        return planTemplateSectionService;
    }

    /**
     * Sets the plan template section remote service.
     *
     * @param planTemplateSectionService the plan template section remote service
     */
    public void setPlanTemplateSectionService(
        com.ext.portlet.service.PlanTemplateSectionService planTemplateSectionService) {
        this.planTemplateSectionService = planTemplateSectionService;
    }

    /**
     * Returns the plan template section persistence.
     *
     * @return the plan template section persistence
     */
    public PlanTemplateSectionPersistence getPlanTemplateSectionPersistence() {
        return planTemplateSectionPersistence;
    }

    /**
     * Sets the plan template section persistence.
     *
     * @param planTemplateSectionPersistence the plan template section persistence
     */
    public void setPlanTemplateSectionPersistence(
        PlanTemplateSectionPersistence planTemplateSectionPersistence) {
        this.planTemplateSectionPersistence = planTemplateSectionPersistence;
    }

    /**
     * Returns the plan type local service.
     *
     * @return the plan type local service
     */
    public com.ext.portlet.service.PlanTypeLocalService getPlanTypeLocalService() {
        return planTypeLocalService;
    }

    /**
     * Sets the plan type local service.
     *
     * @param planTypeLocalService the plan type local service
     */
    public void setPlanTypeLocalService(
        com.ext.portlet.service.PlanTypeLocalService planTypeLocalService) {
        this.planTypeLocalService = planTypeLocalService;
    }

    /**
     * Returns the plan type remote service.
     *
     * @return the plan type remote service
     */
    public com.ext.portlet.service.PlanTypeService getPlanTypeService() {
        return planTypeService;
    }

    /**
     * Sets the plan type remote service.
     *
     * @param planTypeService the plan type remote service
     */
    public void setPlanTypeService(
        com.ext.portlet.service.PlanTypeService planTypeService) {
        this.planTypeService = planTypeService;
    }

    /**
     * Returns the plan type persistence.
     *
     * @return the plan type persistence
     */
    public PlanTypePersistence getPlanTypePersistence() {
        return planTypePersistence;
    }

    /**
     * Sets the plan type persistence.
     *
     * @param planTypePersistence the plan type persistence
     */
    public void setPlanTypePersistence(PlanTypePersistence planTypePersistence) {
        this.planTypePersistence = planTypePersistence;
    }

    /**
     * Returns the plan type attribute local service.
     *
     * @return the plan type attribute local service
     */
    public com.ext.portlet.service.PlanTypeAttributeLocalService getPlanTypeAttributeLocalService() {
        return planTypeAttributeLocalService;
    }

    /**
     * Sets the plan type attribute local service.
     *
     * @param planTypeAttributeLocalService the plan type attribute local service
     */
    public void setPlanTypeAttributeLocalService(
        com.ext.portlet.service.PlanTypeAttributeLocalService planTypeAttributeLocalService) {
        this.planTypeAttributeLocalService = planTypeAttributeLocalService;
    }

    /**
     * Returns the plan type attribute remote service.
     *
     * @return the plan type attribute remote service
     */
    public com.ext.portlet.service.PlanTypeAttributeService getPlanTypeAttributeService() {
        return planTypeAttributeService;
    }

    /**
     * Sets the plan type attribute remote service.
     *
     * @param planTypeAttributeService the plan type attribute remote service
     */
    public void setPlanTypeAttributeService(
        com.ext.portlet.service.PlanTypeAttributeService planTypeAttributeService) {
        this.planTypeAttributeService = planTypeAttributeService;
    }

    /**
     * Returns the plan type attribute persistence.
     *
     * @return the plan type attribute persistence
     */
    public PlanTypeAttributePersistence getPlanTypeAttributePersistence() {
        return planTypeAttributePersistence;
    }

    /**
     * Sets the plan type attribute persistence.
     *
     * @param planTypeAttributePersistence the plan type attribute persistence
     */
    public void setPlanTypeAttributePersistence(
        PlanTypeAttributePersistence planTypeAttributePersistence) {
        this.planTypeAttributePersistence = planTypeAttributePersistence;
    }

    /**
     * Returns the plan type column local service.
     *
     * @return the plan type column local service
     */
    public com.ext.portlet.service.PlanTypeColumnLocalService getPlanTypeColumnLocalService() {
        return planTypeColumnLocalService;
    }

    /**
     * Sets the plan type column local service.
     *
     * @param planTypeColumnLocalService the plan type column local service
     */
    public void setPlanTypeColumnLocalService(
        com.ext.portlet.service.PlanTypeColumnLocalService planTypeColumnLocalService) {
        this.planTypeColumnLocalService = planTypeColumnLocalService;
    }

    /**
     * Returns the plan type column remote service.
     *
     * @return the plan type column remote service
     */
    public com.ext.portlet.service.PlanTypeColumnService getPlanTypeColumnService() {
        return planTypeColumnService;
    }

    /**
     * Sets the plan type column remote service.
     *
     * @param planTypeColumnService the plan type column remote service
     */
    public void setPlanTypeColumnService(
        com.ext.portlet.service.PlanTypeColumnService planTypeColumnService) {
        this.planTypeColumnService = planTypeColumnService;
    }

    /**
     * Returns the plan type column persistence.
     *
     * @return the plan type column persistence
     */
    public PlanTypeColumnPersistence getPlanTypeColumnPersistence() {
        return planTypeColumnPersistence;
    }

    /**
     * Sets the plan type column persistence.
     *
     * @param planTypeColumnPersistence the plan type column persistence
     */
    public void setPlanTypeColumnPersistence(
        PlanTypeColumnPersistence planTypeColumnPersistence) {
        this.planTypeColumnPersistence = planTypeColumnPersistence;
    }

    /**
     * Returns the plan vote local service.
     *
     * @return the plan vote local service
     */
    public com.ext.portlet.service.PlanVoteLocalService getPlanVoteLocalService() {
        return planVoteLocalService;
    }

    /**
     * Sets the plan vote local service.
     *
     * @param planVoteLocalService the plan vote local service
     */
    public void setPlanVoteLocalService(
        com.ext.portlet.service.PlanVoteLocalService planVoteLocalService) {
        this.planVoteLocalService = planVoteLocalService;
    }

    /**
     * Returns the plan vote remote service.
     *
     * @return the plan vote remote service
     */
    public com.ext.portlet.service.PlanVoteService getPlanVoteService() {
        return planVoteService;
    }

    /**
     * Sets the plan vote remote service.
     *
     * @param planVoteService the plan vote remote service
     */
    public void setPlanVoteService(
        com.ext.portlet.service.PlanVoteService planVoteService) {
        this.planVoteService = planVoteService;
    }

    /**
     * Returns the plan vote persistence.
     *
     * @return the plan vote persistence
     */
    public PlanVotePersistence getPlanVotePersistence() {
        return planVotePersistence;
    }

    /**
     * Sets the plan vote persistence.
     *
     * @param planVotePersistence the plan vote persistence
     */
    public void setPlanVotePersistence(PlanVotePersistence planVotePersistence) {
        this.planVotePersistence = planVotePersistence;
    }

    /**
     * Returns the proposal local service.
     *
     * @return the proposal local service
     */
    public com.ext.portlet.service.ProposalLocalService getProposalLocalService() {
        return proposalLocalService;
    }

    /**
     * Sets the proposal local service.
     *
     * @param proposalLocalService the proposal local service
     */
    public void setProposalLocalService(
        com.ext.portlet.service.ProposalLocalService proposalLocalService) {
        this.proposalLocalService = proposalLocalService;
    }

    /**
     * Returns the proposal remote service.
     *
     * @return the proposal remote service
     */
    public com.ext.portlet.service.ProposalService getProposalService() {
        return proposalService;
    }

    /**
     * Sets the proposal remote service.
     *
     * @param proposalService the proposal remote service
     */
    public void setProposalService(
        com.ext.portlet.service.ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    /**
     * Returns the proposal persistence.
     *
     * @return the proposal persistence
     */
    public ProposalPersistence getProposalPersistence() {
        return proposalPersistence;
    }

    /**
     * Sets the proposal persistence.
     *
     * @param proposalPersistence the proposal persistence
     */
    public void setProposalPersistence(ProposalPersistence proposalPersistence) {
        this.proposalPersistence = proposalPersistence;
    }

    /**
     * Returns the proposal2 phase local service.
     *
     * @return the proposal2 phase local service
     */
    public com.ext.portlet.service.Proposal2PhaseLocalService getProposal2PhaseLocalService() {
        return proposal2PhaseLocalService;
    }

    /**
     * Sets the proposal2 phase local service.
     *
     * @param proposal2PhaseLocalService the proposal2 phase local service
     */
    public void setProposal2PhaseLocalService(
        com.ext.portlet.service.Proposal2PhaseLocalService proposal2PhaseLocalService) {
        this.proposal2PhaseLocalService = proposal2PhaseLocalService;
    }

    /**
     * Returns the proposal2 phase remote service.
     *
     * @return the proposal2 phase remote service
     */
    public com.ext.portlet.service.Proposal2PhaseService getProposal2PhaseService() {
        return proposal2PhaseService;
    }

    /**
     * Sets the proposal2 phase remote service.
     *
     * @param proposal2PhaseService the proposal2 phase remote service
     */
    public void setProposal2PhaseService(
        com.ext.portlet.service.Proposal2PhaseService proposal2PhaseService) {
        this.proposal2PhaseService = proposal2PhaseService;
    }

    /**
     * Returns the proposal2 phase persistence.
     *
     * @return the proposal2 phase persistence
     */
    public Proposal2PhasePersistence getProposal2PhasePersistence() {
        return proposal2PhasePersistence;
    }

    /**
     * Sets the proposal2 phase persistence.
     *
     * @param proposal2PhasePersistence the proposal2 phase persistence
     */
    public void setProposal2PhasePersistence(
        Proposal2PhasePersistence proposal2PhasePersistence) {
        this.proposal2PhasePersistence = proposal2PhasePersistence;
    }

    /**
     * Returns the proposal attribute local service.
     *
     * @return the proposal attribute local service
     */
    public com.ext.portlet.service.ProposalAttributeLocalService getProposalAttributeLocalService() {
        return proposalAttributeLocalService;
    }

    /**
     * Sets the proposal attribute local service.
     *
     * @param proposalAttributeLocalService the proposal attribute local service
     */
    public void setProposalAttributeLocalService(
        com.ext.portlet.service.ProposalAttributeLocalService proposalAttributeLocalService) {
        this.proposalAttributeLocalService = proposalAttributeLocalService;
    }

    /**
     * Returns the proposal attribute remote service.
     *
     * @return the proposal attribute remote service
     */
    public com.ext.portlet.service.ProposalAttributeService getProposalAttributeService() {
        return proposalAttributeService;
    }

    /**
     * Sets the proposal attribute remote service.
     *
     * @param proposalAttributeService the proposal attribute remote service
     */
    public void setProposalAttributeService(
        com.ext.portlet.service.ProposalAttributeService proposalAttributeService) {
        this.proposalAttributeService = proposalAttributeService;
    }

    /**
     * Returns the proposal attribute persistence.
     *
     * @return the proposal attribute persistence
     */
    public ProposalAttributePersistence getProposalAttributePersistence() {
        return proposalAttributePersistence;
    }

    /**
     * Sets the proposal attribute persistence.
     *
     * @param proposalAttributePersistence the proposal attribute persistence
     */
    public void setProposalAttributePersistence(
        ProposalAttributePersistence proposalAttributePersistence) {
        this.proposalAttributePersistence = proposalAttributePersistence;
    }

    /**
     * Returns the proposal attribute type local service.
     *
     * @return the proposal attribute type local service
     */
    public com.ext.portlet.service.ProposalAttributeTypeLocalService getProposalAttributeTypeLocalService() {
        return proposalAttributeTypeLocalService;
    }

    /**
     * Sets the proposal attribute type local service.
     *
     * @param proposalAttributeTypeLocalService the proposal attribute type local service
     */
    public void setProposalAttributeTypeLocalService(
        com.ext.portlet.service.ProposalAttributeTypeLocalService proposalAttributeTypeLocalService) {
        this.proposalAttributeTypeLocalService = proposalAttributeTypeLocalService;
    }

    /**
     * Returns the proposal attribute type remote service.
     *
     * @return the proposal attribute type remote service
     */
    public com.ext.portlet.service.ProposalAttributeTypeService getProposalAttributeTypeService() {
        return proposalAttributeTypeService;
    }

    /**
     * Sets the proposal attribute type remote service.
     *
     * @param proposalAttributeTypeService the proposal attribute type remote service
     */
    public void setProposalAttributeTypeService(
        com.ext.portlet.service.ProposalAttributeTypeService proposalAttributeTypeService) {
        this.proposalAttributeTypeService = proposalAttributeTypeService;
    }

    /**
     * Returns the proposal attribute type persistence.
     *
     * @return the proposal attribute type persistence
     */
    public ProposalAttributeTypePersistence getProposalAttributeTypePersistence() {
        return proposalAttributeTypePersistence;
    }

    /**
     * Sets the proposal attribute type persistence.
     *
     * @param proposalAttributeTypePersistence the proposal attribute type persistence
     */
    public void setProposalAttributeTypePersistence(
        ProposalAttributeTypePersistence proposalAttributeTypePersistence) {
        this.proposalAttributeTypePersistence = proposalAttributeTypePersistence;
    }

    /**
     * Returns the proposal contest phase attribute local service.
     *
     * @return the proposal contest phase attribute local service
     */
    public com.ext.portlet.service.ProposalContestPhaseAttributeLocalService getProposalContestPhaseAttributeLocalService() {
        return proposalContestPhaseAttributeLocalService;
    }

    /**
     * Sets the proposal contest phase attribute local service.
     *
     * @param proposalContestPhaseAttributeLocalService the proposal contest phase attribute local service
     */
    public void setProposalContestPhaseAttributeLocalService(
        com.ext.portlet.service.ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService) {
        this.proposalContestPhaseAttributeLocalService = proposalContestPhaseAttributeLocalService;
    }

    /**
     * Returns the proposal contest phase attribute remote service.
     *
     * @return the proposal contest phase attribute remote service
     */
    public com.ext.portlet.service.ProposalContestPhaseAttributeService getProposalContestPhaseAttributeService() {
        return proposalContestPhaseAttributeService;
    }

    /**
     * Sets the proposal contest phase attribute remote service.
     *
     * @param proposalContestPhaseAttributeService the proposal contest phase attribute remote service
     */
    public void setProposalContestPhaseAttributeService(
        com.ext.portlet.service.ProposalContestPhaseAttributeService proposalContestPhaseAttributeService) {
        this.proposalContestPhaseAttributeService = proposalContestPhaseAttributeService;
    }

    /**
     * Returns the proposal contest phase attribute persistence.
     *
     * @return the proposal contest phase attribute persistence
     */
    public ProposalContestPhaseAttributePersistence getProposalContestPhaseAttributePersistence() {
        return proposalContestPhaseAttributePersistence;
    }

    /**
     * Sets the proposal contest phase attribute persistence.
     *
     * @param proposalContestPhaseAttributePersistence the proposal contest phase attribute persistence
     */
    public void setProposalContestPhaseAttributePersistence(
        ProposalContestPhaseAttributePersistence proposalContestPhaseAttributePersistence) {
        this.proposalContestPhaseAttributePersistence = proposalContestPhaseAttributePersistence;
    }

    /**
     * Returns the proposal contest phase attribute type local service.
     *
     * @return the proposal contest phase attribute type local service
     */
    public com.ext.portlet.service.ProposalContestPhaseAttributeTypeLocalService getProposalContestPhaseAttributeTypeLocalService() {
        return proposalContestPhaseAttributeTypeLocalService;
    }

    /**
     * Sets the proposal contest phase attribute type local service.
     *
     * @param proposalContestPhaseAttributeTypeLocalService the proposal contest phase attribute type local service
     */
    public void setProposalContestPhaseAttributeTypeLocalService(
        com.ext.portlet.service.ProposalContestPhaseAttributeTypeLocalService proposalContestPhaseAttributeTypeLocalService) {
        this.proposalContestPhaseAttributeTypeLocalService = proposalContestPhaseAttributeTypeLocalService;
    }

    /**
     * Returns the proposal contest phase attribute type remote service.
     *
     * @return the proposal contest phase attribute type remote service
     */
    public com.ext.portlet.service.ProposalContestPhaseAttributeTypeService getProposalContestPhaseAttributeTypeService() {
        return proposalContestPhaseAttributeTypeService;
    }

    /**
     * Sets the proposal contest phase attribute type remote service.
     *
     * @param proposalContestPhaseAttributeTypeService the proposal contest phase attribute type remote service
     */
    public void setProposalContestPhaseAttributeTypeService(
        com.ext.portlet.service.ProposalContestPhaseAttributeTypeService proposalContestPhaseAttributeTypeService) {
        this.proposalContestPhaseAttributeTypeService = proposalContestPhaseAttributeTypeService;
    }

    /**
     * Returns the proposal contest phase attribute type persistence.
     *
     * @return the proposal contest phase attribute type persistence
     */
    public ProposalContestPhaseAttributeTypePersistence getProposalContestPhaseAttributeTypePersistence() {
        return proposalContestPhaseAttributeTypePersistence;
    }

    /**
     * Sets the proposal contest phase attribute type persistence.
     *
     * @param proposalContestPhaseAttributeTypePersistence the proposal contest phase attribute type persistence
     */
    public void setProposalContestPhaseAttributeTypePersistence(
        ProposalContestPhaseAttributeTypePersistence proposalContestPhaseAttributeTypePersistence) {
        this.proposalContestPhaseAttributeTypePersistence = proposalContestPhaseAttributeTypePersistence;
    }

    /**
     * Returns the proposal rating local service.
     *
     * @return the proposal rating local service
     */
    public com.ext.portlet.service.ProposalRatingLocalService getProposalRatingLocalService() {
        return proposalRatingLocalService;
    }

    /**
     * Sets the proposal rating local service.
     *
     * @param proposalRatingLocalService the proposal rating local service
     */
    public void setProposalRatingLocalService(
        com.ext.portlet.service.ProposalRatingLocalService proposalRatingLocalService) {
        this.proposalRatingLocalService = proposalRatingLocalService;
    }

    /**
     * Returns the proposal rating remote service.
     *
     * @return the proposal rating remote service
     */
    public com.ext.portlet.service.ProposalRatingService getProposalRatingService() {
        return proposalRatingService;
    }

    /**
     * Sets the proposal rating remote service.
     *
     * @param proposalRatingService the proposal rating remote service
     */
    public void setProposalRatingService(
        com.ext.portlet.service.ProposalRatingService proposalRatingService) {
        this.proposalRatingService = proposalRatingService;
    }

    /**
     * Returns the proposal rating persistence.
     *
     * @return the proposal rating persistence
     */
    public ProposalRatingPersistence getProposalRatingPersistence() {
        return proposalRatingPersistence;
    }

    /**
     * Sets the proposal rating persistence.
     *
     * @param proposalRatingPersistence the proposal rating persistence
     */
    public void setProposalRatingPersistence(
        ProposalRatingPersistence proposalRatingPersistence) {
        this.proposalRatingPersistence = proposalRatingPersistence;
    }

    /**
     * Returns the proposal rating finder.
     *
     * @return the proposal rating finder
     */
    public ProposalRatingFinder getProposalRatingFinder() {
        return proposalRatingFinder;
    }

    /**
     * Sets the proposal rating finder.
     *
     * @param proposalRatingFinder the proposal rating finder
     */
    public void setProposalRatingFinder(
        ProposalRatingFinder proposalRatingFinder) {
        this.proposalRatingFinder = proposalRatingFinder;
    }

    /**
     * Returns the proposal rating type local service.
     *
     * @return the proposal rating type local service
     */
    public com.ext.portlet.service.ProposalRatingTypeLocalService getProposalRatingTypeLocalService() {
        return proposalRatingTypeLocalService;
    }

    /**
     * Sets the proposal rating type local service.
     *
     * @param proposalRatingTypeLocalService the proposal rating type local service
     */
    public void setProposalRatingTypeLocalService(
        com.ext.portlet.service.ProposalRatingTypeLocalService proposalRatingTypeLocalService) {
        this.proposalRatingTypeLocalService = proposalRatingTypeLocalService;
    }

    /**
     * Returns the proposal rating type remote service.
     *
     * @return the proposal rating type remote service
     */
    public com.ext.portlet.service.ProposalRatingTypeService getProposalRatingTypeService() {
        return proposalRatingTypeService;
    }

    /**
     * Sets the proposal rating type remote service.
     *
     * @param proposalRatingTypeService the proposal rating type remote service
     */
    public void setProposalRatingTypeService(
        com.ext.portlet.service.ProposalRatingTypeService proposalRatingTypeService) {
        this.proposalRatingTypeService = proposalRatingTypeService;
    }

    /**
     * Returns the proposal rating type persistence.
     *
     * @return the proposal rating type persistence
     */
    public ProposalRatingTypePersistence getProposalRatingTypePersistence() {
        return proposalRatingTypePersistence;
    }

    /**
     * Sets the proposal rating type persistence.
     *
     * @param proposalRatingTypePersistence the proposal rating type persistence
     */
    public void setProposalRatingTypePersistence(
        ProposalRatingTypePersistence proposalRatingTypePersistence) {
        this.proposalRatingTypePersistence = proposalRatingTypePersistence;
    }

    /**
     * Returns the proposal rating value local service.
     *
     * @return the proposal rating value local service
     */
    public com.ext.portlet.service.ProposalRatingValueLocalService getProposalRatingValueLocalService() {
        return proposalRatingValueLocalService;
    }

    /**
     * Sets the proposal rating value local service.
     *
     * @param proposalRatingValueLocalService the proposal rating value local service
     */
    public void setProposalRatingValueLocalService(
        com.ext.portlet.service.ProposalRatingValueLocalService proposalRatingValueLocalService) {
        this.proposalRatingValueLocalService = proposalRatingValueLocalService;
    }

    /**
     * Returns the proposal rating value remote service.
     *
     * @return the proposal rating value remote service
     */
    public com.ext.portlet.service.ProposalRatingValueService getProposalRatingValueService() {
        return proposalRatingValueService;
    }

    /**
     * Sets the proposal rating value remote service.
     *
     * @param proposalRatingValueService the proposal rating value remote service
     */
    public void setProposalRatingValueService(
        com.ext.portlet.service.ProposalRatingValueService proposalRatingValueService) {
        this.proposalRatingValueService = proposalRatingValueService;
    }

    /**
     * Returns the proposal rating value persistence.
     *
     * @return the proposal rating value persistence
     */
    public ProposalRatingValuePersistence getProposalRatingValuePersistence() {
        return proposalRatingValuePersistence;
    }

    /**
     * Sets the proposal rating value persistence.
     *
     * @param proposalRatingValuePersistence the proposal rating value persistence
     */
    public void setProposalRatingValuePersistence(
        ProposalRatingValuePersistence proposalRatingValuePersistence) {
        this.proposalRatingValuePersistence = proposalRatingValuePersistence;
    }

    /**
     * Returns the proposal supporter local service.
     *
     * @return the proposal supporter local service
     */
    public com.ext.portlet.service.ProposalSupporterLocalService getProposalSupporterLocalService() {
        return proposalSupporterLocalService;
    }

    /**
     * Sets the proposal supporter local service.
     *
     * @param proposalSupporterLocalService the proposal supporter local service
     */
    public void setProposalSupporterLocalService(
        com.ext.portlet.service.ProposalSupporterLocalService proposalSupporterLocalService) {
        this.proposalSupporterLocalService = proposalSupporterLocalService;
    }

    /**
     * Returns the proposal supporter remote service.
     *
     * @return the proposal supporter remote service
     */
    public com.ext.portlet.service.ProposalSupporterService getProposalSupporterService() {
        return proposalSupporterService;
    }

    /**
     * Sets the proposal supporter remote service.
     *
     * @param proposalSupporterService the proposal supporter remote service
     */
    public void setProposalSupporterService(
        com.ext.portlet.service.ProposalSupporterService proposalSupporterService) {
        this.proposalSupporterService = proposalSupporterService;
    }

    /**
     * Returns the proposal supporter persistence.
     *
     * @return the proposal supporter persistence
     */
    public ProposalSupporterPersistence getProposalSupporterPersistence() {
        return proposalSupporterPersistence;
    }

    /**
     * Sets the proposal supporter persistence.
     *
     * @param proposalSupporterPersistence the proposal supporter persistence
     */
    public void setProposalSupporterPersistence(
        ProposalSupporterPersistence proposalSupporterPersistence) {
        this.proposalSupporterPersistence = proposalSupporterPersistence;
    }

    /**
     * Returns the proposal version local service.
     *
     * @return the proposal version local service
     */
    public com.ext.portlet.service.ProposalVersionLocalService getProposalVersionLocalService() {
        return proposalVersionLocalService;
    }

    /**
     * Sets the proposal version local service.
     *
     * @param proposalVersionLocalService the proposal version local service
     */
    public void setProposalVersionLocalService(
        com.ext.portlet.service.ProposalVersionLocalService proposalVersionLocalService) {
        this.proposalVersionLocalService = proposalVersionLocalService;
    }

    /**
     * Returns the proposal version remote service.
     *
     * @return the proposal version remote service
     */
    public com.ext.portlet.service.ProposalVersionService getProposalVersionService() {
        return proposalVersionService;
    }

    /**
     * Sets the proposal version remote service.
     *
     * @param proposalVersionService the proposal version remote service
     */
    public void setProposalVersionService(
        com.ext.portlet.service.ProposalVersionService proposalVersionService) {
        this.proposalVersionService = proposalVersionService;
    }

    /**
     * Returns the proposal version persistence.
     *
     * @return the proposal version persistence
     */
    public ProposalVersionPersistence getProposalVersionPersistence() {
        return proposalVersionPersistence;
    }

    /**
     * Sets the proposal version persistence.
     *
     * @param proposalVersionPersistence the proposal version persistence
     */
    public void setProposalVersionPersistence(
        ProposalVersionPersistence proposalVersionPersistence) {
        this.proposalVersionPersistence = proposalVersionPersistence;
    }

    /**
     * Returns the proposal vote local service.
     *
     * @return the proposal vote local service
     */
    public com.ext.portlet.service.ProposalVoteLocalService getProposalVoteLocalService() {
        return proposalVoteLocalService;
    }

    /**
     * Sets the proposal vote local service.
     *
     * @param proposalVoteLocalService the proposal vote local service
     */
    public void setProposalVoteLocalService(
        com.ext.portlet.service.ProposalVoteLocalService proposalVoteLocalService) {
        this.proposalVoteLocalService = proposalVoteLocalService;
    }

    /**
     * Returns the proposal vote remote service.
     *
     * @return the proposal vote remote service
     */
    public com.ext.portlet.service.ProposalVoteService getProposalVoteService() {
        return proposalVoteService;
    }

    /**
     * Sets the proposal vote remote service.
     *
     * @param proposalVoteService the proposal vote remote service
     */
    public void setProposalVoteService(
        com.ext.portlet.service.ProposalVoteService proposalVoteService) {
        this.proposalVoteService = proposalVoteService;
    }

    /**
     * Returns the proposal vote persistence.
     *
     * @return the proposal vote persistence
     */
    public ProposalVotePersistence getProposalVotePersistence() {
        return proposalVotePersistence;
    }

    /**
     * Sets the proposal vote persistence.
     *
     * @param proposalVotePersistence the proposal vote persistence
     */
    public void setProposalVotePersistence(
        ProposalVotePersistence proposalVotePersistence) {
        this.proposalVotePersistence = proposalVotePersistence;
    }

    /**
     * Returns the staff member local service.
     *
     * @return the staff member local service
     */
    public com.ext.portlet.service.StaffMemberLocalService getStaffMemberLocalService() {
        return staffMemberLocalService;
    }

    /**
     * Sets the staff member local service.
     *
     * @param staffMemberLocalService the staff member local service
     */
    public void setStaffMemberLocalService(
        com.ext.portlet.service.StaffMemberLocalService staffMemberLocalService) {
        this.staffMemberLocalService = staffMemberLocalService;
    }

    /**
     * Returns the staff member remote service.
     *
     * @return the staff member remote service
     */
    public com.ext.portlet.service.StaffMemberService getStaffMemberService() {
        return staffMemberService;
    }

    /**
     * Sets the staff member remote service.
     *
     * @param staffMemberService the staff member remote service
     */
    public void setStaffMemberService(
        com.ext.portlet.service.StaffMemberService staffMemberService) {
        this.staffMemberService = staffMemberService;
    }

    /**
     * Returns the staff member persistence.
     *
     * @return the staff member persistence
     */
    public StaffMemberPersistence getStaffMemberPersistence() {
        return staffMemberPersistence;
    }

    /**
     * Sets the staff member persistence.
     *
     * @param staffMemberPersistence the staff member persistence
     */
    public void setStaffMemberPersistence(
        StaffMemberPersistence staffMemberPersistence) {
        this.staffMemberPersistence = staffMemberPersistence;
    }

    /**
     * Returns the tracked visit local service.
     *
     * @return the tracked visit local service
     */
    public com.ext.portlet.service.TrackedVisitLocalService getTrackedVisitLocalService() {
        return trackedVisitLocalService;
    }

    /**
     * Sets the tracked visit local service.
     *
     * @param trackedVisitLocalService the tracked visit local service
     */
    public void setTrackedVisitLocalService(
        com.ext.portlet.service.TrackedVisitLocalService trackedVisitLocalService) {
        this.trackedVisitLocalService = trackedVisitLocalService;
    }

    /**
     * Returns the tracked visit remote service.
     *
     * @return the tracked visit remote service
     */
    public com.ext.portlet.service.TrackedVisitService getTrackedVisitService() {
        return trackedVisitService;
    }

    /**
     * Sets the tracked visit remote service.
     *
     * @param trackedVisitService the tracked visit remote service
     */
    public void setTrackedVisitService(
        com.ext.portlet.service.TrackedVisitService trackedVisitService) {
        this.trackedVisitService = trackedVisitService;
    }

    /**
     * Returns the tracked visit persistence.
     *
     * @return the tracked visit persistence
     */
    public TrackedVisitPersistence getTrackedVisitPersistence() {
        return trackedVisitPersistence;
    }

    /**
     * Sets the tracked visit persistence.
     *
     * @param trackedVisitPersistence the tracked visit persistence
     */
    public void setTrackedVisitPersistence(
        TrackedVisitPersistence trackedVisitPersistence) {
        this.trackedVisitPersistence = trackedVisitPersistence;
    }

    /**
     * Returns the tracked visitor2 user local service.
     *
     * @return the tracked visitor2 user local service
     */
    public com.ext.portlet.service.TrackedVisitor2UserLocalService getTrackedVisitor2UserLocalService() {
        return trackedVisitor2UserLocalService;
    }

    /**
     * Sets the tracked visitor2 user local service.
     *
     * @param trackedVisitor2UserLocalService the tracked visitor2 user local service
     */
    public void setTrackedVisitor2UserLocalService(
        com.ext.portlet.service.TrackedVisitor2UserLocalService trackedVisitor2UserLocalService) {
        this.trackedVisitor2UserLocalService = trackedVisitor2UserLocalService;
    }

    /**
     * Returns the tracked visitor2 user remote service.
     *
     * @return the tracked visitor2 user remote service
     */
    public com.ext.portlet.service.TrackedVisitor2UserService getTrackedVisitor2UserService() {
        return trackedVisitor2UserService;
    }

    /**
     * Sets the tracked visitor2 user remote service.
     *
     * @param trackedVisitor2UserService the tracked visitor2 user remote service
     */
    public void setTrackedVisitor2UserService(
        com.ext.portlet.service.TrackedVisitor2UserService trackedVisitor2UserService) {
        this.trackedVisitor2UserService = trackedVisitor2UserService;
    }

    /**
     * Returns the tracked visitor2 user persistence.
     *
     * @return the tracked visitor2 user persistence
     */
    public TrackedVisitor2UserPersistence getTrackedVisitor2UserPersistence() {
        return trackedVisitor2UserPersistence;
    }

    /**
     * Sets the tracked visitor2 user persistence.
     *
     * @param trackedVisitor2UserPersistence the tracked visitor2 user persistence
     */
    public void setTrackedVisitor2UserPersistence(
        TrackedVisitor2UserPersistence trackedVisitor2UserPersistence) {
        this.trackedVisitor2UserPersistence = trackedVisitor2UserPersistence;
    }

    /**
     * Returns the counter local service.
     *
     * @return the counter local service
     */
    public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
        return counterLocalService;
    }

    /**
     * Sets the counter local service.
     *
     * @param counterLocalService the counter local service
     */
    public void setCounterLocalService(
        com.liferay.counter.service.CounterLocalService counterLocalService) {
        this.counterLocalService = counterLocalService;
    }

    /**
     * Returns the resource local service.
     *
     * @return the resource local service
     */
    public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
        return resourceLocalService;
    }

    /**
     * Sets the resource local service.
     *
     * @param resourceLocalService the resource local service
     */
    public void setResourceLocalService(
        com.liferay.portal.service.ResourceLocalService resourceLocalService) {
        this.resourceLocalService = resourceLocalService;
    }

    /**
     * Returns the user local service.
     *
     * @return the user local service
     */
    public com.liferay.portal.service.UserLocalService getUserLocalService() {
        return userLocalService;
    }

    /**
     * Sets the user local service.
     *
     * @param userLocalService the user local service
     */
    public void setUserLocalService(
        com.liferay.portal.service.UserLocalService userLocalService) {
        this.userLocalService = userLocalService;
    }

    /**
     * Returns the user remote service.
     *
     * @return the user remote service
     */
    public com.liferay.portal.service.UserService getUserService() {
        return userService;
    }

    /**
     * Sets the user remote service.
     *
     * @param userService the user remote service
     */
    public void setUserService(
        com.liferay.portal.service.UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns the user persistence.
     *
     * @return the user persistence
     */
    public UserPersistence getUserPersistence() {
        return userPersistence;
    }

    /**
     * Sets the user persistence.
     *
     * @param userPersistence the user persistence
     */
    public void setUserPersistence(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    public void afterPropertiesSet() {
        Class<?> clazz = getClass();

        _classLoader = clazz.getClassLoader();
    }

    public void destroy() {
    }

    /**
     * Returns the Spring bean ID for this bean.
     *
     * @return the Spring bean ID for this bean
     */
    @Override
    public String getBeanIdentifier() {
        return _beanIdentifier;
    }

    /**
     * Sets the Spring bean ID for this bean.
     *
     * @param beanIdentifier the Spring bean ID for this bean
     */
    @Override
    public void setBeanIdentifier(String beanIdentifier) {
        _beanIdentifier = beanIdentifier;
    }

    @Override
    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        if (contextClassLoader != _classLoader) {
            currentThread.setContextClassLoader(_classLoader);
        }

        try {
            return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
        } finally {
            if (contextClassLoader != _classLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    protected Class<?> getModelClass() {
        return ContestPhaseColumn.class;
    }

    protected String getModelClassName() {
        return ContestPhaseColumn.class.getName();
    }

    /**
     * Performs an SQL query.
     *
     * @param sql the sql query
     */
    protected void runSQL(String sql) throws SystemException {
        try {
            DataSource dataSource = contestPhaseColumnPersistence.getDataSource();

            SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
                    sql, new int[0]);

            sqlUpdate.update();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
