package org.xcolab.client.contest.pojo.templates;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ProposalTemplateSection extends AbstractProposalTemplateSection {

    public static final TypeProvider<ProposalTemplateSection> TYPES =
            new TypeProvider<>(ProposalTemplateSection.class,
                    new ParameterizedTypeReference<List<ProposalTemplateSection>>() {});

    public ProposalTemplateSection() {}

    public ProposalTemplateSection(ProposalTemplateSection value) {
        super(value);
    }

    public ProposalTemplateSection(AbstractProposalTemplateSection abstractProposalTemplateSection) {
        super(abstractProposalTemplateSection);
    }
}
