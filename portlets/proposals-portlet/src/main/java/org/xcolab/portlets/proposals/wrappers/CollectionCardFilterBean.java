package org.xcolab.portlets.proposals.wrappers;

import java.util.List;

public class CollectionCardFilterBean {
    private final List<CollectionCardWrapper> collectionCards;

    private List<CollectionCardWrapper> collectionCardsFeatured;

    // todo:  SortFilterPage

    public CollectionCardFilterBean(List<CollectionCardWrapper> collectionCards) {
        this.collectionCards = collectionCards;


        // filter collectionCards

        for (CollectionCardWrapper card: collectionCards) {
            if(card.getOnlyFeatured()){
                collectionCardsFeatured.add(card);
            }
        }



        // sort collectionCards

        /*
        if (StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
            sortColumn = ContestsColumn.valueOf(sortFilterPage.getSortColumn());
        }
        else {
            sortColumn = sortColumnConstruct == null ? ContestsColumn.DEFAULT : sortColumnConstruct;
        }
        Collections.sort(this.contests, new Comparator<ContestWrapper>() {
            final
            @Override
            public int compare(ContestWrapper o1, ContestWrapper o2) {
                if (o1.isFeatured() && !o2.isFeatured()) {
                    return -1;
                }
                else if (! o1.isFeatured() && o2.isFeatured()) {
                    return 1;
                }
                if (sortFilterPage.isSortAscending()) {
                    return sortColumn.getColumnComparator().compare(o1, o2);
                }
                return sortColumn.getColumnComparator().compare(o2, o1);
            }
        });
        for (ContestWrapper contest: this.contests) {
            if (contest.isFeatured()) {
                contestsFeatured.add(contest);
            } else {
                contestsNormal.add(contest);
            }
        }
        */
    }

    public List<CollectionCardWrapper> getCollectionCardsFeatured() {
        return collectionCardsFeatured;
    }

  public List<CollectionCardWrapper> getCollectionCards() {
        return collectionCards;
    }



}
