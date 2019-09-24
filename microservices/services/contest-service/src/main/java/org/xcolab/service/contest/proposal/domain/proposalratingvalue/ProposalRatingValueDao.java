package org.xcolab.service.contest.proposal.domain.proposalratingvalue;

import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalRatingValueDao {

    IProposalRatingValue get(Long id) throws NotFoundException;

    List<IProposalRatingValue> findByGiven(Long ratingTypeId);
}
