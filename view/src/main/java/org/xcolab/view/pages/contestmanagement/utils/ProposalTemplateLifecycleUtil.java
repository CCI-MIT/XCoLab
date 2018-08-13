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
        ProposalTemplate newProposalTemplate = new ProposalTemplate();
        newProposalTemplate.setName(name);
        newProposalTemplate.setImpactSeriesTemplateId(1L);
        newProposalTemplate.setBaseTemplateId(0L);
        newProposalTemplate = ProposalTemplateClientUtil.createProposalTemplate(newProposalTemplate);
        return newProposalTemplate;
    }

    public static void delete(Long templateId) {
        ProposalTemplate proposalTemplate = ProposalTemplateClientUtil.getProposalTemplate(templateId);
        deleteProposalTemplateSections(templateId);
        deleteUnusedPlanSectionDefinitions(proposalTemplate);
        ProposalTemplateClientUtil.deleteProposalTemplate(templateId);

    }

    private static void deleteProposalTemplateSections(Long proposalTemplateId) {
        List<ProposalTemplateSection> proposalTemplateSections =
                ProposalTemplateClientUtil.getProposalTemplateSectionByProposalTemplateId(proposalTemplateId);
        for (ProposalTemplateSection proposalTemplateSection : proposalTemplateSections) {
            ProposalTemplateClientUtil
                    .deleteProposalTemplateSection(proposalTemplateSection.getProposalTemplateId(),
                            proposalTemplateSection.getSectionDefinitionId());
        }
    }

    private static void deleteUnusedPlanSectionDefinitions(ProposalTemplate proposalTemplate) {
        List<ProposalTemplateSectionDefinition> planSectionDefinitions = ProposalTemplateClientUtil
                .getPlanSectionDefinitionByProposalTemplateId(proposalTemplate.getId(), true);
        for (ProposalTemplateSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if (!isPlanSectionDefinitionUsedInOtherTemplate(planSectionDefinition.getId(),
                    proposalTemplate.getId())) {
                ProposalTemplateClientUtil
                        .deletePlanSectionDefinition(planSectionDefinition.getId());
            }
        }

    }

    public static boolean isPlanSectionDefinitionUsedInOtherTemplate(Long planSectionDefinitionId,
            Long proposalTemplateId) {
        List<ProposalTemplateSection> proposalTemplateSections =
                ProposalTemplateClientUtil
                        .getProposalTemplateSectionsBySectionDefinitionId(planSectionDefinitionId);
        return !(proposalTemplateSections.size() == 1
                && proposalTemplateSections.get(0).getProposalTemplateId() == proposalTemplateId.longValue())
                && !proposalTemplateSections.isEmpty();

    }
}
