package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.service.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.proposal.service.proposal2phase.Proposal2PhaseService;

import java.util.List;


@RestController
public class Proposal2PhaseController {

    @Autowired
    private Proposal2PhaseDao proposal2PhaseDao;

    @Autowired
    Proposal2PhaseService proposal2PhaseService;

    @RequestMapping(value = "/proposal2Phases/{proposal2PhaseId}/getProposalCount", method = RequestMethod.GET)
    public Integer getProposalCountForActivePhase(@PathVariable Long proposal2PhaseId) throws NotFoundException {

        return proposal2PhaseDao.getProposalCountForActiveContestPhase(proposal2PhaseId);

    }

    @RequestMapping(value = "/proposal2Phases", method = RequestMethod.POST)
    public Proposal2Phase createProposal2Phase(@RequestBody Proposal2Phase proposal2Phase) {
        return this.proposal2PhaseDao.create(proposal2Phase);
    }

    @RequestMapping(value = "/proposal2Phases/getByContestPhaseIdProposalId", method = {RequestMethod.GET})
    public Proposal2Phase getByContestPhaseIdProposalId(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId
    ) throws NotFoundException {
        List<Proposal2Phase> ret = proposal2PhaseDao.findByGiven(proposalId, contestPhaseId);
        if (ret == null || ret.size() == 0) {
            throw new NotFoundException("Proposal2Phase not found by given proposalId: " + proposalId + " and contestPhaseId: " + contestPhaseId);
        }
        return ret.get(0);
    }

    @RequestMapping(value = "/proposal2Phases/updateProposal2Phase", method = RequestMethod.POST)
    public boolean updateProposal2Phase(@RequestBody Proposal2Phase proposal2Phase) throws NotFoundException {

            return proposal2PhaseDao.update(proposal2Phase);
    }

    @RequestMapping(value = "/proposal2Phases/deleteProposal2Phase", method = RequestMethod.POST)
    public boolean deleteProposal2Phase(@RequestBody Proposal2Phase proposal2Phase) throws NotFoundException {

        return proposal2PhaseDao.delete(proposal2Phase.getProposalId(), proposal2Phase.getContestPhaseId())>0;
    }


    @RequestMapping(value = "/proposal2Phases/promoteProposal", method = RequestMethod.GET)
    public boolean promoteProposal(@RequestParam Long proposalId,
                                        @RequestParam Long activePhaseForContest,
                                        @RequestParam Long currentProposalContestPhase) throws NotFoundException {

         proposal2PhaseService.promoteProposal(proposalId,activePhaseForContest, currentProposalContestPhase);
        return true;
    }

    @RequestMapping(value = "/proposal2Phases", method = {RequestMethod.GET})
    public List<Proposal2Phase>  getProposal2Phases(
            @RequestParam(required = false) Long proposalId
    ) throws NotFoundException {
        return proposal2PhaseDao.findByGiven(proposalId, null);

    }

}
