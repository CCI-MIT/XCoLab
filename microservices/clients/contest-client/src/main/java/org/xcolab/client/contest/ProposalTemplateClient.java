package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.IProposalTemplateSection;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.proposals.exceptions.ProposalTemplateNotFoundException;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public class ProposalTemplateClient {

    private final RestResource1<IProposalTemplate, Long> proposalTemplateResource = null; // proposalTemplates
    private final RestResource1<ProposalTemplateSectionDefinitionWrapper, Long> proposalTemplateSectionDefinitionResource = null; // proposalTemplateSectionDefinitions
    private final RestResource1<IProposalTemplateSection, Long> proposalTemplateSectionResource = null; // proposalTemplateSections

    public IProposalTemplate getProposalTemplate(long id) {
        try {
            return proposalTemplateResource.get(id)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ProposalTemplateNotFoundException(id);
        }
    }

    public List<IProposalTemplate> getProposalTemplates() {
        return proposalTemplateResource.list()
                .execute();
    }

    public  Boolean deleteProposalTemplate(Long id) {
        return  proposalTemplateResource.delete(id).execute();
    }


    public IProposalTemplate createProposalTemplate(IProposalTemplate proposalTemplate) {
        return proposalTemplateResource.create(proposalTemplate)
                .execute();
    }

    public boolean updateProposalTemplate(IProposalTemplate proposalTemplate) {
        return proposalTemplateResource.update(proposalTemplate, proposalTemplate.getId())
                .execute();
    }

    public ProposalTemplateSectionDefinitionWrapper getProposalTemplateSectionDefinition(long id) {
        return proposalTemplateSectionDefinitionResource.get(id)
                .withCache(CacheName.MISC_REQUEST)
                .execute();
    }

    public boolean updateProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        return proposalTemplateSectionDefinitionResource.update(
                new ProposalTemplateSectionDefinitionWrapper(proposalTemplateSectionDefinition), proposalTemplateSectionDefinition.getId())
                .execute();
    }

    public ProposalTemplateSectionDefinitionWrapper createProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        return proposalTemplateSectionDefinitionResource
                .create(new ProposalTemplateSectionDefinitionWrapper(proposalTemplateSectionDefinition))
                .execute();
    }

    public List<ProposalTemplateSectionDefinitionWrapper> getProposalTemplateSectionDefinitionByProposalTemplateId(Long proposalTemplateId,
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

    public List<IProposalTemplateSection> getProposalTemplateSectionByProposalTemplateId(Long proposalTemplateId) {
        return proposalTemplateSectionResource.list()
                .optionalQueryParam("proposalTemplateId", proposalTemplateId)
                .execute();
    }
    public IProposalTemplateSection createProposalTemplateSection(
            IProposalTemplateSection proposalTemplateSection) {
        return proposalTemplateSectionResource.create(proposalTemplateSection)
                .execute();
    }

    public boolean updateProposalTemplateSection(IProposalTemplateSection proposalTemplateSection) {
        return proposalTemplateSectionResource.collectionService("updateTemplateSection",Boolean.class)
                .post(proposalTemplateSection);
    }

    public List<IProposalTemplateSection> getProposalTemplateSectionsByTemplateId(long proposalTemplateId) {
        return getProposalTemplateSections(proposalTemplateId, null);
    }

    public List<IProposalTemplateSection> getProposalTemplateSectionsBySectionDefinitionId(long sectionDefinitionId) {
        return getProposalTemplateSections(null, sectionDefinitionId);
    }

    private List<IProposalTemplateSection> getProposalTemplateSections(Long proposalTemplateId, Long sectionDefinitionId) {
        return proposalTemplateSectionResource.list()
                .optionalQueryParam("proposalTemplateId", proposalTemplateId)
                .optionalQueryParam("planSectionId", sectionDefinitionId)
                .execute();
    }

    public IProposalTemplateSection getProposalTemplateSection(long proposalTemplateId, long sectionDefinitionId) {
        return proposalTemplateSectionResource.list()
                .queryParam("proposalTemplateId", proposalTemplateId)
                .queryParam("planSectionId", sectionDefinitionId)
                .executeWithResult()
                .getOneIfExists();
    }
}
