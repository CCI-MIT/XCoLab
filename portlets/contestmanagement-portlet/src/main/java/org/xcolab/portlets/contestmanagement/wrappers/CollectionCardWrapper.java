package org.xcolab.portlets.contestmanagement.wrappers;


import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionCardWrapper {

    private ContestCollectionCard contestCollectionCard;
    private Boolean createNew = false;
    private static final String REFERENCE_NULL_IDENTIFIER = "none";

    public CollectionCardWrapper(long collectionCardId, long bigOntologyTerm, long ontologyTermToLoad, long smallOntologyTerm, boolean onlyFeatured, boolean visible, long parentId) {
        contestCollectionCard = new ContestCollectionCard();
        contestCollectionCard.setOntology_term_to_load(ontologyTermToLoad);
        contestCollectionCard.setId_(collectionCardId);
        contestCollectionCard.setBig_ontology_term(bigOntologyTerm);
        contestCollectionCard.setSmall_ontology_term(smallOntologyTerm);
        contestCollectionCard.setParent(parentId);
        contestCollectionCard.setOnly_featured(onlyFeatured);
        contestCollectionCard.setVisible(visible);
    }

    public CollectionCardWrapper() {
        contestCollectionCard = new ContestCollectionCard();
    }

    public CollectionCardWrapper(long collectionCardId) {
        this.contestCollectionCard = ContestClientUtil.getContestCollectionCard(collectionCardId);
    }

    public long getId() {
        return contestCollectionCard.getId_();
    }

    public void setId(long id) { contestCollectionCard.setId_(id);}

    public Boolean getCreateNew() {
        return createNew;
    }

    public void setCreateNew(Boolean createNew) {
        this.createNew = createNew;
    }

    public List<CollectionCardWrapper> getAllCollectionCards() {
        List<CollectionCardWrapper> cardList = new ArrayList<>();
        for(ContestCollectionCard contestCollectionCard: ContestClientUtil.getAllContestCollectionCards()){
            cardList.add(new CollectionCardWrapper(contestCollectionCard));
        }
        return cardList;
    }

    public void persist() {
        if(createNew){
            contestCollectionCard = ContestClientUtil.createContestCollectionCard(contestCollectionCard);
        } else {
            ContestClientUtil.updateContestCollectionCard(this.contestCollectionCard);
        }
    }

    public Map<Long , String > getOntologyTerms() {
        Map<Long, String> ontologyTerms = new HashMap<>();

        ontologyTerms.put(1L -1, REFERENCE_NULL_IDENTIFIER);
        for(OntologyTerm term: OntologyClientUtil.getAllOntologyTerms()) {
            ontologyTerms.put(term.getId(), term.getName());
        }

        List<Map.Entry<Long, String>> list =
                new LinkedList<>( ontologyTerms.entrySet() );
        Collections.sort(list, new Comparator<Entry<Long, String>>() {
            public int compare(Map.Entry<Long, String> o1,
                    Map.Entry<Long, String> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<Long, String> sortedOntologyTerms = new LinkedHashMap<>();
        for (Map.Entry<Long, String> entry : list)
        {
            sortedOntologyTerms.put( entry.getKey(), entry.getValue() );
        }

        return sortedOntologyTerms;
    }

    public Map<Long , String > getCollectionCards() {
        Map<Long, String> cards = new HashMap<>();
        cards.put(1L -1, REFERENCE_NULL_IDENTIFIER);
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

    public String getShortName() {
        return contestCollectionCard.getShort_name();
    }

    public void setShortName(String shortName) {
        contestCollectionCard.setShort_name(shortName);
    }

    public String getOntologyTermToLoad() {
        if(contestCollectionCard.getOntology_term_to_load() != null) {
            return OntologyClientUtil.getOntologyTerm(contestCollectionCard.getOntology_term_to_load()).getName();
        }
        return "";
    }

    public long getOntologyTermToLoadId() {
        Long term = contestCollectionCard.getOntology_term_to_load();
        return term != null ? term : -1;
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
        Long term = contestCollectionCard.getBig_ontology_term();
        return term != null ? term : -1;
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
        Long term = contestCollectionCard.getSmall_ontology_term();
        return term != null ? term : -1;
    }

    public void setSmallOntologyTerm(long smallOntologyTermId) {
        contestCollectionCard.setSmall_ontology_term(smallOntologyTermId);
    }

    public long getParentId() {
        Long parent = this.contestCollectionCard.getParent();
        return  parent != null ?  parent : -1;
    }

    public void setParentId(Long parentId) {
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
        Integer order = contestCollectionCard.getOrder();
        return  order != null ? order : 0;
    }

    public void setOrder(int order) {
        contestCollectionCard.setOrder(order);
    }



}
