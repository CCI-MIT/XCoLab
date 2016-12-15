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
    }

    public List<CollectionCardWrapper> getCollectionCardsFeatured() {
        return collectionCardsFeatured;
    }

  public List<CollectionCardWrapper> getCollectionCards() {
        return collectionCards;
    }



}
