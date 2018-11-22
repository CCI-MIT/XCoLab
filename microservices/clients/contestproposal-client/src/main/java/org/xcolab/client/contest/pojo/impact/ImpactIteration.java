package org.xcolab.client.contest.pojo.impact;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ImpactIteration extends AbstractImpactIteration {

    public static final TypeProvider<ImpactIteration> TYPES =
            new TypeProvider<>(ImpactIteration.class,
                    new ParameterizedTypeReference<List<ImpactIteration>>() {});

    public ImpactIteration() {}

    public ImpactIteration(ImpactIteration value) {
        super(value);
    }

    public ImpactIteration(AbstractImpactIteration abstractImpactIteration,
            ServiceNamespace serviceNamespace) {
        super(abstractImpactIteration);
    }
}
