package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.client.contest.pojo.templates.PlanTemplateSection;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class PlanTemplateClientUtil {

    private static final RestService contestService = new RestService("contest-service");

    private static final PlanTemplateClient client = PlanTemplateClient.fromService(contestService);

    private PlanTemplateClientUtil() {
    }

    public static PlanTemplateClient getClient() {
        return client;
    }

    public static PlanTemplate getPlanTemplate(long Id_) {
        return client.getPlanTemplate(Id_);
    }

    public static List<PlanTemplate> getPlanTemplates() {
        return client.getPlanTemplates();
    }

    public static PlanTemplate createPlanTemplate(
            PlanTemplate planTemplate) {
        return client.createPlanTemplate(planTemplate);
    }

    public static boolean updatePlanTemplate(PlanTemplate planTemplate) {
        return client.updatePlanTemplate(planTemplate);
    }

    public static PlanSectionDefinition getPlanSectionDefinition(long id) {
        return client.getPlanSectionDefinition(id);
    }

    public static boolean updatePlanSectionDefinition(
            PlanSectionDefinition planSectionDefinition) {
        return client.updatePlanSectionDefinition(planSectionDefinition);
    }

    public static PlanSectionDefinition createPlanSectionDefinition(
            PlanSectionDefinition planSectionDefinition) {
        return client.createPlanSectionDefinition(planSectionDefinition);
    }

    public static List<PlanSectionDefinition> getPlanSectionDefinitionByPlanTemplateId(
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

    public static List<PlanTemplateSection> getPlanTemplateSectionByPlanTemplateId(
            Long planTemplateId) {
        return client.getPlanTemplateSectionByPlanTemplateId(planTemplateId);
    }

    public static boolean updatePlanTemplateSection(
            PlanTemplateSection planTemplateSection) {
        return client.updatePlanTemplateSection(planTemplateSection);
    }

    public static List<PlanTemplateSection> getPlanTemplateSectionByPlanSectionDefinitionId(
            Long planSectionId) {
        return client.getPlanTemplateSectionByPlanSectionDefinitionId(planSectionId);
    }
}
