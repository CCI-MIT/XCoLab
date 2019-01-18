package org.xcolab.service.contest.proposal.domain.proposalrating;

import org.xcolab.client.contest.pojo.IProposalRating;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalRatingDao {

    int delete(Long id);

    List<IProposalRating> findByGiven(Long proposalId, Long contestPhaseId, Long userId);

    IProposalRating get(Long id) throws NotFoundException;

    IProposalRating create(IProposalRating proposalRating);

    boolean update(IProposalRating proposalRating);

    List<IProposalRating> findByProposalIdJudgeTypeJudgeIdContestPhaseId(Long proposalId,
            Integer judgeType, Long contestPhaseId, Long userId);
}
