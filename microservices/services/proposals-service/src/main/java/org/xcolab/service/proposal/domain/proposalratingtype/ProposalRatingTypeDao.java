package org.xcolab.service.proposal.domain.proposalratingtype;

import org.xcolab.model.tables.pojos.ProposalRatingType;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

public interface ProposalRatingTypeDao {

    ProposalRatingType get(Long id_) throws NotFoundException;
    List<ProposalRatingType> findByGiven(Integer judgeType);
}
