package org.xcolab.service.contest.domain.contestcollectioncard;

import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ContestCollectionCardDao {

    IContestCollectionCard create(IContestCollectionCard proposal);

    IContestCollectionCard get(Long contestCollectionCardId) throws NotFoundException;

    boolean update(IContestCollectionCard contestCollectionCard);

    boolean delete(Long contestCollectionCardId) throws NotFoundException;

    List<IContestCollectionCard>  findByGiven(Long parentCollectionCardId) throws NotFoundException;
}
