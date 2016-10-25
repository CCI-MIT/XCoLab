package org.xcolab.client.contest.pojo.impact;

import org.xcolab.util.http.client.RestService;

public class ImpactDefaultSeries extends AbstractImpactDefaultSeries {

    public ImpactDefaultSeries() {}

    public ImpactDefaultSeries(ImpactDefaultSeries value) {
        super(value);
    }

    public ImpactDefaultSeries(AbstractImpactDefaultSeries abstractImpactDefaultSeries,
            RestService restService) {
        super(abstractImpactDefaultSeries);
    }
}
