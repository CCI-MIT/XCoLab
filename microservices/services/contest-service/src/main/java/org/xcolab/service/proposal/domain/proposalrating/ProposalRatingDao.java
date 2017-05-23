package org.xcolab.service.proposal.domain.proposalrating;

import org.xcolab.model.tables.pojos.ProposalRating;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalRatingDao {

    int delete(Long id_) ;

    List<ProposalRating> findByGiven(Long proposalId, Long contestPhaseId, Long userId);

    ProposalRating get(Long id_) throws NotFoundException;

    ProposalRating create(ProposalRating proposalRating);

    boolean update(ProposalRating proposalRating);

    List<ProposalRating> findByProposalIdJudgeTypeJudgeIdContestPhaseId(Long proposalId, Integer judgeType, Long contestPhaseId, Long userId);
}
