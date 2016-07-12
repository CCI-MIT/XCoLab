package org.xcolab.service.proposal.domain.proposalvote;

import org.xcolab.model.tables.pojos.ProposalVote;

import java.util.List;

public interface ProposalVoteDao {
    int delete(Long proposalId, Long contestPhaseId);
    List<ProposalVote> findByGiven(Long proposalId, Long contestPhaseId, Long userId);
    boolean update(ProposalVote proposalVote);
}
