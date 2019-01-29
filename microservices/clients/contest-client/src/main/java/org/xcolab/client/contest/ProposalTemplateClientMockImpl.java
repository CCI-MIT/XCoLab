package org.xcolab.client.contest;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.IProposalTemplateSection;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ProposalTemplateClientMockImpl implements IProposalTemplateClient {

    @Override
    public IProposalTemplate getProposalTemplate(Long proposalTemplateId) {
        return null;
    }

    @Override
    public List<IProposalTemplate> getProposalTemplates() {
        return Collections.emptyList();
    }

    @Override
    public boolean deleteProposalTemplate(Long id) {
        return false;
    }

    @Override
    public IProposalTemplate createProposalTemplate(IProposalTemplate proposalTemplate) {
        return null;
    }

    @Override
    public boolean updateProposalTemplate(IProposalTemplate proposalTemplate) {
        return false;
    }

    @Override
    public ProposalTemplateSectionDefinitionWrapper getProposalTemplateSectionDefinition(
            Long proposalTemplateSectionDefinitionId) {
        return null;
    }

    @Override
    public boolean updateProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        return false;
    }

    @Override
    public ProposalTemplateSectionDefinitionWrapper createProposalTemplateSectionDefinition(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        return null;
    }

    @Override
    public List<ProposalTemplateSectionDefinitionWrapper> getProposalTemplateSectionDefinitionByProposalTemplateId(
            Long proposalTemplateId, Boolean weight) {
        return Collections.emptyList();
    }

    @Override
    public boolean deleteProposalTemplateSectionDefinition(Long id) {
        return false;
    }

    @Override
    public boolean deleteProposalTemplateSection(Long proposalTemplateId,
            Long proposalTemplateSectionDefinitionId) {
        return false;
    }

    @Override
    public IProposalTemplateSection createProposalTemplateSection(
            IProposalTemplateSection proposalTemplateSection) {
        return null;
    }

    @Override
    public boolean updateProposalTemplateSection(IProposalTemplateSection proposalTemplateSection) {
        return false;
    }

    @Override
    public List<IProposalTemplateSection> getProposalTemplateSections(Long proposalTemplateId,
            Long planSectionId) {
        return Collections.emptyList();
    }
}
