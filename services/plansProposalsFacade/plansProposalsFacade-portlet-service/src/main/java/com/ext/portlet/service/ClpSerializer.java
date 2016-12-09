package com.ext.portlet.service;

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
import com.ext.portlet.model.ContestTeamMemberRoleClp;
import com.ext.portlet.model.ContestTypeClp;
import com.ext.portlet.model.FocusAreaClp;
import com.ext.portlet.model.FocusAreaOntologyTermClp;
import com.ext.portlet.model.ImpactDefaultSeriesClp;
import com.ext.portlet.model.ImpactDefaultSeriesDataClp;
import com.ext.portlet.model.ImpactIterationClp;
import com.ext.portlet.model.ImpactTemplateFocusAreaListClp;
import com.ext.portlet.model.ImpactTemplateMaxFocusAreaClp;
import com.ext.portlet.model.ImpactTemplateSeriesClp;
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
import com.ext.portlet.model.PlanSectionDefinitionClp;
import com.ext.portlet.model.PlanTemplateClp;
import com.ext.portlet.model.PlanTemplateSectionClp;
import com.ext.portlet.model.PointDistributionTargetClp;
import com.ext.portlet.model.PointTypeClp;
import com.ext.portlet.model.PointsClp;
import com.ext.portlet.model.PointsDistributionConfigurationClp;
import com.ext.portlet.model.Proposal2PhaseClp;
import com.ext.portlet.model.ProposalAttributeClp;
import com.ext.portlet.model.ProposalClp;
import com.ext.portlet.model.ProposalContestPhaseAttributeClp;
import com.ext.portlet.model.ProposalMoveHistoryClp;
import com.ext.portlet.model.ProposalRatingClp;
import com.ext.portlet.model.ProposalRatingTypeClp;
import com.ext.portlet.model.ProposalRatingValueClp;
import com.ext.portlet.model.ProposalReferenceClp;
import com.ext.portlet.model.ProposalSupporterClp;
import com.ext.portlet.model.ProposalUnversionedAttributeClp;
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

        if (oldModelClassName.equals(ContestTeamMemberRoleClp.class.getName())) {
            return translateInputContestTeamMemberRole(oldModel);
        }

        if (oldModelClassName.equals(ContestTypeClp.class.getName())) {
            return translateInputContestType(oldModel);
        }

        if (oldModelClassName.equals(FocusAreaClp.class.getName())) {
            return translateInputFocusArea(oldModel);
        }

        if (oldModelClassName.equals(FocusAreaOntologyTermClp.class.getName())) {
            return translateInputFocusAreaOntologyTerm(oldModel);
        }

        if (oldModelClassName.equals(ImpactDefaultSeriesClp.class.getName())) {
            return translateInputImpactDefaultSeries(oldModel);
        }

        if (oldModelClassName.equals(ImpactDefaultSeriesDataClp.class.getName())) {
            return translateInputImpactDefaultSeriesData(oldModel);
        }

        if (oldModelClassName.equals(ImpactIterationClp.class.getName())) {
            return translateInputImpactIteration(oldModel);
        }

        if (oldModelClassName.equals(
                    ImpactTemplateFocusAreaListClp.class.getName())) {
            return translateInputImpactTemplateFocusAreaList(oldModel);
        }

        if (oldModelClassName.equals(
                    ImpactTemplateMaxFocusAreaClp.class.getName())) {
            return translateInputImpactTemplateMaxFocusArea(oldModel);
        }

        if (oldModelClassName.equals(ImpactTemplateSeriesClp.class.getName())) {
            return translateInputImpactTemplateSeries(oldModel);
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

        if (oldModelClassName.equals(PlanSectionDefinitionClp.class.getName())) {
            return translateInputPlanSectionDefinition(oldModel);
        }

        if (oldModelClassName.equals(PlanTemplateClp.class.getName())) {
            return translateInputPlanTemplate(oldModel);
        }

        if (oldModelClassName.equals(PlanTemplateSectionClp.class.getName())) {
            return translateInputPlanTemplateSection(oldModel);
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

        if (oldModelClassName.equals(
                    ProposalContestPhaseAttributeClp.class.getName())) {
            return translateInputProposalContestPhaseAttribute(oldModel);
        }

        if (oldModelClassName.equals(ProposalMoveHistoryClp.class.getName())) {
            return translateInputProposalMoveHistory(oldModel);
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

        if (oldModelClassName.equals(ProposalReferenceClp.class.getName())) {
            return translateInputProposalReference(oldModel);
        }

        if (oldModelClassName.equals(ProposalSupporterClp.class.getName())) {
            return translateInputProposalSupporter(oldModel);
        }

        if (oldModelClassName.equals(
                    ProposalUnversionedAttributeClp.class.getName())) {
            return translateInputProposalUnversionedAttribute(oldModel);
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

    public static Object translateInputContestTeamMemberRole(
        BaseModel<?> oldModel) {
        ContestTeamMemberRoleClp oldClpModel = (ContestTeamMemberRoleClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestTeamMemberRoleRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputContestType(BaseModel<?> oldModel) {
        ContestTypeClp oldClpModel = (ContestTypeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getContestTypeRemoteModel();

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

    public static Object translateInputImpactDefaultSeries(
        BaseModel<?> oldModel) {
        ImpactDefaultSeriesClp oldClpModel = (ImpactDefaultSeriesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImpactDefaultSeriesRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImpactDefaultSeriesData(
        BaseModel<?> oldModel) {
        ImpactDefaultSeriesDataClp oldClpModel = (ImpactDefaultSeriesDataClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImpactDefaultSeriesDataRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImpactIteration(BaseModel<?> oldModel) {
        ImpactIterationClp oldClpModel = (ImpactIterationClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImpactIterationRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImpactTemplateFocusAreaList(
        BaseModel<?> oldModel) {
        ImpactTemplateFocusAreaListClp oldClpModel = (ImpactTemplateFocusAreaListClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImpactTemplateFocusAreaListRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImpactTemplateMaxFocusArea(
        BaseModel<?> oldModel) {
        ImpactTemplateMaxFocusAreaClp oldClpModel = (ImpactTemplateMaxFocusAreaClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImpactTemplateMaxFocusAreaRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputImpactTemplateSeries(
        BaseModel<?> oldModel) {
        ImpactTemplateSeriesClp oldClpModel = (ImpactTemplateSeriesClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getImpactTemplateSeriesRemoteModel();

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

    public static Object translateInputPlanSectionDefinition(
        BaseModel<?> oldModel) {
        PlanSectionDefinitionClp oldClpModel = (PlanSectionDefinitionClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getPlanSectionDefinitionRemoteModel();

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

    public static Object translateInputProposalContestPhaseAttribute(
        BaseModel<?> oldModel) {
        ProposalContestPhaseAttributeClp oldClpModel = (ProposalContestPhaseAttributeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalContestPhaseAttributeRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalMoveHistory(
        BaseModel<?> oldModel) {
        ProposalMoveHistoryClp oldClpModel = (ProposalMoveHistoryClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalMoveHistoryRemoteModel();

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

    public static Object translateInputProposalReference(BaseModel<?> oldModel) {
        ProposalReferenceClp oldClpModel = (ProposalReferenceClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalReferenceRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalSupporter(BaseModel<?> oldModel) {
        ProposalSupporterClp oldClpModel = (ProposalSupporterClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalSupporterRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputProposalUnversionedAttribute(
        BaseModel<?> oldModel) {
        ProposalUnversionedAttributeClp oldClpModel = (ProposalUnversionedAttributeClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getProposalUnversionedAttributeRemoteModel();

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

        if (oldModelClassName.equals("com.ext.portlet.model.impl.ContestImpl")) {
            return translateOutputContest(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestDebateImpl")) {
            return translateOutputContestDebate(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestDiscussionImpl")) {
            return translateOutputContestDiscussion(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestEmailTemplateImpl")) {
            return translateOutputContestEmailTemplate(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestPhaseImpl")) {
            return translateOutputContestPhase(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestPhaseColumnImpl")) {
            return translateOutputContestPhaseColumn(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestPhaseRibbonTypeImpl")) {
            return translateOutputContestPhaseRibbonType(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestPhaseTypeImpl")) {
            return translateOutputContestPhaseType(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestScheduleImpl")) {
            return translateOutputContestSchedule(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestTeamMemberImpl")) {
            return translateOutputContestTeamMember(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestTeamMemberRoleImpl")) {
            return translateOutputContestTeamMemberRole(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ContestTypeImpl")) {
            return translateOutputContestType(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.FocusAreaImpl")) {
            return translateOutputFocusArea(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.FocusAreaOntologyTermImpl")) {
            return translateOutputFocusAreaOntologyTerm(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ImpactDefaultSeriesImpl")) {
            return translateOutputImpactDefaultSeries(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ImpactDefaultSeriesDataImpl")) {
            return translateOutputImpactDefaultSeriesData(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ImpactIterationImpl")) {
            return translateOutputImpactIteration(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ImpactTemplateFocusAreaListImpl")) {
            return translateOutputImpactTemplateFocusAreaList(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaImpl")) {
            return translateOutputImpactTemplateMaxFocusArea(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ImpactTemplateSeriesImpl")) {
            return translateOutputImpactTemplateSeries(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelCategoryImpl")) {
            return translateOutputModelCategory(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelDiscussionImpl")) {
            return translateOutputModelDiscussion(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelGlobalPreferenceImpl")) {
            return translateOutputModelGlobalPreference(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelInputGroupImpl")) {
            return translateOutputModelInputGroup(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelInputItemImpl")) {
            return translateOutputModelInputItem(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelOutputChartOrderImpl")) {
            return translateOutputModelOutputChartOrder(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelOutputItemImpl")) {
            return translateOutputModelOutputItem(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ModelPositionImpl")) {
            return translateOutputModelPosition(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.OntologySpaceImpl")) {
            return translateOutputOntologySpace(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.OntologyTermImpl")) {
            return translateOutputOntologyTerm(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.OntologyTermEntityImpl")) {
            return translateOutputOntologyTermEntity(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanSectionDefinitionImpl")) {
            return translateOutputPlanSectionDefinition(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanTemplateImpl")) {
            return translateOutputPlanTemplate(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PlanTemplateSectionImpl")) {
            return translateOutputPlanTemplateSection(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PointDistributionTargetImpl")) {
            return translateOutputPointDistributionTarget(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.PointsImpl")) {
            return translateOutputPoints(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.PointsDistributionConfigurationImpl")) {
            return translateOutputPointsDistributionConfiguration(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.PointTypeImpl")) {
            return translateOutputPointType(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals("com.ext.portlet.model.impl.ProposalImpl")) {
            return translateOutputProposal(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.Proposal2PhaseImpl")) {
            return translateOutputProposal2Phase(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalAttributeImpl")) {
            return translateOutputProposalAttribute(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalContestPhaseAttributeImpl")) {
            return translateOutputProposalContestPhaseAttribute(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalMoveHistoryImpl")) {
            return translateOutputProposalMoveHistory(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalRatingImpl")) {
            return translateOutputProposalRating(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalRatingTypeImpl")) {
            return translateOutputProposalRatingType(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalRatingValueImpl")) {
            return translateOutputProposalRatingValue(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalReferenceImpl")) {
            return translateOutputProposalReference(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalSupporterImpl")) {
            return translateOutputProposalSupporter(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalUnversionedAttributeImpl")) {
            return translateOutputProposalUnversionedAttribute(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalVersionImpl")) {
            return translateOutputProposalVersion(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
        }

        if (oldModelClassName.equals(
                    "com.ext.portlet.model.impl.ProposalVoteImpl")) {
            return translateOutputProposalVote(oldModel);
        } else if (oldModelClassName.endsWith("Clp")) {
            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Method getClpSerializerClassMethod = oldModelClass.getMethod(
                        "getClpSerializerClass");

                Class<?> oldClpSerializerClass = (Class<?>) getClpSerializerClassMethod.invoke(oldModel);

                Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

                Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
                        BaseModel.class);

                Class<?> oldModelModelClass = oldModel.getModelClass();

                Method getRemoteModelMethod = oldModelClass.getMethod("get" +
                        oldModelModelClass.getSimpleName() + "RemoteModel");

                Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

                BaseModel<?> newModel = (BaseModel<?>) translateOutputMethod.invoke(null,
                        oldRemoteModel);

                return newModel;
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info("Unable to translate " + oldModelClassName, t);
                }
            }
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

        if (className.equals(
                    "com.ext.portlet.NoSuchFocusAreaExceptionException")) {
            return new com.ext.portlet.NoSuchFocusAreaExceptionException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchFocusAreaOntologyTermExceptionException")) {
            return new com.ext.portlet.NoSuchFocusAreaOntologyTermExceptionException();
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
                    "com.ext.portlet.NoSuchContestTeamMemberRoleException")) {
            return new com.ext.portlet.NoSuchContestTeamMemberRoleException();
        }

        if (className.equals("com.ext.portlet.NoSuchContestTypeException")) {
            return new com.ext.portlet.NoSuchContestTypeException();
        }

        if (className.equals("com.ext.portlet.NoSuchFocusAreaException")) {
            return new com.ext.portlet.NoSuchFocusAreaException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchFocusAreaOntologyTermException")) {
            return new com.ext.portlet.NoSuchFocusAreaOntologyTermException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchImpactDefaultSeriesException")) {
            return new com.ext.portlet.NoSuchImpactDefaultSeriesException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchImpactDefaultSeriesDataException")) {
            return new com.ext.portlet.NoSuchImpactDefaultSeriesDataException();
        }

        if (className.equals("com.ext.portlet.NoSuchImpactIterationException")) {
            return new com.ext.portlet.NoSuchImpactIterationException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchImpactTemplateFocusAreaListException")) {
            return new com.ext.portlet.NoSuchImpactTemplateFocusAreaListException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException")) {
            return new com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchImpactTemplateSeriesException")) {
            return new com.ext.portlet.NoSuchImpactTemplateSeriesException();
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

        if (className.equals(
                    "com.ext.portlet.NoSuchPlanSectionDefinitionException")) {
            return new com.ext.portlet.NoSuchPlanSectionDefinitionException();
        }

        if (className.equals("com.ext.portlet.NoSuchPlanTemplateException")) {
            return new com.ext.portlet.NoSuchPlanTemplateException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchPlanTemplateSectionException")) {
            return new com.ext.portlet.NoSuchPlanTemplateSectionException();
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
                    "com.ext.portlet.NoSuchProposalContestPhaseAttributeException")) {
            return new com.ext.portlet.NoSuchProposalContestPhaseAttributeException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchProposalMoveHistoryException")) {
            return new com.ext.portlet.NoSuchProposalMoveHistoryException();
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

        if (className.equals("com.ext.portlet.NoSuchProposalReferenceException")) {
            return new com.ext.portlet.NoSuchProposalReferenceException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalSupporterException")) {
            return new com.ext.portlet.NoSuchProposalSupporterException();
        }

        if (className.equals(
                    "com.ext.portlet.NoSuchProposalUnversionedAttributeException")) {
            return new com.ext.portlet.NoSuchProposalUnversionedAttributeException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalVersionException")) {
            return new com.ext.portlet.NoSuchProposalVersionException();
        }

        if (className.equals("com.ext.portlet.NoSuchProposalVoteException")) {
            return new com.ext.portlet.NoSuchProposalVoteException();
        }

        return throwable;
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

    public static Object translateOutputContestTeamMemberRole(
        BaseModel<?> oldModel) {
        ContestTeamMemberRoleClp newModel = new ContestTeamMemberRoleClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestTeamMemberRoleRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputContestType(BaseModel<?> oldModel) {
        ContestTypeClp newModel = new ContestTypeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setContestTypeRemoteModel(oldModel);

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

    public static Object translateOutputImpactDefaultSeries(
        BaseModel<?> oldModel) {
        ImpactDefaultSeriesClp newModel = new ImpactDefaultSeriesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImpactDefaultSeriesRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImpactDefaultSeriesData(
        BaseModel<?> oldModel) {
        ImpactDefaultSeriesDataClp newModel = new ImpactDefaultSeriesDataClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImpactDefaultSeriesDataRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImpactIteration(BaseModel<?> oldModel) {
        ImpactIterationClp newModel = new ImpactIterationClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImpactIterationRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImpactTemplateFocusAreaList(
        BaseModel<?> oldModel) {
        ImpactTemplateFocusAreaListClp newModel = new ImpactTemplateFocusAreaListClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImpactTemplateFocusAreaListRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImpactTemplateMaxFocusArea(
        BaseModel<?> oldModel) {
        ImpactTemplateMaxFocusAreaClp newModel = new ImpactTemplateMaxFocusAreaClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImpactTemplateMaxFocusAreaRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputImpactTemplateSeries(
        BaseModel<?> oldModel) {
        ImpactTemplateSeriesClp newModel = new ImpactTemplateSeriesClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setImpactTemplateSeriesRemoteModel(oldModel);

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

    public static Object translateOutputPlanSectionDefinition(
        BaseModel<?> oldModel) {
        PlanSectionDefinitionClp newModel = new PlanSectionDefinitionClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setPlanSectionDefinitionRemoteModel(oldModel);

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

    public static Object translateOutputProposalContestPhaseAttribute(
        BaseModel<?> oldModel) {
        ProposalContestPhaseAttributeClp newModel = new ProposalContestPhaseAttributeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalContestPhaseAttributeRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalMoveHistory(
        BaseModel<?> oldModel) {
        ProposalMoveHistoryClp newModel = new ProposalMoveHistoryClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalMoveHistoryRemoteModel(oldModel);

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

    public static Object translateOutputProposalReference(BaseModel<?> oldModel) {
        ProposalReferenceClp newModel = new ProposalReferenceClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalReferenceRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalSupporter(BaseModel<?> oldModel) {
        ProposalSupporterClp newModel = new ProposalSupporterClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalSupporterRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputProposalUnversionedAttribute(
        BaseModel<?> oldModel) {
        ProposalUnversionedAttributeClp newModel = new ProposalUnversionedAttributeClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setProposalUnversionedAttributeRemoteModel(oldModel);

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
