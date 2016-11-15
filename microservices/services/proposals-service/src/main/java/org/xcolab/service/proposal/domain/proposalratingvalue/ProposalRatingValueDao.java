package org.xcolab.service.proposal.domain.proposalratingvalue;

import org.xcolab.model.tables.pojos.ProposalRatingValue;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

public interface ProposalRatingValueDao {
    ProposalRatingValue get(Long id_) throws NotFoundException;
    List<ProposalRatingValue> findByGiven(Long ratingTypeId);
}
