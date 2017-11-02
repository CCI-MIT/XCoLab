package org.xcolab.service.proposal.domain.proposalvote;

import org.xcolab.model.tables.pojos.ProposalVote;

import java.util.List;

public interface ProposalVoteDao {

    int delete(long proposalId, long memberId, long contestPhaseId);

    List<ProposalVote> findByGiven(Long proposalId, Long contestPhaseId, Long userId);

    Integer countByGiven(Long proposalId, Long contestPhaseId, Long userId);

    boolean update(ProposalVote proposalVote);

    ProposalVote create(ProposalVote proposalVote);
}
