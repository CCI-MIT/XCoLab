package org.xcolab.view.pages.contestmanagement.wrappers;

import org.apache.commons.text.StringEscapeUtils;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.client.contest.pojo.tables.pojos.ContestCollectionCard;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionCardWrapper {

    private static final String REFERENCE_NULL_IDENTIFIER = "none";
    private IContestCollectionCard contestCollectionCard;
    private Boolean createNew = false;

    public CollectionCardWrapper(long collectionCardId, long bigOntologyTerm,
            long ontologyTermToLoad, long smallOntologyTerm, boolean onlyFeatured, boolean visible,
            long parentId) {
        contestCollectionCard = new ContestCollectionCard();
        contestCollectionCard.setOntologyTermToLoad(ontologyTermToLoad);
        contestCollectionCard.setId(collectionCardId);
        contestCollectionCard.setBigOntologyTerm(bigOntologyTerm);
        contestCollectionCard.setSmallOntologyTerm(smallOntologyTerm);
        contestCollectionCard.setParent(parentId);
        contestCollectionCard.setOnlyFeatured(onlyFeatured);
        contestCollectionCard.setVisible(visible);
    }

    public CollectionCardWrapper() {
        contestCollectionCard = new ContestCollectionCard();
    }

    public CollectionCardWrapper(long collectionCardId) {
        this.contestCollectionCard = StaticContestContext.getContestClient()
                .getContestCollectionCard(collectionCardId);
    }

    public CollectionCardWrapper(IContestCollectionCard contestCollectionCard) {
        this.contestCollectionCard = contestCollectionCard;
    }

    public long getId() {
        return contestCollectionCard.getId();
    }

    public void setId(long id) { contestCollectionCard.setId(id);}

    public Boolean getCreateNew() {
        return createNew;
    }

    public void setCreateNew(Boolean createNew) {
        this.createNew = createNew;
    }

    public List<CollectionCardWrapper> getAllCollectionCards() {
        List<CollectionCardWrapper> cardList = new ArrayList<>();
        for (IContestCollectionCard contestCollectionCard : StaticContestContext.getContestClient()
                .getAllContestCollectionCards()) {
            cardList.add(new CollectionCardWrapper(contestCollectionCard));
        }
        return cardList;
    }

    public void persist() {
        if (createNew) {
            contestCollectionCard =
                    StaticContestContext.getContestClient()
                            .createContestCollectionCard(contestCollectionCard);
        } else {
            StaticContestContext.getContestClient()
                    .updateContestCollectionCard(this.contestCollectionCard);
        }
    }

    public Map<Long, String> getOntologyTerms() {
        Map<Long, String> ontologyTerms = new HashMap<>();

        ontologyTerms.put(1L - 1, REFERENCE_NULL_IDENTIFIER);
        for (OntologyTermWrapper term : StaticContestContext.getOntologyClient().getAllOntologyTerms()) {
            ontologyTerms.put(term.getId(), StringEscapeUtils.escapeEcmaScript(term.getName()));
        }
        return ontologyTerms;
    }

    public Map<Long, String> getCollectionCards() {
        Map<Long, String> cards = new HashMap<>();
        cards.put(1L - 1, REFERENCE_NULL_IDENTIFIER);
        for (IContestCollectionCard card : StaticContestContext.getContestClient()
                .getAllContestCollectionCards()) {
            cards.put(card.getId(), StringEscapeUtils.escapeEcmaScript(card.getShortName()));
        }
        return cards;
    }

    public List<CollectionCardWrapper> getChildren() {
        List<CollectionCardWrapper> childList = new ArrayList<>();
        for (IContestCollectionCard contestCollectionCard : StaticContestContext.getContestClient()
                .getSubContestCollectionCards(this.contestCollectionCard.getId())) {
            childList.add(new CollectionCardWrapper(contestCollectionCard));
        }
        return childList;
    }

    public String getDescription() {
        return StringEscapeUtils.escapeEcmaScript(contestCollectionCard.getDescription());
    }

    public void setDescription(String description) {
        contestCollectionCard.setDescription(description);
    }

    public String getShortName() {
        return StringEscapeUtils.escapeEcmaScript(contestCollectionCard.getShortName());
    }

    public void setShortName(String shortName) {
        contestCollectionCard.setShortName(shortName);
    }

    public String getOntologyTermToLoad() {
        if (contestCollectionCard.getOntologyTermToLoad() != null) {
            return StringEscapeUtils.escapeEcmaScript(StaticContestContext.getOntologyClient()
                    .getOntologyTerm(contestCollectionCard.getOntologyTermToLoad()).getName());
        }
        return "";
    }

    public void setOntologyTermToLoad(long ontologyTermToLoadId) {
        contestCollectionCard.setOntologyTermToLoad(ontologyTermToLoadId);
    }

    public long getOntologyTermToLoadId() {
        Long term = contestCollectionCard.getOntologyTermToLoad();
        return term != null ? term : -1;
    }

    public String getBigOntologyTerm() {
        if (contestCollectionCard.getBigOntologyTerm() != null) {
            return StringEscapeUtils.escapeEcmaScript(
                    StaticContestContext.getOntologyClient()
                            .getOntologyTerm(contestCollectionCard.getBigOntologyTerm()).getName());
        }
        return "";
    }

    public void setBigOntologyTerm(long bigOntologyTermId) {
        contestCollectionCard.setBigOntologyTerm(bigOntologyTermId);
    }

    public long getBigOntologyTermId() {
        Long term = contestCollectionCard.getBigOntologyTerm();
        return term != null ? term : -1;
    }

    public String getSmallOntologyTerm() {
        if (contestCollectionCard.getSmallOntologyTerm() != null) {
            return StringEscapeUtils.escapeEcmaScript(StaticContestContext.getOntologyClient()
                    .getOntologyTerm(contestCollectionCard.getSmallOntologyTerm()).getName());
        }
        return "";
    }

    public void setSmallOntologyTerm(long smallOntologyTermId) {
        contestCollectionCard.setSmallOntologyTerm(smallOntologyTermId);
    }

    public long getSmallOntologyTermId() {
        Long term = contestCollectionCard.getSmallOntologyTerm();
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
        return contestCollectionCard.isVisible();
    }

    public void setVisible(boolean visible) {
        contestCollectionCard.setVisible(visible);
    }

    public boolean isOnlyFeatured() {
        return contestCollectionCard.isOnlyFeatured();
    }

    public void setOnlyFeatured(boolean onlyFeatured) {
        contestCollectionCard.setOnlyFeatured(onlyFeatured);
    }

    public int getOrder() {
        Integer order = contestCollectionCard.getSortOrder();
        return order != null ? order : 0;
    }

    public void setOrder(int order) {
        contestCollectionCard.setSortOrder(order);
    }
}
