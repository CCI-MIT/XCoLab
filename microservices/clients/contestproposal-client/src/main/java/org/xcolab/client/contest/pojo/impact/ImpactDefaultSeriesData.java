package org.xcolab.client.contest.pojo.impact;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ImpactDefaultSeriesData extends AbstractImpactDefaultSeriesData {

    public ImpactDefaultSeriesData() {}

    public ImpactDefaultSeriesData(ImpactDefaultSeriesData value) {
        super(value);
    }

    public ImpactDefaultSeriesData(AbstractImpactDefaultSeriesData abstractImpactDefaultSeriesData,
            ServiceNamespace serviceNamespace) {
        super(abstractImpactDefaultSeriesData);
    }
}
