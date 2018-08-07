package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ProposalTeamMembershipRequest;
import org.xcolab.service.proposal.domain.membershiprequest.ProposalTeamMembershipRequestDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.exceptions.ConflictException;
import org.xcolab.service.proposal.domain.proposalteammember.ProposalTeamMemberDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class MembershipController {

    private final ProposalTeamMembershipRequestDao ProposalTeamMembershipRequestDao;
    private final ProposalTeamMemberDao proposalTeamMemberDao;

    @Autowired
    public MembershipController(
            org.xcolab.service.proposal.domain.membershiprequest.ProposalTeamMembershipRequestDao ProposalTeamMembershipRequestDao,
            ProposalTeamMemberDao proposalTeamMemberDao) {
        this.ProposalTeamMembershipRequestDao = ProposalTeamMembershipRequestDao;
        this.proposalTeamMemberDao = proposalTeamMemberDao;
    }

    @RequestMapping(value = "/membershipRequests", method = RequestMethod.POST)
    public ProposalTeamMembershipRequest createProposalTeamMembershipRequest(@RequestBody ProposalTeamMembershipRequest ProposalTeamMembershipRequest) {
            ProposalTeamMembershipRequest.setCreatedAt(new Timestamp(new Date().getTime()));
        return this.ProposalTeamMembershipRequestDao.create(ProposalTeamMembershipRequest);
    }

    @RequestMapping(value = "/membershipRequests/{membershipRequestId}", method = RequestMethod.GET)
    public ProposalTeamMembershipRequest getProposalTeamMembershipRequest(@PathVariable Long membershipRequestId) throws NotFoundException {
        if (membershipRequestId == null || membershipRequestId == 0) {
            throw new NotFoundException("No membershipRequestId given");
        } else {
            return ProposalTeamMembershipRequestDao.get(membershipRequestId);
        }
    }

    @RequestMapping(value = "/membershipRequests/{membershipRequestId}", method = RequestMethod.PUT)
    public boolean updateProposalTeamMembershipRequest(@RequestBody ProposalTeamMembershipRequest ProposalTeamMembershipRequest,
                                           @PathVariable Long membershipRequestId) throws NotFoundException {

        if (membershipRequestId == null || membershipRequestId == 0 || ProposalTeamMembershipRequestDao.get(membershipRequestId) == null) {
            throw new NotFoundException("No ProposalTeamMembershipRequest with id " + membershipRequestId);
        } else {
            return ProposalTeamMembershipRequestDao.update(ProposalTeamMembershipRequest);
        }
    }

    @RequestMapping(value = "/membershipRequests", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalTeamMembershipRequest> getProposalTeamMembershipRequests(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Integer statusId,
            @RequestParam(required = false) Long userId
    ) {
        return ProposalTeamMembershipRequestDao.findByGiven(proposalId, statusId, userId);
    }

    @PostMapping("/proposals/{proposalId}/teamMembers")
    public Long addTeamMember(@PathVariable long proposalId, @RequestBody Long userId)
            throws ConflictException {
        if (proposalTeamMemberDao.exists(proposalId, userId)) {
            throw new ConflictException();
        }
        proposalTeamMemberDao.addUserToTeam(proposalId, userId);
        return userId;
    }
}
