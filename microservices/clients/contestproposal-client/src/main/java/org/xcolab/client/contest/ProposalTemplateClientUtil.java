package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.templates.ProposalTemplate;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSection;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDefinition;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;

public final class ProposalTemplateClientUtil {

    private static final ProposalTemplateClient client = ProposalTemplateClient.fromNamespace(
            ServiceNamespace.instance());

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
        return client.getPlanSectionDefinition(id);
    }

    public static boolean updatePlanSectionDefinition(
            ProposalTemplateSectionDefinition planSectionDefinition) {
        return client.updatePlanSectionDefinition(planSectionDefinition);
    }

    public static ProposalTemplateSectionDefinition createPlanSectionDefinition(
            ProposalTemplateSectionDefinition planSectionDefinition) {
        return client.createPlanSectionDefinition(planSectionDefinition);
    }

    public static ProposalTemplateSection createProposalTemplateSection(
            ProposalTemplateSection proposalTemplateSection) {
        return client.createProposalTemplateSection(proposalTemplateSection);
    }

    public static List<ProposalTemplateSectionDefinition> getPlanSectionDefinitionByProposalTemplateId(
            Long proposalTemplateId, Boolean weight) {
        return client.getPlanSectionDefinitionByProposalTemplateId(proposalTemplateId, weight);
    }

    public static Boolean deletePlanSectionDefinition(Long id) {
        return client.deletePlanSectionDefinition(id);
    }

    public static Boolean deleteProposalTemplateSection(Long proposalTemplateId,
            Long planSectionDefinitionId) {
        return client.deleteProposalTemplateSection(proposalTemplateId, planSectionDefinitionId);
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
