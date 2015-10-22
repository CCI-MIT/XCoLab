package com.ext.portlet.service.messaging;

import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ActivitySubscriptionServiceUtil;
import com.ext.portlet.service.AnalyticsUserEventLocalServiceUtil;
import com.ext.portlet.service.AnalyticsUserEventServiceUtil;
import com.ext.portlet.service.BalloonLinkLocalServiceUtil;
import com.ext.portlet.service.BalloonLinkServiceUtil;
import com.ext.portlet.service.BalloonStatsEntryLocalServiceUtil;
import com.ext.portlet.service.BalloonStatsEntryServiceUtil;
import com.ext.portlet.service.BalloonTextLocalServiceUtil;
import com.ext.portlet.service.BalloonTextServiceUtil;
import com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil;
import com.ext.portlet.service.BalloonUserTrackingServiceUtil;
import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestDebateLocalServiceUtil;
import com.ext.portlet.service.ContestDebateServiceUtil;
import com.ext.portlet.service.ContestDiscussionLocalServiceUtil;
import com.ext.portlet.service.ContestDiscussionServiceUtil;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.ext.portlet.service.ContestEmailTemplateServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseColumnLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseColumnServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeServiceUtil;
import com.ext.portlet.service.ContestPhaseServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeServiceUtil;
import com.ext.portlet.service.ContestScheduleLocalServiceUtil;
import com.ext.portlet.service.ContestScheduleServiceUtil;
import com.ext.portlet.service.ContestServiceUtil;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.ext.portlet.service.ContestTeamMemberServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.ContestTypeServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryServiceUtil;
import com.ext.portlet.service.DiscussionMessageFlagLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageFlagServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageServiceUtil;
import com.ext.portlet.service.EmailListLocalServiceUtil;
import com.ext.portlet.service.EmailListServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermServiceUtil;
import com.ext.portlet.service.FocusAreaServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesDataLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesDataServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil;
import com.ext.portlet.service.ImpactDefaultSeriesServiceUtil;
import com.ext.portlet.service.ImpactIterationLocalServiceUtil;
import com.ext.portlet.service.ImpactIterationServiceUtil;
import com.ext.portlet.service.ImpactTemplateFocusAreaListLocalServiceUtil;
import com.ext.portlet.service.ImpactTemplateFocusAreaListServiceUtil;
import com.ext.portlet.service.ImpactTemplateMaxFocusAreaLocalServiceUtil;
import com.ext.portlet.service.ImpactTemplateMaxFocusAreaServiceUtil;
import com.ext.portlet.service.ImpactTemplateSeriesLocalServiceUtil;
import com.ext.portlet.service.ImpactTemplateSeriesServiceUtil;
import com.ext.portlet.service.LandingPageLocalServiceUtil;
import com.ext.portlet.service.LandingPageServiceUtil;
import com.ext.portlet.service.LoginLogLocalServiceUtil;
import com.ext.portlet.service.LoginLogServiceUtil;
import com.ext.portlet.service.MessageLocalServiceUtil;
import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;
import com.ext.portlet.service.MessageRecipientStatusServiceUtil;
import com.ext.portlet.service.MessageServiceUtil;
import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;
import com.ext.portlet.service.MessagingIgnoredRecipientsServiceUtil;
import com.ext.portlet.service.MessagingMessageConversionLocalServiceUtil;
import com.ext.portlet.service.MessagingMessageConversionServiceUtil;
import com.ext.portlet.service.MessagingMessageConversionTypeLocalServiceUtil;
import com.ext.portlet.service.MessagingMessageConversionTypeServiceUtil;
import com.ext.portlet.service.MessagingMessageLocalServiceUtil;
import com.ext.portlet.service.MessagingMessageRecipientLocalServiceUtil;
import com.ext.portlet.service.MessagingMessageRecipientServiceUtil;
import com.ext.portlet.service.MessagingMessageServiceUtil;
import com.ext.portlet.service.MessagingRedirectLinkLocalServiceUtil;
import com.ext.portlet.service.MessagingRedirectLinkServiceUtil;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;
import com.ext.portlet.service.MessagingUserPreferencesServiceUtil;
import com.ext.portlet.service.ModelCategoryLocalServiceUtil;
import com.ext.portlet.service.ModelCategoryServiceUtil;
import com.ext.portlet.service.ModelDiscussionLocalServiceUtil;
import com.ext.portlet.service.ModelDiscussionServiceUtil;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;
import com.ext.portlet.service.ModelGlobalPreferenceServiceUtil;
import com.ext.portlet.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.service.ModelInputGroupServiceUtil;
import com.ext.portlet.service.ModelInputItemLocalServiceUtil;
import com.ext.portlet.service.ModelInputItemServiceUtil;
import com.ext.portlet.service.ModelOutputChartOrderLocalServiceUtil;
import com.ext.portlet.service.ModelOutputChartOrderServiceUtil;
import com.ext.portlet.service.ModelOutputItemLocalServiceUtil;
import com.ext.portlet.service.ModelOutputItemServiceUtil;
import com.ext.portlet.service.ModelPositionLocalServiceUtil;
import com.ext.portlet.service.ModelPositionServiceUtil;
import com.ext.portlet.service.ModelRunnerLocalServiceUtil;
import com.ext.portlet.service.ModelRunnerServiceUtil;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceServiceUtil;
import com.ext.portlet.service.OntologyTermEntityLocalServiceUtil;
import com.ext.portlet.service.OntologyTermEntityServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.OntologyTermServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateSectionServiceUtil;
import com.ext.portlet.service.PlanTemplateServiceUtil;
import com.ext.portlet.service.PointDistributionTargetLocalServiceUtil;
import com.ext.portlet.service.PointDistributionTargetServiceUtil;
import com.ext.portlet.service.PointTypeLocalServiceUtil;
import com.ext.portlet.service.PointTypeServiceUtil;
import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;
import com.ext.portlet.service.PointsDistributionConfigurationServiceUtil;
import com.ext.portlet.service.PointsLocalServiceUtil;
import com.ext.portlet.service.PointsServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeServiceUtil;
import com.ext.portlet.service.ProposalAttributeTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeTypeServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeTypeServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingServiceUtil;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingTypeServiceUtil;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingValueServiceUtil;
import com.ext.portlet.service.ProposalReferenceLocalServiceUtil;
import com.ext.portlet.service.ProposalReferenceServiceUtil;
import com.ext.portlet.service.ProposalServiceUtil;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.ext.portlet.service.ProposalSupporterServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionServiceUtil;
import com.ext.portlet.service.ProposalVoteLocalServiceUtil;
import com.ext.portlet.service.ProposalVoteServiceUtil;
import com.ext.portlet.service.StaffMemberLocalServiceUtil;
import com.ext.portlet.service.StaffMemberServiceUtil;
import com.ext.portlet.service.TrackedVisitLocalServiceUtil;
import com.ext.portlet.service.TrackedVisitServiceUtil;
import com.ext.portlet.service.TrackedVisitor2UserLocalServiceUtil;
import com.ext.portlet.service.TrackedVisitor2UserServiceUtil;
import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;
import com.ext.portlet.service.Xcolab_UserServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;


