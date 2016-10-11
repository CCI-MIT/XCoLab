package org.xcolab.client.proposals;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.members.UsersGroupsClient;
import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.client.proposals.enums.MembershipRequestStatus;
import org.xcolab.client.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.MembershipRequest;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MembershipRequestClient {

    private static final RestService proposalService = new RestService("proposals-service");

    private static final RestResource1<MembershipRequest,Long> membershipRequestResource = new RestResource1<>(proposalService,
            "membershipRequests", MembershipRequest.TYPES);

    public static MembershipRequest createMembershipRequest(MembershipRequest membershipRequest) {
        return membershipRequestResource.create(membershipRequest).execute();
    }
    public static boolean updateMembershipRequest(MembershipRequest membershipRequest) {
        return membershipRequestResource.update(membershipRequest, membershipRequest.getMembershipRequestId())
                .execute();
    }

    private static MembershipRequest createMembershipRequest(Long proposalId, Long userId, String comment, Integer status){
        try{
            Long groupId = ProposalsClient.getProposal(proposalId).getGroupId();

            MembershipRequest membershipRequest = new MembershipRequest();
            membershipRequest.setComments(comment == null? "":comment);
            membershipRequest.setUserId(userId);
            membershipRequest.setGroupId(groupId);
            membershipRequest.setCompanyId(10112l);
            membershipRequest.setStatusId(status);
            membershipRequest = createMembershipRequest(membershipRequest);
            return membershipRequest;
        }catch (ProposalNotFoundException ignored){

        }
        return null;
    }

    public static Boolean hasUserRequestedMembership(Long proposalId, Long userId){
        try{
            Long groupId = ProposalsClient.getProposal(proposalId).getGroupId();
            List<MembershipRequest> userRequests = getMembershipRequestsByUser(groupId, userId);
            if(userRequests != null && userRequests.size()>0){
                return true;
            }
        }catch (ProposalNotFoundException ignored){

        }
        return false;
    }

    public static void denyMembershipRequest(long proposalId, long userId, long membershipRequestId, String reply, long updateAuthorId) {
        if (hasUserRequestedMembership(proposalId, userId)) {
            try {
                MembershipRequest membershipRequest = getMembershipRequest(membershipRequestId);
                membershipRequest.setStatusId(MembershipRequestStatus.STATUS_DENIED);
                membershipRequest.setReplierUserId(updateAuthorId);
                membershipRequest.setReplyComments(reply);
                membershipRequest.setReplyDate(new Timestamp((new Date()).getTime()));
                updateMembershipRequest(membershipRequest);
            }catch (MembershipRequestNotFoundException ignored){

            }
        }
    }

    public static void approveMembershipRequest(long proposalId, Long userId, MembershipRequest request, String reply, Long updateAuthorId) {

        if (hasUserRequestedMembership(proposalId, userId)) {
            try {
                MembershipRequest membershipRequest = getMembershipRequest(request.getMembershipRequestId());
                membershipRequest.setStatusId(MembershipRequestStatus.STATUS_APPROVED);
                membershipRequest.setReplierUserId(updateAuthorId);
                membershipRequest.setReplyComments(reply);
                membershipRequest.setReplyDate(new Timestamp((new Date()).getTime()));
                updateMembershipRequest(membershipRequest);

                UsersGroupsClient.createUsersGroups(userId, membershipRequest.getGroupId());
            }catch (MembershipRequestNotFoundException ignored){

            }

            ActivityEntryHelper.createActivityEntry(userId,proposalId,null,
                    ActivityProvidersType.ProposalMemberAddedActivityEntry.getType());

            if (!ActivitiesClient.isSubscribedToActivity(userId,
                    ActivityEntryType.PROPOSAL.getPrimaryTypeId(), proposalId, 0, "")) {
                ActivitiesClient.addSubscription(userId, ActivityEntryType.PROPOSAL, proposalId, null);

            }
        }
    }

    public static MembershipRequest addInvitedMembershipRequest(Long proposalId, Long userId, String comment){
        return createMembershipRequest(proposalId,userId,comment,MembershipRequestStatus.STATUS_PENDING_INVITED);
    }
    public static MembershipRequest addRequestedMembershipRequest(Long proposalId, Long userId, String comment){
        return createMembershipRequest(proposalId,userId,comment,MembershipRequestStatus.STATUS_PENDING_REQUESTED);
    }

    public static MembershipRequest getMembershipRequest(long MembershipRequestId) throws MembershipRequestNotFoundException {
            return membershipRequestResource.get(MembershipRequestId)
                    .execute();
    }

    public static List<MembershipRequest> getMembershipRequestsByUser(Long groupId, Long userId) {
        return membershipRequestResource.list()
                .withCache(CacheKeys.withClass(MembershipRequest.class)
                        .withParameter("groupId", groupId)
                        .withParameter("userId", userId).asList(),CacheRetention.MEDIUM)
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public static List<MembershipRequest> getMembershipRequestsByStatus(Long groupId, Integer statusId) {
        return membershipRequestResource.list()
                .withCache(CacheKeys.withClass(MembershipRequest.class)
                        .withParameter("groupId", groupId)
                        .withParameter("statusId", statusId).asList(),CacheRetention.REQUEST)
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("statusId", statusId)
                .execute();
    }

    public static List<MembershipRequest> getMembershipRequests(Long proposalId) {

        try {
            Proposal proposal = ProposalsClient.getProposal(proposalId);
            List<MembershipRequest> invited = getMembershipRequestsByStatus(proposal.getGroupId(),
                    MembershipRequestStatus.STATUS_PENDING_INVITED);
            List<MembershipRequest> requested = getMembershipRequestsByStatus(proposal.getGroupId(),
                    MembershipRequestStatus.STATUS_PENDING_REQUESTED);
            List<MembershipRequest> olderRequests = getMembershipRequestsByStatus(proposal.getGroupId(),
                    MembershipRequestStatus.STATUS_PENDING);
            List<MembershipRequest> combined = new ArrayList<>();
            if (invited != null && invited.size() > 0) {
                combined.addAll(invited);
            }
            if (requested != null && requested.size() > 0) {
                combined.addAll(requested);
            }
            if (olderRequests != null && olderRequests.size() > 0) {
                combined.addAll(olderRequests);
            }
            return combined;
        }catch( ProposalNotFoundException ignored){
            return null;
        }
    }


}
