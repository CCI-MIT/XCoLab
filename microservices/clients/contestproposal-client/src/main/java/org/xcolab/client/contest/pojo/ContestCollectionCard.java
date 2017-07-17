package org.xcolab.client.contest.pojo;

import org.xcolab.util.http.client.RestService;

public class ContestCollectionCard extends AbstractContestCollectionCard {

    public ContestCollectionCard() {}

    public ContestCollectionCard(ContestCollectionCard value) {
        super(value);
    }

    public ContestCollectionCard(AbstractContestCollectionCard abstractContestCollectionCard,
            RestService contestService) {
        super(abstractContestCollectionCard);
    }
}
