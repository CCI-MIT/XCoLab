package org.xcolab.portlets.contestmanagement.wrappers;


import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;

import java.util.ArrayList;
import java.util.List;

public class CollectionCardWrapper {

    private final ContestCollectionCard contestCollectionCard;

    public CollectionCardWrapper(long collectionCardId) {
        this.contestCollectionCard = ContestClientUtil.getContestCollectionCard(collectionCardId);
    }

    public CollectionCardWrapper(ContestCollectionCard contestCollectionCard) {
        this.contestCollectionCard = contestCollectionCard;
    }

    public List<CollectionCardWrapper> getChildren() {
        List<CollectionCardWrapper> childList = new ArrayList<>();
        for(ContestCollectionCard contestCollectionCard: ContestClientUtil.getSubContestCollectionCards(this.contestCollectionCard.getId_())){
            childList.add(new CollectionCardWrapper(contestCollectionCard));
        }
        return childList;
    }

    public String getDescription() {
        return contestCollectionCard.getDescription();
    }

    public OntologyTerm getOntologyTermToLoad() {
        return OntologyClientUtil.getOntologyTerm(contestCollectionCard.getOntology_term_to_load());
    }

}
