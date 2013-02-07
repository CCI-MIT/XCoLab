package com.ext.portlet.service.messaging;

import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ActivitySubscriptionServiceUtil;
import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestDebateLocalServiceUtil;
import com.ext.portlet.service.ContestDebateServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseColumnLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseColumnServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeServiceUtil;
import com.ext.portlet.service.ContestServiceUtil;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.ext.portlet.service.ContestTeamMemberServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryServiceUtil;
import com.ext.portlet.service.DiscussionMessageFlagLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageFlagServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermServiceUtil;
import com.ext.portlet.service.FocusAreaServiceUtil;
import com.ext.portlet.service.LandingPageLocalServiceUtil;
import com.ext.portlet.service.LandingPageServiceUtil;
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
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceServiceUtil;
import com.ext.portlet.service.OntologyTermEntityLocalServiceUtil;
import com.ext.portlet.service.OntologyTermEntityServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.OntologyTermServiceUtil;
import com.ext.portlet.service.PlanAttributeFilterLocalServiceUtil;
import com.ext.portlet.service.PlanAttributeFilterServiceUtil;
import com.ext.portlet.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.service.PlanAttributeServiceUtil;
import com.ext.portlet.service.PlanColumnSettingsLocalServiceUtil;
import com.ext.portlet.service.PlanColumnSettingsServiceUtil;
import com.ext.portlet.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.service.PlanDescriptionServiceUtil;
import com.ext.portlet.service.PlanFanLocalServiceUtil;
import com.ext.portlet.service.PlanFanServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanItemServiceUtil;
import com.ext.portlet.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.service.PlanMetaServiceUtil;
import com.ext.portlet.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.service.PlanModelRunServiceUtil;
import com.ext.portlet.service.PlanPositionItemLocalServiceUtil;
import com.ext.portlet.service.PlanPositionItemServiceUtil;
import com.ext.portlet.service.PlanPositionLocalServiceUtil;
import com.ext.portlet.service.PlanPositionServiceUtil;
import com.ext.portlet.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.service.PlanPositionsServiceUtil;
import com.ext.portlet.service.PlanPropertyFilterLocalServiceUtil;
import com.ext.portlet.service.PlanPropertyFilterServiceUtil;
import com.ext.portlet.service.PlanRelatedLocalServiceUtil;
import com.ext.portlet.service.PlanRelatedServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionServiceUtil;
import com.ext.portlet.service.PlanSectionLocalServiceUtil;
import com.ext.portlet.service.PlanSectionPlanMapLocalServiceUtil;
import com.ext.portlet.service.PlanSectionPlanMapServiceUtil;
import com.ext.portlet.service.PlanSectionServiceUtil;
import com.ext.portlet.service.PlanTeamHistoryLocalServiceUtil;
import com.ext.portlet.service.PlanTeamHistoryServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateSectionServiceUtil;
import com.ext.portlet.service.PlanTemplateServiceUtil;
import com.ext.portlet.service.PlanTypeAttributeLocalServiceUtil;
import com.ext.portlet.service.PlanTypeAttributeServiceUtil;
import com.ext.portlet.service.PlanTypeColumnLocalServiceUtil;
import com.ext.portlet.service.PlanTypeColumnServiceUtil;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.service.PlanTypeServiceUtil;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.service.PlanVoteServiceUtil;
import com.ext.portlet.service.PlansFilterLocalServiceUtil;
import com.ext.portlet.service.PlansFilterPositionLocalServiceUtil;
import com.ext.portlet.service.PlansFilterPositionServiceUtil;
import com.ext.portlet.service.PlansFilterServiceUtil;
import com.ext.portlet.service.PlansUserSettingsLocalServiceUtil;
import com.ext.portlet.service.PlansUserSettingsServiceUtil;

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
            ContestLocalServiceUtil.clearService();

            ContestServiceUtil.clearService();
            ContestDebateLocalServiceUtil.clearService();

            ContestDebateServiceUtil.clearService();
            ContestPhaseLocalServiceUtil.clearService();

            ContestPhaseServiceUtil.clearService();
            ContestPhaseColumnLocalServiceUtil.clearService();

            ContestPhaseColumnServiceUtil.clearService();
            ContestPhaseTypeLocalServiceUtil.clearService();

            ContestPhaseTypeServiceUtil.clearService();
            ContestTeamMemberLocalServiceUtil.clearService();

            ContestTeamMemberServiceUtil.clearService();
            DiscussionCategoryLocalServiceUtil.clearService();

            DiscussionCategoryServiceUtil.clearService();
            DiscussionCategoryGroupLocalServiceUtil.clearService();

            DiscussionCategoryGroupServiceUtil.clearService();
            DiscussionMessageLocalServiceUtil.clearService();

            DiscussionMessageServiceUtil.clearService();
            DiscussionMessageFlagLocalServiceUtil.clearService();

            DiscussionMessageFlagServiceUtil.clearService();
            FocusAreaLocalServiceUtil.clearService();

            FocusAreaServiceUtil.clearService();
            FocusAreaOntologyTermLocalServiceUtil.clearService();

            FocusAreaOntologyTermServiceUtil.clearService();
            LandingPageLocalServiceUtil.clearService();

            LandingPageServiceUtil.clearService();
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
            OntologySpaceLocalServiceUtil.clearService();

            OntologySpaceServiceUtil.clearService();
            OntologyTermLocalServiceUtil.clearService();

            OntologyTermServiceUtil.clearService();
            OntologyTermEntityLocalServiceUtil.clearService();

            OntologyTermEntityServiceUtil.clearService();
            PlanAttributeLocalServiceUtil.clearService();

            PlanAttributeServiceUtil.clearService();
            PlanAttributeFilterLocalServiceUtil.clearService();

            PlanAttributeFilterServiceUtil.clearService();
            PlanColumnSettingsLocalServiceUtil.clearService();

            PlanColumnSettingsServiceUtil.clearService();
            PlanDescriptionLocalServiceUtil.clearService();

            PlanDescriptionServiceUtil.clearService();
            PlanFanLocalServiceUtil.clearService();

            PlanFanServiceUtil.clearService();
            PlanItemLocalServiceUtil.clearService();

            PlanItemServiceUtil.clearService();
            PlanMetaLocalServiceUtil.clearService();

            PlanMetaServiceUtil.clearService();
            PlanModelRunLocalServiceUtil.clearService();

            PlanModelRunServiceUtil.clearService();
            PlanPositionLocalServiceUtil.clearService();

            PlanPositionServiceUtil.clearService();
            PlanPositionItemLocalServiceUtil.clearService();

            PlanPositionItemServiceUtil.clearService();
            PlanPositionsLocalServiceUtil.clearService();

            PlanPositionsServiceUtil.clearService();
            PlanPropertyFilterLocalServiceUtil.clearService();

            PlanPropertyFilterServiceUtil.clearService();
            PlanRelatedLocalServiceUtil.clearService();

            PlanRelatedServiceUtil.clearService();
            PlanSectionLocalServiceUtil.clearService();

            PlanSectionServiceUtil.clearService();
            PlanSectionDefinitionLocalServiceUtil.clearService();

            PlanSectionDefinitionServiceUtil.clearService();
            PlanSectionPlanMapLocalServiceUtil.clearService();

            PlanSectionPlanMapServiceUtil.clearService();
            PlansFilterLocalServiceUtil.clearService();

            PlansFilterServiceUtil.clearService();
            PlansFilterPositionLocalServiceUtil.clearService();

            PlansFilterPositionServiceUtil.clearService();
            PlansUserSettingsLocalServiceUtil.clearService();

            PlansUserSettingsServiceUtil.clearService();
            PlanTeamHistoryLocalServiceUtil.clearService();

            PlanTeamHistoryServiceUtil.clearService();
            PlanTemplateLocalServiceUtil.clearService();

            PlanTemplateServiceUtil.clearService();
            PlanTemplateSectionLocalServiceUtil.clearService();

            PlanTemplateSectionServiceUtil.clearService();
            PlanTypeLocalServiceUtil.clearService();

            PlanTypeServiceUtil.clearService();
            PlanTypeAttributeLocalServiceUtil.clearService();

            PlanTypeAttributeServiceUtil.clearService();
            PlanTypeColumnLocalServiceUtil.clearService();

            PlanTypeColumnServiceUtil.clearService();
            PlanVoteLocalServiceUtil.clearService();

            PlanVoteServiceUtil.clearService();
        }
    }
}
