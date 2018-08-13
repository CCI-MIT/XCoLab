package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDefinition;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDefinitionDto;
import org.xcolab.client.contest.pojo.templates.ProposalTemplate;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateDto;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSection;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDto;
import org.xcolab.client.contest.resources.ContestResource;
import org.xcolab.client.proposals.exceptions.ProposalTemplateNotFoundException;
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

    private final RestResource1<ProposalTemplateDto, Long> proposalTemplateResource;
    private final RestResource1<ProposalTemplateSectionDefinitionDto, Long> planSectionDefinitionResource;
    private final RestResource1<ProposalTemplateSectionDto, Long> proposalTemplateSectionResource;

    private ProposalTemplateClient(ServiceNamespace serviceNamespace) {
        this.serviceNamespace = serviceNamespace;
        proposalTemplateResource =
                new RestResource1<>(ContestResource.PLAN_TEMPLATE, ProposalTemplateDto.TYPES,
                        serviceNamespace);
        planSectionDefinitionResource = new RestResource1<>(
                ContestResource.PLAN_SECTION_DEFINITION, ProposalTemplateSectionDefinitionDto.TYPES,
                serviceNamespace);
        proposalTemplateSectionResource = new RestResource1<>(
                ContestResource.PLAN_TEMPLATE_SECTION, ProposalTemplateSectionDto.TYPES,
                serviceNamespace);
    }

    public static ProposalTemplateClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, ProposalTemplateClient::new);
    }

    public ProposalTemplate getProposalTemplate(long id) {
        try {
            return proposalTemplateResource.get(id)
                    .executeChecked().toPojo(serviceNamespace);
        } catch (EntityNotFoundException e) {
            throw new ProposalTemplateNotFoundException(id);
        }
    }

    public List<ProposalTemplate> getProposalTemplates() {
        return DtoUtil.toPojos(proposalTemplateResource.list()
                .execute(), serviceNamespace);
    }

    public  Boolean deleteProposalTemplate(Long id) {
        return  proposalTemplateResource.delete(id).execute();
    }


    public ProposalTemplate createProposalTemplate(ProposalTemplate proposalTemplate) {
        return proposalTemplateResource.create(new ProposalTemplateDto(proposalTemplate))
                .execute().toPojo(serviceNamespace);
    }

    public boolean updateProposalTemplate(ProposalTemplate proposalTemplate) {
        return proposalTemplateResource.update(new ProposalTemplateDto(proposalTemplate), proposalTemplate.getId())
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

    public List<ProposalTemplateSectionDefinition> getPlanSectionDefinitionByProposalTemplateId(Long proposalTemplateId,
            Boolean weight) {

        return DtoUtil.toPojos(planSectionDefinitionResource.list()
                .optionalQueryParam("proposalTemplateId", proposalTemplateId)
                .optionalQueryParam("weight", ((weight == null) ? (false) : weight))
                .execute(), serviceNamespace);
    }

    public Boolean deletePlanSectionDefinition(Long id) {
        return planSectionDefinitionResource.delete(id).execute();
    }

    public Boolean deleteProposalTemplateSection(Long proposalTemplateId, Long planSectionDefinitionId) {
        return proposalTemplateSectionResource.collectionService("deleteProposalTemplateSection", Boolean.class)
                .queryParam("proposalTemplateId", proposalTemplateId)
                .queryParam("planSectionDefinitionId", planSectionDefinitionId)
                .delete();
    }

    public List<ProposalTemplateSection> getProposalTemplateSectionByProposalTemplateId(Long proposalTemplateId) {
        return DtoUtil.toPojos(proposalTemplateSectionResource.list()
                .optionalQueryParam("proposalTemplateId", proposalTemplateId)
                .execute(), serviceNamespace);
    }
    public ProposalTemplateSection createProposalTemplateSection(
            ProposalTemplateSection proposalTemplateSection) {
        return proposalTemplateSectionResource.create(new ProposalTemplateSectionDto(proposalTemplateSection))
                .execute().toPojo(serviceNamespace);
    }

    public boolean updateProposalTemplateSection(ProposalTemplateSection proposalTemplateSection) {
        return proposalTemplateSectionResource.collectionService("updateTemplateSection",Boolean.class)
                .post(new ProposalTemplateSectionDto(proposalTemplateSection));
    }


    public List<ProposalTemplateSection> getProposalTemplateSectionsByTemplateId(long proposalTemplateId) {
        return getProposalTemplateSections(proposalTemplateId, null);
    }

    public List<ProposalTemplateSection> getProposalTemplateSectionsBySectionDefinitionId(long sectionDefinitionId) {
        return getProposalTemplateSections(null, sectionDefinitionId);
    }

    private List<ProposalTemplateSection> getProposalTemplateSections(Long proposalTemplateId, Long sectionDefinitionId) {
        return DtoUtil.toPojos(proposalTemplateSectionResource.list()
                .optionalQueryParam("proposalTemplateId", proposalTemplateId)
                .optionalQueryParam("planSectionId", sectionDefinitionId)
                .execute(), serviceNamespace);
    }

    public ProposalTemplateSection getProposalTemplateSection(long proposalTemplateId, long sectionDefinitionId) {
        return DtoUtil.toPojo(proposalTemplateSectionResource.list()
                .queryParam("proposalTemplateId", proposalTemplateId)
                .queryParam("planSectionId", sectionDefinitionId)
                .executeWithResult()
                .getOneIfExists(), serviceNamespace);
    }
}
