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

import java.util.List;

/**
 * Created by johannes on 12/16/15.
 */
public final class ProposalTemplateLifecycleUtil {

    public static final String DEFAULT_TEMPLATE_NAME = "New template";

    private ProposalTemplateLifecycleUtil() { }

    public static PlanTemplate create() throws SystemException {
        Long newPlanTemplateId = CounterLocalServiceUtil.increment(PlanTemplate.class.getName());
        PlanTemplate newPlanTemplate = PlanTemplateLocalServiceUtil.createPlanTemplate(newPlanTemplateId);
        newPlanTemplate.setName(DEFAULT_TEMPLATE_NAME);
        newPlanTemplate.persist();
        return newPlanTemplate;
    }

    public static void delete(Long templateId) throws PortalException, SystemException {
        PlanTemplate planTemplate = PlanTemplateLocalServiceUtil.getPlanTemplate(templateId);
        deletePlanTemplateSections(templateId);
        deleteUnusedPlanSectionDefinitions(planTemplate);
        PlanTemplateLocalServiceUtil.deletePlanTemplate(templateId);
    }

    private static void deletePlanTemplateSections(Long planTemplateId) throws SystemException {
        List<PlanTemplateSection> planTemplateSections =  PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(planTemplateId);
        for(PlanTemplateSection planTemplateSection : planTemplateSections){
            PlanTemplateSectionLocalServiceUtil.remove(planTemplateSection);
        }
    }

    private static void deleteUnusedPlanSectionDefinitions(PlanTemplate planTemplate)
            throws SystemException, PortalException {

        List<PlanSectionDefinition> planSectionDefinitions = PlanTemplateLocalServiceUtil.getSections(planTemplate);
        for(PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if(!isPlanSectionDefinitionUsedInOtherTemplate(planSectionDefinition.getId(), planTemplate.getId())) {
                PlanSectionDefinitionLocalServiceUtil.deletePlanSectionDefinition(planSectionDefinition);
            }
        }
    }

    public static boolean isPlanSectionDefinitionUsedInOtherTemplate(Long planSectionDefinitionId, Long planTemplateId) throws SystemException {
        List<PlanTemplateSection> planTemplateSections = PlanTemplateSectionLocalServiceUtil.findByPlanSectionDefinitionId(planSectionDefinitionId);
        return !(planTemplateSections.size() == 1 && planTemplateSections.get(0).getPlanTemplateId() == planTemplateId) && !planTemplateSections.isEmpty();
    }
}
