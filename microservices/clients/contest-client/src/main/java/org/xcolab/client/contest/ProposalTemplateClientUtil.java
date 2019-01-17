package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.ProposalTemplate;
import org.xcolab.client.contest.pojo.ProposalTemplateSection;
import org.xcolab.client.contest.pojo.ProposalTemplateSectionDefinition;

import java.util.List;

public final class ProposalTemplateClientUtil {

    private static final ProposalTemplateClient client = new ProposalTemplateClient();

    private ProposalTemplateClientUtil() {
    }

    public static ProposalTemplateClient getClient() {
        return client;
    }

    public static ProposalTemplate getProposalTemplate(long Id) {
        return client.getProposalTemplate(Id);
    }

    public static List<ProposalTemplate> getProposalTemplates() {
        return client.getProposalTemplates();
    }

    public static ProposalTemplate createProposalTemplate(
            ProposalTemplate proposalTemplate) {
        return client.createProposalTemplate(proposalTemplate);
    }

    public static  Boolean deleteProposalTemplate(Long id) {
        return  client.deleteProposalTemplate(id);
    }

    public static boolean updateProposalTemplate(ProposalTemplate proposalTemplate) {
        return client.updateProposalTemplate(proposalTemplate);
    }

    public static ProposalTemplateSectionDefinition getProposalTemplateSectionDefinition(long id) {
        return client.getProposalTemplateSectionDefinition(id);
    }

    public static boolean updateProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinition proposalTemplateSectionDefinition) {
        return client.updateProposalTemplateSectionDefinition(proposalTemplateSectionDefinition);
    }

    public static ProposalTemplateSectionDefinition createProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinition proposalTemplateSectionDefinition) {
        return client.createProposalTemplateSectionDefinition(proposalTemplateSectionDefinition);
    }

    public static ProposalTemplateSection createProposalTemplateSection(
            ProposalTemplateSection proposalTemplateSection) {
        return client.createProposalTemplateSection(proposalTemplateSection);
    }

    public static List<ProposalTemplateSectionDefinition> getProposalTemplateSectionDefinitionByProposalTemplateId(
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

    public static List<ProposalTemplateSection> getProposalTemplateSectionByProposalTemplateId(
            Long proposalTemplateId) {
        return client.getProposalTemplateSectionByProposalTemplateId(proposalTemplateId);
    }

    public static boolean updateProposalTemplateSection(
            ProposalTemplateSection proposalTemplateSection) {
        return client.updateProposalTemplateSection(proposalTemplateSection);
    }

    public static List<ProposalTemplateSection> getProposalTemplateSectionsByTemplateId(long proposalTemplateId) {
        return client.getProposalTemplateSectionsByTemplateId(proposalTemplateId);
    }

    public static List<ProposalTemplateSection> getProposalTemplateSectionsBySectionDefinitionId(long sectionDefinitionId) {
        return client.getProposalTemplateSectionsBySectionDefinitionId(sectionDefinitionId);
    }

    public static ProposalTemplateSection getProposalTemplateSection(Long proposalTemplateId, Long sectionDefinitionId) {
        return client.getProposalTemplateSection(proposalTemplateId, sectionDefinitionId);
    }
}
