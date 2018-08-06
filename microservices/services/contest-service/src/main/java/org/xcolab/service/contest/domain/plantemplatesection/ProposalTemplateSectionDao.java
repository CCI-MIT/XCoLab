package org.xcolab.service.contest.domain.plantemplatesection;

import org.xcolab.model.tables.pojos.ProposalTemplateSection;

import java.util.List;

public interface ProposalTemplateSectionDao {
    List<ProposalTemplateSection> findByGiven(Long planTemplateId, Long planSectionId);
    int delete(Long planTemplateId, Long planSectionDefinitionId);
    boolean update(ProposalTemplateSection planTemplateSection);
    ProposalTemplateSection create(ProposalTemplateSection planTemplateSection);
}
