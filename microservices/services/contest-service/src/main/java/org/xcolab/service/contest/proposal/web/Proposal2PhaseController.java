package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.service.contest.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.service.proposal2phase.Proposal2PhaseService;

import java.util.List;


@RestController
public class Proposal2PhaseController {

    private final Proposal2PhaseDao proposal2PhaseDao;

    private final Proposal2PhaseService proposal2PhaseService;

    @Autowired
    public Proposal2PhaseController(Proposal2PhaseService proposal2PhaseService,
            Proposal2PhaseDao proposal2PhaseDao) {
        this.proposal2PhaseService = proposal2PhaseService;
        this.proposal2PhaseDao = proposal2PhaseDao;
    }

    @RequestMapping(value = "/proposal2Phases/{proposal2PhaseId}/getProposalCount", method = RequestMethod.GET)
    public Integer getProposalCountForActivePhase(@PathVariable Long proposal2PhaseId) throws NotFoundException {

        return proposal2PhaseDao.getProposalCountForActiveContestPhase(proposal2PhaseId);

    }

    @RequestMapping(value = "/proposal2Phases", method = RequestMethod.POST)
    public Proposal2Phase createProposal2Phase(@RequestBody Proposal2Phase proposal2Phase) {
        return this.proposal2PhaseDao.create(proposal2Phase);
    }

    @RequestMapping(value = "/proposal2Phases/updateProposal2Phase", method = RequestMethod.POST)
    public boolean updateProposal2Phase(@RequestBody Proposal2Phase proposal2Phase) throws NotFoundException {

            return proposal2PhaseDao.update(proposal2Phase);
    }

    @RequestMapping(value = "/proposal2Phases/deleteProposal2Phase", method = RequestMethod.POST)
    public boolean deleteProposal2Phase(@RequestBody Proposal2Phase proposal2Phase) throws NotFoundException {

        return proposal2PhaseDao.delete(proposal2Phase.getProposalId(), proposal2Phase.getContestPhaseId())>0;
    }


    @PostMapping("/proposal2Phases/promoteProposal")
    public boolean promoteProposal(@RequestParam Long proposalId,
            @RequestParam Long activePhaseForContest,
            @RequestParam Long currentProposalContestPhase)
            throws NotFoundException {
        proposal2PhaseService.promoteProposal(proposalId,activePhaseForContest, currentProposalContestPhase);
        return true;
    }

    @GetMapping(value = "/proposal2Phases")
    public List<Proposal2Phase>  getProposal2Phases(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Integer version) {
        return proposal2PhaseDao.findByGiven(proposalId, contestPhaseId, version);
    }

}
