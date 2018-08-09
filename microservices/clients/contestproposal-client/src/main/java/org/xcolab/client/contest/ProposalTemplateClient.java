package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDefinition;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDefinitionDto;
import org.xcolab.client.contest.pojo.templates.ProposalTemplate;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateDto;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSection;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDto;
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

public class ProposalTemplateClient {

    private static final Map<ServiceNamespace, ProposalTemplateClient> instances = new HashMap<>();

    private final ServiceNamespace serviceNamespace;

    private final RestResource1<ProposalTemplateDto, Long> planTemplateResource;
    private final RestResource1<ProposalTemplateSectionDefinitionDto, Long> planSectionDefinitionResource;
    private final RestResource1<ProposalTemplateSectionDto, Long> planTemplateSectionResource;

    private ProposalTemplateClient(ServiceNamespace serviceNamespace) {
        this.serviceNamespace = serviceNamespace;
        planTemplateResource =
                new RestResource1<>(ContestResource.PLAN_TEMPLATE, ProposalTemplateDto.TYPES,
                        serviceNamespace);
        planSectionDefinitionResource = new RestResource1<>(
                ContestResource.PLAN_SECTION_DEFINITION, ProposalTemplateSectionDefinitionDto.TYPES,
                serviceNamespace);
        planTemplateSectionResource = new RestResource1<>(
                ContestResource.PLAN_TEMPLATE_SECTION, ProposalTemplateSectionDto.TYPES,
                serviceNamespace);
    }

    public static ProposalTemplateClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, ProposalTemplateClient::new);
    }

    public ProposalTemplate getPlanTemplate(long id) {
        try {
            return planTemplateResource.get(id)
                    .executeChecked().toPojo(serviceNamespace);
        } catch (EntityNotFoundException e) {
            throw new PlanTemplateNotFoundException(id);
        }
    }

    public List<ProposalTemplate> getPlanTemplates() {
        return DtoUtil.toPojos(planTemplateResource.list()
                .execute(), serviceNamespace);
    }

    public  Boolean deletePlanTemplate(Long id) {
        return  planTemplateResource.delete(id).execute();
    }


    public ProposalTemplate createPlanTemplate(ProposalTemplate planTemplate) {
        return planTemplateResource.create(new ProposalTemplateDto(planTemplate))
                .execute().toPojo(serviceNamespace);
    }

    public boolean updatePlanTemplate(ProposalTemplate planTemplate) {
        return planTemplateResource.update(new ProposalTemplateDto(planTemplate), planTemplate.getId())
                .execute();
    }

    public ProposalTemplateSectionDefinition getPlanSectionDefinition(long id) {
        return planSectionDefinitionResource.get(id)
                .withCache(CacheName.MISC_REQUEST)
                .execute().toPojo(serviceNamespace);
    }

    public boolean updatePlanSectionDefinition(
            ProposalTemplateSectionDefinition planSectionDefinition) {
        return planSectionDefinitionResource.update(
                new ProposalTemplateSectionDefinitionDto(planSectionDefinition), planSectionDefinition.getId())
                .execute();
    }

    public ProposalTemplateSectionDefinition createPlanSectionDefinition(
            ProposalTemplateSectionDefinition planSectionDefinition) {
        return planSectionDefinitionResource
                .create(new ProposalTemplateSectionDefinitionDto(planSectionDefinition))
                .execute().toPojo(serviceNamespace);
    }

    public List<ProposalTemplateSectionDefinition> getPlanSectionDefinitionByPlanTemplateId(Long planTemplateId,
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
        return planTemplateSectionResource.collectionService("deletePlanTemplateSection", Boolean.class)
                .queryParam("planTemplateId", planTemplateId)
                .queryParam("planSectionDefinitionId", planSectionDefinitionId)
                .delete();
    }

    public List<ProposalTemplateSection> getPlanTemplateSectionByPlanTemplateId(Long planTemplateId) {
        return DtoUtil.toPojos(planTemplateSectionResource.list()
                .optionalQueryParam("planTemplateId", planTemplateId)
                .execute(), serviceNamespace);
    }
    public ProposalTemplateSection createPlanTemplateSection(
            ProposalTemplateSection planTemplateSection) {
        return planTemplateSectionResource.create(new ProposalTemplateSectionDto(planTemplateSection))
                .execute().toPojo(serviceNamespace);
    }

    public boolean updatePlanTemplateSection(ProposalTemplateSection planTemplateSection) {
        return planTemplateSectionResource.collectionService("updateTemplateSection",Boolean.class)
                .post(new ProposalTemplateSectionDto(planTemplateSection));
    }


    public List<ProposalTemplateSection> getProposalTemplateSectionsByTemplateId(long proposalTemplateId) {
        return getProposalTemplateSections(proposalTemplateId, null);
    }

    public List<ProposalTemplateSection> getProposalTemplateSectionsBySectionDefinitionId(long sectionDefinitionId) {
        return getProposalTemplateSections(null, sectionDefinitionId);
    }

    private List<ProposalTemplateSection> getProposalTemplateSections(Long proposalTemplateId, Long sectionDefinitionId) {
        return DtoUtil.toPojos(planTemplateSectionResource.list()
                .optionalQueryParam("planTemplateId", proposalTemplateId)
                .optionalQueryParam("planSectionId", sectionDefinitionId)
                .execute(), serviceNamespace);
    }

    public ProposalTemplateSection getProposalTemplateSection(long proposalTemplateId, long sectionDefinitionId) {
        return DtoUtil.toPojo(planTemplateSectionResource.list()
                .queryParam("planTemplateId", proposalTemplateId)
                .queryParam("planSectionId", sectionDefinitionId)
                .executeWithResult()
                .getOneIfExists(), serviceNamespace);
    }
}
