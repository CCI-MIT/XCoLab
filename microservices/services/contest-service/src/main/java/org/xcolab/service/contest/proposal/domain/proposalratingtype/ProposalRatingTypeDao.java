package org.xcolab.service.contest.proposal.domain.proposalratingtype;

import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalRatingTypeDao {

    IProposalRatingType get(Long id) throws NotFoundException;

    List<IProposalRatingType> findByGiven(Integer judgeType, Boolean active);
}
