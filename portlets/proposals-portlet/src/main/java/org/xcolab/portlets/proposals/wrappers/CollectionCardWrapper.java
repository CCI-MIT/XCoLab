package org.xcolab.portlets.proposals.wrappers;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionCardWrapper{

//TODO: NotFoundexception

    protected final ContestCollectionCard contestCollectionCard;

    public CollectionCardWrapper(long contestCollectionCardId) {
        this(ContestClientUtil.getContestCollectionCard(contestCollectionCardId));
    }

    public CollectionCardWrapper(ContestCollectionCard contestCollectionCard) {
       this.contestCollectionCard = contestCollectionCard;
    }

    public CollectionCardWrapper getParent() {
        if(hasParent()){
            return new CollectionCardWrapper(ContestClientUtil.getContestCollectionCard(this.contestCollectionCard.getParent()));
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

    public int getNumberOfContests() {
        int count = 0;
        List<ContestCollectionCard> childList = new ArrayList<>();
        childList.add(ContestClientUtil.getContestCollectionCard(this.getId()));
        while(!childList.isEmpty()) {
            count += ContestClientUtil.getNumberOfContestMatchingOntologyTerms(
                    Arrays.asList(childList.get(0).getOntology_term_to_load()));
            childList.addAll(ContestClientUtil.getSubContestCollectionCards(childList.get(0).getId_()));
            childList.remove(0);
        }
        return count;
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

}
