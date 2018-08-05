package org.xcolab.service.proposal.domain.proposalratingtype;

import org.xcolab.model.tables.pojos.ProposalRatingType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalRatingTypeDao {

    ProposalRatingType get(Long id) throws NotFoundException;
    List<ProposalRatingType> findByGiven(Integer judgeType, Boolean active);
}
