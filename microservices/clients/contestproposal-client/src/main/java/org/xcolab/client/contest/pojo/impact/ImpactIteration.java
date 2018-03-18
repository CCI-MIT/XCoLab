package org.xcolab.client.contest.pojo.impact;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class ImpactIteration extends AbstractImpactIteration {

    public ImpactIteration() {}

    public ImpactIteration(ImpactIteration value) {
        super(value);
    }

    public ImpactIteration(AbstractImpactIteration abstractImpactIteration,
            ServiceNamespace serviceNamespace) {
        super(abstractImpactIteration);
    }
}
