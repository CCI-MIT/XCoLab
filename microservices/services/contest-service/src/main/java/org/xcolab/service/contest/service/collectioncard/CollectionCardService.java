package org.xcolab.service.contest.service.collectioncard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.ContestCollectionCard;
import org.xcolab.service.contest.domain.contestcollectioncard.ContestCollectionCardDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.contest.ContestService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionCardService {

    private final ContestService contestService;

    private final ContestCollectionCardDao contestCollectionCardDao;

    private final static String VIEW_TYPE_GRID = "GRID";
    private final static String VIEW_TYPE_LIST = "LIST";
    private final static String VIEW_TYPE_OUTLINE = "OUTLINE";

    @Autowired
    public CollectionCardService(ContestService contestService, ContestCollectionCardDao contestCollectionCardDao) {


        this.contestService = contestService;
        this.contestCollectionCardDao=contestCollectionCardDao;
    }

    public int getNumberOfContestsInCollectionCard(Long collectionCardId, Boolean getActive, String viewType, boolean onlyFeatured) {


        int count = 0;
        List<Long> contestList = new ArrayList<>();
        List<Long> collectionCards = new ArrayList<>();
        try {
            collectionCards.add(contestCollectionCardDao.get(collectionCardId).getId_());
            while(!collectionCards.isEmpty()) {
                for(Contest contest: contestService.getContestsByOntologyTerm(contestCollectionCardDao.get(collectionCards.get(0)).getOntology_term_to_load(), getActive)) {
                    if(!contestList.contains(contest.getContestPK())) {
                        if(     (!onlyFeatured || contest.getFeatured_()) &&
                                (viewType.equals(VIEW_TYPE_GRID) && contest.getShow_in_tile_view()) ||
                                (viewType.equals(VIEW_TYPE_LIST) && contest.getShow_in_list_view()) ||
                                (viewType.equals(VIEW_TYPE_OUTLINE) && contest.getShow_in_outline_view())) {
                            contestList.add(contest.getContestPK());
                        }
                    }
                }
                for(ContestCollectionCard childCollectionCards : contestCollectionCardDao.findByGiven(collectionCards.get(0))) {
                    collectionCards.add(childCollectionCards.getId_());
                }
                collectionCards.remove(0);
            }
            count = contestList.size();
        } catch(NotFoundException ignored) {

        }
        return count;
    }

    public boolean deleteContestCollectionCardAndMoveChildren(long collectionCardId) {
        try {
            if(contestCollectionCardDao.get(collectionCardId).getParent() == null){
                return false;
            }
            long parentId = contestCollectionCardDao.get(collectionCardId).getParent();
            for(ContestCollectionCard card : contestCollectionCardDao.findByGiven(collectionCardId)) {
                card.setParent(parentId);
                contestCollectionCardDao.update(card);
            }
            return contestCollectionCardDao.delete(collectionCardId);
        } catch (NotFoundException e) {
            return false;
        }
    }

}
