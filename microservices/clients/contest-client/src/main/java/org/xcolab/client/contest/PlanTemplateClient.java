package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.PlanTemplate;
import org.xcolab.client.contest.pojo.PlanTemplateSection;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class PlanTemplateClient {

    private static final RestService contestService = new RestService("contest-service");

    private static final RestResource1<PlanTemplate,Long> planTemplateResource = new RestResource1<>(contestService,
            "planTemplates", PlanTemplate.TYPES);

    private static final RestResource1<PlanSectionDefinition,Long> planSectionDefinitionResource = new RestResource1<>(contestService,
            "planSectionDefinitions", PlanSectionDefinition.TYPES);

    private static final RestResource1<PlanTemplateSection,Long> planTemplateSectionResource = new RestResource1<>(contestService,
            "planTemplateSections", PlanTemplateSection.TYPES);

    public static PlanTemplate getPlanTemplate(long Id_) {
        return planTemplateResource.get(Id_)
                .execute();
    }

    public static List<PlanTemplate> getPlanTemplates() {
        return planTemplateResource.list()
                .execute();
    }

    public static PlanTemplate createPlanTemplate(PlanTemplate planTemplate) {
        return planTemplateResource.create(planTemplate).execute();
    }

    public static boolean updatePlanTemplate(PlanTemplate planTemplate) {
        return planTemplateResource.update(planTemplate, planTemplate.getId_())
                .execute();
    }

    public static PlanSectionDefinition getPlanSectionDefinition(long Id_) {
        return planSectionDefinitionResource.get(Id_)
                .execute();


    }

    public static boolean updatePlanSectionDefinition(PlanSectionDefinition planSectionDefinition) {
        return planSectionDefinitionResource.update(planSectionDefinition, planSectionDefinition.getId_())
                .execute();
    }

    public static PlanSectionDefinition createPlanSectionDefinition(PlanSectionDefinition planSectionDefinition) {
        return planSectionDefinitionResource.create(planSectionDefinition).execute();
    }

    public static List<PlanSectionDefinition> getPlanSectionDefinitionByPlanTemplateId(Long planTemplateId, Boolean weight) {

        return planSectionDefinitionResource.list()
                .optionalQueryParam("planTemplateId", planTemplateId)
                .optionalQueryParam("weight", ((weight == null) ? (false) : weight))
                .execute();
    }

    public static Boolean deletePlanSectionDefinition(Long id_) {
        return planSectionDefinitionResource.delete(id_).execute();
    }

    public static Boolean deletePlanTemplateSection(Long planTemplateId, Long planSectionDefinitionId) {
        return planTemplateSectionResource.service("deletePlanTemplateSection", Boolean.class)
                .queryParam("planTemplateId", planTemplateId)
                .queryParam("planSectionDefinitionId", planSectionDefinitionId)
                .delete();
    }

    public static List<PlanTemplateSection> getPlanTemplateSectionByPlanTemplateId(Long planTemplateId) {
        return planTemplateSectionResource.list()
                .optionalQueryParam("planTemplateId", planTemplateId)
                .execute();
    }

    public static boolean updatePlanTemplateSection(PlanTemplateSection planTemplateSection) {
        return planTemplateSectionResource.update(planTemplateSection, planTemplateSection.getPlanTemplateId())
                .execute();
    }

    public static List<PlanTemplateSection> getPlanTemplateSectionByPlanSectionDefinitionId(Long planSectionId) {
        return planTemplateSectionResource.list()
                .optionalQueryParam("planSectionId", planSectionId)
                .execute();
    }
}
