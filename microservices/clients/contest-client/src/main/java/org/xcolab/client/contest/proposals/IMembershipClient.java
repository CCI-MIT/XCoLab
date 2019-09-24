package org.xcolab.client.contest.proposals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMembershipRequestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.exceptions.ConflictException;
import org.xcolab.client.contest.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.util.enums.membershiprequest.MembershipRequestStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FeignClient("xcolab-contest-service")
public interface IMembershipClient {

    @GetMapping("/membershipRequests/{membershipRequestId}")
    ProposalTeamMembershipRequestWrapper getMembershipRequest(
            @PathVariable("membershipRequestId") Long membershipRequestId)
            throws MembershipRequestNotFoundException;

    @GetMapping("/membershipRequests")
    List<ProposalTeamMembershipRequestWrapper> getMembershipRequests(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "statusId", required = false) Integer statusId,
            @RequestParam(value = "userId", required = false) Long userId);

    default List<ProposalTeamMembershipRequestWrapper> getMembershipRequests(Long proposalId) {
        List<ProposalTeamMembershipRequestWrapper> invited =
                getMembershipRequests(proposalId, MembershipRequestStatus.STATUS_PENDING_INVITED,
                        null);
        List<ProposalTeamMembershipRequestWrapper> requested =
                getMembershipRequests(proposalId, MembershipRequestStatus.STATUS_PENDING_REQUESTED,
                        null);
        List<ProposalTeamMembershipRequestWrapper> olderRequests =
                getMembershipRequests(proposalId, MembershipRequestStatus.STATUS_PENDING,
                        null);

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
    }

    default ProposalTeamMembershipRequestWrapper getActiveMembershipRequestByUser(
            ProposalWrapper proposal, Long userId) {
        List<ProposalTeamMembershipRequestWrapper> membershipRequests =
                getMembershipRequests(proposal.getId(), null, userId);
        return membershipRequests.isEmpty() ? null : membershipRequests.get(0);
    }

    default List<ProposalTeamMembershipRequestWrapper> getMembershipRequestsByStatus(
            Long proposalId, Integer statusId) {
        return getMembershipRequests(proposalId, statusId, null);
    }

    default Boolean hasUserRequestedMembership(ProposalWrapper proposal, Long userId) {
        ProposalTeamMembershipRequestWrapper userRequest =
                getActiveMembershipRequestByUser(proposal, userId);
        return userRequest != null;
    }

    @PostMapping("/membershipRequests")
    ProposalTeamMembershipRequestWrapper createMembershipRequest(
            @RequestBody ProposalTeamMembershipRequestWrapper membershipRequest);

    default ProposalTeamMembershipRequestWrapper createMembershipRequest(Long proposalId,
            Long userId, String comment,
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

    default ProposalTeamMembershipRequestWrapper addInvitedMembershipRequest(Long proposalId,
            Long userId, String comment) {
        return createMembershipRequest(proposalId, userId, comment,
                MembershipRequestStatus.STATUS_PENDING_INVITED);
    }

    default ProposalTeamMembershipRequestWrapper addRequestedMembershipRequest(Long proposalId,
            Long userId, String comment) {
        return createMembershipRequest(proposalId, userId, comment,
                MembershipRequestStatus.STATUS_PENDING_REQUESTED);
    }

    @PutMapping("/membershipRequests")
    boolean updateMembershipRequest(
            @RequestBody ProposalTeamMembershipRequestWrapper membershipRequest)
            throws MembershipRequestNotFoundException;

    default void approveMembershipRequest(ProposalWrapper proposal, Long userId,
            ProposalTeamMembershipRequestWrapper request, String reply, Long updateauthorUserId)
            throws MembershipRequestNotFoundException, ConflictException {

        if (hasUserRequestedMembership(proposal, userId)) {
            ProposalTeamMembershipRequestWrapper membershipRequest =
                    getMembershipRequest(request.getId());
            membershipRequest.setStatusId(MembershipRequestStatus.STATUS_APPROVED);
            membershipRequest.setReplierUserId(updateauthorUserId);
            membershipRequest.setReplyComments(reply);
            membershipRequest.setReplyDate(new Timestamp((new Date()).getTime()));
            updateMembershipRequest(membershipRequest);
            addUserToProposalTeam(userId, proposal);
        }
    }

    default void denyMembershipRequest(ProposalWrapper proposal, long userId,
            long membershipRequestId, String reply, long updateauthorUserId)
            throws MembershipRequestNotFoundException {
        if (hasUserRequestedMembership(proposal, userId)) {
            ProposalTeamMembershipRequestWrapper membershipRequest =
                    getMembershipRequest(membershipRequestId);
            membershipRequest.setStatusId(MembershipRequestStatus.STATUS_DENIED);
            membershipRequest.setReplierUserId(updateauthorUserId);
            membershipRequest.setReplyComments(reply);
            membershipRequest.setReplyDate(new Timestamp((new Date()).getTime()));
            updateMembershipRequest(membershipRequest);
        }
    }

    @PostMapping("/proposals/{userId}/teamMembers")
    void addUserToProposalTeam(@PathVariable("userId") Long userId,
            @RequestBody ProposalWrapper proposal) throws ConflictException;
}
