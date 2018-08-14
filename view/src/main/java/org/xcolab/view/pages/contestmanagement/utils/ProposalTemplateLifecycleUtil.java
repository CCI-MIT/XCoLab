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
        deleteUnusedProposalTemplateSectionDefinitions(proposalTemplate);
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

    private static void deleteUnusedProposalTemplateSectionDefinitions(ProposalTemplate proposalTemplate) {
        List<ProposalTemplateSectionDefinition> proposalTemplateSectionDefinitions = ProposalTemplateClientUtil
                .getProposalTemplateSectionDefinitionByProposalTemplateId(proposalTemplate.getId(), true);
        for (ProposalTemplateSectionDefinition proposalTemplateSectionDefinition : proposalTemplateSectionDefinitions) {
            if (!isProposalTemplateSectionDefinitionUsedInOtherTemplate(proposalTemplateSectionDefinition.getId(),
                    proposalTemplate.getId())) {
                ProposalTemplateClientUtil
                        .deleteProposalTemplateSectionDefinition(proposalTemplateSectionDefinition.getId());
            }
        }

    }

    public static boolean isProposalTemplateSectionDefinitionUsedInOtherTemplate(Long proposalTemplateSectionDefinitionId,
            Long proposalTemplateId) {
        List<ProposalTemplateSection> proposalTemplateSections =
                ProposalTemplateClientUtil
                        .getProposalTemplateSectionsBySectionDefinitionId(proposalTemplateSectionDefinitionId);
        return !(proposalTemplateSections.size() == 1
                && proposalTemplateSections.get(0).getProposalTemplateId() == proposalTemplateId.longValue())
                && !proposalTemplateSections.isEmpty();

    }
}
