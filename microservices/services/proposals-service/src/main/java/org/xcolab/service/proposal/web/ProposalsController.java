package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.Proposal;

import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.proposal.domain.proposal2phase.Proposal2PhaseDaoImpl;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class ProposalsController {

    @Autowired
    private ProposalDao proposalDao;

    @Autowired
    private Proposal2PhaseDao proposal2PhaseDao;


    @RequestMapping(value = "/proposals", method = RequestMethod.POST)
    public Proposal createProposal(@RequestBody Proposal proposal) {
        return this.proposalDao.create(proposal);
    }

    @RequestMapping(value = "/proposals", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Proposal> listProposals(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Boolean visible,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Integer ribbon,
            @RequestParam(required = false) String sort) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return proposalDao
                .findByGiven(paginationHelper, contestId, visible, contestPhaseId, ribbon);
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

    @RequestMapping(value = "/proposal2Phases/{proposal2PhaseId}/getProposalCount", method = RequestMethod.GET)
    public Integer getProposalCountForActivePhase(@PathVariable Long proposal2PhaseId) throws NotFoundException {

        return proposal2PhaseDao.getProposalCountForActiveContestPhase(proposal2PhaseId);

    }
}
