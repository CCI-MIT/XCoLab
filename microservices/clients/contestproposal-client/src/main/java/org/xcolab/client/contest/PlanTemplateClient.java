package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinitionDto;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.client.contest.pojo.templates.PlanTemplateDto;
import org.xcolab.client.contest.pojo.templates.PlanTemplateSection;
import org.xcolab.client.contest.pojo.templates.PlanTemplateSectionDto;
import org.xcolab.client.contest.resources.ContestResource;
import org.xcolab.client.proposals.exceptions.PlanTemplateNotFoundException;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanTemplateClient {

    private static final Map<ServiceNamespace, PlanTemplateClient> instances = new HashMap<>();

    private final ServiceNamespace serviceNamespace;

    private final RestResource1<PlanTemplateDto, Long> planTemplateResource;
    private final RestResource1<PlanSectionDefinitionDto, Long> planSectionDefinitionResource;
    private final RestResource1<PlanTemplateSectionDto, Long> planTemplateSectionResource;

    private PlanTemplateClient(ServiceNamespace serviceNamespace) {
        this.serviceNamespace = serviceNamespace;
        planTemplateResource =
                new RestResource1<>(ContestResource.PLAN_TEMPLATE, PlanTemplateDto.TYPES);
        planSectionDefinitionResource = new RestResource1<>(
                ContestResource.PLAN_SECTION_DEFINITION, PlanSectionDefinitionDto.TYPES);
        planTemplateSectionResource = new RestResource1<>(
                ContestResource.PLAN_TEMPLATE_SECTION, PlanTemplateSectionDto.TYPES);
    }

    public static PlanTemplateClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, PlanTemplateClient::new);
    }

    public PlanTemplate getPlanTemplate(long id) {
        try {
            return planTemplateResource.get(id)
                    .executeChecked().toPojo(serviceNamespace);
        } catch (EntityNotFoundException e) {
            throw new PlanTemplateNotFoundException(id);
        }
    }

    public List<PlanTemplate> getPlanTemplates() {
        return DtoUtil.toPojos(planTemplateResource.list()
                .execute(), serviceNamespace);
    }

    public  Boolean deletePlanTemplate(Long id_) {
        return  planTemplateResource.delete(id_).execute();
    }


    public PlanTemplate createPlanTemplate(PlanTemplate planTemplate) {
        return planTemplateResource.create(new PlanTemplateDto(planTemplate))
                .execute().toPojo(serviceNamespace);
    }

    public boolean updatePlanTemplate(PlanTemplate planTemplate) {
        return planTemplateResource.update(new PlanTemplateDto(planTemplate), planTemplate.getId_())
                .execute();
    }

    public PlanSectionDefinition getPlanSectionDefinition(long id) {
        return planSectionDefinitionResource.get(id)
                .withCache(CacheName.MISC_REQUEST)
                .execute().toPojo(serviceNamespace);
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
                .execute().toPojo(serviceNamespace);
    }

    public List<PlanSectionDefinition> getPlanSectionDefinitionByPlanTemplateId(Long planTemplateId,
            Boolean weight) {

        return DtoUtil.toPojos(planSectionDefinitionResource.list()
                .optionalQueryParam("planTemplateId", planTemplateId)
                .optionalQueryParam("weight", ((weight == null) ? (false) : weight))
                .execute(), serviceNamespace);
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
                .execute(), serviceNamespace);
    }
    public  PlanTemplateSection createPlanTemplateSection(PlanTemplateSection planTemplateSection) {
        return planTemplateSectionResource.create(new PlanTemplateSectionDto(planTemplateSection))
                .execute().toPojo(serviceNamespace);
    }

    public boolean updatePlanTemplateSection(PlanTemplateSection planTemplateSection) {
        return planTemplateSectionResource.service("updateTemplateSection",Boolean.class)
                .post(new PlanTemplateSectionDto(planTemplateSection));
    }

    public List<PlanTemplateSection> getPlanTemplateSectionByPlanSectionDefinitionId(Long planSectionId) {
        return DtoUtil.toPojos(planTemplateSectionResource.list()
                .optionalQueryParam("planSectionId", planSectionId)
                .execute(), serviceNamespace);
    }
}
