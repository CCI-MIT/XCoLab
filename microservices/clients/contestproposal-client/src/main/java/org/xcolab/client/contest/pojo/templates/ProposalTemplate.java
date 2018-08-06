package org.xcolab.client.contest.pojo.templates;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ProposalTemplate extends AbstractProposalTemplate {

    public ProposalTemplate() {}

    public ProposalTemplate(ProposalTemplate value) {
        super(value);
    }

    public ProposalTemplate(AbstractProposalTemplate abstractPlanTemplate,
            ServiceNamespace serviceNamespace) {
        super(abstractPlanTemplate);
    }
}
