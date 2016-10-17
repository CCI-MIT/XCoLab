package org.xcolab.service.contest.service.collectioncard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.ContestCollectionCard;
import org.xcolab.service.contest.domain.contestcollectioncard.ContestCollectionCardDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.contest.ContestService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CollectionCardService {

    private final ContestService contestService;

    private final ContestCollectionCardDao contestCollectionCardDao;

    @Autowired
    public CollectionCardService(ContestService contestService, ContestCollectionCardDao contestCollectionCardDao) {


        this.contestService = contestService;
        this.contestCollectionCardDao=contestCollectionCardDao;
    }

    public int getNumberOfContestsInCollectionCard(Long collectionCardId) {
        int count = 0;
        List<Long> contestList = new ArrayList<>();
        List<Long> collectionCards = new ArrayList<>();
        try {
            collectionCards.add(contestCollectionCardDao.get(collectionCardId).getId_());
            while(!collectionCards.isEmpty()) {
                for(Contest contest: contestService.getContestsMatchingOntologyTerms(Arrays.asList(contestCollectionCardDao.get(collectionCards.get(0)).getOntology_term_to_load()))) {
                    if(!contestList.contains(contest.getContestPK())) {
                        contestList.add(contest.getContestPK());
                    }
                }

                for(ContestCollectionCard contestCollectionCard : contestCollectionCardDao.findByGiven(collectionCards.get(0))) {
                    collectionCards.add(contestCollectionCard.getId_());
                }
                collectionCards.remove(0);
            }
            count = contestList.size();
        } catch(NotFoundException ignored) {

        }
        return count;
    }
}
