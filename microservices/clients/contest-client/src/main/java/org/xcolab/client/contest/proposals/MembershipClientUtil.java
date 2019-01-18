package org.xcolab.client.contest.proposals;

import org.xcolab.client.contest.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMembershipRequestWrapper;

import java.util.List;

public class MembershipClientUtil {

    private static final MembershipClient client = new MembershipClient();

    public static MembershipClient getClient() {
        return client;
    }

    public static void denyMembershipRequest(ProposalWrapper proposal, long userId, long membershipRequestId,
            String reply, long updateauthorUserId) {
        client.denyMembershipRequest(proposal, userId, membershipRequestId, reply,
                updateauthorUserId);
    }

    public static boolean updateMembershipRequest(
            ProposalTeamMembershipRequestWrapper membershipRequest) {
        return client.updateMembershipRequest(membershipRequest);
    }

    public static Boolean hasUserRequestedMembership(ProposalWrapper proposal, Long userId) {
        return client.hasUserRequestedMembership(proposal, userId);
    }

    public static ProposalTeamMembershipRequestWrapper getActiveMembershipRequestByUser(
            ProposalWrapper proposal, Long userId) {
        return client.getActiveMembershipRequestByUser(proposal, userId);
    }

    public static ProposalTeamMembershipRequestWrapper getMembershipRequest(long MembershipRequestId)
            throws MembershipRequestNotFoundException {
        return client.getMembershipRequest(MembershipRequestId);
    }

    public static void approveMembershipRequest(ProposalWrapper proposal, Long userId,
            ProposalTeamMembershipRequestWrapper request, String reply, Long updateauthorUserId) {
        client.approveMembershipRequest(proposal, userId, request, reply, updateauthorUserId);
    }

    public static ProposalTeamMembershipRequestWrapper addInvitedMembershipRequest(
            Long proposalId, Long userId, String comment) {
        return client.addInvitedMembershipRequest(proposalId, userId, comment);
    }

    public static ProposalTeamMembershipRequestWrapper createMembershipRequest(
            ProposalTeamMembershipRequestWrapper membershipRequest) {
        return client.createMembershipRequest(membershipRequest);
    }

    public static ProposalTeamMembershipRequestWrapper addRequestedMembershipRequest(
            Long proposalId, Long userId, String comment) {
        return client.addRequestedMembershipRequest(proposalId, userId, comment);
    }

    public static List<ProposalTeamMembershipRequestWrapper> getMembershipRequests(Long proposalId) {
        return client.getMembershipRequests(proposalId);
    }

    public static List<ProposalTeamMembershipRequestWrapper> getMembershipRequestsByStatus(
            Long proposalId, Integer statusId) {
        return client.getMembershipRequestsByStatus(proposalId, statusId);
    }

}
