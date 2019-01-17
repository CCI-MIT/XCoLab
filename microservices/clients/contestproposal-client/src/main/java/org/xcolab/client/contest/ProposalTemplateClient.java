package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.ProposalTemplate;
import org.xcolab.client.contest.pojo.ProposalTemplateSection;
import org.xcolab.client.contest.pojo.ProposalTemplateSectionDefinition;
import org.xcolab.client.contest.resources.ContestResource;
import org.xcolab.client.contest.proposals.exceptions.ProposalTemplateNotFoundException;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public class ProposalTemplateClient {

    private final RestResource1<ProposalTemplate, Long> proposalTemplateResource;
    private final RestResource1<ProposalTemplateSectionDefinition, Long> proposalTemplateSectionDefinitionResource;
    private final RestResource1<ProposalTemplateSection, Long> proposalTemplateSectionResource;

    public ProposalTemplateClient() {
        proposalTemplateResource =
                new RestResource1<>(ContestResource.PLAN_TEMPLATE, ProposalTemplate.TYPES);
        proposalTemplateSectionDefinitionResource = new RestResource1<>(
                ContestResource.PLAN_SECTION_DEFINITION, ProposalTemplateSectionDefinition.TYPES);
        proposalTemplateSectionResource = new RestResource1<>(
                ContestResource.PLAN_TEMPLATE_SECTION, ProposalTemplateSection.TYPES);
    }

    public ProposalTemplate getProposalTemplate(long id) {
        try {
            return proposalTemplateResource.get(id)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ProposalTemplateNotFoundException(id);
        }
    }

    public List<ProposalTemplate> getProposalTemplates() {
        return proposalTemplateResource.list()
                .execute();
    }

    public  Boolean deleteProposalTemplate(Long id) {
        return  proposalTemplateResource.delete(id).execute();
    }


    public ProposalTemplate createProposalTemplate(ProposalTemplate proposalTemplate) {
        return proposalTemplateResource.create(new ProposalTemplate(proposalTemplate))
                .execute();
    }

    public boolean updateProposalTemplate(ProposalTemplate proposalTemplate) {
        return proposalTemplateResource.update(new ProposalTemplate(proposalTemplate), proposalTemplate.getId())
                .execute();
    }

    public ProposalTemplateSectionDefinition getProposalTemplateSectionDefinition(long id) {
        return proposalTemplateSectionDefinitionResource.get(id)
                .withCache(CacheName.MISC_REQUEST)
                .execute();
    }

    public boolean updateProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinition proposalTemplateSectionDefinition) {
        return proposalTemplateSectionDefinitionResource.update(
                new ProposalTemplateSectionDefinition(proposalTemplateSectionDefinition), proposalTemplateSectionDefinition.getId())
                .execute();
    }

    public ProposalTemplateSectionDefinition createProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinition proposalTemplateSectionDefinition) {
        return proposalTemplateSectionDefinitionResource
                .create(new ProposalTemplateSectionDefinition(proposalTemplateSectionDefinition))
                .execute();
    }

    public List<ProposalTemplateSectionDefinition> getProposalTemplateSectionDefinitionByProposalTemplateId(Long proposalTemplateId,
            Boolean weight) {

        return proposalTemplateSectionDefinitionResource.list()
                .optionalQueryParam("proposalTemplateId", proposalTemplateId)
                .optionalQueryParam("weight", ((weight == null) ? (false) : weight))
                .execute();
    }

    public Boolean deleteProposalTemplateSectionDefinition(Long id) {
        return proposalTemplateSectionDefinitionResource.delete(id).execute();
    }

    public Boolean deleteProposalTemplateSection(Long proposalTemplateId, Long proposalTemplateSectionDefinitionId) {
        return proposalTemplateSectionResource.collectionService("deleteProposalTemplateSection", Boolean.class)
                .queryParam("proposalTemplateId", proposalTemplateId)
                .queryParam("proposalTemplateSectionDefinitionId", proposalTemplateSectionDefinitionId)
                .delete();
    }

    public List<ProposalTemplateSection> getProposalTemplateSectionByProposalTemplateId(Long proposalTemplateId) {
        return proposalTemplateSectionResource.list()
                .optionalQueryParam("proposalTemplateId", proposalTemplateId)
                .execute();
    }
    public ProposalTemplateSection createProposalTemplateSection(
            ProposalTemplateSection proposalTemplateSection) {
        return proposalTemplateSectionResource.create(new ProposalTemplateSection(proposalTemplateSection))
                .execute();
    }

    public boolean updateProposalTemplateSection(ProposalTemplateSection proposalTemplateSection) {
        return proposalTemplateSectionResource.collectionService("updateTemplateSection",Boolean.class)
                .post(new ProposalTemplateSection(proposalTemplateSection));
    }


    public List<ProposalTemplateSection> getProposalTemplateSectionsByTemplateId(long proposalTemplateId) {
        return getProposalTemplateSections(proposalTemplateId, null);
    }

    public List<ProposalTemplateSection> getProposalTemplateSectionsBySectionDefinitionId(long sectionDefinitionId) {
        return getProposalTemplateSections(null, sectionDefinitionId);
    }

    private List<ProposalTemplateSection> getProposalTemplateSections(Long proposalTemplateId, Long sectionDefinitionId) {
        return proposalTemplateSectionResource.list()
                .optionalQueryParam("proposalTemplateId", proposalTemplateId)
                .optionalQueryParam("planSectionId", sectionDefinitionId)
                .execute();
    }

    public ProposalTemplateSection getProposalTemplateSection(long proposalTemplateId, long sectionDefinitionId) {
        return proposalTemplateSectionResource.list()
                .queryParam("proposalTemplateId", proposalTemplateId)
                .queryParam("planSectionId", sectionDefinitionId)
                .executeWithResult()
                .getOneIfExists();
    }
}
