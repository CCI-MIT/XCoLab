package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.service.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;


@RestController
public class Proposal2PhaseController {

    @Autowired
    private Proposal2PhaseDao proposal2PhaseDao;

    @RequestMapping(value = "/proposal2Phases/{proposal2PhaseId}/getProposalCount", method = RequestMethod.GET)
    public Integer getProposalCountForActivePhase(@PathVariable Long proposal2PhaseId) throws NotFoundException {

        return proposal2PhaseDao.getProposalCountForActiveContestPhase(proposal2PhaseId);

    }

    @RequestMapping(value = "/proposal2Phases/getByContestPhaseIdProposalId", method = {RequestMethod.GET})
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
}
