package org.xcolab.view.pages.contestmanagement.wrappers;

import org.apache.commons.lang.StringEscapeUtils;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionCardWrapper {

    private static final String REFERENCE_NULL_IDENTIFIER = "none";
    private ContestCollectionCard contestCollectionCard;
    private Boolean createNew = false;

    public CollectionCardWrapper(long collectionCardId, long bigOntologyTerm,
            long ontologyTermToLoad, long smallOntologyTerm, boolean onlyFeatured, boolean visible,
            long parentId) {
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

    public CollectionCardWrapper(ContestCollectionCard contestCollectionCard) {
        this.contestCollectionCard = contestCollectionCard;
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
        for (ContestCollectionCard contestCollectionCard : ContestClientUtil
                .getAllContestCollectionCards()) {
            cardList.add(new CollectionCardWrapper(contestCollectionCard));
        }
        return cardList;
    }

    public void persist() {
        if (createNew) {
            contestCollectionCard =
                    ContestClientUtil.createContestCollectionCard(contestCollectionCard);
        } else {
            ContestClientUtil.updateContestCollectionCard(this.contestCollectionCard);
        }
    }

    public Map<Long, String> getOntologyTerms() {
        Map<Long, String> ontologyTerms = new HashMap<>();

        ontologyTerms.put(1L - 1, REFERENCE_NULL_IDENTIFIER);
        for (OntologyTerm term : OntologyClientUtil.getAllOntologyTerms()) {
            ontologyTerms.put(term.getId(), StringEscapeUtils.escapeJavaScript(term.getName()));
        }
        return ontologyTerms;
    }

    public Map<Long, String> getCollectionCards() {
        Map<Long, String> cards = new HashMap<>();
        cards.put(1L - 1, REFERENCE_NULL_IDENTIFIER);
        for (ContestCollectionCard card : ContestClientUtil.getAllContestCollectionCards()) {
            cards.put(card.getId_(), StringEscapeUtils.escapeJavaScript(card.getShort_name()));
        }
        return cards;
    }

    public List<CollectionCardWrapper> getChildren() {
        List<CollectionCardWrapper> childList = new ArrayList<>();
        for (ContestCollectionCard contestCollectionCard : ContestClientUtil
                .getSubContestCollectionCards(this.contestCollectionCard.getId_())) {
            childList.add(new CollectionCardWrapper(contestCollectionCard));
        }
        return childList;
    }

    public String getDescription() {
        return StringEscapeUtils.escapeJavaScript(contestCollectionCard.getDescription());
    }

    public void setDescription(String description) {
        contestCollectionCard.setDescription(description);
    }

    public String getShortName() {
        return StringEscapeUtils.escapeJavaScript(contestCollectionCard.getShort_name());
    }

    public void setShortName(String shortName) {
        contestCollectionCard.setShort_name(shortName);
    }

    public String getOntologyTermToLoad() {
        if (contestCollectionCard.getOntology_term_to_load() != null) {
            return StringEscapeUtils.escapeJavaScript(OntologyClientUtil
                    .getOntologyTerm(contestCollectionCard.getOntology_term_to_load()).getName());
        }
        return "";
    }

    public void setOntologyTermToLoad(long ontologyTermToLoadId) {
        contestCollectionCard.setOntology_term_to_load(ontologyTermToLoadId);
    }

    public long getOntologyTermToLoadId() {
        Long term = contestCollectionCard.getOntology_term_to_load();
        return term != null ? term : -1;
    }

    public String getBigOntologyTerm() {
        if (contestCollectionCard.getBig_ontology_term() != null) {
            return StringEscapeUtils.escapeJavaScript(
                    OntologyClientUtil.getOntologyTerm(contestCollectionCard.getBig_ontology_term())
                            .getName());
        }
        return "";
    }

    public void setBigOntologyTerm(long bigOntologyTermId) {
        contestCollectionCard.setBig_ontology_term(bigOntologyTermId);
    }

    public long getBigOntologyTermId() {
        Long term = contestCollectionCard.getBig_ontology_term();
        return term != null ? term : -1;
    }

    public String getSmallOntologyTerm() {
        if (contestCollectionCard.getSmall_ontology_term() != null) {
            return StringEscapeUtils.escapeJavaScript(OntologyClientUtil
                    .getOntologyTerm(contestCollectionCard.getSmall_ontology_term()).getName());
        }
        return "";
    }

    public void setSmallOntologyTerm(long smallOntologyTermId) {
        contestCollectionCard.setSmall_ontology_term(smallOntologyTermId);
    }

    public long getSmallOntologyTermId() {
        Long term = contestCollectionCard.getSmall_ontology_term();
        return term != null ? term : -1;
    }

    public long getParentId() {
        Long parent = this.contestCollectionCard.getParent();
        return parent != null ? parent : -1;
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
        return order != null ? order : 0;
    }

    public void setOrder(int order) {
        contestCollectionCard.setOrder(order);
    }

}
