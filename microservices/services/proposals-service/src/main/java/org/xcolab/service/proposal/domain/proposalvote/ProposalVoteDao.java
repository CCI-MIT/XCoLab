package org.xcolab.service.proposal.domain.proposalvote;

import org.xcolab.model.tables.pojos.ProposalVote;

import java.util.List;

public interface ProposalVoteDao {
    ProposalVote create(ProposalVote proposalVote);
    int delete(Long proposalId, Long contestPhaseId);
    List<ProposalVote> findByGiven(Long proposalId, Long contestPhaseId, Long userId);
    Integer countByGiven(Long proposalId, Long contestPhaseId, Long userId);
    boolean update(ProposalVote proposalVote);
    ProposalVote create(ProposalVote proposalVote);
}
