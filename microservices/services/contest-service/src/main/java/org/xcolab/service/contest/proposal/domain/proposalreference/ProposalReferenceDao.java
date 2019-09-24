package org.xcolab.service.contest.proposal.domain.proposalreference;

import org.xcolab.client.contest.pojo.IProposalReference;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalReferenceDao {

    List<IProposalReference> findByGiven(Long proposalId, Long subProposalId);

    IProposalReference get(Long proposalId, Long subProposalId) throws NotFoundException;

    IProposalReference create(IProposalReference proposalReference);

    boolean update(IProposalReference proposalReference);

    int delete(Long proposalId, Long subproposalId);
}
