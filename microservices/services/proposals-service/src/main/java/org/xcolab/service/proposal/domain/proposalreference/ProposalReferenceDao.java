package org.xcolab.service.proposal.domain.proposalreference;

import org.xcolab.model.tables.pojos.ProposalReference;

import java.util.List;

public interface ProposalReferenceDao {

    List<ProposalReference> findByGiven(Long proposalId);
}