public class ClpMessageListener extends BaseMessageListener {
    public static String getServletContextName() {
        return ClpSerializer.getServletContextName();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        String command = message.getString("command");
        String servletContextName = message.getString("servletContextName");

        if (command.equals("undeploy") &&
                servletContextName.equals(getServletContextName())) {
            ActivitySubscriptionLocalServiceUtil.clearService();

            ActivitySubscriptionServiceUtil.clearService();
            AnalyticsUserEventLocalServiceUtil.clearService();

            AnalyticsUserEventServiceUtil.clearService();
            BalloonLinkLocalServiceUtil.clearService();

            BalloonLinkServiceUtil.clearService();
            BalloonStatsEntryLocalServiceUtil.clearService();

            BalloonStatsEntryServiceUtil.clearService();
            BalloonTextLocalServiceUtil.clearService();

            BalloonTextServiceUtil.clearService();
            BalloonUserTrackingLocalServiceUtil.clearService();

            BalloonUserTrackingServiceUtil.clearService();
            ContestLocalServiceUtil.clearService();

            ContestServiceUtil.clearService();
            ContestDebateLocalServiceUtil.clearService();

            ContestDebateServiceUtil.clearService();
            ContestDiscussionLocalServiceUtil.clearService();

            ContestDiscussionServiceUtil.clearService();
            ContestEmailTemplateLocalServiceUtil.clearService();

            ContestEmailTemplateServiceUtil.clearService();
            ContestPhaseLocalServiceUtil.clearService();

            ContestPhaseServiceUtil.clearService();
            ContestPhaseColumnLocalServiceUtil.clearService();

            ContestPhaseColumnServiceUtil.clearService();
            ContestPhaseRibbonTypeLocalServiceUtil.clearService();

            ContestPhaseRibbonTypeServiceUtil.clearService();
            ContestPhaseTypeLocalServiceUtil.clearService();

            ContestPhaseTypeServiceUtil.clearService();
            ContestScheduleLocalServiceUtil.clearService();

            ContestScheduleServiceUtil.clearService();
            ContestTeamMemberLocalServiceUtil.clearService();

            ContestTeamMemberServiceUtil.clearService();
            ContestTypeLocalServiceUtil.clearService();

            ContestTypeServiceUtil.clearService();
            DiscussionCategoryLocalServiceUtil.clearService();

            DiscussionCategoryServiceUtil.clearService();
            DiscussionCategoryGroupLocalServiceUtil.clearService();

            DiscussionCategoryGroupServiceUtil.clearService();
            DiscussionMessageLocalServiceUtil.clearService();

            DiscussionMessageServiceUtil.clearService();
            DiscussionMessageFlagLocalServiceUtil.clearService();

            DiscussionMessageFlagServiceUtil.clearService();
            EmailListLocalServiceUtil.clearService();

            EmailListServiceUtil.clearService();
            FocusAreaLocalServiceUtil.clearService();

            FocusAreaServiceUtil.clearService();
            FocusAreaOntologyTermLocalServiceUtil.clearService();

            FocusAreaOntologyTermServiceUtil.clearService();
            ImpactDefaultSeriesLocalServiceUtil.clearService();

            ImpactDefaultSeriesServiceUtil.clearService();
            ImpactDefaultSeriesDataLocalServiceUtil.clearService();

            ImpactDefaultSeriesDataServiceUtil.clearService();
            ImpactIterationLocalServiceUtil.clearService();

            ImpactIterationServiceUtil.clearService();
            ImpactTemplateFocusAreaListLocalServiceUtil.clearService();

            ImpactTemplateFocusAreaListServiceUtil.clearService();
            ImpactTemplateMaxFocusAreaLocalServiceUtil.clearService();

            ImpactTemplateMaxFocusAreaServiceUtil.clearService();
            ImpactTemplateSeriesLocalServiceUtil.clearService();

            ImpactTemplateSeriesServiceUtil.clearService();
            LandingPageLocalServiceUtil.clearService();

            LandingPageServiceUtil.clearService();
            LoginLogLocalServiceUtil.clearService();

            LoginLogServiceUtil.clearService();
            MessageLocalServiceUtil.clearService();

            MessageServiceUtil.clearService();
            MessageRecipientStatusLocalServiceUtil.clearService();

            MessageRecipientStatusServiceUtil.clearService();
            MessagingIgnoredRecipientsLocalServiceUtil.clearService();

            MessagingIgnoredRecipientsServiceUtil.clearService();
            MessagingMessageLocalServiceUtil.clearService();

            MessagingMessageServiceUtil.clearService();
            MessagingMessageConversionLocalServiceUtil.clearService();

            MessagingMessageConversionServiceUtil.clearService();
            MessagingMessageConversionTypeLocalServiceUtil.clearService();

            MessagingMessageConversionTypeServiceUtil.clearService();
            MessagingMessageRecipientLocalServiceUtil.clearService();

            MessagingMessageRecipientServiceUtil.clearService();
            MessagingRedirectLinkLocalServiceUtil.clearService();

            MessagingRedirectLinkServiceUtil.clearService();
            MessagingUserPreferencesLocalServiceUtil.clearService();

            MessagingUserPreferencesServiceUtil.clearService();
            ModelCategoryLocalServiceUtil.clearService();

            ModelCategoryServiceUtil.clearService();
            ModelDiscussionLocalServiceUtil.clearService();

            ModelDiscussionServiceUtil.clearService();
            ModelGlobalPreferenceLocalServiceUtil.clearService();

            ModelGlobalPreferenceServiceUtil.clearService();
            ModelInputGroupLocalServiceUtil.clearService();

            ModelInputGroupServiceUtil.clearService();
            ModelInputItemLocalServiceUtil.clearService();

            ModelInputItemServiceUtil.clearService();
            ModelOutputChartOrderLocalServiceUtil.clearService();

            ModelOutputChartOrderServiceUtil.clearService();
            ModelOutputItemLocalServiceUtil.clearService();

            ModelOutputItemServiceUtil.clearService();
            ModelPositionLocalServiceUtil.clearService();

            ModelPositionServiceUtil.clearService();
            ModelRunnerLocalServiceUtil.clearService();

            ModelRunnerServiceUtil.clearService();
            OntologySpaceLocalServiceUtil.clearService();

            OntologySpaceServiceUtil.clearService();
            OntologyTermLocalServiceUtil.clearService();

            OntologyTermServiceUtil.clearService();
            OntologyTermEntityLocalServiceUtil.clearService();

            OntologyTermEntityServiceUtil.clearService();
            PlanSectionDefinitionLocalServiceUtil.clearService();

            PlanSectionDefinitionServiceUtil.clearService();
            PlanTemplateLocalServiceUtil.clearService();

            PlanTemplateServiceUtil.clearService();
            PlanTemplateSectionLocalServiceUtil.clearService();

            PlanTemplateSectionServiceUtil.clearService();
            PointDistributionTargetLocalServiceUtil.clearService();

            PointDistributionTargetServiceUtil.clearService();
            PointsLocalServiceUtil.clearService();

            PointsServiceUtil.clearService();
            PointsDistributionConfigurationLocalServiceUtil.clearService();

            PointsDistributionConfigurationServiceUtil.clearService();
            PointTypeLocalServiceUtil.clearService();

            PointTypeServiceUtil.clearService();
            ProposalLocalServiceUtil.clearService();

            ProposalServiceUtil.clearService();
            Proposal2PhaseLocalServiceUtil.clearService();

            Proposal2PhaseServiceUtil.clearService();
            ProposalAttributeLocalServiceUtil.clearService();

            ProposalAttributeServiceUtil.clearService();
            ProposalAttributeTypeLocalServiceUtil.clearService();

            ProposalAttributeTypeServiceUtil.clearService();
            ProposalContestPhaseAttributeLocalServiceUtil.clearService();

            ProposalContestPhaseAttributeServiceUtil.clearService();
            ProposalContestPhaseAttributeTypeLocalServiceUtil.clearService();

            ProposalContestPhaseAttributeTypeServiceUtil.clearService();
            ProposalRatingLocalServiceUtil.clearService();

            ProposalRatingServiceUtil.clearService();
            ProposalRatingTypeLocalServiceUtil.clearService();

            ProposalRatingTypeServiceUtil.clearService();
            ProposalRatingValueLocalServiceUtil.clearService();

            ProposalRatingValueServiceUtil.clearService();
            ProposalReferenceLocalServiceUtil.clearService();

            ProposalReferenceServiceUtil.clearService();
            ProposalSupporterLocalServiceUtil.clearService();

            ProposalSupporterServiceUtil.clearService();
            ProposalVersionLocalServiceUtil.clearService();

            ProposalVersionServiceUtil.clearService();
            ProposalVoteLocalServiceUtil.clearService();

            ProposalVoteServiceUtil.clearService();
            StaffMemberLocalServiceUtil.clearService();

            StaffMemberServiceUtil.clearService();
            TrackedVisitLocalServiceUtil.clearService();

            TrackedVisitServiceUtil.clearService();
            TrackedVisitor2UserLocalServiceUtil.clearService();

            TrackedVisitor2UserServiceUtil.clearService();
            Xcolab_UserLocalServiceUtil.clearService();

            Xcolab_UserServiceUtil.clearService();
        }
    }
}
