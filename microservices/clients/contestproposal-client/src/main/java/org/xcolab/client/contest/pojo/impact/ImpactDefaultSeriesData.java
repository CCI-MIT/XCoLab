package org.xcolab.client.contest.pojo.impact;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ImpactDefaultSeriesData extends AbstractImpactDefaultSeriesData {

    public static final TypeProvider<ImpactDefaultSeriesData> TYPES =
            new TypeProvider<>(ImpactDefaultSeriesData.class,
                    new ParameterizedTypeReference<List<ImpactDefaultSeriesData>>() {});

    public ImpactDefaultSeriesData() {}

    public ImpactDefaultSeriesData(ImpactDefaultSeriesData value) {
        super(value);
    }

    public ImpactDefaultSeriesData(AbstractImpactDefaultSeriesData abstractImpactDefaultSeriesData,
            ServiceNamespace serviceNamespace) {
        super(abstractImpactDefaultSeriesData);
    }
}
