package org.xcolab.service.contest.domain.proposaltemplate;

import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalTemplateDao {

    IProposalTemplate get(Long id) throws NotFoundException;

    boolean update(IProposalTemplate proposalTemplate);

    IProposalTemplate create(IProposalTemplate proposalTemplate);

    List<IProposalTemplate> findByGiven();

    int delete(Long id);
}
