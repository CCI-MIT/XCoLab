package org.xcolab.portlets.contestmanagement.wrappers;


import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ContestCollectionCard;

import java.util.ArrayList;
import java.util.List;

public class CollectionCardWrapper {

    private final ContestCollectionCard contestCollectionCard;

    public CollectionCardWrapper(long collectionCardId) {
        this.contestCollectionCard = ContestClientUtil.getContestCollectionCard(collectionCardId);
    }

    public long getId() {
        return contestCollectionCard.getId_();
    }

    public List<CollectionCardWrapper> getAllCollectionCards() {
        List<CollectionCardWrapper> cardList = new ArrayList<>();
        for(ContestCollectionCard contestCollectionCard: ContestClientUtil.getAllContestCollectionCards()){
            cardList.add(new CollectionCardWrapper(contestCollectionCard));
        }
        return cardList;
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

    public void setDescription(String description) {
        contestCollectionCard.setDescription(description);
    }

    public String getOntologyTermToLoad() {
        if(contestCollectionCard.getOntology_term_to_load() != null) {
            return OntologyClientUtil.getOntologyTerm(contestCollectionCard.getOntology_term_to_load()).getName();
        }
        return "";
    }

    public void getOntologyTermToLoad(long ontologyTermToLoadId) {
        contestCollectionCard.setOntology_term_to_load(ontologyTermToLoadId);
    }

    public String getBigOntologyTerm() {
        if(contestCollectionCard.getBig_ontology_term() != null) {
            return OntologyClientUtil.getOntologyTerm(contestCollectionCard.getBig_ontology_term()).getName();
        }
        return "";
    }

    public void setBigOntologyTerm(long bigOntologyTermId) {
        contestCollectionCard.setBig_ontology_term(bigOntologyTermId);
    }

    public String getSmallOntologyTerm() {
        if(contestCollectionCard.getSmall_ontology_term() != null) {
            return OntologyClientUtil.getOntologyTerm(contestCollectionCard.getSmall_ontology_term()).getName();
        }
        return "";
    }

    public void setSmallOntologyTerm(long smallOntologyTermId) {
        contestCollectionCard.setBig_ontology_term(smallOntologyTermId);
    }

    public Long getParentId() {
        return this.contestCollectionCard.getParent();
    }

    public void setParent(Long parentId) {
        contestCollectionCard.setParent(parentId);
    }

    public boolean isVisible() {
        return contestCollectionCard.getVisible();
    }

    public void setVisible(boolean visible) {
        contestCollectionCard.setVisible(visible);
    }

    public boolean isOnlyFeatured() {
        return contestCollectionCard.getOnly_featured();
    }

    public void setOnlyFeatured(boolean onlyFeatured) {
        contestCollectionCard.setOnly_featured(onlyFeatured);
    }

    public int getOrder() {
        return contestCollectionCard.getOrder();
    }

    public void setOrder( int order) {
        contestCollectionCard.setOrder(order);
    }



}
