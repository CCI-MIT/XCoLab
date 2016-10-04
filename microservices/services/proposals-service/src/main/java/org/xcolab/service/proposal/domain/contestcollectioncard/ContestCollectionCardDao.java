package org.xcolab.service.proposal.domain.contestcollectioncard;

import org.xcolab.model.tables.pojos.ContestCollectionCard;
import org.xcolab.service.proposal.exceptions.NotFoundException;

public interface ContestCollectionCardDao {

    ContestCollectionCard create(ContestCollectionCard proposal);

    ContestCollectionCard get(Long contestCollectionCardId) throws NotFoundException;

    boolean update(ContestCollectionCard contestCollectionCard);

}
