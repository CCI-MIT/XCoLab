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


    public static MembershipRequest getMembershipRequest(long MembershipRequestId) throws MembershipRequestNotFoundException {
            return membershipRequestResource.get(MembershipRequestId)
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
