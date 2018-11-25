package org.xcolab.client.contest.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ContestTranslation extends AbstractContestTranslation {

    public static final TypeProvider<ContestTranslation> TYPES = new TypeProvider<>(
            ContestTranslation.class, new ParameterizedTypeReference<List<ContestTranslation>>() {});

    public ContestTranslation() {
    }

    public ContestTranslation(AbstractContestTranslation value) {
        super(value);
    }
}
