package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMembershipRequestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IMembershipClient;
import org.xcolab.client.contest.proposals.exceptions.ConflictException;
import org.xcolab.client.contest.proposals.exceptions.MembershipRequestNotFoundException;
import org.xcolab.service.contest.proposal.domain.membershiprequest.ProposalTeamMembershipRequestDao;
import org.xcolab.service.contest.proposal.domain.proposalteammember.ProposalTeamMemberDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MembershipController implements IMembershipClient {

    private final ProposalTeamMembershipRequestDao proposalTeamMembershipRequestDao;
    private final ProposalTeamMemberDao proposalTeamMemberDao;

    @Autowired
    public MembershipController(ProposalTeamMembershipRequestDao proposalTeamMembershipRequestDao,
            ProposalTeamMemberDao proposalTeamMemberDao) {
        this.proposalTeamMembershipRequestDao = proposalTeamMembershipRequestDao;
        this.proposalTeamMemberDao = proposalTeamMemberDao;
    }

    @GetMapping("/membershipRequests/{membershipRequestId}")
    public ProposalTeamMembershipRequestWrapper getMembershipRequest(
            @PathVariable Long membershipRequestId) throws MembershipRequestNotFoundException {
        if (membershipRequestId == null || membershipRequestId == 0) {
            throw new MembershipRequestNotFoundException(membershipRequestId);
        } else {
            return proposalTeamMembershipRequestDao.get(membershipRequestId);
        }
    }

    @PostMapping("/membershipRequests")
    public ProposalTeamMembershipRequestWrapper createMembershipRequest(@RequestBody
            ProposalTeamMembershipRequestWrapper membershipRequest) {
        membershipRequest.setCreatedAt(new Timestamp(new Date().getTime()));
        return this.proposalTeamMembershipRequestDao.create(membershipRequest);
    }

    @PutMapping("/membershipRequests")
    public boolean updateMembershipRequest(
            @RequestBody ProposalTeamMembershipRequestWrapper membershipRequest)
            throws MembershipRequestNotFoundException {
        Long membershipRequestId = membershipRequest.getId();
        if (membershipRequestId == null || membershipRequestId == 0
                || proposalTeamMembershipRequestDao.get(membershipRequestId) == null) {
            throw new MembershipRequestNotFoundException(membershipRequestId);
        } else {
            return proposalTeamMembershipRequestDao.update(membershipRequest);
        }
    }

    @GetMapping("/membershipRequests")
    public List<ProposalTeamMembershipRequestWrapper> getMembershipRequests(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Integer statusId,
            @RequestParam(required = false) Long userId) {
        return proposalTeamMembershipRequestDao.findByGiven(proposalId, statusId, userId)
                .stream()
                .filter(request -> request.getReplierUserId() == null)
                .collect(Collectors.toList());
    }

    @PostMapping("/proposals/{userId}/teamMembers")
    public void addUserToProposalTeam(@PathVariable Long userId,
            @RequestBody ProposalWrapper proposal) throws ConflictException {
        Long proposalId = proposal.getId();
        if (proposalTeamMemberDao.exists(proposalId, userId)) {
            throw new ConflictException();
        }
        proposalTeamMemberDao.addUserToTeam(proposalId, userId);
    }
}
