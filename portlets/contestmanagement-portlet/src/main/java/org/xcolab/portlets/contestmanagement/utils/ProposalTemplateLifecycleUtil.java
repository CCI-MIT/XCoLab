package org.xcolab.portlets.contestmanagement.utils;


import org.xcolab.client.contest.PlanTemplateClient;
import org.xcolab.client.contest.pojo.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.PlanTemplate;
import org.xcolab.client.contest.pojo.PlanTemplateSection;

import java.util.List;


public final class ProposalTemplateLifecycleUtil {

    private static final String DEFAULT_TEMPLATE_NAME = "New template";

    private ProposalTemplateLifecycleUtil() { }

    public static PlanTemplate create() {
            PlanTemplate newPlanTemplate = new PlanTemplate();
            newPlanTemplate.setName(DEFAULT_TEMPLATE_NAME);
            newPlanTemplate.setImpactSeriesTemplateId(1l);
            newPlanTemplate.setImpactSeriesTemplateId(1l);
            newPlanTemplate.setBaseTemplateId(0l);
            newPlanTemplate = PlanTemplateClient.createPlanTemplate(newPlanTemplate);
            return newPlanTemplate;

    }

    public static void delete(Long templateId) {
            PlanTemplate planTemplate = PlanTemplateClient.getPlanTemplate(templateId);
            deletePlanTemplateSections(templateId);
            deleteUnusedPlanSectionDefinitions(planTemplate);
            PlanTemplateClient.deletePlanTemplate(templateId);

    }

    private static void deletePlanTemplateSections(Long planTemplateId) {
            List<PlanTemplateSection> planTemplateSections =
                    PlanTemplateClient.getPlanTemplateSectionByPlanTemplateId(planTemplateId);
            for (PlanTemplateSection planTemplateSection : planTemplateSections) {
                PlanTemplateClient.deletePlanTemplateSection(planTemplateSection.getPlanTemplateId(),planTemplateSection.getPlanSectionId());
            }
    }

    private static void deleteUnusedPlanSectionDefinitions(PlanTemplate planTemplate) {
            List<PlanSectionDefinition> planSectionDefinitions = PlanTemplateClient
                    .getPlanSectionDefinitionByPlanTemplateId(planTemplate.getId_(),true);
            for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
                if (!isPlanSectionDefinitionUsedInOtherTemplate(planSectionDefinition.getId_(),
                        planTemplate.getId_())) {
                    PlanTemplateClient
                            .deletePlanSectionDefinition(planSectionDefinition.getId_());
                }
            }

    }

    public static boolean isPlanSectionDefinitionUsedInOtherTemplate(Long planSectionDefinitionId,
            Long planTemplateId) {
            List<PlanTemplateSection> planTemplateSections =
                    PlanTemplateClient
                            .getPlanTemplateSectionByPlanSectionDefinitionId(planSectionDefinitionId);
            return !(planTemplateSections.size() == 1
                    && planTemplateSections.get(0).getPlanTemplateId() == planTemplateId)
                    && !planTemplateSections.isEmpty();

    }
}
