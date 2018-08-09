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

    public static ProposalTemplate getPlanTemplate(long Id) {
        return client.getPlanTemplate(Id);
    }

    public static List<ProposalTemplate> getPlanTemplates() {
        return client.getPlanTemplates();
    }

    public static ProposalTemplate createPlanTemplate(
            ProposalTemplate planTemplate) {
        return client.createPlanTemplate(planTemplate);
    }

    public static  Boolean deletePlanTemplate(Long id) {
        return  client.deletePlanTemplate(id);
    }

    public static boolean updatePlanTemplate(ProposalTemplate planTemplate) {
        return client.updatePlanTemplate(planTemplate);
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

    public static ProposalTemplateSection createPlanTemplateSection(
            ProposalTemplateSection planTemplateSection) {
        return client.createPlanTemplateSection(planTemplateSection);
    }

    public static List<ProposalTemplateSectionDefinition> getPlanSectionDefinitionByPlanTemplateId(
            Long planTemplateId, Boolean weight) {
        return client.getPlanSectionDefinitionByPlanTemplateId(planTemplateId, weight);
    }

    public static Boolean deletePlanSectionDefinition(Long id) {
        return client.deletePlanSectionDefinition(id);
    }

    public static Boolean deletePlanTemplateSection(Long planTemplateId,
            Long planSectionDefinitionId) {
        return client.deletePlanTemplateSection(planTemplateId, planSectionDefinitionId);
    }

    public static List<ProposalTemplateSection> getPlanTemplateSectionByPlanTemplateId(
            Long planTemplateId) {
        return client.getPlanTemplateSectionByPlanTemplateId(planTemplateId);
    }

    public static boolean updatePlanTemplateSection(
            ProposalTemplateSection planTemplateSection) {
        return client.updatePlanTemplateSection(planTemplateSection);
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
