package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.ProposalReference;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.contest.proposal.domain.proposalreference.ProposalReferenceDao;
import org.xcolab.service.contest.proposal.service.proposalreference.ProposalReferenceService;

import java.util.List;

@RestController
public class ProposalReferenceController {

    private final ProposalReferenceService proposalReferenceService;

    private final ProposalReferenceDao proposalReferenceDao;

    private final ProposalDao proposalDao;

    @Autowired
    public ProposalReferenceController(ProposalReferenceService proposalReferenceService,
            ProposalReferenceDao proposalReferenceDao, ProposalDao proposalDao) {
        this.proposalReferenceService = proposalReferenceService;
        this.proposalReferenceDao = proposalReferenceDao;
        this.proposalDao = proposalDao;
    }

    @GetMapping("/proposalReferences/populateTableWithProposal")
    public Boolean getProposalReference(@RequestParam Long proposalId) throws NotFoundException {
        Proposal proposal = proposalDao.get(proposalId);
        proposalReferenceService.populateTableWithProposal(proposal);
        return true;
    }

    @RequestMapping(value = "/proposalReferences", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalReference> getProposalReferences(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long subProposalId) {
        return proposalReferenceDao.findByGiven(proposalId, subProposalId);
    }
}
