package org.xcolab.client.proposals.pojo.evaluation.judges;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ProposalRatingType extends AbstractProposalRatingType {

    public static final TypeProvider<ProposalRatingType> TYPES =
            new TypeProvider<>(ProposalRatingType.class,
                    new ParameterizedTypeReference<List<ProposalRatingType>>() {});

    public ProposalRatingType() {}

    public ProposalRatingType(ProposalRatingType value) {
        super(value);
    }

    public ProposalRatingType(
            Long id,
            String label,
            String description,
            Integer judgetype,
            Boolean isactive
    ) {
        super(id, label, description, judgetype, isactive);
    }

    public ProposalRatingType(AbstractProposalRatingType abstractProposalRatingType,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalRatingType);
    }
}
