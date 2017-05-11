package org.xcolab.service.proposal.web;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.ProposalReference;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposalreference.ProposalReferenceDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.proposal.service.proposalreference.ProposalReferenceService;

import java.util.List;

@RestController
public class ProposalReferenceController {

    @Autowired
    ProposalReferenceService proposalReferenceService;

    @Autowired
    ProposalReferenceDao proposalReferenceDao;

    @Autowired
    ProposalDao proposalDao;

    @RequestMapping(value = "/proposalReference/populateTableWithProposal", method = RequestMethod.GET)
    public Boolean getProposalReference(@RequestParam("proposalId") Long proposalId) throws NotFoundException {
        Proposal proposal = proposalDao.get(proposalId);
        proposalReferenceService.populateTableWithProposal(proposal);
        return true;
    }
    @RequestMapping(value = "/proposalReference", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalReference> getProposalReferences(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long subProposalId
    ) {
        return proposalReferenceDao.findByGiven(proposalId, subProposalId);
    }
}
