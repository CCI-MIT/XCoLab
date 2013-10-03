package com.ext.portlet.service;

import com.ext.portlet.model.ActivitySubscriptionClp;
import com.ext.portlet.model.BalloonStatsEntryClp;
import com.ext.portlet.model.ContestClp;
import com.ext.portlet.model.ContestDebateClp;
import com.ext.portlet.model.ContestPhaseClp;
import com.ext.portlet.model.ContestPhaseColumnClp;
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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ClpSerializer {
    private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
    private static ClassLoader _classLoader;
    private static String _servletContextName;

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

    public static void setClassLoader(ClassLoader classLoader) {
        _classLoader = classLoader;
    }

    public static Object translateInput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(ActivitySubscriptionClp.class.getName())) {
            return translateInputActivitySubscription(oldModel);
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
        ActivitySubscriptionClp oldCplModel = (ActivitySubscriptionClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ActivitySubscriptionImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPk",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPk());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setClassNameId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getClassNameId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setClassPK",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getClassPK());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setType",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getType());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setAutomaticSubscriptionCounter",
                        new Class[] { Integer.TYPE });

                Integer value4 = new Integer(oldCplModel.getAutomaticSubscriptionCounter());

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setExtraData",
                        new Class[] { String.class });

                String value5 = oldCplModel.getExtraData();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setReceiverId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getReceiverId());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setCreateDate",
                        new Class[] { Date.class });

                Date value7 = oldCplModel.getCreateDate();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setModifiedDate",
                        new Class[] { Date.class });

                Date value8 = oldCplModel.getModifiedDate();

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

    public static Object translateInputBalloonStatsEntry(BaseModel<?> oldModel) {
        BalloonStatsEntryClp oldCplModel = (BalloonStatsEntryClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.BalloonStatsEntryImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setFirstContestId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getFirstContestId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setSecondContestId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getSecondContestId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setChoosenContest",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getChoosenContest());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCookie",
                        new Class[] { String.class });

                String value4 = oldCplModel.getCookie();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setIp",
                        new Class[] { String.class });

                String value5 = oldCplModel.getIp();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setExtraData",
                        new Class[] { String.class });

                String value6 = oldCplModel.getExtraData();

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

    public static Object translateInputContest(BaseModel<?> oldModel) {
        ContestClp oldCplModel = (ContestClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ContestImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setContestPK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getContestPK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestName",
                        new Class[] { String.class });

                String value1 = oldCplModel.getContestName();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setContestShortName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getContestShortName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setContestDescription",
                        new Class[] { String.class });

                String value3 = oldCplModel.getContestDescription();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setContestModelDescription",
                        new Class[] { String.class });

                String value4 = oldCplModel.getContestModelDescription();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setContestPositionsDescription",
                        new Class[] { String.class });

                String value5 = oldCplModel.getContestPositionsDescription();

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setDefaultPlanDescription",
                        new Class[] { String.class });

                String value6 = oldCplModel.getDefaultPlanDescription();

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setPlanTypeId",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getPlanTypeId());

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value8 = oldCplModel.getCreated();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setUpdated",
                        new Class[] { Date.class });

                Date value9 = oldCplModel.getUpdated();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value10 = new Long(oldCplModel.getAuthorId());

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setContestActive",
                        new Class[] { Boolean.TYPE });

                Boolean value11 = new Boolean(oldCplModel.getContestActive());

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setPlanTemplateId",
                        new Class[] { Long.TYPE });

                Long value12 = new Long(oldCplModel.getPlanTemplateId());

                method12.invoke(newModel, value12);

                Method method13 = newModelClass.getMethod("setFocusAreaId",
                        new Class[] { Long.TYPE });

                Long value13 = new Long(oldCplModel.getFocusAreaId());

                method13.invoke(newModel, value13);

                Method method14 = newModelClass.getMethod("setContestLogoId",
                        new Class[] { Long.TYPE });

                Long value14 = new Long(oldCplModel.getContestLogoId());

                method14.invoke(newModel, value14);

                Method method15 = newModelClass.getMethod("setFeatured",
                        new Class[] { Boolean.TYPE });

                Boolean value15 = new Boolean(oldCplModel.getFeatured());

                method15.invoke(newModel, value15);

                Method method16 = newModelClass.getMethod("setPlansOpenByDefault",
                        new Class[] { Boolean.TYPE });

                Boolean value16 = new Boolean(oldCplModel.getPlansOpenByDefault());

                method16.invoke(newModel, value16);

                Method method17 = newModelClass.getMethod("setSponsorLogoId",
                        new Class[] { Long.TYPE });

                Long value17 = new Long(oldCplModel.getSponsorLogoId());

                method17.invoke(newModel, value17);

                Method method18 = newModelClass.getMethod("setSponsorText",
                        new Class[] { String.class });

                String value18 = oldCplModel.getSponsorText();

                method18.invoke(newModel, value18);

                Method method19 = newModelClass.getMethod("setFlag",
                        new Class[] { Integer.TYPE });

                Integer value19 = new Integer(oldCplModel.getFlag());

                method19.invoke(newModel, value19);

                Method method20 = newModelClass.getMethod("setFlagText",
                        new Class[] { String.class });

                String value20 = oldCplModel.getFlagText();

                method20.invoke(newModel, value20);

                Method method21 = newModelClass.getMethod("setFlagTooltip",
                        new Class[] { String.class });

                String value21 = oldCplModel.getFlagTooltip();

                method21.invoke(newModel, value21);

                Method method22 = newModelClass.getMethod("setGroupId",
                        new Class[] { Long.TYPE });

                Long value22 = new Long(oldCplModel.getGroupId());

                method22.invoke(newModel, value22);

                Method method23 = newModelClass.getMethod("setDiscussionGroupId",
                        new Class[] { Long.TYPE });

                Long value23 = new Long(oldCplModel.getDiscussionGroupId());

                method23.invoke(newModel, value23);

                Method method24 = newModelClass.getMethod("setWeight",
                        new Class[] { Integer.TYPE });

                Integer value24 = new Integer(oldCplModel.getWeight());

                method24.invoke(newModel, value24);

                Method method25 = newModelClass.getMethod("setResourcesUrl",
                        new Class[] { String.class });

                String value25 = oldCplModel.getResourcesUrl();

                method25.invoke(newModel, value25);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputContestDebate(BaseModel<?> oldModel) {
        ContestDebateClp oldCplModel = (ContestDebateClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ContestDebateImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setDebateId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getDebateId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setContestPK",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getContestPK());

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

    public static Object translateInputContestPhase(BaseModel<?> oldModel) {
        ContestPhaseClp oldCplModel = (ContestPhaseClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ContestPhaseImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setContestPhasePK",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getContestPhasePK());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestPK",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getContestPK());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setContestPhaseType",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getContestPhaseType());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setContestPhaseAutopromote",
                        new Class[] { String.class });

                String value3 = oldCplModel.getContestPhaseAutopromote();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setContestPhaseDescriptionOverride",
                        new Class[] { String.class });

                String value4 = oldCplModel.getContestPhaseDescriptionOverride();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setPhaseActiveOverride",
                        new Class[] { Boolean.TYPE });

                Boolean value5 = new Boolean(oldCplModel.getPhaseActiveOverride());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setPhaseInactiveOverride",
                        new Class[] { Boolean.TYPE });

                Boolean value6 = new Boolean(oldCplModel.getPhaseInactiveOverride());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setPhaseStartDate",
                        new Class[] { Date.class });

                Date value7 = oldCplModel.getPhaseStartDate();

                method7.invoke(newModel, value7);

                Method method8 = newModelClass.getMethod("setPhaseEndDate",
                        new Class[] { Date.class });

                Date value8 = oldCplModel.getPhaseEndDate();

                method8.invoke(newModel, value8);

                Method method9 = newModelClass.getMethod("setNextStatus",
                        new Class[] { String.class });

                String value9 = oldCplModel.getNextStatus();

                method9.invoke(newModel, value9);

                Method method10 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value10 = oldCplModel.getCreated();

                method10.invoke(newModel, value10);

                Method method11 = newModelClass.getMethod("setUpdated",
                        new Class[] { Date.class });

                Date value11 = oldCplModel.getUpdated();

                method11.invoke(newModel, value11);

                Method method12 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value12 = new Long(oldCplModel.getAuthorId());

                method12.invoke(newModel, value12);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateInputContestPhaseColumn(BaseModel<?> oldModel) {
        ContestPhaseColumnClp oldCplModel = (ContestPhaseColumnClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ContestPhaseColumnImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestPhasePK",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getContestPhasePK());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setColumnName",
                        new Class[] { String.class });

                String value2 = oldCplModel.getColumnName();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setColumnWeight",
                        new Class[] { Integer.TYPE });

                Integer value3 = new Integer(oldCplModel.getColumnWeight());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setDefaultSort",
                        new Class[] { Boolean.TYPE });

                Boolean value4 = new Boolean(oldCplModel.getDefaultSort());

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

    public static Object translateInputContestPhaseType(BaseModel<?> oldModel) {
        ContestPhaseTypeClp oldCplModel = (ContestPhaseTypeClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ContestPhaseTypeImpl",
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

                Method method3 = newModelClass.getMethod("setStatus",
                        new Class[] { String.class });

                String value3 = oldCplModel.getStatus();

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

    public static Object translateInputContestTeamMember(BaseModel<?> oldModel) {
        ContestTeamMemberClp oldCplModel = (ContestTeamMemberClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.ContestTeamMemberImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setContestId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getContestId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setUserId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getUserId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setRole",
                        new Class[] { String.class });

                String value3 = oldCplModel.getRole();

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

    public static Object translateInputDiscussionCategory(BaseModel<?> oldModel) {
        DiscussionCategoryClp oldCplModel = (DiscussionCategoryClp) oldModel;

        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Class<?> newModelClass = Class.forName("com.ext.portlet.model.impl.DiscussionCategoryImpl",
                        true, _classLoader);

                Object newModel = newModelClass.newInstance();

                Method method0 = newModelClass.getMethod("setPk",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getPk());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setCategoryId",
                        new Class[] { Long.TYPE });

                Long value1 = new Long(oldCplModel.getCategoryId());

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setCategoryGroupId",
                        new Class[] { Long.TYPE });

                Long value2 = new Long(oldCplModel.getCategoryGroupId());

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setAuthorId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getAuthorId());

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setName",
                        new Class[] { String.class });

                String value4 = oldCplModel.getName();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value5 = oldCplModel.getDescription();

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

                Method method4 = newModelClass.getMethod("setCreated",
                        new Class[] { Date.class });

                Date value4 = oldCplModel.getCreated();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setVersion",
                        new Class[] { Long.TYPE });

                Long value5 = new Long(oldCplModel.getVersion());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setPlanVersion",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getPlanVersion());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setUpdateAuthorId",
                        new Class[] { Long.TYPE });

                Long value7 = new Long(oldCplModel.getUpdateAuthorId());

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

                Method method1 = newModelClass.getMethod("setAdminTitle",
                        new Class[] { String.class });

                String value1 = oldCplModel.getAdminTitle();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setTitle",
                        new Class[] { String.class });

                String value2 = oldCplModel.getTitle();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setDefaultText",
                        new Class[] { String.class });

                String value3 = oldCplModel.getDefaultText();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setHelpText",
                        new Class[] { String.class });

                String value4 = oldCplModel.getHelpText();

                method4.invoke(newModel, value4);

                Method method5 = newModelClass.getMethod("setCharacterLimit",
                        new Class[] { Integer.TYPE });

                Integer value5 = new Integer(oldCplModel.getCharacterLimit());

                method5.invoke(newModel, value5);

                Method method6 = newModelClass.getMethod("setFocusAreaId",
                        new Class[] { Long.TYPE });

                Long value6 = new Long(oldCplModel.getFocusAreaId());

                method6.invoke(newModel, value6);

                Method method7 = newModelClass.getMethod("setLocked",
                        new Class[] { Boolean.TYPE });

                Boolean value7 = new Boolean(oldCplModel.getLocked());

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

                Method method3 = newModelClass.getMethod("setTypeId",
                        new Class[] { Long.TYPE });

                Long value3 = new Long(oldCplModel.getTypeId());

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

                Method method0 = newModelClass.getMethod("setId",
                        new Class[] { Long.TYPE });

                Long value0 = new Long(oldCplModel.getId());

                method0.invoke(newModel, value0);

                Method method1 = newModelClass.getMethod("setRibbon",
                        new Class[] { String.class });

                String value1 = oldCplModel.getRibbon();

                method1.invoke(newModel, value1);

                Method method2 = newModelClass.getMethod("setHoverText",
                        new Class[] { String.class });

                String value2 = oldCplModel.getHoverText();

                method2.invoke(newModel, value2);

                Method method3 = newModelClass.getMethod("setDescription",
                        new Class[] { String.class });

                String value3 = oldCplModel.getDescription();

                method3.invoke(newModel, value3);

                Method method4 = newModelClass.getMethod("setCopyOnPromote",
                        new Class[] { Boolean.TYPE });

                Boolean value4 = new Boolean(oldCplModel.getCopyOnPromote());

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
        }

        return oldModel;
    }

    public static Object translateOutputPlanRelated(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanRelatedClp newModel = new PlanRelatedClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getSectionId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setSectionId(value0);

                Method method1 = oldModelClass.getMethod("getRelatedPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setRelatedPlanId(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanSection(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanSectionClp newModel = new PlanSectionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod(
                        "getPlanSectionDefinitionId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanSectionDefinitionId(value1);

                Method method2 = oldModelClass.getMethod("getPlanId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value2);

                Method method3 = oldModelClass.getMethod("getContent");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setContent(value3);

                Method method4 = oldModelClass.getMethod("getCreated");

                Date value4 = (Date) method4.invoke(oldModel, (Object[]) null);

                newModel.setCreated(value4);

                Method method5 = oldModelClass.getMethod("getVersion");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setVersion(value5);

                Method method6 = oldModelClass.getMethod("getPlanVersion");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setPlanVersion(value6);

                Method method7 = oldModelClass.getMethod("getUpdateAuthorId");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAuthorId(value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanSectionDefinition(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanSectionDefinitionClp newModel = new PlanSectionDefinitionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getAdminTitle");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setAdminTitle(value1);

                Method method2 = oldModelClass.getMethod("getTitle");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setTitle(value2);

                Method method3 = oldModelClass.getMethod("getDefaultText");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setDefaultText(value3);

                Method method4 = oldModelClass.getMethod("getHelpText");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setHelpText(value4);

                Method method5 = oldModelClass.getMethod("getCharacterLimit");

                Integer value5 = (Integer) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setCharacterLimit(value5);

                Method method6 = oldModelClass.getMethod("getFocusAreaId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setFocusAreaId(value6);

                Method method7 = oldModelClass.getMethod("getLocked");

                Boolean value7 = (Boolean) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setLocked(value7);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanSectionPlanMap(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanSectionPlanMapClp newModel = new PlanSectionPlanMapClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getSectionId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setSectionId(value0);

                Method method1 = oldModelClass.getMethod("getRelatedPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setRelatedPlanId(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlansFilter(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlansFilterClp newModel = new PlansFilterClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getUserId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value0);

                Method method1 = oldModelClass.getMethod("getPlanTypeId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value1);

                Method method2 = oldModelClass.getMethod("getName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value2);

                Method method3 = oldModelClass.getMethod("getCreator");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setCreator(value3);

                Method method4 = oldModelClass.getMethod("getDescription");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value4);

                Method method5 = oldModelClass.getMethod("getCO2From");

                Double value5 = (Double) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setCO2From(value5);

                Method method6 = oldModelClass.getMethod("getCO2To");

                Double value6 = (Double) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setCO2To(value6);

                Method method7 = oldModelClass.getMethod("getVotesFrom");

                Double value7 = (Double) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setVotesFrom(value7);

                Method method8 = oldModelClass.getMethod("getVotesTo");

                Double value8 = (Double) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setVotesTo(value8);

                Method method9 = oldModelClass.getMethod("getDamageFrom");

                Double value9 = (Double) method9.invoke(oldModel,
                        (Object[]) null);

                newModel.setDamageFrom(value9);

                Method method10 = oldModelClass.getMethod("getDamageTo");

                Double value10 = (Double) method10.invoke(oldModel,
                        (Object[]) null);

                newModel.setDamageTo(value10);

                Method method11 = oldModelClass.getMethod("getMitigationFrom");

                Double value11 = (Double) method11.invoke(oldModel,
                        (Object[]) null);

                newModel.setMitigationFrom(value11);

                Method method12 = oldModelClass.getMethod("getMitigationTo");

                Double value12 = (Double) method12.invoke(oldModel,
                        (Object[]) null);

                newModel.setMitigationTo(value12);

                Method method13 = oldModelClass.getMethod("getDateFrom");

                Date value13 = (Date) method13.invoke(oldModel, (Object[]) null);

                newModel.setDateFrom(value13);

                Method method14 = oldModelClass.getMethod("getDateTo");

                Date value14 = (Date) method14.invoke(oldModel, (Object[]) null);

                newModel.setDateTo(value14);

                Method method15 = oldModelClass.getMethod(
                        "getFilterPositionsAll");

                Boolean value15 = (Boolean) method15.invoke(oldModel,
                        (Object[]) null);

                newModel.setFilterPositionsAll(value15);

                Method method16 = oldModelClass.getMethod("getEnabled");

                Boolean value16 = (Boolean) method16.invoke(oldModel,
                        (Object[]) null);

                newModel.setEnabled(value16);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlansFilterPosition(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlansFilterPositionClp newModel = new PlansFilterPositionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getUserId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value0);

                Method method1 = oldModelClass.getMethod("getPlanTypeId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value1);

                Method method2 = oldModelClass.getMethod("getPositionId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPositionId(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlansUserSettings(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlansUserSettingsClp newModel = new PlansUserSettingsClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getPlanUserSettingsId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanUserSettingsId(value0);

                Method method1 = oldModelClass.getMethod("getUserId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value1);

                Method method2 = oldModelClass.getMethod("getPlanTypeId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value2);

                Method method3 = oldModelClass.getMethod("getSortColumn");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setSortColumn(value3);

                Method method4 = oldModelClass.getMethod("getSortDirection");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setSortDirection(value4);

                Method method5 = oldModelClass.getMethod("getFilterEnabled");

                Boolean value5 = (Boolean) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setFilterEnabled(value5);

                Method method6 = oldModelClass.getMethod(
                        "getFilterPositionsAll");

                Boolean value6 = (Boolean) method6.invoke(oldModel,
                        (Object[]) null);

                newModel.setFilterPositionsAll(value6);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanTeamHistory(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTeamHistoryClp newModel = new PlanTeamHistoryClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getPlanId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value1);

                Method method2 = oldModelClass.getMethod("getUserId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value2);

                Method method3 = oldModelClass.getMethod("getAction");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setAction(value3);

                Method method4 = oldModelClass.getMethod("getPayload");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setPayload(value4);

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

    public static Object translateOutputPlanTemplate(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTemplateClp newModel = new PlanTemplateClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanTemplateSection(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTemplateSectionClp newModel = new PlanTemplateSectionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanTemplateId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanTemplateId(value0);

                Method method1 = oldModelClass.getMethod("getPlanSectionId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanSectionId(value1);

                Method method2 = oldModelClass.getMethod("getWeight");

                Integer value2 = (Integer) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setWeight(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanType(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTypeClp newModel = new PlanTypeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanTypeId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value0);

                Method method1 = oldModelClass.getMethod("getName");

                String value1 = (String) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value1);

                Method method2 = oldModelClass.getMethod("getDescription");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setDescription(value2);

                Method method3 = oldModelClass.getMethod("getModelId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setModelId(value3);

                Method method4 = oldModelClass.getMethod("getModelTypeName");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setModelTypeName(value4);

                Method method5 = oldModelClass.getMethod("getPublished");

                Boolean value5 = (Boolean) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setPublished(value5);

                Method method6 = oldModelClass.getMethod(
                        "getPublishedCounterpartId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setPublishedCounterpartId(value6);

                Method method7 = oldModelClass.getMethod("getIsDefault");

                Boolean value7 = (Boolean) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setIsDefault(value7);

                Method method8 = oldModelClass.getMethod("getDefaultModelId");

                Long value8 = (Long) method8.invoke(oldModel, (Object[]) null);

                newModel.setDefaultModelId(value8);

                Method method9 = oldModelClass.getMethod("getDefaultScenarioId");

                Long value9 = (Long) method9.invoke(oldModel, (Object[]) null);

                newModel.setDefaultScenarioId(value9);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanTypeAttribute(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTypeAttributeClp newModel = new PlanTypeAttributeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod(
                        "getPlanTypeAttributeId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeAttributeId(value0);

                Method method1 = oldModelClass.getMethod("getPlanTypeId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value1);

                Method method2 = oldModelClass.getMethod("getAttributeName");

                String value2 = (String) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setAttributeName(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanTypeColumn(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanTypeColumnClp newModel = new PlanTypeColumnClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getPlanTypeColumnId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeColumnId(value0);

                Method method1 = oldModelClass.getMethod("getPlanTypeId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setPlanTypeId(value1);

                Method method2 = oldModelClass.getMethod("getWeight");

                Integer value2 = (Integer) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setWeight(value2);

                Method method3 = oldModelClass.getMethod("getColumnName");

                String value3 = (String) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setColumnName(value3);

                Method method4 = oldModelClass.getMethod("getVisibleByDefault");

                Boolean value4 = (Boolean) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setVisibleByDefault(value4);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputPlanVote(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                PlanVoteClp newModel = new PlanVoteClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getUserId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value0);

                Method method1 = oldModelClass.getMethod("getContestId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setContestId(value1);

                Method method2 = oldModelClass.getMethod("getPlanId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setPlanId(value2);

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

    public static Object translateOutputProposal(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ProposalClp newModel = new ProposalClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getProposalId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setProposalId(value0);

                Method method1 = oldModelClass.getMethod("getCreateDate");

                Date value1 = (Date) method1.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value1);

                Method method2 = oldModelClass.getMethod("getUpdatedDate");

                Date value2 = (Date) method2.invoke(oldModel, (Object[]) null);

                newModel.setUpdatedDate(value2);

                Method method3 = oldModelClass.getMethod("getCurrentVersion");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setCurrentVersion(value3);

                Method method4 = oldModelClass.getMethod("getAuthorId");

                Long value4 = (Long) method4.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value4);

                Method method5 = oldModelClass.getMethod("getVisible");

                Boolean value5 = (Boolean) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setVisible(value5);

                Method method6 = oldModelClass.getMethod("getDiscussionId");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setDiscussionId(value6);

                Method method7 = oldModelClass.getMethod("getJudgeDiscussionId");

                Long value7 = (Long) method7.invoke(oldModel, (Object[]) null);

                newModel.setJudgeDiscussionId(value7);

                Method method8 = oldModelClass.getMethod(
                        "getFellowDiscussionId");

                Long value8 = (Long) method8.invoke(oldModel, (Object[]) null);

                newModel.setFellowDiscussionId(value8);

                Method method9 = oldModelClass.getMethod(
                        "getAdvisorDiscussionId");

                Long value9 = (Long) method9.invoke(oldModel, (Object[]) null);

                newModel.setAdvisorDiscussionId(value9);

                Method method10 = oldModelClass.getMethod("getGroupId");

                Long value10 = (Long) method10.invoke(oldModel, (Object[]) null);

                newModel.setGroupId(value10);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputProposal2Phase(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                Proposal2PhaseClp newModel = new Proposal2PhaseClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getProposalId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setProposalId(value0);

                Method method1 = oldModelClass.getMethod("getContestPhaseId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setContestPhaseId(value1);

                Method method2 = oldModelClass.getMethod("getVersionFrom");

                Integer value2 = (Integer) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setVersionFrom(value2);

                Method method3 = oldModelClass.getMethod("getVersionTo");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setVersionTo(value3);

                Method method4 = oldModelClass.getMethod("getSortWeight");

                Integer value4 = (Integer) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setSortWeight(value4);

                Method method5 = oldModelClass.getMethod(
                        "getAutopromoteCandidate");

                Boolean value5 = (Boolean) method5.invoke(oldModel,
                        (Object[]) null);

                newModel.setAutopromoteCandidate(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputProposalAttribute(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ProposalAttributeClp newModel = new ProposalAttributeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getProposalId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setProposalId(value1);

                Method method2 = oldModelClass.getMethod("getVersion");

                Integer value2 = (Integer) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setVersion(value2);

                Method method3 = oldModelClass.getMethod(
                        "getVersionWhenCreated");

                Integer value3 = (Integer) method3.invoke(oldModel,
                        (Object[]) null);

                newModel.setVersionWhenCreated(value3);

                Method method4 = oldModelClass.getMethod("getName");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value4);

                Method method5 = oldModelClass.getMethod("getAdditionalId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setAdditionalId(value5);

                Method method6 = oldModelClass.getMethod("getNumericValue");

                Long value6 = (Long) method6.invoke(oldModel, (Object[]) null);

                newModel.setNumericValue(value6);

                Method method7 = oldModelClass.getMethod("getStringValue");

                String value7 = (String) method7.invoke(oldModel,
                        (Object[]) null);

                newModel.setStringValue(value7);

                Method method8 = oldModelClass.getMethod("getRealValue");

                Double value8 = (Double) method8.invoke(oldModel,
                        (Object[]) null);

                newModel.setRealValue(value8);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputProposalAttributeType(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ProposalAttributeTypeClp newModel = new ProposalAttributeTypeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getName");

                String value0 = (String) method0.invoke(oldModel,
                        (Object[]) null);

                newModel.setName(value0);

                Method method1 = oldModelClass.getMethod(
                        "getVisibleInVersionHistory");

                Boolean value1 = (Boolean) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setVisibleInVersionHistory(value1);

                Method method2 = oldModelClass.getMethod("getCopyOnPromote");

                Boolean value2 = (Boolean) method2.invoke(oldModel,
                        (Object[]) null);

                newModel.setCopyOnPromote(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputProposalContestPhaseAttribute(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ProposalContestPhaseAttributeClp newModel = new ProposalContestPhaseAttributeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getProposalId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setProposalId(value1);

                Method method2 = oldModelClass.getMethod("getContestPhaseId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setContestPhaseId(value2);

                Method method3 = oldModelClass.getMethod("getTypeId");

                Long value3 = (Long) method3.invoke(oldModel, (Object[]) null);

                newModel.setTypeId(value3);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputProposalContestPhaseAttributeType(
        BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ProposalContestPhaseAttributeTypeClp newModel = new ProposalContestPhaseAttributeTypeClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setId(value0);

                Method method1 = oldModelClass.getMethod("getRibbon");

                String value1 = (String) method1.invoke(oldModel,
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

    public static Object translateOutputProposalSupporter(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ProposalSupporterClp newModel = new ProposalSupporterClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getProposalId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setProposalId(value0);

                Method method1 = oldModelClass.getMethod("getUserId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value1);

                Method method2 = oldModelClass.getMethod("getCreateDate");

                Date value2 = (Date) method2.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value2);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputProposalVersion(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ProposalVersionClp newModel = new ProposalVersionClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getProposalId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setProposalId(value0);

                Method method1 = oldModelClass.getMethod("getVersion");

                Integer value1 = (Integer) method1.invoke(oldModel,
                        (Object[]) null);

                newModel.setVersion(value1);

                Method method2 = oldModelClass.getMethod("getAuthorId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setAuthorId(value2);

                Method method3 = oldModelClass.getMethod("getCreateDate");

                Date value3 = (Date) method3.invoke(oldModel, (Object[]) null);

                newModel.setCreateDate(value3);

                Method method4 = oldModelClass.getMethod("getUpdateType");

                String value4 = (String) method4.invoke(oldModel,
                        (Object[]) null);

                newModel.setUpdateType(value4);

                Method method5 = oldModelClass.getMethod(
                        "getUpdateAdditionalId");

                Long value5 = (Long) method5.invoke(oldModel, (Object[]) null);

                newModel.setUpdateAdditionalId(value5);

                return newModel;
            } catch (Exception e) {
                _log.error(e, e);
            }
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }

        return oldModel;
    }

    public static Object translateOutputProposalVote(BaseModel<?> oldModel) {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        try {
            currentThread.setContextClassLoader(_classLoader);

            try {
                ProposalVoteClp newModel = new ProposalVoteClp();

                Class<?> oldModelClass = oldModel.getClass();

                Method method0 = oldModelClass.getMethod("getProposalId");

                Long value0 = (Long) method0.invoke(oldModel, (Object[]) null);

                newModel.setProposalId(value0);

                Method method1 = oldModelClass.getMethod("getContestPhaseId");

                Long value1 = (Long) method1.invoke(oldModel, (Object[]) null);

                newModel.setContestPhaseId(value1);

                Method method2 = oldModelClass.getMethod("getUserId");

                Long value2 = (Long) method2.invoke(oldModel, (Object[]) null);

                newModel.setUserId(value2);

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
}
