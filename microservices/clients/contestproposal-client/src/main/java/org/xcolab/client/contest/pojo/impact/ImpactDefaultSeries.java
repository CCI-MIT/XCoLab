package org.xcolab.client.contest.pojo.impact;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class ImpactDefaultSeries extends AbstractImpactDefaultSeries {

    public ImpactDefaultSeries() {}

    public ImpactDefaultSeries(ImpactDefaultSeries value) {
        super(value);
    }

    public ImpactDefaultSeries(AbstractImpactDefaultSeries abstractImpactDefaultSeries,
            ServiceNamespace serviceNamespace) {
        super(abstractImpactDefaultSeries);
    }
}
