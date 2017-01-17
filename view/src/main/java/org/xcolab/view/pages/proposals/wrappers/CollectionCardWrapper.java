package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;

public class CollectionCardWrapper{

//TODO: NotFoundexception

    protected final ContestCollectionCard contestCollectionCard;
    protected String viewType;

    public CollectionCardWrapper(long contestCollectionCardId, String viewType) {
        this(ContestClientUtil.getContestCollectionCard(contestCollectionCardId), viewType);
        this.viewType=viewType;
    }

    public CollectionCardWrapper(ContestCollectionCard contestCollectionCard, String viewType) {
       this.contestCollectionCard = contestCollectionCard;
        this.viewType=viewType;
    }

    public CollectionCardWrapper getParent() {
        if(hasParent()){
            return new CollectionCardWrapper(ContestClientUtil.getContestCollectionCard(this.contestCollectionCard.getParent()), this.viewType);
        } else {
            return null;
        }
    }

    public boolean hasParent() {
        if(this.contestCollectionCard.getParent() != null) {
            if(ContestClientUtil.getContestCollectionCard(this.contestCollectionCard.getParent()) != null) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfActiveContests() {
        return ContestClientUtil.getNumberOfActiveContestsInCollectionCard(this.getId(), viewType, this.getOnlyFeatured());
    }

    public int getNumberOfAllContests() {
        return ContestClientUtil.getNumberOfAllContestsInCollectionCard(this.getId(), viewType, this.getOnlyFeatured());
    }

    public int getNumberOfPriorContests() {
        return ContestClientUtil.getNumberOfPriorContestsInCollectionCard(this.getId(), viewType, this.getOnlyFeatured());
    }

    public long getId() {
       return this.contestCollectionCard.getId_();
    }

    public OntologyTerm getBigOntologyTerm() {
        return OntologyClientUtil.getOntologyTerm(this.contestCollectionCard.getBig_ontology_term());
    }

    public OntologyTerm getSmallOntologyTerm() {
        return OntologyClientUtil.getOntologyTerm(this.contestCollectionCard.getSmall_ontology_term());
    }

    public OntologyTerm getOntologyTermToLoad() {
        return OntologyClientUtil.getOntologyTerm(this.contestCollectionCard.getOntology_term_to_load());
    }

    public int getOrder() {
        return this.contestCollectionCard.getOrder();
    }

    public boolean getOnlyFeatured() {
        return this.contestCollectionCard.getOnly_featured();
    }

    public boolean getVisible() {
        return this.contestCollectionCard.getVisible();
    }

    public String getDescription() {
        return this.contestCollectionCard.getDescription();
    }

    public String getShortName() {
        return this.contestCollectionCard.getShort_name();
    }

}
