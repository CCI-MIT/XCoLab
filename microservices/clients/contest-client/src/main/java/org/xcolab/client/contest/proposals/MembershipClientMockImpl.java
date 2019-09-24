package org.xcolab.client.contest.proposals;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMembershipRequestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.exceptions.ConflictException;
import org.xcolab.client.contest.proposals.exceptions.MembershipRequestNotFoundException;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class MembershipClientMockImpl implements IMembershipClient {

    @Override
    public ProposalTeamMembershipRequestWrapper getMembershipRequest(Long membershipRequestId)
            throws MembershipRequestNotFoundException {
        return null;
    }

    @Override
    public List<ProposalTeamMembershipRequestWrapper> getMembershipRequests(Long proposalId,
            Integer statusId, Long userId) {
        return Collections.emptyList();
    }

    @Override
    public ProposalTeamMembershipRequestWrapper createMembershipRequest(
            ProposalTeamMembershipRequestWrapper membershipRequest) {
        return null;
    }

    @Override
    public boolean updateMembershipRequest(ProposalTeamMembershipRequestWrapper membershipRequest)
            throws MembershipRequestNotFoundException {
        return false;
    }

    @Override
    public void addUserToProposalTeam(Long userId, ProposalWrapper proposal)
            throws ConflictException {

    }
}
