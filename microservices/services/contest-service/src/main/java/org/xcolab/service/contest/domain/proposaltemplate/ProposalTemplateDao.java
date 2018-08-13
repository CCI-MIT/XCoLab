package org.xcolab.service.contest.domain.proposaltemplate;

import org.xcolab.model.tables.pojos.ProposalTemplate;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalTemplateDao {

    ProposalTemplate get(Long id) throws NotFoundException;

    boolean update(ProposalTemplate proposalTemplate);

    ProposalTemplate create(ProposalTemplate proposalTemplate);

    List<ProposalTemplate> findByGiven();

    int delete(Long id);
}
