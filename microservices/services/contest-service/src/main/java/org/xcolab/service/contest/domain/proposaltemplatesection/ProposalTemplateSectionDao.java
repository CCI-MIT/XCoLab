package org.xcolab.service.contest.domain.proposaltemplatesection;

import org.xcolab.client.contest.pojo.IProposalTemplateSection;

import java.util.List;

public interface ProposalTemplateSectionDao {

    List<IProposalTemplateSection> findByGiven(Long proposalTemplateId, Long planSectionId);

    int delete(Long proposalTemplateId, Long proposalTemplateSectionDefinitionId);

    boolean update(IProposalTemplateSection proposalTemplateSection);

    IProposalTemplateSection create(IProposalTemplateSection proposalTemplateSection);
}
