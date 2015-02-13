package com.ext.portlet.service;

import com.ext.portlet.model.ActivitySubscriptionClp;
import com.ext.portlet.model.AnalyticsUserEventClp;
import com.ext.portlet.model.BalloonLinkClp;
import com.ext.portlet.model.BalloonStatsEntryClp;
import com.ext.portlet.model.BalloonTextClp;
import com.ext.portlet.model.BalloonUserTrackingClp;
import com.ext.portlet.model.ContestClp;
import com.ext.portlet.model.ContestDebateClp;
import com.ext.portlet.model.ContestDiscussionClp;
import com.ext.portlet.model.ContestEmailTemplateClp;
import com.ext.portlet.model.ContestPhaseClp;
import com.ext.portlet.model.ContestPhaseColumnClp;
import com.ext.portlet.model.ContestPhaseRibbonTypeClp;
import com.ext.portlet.model.ContestPhaseTypeClp;
import com.ext.portlet.model.ContestScheduleClp;
import com.ext.portlet.model.ContestTeamMemberClp;
import com.ext.portlet.model.DiscussionCategoryClp;
import com.ext.portlet.model.DiscussionCategoryGroupClp;
import com.ext.portlet.model.DiscussionMessageClp;
import com.ext.portlet.model.DiscussionMessageFlagClp;
import com.ext.portlet.model.EmailListClp;
import com.ext.portlet.model.FocusAreaClp;
import com.ext.portlet.model.FocusAreaOntologyTermClp;
import com.ext.portlet.model.LandingPageClp;
import com.ext.portlet.model.LoginLogClp;
import com.ext.portlet.model.MessageClp;
import com.ext.portlet.model.MessageRecipientStatusClp;
import com.ext.portlet.model.MessagingIgnoredRecipientsClp;
import com.ext.portlet.model.MessagingMessageClp;
import com.ext.portlet.model.MessagingMessageConversionClp;
import com.ext.portlet.model.MessagingMessageConversionTypeClp;
import com.ext.portlet.model.MessagingMessageRecipientClp;
import com.ext.portlet.model.MessagingRedirectLinkClp;
import com.ext.portlet.model.MessagingUserPreferencesClp;
import com.ext.portlet.model.ModelCategoryClp;
import com.ext.portlet.model.ModelDiscussionClp;
import com.ext.portlet.model.ModelGlobalPreferenceClp;
import com.ext.portlet.model.ModelInputGroupClp;
import com.ext.portlet.model.ModelInputItemClp;
import com.ext.portlet.model.ModelOutputChartOrderClp;
import com.ext.portlet.model.ModelOutputItemClp;
import com.ext.portlet.model.ModelPositionClp;
import com.ext.portlet.model.OntologySpaceClp;
import com.ext.portlet.model.OntologyTermClp;
import com.ext.portlet.model.OntologyTermEntityClp;
import com.ext.portlet.model.Plan2ProposalClp;
import com.ext.portlet.model.PlanAttributeClp;
import com.ext.portlet.model.PlanAttributeFilterClp;
import com.ext.portlet.model.PlanColumnSettingsClp;
import com.ext.portlet.model.PlanDescriptionClp;
import com.ext.portlet.model.PlanFanClp;
import com.ext.portlet.model.PlanItemClp;
import com.ext.portlet.model.PlanItemGroupClp;
import com.ext.portlet.model.PlanMetaClp;
import com.ext.portlet.model.PlanModelRunClp;
import com.ext.portlet.model.PlanPositionClp;
import com.ext.portlet.model.PlanPositionItemClp;
import com.ext.portlet.model.PlanPositionsClp;
import com.ext.portlet.model.PlanPropertyFilterClp;
import com.ext.portlet.model.PlanRelatedClp;
import com.ext.portlet.model.PlanSectionClp;
import com.ext.portlet.model.PlanSectionDefinitionClp;
import com.ext.portlet.model.PlanSectionPlanMapClp;
import com.ext.portlet.model.PlanTeamHistoryClp;
import com.ext.portlet.model.PlanTemplateClp;
import com.ext.portlet.model.PlanTemplateSectionClp;
import com.ext.portlet.model.PlanTypeAttributeClp;
import com.ext.portlet.model.PlanTypeClp;
import com.ext.portlet.model.PlanTypeColumnClp;
import com.ext.portlet.model.PlanVoteClp;
import com.ext.portlet.model.PlansFilterClp;
import com.ext.portlet.model.PlansFilterPositionClp;
import com.ext.portlet.model.PlansUserSettingsClp;
import com.ext.portlet.model.PointDistributionTargetClp;
import com.ext.portlet.model.PointTypeClp;
import com.ext.portlet.model.PointsClp;
import com.ext.portlet.model.PointsDistributionConfigurationClp;
import com.ext.portlet.model.Proposal2PhaseClp;
import com.ext.portlet.model.ProposalAttributeClp;
import com.ext.portlet.model.ProposalAttributeTypeClp;
import com.ext.portlet.model.ProposalClp;
import com.ext.portlet.model.ProposalContestPhaseAttributeClp;
import com.ext.portlet.model.ProposalContestPhaseAttributeTypeClp;
import com.ext.portlet.model.ProposalRatingClp;
import com.ext.portlet.model.ProposalRatingTypeClp;
import com.ext.portlet.model.ProposalRatingValueClp;
import com.ext.portlet.model.ProposalSupporterClp;
import com.ext.portlet.model.ProposalVersionClp;
import com.ext.portlet.model.ProposalVoteClp;
import com.ext.portlet.model.StaffMemberClp;
import com.ext.portlet.model.TrackedVisitClp;
import com.ext.portlet.model.TrackedVisitor2UserClp;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;


public class ClpSerializer {
    private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
    private static String _servletContextName;
    private static boolean _useReflectionToTranslateThrowable = true;

    public static String getServletContextName() {
        if (Validator.isNotNull(_servletContextName)) {
            return _servletContextName;
        }

        synchronized (ClpSerializer.class) {
            if (Validator.isNotNull(_servletContextName)) {
                return _servletContextName;
            }

            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Class<?> portletPropsClass = classLoader.loadClass(
                        "com.liferay.util.portlet.PortletProps");

                Method getMethod = portletPropsClass.getMethod("get",
                        new Class<?>[] { String.class });

                String portletPropsServletContextName = (String) getMethod.invoke(null,
                        "plansProposalsFacade-portlet-deployment-context");

                if (Validator.isNotNull(portletPropsServletContextName)) {
                    _servletContextName = portletPropsServletContextName;
                }
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info(
                        "Unable to locate deployment context from portlet properties");
                }
            }

            if (Validator.isNull(_servletContextName)) {
                try {
                    String propsUtilServletContextName = PropsUtil.get(
                            "plansProposalsFacade-portlet-deployment-context");

                    if (Validator.isNotNull(propsUtilServletContextName)) {
                        _servletContextName = propsUtilServletContextName;
                    }
                } catch (Throwable t) {
                    if (_log.isInfoEnabled()) {
                        _log.info(
                            "Unable to locate deployment context from portal properties");
                    }
                }
            }

            if (Validator.isNull(_servletContextName)) {
                _servletContextName = "plansProposalsFacade-portlet";
            }

