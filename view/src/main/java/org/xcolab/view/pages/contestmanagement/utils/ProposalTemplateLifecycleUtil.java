package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.contest.ProposalTemplateClientUtil;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDefinition;
import org.xcolab.client.contest.pojo.templates.ProposalTemplate;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSection;

import java.util.List;

public final class ProposalTemplateLifecycleUtil {

    private static final String DEFAULT_TEMPLATE_NAME = "New template";

    private ProposalTemplateLifecycleUtil() { }

    public static ProposalTemplate create() {
        return create(DEFAULT_TEMPLATE_NAME);
    }

    public static ProposalTemplate create(String name) {
        ProposalTemplate newPlanTemplate = new ProposalTemplate();
        newPlanTemplate.setName(name);
        newPlanTemplate.setImpactSeriesTemplateId(1L);
        newPlanTemplate.setBaseTemplateId(0L);
        newPlanTemplate = ProposalTemplateClientUtil.createPlanTemplate(newPlanTemplate);
        return newPlanTemplate;
    }

    public static void delete(Long templateId) {
        ProposalTemplate planTemplate = ProposalTemplateClientUtil.getPlanTemplate(templateId);
        deletePlanTemplateSections(templateId);
        deleteUnusedPlanSectionDefinitions(planTemplate);
        ProposalTemplateClientUtil.deletePlanTemplate(templateId);

    }

    private static void deletePlanTemplateSections(Long planTemplateId) {
        List<ProposalTemplateSection> planTemplateSections =
                ProposalTemplateClientUtil.getPlanTemplateSectionByPlanTemplateId(planTemplateId);
        for (ProposalTemplateSection planTemplateSection : planTemplateSections) {
            ProposalTemplateClientUtil
                    .deletePlanTemplateSection(planTemplateSection.getProposalTemplateId(),
                            planTemplateSection.getSectionDefinitionId());
        }
    }

    private static void deleteUnusedPlanSectionDefinitions(ProposalTemplate planTemplate) {
        List<ProposalTemplateSectionDefinition> planSectionDefinitions = ProposalTemplateClientUtil
                .getPlanSectionDefinitionByPlanTemplateId(planTemplate.getId(), true);
        for (ProposalTemplateSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if (!isPlanSectionDefinitionUsedInOtherTemplate(planSectionDefinition.getId(),
                    planTemplate.getId())) {
                ProposalTemplateClientUtil
                        .deletePlanSectionDefinition(planSectionDefinition.getId());
            }
        }

    }

    public static boolean isPlanSectionDefinitionUsedInOtherTemplate(Long planSectionDefinitionId,
            Long planTemplateId) {
        List<ProposalTemplateSection> planTemplateSections =
                ProposalTemplateClientUtil
                        .getProposalTemplateSectionsBySectionDefinitionId(planSectionDefinitionId);
        return !(planTemplateSections.size() == 1
                && planTemplateSections.get(0).getProposalTemplateId() == planTemplateId.longValue())
                && !planTemplateSections.isEmpty();

    }
}
