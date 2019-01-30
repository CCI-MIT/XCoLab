package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;

public class CollectionCardWrapper{

//TODO COLAB-2627: NotFoundexception

    protected final IContestCollectionCard contestCollectionCard;
    protected String viewType;

    public CollectionCardWrapper(long contestCollectionCardId, String viewType) {
        this(StaticContestContext.getContestClient()
                .getContestCollectionCard(contestCollectionCardId), viewType);
        this.viewType=viewType;
    }

    public CollectionCardWrapper(IContestCollectionCard contestCollectionCard, String viewType) {
       this.contestCollectionCard = contestCollectionCard;
        this.viewType=viewType;
    }

    public CollectionCardWrapper getParent() {
        if(hasParent()){
            return new CollectionCardWrapper(StaticContestContext.getContestClient()
                    .getContestCollectionCard(this.contestCollectionCard.getParent()),
                    this.viewType);
        } else {
            return null;
        }
    }

    public boolean hasParent() {
        if(this.contestCollectionCard.getParent() != null) {
            return StaticContestContext.getContestClient()
                    .getContestCollectionCard(this.contestCollectionCard.getParent()) != null;
        }
        return false;
    }

    public int getNumberOfActiveContests() {
        return StaticContestContext.getContestClient().getNumberOfActiveContestsInCollectionCard(
                this.getId(), viewType, this.getOnlyFeatured());
    }

    public int getNumberOfAllContests() {
        return StaticContestContext.getContestClient().getNumberOfAllContestsInCollectionCard(
                this.getId(), viewType, this.getOnlyFeatured());
    }

    public int getNumberOfPriorContests() {
        return StaticContestContext.getContestClient().getNumberOfPriorContestsInCollectionCard(
                this.getId(), viewType, this.getOnlyFeatured());
    }

    public long getId() {
       return this.contestCollectionCard.getId();
    }

    public OntologyTermWrapper getBigOntologyTerm() {
        return StaticContestContext.getOntologyClient()
                .getOntologyTerm(this.contestCollectionCard.getBigOntologyTerm());
    }

    public OntologyTermWrapper getSmallOntologyTerm() {
        return StaticContestContext.getOntologyClient()
                .getOntologyTerm(this.contestCollectionCard.getSmallOntologyTerm());
    }

    public OntologyTermWrapper getOntologyTermToLoad() {
        return StaticContestContext.getOntologyClient()
                .getOntologyTerm(this.contestCollectionCard.getOntologyTermToLoad());
    }

    public int getOrder() {
        return this.contestCollectionCard.getSortOrder();
    }

    public boolean getOnlyFeatured() {
        return this.contestCollectionCard.isOnlyFeatured();
    }

    public boolean getVisible() {
        return this.contestCollectionCard.isVisible();
    }

    public String getDescription() {
        return this.contestCollectionCard.getDescription();
    }

    public String getShortName() {
        return this.contestCollectionCard.getShortName();
    }
}
