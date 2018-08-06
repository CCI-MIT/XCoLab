package org.xcolab.client.proposals;

import org.xcolab.client.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.client.proposals.pojo.team.ProposalTeamMembershipRequest;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;

public class MembershipClientUtil {

    private static final MembershipClient client = MembershipClient.fromNamespace(
            ServiceNamespace.instance());

    public static MembershipClient getClient() {
        return client;
    }

    public static void denyMembershipRequest(long proposalId, long userId, long membershipRequestId,
            String reply, long updateauthorUserId) {
        client.denyMembershipRequest(proposalId, userId, membershipRequestId, reply,
                updateauthorUserId);
    }

    public static boolean updateMembershipRequest(ProposalTeamMembershipRequest membershipRequest) {
        return client.updateMembershipRequest(membershipRequest);
    }

    public static Boolean hasUserRequestedMembership(Long proposalId, Long userId) {
        return client.hasUserRequestedMembership(proposalId, userId);
    }

    public static List<ProposalTeamMembershipRequest> getMembershipRequestsByUser(Long groupId, Long userId) {
        return client.getMembershipRequestsByUser(groupId, userId);
    }

    public static ProposalTeamMembershipRequest getMembershipRequest(long MembershipRequestId)
            throws MembershipRequestNotFoundException {
        return client.getMembershipRequest(MembershipRequestId);
    }

    public static void approveMembershipRequest(long proposalId, Long userId,
            ProposalTeamMembershipRequest request, String reply, Long updateauthorUserId) {
        client.approveMembershipRequest(proposalId, userId, request, reply, updateauthorUserId);
    }

    public static ProposalTeamMembershipRequest addInvitedMembershipRequest(
            Long proposalId, Long userId, String comment) {
        return client.addInvitedMembershipRequest(proposalId, userId, comment);
    }

    public static ProposalTeamMembershipRequest createMembershipRequest(
            ProposalTeamMembershipRequest membershipRequest) {
        return client.createMembershipRequest(membershipRequest);
    }

    public static ProposalTeamMembershipRequest addRequestedMembershipRequest(
            Long proposalId, Long userId, String comment) {
        return client.addRequestedMembershipRequest(proposalId, userId, comment);
    }

    public static List<ProposalTeamMembershipRequest> getMembershipRequests(Long proposalId) {
        return client.getMembershipRequests(proposalId);
    }

    public static List<ProposalTeamMembershipRequest> getMembershipRequestsByStatus(
            Long groupId, Integer statusId) {
        return client.getMembershipRequestsByStatus(groupId, statusId);
    }

}
