package org.xcolab.service.proposal.domain.membershiprequest;

import org.xcolab.model.tables.pojos.ProposalTeamMembershipRequest;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalTeamMembershipRequestDao {

    List<ProposalTeamMembershipRequest> findByGiven(Integer statusId, Long userId);

    ProposalTeamMembershipRequest create(ProposalTeamMembershipRequest ProposalTeamMembershipRequest);

    ProposalTeamMembershipRequest get(Long ProposalTeamMembershipRequestId) throws NotFoundException;

    boolean update(ProposalTeamMembershipRequest ProposalTeamMembershipRequest);
}
