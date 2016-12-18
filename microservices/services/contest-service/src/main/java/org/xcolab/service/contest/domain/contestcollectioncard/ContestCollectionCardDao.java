package org.xcolab.service.contest.domain.contestcollectioncard;

import org.xcolab.model.tables.pojos.ContestCollectionCard;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ContestCollectionCardDao {

    ContestCollectionCard create(ContestCollectionCard proposal);

    ContestCollectionCard get(Long contestCollectionCardId) throws NotFoundException;

    boolean update(ContestCollectionCard contestCollectionCard);

    boolean delete(Long contestCollectionCardId) throws NotFoundException;

    List<ContestCollectionCard>  findByGiven(Long parentCollectionCardId) throws NotFoundException;

}
