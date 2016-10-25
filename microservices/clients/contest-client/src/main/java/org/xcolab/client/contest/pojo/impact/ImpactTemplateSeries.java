package org.xcolab.client.contest.pojo.impact;

import org.xcolab.util.http.client.RestService;

public class ImpactTemplateSeries extends AbstractImpactTemplateSeries {

    public ImpactTemplateSeries() {}

    public ImpactTemplateSeries(ImpactTemplateSeries value) {
        super(value);
    }

    public ImpactTemplateSeries(AbstractImpactTemplateSeries abstractImpactTemplateSeries,
            RestService restService) {
        super(abstractImpactTemplateSeries);
    }
}
