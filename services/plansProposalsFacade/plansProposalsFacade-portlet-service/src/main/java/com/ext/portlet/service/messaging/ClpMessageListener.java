package com.ext.portlet.service.messaging;

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
import com.ext.portlet.service.ContestTeamMemberRoleLocalServiceUtil;
import com.ext.portlet.service.ContestTeamMemberRoleServiceUtil;
import com.ext.portlet.service.ContestTeamMemberServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.ContestTypeServiceUtil;
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
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalMoveHistoryLocalServiceUtil;
import com.ext.portlet.service.ProposalMoveHistoryServiceUtil;
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
import com.ext.portlet.service.ProposalUnversionedAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalUnversionedAttributeServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionServiceUtil;
import com.ext.portlet.service.ProposalVoteLocalServiceUtil;
import com.ext.portlet.service.ProposalVoteServiceUtil;
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
            ContestTeamMemberRoleLocalServiceUtil.clearService();

            ContestTeamMemberRoleServiceUtil.clearService();
            ContestTypeLocalServiceUtil.clearService();

            ContestTypeServiceUtil.clearService();
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
            ProposalContestPhaseAttributeLocalServiceUtil.clearService();

            ProposalContestPhaseAttributeServiceUtil.clearService();
            ProposalMoveHistoryLocalServiceUtil.clearService();

            ProposalMoveHistoryServiceUtil.clearService();
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
            ProposalUnversionedAttributeLocalServiceUtil.clearService();

            ProposalUnversionedAttributeServiceUtil.clearService();
            ProposalVersionLocalServiceUtil.clearService();

            ProposalVersionServiceUtil.clearService();
            ProposalVoteLocalServiceUtil.clearService();

            ProposalVoteServiceUtil.clearService();
            Xcolab_UserLocalServiceUtil.clearService();

            Xcolab_UserServiceUtil.clearService();
        }
    }
}
