package org.xcolab.portlets.contestmanagement.wrappers;


import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Long , String > getOntologyTerms() {
        Map<Long, String> ontologyTerms = new HashMap<>();
        ontologyTerms.put((long) (-1), "null");
        for(OntologyTerm term: OntologyClientUtil.getAllOntologyTerms()) {
            ontologyTerms.put(term.getId(), term.getName());
        }
        return ontologyTerms;
    }

    public Map<Long , String > getCollectionCards() {
        Map<Long, String> cards = new HashMap<>();
        cards.put((long) (-1), "null");
        for(ContestCollectionCard card: ContestClientUtil.getAllContestCollectionCards()) {
            cards.put(card.getId_(), card.getDescription());
        }
        return cards;
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

    public long getOntologyTermToLoadId() {
        return contestCollectionCard.getOntology_term_to_load() != null ? contestCollectionCard.getOntology_term_to_load() : -1;
    }

    public void setOntologyTermToLoad(long ontologyTermToLoadId) {
        contestCollectionCard.setOntology_term_to_load(ontologyTermToLoadId);
    }

    public String getBigOntologyTerm() {
        if(contestCollectionCard.getBig_ontology_term() != null) {
            return OntologyClientUtil.getOntologyTerm(contestCollectionCard.getBig_ontology_term()).getName();
        }
        return "";
    }

    public long getBigOntologyTermId() {
        return contestCollectionCard.getBig_ontology_term() != null ? contestCollectionCard.getBig_ontology_term() : -1;
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

    public long getSmallOntologyTermId() {
        return contestCollectionCard.getSmall_ontology_term() != null ? contestCollectionCard.getSmall_ontology_term() : -1;
    }

    public void setSmallOntologyTerm(long smallOntologyTermId) {
        contestCollectionCard.setBig_ontology_term(smallOntologyTermId);
    }

    public long getParentId() {
        return this.contestCollectionCard.getParent() != null ?  this.contestCollectionCard.getParent() : -1;
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
