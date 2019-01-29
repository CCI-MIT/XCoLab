package org.xcolab.service.contest.domain.proposaltemplatesectiondefinition;

import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalTemplateSectionDefinitionDao {

    List<ProposalTemplateSectionDefinitionWrapper> findByGiven(Long proposalTemplateId, Boolean weight);

    ProposalTemplateSectionDefinitionWrapper get(Long id) throws NotFoundException;

    ProposalTemplateSectionDefinitionWrapper create(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition);

    boolean update(ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition);

    int delete(Long id);
}
