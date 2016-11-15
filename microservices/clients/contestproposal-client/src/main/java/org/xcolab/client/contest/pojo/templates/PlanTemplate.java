package org.xcolab.client.contest.pojo.templates;

import org.xcolab.util.http.client.RestService;

public class PlanTemplate extends AbstractPlanTemplate {

    public PlanTemplate() {}

    public PlanTemplate(PlanTemplate value) {
        super(value);
    }

    public PlanTemplate(AbstractPlanTemplate abstractPlanTemplate, RestService restService) {
        super(abstractPlanTemplate);
    }
}
