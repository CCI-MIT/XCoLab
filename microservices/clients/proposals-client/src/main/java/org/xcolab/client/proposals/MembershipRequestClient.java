package org.xcolab.client.proposals;

import org.xcolab.client.proposals.enums.MembershipRequestStatus;
import org.xcolab.client.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.MembershipRequest;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MembershipRequestClient {

    private static final RestService proposalService = new RestService("proposals-service");

    private static final RestResource<MembershipRequest> membershipRequestResource = new RestResource<>(proposalService,
            "membershipRequests", MembershipRequest.TYPES);

    public static MembershipRequest createMembershipRequest(MembershipRequest membershipRequest) {
        return membershipRequestResource.create(membershipRequest).execute();
    }

    private static MembershipRequest createMembershipRequest(Long proposalId, Long userId, String comment, Integer status){
        try{
            Long groupId = ProposalsClient.getProposal(proposalId).getGroupId();

            MembershipRequest membershipRequest = new MembershipRequest();
            membershipRequest.setComments(comment == null? "":comment);
            membershipRequest.setUserId(userId);
            membershipRequest.setGroupId(groupId);
            membershipRequest.setCompanyId(10112l);
            membershipRequest = createMembershipRequest(membershipRequest);
            return membershipRequest;
        }catch (ProposalNotFoundException ignored){

        }
        return null;
    }
    public void approveMembershipRequest(){

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
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public static List<MembershipRequest> getMembershipRequestsByStatus(Long groupId, Integer statusId) {
        return membershipRequestResource.list()
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
