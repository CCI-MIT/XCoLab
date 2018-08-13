package org.xcolab.service.contest.domain.proposaltemplatesectiondefinition;

import org.xcolab.model.tables.pojos.ProposalTemplateSectionDefinition;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalTemplateSectionDefinitionDao {

    List<ProposalTemplateSectionDefinition> findByGiven(Long proposalTemplateId, Boolean weight);

    ProposalTemplateSectionDefinition get(Long id) throws NotFoundException;

    ProposalTemplateSectionDefinition create(ProposalTemplateSectionDefinition proposalTemplateSectionDefinition);

    boolean update(ProposalTemplateSectionDefinition proposalTemplateSectionDefinition);

    int delete(Long id);
}
