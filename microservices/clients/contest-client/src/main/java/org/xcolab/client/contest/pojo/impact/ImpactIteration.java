package org.xcolab.client.contest.pojo.impact;

import org.xcolab.util.http.client.RestService;

public class ImpactIteration extends AbstractImpactIteration {

    public ImpactIteration() {}

    public ImpactIteration(ImpactIteration value) {
        super(value);
    }

    public ImpactIteration(AbstractImpactIteration abstractImpactIteration,
            RestService restService) {
        super(abstractImpactIteration);
    }
}
