package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.contest.proposal.domain.proposalcontestphaseattribute.ProposalContestPhaseAttributeDao;
import org.xcolab.service.contest.proposal.service.proposal2phase.Proposal2PhaseService;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.util.List;

@RestController
public class ProposalPhaseController implements IProposalPhaseClient {

    private final Proposal2PhaseDao proposal2PhaseDao;
    private final ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao;

    private final Proposal2PhaseService proposal2PhaseService;

    @Autowired
    public ProposalPhaseController(Proposal2PhaseService proposal2PhaseService,
            Proposal2PhaseDao proposal2PhaseDao,
            ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao) {
        this.proposal2PhaseService = proposal2PhaseService;
        this.proposal2PhaseDao = proposal2PhaseDao;
        this.proposalContestPhaseAttributeDao = proposalContestPhaseAttributeDao;
    }

    @Override
    @GetMapping("/count/proposal2Phases/{proposal2PhaseId}")
    public int getProposalCountForActiveContestPhase(@PathVariable Long proposal2PhaseId) {
        return proposal2PhaseDao.getProposalCountForActiveContestPhase(proposal2PhaseId);
    }

    @Override
    @PostMapping("/proposal2Phases")
    public void createProposal2Phase(@RequestBody IProposal2Phase proposal2Phase) {
        proposal2PhaseDao.create(proposal2Phase);
    }

    @Override
    @PutMapping("/proposal2Phases")
    public boolean updateProposal2Phase(@RequestBody IProposal2Phase proposal2Phase) {
        return proposal2PhaseDao.update(proposal2Phase);
    }

    @Override
    @DeleteMapping("/proposal2Phases")
    public boolean deleteProposal2Phase(@RequestBody IProposal2Phase proposal2Phase) {
        return proposal2PhaseDao
                .delete(proposal2Phase.getProposalId(), proposal2Phase.getContestPhaseId()) > 0;
    }

    @Override
    @PostMapping("/proposal2Phases/promoteProposal")
    public boolean promoteProposal(@RequestParam Long proposalId,
            @RequestParam Long activePhaseForContest,
            @RequestParam Long currentProposalContestPhase) {
        proposal2PhaseService
                .promoteProposal(proposalId, activePhaseForContest, currentProposalContestPhase);
        return true;
    }

    @Override
    @GetMapping("/proposal2Phases")
    public List<IProposal2Phase> getProposal2Phases(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Integer version) {
        return proposal2PhaseDao.findByGiven(proposalId, contestPhaseId, version);
    }

    @Override
    @GetMapping("/proposalContestPhaseAttributes")
    public List<IProposalContestPhaseAttribute> getProposalContestPhaseAttributes(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name) {
        return proposalContestPhaseAttributeDao.findByGiven(proposalId, contestPhaseId, name);
    }

    @Override
    @GetMapping("/proposalContestPhaseAttributes/getByContestPhaseProposalIdName")
    public IProposalContestPhaseAttribute getProposalContestPhaseAttribute(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name) {
        List<IProposalContestPhaseAttribute> prop =
                proposalContestPhaseAttributeDao.findByGiven(proposalId, contestPhaseId, name);
        if (!prop.isEmpty()) {
            return prop.get(0);
        }
        return null;
    }

    @Override
    @PostMapping("/proposalContestPhaseAttributes")
    public IProposalContestPhaseAttribute createProposalContestPhaseAttribute(
            @RequestBody IProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        if (proposalContestPhaseAttribute.getAdditionalId() == null) {
            proposalContestPhaseAttribute.setAdditionalId(0L);
        }
        if (proposalContestPhaseAttribute.getStringValue() == null) {
            proposalContestPhaseAttribute.setStringValue("");
        }
        if (proposalContestPhaseAttribute.getRealValue() == null) {
            proposalContestPhaseAttribute.setRealValue(0.0);
        }
        if (proposalContestPhaseAttribute.getNumericValue() == null) {
            proposalContestPhaseAttribute.setNumericValue(0L);
        }
        return this.proposalContestPhaseAttributeDao.create(proposalContestPhaseAttribute);
    }

    @Override
    @PutMapping("/proposalContestPhaseAttributes")
    public boolean updateProposalContestPhaseAttribute(
            @RequestBody IProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        Long id = proposalContestPhaseAttribute.getId();
        try {
            if (!(id == null || id == 0 || proposalContestPhaseAttributeDao.get(id) == null)) {
                return proposalContestPhaseAttributeDao.update(proposalContestPhaseAttribute);
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException("No ProposalContestPhaseAttribute with id " + id);
    }

    @Override
    @DeleteMapping("/proposalContestPhaseAttributes/{id}")
    public boolean deleteProposalContestPhaseAttribute(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new RuntimeEntityNotFoundException(
                    "ProposalContestPhaseAttribute not found with id " + id);
        }
        try {
            IProposalContestPhaseAttribute proposalContestPhaseAttribute =
                    this.proposalContestPhaseAttributeDao.get(id);
            if (proposalContestPhaseAttribute != null) {
                this.proposalContestPhaseAttributeDao.delete(proposalContestPhaseAttribute.getId());
                return true;
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException(
                "ProposalContestPhaseAttribute not found with id " + id);
    }

    @GetMapping("/proposals/{proposalId}/phaseIds")
    public List<Long> getContestPhasesForProposal(@PathVariable Long proposalId) {
        return proposal2PhaseService.getContestPhasesForProposal(proposalId);
    }
}
