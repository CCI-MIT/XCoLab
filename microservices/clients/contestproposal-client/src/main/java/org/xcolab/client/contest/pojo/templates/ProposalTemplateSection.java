package org.xcolab.client.contest.pojo.templates;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ProposalTemplateSection extends AbstractProposalTemplateSection {

    public ProposalTemplateSection() {}

    public ProposalTemplateSection(ProposalTemplateSection value) {
        super(value);
    }

    public ProposalTemplateSection(AbstractProposalTemplateSection abstractProposalTemplateSection,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalTemplateSection);
    }
}
