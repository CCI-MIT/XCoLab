package org.xcolab.client.contest.pojo.templates;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class PlanTemplate extends AbstractPlanTemplate {

    public PlanTemplate() {}

    public PlanTemplate(PlanTemplate value) {
        super(value);
    }

    public PlanTemplate(AbstractPlanTemplate abstractPlanTemplate,
            ServiceNamespace serviceNamespace) {
        super(abstractPlanTemplate);
    }
}
