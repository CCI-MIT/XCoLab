package org.xcolab.service.contest.proposal.domain.proposalrating;

import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalRatingDao {

    int delete(Long id);

    List<ProposalRatingWrapper> findByGiven(Long proposalId, Long contestPhaseId, Long userId);

    ProposalRatingWrapper get(Long id) throws NotFoundException;

    ProposalRatingWrapper create(ProposalRatingWrapper proposalRating);

    boolean update(ProposalRatingWrapper proposalRating);

    List<ProposalRatingWrapper> findByProposalIdJudgeTypeJudgeIdContestPhaseId(Long proposalId,
            Integer judgeType, Long contestPhaseId, Long userId);
}
