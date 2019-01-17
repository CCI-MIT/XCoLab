package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalRatingType extends AbstractProposalRatingType implements Serializable {

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

    public ProposalRatingType(AbstractProposalRatingType abstractProposalRatingType) {
        super(abstractProposalRatingType);
    }
}
