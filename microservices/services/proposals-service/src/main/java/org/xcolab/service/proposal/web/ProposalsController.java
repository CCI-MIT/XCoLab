package org.xcolab.service.proposal.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.*;

import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.proposal.domain.proposal2phase.Proposal2PhaseDaoImpl;
import org.xcolab.service.proposal.domain.proposalcontestphaseattribute.ProposalContestPhaseAttributeDao;
import org.xcolab.service.proposal.domain.proposalvote.ProposalVoteDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.util.List;

@RestController
public class ProposalsController {

    @Autowired
    private ProposalDao proposalDao;

    @Autowired
    private Proposal2PhaseDao proposal2PhaseDao;

    @Autowired
    private ProposalVoteDao proposalVoteDao;

    @Autowired
    private ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao;

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

    @RequestMapping(value = "/proposals/numberOfProposalsForJudge", method = RequestMethod.GET)
    public Integer numberOfProposalsForJudge(@RequestParam long contestPhaseId, @RequestParam long userId)
            throws NotFoundException {
        PaginationHelper paginationHelper = new PaginationHelper(0, Integer.MAX_VALUE, null);

        List<Proposal> proposals = proposalDao.findByGiven(paginationHelper, null, null, contestPhaseId, null);
        int counter = 0;
        for (Proposal p : proposals){
            String judges = "";
                judges = proposalContestPhaseAttributeDao.getByProposalIdContestPhaseIdName(p.getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.SELECTED_JUDGES).getStringValue();
            if (StringUtils.containsIgnoreCase(judges, userId + "")) {
                counter++;
            }
        }
        return counter;
    }

    @RequestMapping(value = "/proposal2Phases/{proposal2PhaseId}/getProposalCount", method = RequestMethod.GET)
    public Integer getProposalCountForActivePhase(@PathVariable Long proposal2PhaseId) throws NotFoundException {

        return proposal2PhaseDao.getProposalCountForActiveContestPhase(proposal2PhaseId);

    }


    @RequestMapping(value = "/proposal2Phases", method = {RequestMethod.GET})
    public Proposal2Phase getProposal2Phases(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId
    ) throws NotFoundException {
        List<Proposal2Phase> ret = proposal2PhaseDao.findByGiven(proposalId, contestPhaseId);
        if (ret == null || ret.size() == 0) {
            throw new NotFoundException("Proposal2Phase not found by given proposalId: " + proposalId + " and contestPhaseId: " + contestPhaseId);
        }
        return ret.get(0);
    }

    @RequestMapping(value = "/proposalVotes/count", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Integer getProposalVotes(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId
    ) {
        return proposalVoteDao.countByGiven(contestPhaseId, proposalId, userId);
    }

    @RequestMapping(value = "/proposalContestPhaseAttributes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalContestPhaseAttribute> getProposalContestPhaseAttributes(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name
    ) {
        return proposalContestPhaseAttributeDao.findByGiven(contestPhaseId, proposalId, name);
    }
    @RequestMapping(value = "/proposalContestPhaseAttributes", method = RequestMethod.POST)
    public ProposalContestPhaseAttribute createProposalContestPhaseAttribute(@RequestBody ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return this.proposalContestPhaseAttributeDao.create(proposalContestPhaseAttribute);
    }
}
