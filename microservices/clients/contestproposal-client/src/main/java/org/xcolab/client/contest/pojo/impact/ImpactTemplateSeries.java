package org.xcolab.client.contest.pojo.impact;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ImpactTemplateSeries extends AbstractImpactTemplateSeries {

    public static final TypeProvider<ImpactTemplateSeries> TYPES =
            new TypeProvider<>(ImpactTemplateSeries.class,
                    new ParameterizedTypeReference<List<ImpactTemplateSeries>>() {});

    public ImpactTemplateSeries() {}

    public ImpactTemplateSeries(ImpactTemplateSeries value) {
        super(value);
    }

    public ImpactTemplateSeries(AbstractImpactTemplateSeries abstractImpactTemplateSeries) {
        super(abstractImpactTemplateSeries);
    }
}
