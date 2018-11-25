package org.xcolab.client.proposals.pojo.evaluation.members;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ProposalVote extends AbstractProposalVote {

    public static final TypeProvider<ProposalVote> TYPES =
            new TypeProvider<>(ProposalVote.class,
                    new ParameterizedTypeReference<List<ProposalVote>>() {});

    public ProposalVote() {}

    public ProposalVote(ProposalVote value) {
        super(value);
    }

    public ProposalVote(AbstractProposalVote abstractProposalVote,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalVote);
    }
}
