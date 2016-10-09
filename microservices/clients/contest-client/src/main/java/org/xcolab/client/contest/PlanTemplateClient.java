package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.client.contest.pojo.templates.PlanTemplateSection;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinitionDto;
import org.xcolab.client.contest.pojo.templates.PlanTemplateDto;
import org.xcolab.client.contest.pojo.templates.PlanTemplateSectionDto;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanTemplateClient {

    private static final Map<RestService, PlanTemplateClient> instances = new HashMap<>();

    private final RestService contestService;

    private final RestResource1<PlanTemplateDto, Long> planTemplateResource;
    private final RestResource1<PlanSectionDefinitionDto, Long> planSectionDefinitionResource;
    private final RestResource1<PlanTemplateSectionDto, Long> planTemplateSectionResource;

    private PlanTemplateClient(RestService contestService) {
        this.contestService = contestService;
        planTemplateResource =
                new RestResource1<>(this.contestService, "planTemplates", PlanTemplateDto.TYPES);
        planSectionDefinitionResource =
                new RestResource1<>(this.contestService, "planSectionDefinitions",
                        PlanSectionDefinitionDto.TYPES);
        planTemplateSectionResource =
                new RestResource1<>(this.contestService, "planTemplateSections",
                        PlanTemplateSectionDto.TYPES);
    }

    public static PlanTemplateClient fromService(RestService contestService) {
        PlanTemplateClient client = instances.get(contestService);
        if (client == null) {
            client = new PlanTemplateClient(contestService);
            instances.put(contestService, client);
        }
        return client;
    }

    public PlanTemplate getPlanTemplate(long Id_) {
        return planTemplateResource.get(Id_)
                .execute().toPojo(contestService);
    }

    public List<PlanTemplate> getPlanTemplates() {
        return DtoUtil.toPojos(planTemplateResource.list()
                .execute(), contestService);
    }

    public PlanTemplate createPlanTemplate(PlanTemplate planTemplate) {
        return planTemplateResource.create(new PlanTemplateDto(planTemplate))
                .execute().toPojo(contestService);
    }

    public boolean updatePlanTemplate(PlanTemplate planTemplate) {
        return planTemplateResource.update(new PlanTemplateDto(planTemplate), planTemplate.getId_())
                .execute();
    }

    public PlanSectionDefinition getPlanSectionDefinition(long id) {
        return planSectionDefinitionResource.get(id)
                .execute().toPojo(contestService);
    }

    public boolean updatePlanSectionDefinition(PlanSectionDefinition planSectionDefinition) {
        return planSectionDefinitionResource.update(
                new PlanSectionDefinitionDto(planSectionDefinition), planSectionDefinition.getId_())
                .execute();
    }

    public PlanSectionDefinition createPlanSectionDefinition(
            PlanSectionDefinition planSectionDefinition) {
        return planSectionDefinitionResource
                .create(new PlanSectionDefinitionDto(planSectionDefinition))
                .execute().toPojo(contestService);
    }

    public List<PlanSectionDefinition> getPlanSectionDefinitionByPlanTemplateId(Long planTemplateId,
            Boolean weight) {

        return DtoUtil.toPojos(planSectionDefinitionResource.list()
                .optionalQueryParam("planTemplateId", planTemplateId)
                .optionalQueryParam("weight", ((weight == null) ? (false) : weight))
                .execute(), contestService);
    }

    public Boolean deletePlanSectionDefinition(Long id) {
        return planSectionDefinitionResource.delete(id).execute();
    }

    public Boolean deletePlanTemplateSection(Long planTemplateId, Long planSectionDefinitionId) {
        return planTemplateSectionResource.service("deletePlanTemplateSection", Boolean.class)
                .queryParam("planTemplateId", planTemplateId)
                .queryParam("planSectionDefinitionId", planSectionDefinitionId)
                .delete();
    }

    public List<PlanTemplateSection> getPlanTemplateSectionByPlanTemplateId(Long planTemplateId) {
        return DtoUtil.toPojos(planTemplateSectionResource.list()
                .optionalQueryParam("planTemplateId", planTemplateId)
                .execute(), contestService);
    }

    public boolean updatePlanTemplateSection(PlanTemplateSection planTemplateSection) {
        return planTemplateSectionResource.update(new PlanTemplateSectionDto(planTemplateSection),
                planTemplateSection.getPlanTemplateId())
                .execute();
    }

    public List<PlanTemplateSection> getPlanTemplateSectionByPlanSectionDefinitionId(
            Long planSectionId) {
        return DtoUtil.toPojos(planTemplateSectionResource.list()
                .optionalQueryParam("planSectionId", planSectionId)
                .execute(), contestService);
    }
}
