package org.xcolab.service.proposal.domain.proposalreference;

import org.xcolab.model.tables.pojos.ProposalReference;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalReferenceDao {

    List<ProposalReference> findByGiven(Long proposalId, Long subProposalId);
    ProposalReference get(Long proposalId, Long subProposalId) throws NotFoundException;
    ProposalReference create(ProposalReference proposalReference);
    boolean update(ProposalReference proposalReference);
    int delete(Long proposalId, Long subproposalId);
}
