package org.xcolab.client.contest.pojo.phases;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ContestPhaseRibbonType extends AbstractContestPhaseRibbonType {

    public static final TypeProvider<ContestPhaseRibbonType> TYPES =
            new TypeProvider<>(ContestPhaseRibbonType.class,
                    new ParameterizedTypeReference<List<ContestPhaseRibbonType>>() {});

    public ContestPhaseRibbonType() {}

    public ContestPhaseRibbonType(ContestPhaseRibbonType value) {
        super(value);
    }

    public ContestPhaseRibbonType(AbstractContestPhaseRibbonType abstractContestPhaseRibbonType,
            ServiceNamespace serviceNamespace) {
        super(abstractContestPhaseRibbonType);
    }
}