            return _servletContextName;
        }
    }

    public static Object translateInput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(ActivitySubscriptionClp.class.getName())) {
            return translateInputActivitySubscription(oldModel);
        }

        if (oldModelClassName.equals(AnalyticsUserEventClp.class.getName())) {
            return translateInputAnalyticsUserEvent(oldModel);
        }

        if (oldModelClassName.equals(BalloonLinkClp.class.getName())) {
            return translateInputBalloonLink(oldModel);
        }

        if (oldModelClassName.equals(BalloonStatsEntryClp.class.getName())) {
            return translateInputBalloonStatsEntry(oldModel);
        }

        if (oldModelClassName.equals(BalloonTextClp.class.getName())) {
            return translateInputBalloonText(oldModel);
        }

        if (oldModelClassName.equals(BalloonUserTrackingClp.class.getName())) {
            return translateInputBalloonUserTracking(oldModel);
        }

        if (oldModelClassName.equals(ContestClp.class.getName())) {
            return translateInputContest(oldModel);
        }

        if (oldModelClassName.equals(ContestDebateClp.class.getName())) {
            return translateInputContestDebate(oldModel);
        }

        if (oldModelClassName.equals(ContestDiscussionClp.class.getName())) {
            return translateInputContestDiscussion(oldModel);
        }

        if (oldModelClassName.equals(ContestEmailTemplateClp.class.getName())) {
            return translateInputContestEmailTemplate(oldModel);
        }

        if (oldModelClassName.equals(ContestPhaseClp.class.getName())) {
            return translateInputContestPhase(oldModel);
        }

        if (oldModelClassName.equals(ContestPhaseColumnClp.class.getName())) {
            return translateInputContestPhaseColumn(oldModel);
        }

        if (oldModelClassName.equals(ContestPhaseRibbonTypeClp.class.getName())) {
            return translateInputContestPhaseRibbonType(oldModel);
        }

        if (oldModelClassName.equals(ContestPhaseTypeClp.class.getName())) {
            return translateInputContestPhaseType(oldModel);
        }

        if (oldModelClassName.equals(ContestScheduleClp.class.getName())) {
            return translateInputContestSchedule(oldModel);
        }

        if (oldModelClassName.equals(ContestTeamMemberClp.class.getName())) {
            return translateInputContestTeamMember(oldModel);
        }

        if (oldModelClassName.equals(DiscussionCategoryClp.class.getName())) {
            return translateInputDiscussionCategory(oldModel);
        }

        if (oldModelClassName.equals(DiscussionCategoryGroupClp.class.getName())) {
            return translateInputDiscussionCategoryGroup(oldModel);
        }

        if (oldModelClassName.equals(DiscussionMessageClp.class.getName())) {
            return translateInputDiscussionMessage(oldModel);
        }

        if (oldModelClassName.equals(DiscussionMessageFlagClp.class.getName())) {
            return translateInputDiscussionMessageFlag(oldModel);
        }

        if (oldModelClassName.equals(EmailListClp.class.getName())) {
            return translateInputEmailList(oldModel);
        }

        if (oldModelClassName.equals(FocusAreaClp.class.getName())) {
            return translateInputFocusArea(oldModel);
        }

        if (oldModelClassName.equals(FocusAreaOntologyTermClp.class.getName())) {
            return translateInputFocusAreaOntologyTerm(oldModel);
        }

        if (oldModelClassName.equals(LandingPageClp.class.getName())) {
            return translateInputLandingPage(oldModel);
        }

        if (oldModelClassName.equals(LoginLogClp.class.getName())) {
            return translateInputLoginLog(oldModel);
        }

        if (oldModelClassName.equals(MessageClp.class.getName())) {
            return translateInputMessage(oldModel);
        }

        if (oldModelClassName.equals(MessageRecipientStatusClp.class.getName())) {
            return translateInputMessageRecipientStatus(oldModel);
        }

        if (oldModelClassName.equals(
                    MessagingIgnoredRecipientsClp.class.getName())) {
            return translateInputMessagingIgnoredRecipients(oldModel);
        }

        if (oldModelClassName.equals(MessagingMessageClp.class.getName())) {
            return translateInputMessagingMessage(oldModel);
        }

        if (oldModelClassName.equals(
                    MessagingMessageConversionClp.class.getName())) {
            return translateInputMessagingMessageConversion(oldModel);
        }

        if (oldModelClassName.equals(
                    MessagingMessageConversionTypeClp.class.getName())) {
            return translateInputMessagingMessageConversionType(oldModel);
        }

        if (oldModelClassName.equals(
                    MessagingMessageRecipientClp.class.getName())) {
            return translateInputMessagingMessageRecipient(oldModel);
        }

        if (oldModelClassName.equals(MessagingRedirectLinkClp.class.getName())) {
            return translateInputMessagingRedirectLink(oldModel);
        }

        if (oldModelClassName.equals(
                    MessagingUserPreferencesClp.class.getName())) {
            return translateInputMessagingUserPreferences(oldModel);
        }

        if (oldModelClassName.equals(ModelCategoryClp.class.getName())) {
            return translateInputModelCategory(oldModel);
        }

        if (oldModelClassName.equals(ModelDiscussionClp.class.getName())) {
            return translateInputModelDiscussion(oldModel);
        }

        if (oldModelClassName.equals(ModelGlobalPreferenceClp.class.getName())) {
            return translateInputModelGlobalPreference(oldModel);
        }

        if (oldModelClassName.equals(ModelInputGroupClp.class.getName())) {
            return translateInputModelInputGroup(oldModel);
        }

        if (oldModelClassName.equals(ModelInputItemClp.class.getName())) {
            return translateInputModelInputItem(oldModel);
        }

        if (oldModelClassName.equals(ModelOutputChartOrderClp.class.getName())) {
            return translateInputModelOutputChartOrder(oldModel);
        }

        if (oldModelClassName.equals(ModelOutputItemClp.class.getName())) {
            return translateInputModelOutputItem(oldModel);
        }

        if (oldModelClassName.equals(ModelPositionClp.class.getName())) {
            return translateInputModelPosition(oldModel);
        }

        if (oldModelClassName.equals(OntologySpaceClp.class.getName())) {
            return translateInputOntologySpace(oldModel);
        }

        if (oldModelClassName.equals(OntologyTermClp.class.getName())) {
            return translateInputOntologyTerm(oldModel);
        }

        if (oldModelClassName.equals(OntologyTermEntityClp.class.getName())) {
            return translateInputOntologyTermEntity(oldModel);
        }

        if (oldModelClassName.equals(Plan2ProposalClp.class.getName())) {
            return translateInputPlan2Proposal(oldModel);
        }

        if (oldModelClassName.equals(PlanAttributeClp.class.getName())) {
            return translateInputPlanAttribute(oldModel);
        }

        if (oldModelClassName.equals(PlanAttributeFilterClp.class.getName())) {
            return translateInputPlanAttributeFilter(oldModel);
        }

        if (oldModelClassName.equals(PlanColumnSettingsClp.class.getName())) {
            return translateInputPlanColumnSettings(oldModel);
        }

        if (oldModelClassName.equals(PlanDescriptionClp.class.getName())) {
            return translateInputPlanDescription(oldModel);
        }

        if (oldModelClassName.equals(PlanFanClp.class.getName())) {
            return translateInputPlanFan(oldModel);
        }

        if (oldModelClassName.equals(PlanItemClp.class.getName())) {
            return translateInputPlanItem(oldModel);
        }

        if (oldModelClassName.equals(PlanItemGroupClp.class.getName())) {
            return translateInputPlanItemGroup(oldModel);
        }

        if (oldModelClassName.equals(PlanMetaClp.class.getName())) {
            return translateInputPlanMeta(oldModel);
        }

        if (oldModelClassName.equals(PlanModelRunClp.class.getName())) {
            return translateInputPlanModelRun(oldModel);
        }

        if (oldModelClassName.equals(PlanPositionClp.class.getName())) {
            return translateInputPlanPosition(oldModel);
        }

        if (oldModelClassName.equals(PlanPositionItemClp.class.getName())) {
            return translateInputPlanPositionItem(oldModel);
        }

        if (oldModelClassName.equals(PlanPositionsClp.class.getName())) {
            return translateInputPlanPositions(oldModel);
        }

        if (oldModelClassName.equals(PlanPropertyFilterClp.class.getName())) {
            return translateInputPlanPropertyFilter(oldModel);
        }

        if (oldModelClassName.equals(PlanRelatedClp.class.getName())) {
            return translateInputPlanRelated(oldModel);
        }

        if (oldModelClassName.equals(PlanSectionClp.class.getName())) {
            return translateInputPlanSection(oldModel);
        }

        if (oldModelClassName.equals(PlanSectionDefinitionClp.class.getName())) {
            return translateInputPlanSectionDefinition(oldModel);
        }

        if (oldModelClassName.equals(PlanSectionPlanMapClp.class.getName())) {
            return translateInputPlanSectionPlanMap(oldModel);
        }

        if (oldModelClassName.equals(PlansFilterClp.class.getName())) {
            return translateInputPlansFilter(oldModel);
        }

        if (oldModelClassName.equals(PlansFilterPositionClp.class.getName())) {
            return translateInputPlansFilterPosition(oldModel);
        }

        if (oldModelClassName.equals(PlansUserSettingsClp.class.getName())) {
            return translateInputPlansUserSettings(oldModel);
        }

        if (oldModelClassName.equals(PlanTeamHistoryClp.class.getName())) {
            return translateInputPlanTeamHistory(oldModel);
        }

        if (oldModelClassName.equals(PlanTemplateClp.class.getName())) {
            return translateInputPlanTemplate(oldModel);
        }

        if (oldModelClassName.equals(PlanTemplateSectionClp.class.getName())) {
            return translateInputPlanTemplateSection(oldModel);
        }

        if (oldModelClassName.equals(PlanTypeClp.class.getName())) {
            return translateInputPlanType(oldModel);
        }

        if (oldModelClassName.equals(PlanTypeAttributeClp.class.getName())) {
            return translateInputPlanTypeAttribute(oldModel);
        }

        if (oldModelClassName.equals(PlanTypeColumnClp.class.getName())) {
            return translateInputPlanTypeColumn(oldModel);
        }

        if (oldModelClassName.equals(PlanVoteClp.class.getName())) {
            return translateInputPlanVote(oldModel);
        }

        if (oldModelClassName.equals(PointDistributionTargetClp.class.getName())) {
            return translateInputPointDistributionTarget(oldModel);
        }

        if (oldModelClassName.equals(PointsClp.class.getName())) {
            return translateInputPoints(oldModel);
        }

        if (oldModelClassName.equals(
                    PointsDistributionConfigurationClp.class.getName())) {
            return translateInputPointsDistributionConfiguration(oldModel);
        }

        if (oldModelClassName.equals(PointTypeClp.class.getName())) {
            return translateInputPointType(oldModel);
        }

        if (oldModelClassName.equals(ProposalClp.class.getName())) {
            return translateInputProposal(oldModel);
        }

        if (oldModelClassName.equals(Proposal2PhaseClp.class.getName())) {
            return translateInputProposal2Phase(oldModel);
        }

        if (oldModelClassName.equals(ProposalAttributeClp.class.getName())) {
            return translateInputProposalAttribute(oldModel);
        }

        if (oldModelClassName.equals(ProposalAttributeTypeClp.class.getName())) {
            return translateInputProposalAttributeType(oldModel);
        }

        if (oldModelClassName.equals(
                    ProposalContestPhaseAttributeClp.class.getName())) {
            return translateInputProposalContestPhaseAttribute(oldModel);
        }

        if (oldModelClassName.equals(
                    ProposalContestPhaseAttributeTypeClp.class.getName())) {
            return translateInputProposalContestPhaseAttributeType(oldModel);
        }

        if (oldModelClassName.equals(ProposalRatingClp.class.getName())) {
            return translateInputProposalRating(oldModel);
        }

        if (oldModelClassName.equals(ProposalRatingTypeClp.class.getName())) {
            return translateInputProposalRatingType(oldModel);
        }

        if (oldModelClassName.equals(ProposalRatingValueClp.class.getName())) {
            return translateInputProposalRatingValue(oldModel);
        }

        if (oldModelClassName.equals(ProposalSupporterClp.class.getName())) {
            return translateInputProposalSupporter(oldModel);
        }

        if (oldModelClassName.equals(ProposalVersionClp.class.getName())) {
            return translateInputProposalVersion(oldModel);
        }

        if (oldModelClassName.equals(ProposalVoteClp.class.getName())) {
            return translateInputProposalVote(oldModel);
        }

        if (oldModelClassName.equals(StaffMemberClp.class.getName())) {
            return translateInputStaffMember(oldModel);
        }

        if (oldModelClassName.equals(TrackedVisitClp.class.getName())) {
            return translateInputTrackedVisit(oldModel);
        }

        if (oldModelClassName.equals(TrackedVisitor2UserClp.class.getName())) {
            return translateInputTrackedVisitor2User(oldModel);
        }

        return oldModel;
    }

    public static Object translateInput(List<Object> oldList) {
        List<Object> newList = new ArrayList<Object>(oldList.size());

        for (int i = 0; i < oldList.size(); i++) {
            Object curObj = oldList.get(i);

            newList.add(translateInput(curObj));
        }

        return newList;
    }

    public static Object translateInputActivitySubscription(
        BaseModel<?> oldModel) {
        ActivitySubscriptionClp oldClpModel = (ActivitySubscriptionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getActivitySubscriptionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputAnalyticsUserEvent(BaseModel<?> oldModel) {
        AnalyticsUserEventClp oldClpModel = (AnalyticsUserEventClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getAnalyticsUserEventRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputBalloonLink(BaseModel<?> oldModel) {
        BalloonLinkClp oldClpModel = (BalloonLinkClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getBalloonLinkRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputBalloonStatsEntry(BaseModel<?> oldModel) {
        BalloonStatsEntryClp oldClpModel = (BalloonStatsEntryClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getBalloonStatsEntryRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputBalloonText(BaseModel<?> oldModel) {
        BalloonTextClp oldClpModel = (BalloonTextClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getBalloonTextRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputBalloonUserTracking(
        BaseModel<?> oldModel) {
        BalloonUserTrackingClp oldClpModel = (BalloonUserTrackingClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getBalloonUserTrackingRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContest(BaseModel<?> oldModel) {
        ContestClp oldClpModel = (ContestClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContestDebate(BaseModel<?> oldModel) {
        ContestDebateClp oldClpModel = (ContestDebateClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestDebateRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContestDiscussion(BaseModel<?> oldModel) {
        ContestDiscussionClp oldClpModel = (ContestDiscussionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestDiscussionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContestEmailTemplate(
        BaseModel<?> oldModel) {
        ContestEmailTemplateClp oldClpModel = (ContestEmailTemplateClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestEmailTemplateRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContestPhase(BaseModel<?> oldModel) {
        ContestPhaseClp oldClpModel = (ContestPhaseClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestPhaseRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContestPhaseColumn(BaseModel<?> oldModel) {
        ContestPhaseColumnClp oldClpModel = (ContestPhaseColumnClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestPhaseColumnRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContestPhaseRibbonType(
        BaseModel<?> oldModel) {
        ContestPhaseRibbonTypeClp oldClpModel = (ContestPhaseRibbonTypeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestPhaseRibbonTypeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContestPhaseType(BaseModel<?> oldModel) {
        ContestPhaseTypeClp oldClpModel = (ContestPhaseTypeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestPhaseTypeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContestSchedule(BaseModel<?> oldModel) {
        ContestScheduleClp oldClpModel = (ContestScheduleClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestScheduleRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContestTeamMember(BaseModel<?> oldModel) {
        ContestTeamMemberClp oldClpModel = (ContestTeamMemberClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestTeamMemberRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDiscussionCategory(BaseModel<?> oldModel) {
        DiscussionCategoryClp oldClpModel = (DiscussionCategoryClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDiscussionCategoryRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDiscussionCategoryGroup(
        BaseModel<?> oldModel) {
        DiscussionCategoryGroupClp oldClpModel = (DiscussionCategoryGroupClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDiscussionCategoryGroupRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDiscussionMessage(BaseModel<?> oldModel) {
        DiscussionMessageClp oldClpModel = (DiscussionMessageClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDiscussionMessageRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputDiscussionMessageFlag(
        BaseModel<?> oldModel) {
        DiscussionMessageFlagClp oldClpModel = (DiscussionMessageFlagClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getDiscussionMessageFlagRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputEmailList(BaseModel<?> oldModel) {
        EmailListClp oldClpModel = (EmailListClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getEmailListRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputFocusArea(BaseModel<?> oldModel) {
        FocusAreaClp oldClpModel = (FocusAreaClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getFocusAreaRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputFocusAreaOntologyTerm(
        BaseModel<?> oldModel) {
        FocusAreaOntologyTermClp oldClpModel = (FocusAreaOntologyTermClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getFocusAreaOntologyTermRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputLandingPage(BaseModel<?> oldModel) {
        LandingPageClp oldClpModel = (LandingPageClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getLandingPageRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputLoginLog(BaseModel<?> oldModel) {
        LoginLogClp oldClpModel = (LoginLogClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getLoginLogRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMessage(BaseModel<?> oldModel) {
        MessageClp oldClpModel = (MessageClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMessageRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMessageRecipientStatus(
        BaseModel<?> oldModel) {
        MessageRecipientStatusClp oldClpModel = (MessageRecipientStatusClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMessageRecipientStatusRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMessagingIgnoredRecipients(
        BaseModel<?> oldModel) {
        MessagingIgnoredRecipientsClp oldClpModel = (MessagingIgnoredRecipientsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMessagingIgnoredRecipientsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMessagingMessage(BaseModel<?> oldModel) {
        MessagingMessageClp oldClpModel = (MessagingMessageClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMessagingMessageRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMessagingMessageConversion(
        BaseModel<?> oldModel) {
        MessagingMessageConversionClp oldClpModel = (MessagingMessageConversionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMessagingMessageConversionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMessagingMessageConversionType(
        BaseModel<?> oldModel) {
        MessagingMessageConversionTypeClp oldClpModel = (MessagingMessageConversionTypeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMessagingMessageConversionTypeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMessagingMessageRecipient(
        BaseModel<?> oldModel) {
        MessagingMessageRecipientClp oldClpModel = (MessagingMessageRecipientClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMessagingMessageRecipientRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMessagingRedirectLink(
        BaseModel<?> oldModel) {
        MessagingRedirectLinkClp oldClpModel = (MessagingRedirectLinkClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMessagingRedirectLinkRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputMessagingUserPreferences(
        BaseModel<?> oldModel) {
        MessagingUserPreferencesClp oldClpModel = (MessagingUserPreferencesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getMessagingUserPreferencesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputModelCategory(BaseModel<?> oldModel) {
        ModelCategoryClp oldClpModel = (ModelCategoryClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getModelCategoryRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputModelDiscussion(BaseModel<?> oldModel) {
        ModelDiscussionClp oldClpModel = (ModelDiscussionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getModelDiscussionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputModelGlobalPreference(
        BaseModel<?> oldModel) {
        ModelGlobalPreferenceClp oldClpModel = (ModelGlobalPreferenceClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getModelGlobalPreferenceRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputModelInputGroup(BaseModel<?> oldModel) {
        ModelInputGroupClp oldClpModel = (ModelInputGroupClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getModelInputGroupRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputModelInputItem(BaseModel<?> oldModel) {
        ModelInputItemClp oldClpModel = (ModelInputItemClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getModelInputItemRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputModelOutputChartOrder(
        BaseModel<?> oldModel) {
        ModelOutputChartOrderClp oldClpModel = (ModelOutputChartOrderClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getModelOutputChartOrderRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputModelOutputItem(BaseModel<?> oldModel) {
        ModelOutputItemClp oldClpModel = (ModelOutputItemClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getModelOutputItemRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputModelPosition(BaseModel<?> oldModel) {
        ModelPositionClp oldClpModel = (ModelPositionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getModelPositionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputOntologySpace(BaseModel<?> oldModel) {
        OntologySpaceClp oldClpModel = (OntologySpaceClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getOntologySpaceRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputOntologyTerm(BaseModel<?> oldModel) {
        OntologyTermClp oldClpModel = (OntologyTermClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getOntologyTermRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputOntologyTermEntity(BaseModel<?> oldModel) {
        OntologyTermEntityClp oldClpModel = (OntologyTermEntityClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getOntologyTermEntityRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlan2Proposal(BaseModel<?> oldModel) {
        Plan2ProposalClp oldClpModel = (Plan2ProposalClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlan2ProposalRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanAttribute(BaseModel<?> oldModel) {
        PlanAttributeClp oldClpModel = (PlanAttributeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanAttributeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanAttributeFilter(
        BaseModel<?> oldModel) {
        PlanAttributeFilterClp oldClpModel = (PlanAttributeFilterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanAttributeFilterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanColumnSettings(BaseModel<?> oldModel) {
        PlanColumnSettingsClp oldClpModel = (PlanColumnSettingsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanColumnSettingsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanDescription(BaseModel<?> oldModel) {
        PlanDescriptionClp oldClpModel = (PlanDescriptionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanDescriptionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanFan(BaseModel<?> oldModel) {
        PlanFanClp oldClpModel = (PlanFanClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanFanRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanItem(BaseModel<?> oldModel) {
        PlanItemClp oldClpModel = (PlanItemClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanItemRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanItemGroup(BaseModel<?> oldModel) {
        PlanItemGroupClp oldClpModel = (PlanItemGroupClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanItemGroupRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanMeta(BaseModel<?> oldModel) {
        PlanMetaClp oldClpModel = (PlanMetaClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanMetaRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanModelRun(BaseModel<?> oldModel) {
        PlanModelRunClp oldClpModel = (PlanModelRunClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanModelRunRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanPosition(BaseModel<?> oldModel) {
        PlanPositionClp oldClpModel = (PlanPositionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanPositionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanPositionItem(BaseModel<?> oldModel) {
        PlanPositionItemClp oldClpModel = (PlanPositionItemClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanPositionItemRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanPositions(BaseModel<?> oldModel) {
        PlanPositionsClp oldClpModel = (PlanPositionsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanPositionsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanPropertyFilter(BaseModel<?> oldModel) {
        PlanPropertyFilterClp oldClpModel = (PlanPropertyFilterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanPropertyFilterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanRelated(BaseModel<?> oldModel) {
        PlanRelatedClp oldClpModel = (PlanRelatedClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanRelatedRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanSection(BaseModel<?> oldModel) {
        PlanSectionClp oldClpModel = (PlanSectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanSectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanSectionDefinition(
        BaseModel<?> oldModel) {
        PlanSectionDefinitionClp oldClpModel = (PlanSectionDefinitionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanSectionDefinitionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanSectionPlanMap(BaseModel<?> oldModel) {
        PlanSectionPlanMapClp oldClpModel = (PlanSectionPlanMapClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanSectionPlanMapRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlansFilter(BaseModel<?> oldModel) {
        PlansFilterClp oldClpModel = (PlansFilterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlansFilterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlansFilterPosition(
        BaseModel<?> oldModel) {
        PlansFilterPositionClp oldClpModel = (PlansFilterPositionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlansFilterPositionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlansUserSettings(BaseModel<?> oldModel) {
        PlansUserSettingsClp oldClpModel = (PlansUserSettingsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlansUserSettingsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanTeamHistory(BaseModel<?> oldModel) {
        PlanTeamHistoryClp oldClpModel = (PlanTeamHistoryClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanTeamHistoryRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanTemplate(BaseModel<?> oldModel) {
        PlanTemplateClp oldClpModel = (PlanTemplateClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanTemplateRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanTemplateSection(
        BaseModel<?> oldModel) {
        PlanTemplateSectionClp oldClpModel = (PlanTemplateSectionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanTemplateSectionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanType(BaseModel<?> oldModel) {
        PlanTypeClp oldClpModel = (PlanTypeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanTypeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanTypeAttribute(BaseModel<?> oldModel) {
        PlanTypeAttributeClp oldClpModel = (PlanTypeAttributeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanTypeAttributeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanTypeColumn(BaseModel<?> oldModel) {
        PlanTypeColumnClp oldClpModel = (PlanTypeColumnClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanTypeColumnRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPlanVote(BaseModel<?> oldModel) {
        PlanVoteClp oldClpModel = (PlanVoteClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanVoteRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPointDistributionTarget(
        BaseModel<?> oldModel) {
        PointDistributionTargetClp oldClpModel = (PointDistributionTargetClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPointDistributionTargetRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPoints(BaseModel<?> oldModel) {
        PointsClp oldClpModel = (PointsClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPointsRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPointsDistributionConfiguration(
        BaseModel<?> oldModel) {
        PointsDistributionConfigurationClp oldClpModel = (PointsDistributionConfigurationClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPointsDistributionConfigurationRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputPointType(BaseModel<?> oldModel) {
        PointTypeClp oldClpModel = (PointTypeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPointTypeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposal(BaseModel<?> oldModel) {
        ProposalClp oldClpModel = (ProposalClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposal2Phase(BaseModel<?> oldModel) {
        Proposal2PhaseClp oldClpModel = (Proposal2PhaseClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposal2PhaseRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalAttribute(BaseModel<?> oldModel) {
        ProposalAttributeClp oldClpModel = (ProposalAttributeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalAttributeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalAttributeType(
        BaseModel<?> oldModel) {
        ProposalAttributeTypeClp oldClpModel = (ProposalAttributeTypeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalAttributeTypeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalContestPhaseAttribute(
        BaseModel<?> oldModel) {
        ProposalContestPhaseAttributeClp oldClpModel = (ProposalContestPhaseAttributeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalContestPhaseAttributeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalContestPhaseAttributeType(
        BaseModel<?> oldModel) {
        ProposalContestPhaseAttributeTypeClp oldClpModel = (ProposalContestPhaseAttributeTypeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalContestPhaseAttributeTypeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalRating(BaseModel<?> oldModel) {
        ProposalRatingClp oldClpModel = (ProposalRatingClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalRatingRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalRatingType(BaseModel<?> oldModel) {
        ProposalRatingTypeClp oldClpModel = (ProposalRatingTypeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalRatingTypeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalRatingValue(
        BaseModel<?> oldModel) {
        ProposalRatingValueClp oldClpModel = (ProposalRatingValueClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalRatingValueRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalSupporter(BaseModel<?> oldModel) {
        ProposalSupporterClp oldClpModel = (ProposalSupporterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalSupporterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalVersion(BaseModel<?> oldModel) {
        ProposalVersionClp oldClpModel = (ProposalVersionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalVersionRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalVote(BaseModel<?> oldModel) {
        ProposalVoteClp oldClpModel = (ProposalVoteClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalVoteRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputStaffMember(BaseModel<?> oldModel) {
        StaffMemberClp oldClpModel = (StaffMemberClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getStaffMemberRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputTrackedVisit(BaseModel<?> oldModel) {
        TrackedVisitClp oldClpModel = (TrackedVisitClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getTrackedVisitRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputTrackedVisitor2User(
        BaseModel<?> oldModel) {
        TrackedVisitor2UserClp oldClpModel = (TrackedVisitor2UserClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getTrackedVisitor2UserRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInput(Object obj) {
        if (obj instanceof BaseModel<?>) {
            return translateInput((BaseModel<?>) obj);
        } else if (obj instanceof List<?>) {
            return translateInput((List<Object>) obj);
        } else {
            return obj;
        }
    }

    public static Object translateOutput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ActivitySubscriptionImpl")) {
            return translateOutputActivitySubscription(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.AnalyticsUserEventImpl")) {
            return translateOutputAnalyticsUserEvent(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.BalloonLinkImpl")) {
            return translateOutputBalloonLink(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.BalloonStatsEntryImpl")) {
            return translateOutputBalloonStatsEntry(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.BalloonTextImpl")) {
            return translateOutputBalloonText(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.BalloonUserTrackingImpl")) {
            return translateOutputBalloonUserTracking(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.ContestImpl")) {
            return translateOutputContest(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestDebateImpl")) {
            return translateOutputContestDebate(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestDiscussionImpl")) {
            return translateOutputContestDiscussion(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestEmailTemplateImpl")) {
            return translateOutputContestEmailTemplate(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestPhaseImpl")) {
            return translateOutputContestPhase(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestPhaseColumnImpl")) {
            return translateOutputContestPhaseColumn(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestPhaseRibbonTypeImpl")) {
            return translateOutputContestPhaseRibbonType(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestPhaseTypeImpl")) {
            return translateOutputContestPhaseType(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestScheduleImpl")) {
            return translateOutputContestSchedule(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestTeamMemberImpl")) {
            return translateOutputContestTeamMember(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.DiscussionCategoryImpl")) {
            return translateOutputDiscussionCategory(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.DiscussionCategoryGroupImpl")) {
            return translateOutputDiscussionCategoryGroup(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.DiscussionMessageImpl")) {
            return translateOutputDiscussionMessage(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.DiscussionMessageFlagImpl")) {
            return translateOutputDiscussionMessageFlag(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.EmailListImpl")) {
            return translateOutputEmailList(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.FocusAreaImpl")) {
            return translateOutputFocusArea(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.FocusAreaOntologyTermImpl")) {
            return translateOutputFocusAreaOntologyTerm(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.LandingPageImpl")) {
            return translateOutputLandingPage(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.LoginLogImpl")) {
            return translateOutputLoginLog(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.MessageImpl")) {
            return translateOutputMessage(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.MessageRecipientStatusImpl")) {
            return translateOutputMessageRecipientStatus(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.MessagingIgnoredRecipientsImpl")) {
            return translateOutputMessagingIgnoredRecipients(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.MessagingMessageImpl")) {
            return translateOutputMessagingMessage(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.MessagingMessageConversionImpl")) {
            return translateOutputMessagingMessageConversion(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.MessagingMessageConversionTypeImpl")) {
            return translateOutputMessagingMessageConversionType(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.MessagingMessageRecipientImpl")) {
            return translateOutputMessagingMessageRecipient(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.MessagingRedirectLinkImpl")) {
            return translateOutputMessagingRedirectLink(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.MessagingUserPreferencesImpl")) {
            return translateOutputMessagingUserPreferences(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelCategoryImpl")) {
            return translateOutputModelCategory(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelDiscussionImpl")) {
            return translateOutputModelDiscussion(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelGlobalPreferenceImpl")) {
            return translateOutputModelGlobalPreference(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelInputGroupImpl")) {
            return translateOutputModelInputGroup(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelInputItemImpl")) {
            return translateOutputModelInputItem(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelOutputChartOrderImpl")) {
            return translateOutputModelOutputChartOrder(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelOutputItemImpl")) {
            return translateOutputModelOutputItem(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelPositionImpl")) {
            return translateOutputModelPosition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.OntologySpaceImpl")) {
            return translateOutputOntologySpace(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.OntologyTermImpl")) {
            return translateOutputOntologyTerm(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.OntologyTermEntityImpl")) {
            return translateOutputOntologyTermEntity(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.Plan2ProposalImpl")) {
            return translateOutputPlan2Proposal(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanAttributeImpl")) {
            return translateOutputPlanAttribute(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanAttributeFilterImpl")) {
            return translateOutputPlanAttributeFilter(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanColumnSettingsImpl")) {
            return translateOutputPlanColumnSettings(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanDescriptionImpl")) {
            return translateOutputPlanDescription(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.PlanFanImpl")) {
            return translateOutputPlanFan(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.PlanItemImpl")) {
            return translateOutputPlanItem(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanItemGroupImpl")) {
            return translateOutputPlanItemGroup(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.PlanMetaImpl")) {
            return translateOutputPlanMeta(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanModelRunImpl")) {
            return translateOutputPlanModelRun(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanPositionImpl")) {
            return translateOutputPlanPosition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanPositionItemImpl")) {
            return translateOutputPlanPositionItem(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanPositionsImpl")) {
            return translateOutputPlanPositions(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanPropertyFilterImpl")) {
            return translateOutputPlanPropertyFilter(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanRelatedImpl")) {
            return translateOutputPlanRelated(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanSectionImpl")) {
            return translateOutputPlanSection(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanSectionDefinitionImpl")) {
            return translateOutputPlanSectionDefinition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanSectionPlanMapImpl")) {
            return translateOutputPlanSectionPlanMap(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlansFilterImpl")) {
            return translateOutputPlansFilter(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlansFilterPositionImpl")) {
            return translateOutputPlansFilterPosition(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlansUserSettingsImpl")) {
            return translateOutputPlansUserSettings(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanTeamHistoryImpl")) {
            return translateOutputPlanTeamHistory(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanTemplateImpl")) {
            return translateOutputPlanTemplate(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanTemplateSectionImpl")) {
            return translateOutputPlanTemplateSection(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.PlanTypeImpl")) {
            return translateOutputPlanType(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanTypeAttributeImpl")) {
            return translateOutputPlanTypeAttribute(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanTypeColumnImpl")) {
            return translateOutputPlanTypeColumn(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.PlanVoteImpl")) {
            return translateOutputPlanVote(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PointDistributionTargetImpl")) {
            return translateOutputPointDistributionTarget(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.PointsImpl")) {
            return translateOutputPoints(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PointsDistributionConfigurationImpl")) {
            return translateOutputPointsDistributionConfiguration(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.PointTypeImpl")) {
            return translateOutputPointType(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.ProposalImpl")) {
            return translateOutputProposal(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.Proposal2PhaseImpl")) {
            return translateOutputProposal2Phase(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalAttributeImpl")) {
            return translateOutputProposalAttribute(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalAttributeTypeImpl")) {
            return translateOutputProposalAttributeType(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalContestPhaseAttributeImpl")) {
            return translateOutputProposalContestPhaseAttribute(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalContestPhaseAttributeTypeImpl")) {
            return translateOutputProposalContestPhaseAttributeType(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalRatingImpl")) {
            return translateOutputProposalRating(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalRatingTypeImpl")) {
            return translateOutputProposalRatingType(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalRatingValueImpl")) {
            return translateOutputProposalRatingValue(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalSupporterImpl")) {
            return translateOutputProposalSupporter(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalVersionImpl")) {
            return translateOutputProposalVersion(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalVoteImpl")) {
            return translateOutputProposalVote(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.StaffMemberImpl")) {
            return translateOutputStaffMember(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.TrackedVisitImpl")) {
            return translateOutputTrackedVisit(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.TrackedVisitor2UserImpl")) {
            return translateOutputTrackedVisitor2User(oldModel);
        }

        return oldModel;
    }

    public static Object translateOutput(List<Object> oldList) {
        List<Object> newList = new ArrayList<Object>(oldList.size());

        for (int i = 0; i < oldList.size(); i++) {
            Object curObj = oldList.get(i);

            newList.add(translateOutput(curObj));
        }

        return newList;
    }

    public static Object translateOutput(Object obj) {
        if (obj instanceof BaseModel<?>) {
            return translateOutput((BaseModel<?>) obj);
        } else if (obj instanceof List<?>) {
            return translateOutput((List<Object>) obj);
        } else {
            return obj;
        }
    }

    public static Throwable translateThrowable(Throwable throwable) {
        if (_useReflectionToTranslateThrowable) {
            try {
                UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

                objectOutputStream.writeObject(throwable);

                objectOutputStream.flush();
                objectOutputStream.close();

                UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
                        0, unsyncByteArrayOutputStream.size());

                Thread currentThread = Thread.currentThread();

                ClassLoader contextClassLoader = currentThread.getContextClassLoader();

                ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
                        contextClassLoader);

                throwable = (Throwable) objectInputStream.readObject();

                objectInputStream.close();

                return throwable;
            } catch (SecurityException se) {
                if (_log.isInfoEnabled()) {
                    _log.info("Do not use reflection to translate throwable");
                }

                _useReflectionToTranslateThrowable = false;
            } catch (Throwable throwable2) {
                _log.error(throwable2, throwable2);

                return throwable2;
            }
        }

        Class<?> clazz = throwable.getClass();

        String className = clazz.getName();

        if (className.equals(PortalException.class.getName())) {
            return new PortalException();
        }

        if (className.equals(SystemException.class.getName())) {
            return new SystemException();
        }

        if (className.equals("com.ext.portlet.ModelNameException")) {
            return new com.ext.portlet.ModelNameException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchActivitySubscriptionException")) {
            return new com.ext.portlet.NoSuchActivitySubscriptionException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestException")) {
            return new com.ext.portlet.NoSuchContestException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestPhaseException")) {
            return new com.ext.portlet.NoSuchContestPhaseException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchContestPhaseExceptionException")) {
            return new com.ext.portlet.NoSuchContestPhaseExceptionException();
        }

        if (className.equals("com.ext.portlet.NoSuchEmailListException")) {
            return new com.ext.portlet.NoSuchEmailListException();
        }

        if (className.equals("com.ext.portlet.NoSuchEntityException")) {
            return new com.ext.portlet.NoSuchEntityException();
        }

        if (className.equals("com.ext.portlet.NoSuchEntityIdException")) {
            return new com.ext.portlet.NoSuchEntityIdException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchFocusAreaExceptionException")) {
            return new com.ext.portlet.NoSuchFocusAreaExceptionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchFocusAreaOntologyTermExceptionException")) {
            return new com.ext.portlet.NoSuchFocusAreaOntologyTermExceptionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchLandingPageExceptionException")) {
            return new com.ext.portlet.NoSuchLandingPageExceptionException();
        }

        if (className.equals("com.ext.portlet.NoSuchLoginLogExceptionException")) {
            return new com.ext.portlet.NoSuchLoginLogExceptionException();
        }

        if (className.equals("com.ext.portlet.NoSuchMessageException")) {
            return new com.ext.portlet.NoSuchMessageException();
        }

        if (className.equals("com.ext.portlet.NoSuchMessageRecipientException")) {
            return new com.ext.portlet.NoSuchMessageRecipientException();
        }

        if (className.equals("com.ext.portlet.NoSuchMessageStatusException")) {
            return new com.ext.portlet.NoSuchMessageStatusException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingIgnoredRecipientsExceptionException")) {
            return new com.ext.portlet.NoSuchMessagingIgnoredRecipientsExceptionException();
        }

        if (className.equals("com.ext.portlet.NoSuchMessagingMessageException")) {
            return new com.ext.portlet.NoSuchMessagingMessageException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingMessageConversionException")) {
            return new com.ext.portlet.NoSuchMessagingMessageConversionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingMessageConversionTypeException")) {
            return new com.ext.portlet.NoSuchMessagingMessageConversionTypeException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingMessageRecipientException")) {
            return new com.ext.portlet.NoSuchMessagingMessageRecipientException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingUserPreferencesException")) {
            return new com.ext.portlet.NoSuchMessagingUserPreferencesException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelException")) {
            return new com.ext.portlet.NoSuchModelException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelInputGroupException")) {
            return new com.ext.portlet.NoSuchModelInputGroupException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelInputItemException")) {
            return new com.ext.portlet.NoSuchModelInputItemException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchModelOutputChartOrderException")) {
            return new com.ext.portlet.NoSuchModelOutputChartOrderException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelOutputItemException")) {
            return new com.ext.portlet.NoSuchModelOutputItemException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelPositionException")) {
            return new com.ext.portlet.NoSuchModelPositionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchOntologyTermEntityExceptionException")) {
            return new com.ext.portlet.NoSuchOntologyTermEntityExceptionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchOntologyTermExceptionException")) {
            return new com.ext.portlet.NoSuchOntologyTermExceptionException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanException")) {
            return new com.ext.portlet.NoSuchPlanException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanPositionException")) {
            return new com.ext.portlet.NoSuchPlanPositionException();
        }

        if (className.equals("com.ext.portlet.NoSuchVoteException")) {
            return new com.ext.portlet.NoSuchVoteException();
        }

        if (className.equals("com.ext.portlet.PlanNameException")) {
            return new com.ext.portlet.PlanNameException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchActivitySubscriptionException")) {
            return new com.ext.portlet.NoSuchActivitySubscriptionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchAnalyticsUserEventException")) {
            return new com.ext.portlet.NoSuchAnalyticsUserEventException();
        }

        if (className.equals("com.ext.portlet.NoSuchBalloonLinkException")) {
            return new com.ext.portlet.NoSuchBalloonLinkException();
        }

        if (className.equals("com.ext.portlet.NoSuchBalloonStatsEntryException")) {
            return new com.ext.portlet.NoSuchBalloonStatsEntryException();
        }

        if (className.equals("com.ext.portlet.NoSuchBalloonTextException")) {
            return new com.ext.portlet.NoSuchBalloonTextException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchBalloonUserTrackingException")) {
            return new com.ext.portlet.NoSuchBalloonUserTrackingException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestException")) {
            return new com.ext.portlet.NoSuchContestException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestDebateException")) {
            return new com.ext.portlet.NoSuchContestDebateException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestDiscussionException")) {
            return new com.ext.portlet.NoSuchContestDiscussionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchContestEmailTemplateException")) {
            return new com.ext.portlet.NoSuchContestEmailTemplateException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestPhaseException")) {
            return new com.ext.portlet.NoSuchContestPhaseException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchContestPhaseColumnException")) {
            return new com.ext.portlet.NoSuchContestPhaseColumnException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchContestPhaseRibbonTypeException")) {
            return new com.ext.portlet.NoSuchContestPhaseRibbonTypeException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestPhaseTypeException")) {
            return new com.ext.portlet.NoSuchContestPhaseTypeException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestScheduleException")) {
            return new com.ext.portlet.NoSuchContestScheduleException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestTeamMemberException")) {
            return new com.ext.portlet.NoSuchContestTeamMemberException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchDiscussionCategoryException")) {
            return new com.ext.portlet.NoSuchDiscussionCategoryException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchDiscussionCategoryGroupException")) {
            return new com.ext.portlet.NoSuchDiscussionCategoryGroupException();
        }

        if (className.equals("com.ext.portlet.NoSuchDiscussionMessageException")) {
            return new com.ext.portlet.NoSuchDiscussionMessageException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchDiscussionMessageFlagException")) {
            return new com.ext.portlet.NoSuchDiscussionMessageFlagException();
        }

        if (className.equals("com.ext.portlet.NoSuchEmailListException")) {
            return new com.ext.portlet.NoSuchEmailListException();
        }

        if (className.equals("com.ext.portlet.NoSuchFocusAreaException")) {
            return new com.ext.portlet.NoSuchFocusAreaException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchFocusAreaOntologyTermException")) {
            return new com.ext.portlet.NoSuchFocusAreaOntologyTermException();
        }

        if (className.equals("com.ext.portlet.NoSuchLandingPageException")) {
            return new com.ext.portlet.NoSuchLandingPageException();
        }

        if (className.equals("com.ext.portlet.NoSuchLoginLogException")) {
            return new com.ext.portlet.NoSuchLoginLogException();
        }

        if (className.equals("com.ext.portlet.NoSuchMessageException")) {
            return new com.ext.portlet.NoSuchMessageException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessageRecipientStatusException")) {
            return new com.ext.portlet.NoSuchMessageRecipientStatusException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingIgnoredRecipientsException")) {
            return new com.ext.portlet.NoSuchMessagingIgnoredRecipientsException();
        }

        if (className.equals("com.ext.portlet.NoSuchMessagingMessageException")) {
            return new com.ext.portlet.NoSuchMessagingMessageException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingMessageConversionException")) {
            return new com.ext.portlet.NoSuchMessagingMessageConversionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingMessageConversionTypeException")) {
            return new com.ext.portlet.NoSuchMessagingMessageConversionTypeException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingMessageRecipientException")) {
            return new com.ext.portlet.NoSuchMessagingMessageRecipientException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingRedirectLinkException")) {
            return new com.ext.portlet.NoSuchMessagingRedirectLinkException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchMessagingUserPreferencesException")) {
            return new com.ext.portlet.NoSuchMessagingUserPreferencesException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelCategoryException")) {
            return new com.ext.portlet.NoSuchModelCategoryException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelDiscussionException")) {
            return new com.ext.portlet.NoSuchModelDiscussionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchModelGlobalPreferenceException")) {
            return new com.ext.portlet.NoSuchModelGlobalPreferenceException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelInputGroupException")) {
            return new com.ext.portlet.NoSuchModelInputGroupException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelInputItemException")) {
            return new com.ext.portlet.NoSuchModelInputItemException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchModelOutputChartOrderException")) {
            return new com.ext.portlet.NoSuchModelOutputChartOrderException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelOutputItemException")) {
            return new com.ext.portlet.NoSuchModelOutputItemException();
        }

        if (className.equals("com.ext.portlet.NoSuchModelPositionException")) {
            return new com.ext.portlet.NoSuchModelPositionException();
        }

        if (className.equals("com.ext.portlet.NoSuchOntologySpaceException")) {
            return new com.ext.portlet.NoSuchOntologySpaceException();
        }

        if (className.equals("com.ext.portlet.NoSuchOntologyTermException")) {
            return new com.ext.portlet.NoSuchOntologyTermException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchOntologyTermEntityException")) {
            return new com.ext.portlet.NoSuchOntologyTermEntityException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlan2ProposalException")) {
            return new com.ext.portlet.NoSuchPlan2ProposalException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanAttributeException")) {
            return new com.ext.portlet.NoSuchPlanAttributeException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchPlanAttributeFilterException")) {
            return new com.ext.portlet.NoSuchPlanAttributeFilterException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchPlanColumnSettingsException")) {
            return new com.ext.portlet.NoSuchPlanColumnSettingsException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanDescriptionException")) {
            return new com.ext.portlet.NoSuchPlanDescriptionException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanFanException")) {
            return new com.ext.portlet.NoSuchPlanFanException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanItemException")) {
            return new com.ext.portlet.NoSuchPlanItemException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanItemGroupException")) {
            return new com.ext.portlet.NoSuchPlanItemGroupException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanMetaException")) {
            return new com.ext.portlet.NoSuchPlanMetaException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanModelRunException")) {
            return new com.ext.portlet.NoSuchPlanModelRunException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanPositionException")) {
            return new com.ext.portlet.NoSuchPlanPositionException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanPositionItemException")) {
            return new com.ext.portlet.NoSuchPlanPositionItemException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanPositionsException")) {
            return new com.ext.portlet.NoSuchPlanPositionsException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchPlanPropertyFilterException")) {
            return new com.ext.portlet.NoSuchPlanPropertyFilterException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanRelatedException")) {
            return new com.ext.portlet.NoSuchPlanRelatedException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanSectionException")) {
            return new com.ext.portlet.NoSuchPlanSectionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchPlanSectionDefinitionException")) {
            return new com.ext.portlet.NoSuchPlanSectionDefinitionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchPlanSectionPlanMapException")) {
            return new com.ext.portlet.NoSuchPlanSectionPlanMapException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlansFilterException")) {
            return new com.ext.portlet.NoSuchPlansFilterException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchPlansFilterPositionException")) {
            return new com.ext.portlet.NoSuchPlansFilterPositionException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlansUserSettingsException")) {
            return new com.ext.portlet.NoSuchPlansUserSettingsException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanTeamHistoryException")) {
            return new com.ext.portlet.NoSuchPlanTeamHistoryException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanTemplateException")) {
            return new com.ext.portlet.NoSuchPlanTemplateException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchPlanTemplateSectionException")) {
            return new com.ext.portlet.NoSuchPlanTemplateSectionException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanTypeException")) {
            return new com.ext.portlet.NoSuchPlanTypeException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanTypeAttributeException")) {
            return new com.ext.portlet.NoSuchPlanTypeAttributeException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanTypeColumnException")) {
            return new com.ext.portlet.NoSuchPlanTypeColumnException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanVoteException")) {
            return new com.ext.portlet.NoSuchPlanVoteException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchPointDistributionTargetException")) {
            return new com.ext.portlet.NoSuchPointDistributionTargetException();
        }

        if (className.equals("com.ext.portlet.NoSuchPointsException")) {
            return new com.ext.portlet.NoSuchPointsException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchPointsDistributionConfigurationException")) {
            return new com.ext.portlet.NoSuchPointsDistributionConfigurationException();
        }

        if (className.equals("com.ext.portlet.NoSuchPointTypeException")) {
            return new com.ext.portlet.NoSuchPointTypeException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalException")) {
            return new com.ext.portlet.NoSuchProposalException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposal2PhaseException")) {
            return new com.ext.portlet.NoSuchProposal2PhaseException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalAttributeException")) {
            return new com.ext.portlet.NoSuchProposalAttributeException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchProposalAttributeTypeException")) {
            return new com.ext.portlet.NoSuchProposalAttributeTypeException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchProposalContestPhaseAttributeException")) {
            return new com.ext.portlet.NoSuchProposalContestPhaseAttributeException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException")) {
            return new com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalRatingException")) {
            return new com.ext.portlet.NoSuchProposalRatingException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchProposalRatingTypeException")) {
            return new com.ext.portlet.NoSuchProposalRatingTypeException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchProposalRatingValueException")) {
            return new com.ext.portlet.NoSuchProposalRatingValueException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalSupporterException")) {
            return new com.ext.portlet.NoSuchProposalSupporterException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalVersionException")) {
            return new com.ext.portlet.NoSuchProposalVersionException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalVoteException")) {
            return new com.ext.portlet.NoSuchProposalVoteException();
        }

        if (className.equals("com.ext.portlet.NoSuchStaffMemberException")) {
            return new com.ext.portlet.NoSuchStaffMemberException();
        }

        if (className.equals("com.ext.portlet.NoSuchTrackedVisitException")) {
            return new com.ext.portlet.NoSuchTrackedVisitException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchTrackedVisitor2UserException")) {
            return new com.ext.portlet.NoSuchTrackedVisitor2UserException();
        }

        return throwable;
    }

    public static Object translateOutputActivitySubscription(
        BaseModel<?> oldModel) {
        ActivitySubscriptionClp newModel = new ActivitySubscriptionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setActivitySubscriptionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputAnalyticsUserEvent(
        BaseModel<?> oldModel) {
        AnalyticsUserEventClp newModel = new AnalyticsUserEventClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setAnalyticsUserEventRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputBalloonLink(BaseModel<?> oldModel) {
        BalloonLinkClp newModel = new BalloonLinkClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setBalloonLinkRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputBalloonStatsEntry(BaseModel<?> oldModel) {
        BalloonStatsEntryClp newModel = new BalloonStatsEntryClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setBalloonStatsEntryRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputBalloonText(BaseModel<?> oldModel) {
        BalloonTextClp newModel = new BalloonTextClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setBalloonTextRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputBalloonUserTracking(
        BaseModel<?> oldModel) {
        BalloonUserTrackingClp newModel = new BalloonUserTrackingClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setBalloonUserTrackingRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContest(BaseModel<?> oldModel) {
        ContestClp newModel = new ContestClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContestDebate(BaseModel<?> oldModel) {
        ContestDebateClp newModel = new ContestDebateClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestDebateRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContestDiscussion(BaseModel<?> oldModel) {
        ContestDiscussionClp newModel = new ContestDiscussionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestDiscussionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContestEmailTemplate(
        BaseModel<?> oldModel) {
        ContestEmailTemplateClp newModel = new ContestEmailTemplateClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestEmailTemplateRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContestPhase(BaseModel<?> oldModel) {
        ContestPhaseClp newModel = new ContestPhaseClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestPhaseRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContestPhaseColumn(
        BaseModel<?> oldModel) {
        ContestPhaseColumnClp newModel = new ContestPhaseColumnClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestPhaseColumnRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContestPhaseRibbonType(
        BaseModel<?> oldModel) {
        ContestPhaseRibbonTypeClp newModel = new ContestPhaseRibbonTypeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestPhaseRibbonTypeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContestPhaseType(BaseModel<?> oldModel) {
        ContestPhaseTypeClp newModel = new ContestPhaseTypeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestPhaseTypeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContestSchedule(BaseModel<?> oldModel) {
        ContestScheduleClp newModel = new ContestScheduleClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestScheduleRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContestTeamMember(BaseModel<?> oldModel) {
        ContestTeamMemberClp newModel = new ContestTeamMemberClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestTeamMemberRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDiscussionCategory(
        BaseModel<?> oldModel) {
        DiscussionCategoryClp newModel = new DiscussionCategoryClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDiscussionCategoryRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDiscussionCategoryGroup(
        BaseModel<?> oldModel) {
        DiscussionCategoryGroupClp newModel = new DiscussionCategoryGroupClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDiscussionCategoryGroupRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDiscussionMessage(BaseModel<?> oldModel) {
        DiscussionMessageClp newModel = new DiscussionMessageClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDiscussionMessageRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputDiscussionMessageFlag(
        BaseModel<?> oldModel) {
        DiscussionMessageFlagClp newModel = new DiscussionMessageFlagClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setDiscussionMessageFlagRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputEmailList(BaseModel<?> oldModel) {
        EmailListClp newModel = new EmailListClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setEmailListRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputFocusArea(BaseModel<?> oldModel) {
        FocusAreaClp newModel = new FocusAreaClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setFocusAreaRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputFocusAreaOntologyTerm(
        BaseModel<?> oldModel) {
        FocusAreaOntologyTermClp newModel = new FocusAreaOntologyTermClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setFocusAreaOntologyTermRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputLandingPage(BaseModel<?> oldModel) {
        LandingPageClp newModel = new LandingPageClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setLandingPageRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputLoginLog(BaseModel<?> oldModel) {
        LoginLogClp newModel = new LoginLogClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setLoginLogRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMessage(BaseModel<?> oldModel) {
        MessageClp newModel = new MessageClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMessageRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMessageRecipientStatus(
        BaseModel<?> oldModel) {
        MessageRecipientStatusClp newModel = new MessageRecipientStatusClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMessageRecipientStatusRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMessagingIgnoredRecipients(
        BaseModel<?> oldModel) {
        MessagingIgnoredRecipientsClp newModel = new MessagingIgnoredRecipientsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMessagingIgnoredRecipientsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMessagingMessage(BaseModel<?> oldModel) {
        MessagingMessageClp newModel = new MessagingMessageClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMessagingMessageRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMessagingMessageConversion(
        BaseModel<?> oldModel) {
        MessagingMessageConversionClp newModel = new MessagingMessageConversionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMessagingMessageConversionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMessagingMessageConversionType(
        BaseModel<?> oldModel) {
        MessagingMessageConversionTypeClp newModel = new MessagingMessageConversionTypeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMessagingMessageConversionTypeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMessagingMessageRecipient(
        BaseModel<?> oldModel) {
        MessagingMessageRecipientClp newModel = new MessagingMessageRecipientClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMessagingMessageRecipientRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMessagingRedirectLink(
        BaseModel<?> oldModel) {
        MessagingRedirectLinkClp newModel = new MessagingRedirectLinkClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMessagingRedirectLinkRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputMessagingUserPreferences(
        BaseModel<?> oldModel) {
        MessagingUserPreferencesClp newModel = new MessagingUserPreferencesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setMessagingUserPreferencesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputModelCategory(BaseModel<?> oldModel) {
        ModelCategoryClp newModel = new ModelCategoryClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setModelCategoryRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputModelDiscussion(BaseModel<?> oldModel) {
        ModelDiscussionClp newModel = new ModelDiscussionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setModelDiscussionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputModelGlobalPreference(
        BaseModel<?> oldModel) {
        ModelGlobalPreferenceClp newModel = new ModelGlobalPreferenceClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setModelGlobalPreferenceRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputModelInputGroup(BaseModel<?> oldModel) {
        ModelInputGroupClp newModel = new ModelInputGroupClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setModelInputGroupRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputModelInputItem(BaseModel<?> oldModel) {
        ModelInputItemClp newModel = new ModelInputItemClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setModelInputItemRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputModelOutputChartOrder(
        BaseModel<?> oldModel) {
        ModelOutputChartOrderClp newModel = new ModelOutputChartOrderClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setModelOutputChartOrderRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputModelOutputItem(BaseModel<?> oldModel) {
        ModelOutputItemClp newModel = new ModelOutputItemClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setModelOutputItemRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputModelPosition(BaseModel<?> oldModel) {
        ModelPositionClp newModel = new ModelPositionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setModelPositionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputOntologySpace(BaseModel<?> oldModel) {
        OntologySpaceClp newModel = new OntologySpaceClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setOntologySpaceRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputOntologyTerm(BaseModel<?> oldModel) {
        OntologyTermClp newModel = new OntologyTermClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setOntologyTermRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputOntologyTermEntity(
        BaseModel<?> oldModel) {
        OntologyTermEntityClp newModel = new OntologyTermEntityClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setOntologyTermEntityRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlan2Proposal(BaseModel<?> oldModel) {
        Plan2ProposalClp newModel = new Plan2ProposalClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlan2ProposalRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanAttribute(BaseModel<?> oldModel) {
        PlanAttributeClp newModel = new PlanAttributeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanAttributeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanAttributeFilter(
        BaseModel<?> oldModel) {
        PlanAttributeFilterClp newModel = new PlanAttributeFilterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanAttributeFilterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanColumnSettings(
        BaseModel<?> oldModel) {
        PlanColumnSettingsClp newModel = new PlanColumnSettingsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanColumnSettingsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanDescription(BaseModel<?> oldModel) {
        PlanDescriptionClp newModel = new PlanDescriptionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanDescriptionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanFan(BaseModel<?> oldModel) {
        PlanFanClp newModel = new PlanFanClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanFanRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanItem(BaseModel<?> oldModel) {
        PlanItemClp newModel = new PlanItemClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanItemRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanItemGroup(BaseModel<?> oldModel) {
        PlanItemGroupClp newModel = new PlanItemGroupClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanItemGroupRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanMeta(BaseModel<?> oldModel) {
        PlanMetaClp newModel = new PlanMetaClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanMetaRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanModelRun(BaseModel<?> oldModel) {
        PlanModelRunClp newModel = new PlanModelRunClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanModelRunRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanPosition(BaseModel<?> oldModel) {
        PlanPositionClp newModel = new PlanPositionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanPositionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanPositionItem(BaseModel<?> oldModel) {
        PlanPositionItemClp newModel = new PlanPositionItemClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanPositionItemRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanPositions(BaseModel<?> oldModel) {
        PlanPositionsClp newModel = new PlanPositionsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanPositionsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanPropertyFilter(
        BaseModel<?> oldModel) {
        PlanPropertyFilterClp newModel = new PlanPropertyFilterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanPropertyFilterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanRelated(BaseModel<?> oldModel) {
        PlanRelatedClp newModel = new PlanRelatedClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanRelatedRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanSection(BaseModel<?> oldModel) {
        PlanSectionClp newModel = new PlanSectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanSectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanSectionDefinition(
        BaseModel<?> oldModel) {
        PlanSectionDefinitionClp newModel = new PlanSectionDefinitionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanSectionDefinitionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanSectionPlanMap(
        BaseModel<?> oldModel) {
        PlanSectionPlanMapClp newModel = new PlanSectionPlanMapClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanSectionPlanMapRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlansFilter(BaseModel<?> oldModel) {
        PlansFilterClp newModel = new PlansFilterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlansFilterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlansFilterPosition(
        BaseModel<?> oldModel) {
        PlansFilterPositionClp newModel = new PlansFilterPositionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlansFilterPositionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlansUserSettings(BaseModel<?> oldModel) {
        PlansUserSettingsClp newModel = new PlansUserSettingsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlansUserSettingsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanTeamHistory(BaseModel<?> oldModel) {
        PlanTeamHistoryClp newModel = new PlanTeamHistoryClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanTeamHistoryRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanTemplate(BaseModel<?> oldModel) {
        PlanTemplateClp newModel = new PlanTemplateClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanTemplateRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanTemplateSection(
        BaseModel<?> oldModel) {
        PlanTemplateSectionClp newModel = new PlanTemplateSectionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanTemplateSectionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanType(BaseModel<?> oldModel) {
        PlanTypeClp newModel = new PlanTypeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanTypeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanTypeAttribute(BaseModel<?> oldModel) {
        PlanTypeAttributeClp newModel = new PlanTypeAttributeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanTypeAttributeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanTypeColumn(BaseModel<?> oldModel) {
        PlanTypeColumnClp newModel = new PlanTypeColumnClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanTypeColumnRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPlanVote(BaseModel<?> oldModel) {
        PlanVoteClp newModel = new PlanVoteClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanVoteRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPointDistributionTarget(
        BaseModel<?> oldModel) {
        PointDistributionTargetClp newModel = new PointDistributionTargetClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPointDistributionTargetRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPoints(BaseModel<?> oldModel) {
        PointsClp newModel = new PointsClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPointsRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPointsDistributionConfiguration(
        BaseModel<?> oldModel) {
        PointsDistributionConfigurationClp newModel = new PointsDistributionConfigurationClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPointsDistributionConfigurationRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputPointType(BaseModel<?> oldModel) {
        PointTypeClp newModel = new PointTypeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPointTypeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposal(BaseModel<?> oldModel) {
        ProposalClp newModel = new ProposalClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposal2Phase(BaseModel<?> oldModel) {
        Proposal2PhaseClp newModel = new Proposal2PhaseClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposal2PhaseRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalAttribute(BaseModel<?> oldModel) {
        ProposalAttributeClp newModel = new ProposalAttributeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalAttributeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalAttributeType(
        BaseModel<?> oldModel) {
        ProposalAttributeTypeClp newModel = new ProposalAttributeTypeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalAttributeTypeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalContestPhaseAttribute(
        BaseModel<?> oldModel) {
        ProposalContestPhaseAttributeClp newModel = new ProposalContestPhaseAttributeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalContestPhaseAttributeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalContestPhaseAttributeType(
        BaseModel<?> oldModel) {
        ProposalContestPhaseAttributeTypeClp newModel = new ProposalContestPhaseAttributeTypeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalContestPhaseAttributeTypeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalRating(BaseModel<?> oldModel) {
        ProposalRatingClp newModel = new ProposalRatingClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalRatingRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalRatingType(
        BaseModel<?> oldModel) {
        ProposalRatingTypeClp newModel = new ProposalRatingTypeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalRatingTypeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalRatingValue(
        BaseModel<?> oldModel) {
        ProposalRatingValueClp newModel = new ProposalRatingValueClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalRatingValueRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalSupporter(BaseModel<?> oldModel) {
        ProposalSupporterClp newModel = new ProposalSupporterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalSupporterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalVersion(BaseModel<?> oldModel) {
        ProposalVersionClp newModel = new ProposalVersionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalVersionRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalVote(BaseModel<?> oldModel) {
        ProposalVoteClp newModel = new ProposalVoteClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalVoteRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputStaffMember(BaseModel<?> oldModel) {
        StaffMemberClp newModel = new StaffMemberClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setStaffMemberRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputTrackedVisit(BaseModel<?> oldModel) {
        TrackedVisitClp newModel = new TrackedVisitClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setTrackedVisitRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputTrackedVisitor2User(
        BaseModel<?> oldModel) {
        TrackedVisitor2UserClp newModel = new TrackedVisitor2UserClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setTrackedVisitor2UserRemoteModel(oldModel);

        return newModel;
    }
}
