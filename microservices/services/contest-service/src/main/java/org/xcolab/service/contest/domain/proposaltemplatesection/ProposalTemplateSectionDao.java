package org.xcolab.service.contest.domain.proposaltemplatesection;

import org.xcolab.model.tables.pojos.ProposalTemplateSection;

import java.util.List;

public interface ProposalTemplateSectionDao {
    List<ProposalTemplateSection> findByGiven(Long proposalTemplateId, Long planSectionId);

    int delete(Long proposalTemplateId, Long planSectionDefinitionId);

    boolean update(ProposalTemplateSection proposalTemplateSection);

    ProposalTemplateSection create(ProposalTemplateSection proposalTemplateSection);
}
