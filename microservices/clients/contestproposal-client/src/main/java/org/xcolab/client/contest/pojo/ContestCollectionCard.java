package org.xcolab.client.contest.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ContestCollectionCard extends AbstractContestCollectionCard {

    public static final TypeProvider<ContestCollectionCard> TYPES =
            new TypeProvider<>(ContestCollectionCard.class,
                    new ParameterizedTypeReference<List<ContestCollectionCard>>() {});

    public ContestCollectionCard() {}

    public ContestCollectionCard(ContestCollectionCard value) {
        super(value);
    }

    public ContestCollectionCard(AbstractContestCollectionCard abstractContestCollectionCard) {
        super(abstractContestCollectionCard);
    }
}
