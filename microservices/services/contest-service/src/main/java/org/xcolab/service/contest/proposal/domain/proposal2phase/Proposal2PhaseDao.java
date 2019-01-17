package org.xcolab.service.contest.proposal.domain.proposal2phase;

import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface Proposal2PhaseDao {

    Proposal2Phase create(Proposal2Phase proposal2Phase);

    boolean update(Proposal2Phase proposal2Phase);

    Proposal2Phase getByProposalIdContestPhaseId(Long proposalId, Long contestPhaseId) throws NotFoundException;

    List<Proposal2Phase> findByGiven(Long proposalId, Long contestPhaseId, Integer version);

    List<Proposal2Phase> findByContestAndProposal(Long proposalId, Long contestId);

    Integer getProposalCountForActiveContestPhase(Long contestPhaseId);

    int delete(Long proposalId, Long contestPhaseId);

}
