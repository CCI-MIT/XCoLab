package org.xcolab.service.proposal.domain.proposal2phase;

import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

public interface Proposal2PhaseDao {

    Proposal2Phase create(Proposal2Phase proposal2Phase);

    boolean update(Proposal2Phase proposal2Phase);

    Proposal2Phase getByProposalIdContestPhaseId(Long proposalId, Long contestPhaseId) throws NotFoundException;

    List<Proposal2Phase> findByGiven(Long proposalId, Long contestPhaseId, Integer version);

    Integer getProposalCountForActiveContestPhase(Long contestPhasePK);

    int delete(Long proposalId, Long contestPhaseId);
}
