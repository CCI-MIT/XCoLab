package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Proposal getProposal(@PathVariable long proposalId,
            @RequestParam(required = false, defaultValue = "false") boolean includeDeleted)
            throws NotFoundException {
        final Proposal proposal = proposalDao.get(proposalId);
        if (proposal.getVisible() || includeDeleted) {
            return proposal;
        }
        throw new NotFoundException();
    }

    @RequestMapping(value = "/proposals/{proposalId}", method = RequestMethod.PUT)
    public boolean updateProposal(@RequestBody Proposal proposal, @PathVariable long proposalId)
            throws NotFoundException {
        proposalDao.get(proposalId);
        return proposalDao.update(proposal);
    }

    @RequestMapping(value = "/proposals/{proposalId}", method = RequestMethod.DELETE)
    public boolean deleteProposal(@PathVariable long proposalId)
            throws NotFoundException {
        final Proposal proposal = proposalDao.get(proposalId);
        proposal.setVisible(false);
        return proposalDao.update(proposal);
    }
}
