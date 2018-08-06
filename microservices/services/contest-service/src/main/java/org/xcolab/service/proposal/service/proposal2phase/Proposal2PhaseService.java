package org.xcolab.service.proposal.service.proposal2phase;

import org.springframework.stereotype.Service;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.model.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.service.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.proposal.domain.proposalcontestphaseattribute.ProposalContestPhaseAttributeDao;
import org.xcolab.service.proposal.domain.proposalversion.ProposalVersionDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.proposal.helper.autopromotion.PhasePromotionHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Proposal2PhaseService {

    private final Proposal2PhaseDao proposal2PhaseDao;

    private final ProposalVersionDao proposalVersionDao;

    private final ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao;

    public Proposal2PhaseService(Proposal2PhaseDao proposal2PhaseDao,
            ProposalVersionDao proposalVersionDao,
            ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao) {
        this.proposal2PhaseDao = proposal2PhaseDao;
        this.proposalVersionDao = proposalVersionDao;
        this.proposalContestPhaseAttributeDao = proposalContestPhaseAttributeDao;
    }

    public void promoteProposal(long proposalId, long nextPhaseId, long currentPhaseId) {
        try {
            // check if proposal isn't already associated with requested phase
            if (proposal2PhaseDao.getByProposalIdContestPhaseId(proposalId, nextPhaseId) != null) {
                //_log.warn("Proposal is already associated with given contest phase");
                return;
            }
        } catch (NotFoundException e) {
            // no such proposal2phase, we can safely add association
        }
        Integer currentProposalVersion = proposalVersionDao.countByGiven(proposalId);
        if (currentProposalVersion <= 0) {
            //_log.error(String.format("Proposal %d not found: version was %d", proposalId, currentProposalVersion));
            //throw new SystemException("Proposal not found");
            return;
        }
        ContestPhase nextPhase = ContestClientUtil.getContestPhase(nextPhaseId);
        if (nextPhase == null) {
            //throw new SystemException("phase not found");
            return;
        }

        //find phase the proposal is in currently in contest c
        List<Long> phases = getContestPhasesForProposal(proposalId);
        List<ContestPhase> candidatePhase = new LinkedList<>();

        for (Long phId : phases) {
            ContestPhase ph = ContestClientUtil.getContestPhase(phId);
            if (ph.getContestId() == nextPhase.getContestId().longValue()) { //this contestphase is in our target contest
                candidatePhase.add(ph);
            }
        }

        boolean isBoundedVersion = false;
        if (!candidatePhase.isEmpty()) {
            //candidate phase now contains all contestphases the proposal has been submitted to of the target contest
            //we now need to find the one that is closest to the next phase in order to provide a smooth promotion
            //set end version of previous phase to now
            ContestPhase closestPhase = candidatePhase.get(0);
            for (ContestPhase current : candidatePhase) {
                if (current.getPhaseStartDate().after(closestPhase.getPhaseStartDate())) {
                    closestPhase = current;
                }
            }

            try {
                Proposal2Phase o = proposal2PhaseDao.getByProposalIdContestPhaseId(proposalId, closestPhase.getId());
                if (o.getVersionTo() < 0) {
                    o.setVersionTo(currentProposalVersion);
                    proposal2PhaseDao.update(o);
                } else {
                    isBoundedVersion = true;
                }
            } catch (NotFoundException ignored) {

            }
        }

        Proposal2Phase p2p = new Proposal2Phase();
        p2p.setProposalId(proposalId);
        p2p.setContestPhaseId(nextPhaseId);
        p2p.setVersionFrom(currentProposalVersion);
        p2p.setVersionTo(isBoundedVersion ? currentProposalVersion : -1);
        proposal2PhaseDao.create(p2p);

        ProposalContestPhaseAttribute proposalContestPhaseAttribute
                = PhasePromotionHelper.createProposalContestPhasePromotionDoneAttribute(proposalId, currentPhaseId);
        proposalContestPhaseAttributeDao.create(proposalContestPhaseAttribute);


    }

    public List<Long> getContestPhasesForProposal(long proposalId) {
        List<Proposal2Phase> proposal2Phases = proposal2PhaseDao.findByGiven(proposalId, null, null);

        return proposal2Phases.stream()
                .filter(p2p -> ContestClientUtil.getContestPhase(p2p.getContestPhaseId()) != null)
                .map(Proposal2Phase::getContestPhaseId)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
