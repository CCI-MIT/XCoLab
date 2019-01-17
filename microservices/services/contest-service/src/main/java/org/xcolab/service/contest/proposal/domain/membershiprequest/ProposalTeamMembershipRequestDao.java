package org.xcolab.service.contest.proposal.domain.membershiprequest;

import org.xcolab.model.tables.pojos.ProposalTeamMembershipRequest;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalTeamMembershipRequestDao {

    List<ProposalTeamMembershipRequest> findByGiven(Long proposalId, Integer statusId, Long userId);

    ProposalTeamMembershipRequest create(ProposalTeamMembershipRequest ProposalTeamMembershipRequest);

    ProposalTeamMembershipRequest get(Long membershipRequestId) throws NotFoundException;

    boolean update(ProposalTeamMembershipRequest ProposalTeamMembershipRequest);
}
