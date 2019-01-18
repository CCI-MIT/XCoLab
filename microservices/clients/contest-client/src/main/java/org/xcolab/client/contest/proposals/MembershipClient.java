package org.xcolab.client.contest.proposals;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalDto;
import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMembershipRequestWrapper;
import org.xcolab.client.contest.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.util.enums.membershiprequest.MembershipRequestStatus;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2;
import org.xcolab.util.http.exceptions.Http409ConflictException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MembershipClient {

    private final RestResource1<ProposalDto, Long> proposalResource = null; // proposals

    private final RestResource1<ProposalTeamMembershipRequestWrapper, Long> membershipRequestResource = null; // membershipRequests
    private final RestResource2<ProposalDto, Long, Long, Long> proposalTeamMemberResource = null; // proposals / teamMembers

    private final ProposalClient proposalClient = null;

    public void denyMembershipRequest(ProposalWrapper proposal, long userId, long membershipRequestId,
            String reply, long updateauthorUserId) {
        if (hasUserRequestedMembership(proposal, userId)) {
            try {
                ProposalTeamMembershipRequestWrapper membershipRequest = getMembershipRequest(membershipRequestId);
                membershipRequest.setStatusId(MembershipRequestStatus.STATUS_DENIED);
                membershipRequest.setReplierUserId(updateauthorUserId);
                membershipRequest.setReplyComments(reply);
                membershipRequest.setReplyDate(new Timestamp((new Date()).getTime()));
                updateMembershipRequest(membershipRequest);
            } catch (MembershipRequestNotFoundException ignored) {

            }
        }
    }

    public boolean updateMembershipRequest(ProposalTeamMembershipRequestWrapper membershipRequest) {
        return membershipRequestResource
                .update(new ProposalTeamMembershipRequestWrapper(membershipRequest),
                        membershipRequest.getId())
                .execute();
    }

    public Boolean hasUserRequestedMembership(ProposalWrapper proposal, Long userId) {
        try {
            ProposalTeamMembershipRequestWrapper
                    userRequest = getActiveMembershipRequestByUser(proposal, userId);
            if (userRequest != null) {
                return true;
            }
        } catch (ProposalNotFoundException ignored) {

        }
        return false;
    }

    public ProposalTeamMembershipRequestWrapper getActiveMembershipRequestByUser(ProposalWrapper proposal, Long userId) {
        return membershipRequestResource.list()
                .optionalQueryParam("proposalId", proposal.getId())
                .optionalQueryParam("userId", userId)
                .executeWithResult().getOneIfExists();
    }

    public ProposalTeamMembershipRequestWrapper getMembershipRequest(long MembershipRequestId)
            throws MembershipRequestNotFoundException {
        return membershipRequestResource.get(MembershipRequestId)
                .execute();
    }

    public void approveMembershipRequest(ProposalWrapper proposal, Long userId, ProposalTeamMembershipRequestWrapper request,
            String reply, Long updateauthorUserId) {

        if (hasUserRequestedMembership(proposal, userId)) {
            try {
                ProposalTeamMembershipRequestWrapper membershipRequest =
                        getMembershipRequest(request.getId());
                membershipRequest.setStatusId(MembershipRequestStatus.STATUS_APPROVED);
                membershipRequest.setReplierUserId(updateauthorUserId);
                membershipRequest.setReplyComments(reply);
                membershipRequest.setReplyDate(new Timestamp((new Date()).getTime()));
                updateMembershipRequest(membershipRequest);
                addUserToProposalTeam(userId, proposal);
            } catch (MembershipRequestNotFoundException e) {
                throw new InternalException(e);
            }
        }
    }

    public void addUserToProposalTeam(Long userId, ProposalWrapper proposal) {
        long proposalId = proposal.getId();
        try {
            proposalTeamMemberResource.resolveParentId(proposalResource.id(proposalId))
                    .create(userId)
                    .execute();

            ActivitiesClient activityClient = ActivitiesClientUtil.getClient();

            activityClient.createActivityEntry(ProposalActivityType.MEMBER_ADDED, userId,
                    proposalId);

            if (!activityClient.isSubscribedToActivity(userId, ActivityCategory.PROPOSAL,
                    proposalId)) {
                activityClient
                        .addSubscription(userId, ActivityCategory.PROPOSAL, proposalId, null);

            }
        } catch (Http409ConflictException ignored) {
            // already a member - don't do anything
        }
    }

    public ProposalTeamMembershipRequestWrapper addInvitedMembershipRequest(Long proposalId, Long userId,
            String comment) {
        return createMembershipRequest(proposalId, userId, comment,
                MembershipRequestStatus.STATUS_PENDING_INVITED);
    }

    private ProposalTeamMembershipRequestWrapper createMembershipRequest(Long proposalId, Long userId, String comment,
            Integer status) {
        try {
            ProposalTeamMembershipRequestWrapper
                    membershipRequest = new ProposalTeamMembershipRequestWrapper();
            membershipRequest.setComments(comment == null ? "" : comment);
            membershipRequest.setProposalId(proposalId);
            membershipRequest.setUserId(userId);
            membershipRequest.setStatusId(status);
            membershipRequest = createMembershipRequest(membershipRequest);
            return membershipRequest;
        } catch (ProposalNotFoundException ignored) {

        }
        return null;
    }

    public ProposalTeamMembershipRequestWrapper createMembershipRequest(
            ProposalTeamMembershipRequestWrapper membershipRequest) {
        return membershipRequestResource.create(new ProposalTeamMembershipRequestWrapper(membershipRequest))
                .execute();
    }

    public ProposalTeamMembershipRequestWrapper addRequestedMembershipRequest(Long proposalId, Long userId,
            String comment) {
        return createMembershipRequest(proposalId, userId, comment,
                MembershipRequestStatus.STATUS_PENDING_REQUESTED);
    }

    public List<ProposalTeamMembershipRequestWrapper> getMembershipRequests(Long proposalId) {

        try {
            ProposalWrapper proposal = proposalClient.getProposal(proposalId);
            List<ProposalTeamMembershipRequestWrapper> invited = getMembershipRequestsByStatus(proposal.getId(),
                    MembershipRequestStatus.STATUS_PENDING_INVITED);
            List<ProposalTeamMembershipRequestWrapper> requested = getMembershipRequestsByStatus(proposal.getId(),
                    MembershipRequestStatus.STATUS_PENDING_REQUESTED);
            List<ProposalTeamMembershipRequestWrapper> olderRequests =
                    getMembershipRequestsByStatus(proposal.getId(),
                            MembershipRequestStatus.STATUS_PENDING);
            List<ProposalTeamMembershipRequestWrapper> combined = new ArrayList<>();
            if (invited != null && !invited.isEmpty()) {
                combined.addAll(invited);
            }
            if (requested != null && !requested.isEmpty()) {
                combined.addAll(requested);
            }
            if (olderRequests != null && !olderRequests.isEmpty()) {
                combined.addAll(olderRequests);
            }
            return combined;
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    public List<ProposalTeamMembershipRequestWrapper> getMembershipRequestsByStatus(Long proposalId, Integer statusId) {
        return membershipRequestResource.list()
                .withCache(CacheKeys.withClass(ProposalTeamMembershipRequestWrapper.class)
                        .withParameter("proposalId", proposalId)
                        .withParameter("statusId", statusId).asList(), CacheName.MISC_REQUEST)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("statusId", statusId)
                .execute();
    }

}
