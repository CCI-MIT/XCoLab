package org.xcolab.client.contest.pojo;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class ContestCollectionCard extends AbstractContestCollectionCard {

    public ContestCollectionCard() {}

    public ContestCollectionCard(ContestCollectionCard value) {
        super(value);
    }

    public ContestCollectionCard(AbstractContestCollectionCard abstractContestCollectionCard,
            ServiceNamespace serviceNamespace) {
        super(abstractContestCollectionCard);
    }
}
