package org.xcolab.service.contest.proposal.domain.membershiprequest;

import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMembershipRequestWrapper;
import org.xcolab.client.contest.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalTeamMembershipRequestDao {

    List<ProposalTeamMembershipRequestWrapper> findByGiven(Long proposalId, Integer statusId, Long userId);

    ProposalTeamMembershipRequestWrapper create(
            ProposalTeamMembershipRequestWrapper ProposalTeamMembershipRequest);

    ProposalTeamMembershipRequestWrapper get(Long membershipRequestId) throws MembershipRequestNotFoundException;

    boolean update(ProposalTeamMembershipRequestWrapper ProposalTeamMembershipRequest);
}
