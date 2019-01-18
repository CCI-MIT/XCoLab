package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.contest.ProposalTemplateClientUtil;
import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.IProposalTemplateSection;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalTemplate;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;

import java.util.List;

public final class ProposalTemplateLifecycleUtil {

    private static final String DEFAULT_TEMPLATE_NAME = "New template";

    private ProposalTemplateLifecycleUtil() { }

    public static IProposalTemplate create() {
        return create(DEFAULT_TEMPLATE_NAME);
    }

    public static IProposalTemplate create(String name) {
        IProposalTemplate newProposalTemplate = new ProposalTemplate();
        newProposalTemplate.setName(name);
        newProposalTemplate.setImpactSeriesTemplateId(1L);
        newProposalTemplate.setBaseTemplateId(0L);
        newProposalTemplate = ProposalTemplateClientUtil.createProposalTemplate(newProposalTemplate);
        return newProposalTemplate;
    }

    public static void delete(Long templateId) {
        IProposalTemplate proposalTemplate = ProposalTemplateClientUtil.getProposalTemplate(templateId);
        deleteProposalTemplateSections(templateId);
        deleteUnusedProposalTemplateSectionDefinitions(proposalTemplate);
        ProposalTemplateClientUtil.deleteProposalTemplate(templateId);

    }

    private static void deleteProposalTemplateSections(Long proposalTemplateId) {
        List<IProposalTemplateSection> proposalTemplateSections =
                ProposalTemplateClientUtil.getProposalTemplateSectionByProposalTemplateId(proposalTemplateId);
        for (IProposalTemplateSection proposalTemplateSection : proposalTemplateSections) {
            ProposalTemplateClientUtil
                    .deleteProposalTemplateSection(proposalTemplateSection.getProposalTemplateId(),
                            proposalTemplateSection.getSectionDefinitionId());
        }
    }

    private static void deleteUnusedProposalTemplateSectionDefinitions(
            IProposalTemplate proposalTemplate) {
        List<ProposalTemplateSectionDefinitionWrapper> proposalTemplateSectionDefinitions = ProposalTemplateClientUtil
                .getProposalTemplateSectionDefinitionByProposalTemplateId(proposalTemplate.getId(), true);
        for (ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition : proposalTemplateSectionDefinitions) {
            if (!isProposalTemplateSectionDefinitionUsedInOtherTemplate(proposalTemplateSectionDefinition.getId(),
                    proposalTemplate.getId())) {
                ProposalTemplateClientUtil
                        .deleteProposalTemplateSectionDefinition(proposalTemplateSectionDefinition.getId());
            }
        }

    }

    public static boolean isProposalTemplateSectionDefinitionUsedInOtherTemplate(Long proposalTemplateSectionDefinitionId,
            Long proposalTemplateId) {
        List<IProposalTemplateSection> proposalTemplateSections =
                ProposalTemplateClientUtil
                        .getProposalTemplateSectionsBySectionDefinitionId(proposalTemplateSectionDefinitionId);
        return !(proposalTemplateSections.size() == 1
                && proposalTemplateSections.get(0).getProposalTemplateId() == proposalTemplateId.longValue())
                && !proposalTemplateSections.isEmpty();

    }
}
