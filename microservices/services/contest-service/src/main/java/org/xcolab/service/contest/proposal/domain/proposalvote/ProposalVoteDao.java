package org.xcolab.service.contest.proposal.domain.proposalvote;

import org.xcolab.client.contest.pojo.IProposalVote;

import java.util.List;

public interface ProposalVoteDao {

    int delete(long proposalId, long userId, long contestPhaseId);

    List<IProposalVote> findByGiven(Long proposalId, Long contestPhaseId, Long userId);

    Integer countByGiven(Long proposalId, Long contestPhaseId, Long userId,
            Boolean isValidOverride);

    boolean update(IProposalVote proposalVote);

    IProposalVote create(IProposalVote proposalVote);
}
