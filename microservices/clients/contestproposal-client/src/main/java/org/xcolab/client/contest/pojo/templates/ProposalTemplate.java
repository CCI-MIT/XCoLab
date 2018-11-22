package org.xcolab.client.contest.pojo.templates;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ProposalTemplate extends AbstractProposalTemplate {

    public static final TypeProvider<ProposalTemplate> TYPES = new TypeProvider<>(
            ProposalTemplate.class, new ParameterizedTypeReference<List<ProposalTemplate>>() {});

    public ProposalTemplate() {}

    public ProposalTemplate(ProposalTemplate value) {
        super(value);
    }

    public ProposalTemplate(AbstractProposalTemplate abstractProposalTemplate,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalTemplate);
    }
}
