package org.xcolab.portlets.proposals.wrappers;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.OntologyTerm;

public class CollectionCardWrapper{

//TODO: BotFoundexception

    protected final ContestCollectionCard contestCollectionCard;

    public CollectionCardWrapper(long contestCollectionCardId) {
        this(ContestClient.getContestCollectionCard(contestCollectionCardId));
    }

    public CollectionCardWrapper(ContestCollectionCard contestCollectionCard) {
       this.contestCollectionCard = contestCollectionCard;
    }

    public ContestCollectionCard getParent() {
        return ContestClient.getContestCollectionCard(this.contestCollectionCard.getParent());
    }

    public boolean hasParent() {
        if(ContestClient.getContestCollectionCard(this.contestCollectionCard.getParent()) != null) {
            return true;
        } else {
            return false;
        }
    }

    public long getId() {
       return this.contestCollectionCard.getId_();
    }

    public OntologyTerm getBigOntologyTerm() {
        return ContestClient.getOntologyTerm(this.contestCollectionCard.getBig_ontology_term());
    }

    public OntologyTerm getSmallOntologyTerm() {
        return ContestClient.getOntologyTerm(this.contestCollectionCard.getSmall_ontology_term());
    }

    public OntologyTerm getOntologyTermToLoad() {
        return ContestClient.getOntologyTerm(this.contestCollectionCard.getOntology_term_to_load());
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

}
