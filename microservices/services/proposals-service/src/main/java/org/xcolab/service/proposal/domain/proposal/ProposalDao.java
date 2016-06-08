package org.xcolab.service.proposal.domain.proposal;

import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.service.proposal.exceptions.NotFoundException;

public interface ProposalDao {

    Proposal create(Proposal proposal);
    Proposal get(Long proposalId) throws NotFoundException;
}
