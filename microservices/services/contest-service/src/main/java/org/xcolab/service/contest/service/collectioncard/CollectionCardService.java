package org.xcolab.service.contest.service.collectioncard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.service.contest.domain.contestcollectioncard.ContestCollectionCardDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.contest.ContestService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionCardService {

    private final ContestService contestService;
    private final ContestCollectionCardDao contestCollectionCardDao;

    private static final String VIEW_TYPE_GRID = "GRID";
    private static final String VIEW_TYPE_LIST = "LIST";
    private static final String VIEW_TYPE_OUTLINE = "OUTLINE";

    @Autowired
    public CollectionCardService(ContestService contestService, ContestCollectionCardDao contestCollectionCardDao) {
        this.contestService = contestService;
        this.contestCollectionCardDao=contestCollectionCardDao;
    }

    public int getNumberOfContestsInCollectionCard(Long collectionCardId, Boolean getActive, String viewType, Boolean onlyFeatured) {
        int count = 0;
        try {
            List<Long> collectionCards = new ArrayList<>();
            collectionCards.add(contestCollectionCardDao.get(collectionCardId).getId());
            List<Long> contestList = new ArrayList<>();
            while(!collectionCards.isEmpty()) {
                for(ContestWrapper contest: contestService.getContestsByOntologyTerm(contestCollectionCardDao.get(collectionCards.get(0)).getOntologyTermToLoad(), getActive, false)) {
                    if(!contestList.contains(contest.getId())) {
                        if(     (!onlyFeatured || contest.isFeatured())                                   &&
                                (   (viewType.equals(VIEW_TYPE_GRID) && contest.isShowInTileView())     ||
                                    (viewType.equals(VIEW_TYPE_LIST) && contest.isShowInListView())     ||
                                    (viewType.equals(VIEW_TYPE_OUTLINE) && contest.isShowInOutlineView())
                                )){
                            contestList.add(contest.getId());
                        }
                    }
                }
                for(IContestCollectionCard childCollectionCard : contestCollectionCardDao.findByGiven(collectionCards.get(0))) {
                    if(childCollectionCard.isVisible()) {
                        collectionCards.add(childCollectionCard.getId());
                    }
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
            for(IContestCollectionCard card : contestCollectionCardDao.findByGiven(collectionCardId)) {
                card.setParent(parentId);
                contestCollectionCardDao.update(card);
            }
            return contestCollectionCardDao.delete(collectionCardId);
        } catch (NotFoundException e) {
            return false;
        }
    }

}
