package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.IProposalTemplateSection;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;

import java.util.List;

public final class ProposalTemplateClientUtil {

    private static final ProposalTemplateClient client = new ProposalTemplateClient();

    private ProposalTemplateClientUtil() {
    }

    public static ProposalTemplateClient getClient() {
        return client;
    }

    public static IProposalTemplate getProposalTemplate(long Id) {
        return client.getProposalTemplate(Id);
    }

    public static List<IProposalTemplate> getProposalTemplates() {
        return client.getProposalTemplates();
    }

    public static IProposalTemplate createProposalTemplate(
            IProposalTemplate proposalTemplate) {
        return client.createProposalTemplate(proposalTemplate);
    }

    public static  Boolean deleteProposalTemplate(Long id) {
        return  client.deleteProposalTemplate(id);
    }

    public static boolean updateProposalTemplate(IProposalTemplate proposalTemplate) {
        return client.updateProposalTemplate(proposalTemplate);
    }

    public static ProposalTemplateSectionDefinitionWrapper getProposalTemplateSectionDefinition(long id) {
        return client.getProposalTemplateSectionDefinition(id);
    }

    public static boolean updateProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        return client.updateProposalTemplateSectionDefinition(proposalTemplateSectionDefinition);
    }

    public static ProposalTemplateSectionDefinitionWrapper createProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        return client.createProposalTemplateSectionDefinition(proposalTemplateSectionDefinition);
    }

    public static IProposalTemplateSection createProposalTemplateSection(
            IProposalTemplateSection proposalTemplateSection) {
        return client.createProposalTemplateSection(proposalTemplateSection);
    }

    public static List<ProposalTemplateSectionDefinitionWrapper> getProposalTemplateSectionDefinitionByProposalTemplateId(
            Long proposalTemplateId, Boolean weight) {
        return client.getProposalTemplateSectionDefinitionByProposalTemplateId(proposalTemplateId, weight);
    }

    public static Boolean deleteProposalTemplateSectionDefinition(Long id) {
        return client.deleteProposalTemplateSectionDefinition(id);
    }

    public static Boolean deleteProposalTemplateSection(Long proposalTemplateId,
            Long proposalTemplateSectionDefinitionId) {
        return client.deleteProposalTemplateSection(proposalTemplateId, proposalTemplateSectionDefinitionId);
    }

    public static List<IProposalTemplateSection> getProposalTemplateSectionByProposalTemplateId(
            Long proposalTemplateId) {
        return client.getProposalTemplateSectionByProposalTemplateId(proposalTemplateId);
    }

    public static boolean updateProposalTemplateSection(
            IProposalTemplateSection proposalTemplateSection) {
        return client.updateProposalTemplateSection(proposalTemplateSection);
    }

    public static List<IProposalTemplateSection> getProposalTemplateSectionsByTemplateId(long proposalTemplateId) {
        return client.getProposalTemplateSectionsByTemplateId(proposalTemplateId);
    }

    public static List<IProposalTemplateSection> getProposalTemplateSectionsBySectionDefinitionId(long sectionDefinitionId) {
        return client.getProposalTemplateSectionsBySectionDefinitionId(sectionDefinitionId);
    }

    public static IProposalTemplateSection getProposalTemplateSection(Long proposalTemplateId, Long sectionDefinitionId) {
        return client.getProposalTemplateSection(proposalTemplateId, sectionDefinitionId);
    }
}
