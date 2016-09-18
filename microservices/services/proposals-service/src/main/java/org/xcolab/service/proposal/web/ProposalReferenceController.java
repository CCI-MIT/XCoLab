package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.proposal.service.proposalreference.ProposalReferenceService;

@RestController
public class ProposalReferenceController {

    @Autowired
    ProposalReferenceService proposalReferenceService;

    @Autowired
    ProposalDao proposalDao;

    @RequestMapping(value = "/proposalReference/populateTableWithProposal", method = RequestMethod.GET)
    public Boolean getProposalReference(@RequestParam("proposalId") Long proposalId) throws NotFoundException {
        Proposal proposal = proposalDao.get(proposalId);
        proposalReferenceService.populateTableWithProposal(proposal);
        return true;
    }
}
