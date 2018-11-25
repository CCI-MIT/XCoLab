package org.xcolab.client.proposals.pojo.evaluation.judges;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ProposalRatingValue extends AbstractProposalRatingValue {

    public static final TypeProvider<ProposalRatingValue> TYPES =
            new TypeProvider<>(ProposalRatingValue.class,
                    new ParameterizedTypeReference<List<ProposalRatingValue>>() {});

    public ProposalRatingValue() {}

    public ProposalRatingValue(ProposalRatingValue value) {
        super(value);
    }

    public ProposalRatingValue(
        Long   id,
        Long   ratingtypeid,
        Long   value,
        String name,
        String description
    ) {
        super(id, ratingtypeid, value, name, description);
    }

    public ProposalRatingValue(AbstractProposalRatingValue abstractProposalRatingValue,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalRatingValue);
    }
}
