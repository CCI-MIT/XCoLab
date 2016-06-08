package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.Proposal;

import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;

@RestController
public class ProposalsController {

    @Autowired
    private ProposalDao proposalDao;


    @RequestMapping(value = "/proposals", method = RequestMethod.POST)
    public Proposal createProposal(@RequestBody Proposal proposal) {
        return this.proposalDao.create(proposal);
    }

    @RequestMapping(value = "/proposals/{proposalId}", method = RequestMethod.GET)
    public Proposal getProposal(@PathVariable("proposalId") Long proposalId) throws NotFoundException {
        if (proposalId == null || proposalId == 0) {
            throw new NotFoundException("No proposalId given");
        } else {
            return proposalDao.get(proposalId);
        }
    }

}
