package org.xcolab.service.contest.domain.plansectiondefinition;

import org.xcolab.model.tables.pojos.ProposalTemplateSectionDefinition;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalTemplateSectionDefinitionDao {

    List<ProposalTemplateSectionDefinition> findByGiven(Long planTemplateId, Boolean weight);

    ProposalTemplateSectionDefinition get(Long id) throws NotFoundException;

    ProposalTemplateSectionDefinition create(ProposalTemplateSectionDefinition planSectionDefinition);

    boolean update(ProposalTemplateSectionDefinition planSectionDefinition);

    int delete(Long id);
}
