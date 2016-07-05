package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.PlanTemplateSection;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.ReferenceResolutionException;

import java.util.List;

public final class ProposalTemplateLifecycleUtil {

    private static final String DEFAULT_TEMPLATE_NAME = "New template";

    private ProposalTemplateLifecycleUtil() { }

    public static PlanTemplate create() {
        try {
            Long newPlanTemplateId = CounterLocalServiceUtil
                    .increment(PlanTemplate.class.getName());
            PlanTemplate newPlanTemplate = PlanTemplateLocalServiceUtil
                    .createPlanTemplate(newPlanTemplateId);
            newPlanTemplate.setName(DEFAULT_TEMPLATE_NAME);
            newPlanTemplate.persist();
            return newPlanTemplate;
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public static void delete(Long templateId) {
        try {
            PlanTemplate planTemplate = PlanTemplateLocalServiceUtil.getPlanTemplate(templateId);
            deletePlanTemplateSections(templateId);
            deleteUnusedPlanSectionDefinitions(planTemplate);
            PlanTemplateLocalServiceUtil.deletePlanTemplate(templateId);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw ReferenceResolutionException.toObject(PlanTemplate.class, templateId).build();
        }
    }

    private static void deletePlanTemplateSections(Long planTemplateId) {
        try {
            List<PlanTemplateSection> planTemplateSections =
                    PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(planTemplateId);
            for (PlanTemplateSection planTemplateSection : planTemplateSections) {
                PlanTemplateSectionLocalServiceUtil.remove(planTemplateSection);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void deleteUnusedPlanSectionDefinitions(PlanTemplate planTemplate) {
        try {
            List<PlanSectionDefinition> planSectionDefinitions = PlanTemplateLocalServiceUtil
                    .getSections(planTemplate);
            for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
                if (!isPlanSectionDefinitionUsedInOtherTemplate(planSectionDefinition.getId(),
                        planTemplate.getId())) {
                    PlanSectionDefinitionLocalServiceUtil
                            .deletePlanSectionDefinition(planSectionDefinition);
                }
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw ReferenceResolutionException.toObject(PlanSectionDefinition.class, "list")
                    .fromObject(PlanTemplate.class, planTemplate.getId());
        }
    }

    public static boolean isPlanSectionDefinitionUsedInOtherTemplate(Long planSectionDefinitionId,
            Long planTemplateId) {
        try {
            List<PlanTemplateSection> planTemplateSections =
                    PlanTemplateSectionLocalServiceUtil
                            .findByPlanSectionDefinitionId(planSectionDefinitionId);
            return !(planTemplateSections.size() == 1
                    && planTemplateSections.get(0).getPlanTemplateId() == planTemplateId)
                    && !planTemplateSections.isEmpty();
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }
}
