package org.xcolab.client.contest.pojo.templates;

import org.xcolab.util.http.client.RestService;

public class PlanTemplateSection extends AbstractPlanTemplateSection {

    public PlanTemplateSection() {}

    public PlanTemplateSection(PlanTemplateSection value) {
        super(value);
    }

    public PlanTemplateSection(AbstractPlanTemplateSection abstractPlanTemplateSection,
            RestService restService) {
        super(abstractPlanTemplateSection);
    }
}
