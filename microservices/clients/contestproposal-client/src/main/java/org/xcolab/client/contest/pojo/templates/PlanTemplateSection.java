package org.xcolab.client.contest.pojo.templates;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class PlanTemplateSection extends AbstractPlanTemplateSection {

    public PlanTemplateSection() {}

    public PlanTemplateSection(PlanTemplateSection value) {
        super(value);
    }

    public PlanTemplateSection(AbstractPlanTemplateSection abstractPlanTemplateSection,
            ServiceNamespace serviceNamespace) {
        super(abstractPlanTemplateSection);
    }
}
