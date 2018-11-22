package org.xcolab.client.contest.pojo.impact;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ImpactDefaultSeries extends AbstractImpactDefaultSeries {

    public static final TypeProvider<ImpactDefaultSeries> TYPES = new TypeProvider<>(ImpactDefaultSeries.class,
            new ParameterizedTypeReference<List<ImpactDefaultSeries>>() {});

    public ImpactDefaultSeries() {}

    public ImpactDefaultSeries(ImpactDefaultSeries value) {
        super(value);
    }

    public ImpactDefaultSeries(AbstractImpactDefaultSeries abstractImpactDefaultSeries,
            ServiceNamespace serviceNamespace) {
        super(abstractImpactDefaultSeries);
    }
}
