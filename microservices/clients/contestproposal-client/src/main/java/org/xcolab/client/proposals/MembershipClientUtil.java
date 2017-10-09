package org.xcolab.client.proposals;

import org.xcolab.client.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.client.proposals.pojo.team.MembershipRequest;
import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class MembershipClientUtil {

    private static final RestService contestService = new RestService(CoLabService.CONTEST,
            ServiceRequestUtils.getNamespace());

    private static final MembershipClient client = MembershipClient.fromService(contestService);

    public static MembershipClient getClient() {
        return client;
    }

    public static void denyMembershipRequest(long proposalId, long userId, long membershipRequestId,
            String reply, long updateAuthorId) {
        client.denyMembershipRequest(proposalId, userId, membershipRequestId, reply,
                updateAuthorId);
    }

    public static boolean updateMembershipRequest(MembershipRequest membershipRequest) {
        return client.updateMembershipRequest(membershipRequest);
    }

    public static Boolean hasUserRequestedMembership(Long proposalId, Long userId) {
        return client.hasUserRequestedMembership(proposalId, userId);
    }

    public static List<MembershipRequest> getMembershipRequestsByUser(Long groupId, Long userId) {
        return client.getMembershipRequestsByUser(groupId, userId);
    }

    public static MembershipRequest getMembershipRequest(long MembershipRequestId)
            throws MembershipRequestNotFoundException {
        return client.getMembershipRequest(MembershipRequestId);
    }

    public static void approveMembershipRequest(long proposalId, Long userId,
            MembershipRequest request, String reply, Long updateAuthorId) {
        client.approveMembershipRequest(proposalId, userId, request, reply, updateAuthorId);
    }

    public static MembershipRequest addInvitedMembershipRequest(
            Long proposalId, Long userId, String comment) {
        return client.addInvitedMembershipRequest(proposalId, userId, comment);
    }

    public static MembershipRequest createMembershipRequest(MembershipRequest membershipRequest) {
        return client.createMembershipRequest(membershipRequest);
    }

    public static MembershipRequest addRequestedMembershipRequest(
            Long proposalId, Long userId, String comment) {
        return client.addRequestedMembershipRequest(proposalId, userId, comment);
    }

    public static List<MembershipRequest> getMembershipRequests(Long proposalId) {
        return client.getMembershipRequests(proposalId);
    }

    public static List<MembershipRequest> getMembershipRequestsByStatus(
            Long groupId, Integer statusId) {
        return client.getMembershipRequestsByStatus(groupId, statusId);
    }

}
