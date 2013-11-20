package com.ext.portlet.service;

import com.ext.portlet.model.ActivitySubscriptionClp;
import com.ext.portlet.model.AnalyticsUserEventClp;
import com.ext.portlet.model.BalloonStatsEntryClp;
import com.ext.portlet.model.ContestClp;
import com.ext.portlet.model.ContestDebateClp;
import com.ext.portlet.model.ContestPhaseClp;
import com.ext.portlet.model.ContestPhaseColumnClp;
import com.ext.portlet.model.ContestPhaseRibbonTypeClp;
import com.ext.portlet.model.ContestPhaseTypeClp;
import com.ext.portlet.model.ContestTeamMemberClp;
import com.ext.portlet.model.DiscussionCategoryClp;
import com.ext.portlet.model.DiscussionCategoryGroupClp;
import com.ext.portlet.model.DiscussionMessageClp;
import com.ext.portlet.model.DiscussionMessageFlagClp;
import com.ext.portlet.model.EmailListClp;
import com.ext.portlet.model.FocusAreaClp;
import com.ext.portlet.model.FocusAreaOntologyTermClp;
import com.ext.portlet.model.LandingPageClp;
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
import com.ext.portlet.model.Proposal2PhaseClp;
import com.ext.portlet.model.ProposalAttributeClp;
import com.ext.portlet.model.ProposalAttributeTypeClp;
import com.ext.portlet.model.ProposalClp;
import com.ext.portlet.model.ProposalContestPhaseAttributeClp;
import com.ext.portlet.model.ProposalContestPhaseAttributeTypeClp;
import com.ext.portlet.model.ProposalSupporterClp;
import com.ext.portlet.model.ProposalVersionClp;
import com.ext.portlet.model.ProposalVoteClp;

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

        if (oldModelClassName.equals(BalloonStatsEntryClp.class.getName())) {
            return translateInputBalloonStatsEntry(oldModel);
        }

        if (oldModelClassName.equals(ContestClp.class.getName())) {
            return translateInputContest(oldModel);
        }

        if (oldModelClassName.equals(ContestDebateClp.class.getName())) {
            return translateInputContestDebate(oldModel);
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

        if (oldModelClassName.equals(ProposalSupporterClp.class.getName())) {
            return translateInputProposalSupporter(oldModel);
        }

        if (oldModelClassName.equals(ProposalVersionClp.class.getName())) {
            return translateInputProposalVersion(oldModel);
        }

        if (oldModelClassName.equals(ProposalVoteClp.class.getName())) {
            return translateInputProposalVote(oldModel);
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

    public static Object translateInputBalloonStatsEntry(BaseModel<?> oldModel) {
        BalloonStatsEntryClp oldClpModel = (BalloonStatsEntryClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getBalloonStatsEntryRemoteModel();

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

<<<<<<< HEAD
    public static Object translateInputAnalyticsUserEvent(BaseModel<?> oldModel) {
        AnalyticsUserEventClp oldCplModel = (AnalyticsUserEventClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.AnalyticsUserEventImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getUserId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setIdString",
                        new Class[] { String.class });

                String value1 = oldCplModel.getIdString();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setCategory",
                        new Class[] { String.class });

                String value2 = oldCplModel.getCategory();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setAction",
                        new Class[] { String.class });

                String value3 = oldCplModel.getAction();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setLabel",
                        new Class[] { String.class });

                String value4 = oldCplModel.getLabel();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setValue",
                        new Class[] { Integer.TYPE });

                Integer value5 = new Integer(oldCplModel.getValue());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value6 = oldCplModel.getCreated();

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputBalloonStatsEntry(BaseModel<?> oldModel) {
        BalloonStatsEntryClp oldCplModel = (BalloonStatsEntryClp) oldModel;

        Thread currentThread = Thread.currentThread();
=======
    public static Object translateInputContestTeamMember(BaseModel<?> oldModel) {
        ContestTeamMemberClp oldClpModel = (ContestTeamMemberClp) oldModel;
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

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
                    "com.ext.portlet.model.impl.BalloonStatsEntryImpl")) {
            return translateOutputBalloonStatsEntry(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.ContestImpl")) {
            return translateOutputContest(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestDebateImpl")) {
            return translateOutputContestDebate(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestPhaseImpl")) {
            return translateOutputContestPhase(oldModel);
        }

<<<<<<< HEAD
                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value6 = oldCplModel.getCreateDate();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setDeleted",
                        new Class[] { Date.class });

                Date value7 = oldCplModel.getDeleted();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setThreadsCount",
                        new Class[] { Integer.TYPE });

                Integer value8 = new Integer(oldCplModel.getThreadsCount());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setLastActivityDate",
                        new Class[] { Date.class });

                Date value9 = oldCplModel.getLastActivityDate();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setLastActivityAuthorId",
                        new Class[] { Long.TYPE });

                Long value10 = new Long(oldCplModel.getLastActivityAuthorId());

                method10.invoke(newModel, value10);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputDiscussionCategoryGroup(
        BaseModel<?> oldModel) {
        DiscussionCategoryGroupClp oldCplModel = (DiscussionCategoryGroupClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.DiscussionCategoryGroupImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value1 = oldCplModel.getDescription();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUrl",
                        new Class[] { String.class });

                String value2 = oldCplModel.getUrl();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCommentsThread",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getCommentsThread());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setIsQuiet",
                        new Class[] { Boolean.TYPE });

                Boolean value4 = new Boolean(oldCplModel.getIsQuiet());

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputDiscussionMessage(BaseModel<?> oldModel) {
        DiscussionMessageClp oldCplModel = (DiscussionMessageClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.DiscussionMessageImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPk",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPk());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getMessageId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setSubject",
                        new Class[] { String.class });

                String value2 = oldCplModel.getSubject();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setBody",
                        new Class[] { String.class });

                String value3 = oldCplModel.getBody();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setThreadId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getThreadId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCategoryId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getCategoryId());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCategoryGroupId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getCategoryGroupId());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getAuthorId());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value8 = oldCplModel.getCreateDate();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value9 = new Long(oldCplModel.getVersion());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setDeleted",
                        new Class[] { Date.class });

                Date value10 = oldCplModel.getDeleted();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setResponsesCount",
                        new Class[] { Integer.TYPE });

                Integer value11 = new Integer(oldCplModel.getResponsesCount());

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setLastActivityDate",
                        new Class[] { Date.class });

                Date value12 = oldCplModel.getLastActivityDate();

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setLastActivityAuthorId",
                        new Class[] { Long.TYPE });

                Long value13 = new Long(oldCplModel.getLastActivityAuthorId());

                method13.invoke(newModel, value13);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputDiscussionMessageFlag(
        BaseModel<?> oldModel) {
        DiscussionMessageFlagClp oldCplModel = (DiscussionMessageFlagClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.DiscussionMessageFlagImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPk",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPk());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getMessageId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setFlagType",
                        new Class[] { String.class });

                String value2 = oldCplModel.getFlagType();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setData",
                        new Class[] { String.class });

                String value3 = oldCplModel.getData();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getCreated();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getUserId());

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputEmailList(BaseModel<?> oldModel) {
        EmailListClp oldCplModel = (EmailListClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.EmailListImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setEmail",
                        new Class[] { String.class });

                String value2 = oldCplModel.getEmail();

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputFocusArea(BaseModel<?> oldModel) {
        FocusAreaClp oldCplModel = (FocusAreaClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.FocusAreaImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setOrder",
                        new Class[] { Integer.TYPE });

                Integer value2 = new Integer(oldCplModel.getOrder());

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputFocusAreaOntologyTerm(
        BaseModel<?> oldModel) {
        FocusAreaOntologyTermClp oldCplModel = (FocusAreaOntologyTermClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.FocusAreaOntologyTermImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setFocusAreaId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getFocusAreaId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setOntologyTermId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getOntologyTermId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setOrder",
                        new Class[] { Integer.TYPE });

                Integer value2 = new Integer(oldCplModel.getOrder());

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputLandingPage(BaseModel<?> oldModel) {
        LandingPageClp oldCplModel = (LandingPageClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.LandingPageImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setBaseUrl",
                        new Class[] { String.class });

                String value1 = oldCplModel.getBaseUrl();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setTargetUrl",
                        new Class[] { String.class });

                String value2 = oldCplModel.getTargetUrl();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setUpdated",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getUpdated();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputMessage(BaseModel<?> oldModel) {
        MessageClp oldCplModel = (MessageClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.MessageImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getMessageId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setFromId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getFromId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setRepliesTo",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getRepliesTo());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getCreateDate();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setSubject",
                        new Class[] { String.class });

                String value4 = oldCplModel.getSubject();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setContent",
                        new Class[] { String.class });

                String value5 = oldCplModel.getContent();

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputMessageRecipientStatus(
        BaseModel<?> oldModel) {
        MessageRecipientStatusClp oldCplModel = (MessageRecipientStatusClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.MessageRecipientStatusImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setMessageRecipientId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getMessageRecipientId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getMessageId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getUserId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setOpened",
                        new Class[] { Boolean.TYPE });

                Boolean value3 = new Boolean(oldCplModel.getOpened());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setArchived",
                        new Class[] { Boolean.TYPE });

                Boolean value4 = new Boolean(oldCplModel.getArchived());

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputMessagingIgnoredRecipients(
        BaseModel<?> oldModel) {
        MessagingIgnoredRecipientsClp oldCplModel = (MessagingIgnoredRecipientsClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.MessagingIgnoredRecipientsImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setIgnoredRecipientId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getIgnoredRecipientId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setEmail",
                        new Class[] { String.class });

                String value1 = oldCplModel.getEmail();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getUserId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getCreateDate();

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputMessagingMessage(BaseModel<?> oldModel) {
        MessagingMessageClp oldCplModel = (MessagingMessageClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.MessagingMessageImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getMessageId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value2 = oldCplModel.getDescription();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setSubject",
                        new Class[] { String.class });

                String value3 = oldCplModel.getSubject();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setBody",
                        new Class[] { String.class });

                String value4 = oldCplModel.getBody();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setReplyTo",
                        new Class[] { String.class });

                String value5 = oldCplModel.getReplyTo();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setSendToAll",
                        new Class[] { Boolean.TYPE });

                Boolean value6 = new Boolean(oldCplModel.getSendToAll());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setConversionCount",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getConversionCount());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setRedirectURL",
                        new Class[] { String.class });

                String value8 = oldCplModel.getRedirectURL();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setCreatorId",
                        new Class[] { Long.TYPE });

                Long value9 = new Long(oldCplModel.getCreatorId());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value10 = oldCplModel.getCreateDate();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setModifiedDate",
                        new Class[] { Date.class });

                Date value11 = oldCplModel.getModifiedDate();

                method11.invoke(newModel, value11);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputMessagingMessageConversion(
        BaseModel<?> oldModel) {
        MessagingMessageConversionClp oldCplModel = (MessagingMessageConversionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.MessagingMessageConversionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setConversionId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getConversionId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setConversionTypeId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getConversionTypeId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getMessageId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setIpAddress",
                        new Class[] { String.class });

                String value3 = oldCplModel.getIpAddress();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setExtraData",
                        new Class[] { String.class });

                String value4 = oldCplModel.getExtraData();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setExtraData2",
                        new Class[] { String.class });

                String value5 = oldCplModel.getExtraData2();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value6 = oldCplModel.getCreateDate();

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputMessagingMessageConversionType(
        BaseModel<?> oldModel) {
        MessagingMessageConversionTypeClp oldCplModel = (MessagingMessageConversionTypeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.MessagingMessageConversionTypeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setTypeId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getTypeId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value2 = oldCplModel.getDescription();

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputMessagingMessageRecipient(
        BaseModel<?> oldModel) {
        MessagingMessageRecipientClp oldCplModel = (MessagingMessageRecipientClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.MessagingMessageRecipientImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setRecipientId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getRecipientId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getMessageId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getUserId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setEmailAddress",
                        new Class[] { String.class });

                String value3 = oldCplModel.getEmailAddress();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputMessagingRedirectLink(
        BaseModel<?> oldModel) {
        MessagingRedirectLinkClp oldCplModel = (MessagingRedirectLinkClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.MessagingRedirectLinkImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setRedirectId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getRedirectId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setLink",
                        new Class[] { String.class });

                String value1 = oldCplModel.getLink();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setMessageId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getMessageId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getCreateDate();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputMessagingUserPreferences(
        BaseModel<?> oldModel) {
        MessagingUserPreferencesClp oldCplModel = (MessagingUserPreferencesClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.MessagingUserPreferencesImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setMessagingPreferencesId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getMessagingPreferencesId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getUserId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setEmailOnSend",
                        new Class[] { Boolean.TYPE });

                Boolean value2 = new Boolean(oldCplModel.getEmailOnSend());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setEmailOnReceipt",
                        new Class[] { Boolean.TYPE });

                Boolean value3 = new Boolean(oldCplModel.getEmailOnReceipt());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setEmailOnActivity",
                        new Class[] { Boolean.TYPE });

                Boolean value4 = new Boolean(oldCplModel.getEmailOnActivity());

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputModelCategory(BaseModel<?> oldModel) {
        ModelCategoryClp oldCplModel = (ModelCategoryClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ModelCategoryImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelCategoryPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelCategoryPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelCategoryName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getModelCategoryName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setModelCategoryDescription",
                        new Class[] { String.class });

                String value2 = oldCplModel.getModelCategoryDescription();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setModelCategoryDisplayWeight",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getModelCategoryDisplayWeight());

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputModelDiscussion(BaseModel<?> oldModel) {
        ModelDiscussionClp oldCplModel = (ModelDiscussionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ModelDiscussionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelDiscussionId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelDiscussionId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setCategoryId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getCategoryId());

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputModelGlobalPreference(
        BaseModel<?> oldModel) {
        ModelGlobalPreferenceClp oldCplModel = (ModelGlobalPreferenceClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ModelGlobalPreferenceImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelGlobalPreferencePK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelGlobalPreferencePK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setVisible",
                        new Class[] { Boolean.TYPE });

                Boolean value2 = new Boolean(oldCplModel.getVisible());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setWeight",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getWeight());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setExpertEvaluationPageId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getExpertEvaluationPageId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setModelCategoryId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getModelCategoryId());

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputModelInputGroup(BaseModel<?> oldModel) {
        ModelInputGroupClp oldCplModel = (ModelInputGroupClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ModelInputGroupImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelInputGroupPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelInputGroupPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setNameAndDescriptionMetaDataId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getNameAndDescriptionMetaDataId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value3 = oldCplModel.getName();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value4 = oldCplModel.getDescription();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setDisplayItemOrder",
                        new Class[] { Integer.TYPE });

                Integer value5 = new Integer(oldCplModel.getDisplayItemOrder());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setGroupType",
                        new Class[] { String.class });

                String value6 = oldCplModel.getGroupType();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setParentGroupPK",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getParentGroupPK());

                method7.invoke(newModel, value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputModelInputItem(BaseModel<?> oldModel) {
        ModelInputItemClp oldCplModel = (ModelInputItemClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ModelInputItemImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelInputItemPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelInputItemPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setModelInputItemID",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getModelInputItemID());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setModelGroupId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getModelGroupId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDisplayItemOrder",
                        new Class[] { Integer.TYPE });

                Integer value4 = new Integer(oldCplModel.getDisplayItemOrder());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setType",
                        new Class[] { String.class });

                String value5 = oldCplModel.getType();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setProperties",
                        new Class[] { String.class });

                String value6 = oldCplModel.getProperties();

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputModelOutputChartOrder(
        BaseModel<?> oldModel) {
        ModelOutputChartOrderClp oldCplModel = (ModelOutputChartOrderClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ModelOutputChartOrderImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelOutputChartOrderPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelOutputChartOrderPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setModelOutputLabel",
                        new Class[] { String.class });

                String value2 = oldCplModel.getModelOutputLabel();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setModelOutputChartOrder",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getModelOutputChartOrder());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setModelIndexRangePolicy",
                        new Class[] { String.class });

                String value4 = oldCplModel.getModelIndexRangePolicy();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setModelIndexRangeMessage",
                        new Class[] { String.class });

                String value5 = oldCplModel.getModelIndexRangeMessage();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setModelIndexErrorPolicy",
                        new Class[] { String.class });

                String value6 = oldCplModel.getModelIndexErrorPolicy();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setModelIndexErrorMessage",
                        new Class[] { String.class });

                String value7 = oldCplModel.getModelIndexErrorMessage();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setModelChartIsVisible",
                        new Class[] { Boolean.TYPE });

                Boolean value8 = new Boolean(oldCplModel.getModelChartIsVisible());

                method8.invoke(newModel, value8);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputModelOutputItem(BaseModel<?> oldModel) {
        ModelOutputItemClp oldCplModel = (ModelOutputItemClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ModelOutputItemImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setModelOutputItemModifierPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getModelOutputItemModifierPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getModelId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setModelOutputItemId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getModelOutputItemId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setModelOutputItemOrder",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getModelOutputItemOrder());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setModelItemRangePolicy",
                        new Class[] { String.class });

                String value4 = oldCplModel.getModelItemRangePolicy();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setModelItemRangeMessage",
                        new Class[] { String.class });

                String value5 = oldCplModel.getModelItemRangeMessage();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setModelItemErrorPolicy",
                        new Class[] { String.class });

                String value6 = oldCplModel.getModelItemErrorPolicy();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setModelItemErrorMessage",
                        new Class[] { String.class });

                String value7 = oldCplModel.getModelItemErrorMessage();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setModelItemLabelFormat",
                        new Class[] { String.class });

                String value8 = oldCplModel.getModelItemLabelFormat();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setModelItemIsVisible",
                        new Class[] { Boolean.TYPE });

                Boolean value9 = new Boolean(oldCplModel.getModelItemIsVisible());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setItemType",
                        new Class[] { String.class });

                String value10 = oldCplModel.getItemType();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setRelatedOutputItem",
                        new Class[] { Long.TYPE });

                Long value11 = new Long(oldCplModel.getRelatedOutputItem());

                method11.invoke(newModel, value11);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputModelPosition(BaseModel<?> oldModel) {
        ModelPositionClp oldCplModel = (ModelPositionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ModelPositionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPositionId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPositionId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getModelId());

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputOntologySpace(BaseModel<?> oldModel) {
        OntologySpaceClp oldCplModel = (OntologySpaceClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.OntologySpaceImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value2 = oldCplModel.getDescription();

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputOntologyTerm(BaseModel<?> oldModel) {
        OntologyTermClp oldCplModel = (OntologyTermClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.OntologyTermImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setParentId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getParentId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setOntologySpaceId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getOntologySpaceId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value3 = oldCplModel.getName();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDescriptionUrl",
                        new Class[] { String.class });

                String value4 = oldCplModel.getDescriptionUrl();

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputOntologyTermEntity(BaseModel<?> oldModel) {
        OntologyTermEntityClp oldCplModel = (OntologyTermEntityClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.OntologyTermEntityImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setTermId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getTermId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setClassNameId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getClassNameId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setClassPK",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getClassPK());

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlan2Proposal(BaseModel<?> oldModel) {
        Plan2ProposalClp oldCplModel = (Plan2ProposalClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.Plan2ProposalImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setProposalId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getProposalId());

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanAttribute(BaseModel<?> oldModel) {
        PlanAttributeClp oldCplModel = (PlanAttributeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanAttributeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setAttributeId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getAttributeId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setAttributeName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getAttributeName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setAttributeValue",
                        new Class[] { String.class });

                String value3 = oldCplModel.getAttributeValue();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanAttributeFilter(
        BaseModel<?> oldModel) {
        PlanAttributeFilterClp oldCplModel = (PlanAttributeFilterClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanAttributeFilterImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanAttributeFilterId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanAttributeFilterId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setAttributeName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getAttributeName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanUserSettingsId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanUserSettingsId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setMax",
                        new Class[] { Double.TYPE });

                Double value3 = new Double(oldCplModel.getMax());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setMin",
                        new Class[] { Double.TYPE });

                Double value4 = new Double(oldCplModel.getMin());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setStringVal",
                        new Class[] { String.class });

                String value5 = oldCplModel.getStringVal();

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanColumnSettings(BaseModel<?> oldModel) {
        PlanColumnSettingsClp oldCplModel = (PlanColumnSettingsClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanColumnSettingsImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanColumnSettingsId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanColumnSettingsId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setColumnName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getColumnName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanUserSettingsId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanUserSettingsId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setVisible",
                        new Class[] { Boolean.TYPE });

                Boolean value3 = new Boolean(oldCplModel.getVisible());

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanDescription(BaseModel<?> oldModel) {
        PlanDescriptionClp oldCplModel = (PlanDescriptionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanDescriptionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value3 = oldCplModel.getDescription();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getVersion());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getPlanVersion());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value6 = oldCplModel.getCreated();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getUpdateAuthorId());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setImage",
                        new Class[] { Long.TYPE });

                Long value8 = new Long(oldCplModel.getImage());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setPitch",
                        new Class[] { String.class });

                String value9 = oldCplModel.getPitch();

                method9.invoke(newModel, value9);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanFan(BaseModel<?> oldModel) {
        PlanFanClp oldCplModel = (PlanFanClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanFanImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getUserId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getCreated();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDeleted",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getDeleted();

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanItem(BaseModel<?> oldModel) {
        PlanItemClp oldCplModel = (PlanItemClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanItemImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setState",
                        new Class[] { String.class });

                String value2 = oldCplModel.getState();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setUpdated",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getUpdated();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getUpdateAuthorId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setUpdateType",
                        new Class[] { String.class });

                String value5 = oldCplModel.getUpdateType();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getVersion());

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanItemGroup(BaseModel<?> oldModel) {
        PlanItemGroupClp oldCplModel = (PlanItemGroupClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanItemGroupImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setGroupId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getGroupId());

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanMeta(BaseModel<?> oldModel) {
        PlanMetaClp oldCplModel = (PlanMetaClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanMetaImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanTypeId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setPlanCreated",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getPlanCreated());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getAuthorId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setVotes",
                        new Class[] { Integer.TYPE });

                Integer value5 = new Integer(oldCplModel.getVotes());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setPlanGroupId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getPlanGroupId());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setOpen",
                        new Class[] { Boolean.TYPE });

                Boolean value7 = new Boolean(oldCplModel.getOpen());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setStatus",
                        new Class[] { String.class });

                String value8 = oldCplModel.getStatus();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setMbCategoryId",
                        new Class[] { Long.TYPE });

                Long value9 = new Long(oldCplModel.getMbCategoryId());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setCategoryGroupId",
                        new Class[] { Long.TYPE });

                Long value10 = new Long(oldCplModel.getCategoryGroupId());

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value11 = new Long(oldCplModel.getVersion());

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value12 = new Long(oldCplModel.getPlanVersion());

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value13 = oldCplModel.getCreated();

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value14 = new Long(oldCplModel.getUpdateAuthorId());

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value15 = new Long(oldCplModel.getModelId());

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setPromoted",
                        new Class[] { Boolean.TYPE });

                Boolean value16 = new Boolean(oldCplModel.getPromoted());

                method16.invoke(newModel, value16);

                Method method17 = newModelClass.getMethod("setPreviousContestPhase",
                        new Class[] { Long.TYPE });

                Long value17 = new Long(oldCplModel.getPreviousContestPhase());

                method17.invoke(newModel, value17);

                Method method18 = newModelClass.getMethod("setContestPhase",
                        new Class[] { Long.TYPE });

                Long value18 = new Long(oldCplModel.getContestPhase());

                method18.invoke(newModel, value18);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanModelRun(BaseModel<?> oldModel) {
        PlanModelRunClp oldCplModel = (PlanModelRunClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanModelRunImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setScenarioId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getScenarioId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getPlanVersion());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getVersion());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value5 = oldCplModel.getCreated();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getUpdateAuthorId());

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanPosition(BaseModel<?> oldModel) {
        PlanPositionClp oldCplModel = (PlanPositionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanPositionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPositionId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPositionId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getUserId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setUserName",
                        new Class[] { String.class });

                String value3 = oldCplModel.getUserName();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getCreateDate();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setModifiedDate",
                        new Class[] { Date.class });

                Date value5 = oldCplModel.getModifiedDate();

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanPositionItem(BaseModel<?> oldModel) {
        PlanPositionItemClp oldCplModel = (PlanPositionItemClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanPositionItemImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanPositionsId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanPositionsId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPositionId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPositionId());

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanPositions(BaseModel<?> oldModel) {
        PlanPositionsClp oldCplModel = (PlanPositionsClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanPositionsImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanVersion());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getVersion());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getCreated();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getUpdateAuthorId());

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanPropertyFilter(BaseModel<?> oldModel) {
        PlanPropertyFilterClp oldCplModel = (PlanPropertyFilterClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanPropertyFilterImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanPropertyFilterId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanPropertyFilterId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPropertyName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getPropertyName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanUserSettingsId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanUserSettingsId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setValue",
                        new Class[] { String.class });

                String value3 = oldCplModel.getValue();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanRelated(BaseModel<?> oldModel) {
        PlanRelatedClp oldCplModel = (PlanRelatedClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanRelatedImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setSectionId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getSectionId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setRelatedPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getRelatedPlanId());

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanSection(BaseModel<?> oldModel) {
        PlanSectionClp oldCplModel = (PlanSectionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanSectionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanSectionDefinitionId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanSectionDefinitionId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setContent",
                        new Class[] { String.class });

                String value3 = oldCplModel.getContent();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setNumericValue",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getNumericValue());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value5 = oldCplModel.getCreated();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getVersion());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getPlanVersion());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value8 = new Long(oldCplModel.getUpdateAuthorId());

                method8.invoke(newModel, value8);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanSectionDefinition(
        BaseModel<?> oldModel) {
        PlanSectionDefinitionClp oldCplModel = (PlanSectionDefinitionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanSectionDefinitionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setType",
                        new Class[] { String.class });

                String value1 = oldCplModel.getType();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setAdminTitle",
                        new Class[] { String.class });

                String value2 = oldCplModel.getAdminTitle();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setTitle",
                        new Class[] { String.class });

                String value3 = oldCplModel.getTitle();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDefaultText",
                        new Class[] { String.class });

                String value4 = oldCplModel.getDefaultText();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setHelpText",
                        new Class[] { String.class });

                String value5 = oldCplModel.getHelpText();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCharacterLimit",
                        new Class[] { Integer.TYPE });

                Integer value6 = new Integer(oldCplModel.getCharacterLimit());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setFocusAreaId",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getFocusAreaId());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setLocked",
                        new Class[] { Boolean.TYPE });

                Boolean value8 = new Boolean(oldCplModel.getLocked());

                method8.invoke(newModel, value8);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanSectionPlanMap(BaseModel<?> oldModel) {
        PlanSectionPlanMapClp oldCplModel = (PlanSectionPlanMapClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanSectionPlanMapImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setSectionId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getSectionId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setRelatedPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getRelatedPlanId());

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlansFilter(BaseModel<?> oldModel) {
        PlansFilterClp oldCplModel = (PlansFilterClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlansFilterImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getUserId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanTypeId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreator",
                        new Class[] { String.class });

                String value3 = oldCplModel.getCreator();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value4 = oldCplModel.getDescription();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCO2From",
                        new Class[] { Double.TYPE });

                Double value5 = new Double(oldCplModel.getCO2From());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setCO2To",
                        new Class[] { Double.TYPE });

                Double value6 = new Double(oldCplModel.getCO2To());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setVotesFrom",
                        new Class[] { Double.TYPE });

                Double value7 = new Double(oldCplModel.getVotesFrom());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setVotesTo",
                        new Class[] { Double.TYPE });

                Double value8 = new Double(oldCplModel.getVotesTo());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setDamageFrom",
                        new Class[] { Double.TYPE });

                Double value9 = new Double(oldCplModel.getDamageFrom());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setDamageTo",
                        new Class[] { Double.TYPE });

                Double value10 = new Double(oldCplModel.getDamageTo());

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setMitigationFrom",
                        new Class[] { Double.TYPE });

                Double value11 = new Double(oldCplModel.getMitigationFrom());

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setMitigationTo",
                        new Class[] { Double.TYPE });

                Double value12 = new Double(oldCplModel.getMitigationTo());

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setDateFrom",
                        new Class[] { Date.class });

                Date value13 = oldCplModel.getDateFrom();

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setDateTo",
                        new Class[] { Date.class });

                Date value14 = oldCplModel.getDateTo();

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setFilterPositionsAll",
                        new Class[] { Boolean.TYPE });

                Boolean value15 = new Boolean(oldCplModel.getFilterPositionsAll());

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setEnabled",
                        new Class[] { Boolean.TYPE });

                Boolean value16 = new Boolean(oldCplModel.getEnabled());

                method16.invoke(newModel, value16);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlansFilterPosition(
        BaseModel<?> oldModel) {
        PlansFilterPositionClp oldCplModel = (PlansFilterPositionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlansFilterPositionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getUserId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanTypeId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPositionId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPositionId());

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlansUserSettings(BaseModel<?> oldModel) {
        PlansUserSettingsClp oldCplModel = (PlansUserSettingsClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlansUserSettingsImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanUserSettingsId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanUserSettingsId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getUserId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanTypeId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setSortColumn",
                        new Class[] { String.class });

                String value3 = oldCplModel.getSortColumn();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setSortDirection",
                        new Class[] { String.class });

                String value4 = oldCplModel.getSortDirection();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setFilterEnabled",
                        new Class[] { Boolean.TYPE });

                Boolean value5 = new Boolean(oldCplModel.getFilterEnabled());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setFilterPositionsAll",
                        new Class[] { Boolean.TYPE });

                Boolean value6 = new Boolean(oldCplModel.getFilterPositionsAll());

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanTeamHistory(BaseModel<?> oldModel) {
        PlanTeamHistoryClp oldCplModel = (PlanTeamHistoryClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanTeamHistoryImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getUserId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setAction",
                        new Class[] { String.class });

                String value3 = oldCplModel.getAction();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setPayload",
                        new Class[] { String.class });

                String value4 = oldCplModel.getPayload();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value5 = oldCplModel.getCreated();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getUpdateAuthorId());

                method6.invoke(newModel, value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanTemplate(BaseModel<?> oldModel) {
        PlanTemplateClp oldCplModel = (PlanTemplateClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanTemplateImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getName();

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanTemplateSection(
        BaseModel<?> oldModel) {
        PlanTemplateSectionClp oldCplModel = (PlanTemplateSectionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanTemplateSectionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanTemplateId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanTemplateId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanSectionId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanSectionId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setWeight",
                        new Class[] { Integer.TYPE });

                Integer value2 = new Integer(oldCplModel.getWeight());

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanType(BaseModel<?> oldModel) {
        PlanTypeClp oldCplModel = (PlanTypeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanTypeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanTypeId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value2 = oldCplModel.getDescription();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setModelId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getModelId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setModelTypeName",
                        new Class[] { String.class });

                String value4 = oldCplModel.getModelTypeName();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setPublished",
                        new Class[] { Boolean.TYPE });

                Boolean value5 = new Boolean(oldCplModel.getPublished());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setPublishedCounterpartId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getPublishedCounterpartId());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setIsDefault",
                        new Class[] { Boolean.TYPE });

                Boolean value7 = new Boolean(oldCplModel.getIsDefault());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setDefaultModelId",
                        new Class[] { Long.TYPE });

                Long value8 = new Long(oldCplModel.getDefaultModelId());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setDefaultScenarioId",
                        new Class[] { Long.TYPE });

                Long value9 = new Long(oldCplModel.getDefaultScenarioId());

                method9.invoke(newModel, value9);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanTypeAttribute(BaseModel<?> oldModel) {
        PlanTypeAttributeClp oldCplModel = (PlanTypeAttributeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanTypeAttributeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanTypeAttributeId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanTypeAttributeId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanTypeId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setAttributeName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getAttributeName();

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanTypeColumn(BaseModel<?> oldModel) {
        PlanTypeColumnClp oldCplModel = (PlanTypeColumnClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanTypeColumnImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPlanTypeColumnId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPlanTypeColumnId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getPlanTypeId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setWeight",
                        new Class[] { Integer.TYPE });

                Integer value2 = new Integer(oldCplModel.getWeight());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setColumnName",
                        new Class[] { String.class });

                String value3 = oldCplModel.getColumnName();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setVisibleByDefault",
                        new Class[] { Boolean.TYPE });

                Boolean value4 = new Boolean(oldCplModel.getVisibleByDefault());

                method4.invoke(newModel, value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputPlanVote(BaseModel<?> oldModel) {
        PlanVoteClp oldCplModel = (PlanVoteClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.PlanVoteImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getUserId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getContestId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setPlanId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getPlanId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getCreateDate();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputProposal(BaseModel<?> oldModel) {
        ProposalClp oldCplModel = (ProposalClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ProposalImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setProposalId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getProposalId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value1 = oldCplModel.getCreateDate();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUpdatedDate",
                        new Class[] { Date.class });

                Date value2 = oldCplModel.getUpdatedDate();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCurrentVersion",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getCurrentVersion());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getAuthorId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setVisible",
                        new Class[] { Boolean.TYPE });

                Boolean value5 = new Boolean(oldCplModel.getVisible());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setDiscussionId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getDiscussionId());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setJudgeDiscussionId",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getJudgeDiscussionId());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setFellowDiscussionId",
                        new Class[] { Long.TYPE });

                Long value8 = new Long(oldCplModel.getFellowDiscussionId());

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setAdvisorDiscussionId",
                        new Class[] { Long.TYPE });

                Long value9 = new Long(oldCplModel.getAdvisorDiscussionId());

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setGroupId",
                        new Class[] { Long.TYPE });

                Long value10 = new Long(oldCplModel.getGroupId());

                method10.invoke(newModel, value10);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputProposal2Phase(BaseModel<?> oldModel) {
        Proposal2PhaseClp oldCplModel = (Proposal2PhaseClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.Proposal2PhaseImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setProposalId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getProposalId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestPhaseId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getContestPhaseId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setVersionFrom",
                        new Class[] { Integer.TYPE });

                Integer value2 = new Integer(oldCplModel.getVersionFrom());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setVersionTo",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getVersionTo());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setSortWeight",
                        new Class[] { Integer.TYPE });

                Integer value4 = new Integer(oldCplModel.getSortWeight());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setAutopromoteCandidate",
                        new Class[] { Boolean.TYPE });

                Boolean value5 = new Boolean(oldCplModel.getAutopromoteCandidate());

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputProposalAttribute(BaseModel<?> oldModel) {
        ProposalAttributeClp oldCplModel = (ProposalAttributeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ProposalAttributeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setProposalId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getProposalId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setVersion",
                        new Class[] { Integer.TYPE });

                Integer value2 = new Integer(oldCplModel.getVersion());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setVersionWhenCreated",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getVersionWhenCreated());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value4 = oldCplModel.getName();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setAdditionalId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getAdditionalId());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setNumericValue",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getNumericValue());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setStringValue",
                        new Class[] { String.class });

                String value7 = oldCplModel.getStringValue();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setRealValue",
                        new Class[] { Double.TYPE });

                Double value8 = new Double(oldCplModel.getRealValue());

                method8.invoke(newModel, value8);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputProposalAttributeType(
        BaseModel<?> oldModel) {
        ProposalAttributeTypeClp oldCplModel = (ProposalAttributeTypeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ProposalAttributeTypeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value0 = oldCplModel.getName();

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setVisibleInVersionHistory",
                        new Class[] { Boolean.TYPE });

                Boolean value1 = new Boolean(oldCplModel.getVisibleInVersionHistory());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setCopyOnPromote",
                        new Class[] { Boolean.TYPE });

                Boolean value2 = new Boolean(oldCplModel.getCopyOnPromote());

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputProposalContestPhaseAttribute(
        BaseModel<?> oldModel) {
        ProposalContestPhaseAttributeClp oldCplModel = (ProposalContestPhaseAttributeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ProposalContestPhaseAttributeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setProposalId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getProposalId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setContestPhaseId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getContestPhaseId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value3 = oldCplModel.getName();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setAdditionalId",
                        new Class[] { Long.TYPE });

                Long value4 = new Long(oldCplModel.getAdditionalId());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setNumericValue",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getNumericValue());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setStringValue",
                        new Class[] { String.class });

                String value6 = oldCplModel.getStringValue();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setRealValue",
                        new Class[] { Double.TYPE });

                Double value7 = new Double(oldCplModel.getRealValue());

                method7.invoke(newModel, value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputProposalContestPhaseAttributeType(
        BaseModel<?> oldModel) {
        ProposalContestPhaseAttributeTypeClp oldCplModel = (ProposalContestPhaseAttributeTypeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ProposalContestPhaseAttributeTypeImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value0 = oldCplModel.getName();

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCopyOnPromote",
                        new Class[] { Boolean.TYPE });

                Boolean value1 = new Boolean(oldCplModel.getCopyOnPromote());

                method1.invoke(newModel, value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputProposalSupporter(BaseModel<?> oldModel) {
        ProposalSupporterClp oldCplModel = (ProposalSupporterClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ProposalSupporterImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setProposalId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getProposalId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getUserId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value2 = oldCplModel.getCreateDate();

                method2.invoke(newModel, value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputProposalVersion(BaseModel<?> oldModel) {
        ProposalVersionClp oldCplModel = (ProposalVersionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ProposalVersionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setProposalId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getProposalId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setVersion",
                        new Class[] { Integer.TYPE });

                Integer value1 = new Integer(oldCplModel.getVersion());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getAuthorId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getCreateDate();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setUpdateType",
                        new Class[] { String.class });

                String value4 = oldCplModel.getUpdateType();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setUpdateAdditionalId",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getUpdateAdditionalId());

                method5.invoke(newModel, value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputProposalVote(BaseModel<?> oldModel) {
        ProposalVoteClp oldCplModel = (ProposalVoteClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ProposalVoteImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setProposalId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getProposalId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestPhaseId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getContestPhaseId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getUserId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value3 = oldCplModel.getCreateDate();

                method3.invoke(newModel, value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
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
                    "com.ext.portlet.model.impl.BalloonStatsEntryImpl")) {
            return translateOutputBalloonStatsEntry(oldModel);
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.ContestImpl")) {
            return translateOutputContest(oldModel);
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestDebateImpl")) {
            return translateOutputContestDebate(oldModel);
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

    public static Object translateOutputActivitySubscription(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ActivitySubscriptionClp newModel = new ActivitySubscriptionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPk");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPk(value0);

                Method method1 = oldModelClass.getMethod("getClassNameId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setClassNameId(value1);

                Method method2 = oldModelClass.getMethod("getClassPK");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setClassPK(value2);

                Method method3 = oldModelClass.getMethod("getType");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setType(value3);

                Method method4 = oldModelClass.getMethod(
                        "getAutomaticSubscriptionCounter");

                Integer value4 = (Integer) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setAutomaticSubscriptionCounter(value4);

                Method method5 = oldModelClass.getMethod("getExtraData");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setExtraData(value5);

                Method method6 = oldModelClass.getMethod("getReceiverId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setReceiverId(value6);

                Method method7 = oldModelClass.getMethod("getCreateDate");

                Date value7 = (Date) method7.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value7);

                Method method8 = oldModelClass.getMethod("getModifiedDate");

                Date value8 = (Date) method8.invoke(oldModel, (Object[]) null);

                newModel.setModifiedDate(value8);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputAnalyticsUserEvent(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                AnalyticsUserEventClp newModel = new AnalyticsUserEventClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getUserId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value0);

                Method method1 = oldModelClass.getMethod("getIdString");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setIdString(value1);

                Method method2 = oldModelClass.getMethod("getCategory");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setCategory(value2);

                Method method3 = oldModelClass.getMethod("getAction");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setAction(value3);

                Method method4 = oldModelClass.getMethod("getLabel");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setLabel(value4);

                Method method5 = oldModelClass.getMethod("getValue");

                Integer value5 = (Integer) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setValue(value5);

                Method method6 = oldModelClass.getMethod("getCreated");

                Date value6 = (Date) method6.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputBalloonStatsEntry(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                BalloonStatsEntryClp newModel = new BalloonStatsEntryClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getFirstContestId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setFirstContestId(value1);

                Method method2 = oldModelClass.getMethod("getSecondContestId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setSecondContestId(value2);

                Method method3 = oldModelClass.getMethod("getChoosenContest");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setChoosenContest(value3);

                Method method4 = oldModelClass.getMethod("getCookie");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setCookie(value4);

                Method method5 = oldModelClass.getMethod("getIp");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setIp(value5);

                Method method6 = oldModelClass.getMethod("getExtraData");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setExtraData(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContest(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestClp newModel = new ContestClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getContestPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setContestPK(value0);

                Method method1 = oldModelClass.getMethod("getContestName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestName(value1);

                Method method2 = oldModelClass.getMethod("getContestShortName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestShortName(value2);

                Method method3 = oldModelClass.getMethod(
                        "getContestDescription");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestDescription(value3);

                Method method4 = oldModelClass.getMethod(
                        "getContestModelDescription");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestModelDescription(value4);

                Method method5 = oldModelClass.getMethod(
                        "getContestPositionsDescription");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestPositionsDescription(value5);

                Method method6 = oldModelClass.getMethod(
                        "getDefaultPlanDescription");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setDefaultPlanDescription(value6);

                Method method7 = oldModelClass.getMethod("getPlanTypeId");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value7);

                Method method8 = oldModelClass.getMethod("getCreated");

                Date value8 = (Date) method8.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value8);

                Method method9 = oldModelClass.getMethod("getUpdated");

                Date value9 = (Date) method9.invoke(oldModel, (Object[]) null);

                newModel.setUpdated(value9);

                Method method10 = oldModelClass.getMethod("getAuthorId");

                Long value10 = (Long) method10.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value10);

                Method method11 = oldModelClass.getMethod("getContestActive");

                Boolean value11 = (Boolean) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestActive(value11);

                Method method12 = oldModelClass.getMethod("getPlanTemplateId");

                Long value12 = (Long) method12.invoke(oldModel, (Object[]) null);

                newModel.setPlanTemplateId(value12);

                Method method13 = oldModelClass.getMethod("getFocusAreaId");

                Long value13 = (Long) method13.invoke(oldModel, (Object[]) null);

                newModel.setFocusAreaId(value13);

                Method method14 = oldModelClass.getMethod("getContestLogoId");

                Long value14 = (Long) method14.invoke(oldModel, (Object[]) null);

                newModel.setContestLogoId(value14);

                Method method15 = oldModelClass.getMethod("getFeatured");

                Boolean value15 = (Boolean) method15.invoke(oldModel,
                        (Object[]) null);

                newModel.setFeatured(value15);

                Method method16 = oldModelClass.getMethod(
                        "getPlansOpenByDefault");

                Boolean value16 = (Boolean) method16.invoke(oldModel,
                        (Object[]) null);

                newModel.setPlansOpenByDefault(value16);

                Method method17 = oldModelClass.getMethod("getSponsorLogoId");

                Long value17 = (Long) method17.invoke(oldModel, (Object[]) null);

                newModel.setSponsorLogoId(value17);

                Method method18 = oldModelClass.getMethod("getSponsorText");

                String value18 = (String) method18.invoke(oldModel,
                        (Object[]) null);

                newModel.setSponsorText(value18);

                Method method19 = oldModelClass.getMethod("getFlag");

                Integer value19 = (Integer) method19.invoke(oldModel,
                        (Object[]) null);

                newModel.setFlag(value19);

                Method method20 = oldModelClass.getMethod("getFlagText");

                String value20 = (String) method20.invoke(oldModel,
                        (Object[]) null);

                newModel.setFlagText(value20);

                Method method21 = oldModelClass.getMethod("getFlagTooltip");

                String value21 = (String) method21.invoke(oldModel,
                        (Object[]) null);

                newModel.setFlagTooltip(value21);

                Method method22 = oldModelClass.getMethod("getGroupId");

                Long value22 = (Long) method22.invoke(oldModel, (Object[]) null);

                newModel.setGroupId(value22);

                Method method23 = oldModelClass.getMethod(
                        "getDiscussionGroupId");

                Long value23 = (Long) method23.invoke(oldModel, (Object[]) null);

                newModel.setDiscussionGroupId(value23);

                Method method24 = oldModelClass.getMethod("getWeight");

                Integer value24 = (Integer) method24.invoke(oldModel,
                        (Object[]) null);

                newModel.setWeight(value24);

                Method method25 = oldModelClass.getMethod("getResourcesUrl");

                String value25 = (String) method25.invoke(oldModel,
                        (Object[]) null);

                newModel.setResourcesUrl(value25);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContestDebate(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestDebateClp newModel = new ContestDebateClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getDebateId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setDebateId(value1);

                Method method2 = oldModelClass.getMethod("getContestPK");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setContestPK(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContestPhase(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestPhaseClp newModel = new ContestPhaseClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getContestPhasePK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setContestPhasePK(value0);

                Method method1 = oldModelClass.getMethod("getContestPK");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setContestPK(value1);

                Method method2 = oldModelClass.getMethod("getContestPhaseType");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setContestPhaseType(value2);

                Method method3 = oldModelClass.getMethod(
                        "getContestPhaseAutopromote");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestPhaseAutopromote(value3);

                Method method4 = oldModelClass.getMethod(
                        "getContestPhaseDescriptionOverride");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setContestPhaseDescriptionOverride(value4);

                Method method5 = oldModelClass.getMethod(
                        "getPhaseActiveOverride");

                Boolean value5 = (Boolean) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setPhaseActiveOverride(value5);

                Method method6 = oldModelClass.getMethod(
                        "getPhaseInactiveOverride");

                Boolean value6 = (Boolean) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setPhaseInactiveOverride(value6);

                Method method7 = oldModelClass.getMethod("getPhaseStartDate");

                Date value7 = (Date) method7.invoke(oldModel, (Object[]) null);

                newModel.setPhaseStartDate(value7);

                Method method8 = oldModelClass.getMethod("getPhaseEndDate");

                Date value8 = (Date) method8.invoke(oldModel, (Object[]) null);

                newModel.setPhaseEndDate(value8);

                Method method9 = oldModelClass.getMethod("getNextStatus");

                String value9 = (String) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setNextStatus(value9);

                Method method10 = oldModelClass.getMethod("getCreated");

                Date value10 = (Date) method10.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value10);

                Method method11 = oldModelClass.getMethod("getUpdated");

                Date value11 = (Date) method11.invoke(oldModel, (Object[]) null);

                newModel.setUpdated(value11);

                Method method12 = oldModelClass.getMethod("getAuthorId");

                Long value12 = (Long) method12.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value12);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContestPhaseColumn(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestPhaseColumnClp newModel = new ContestPhaseColumnClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getContestPhasePK");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setContestPhasePK(value1);

                Method method2 = oldModelClass.getMethod("getColumnName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setColumnName(value2);

                Method method3 = oldModelClass.getMethod("getColumnWeight");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setColumnWeight(value3);

                Method method4 = oldModelClass.getMethod("getDefaultSort");

                Boolean value4 = (Boolean) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setDefaultSort(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContestPhaseRibbonType(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestPhaseRibbonTypeClp newModel = new ContestPhaseRibbonTypeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getRibbon");

                Integer value1 = (Integer) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setRibbon(value1);

                Method method2 = oldModelClass.getMethod("getHoverText");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setHoverText(value2);

                Method method3 = oldModelClass.getMethod("getDescription");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value3);

                Method method4 = oldModelClass.getMethod("getCopyOnPromote");

                Boolean value4 = (Boolean) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setCopyOnPromote(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContestPhaseType(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestPhaseTypeClp newModel = new ContestPhaseTypeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                Method method2 = oldModelClass.getMethod("getDescription");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value2);

                Method method3 = oldModelClass.getMethod("getStatus");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setStatus(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputContestTeamMember(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ContestTeamMemberClp newModel = new ContestTeamMemberClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getContestId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setContestId(value1);

                Method method2 = oldModelClass.getMethod("getUserId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value2);

                Method method3 = oldModelClass.getMethod("getRole");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setRole(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputDiscussionCategory(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                DiscussionCategoryClp newModel = new DiscussionCategoryClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPk");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPk(value0);

                Method method1 = oldModelClass.getMethod("getCategoryId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setCategoryId(value1);

                Method method2 = oldModelClass.getMethod("getCategoryGroupId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setCategoryGroupId(value2);

                Method method3 = oldModelClass.getMethod("getAuthorId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value3);

                Method method4 = oldModelClass.getMethod("getName");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value4);

                Method method5 = oldModelClass.getMethod("getDescription");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value5);

                Method method6 = oldModelClass.getMethod("getCreateDate");

                Date value6 = (Date) method6.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value6);

                Method method7 = oldModelClass.getMethod("getDeleted");

                Date value7 = (Date) method7.invoke(oldModel, (Object[]) null);

                newModel.setDeleted(value7);

                Method method8 = oldModelClass.getMethod("getThreadsCount");

                Integer value8 = (Integer) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setThreadsCount(value8);

                Method method9 = oldModelClass.getMethod("getLastActivityDate");

                Date value9 = (Date) method9.invoke(oldModel, (Object[]) null);

                newModel.setLastActivityDate(value9);

                Method method10 = oldModelClass.getMethod(
                        "getLastActivityAuthorId");

                Long value10 = (Long) method10.invoke(oldModel, (Object[]) null);

                newModel.setLastActivityAuthorId(value10);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputDiscussionCategoryGroup(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                DiscussionCategoryGroupClp newModel = new DiscussionCategoryGroupClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getDescription");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value1);

                Method method2 = oldModelClass.getMethod("getUrl");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setUrl(value2);

                Method method3 = oldModelClass.getMethod("getCommentsThread");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setCommentsThread(value3);

                Method method4 = oldModelClass.getMethod("getIsQuiet");

                Boolean value4 = (Boolean) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setIsQuiet(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputDiscussionMessage(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                DiscussionMessageClp newModel = new DiscussionMessageClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPk");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPk(value0);

                Method method1 = oldModelClass.getMethod("getMessageId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value1);

                Method method2 = oldModelClass.getMethod("getSubject");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setSubject(value2);

                Method method3 = oldModelClass.getMethod("getBody");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setBody(value3);

                Method method4 = oldModelClass.getMethod("getThreadId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setThreadId(value4);

                Method method5 = oldModelClass.getMethod("getCategoryId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setCategoryId(value5);

                Method method6 = oldModelClass.getMethod("getCategoryGroupId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setCategoryGroupId(value6);

                Method method7 = oldModelClass.getMethod("getAuthorId");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value7);

                Method method8 = oldModelClass.getMethod("getCreateDate");

                Date value8 = (Date) method8.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value8);

                Method method9 = oldModelClass.getMethod("getVersion");

                Long value9 = (Long) method9.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value9);

                Method method10 = oldModelClass.getMethod("getDeleted");

                Date value10 = (Date) method10.invoke(oldModel, (Object[]) null);

                newModel.setDeleted(value10);

                Method method11 = oldModelClass.getMethod("getResponsesCount");

                Integer value11 = (Integer) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setResponsesCount(value11);

                Method method12 = oldModelClass.getMethod("getLastActivityDate");

                Date value12 = (Date) method12.invoke(oldModel, (Object[]) null);

                newModel.setLastActivityDate(value12);

                Method method13 = oldModelClass.getMethod(
                        "getLastActivityAuthorId");

                Long value13 = (Long) method13.invoke(oldModel, (Object[]) null);

                newModel.setLastActivityAuthorId(value13);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputDiscussionMessageFlag(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                DiscussionMessageFlagClp newModel = new DiscussionMessageFlagClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPk");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPk(value0);

                Method method1 = oldModelClass.getMethod("getMessageId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value1);

                Method method2 = oldModelClass.getMethod("getFlagType");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setFlagType(value2);

                Method method3 = oldModelClass.getMethod("getData");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setData(value3);

                Method method4 = oldModelClass.getMethod("getCreated");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value4);

                Method method5 = oldModelClass.getMethod("getUserId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputEmailList(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                EmailListClp newModel = new EmailListClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                Method method2 = oldModelClass.getMethod("getEmail");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setEmail(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputFocusArea(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                FocusAreaClp newModel = new FocusAreaClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                Method method2 = oldModelClass.getMethod("getOrder");

                Integer value2 = (Integer) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setOrder(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputFocusAreaOntologyTerm(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                FocusAreaOntologyTermClp newModel = new FocusAreaOntologyTermClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getFocusAreaId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setFocusAreaId(value0);

                Method method1 = oldModelClass.getMethod("getOntologyTermId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setOntologyTermId(value1);

                Method method2 = oldModelClass.getMethod("getOrder");

                Integer value2 = (Integer) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setOrder(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputLandingPage(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                LandingPageClp newModel = new LandingPageClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getBaseUrl");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setBaseUrl(value1);

                Method method2 = oldModelClass.getMethod("getTargetUrl");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setTargetUrl(value2);

                Method method3 = oldModelClass.getMethod("getUpdated");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setUpdated(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessage(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessageClp newModel = new MessageClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getMessageId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value0);

                Method method1 = oldModelClass.getMethod("getFromId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setFromId(value1);

                Method method2 = oldModelClass.getMethod("getRepliesTo");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setRepliesTo(value2);

                Method method3 = oldModelClass.getMethod("getCreateDate");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value3);

                Method method4 = oldModelClass.getMethod("getSubject");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setSubject(value4);

                Method method5 = oldModelClass.getMethod("getContent");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setContent(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessageRecipientStatus(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessageRecipientStatusClp newModel = new MessageRecipientStatusClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getMessageRecipientId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setMessageRecipientId(value0);

                Method method1 = oldModelClass.getMethod("getMessageId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value1);

                Method method2 = oldModelClass.getMethod("getUserId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value2);

                Method method3 = oldModelClass.getMethod("getOpened");

                Boolean value3 = (Boolean) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setOpened(value3);

                Method method4 = oldModelClass.getMethod("getArchived");

                Boolean value4 = (Boolean) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setArchived(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessagingIgnoredRecipients(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessagingIgnoredRecipientsClp newModel = new MessagingIgnoredRecipientsClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getIgnoredRecipientId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setIgnoredRecipientId(value0);

                Method method1 = oldModelClass.getMethod("getEmail");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setEmail(value1);

                Method method2 = oldModelClass.getMethod("getName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value2);

                Method method3 = oldModelClass.getMethod("getUserId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value3);

                Method method4 = oldModelClass.getMethod("getCreateDate");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessagingMessage(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessagingMessageClp newModel = new MessagingMessageClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getMessageId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                Method method2 = oldModelClass.getMethod("getDescription");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value2);

                Method method3 = oldModelClass.getMethod("getSubject");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setSubject(value3);

                Method method4 = oldModelClass.getMethod("getBody");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setBody(value4);

                Method method5 = oldModelClass.getMethod("getReplyTo");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setReplyTo(value5);

                Method method6 = oldModelClass.getMethod("getSendToAll");

                Boolean value6 = (Boolean) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setSendToAll(value6);

                Method method7 = oldModelClass.getMethod("getConversionCount");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setConversionCount(value7);

                Method method8 = oldModelClass.getMethod("getRedirectURL");

                String value8 = (String) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setRedirectURL(value8);

                Method method9 = oldModelClass.getMethod("getCreatorId");

                Long value9 = (Long) method9.invoke(oldModel, (Object[]) null);

                newModel.setCreatorId(value9);

                Method method10 = oldModelClass.getMethod("getCreateDate");

                Date value10 = (Date) method10.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value10);

                Method method11 = oldModelClass.getMethod("getModifiedDate");

                Date value11 = (Date) method11.invoke(oldModel, (Object[]) null);

                newModel.setModifiedDate(value11);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessagingMessageConversion(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessagingMessageConversionClp newModel = new MessagingMessageConversionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getConversionId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setConversionId(value0);

                Method method1 = oldModelClass.getMethod("getConversionTypeId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setConversionTypeId(value1);

                Method method2 = oldModelClass.getMethod("getMessageId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value2);

                Method method3 = oldModelClass.getMethod("getIpAddress");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setIpAddress(value3);

                Method method4 = oldModelClass.getMethod("getExtraData");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setExtraData(value4);

                Method method5 = oldModelClass.getMethod("getExtraData2");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setExtraData2(value5);

                Method method6 = oldModelClass.getMethod("getCreateDate");

                Date value6 = (Date) method6.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessagingMessageConversionType(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessagingMessageConversionTypeClp newModel = new MessagingMessageConversionTypeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getTypeId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setTypeId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                Method method2 = oldModelClass.getMethod("getDescription");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessagingMessageRecipient(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessagingMessageRecipientClp newModel = new MessagingMessageRecipientClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getRecipientId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setRecipientId(value0);

                Method method1 = oldModelClass.getMethod("getMessageId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value1);

                Method method2 = oldModelClass.getMethod("getUserId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value2);

                Method method3 = oldModelClass.getMethod("getEmailAddress");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setEmailAddress(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessagingRedirectLink(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessagingRedirectLinkClp newModel = new MessagingRedirectLinkClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getRedirectId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setRedirectId(value0);

                Method method1 = oldModelClass.getMethod("getLink");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setLink(value1);

                Method method2 = oldModelClass.getMethod("getMessageId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setMessageId(value2);

                Method method3 = oldModelClass.getMethod("getCreateDate");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputMessagingUserPreferences(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                MessagingUserPreferencesClp newModel = new MessagingUserPreferencesClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getMessagingPreferencesId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setMessagingPreferencesId(value0);

                Method method1 = oldModelClass.getMethod("getUserId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value1);

                Method method2 = oldModelClass.getMethod("getEmailOnSend");

                Boolean value2 = (Boolean) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setEmailOnSend(value2);

                Method method3 = oldModelClass.getMethod("getEmailOnReceipt");

                Boolean value3 = (Boolean) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setEmailOnReceipt(value3);

                Method method4 = oldModelClass.getMethod("getEmailOnActivity");

                Boolean value4 = (Boolean) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setEmailOnActivity(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelCategory(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelCategoryClp newModel = new ModelCategoryClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getModelCategoryPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelCategoryPK(value0);

                Method method1 = oldModelClass.getMethod("getModelCategoryName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelCategoryName(value1);

                Method method2 = oldModelClass.getMethod(
                        "getModelCategoryDescription");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelCategoryDescription(value2);

                Method method3 = oldModelClass.getMethod(
                        "getModelCategoryDisplayWeight");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelCategoryDisplayWeight(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelDiscussion(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelDiscussionClp newModel = new ModelDiscussionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getModelDiscussionId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelDiscussionId(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod("getCategoryId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setCategoryId(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelGlobalPreference(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelGlobalPreferenceClp newModel = new ModelGlobalPreferenceClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getModelGlobalPreferencePK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelGlobalPreferencePK(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod("getVisible");

                Boolean value2 = (Boolean) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setVisible(value2);

                Method method3 = oldModelClass.getMethod("getWeight");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setWeight(value3);

                Method method4 = oldModelClass.getMethod(
                        "getExpertEvaluationPageId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setExpertEvaluationPageId(value4);

                Method method5 = oldModelClass.getMethod("getModelCategoryId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setModelCategoryId(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelInputGroup(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelInputGroupClp newModel = new ModelInputGroupClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getModelInputGroupPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelInputGroupPK(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod(
                        "getNameAndDescriptionMetaDataId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setNameAndDescriptionMetaDataId(value2);

                Method method3 = oldModelClass.getMethod("getName");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value3);

                Method method4 = oldModelClass.getMethod("getDescription");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value4);

                Method method5 = oldModelClass.getMethod("getDisplayItemOrder");

                Integer value5 = (Integer) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setDisplayItemOrder(value5);

                Method method6 = oldModelClass.getMethod("getGroupType");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setGroupType(value6);

                Method method7 = oldModelClass.getMethod("getParentGroupPK");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setParentGroupPK(value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelInputItem(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelInputItemClp newModel = new ModelInputItemClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getModelInputItemPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelInputItemPK(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod("getModelInputItemID");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setModelInputItemID(value2);

                Method method3 = oldModelClass.getMethod("getModelGroupId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setModelGroupId(value3);

                Method method4 = oldModelClass.getMethod("getDisplayItemOrder");

                Integer value4 = (Integer) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setDisplayItemOrder(value4);

                Method method5 = oldModelClass.getMethod("getType");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setType(value5);

                Method method6 = oldModelClass.getMethod("getProperties");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setProperties(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelOutputChartOrder(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelOutputChartOrderClp newModel = new ModelOutputChartOrderClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getModelOutputChartOrderPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelOutputChartOrderPK(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod("getModelOutputLabel");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelOutputLabel(value2);

                Method method3 = oldModelClass.getMethod(
                        "getModelOutputChartOrder");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelOutputChartOrder(value3);

                Method method4 = oldModelClass.getMethod(
                        "getModelIndexRangePolicy");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelIndexRangePolicy(value4);

                Method method5 = oldModelClass.getMethod(
                        "getModelIndexRangeMessage");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelIndexRangeMessage(value5);

                Method method6 = oldModelClass.getMethod(
                        "getModelIndexErrorPolicy");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelIndexErrorPolicy(value6);

                Method method7 = oldModelClass.getMethod(
                        "getModelIndexErrorMessage");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelIndexErrorMessage(value7);

                Method method8 = oldModelClass.getMethod(
                        "getModelChartIsVisible");

                Boolean value8 = (Boolean) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelChartIsVisible(value8);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelOutputItem(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelOutputItemClp newModel = new ModelOutputItemClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getModelOutputItemModifierPK");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setModelOutputItemModifierPK(value0);

                Method method1 = oldModelClass.getMethod("getModelId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value1);

                Method method2 = oldModelClass.getMethod("getModelOutputItemId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setModelOutputItemId(value2);

                Method method3 = oldModelClass.getMethod(
                        "getModelOutputItemOrder");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelOutputItemOrder(value3);

                Method method4 = oldModelClass.getMethod(
                        "getModelItemRangePolicy");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemRangePolicy(value4);

                Method method5 = oldModelClass.getMethod(
                        "getModelItemRangeMessage");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemRangeMessage(value5);

                Method method6 = oldModelClass.getMethod(
                        "getModelItemErrorPolicy");

                String value6 = (String) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemErrorPolicy(value6);

                Method method7 = oldModelClass.getMethod(
                        "getModelItemErrorMessage");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemErrorMessage(value7);

                Method method8 = oldModelClass.getMethod(
                        "getModelItemLabelFormat");

                String value8 = (String) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemLabelFormat(value8);

                Method method9 = oldModelClass.getMethod(
                        "getModelItemIsVisible");

                Boolean value9 = (Boolean) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelItemIsVisible(value9);

                Method method10 = oldModelClass.getMethod("getItemType");

                String value10 = (String) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setItemType(value10);

                Method method11 = oldModelClass.getMethod(
                        "getRelatedOutputItem");

                Long value11 = (Long) method11.invoke(oldModel, (Object[]) null);

                newModel.setRelatedOutputItem(value11);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputModelPosition(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ModelPositionClp newModel = new ModelPositionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPositionId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPositionId(value1);

                Method method2 = oldModelClass.getMethod("getModelId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputOntologySpace(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                OntologySpaceClp newModel = new OntologySpaceClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                Method method2 = oldModelClass.getMethod("getDescription");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputOntologyTerm(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                OntologyTermClp newModel = new OntologyTermClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getParentId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setParentId(value1);

                Method method2 = oldModelClass.getMethod("getOntologySpaceId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setOntologySpaceId(value2);

                Method method3 = oldModelClass.getMethod("getName");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value3);

                Method method4 = oldModelClass.getMethod("getDescriptionUrl");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescriptionUrl(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputOntologyTermEntity(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                OntologyTermEntityClp newModel = new OntologyTermEntityClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getTermId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setTermId(value1);

                Method method2 = oldModelClass.getMethod("getClassNameId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setClassNameId(value2);

                Method method3 = oldModelClass.getMethod("getClassPK");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setClassPK(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlan2Proposal(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Plan2ProposalClp newModel = new Plan2ProposalClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value0);

                Method method1 = oldModelClass.getMethod("getProposalId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setProposalId(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanAttribute(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanAttributeClp newModel = new PlanAttributeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getAttributeId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setAttributeId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getAttributeName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setAttributeName(value2);

                Method method3 = oldModelClass.getMethod("getAttributeValue");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setAttributeValue(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanAttributeFilter(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanAttributeFilterClp newModel = new PlanAttributeFilterClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getPlanAttributeFilterId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanAttributeFilterId(value0);

                Method method1 = oldModelClass.getMethod("getAttributeName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setAttributeName(value1);

                Method method2 = oldModelClass.getMethod(
                        "getPlanUserSettingsId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanUserSettingsId(value2);

                Method method3 = oldModelClass.getMethod("getMax");

                Double value3 = (Double) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setMax(value3);

                Method method4 = oldModelClass.getMethod("getMin");

                Double value4 = (Double) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setMin(value4);

                Method method5 = oldModelClass.getMethod("getStringVal");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setStringVal(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanColumnSettings(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanColumnSettingsClp newModel = new PlanColumnSettingsClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getPlanColumnSettingsId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanColumnSettingsId(value0);

                Method method1 = oldModelClass.getMethod("getColumnName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setColumnName(value1);

                Method method2 = oldModelClass.getMethod(
                        "getPlanUserSettingsId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanUserSettingsId(value2);

                Method method3 = oldModelClass.getMethod("getVisible");

                Boolean value3 = (Boolean) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setVisible(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanDescription(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanDescriptionClp newModel = new PlanDescriptionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value2);

                Method method3 = oldModelClass.getMethod("getDescription");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value3);

                Method method4 = oldModelClass.getMethod("getVersion");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value4);

                Method method5 = oldModelClass.getMethod("getPlanVersion");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setPlanVersion(value5);

                Method method6 = oldModelClass.getMethod("getCreated");

                Date value6 = (Date) method6.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value6);

                Method method7 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value7);

                Method method8 = oldModelClass.getMethod("getImage");

                Long value8 = (Long) method8.invoke(oldModel, (Object[]) null);

                newModel.setImage(value8);

                Method method9 = oldModelClass.getMethod("getPitch");

                String value9 = (String) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setPitch(value9);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanFan(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanFanClp newModel = new PlanFanClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getUserId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value1);

                Method method2 = oldModelClass.getMethod("getPlanId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value2);

                Method method3 = oldModelClass.getMethod("getCreated");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value3);

                Method method4 = oldModelClass.getMethod("getDeleted");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setDeleted(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanItem(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanItemClp newModel = new PlanItemClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getState");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setState(value2);

                Method method3 = oldModelClass.getMethod("getUpdated");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setUpdated(value3);

                Method method4 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value4);

                Method method5 = oldModelClass.getMethod("getUpdateType");

                String value5 = (String) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setUpdateType(value5);

                Method method6 = oldModelClass.getMethod("getVersion");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanItemGroup(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanItemGroupClp newModel = new PlanItemGroupClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value0);

                Method method1 = oldModelClass.getMethod("getGroupId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setGroupId(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanMeta(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanMetaClp newModel = new PlanMetaClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getPlanTypeId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value2);

                Method method3 = oldModelClass.getMethod("getPlanCreated");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setPlanCreated(value3);

                Method method4 = oldModelClass.getMethod("getAuthorId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value4);

                Method method5 = oldModelClass.getMethod("getVotes");

                Integer value5 = (Integer) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setVotes(value5);

                Method method6 = oldModelClass.getMethod("getPlanGroupId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setPlanGroupId(value6);

                Method method7 = oldModelClass.getMethod("getOpen");

                Boolean value7 = (Boolean) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setOpen(value7);

                Method method8 = oldModelClass.getMethod("getStatus");

                String value8 = (String) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setStatus(value8);

                Method method9 = oldModelClass.getMethod("getMbCategoryId");

                Long value9 = (Long) method9.invoke(oldModel, (Object[]) null);

                newModel.setMbCategoryId(value9);

                Method method10 = oldModelClass.getMethod("getCategoryGroupId");

                Long value10 = (Long) method10.invoke(oldModel, (Object[]) null);

                newModel.setCategoryGroupId(value10);

                Method method11 = oldModelClass.getMethod("getVersion");

                Long value11 = (Long) method11.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value11);

                Method method12 = oldModelClass.getMethod("getPlanVersion");

                Long value12 = (Long) method12.invoke(oldModel, (Object[]) null);

                newModel.setPlanVersion(value12);

                Method method13 = oldModelClass.getMethod("getCreated");

                Date value13 = (Date) method13.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value13);

                Method method14 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value14 = (Long) method14.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value14);

                Method method15 = oldModelClass.getMethod("getModelId");

                Long value15 = (Long) method15.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value15);

                Method method16 = oldModelClass.getMethod("getPromoted");

                Boolean value16 = (Boolean) method16.invoke(oldModel,
                        (Object[]) null);

                newModel.setPromoted(value16);

                Method method17 = oldModelClass.getMethod(
                        "getPreviousContestPhase");

                Long value17 = (Long) method17.invoke(oldModel, (Object[]) null);

                newModel.setPreviousContestPhase(value17);

                Method method18 = oldModelClass.getMethod("getContestPhase");

                Long value18 = (Long) method18.invoke(oldModel, (Object[]) null);

                newModel.setContestPhase(value18);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanModelRun(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanModelRunClp newModel = new PlanModelRunClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getScenarioId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setScenarioId(value2);

                Method method3 = oldModelClass.getMethod("getPlanVersion");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setPlanVersion(value3);

                Method method4 = oldModelClass.getMethod("getVersion");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value4);

                Method method5 = oldModelClass.getMethod("getCreated");

                Date value5 = (Date) method5.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value5);

                Method method6 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanPosition(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanPositionClp newModel = new PlanPositionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value0);

                Method method1 = oldModelClass.getMethod("getPositionId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPositionId(value1);

                Method method2 = oldModelClass.getMethod("getUserId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value2);

                Method method3 = oldModelClass.getMethod("getUserName");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setUserName(value3);

                Method method4 = oldModelClass.getMethod("getCreateDate");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value4);

                Method method5 = oldModelClass.getMethod("getModifiedDate");

                Date value5 = (Date) method5.invoke(oldModel, (Object[]) null);

                newModel.setModifiedDate(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanPositionItem(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanPositionItemClp newModel = new PlanPositionItemClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanPositionsId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanPositionsId(value0);

                Method method1 = oldModelClass.getMethod("getPositionId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPositionId(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanPositions(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanPositionsClp newModel = new PlanPositionsClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getPlanVersion");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanVersion(value2);

                Method method3 = oldModelClass.getMethod("getVersion");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value3);

                Method method4 = oldModelClass.getMethod("getCreated");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value4);

                Method method5 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanPropertyFilter(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanPropertyFilterClp newModel = new PlanPropertyFilterClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getPlanPropertyFilterId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanPropertyFilterId(value0);

                Method method1 = oldModelClass.getMethod("getPropertyName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setPropertyName(value1);

                Method method2 = oldModelClass.getMethod(
                        "getPlanUserSettingsId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanUserSettingsId(value2);

                Method method3 = oldModelClass.getMethod("getValue");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setValue(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
=======
        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestPhaseColumnImpl")) {
            return translateOutputContestPhaseColumn(oldModel);
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
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

        if (className.equals("com.ext.portlet.NoSuchBalloonStatsEntryException")) {
            return new com.ext.portlet.NoSuchBalloonStatsEntryException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestException")) {
            return new com.ext.portlet.NoSuchContestException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestDebateException")) {
            return new com.ext.portlet.NoSuchContestDebateException();
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

        if (className.equals("com.ext.portlet.NoSuchProposalSupporterException")) {
            return new com.ext.portlet.NoSuchProposalSupporterException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalVersionException")) {
            return new com.ext.portlet.NoSuchProposalVersionException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalVoteException")) {
            return new com.ext.portlet.NoSuchProposalVoteException();
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

    public static Object translateOutputBalloonStatsEntry(BaseModel<?> oldModel) {
        BalloonStatsEntryClp newModel = new BalloonStatsEntryClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setBalloonStatsEntryRemoteModel(oldModel);

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
}
