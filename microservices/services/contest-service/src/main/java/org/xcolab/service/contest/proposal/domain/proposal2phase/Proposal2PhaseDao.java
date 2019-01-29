package org.xcolab.service.contest.proposal.domain.proposal2phase;

import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface Proposal2PhaseDao {

    IProposal2Phase create(IProposal2Phase proposal2Phase);

    boolean update(IProposal2Phase proposal2Phase);

    IProposal2Phase getByProposalIdContestPhaseId(Long proposalId, Long contestPhaseId) throws NotFoundException;

    List<IProposal2Phase> findByGiven(Long proposalId, Long contestPhaseId, Integer version);

    List<IProposal2Phase> findByContestAndProposal(Long proposalId, Long contestId);

    Integer getProposalCountForActiveContestPhase(Long contestPhaseId);

    int delete(Long proposalId, Long contestPhaseId);

}
