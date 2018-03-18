package org.xcolab.client.contest.pojo.impact;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class ImpactTemplateSeries extends AbstractImpactTemplateSeries {

    public ImpactTemplateSeries() {}

    public ImpactTemplateSeries(ImpactTemplateSeries value) {
        super(value);
    }

    public ImpactTemplateSeries(AbstractImpactTemplateSeries abstractImpactTemplateSeries,
            ServiceNamespace serviceNamespace) {
        super(abstractImpactTemplateSeries);
    }
}
