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
public class ProposalRatingValue extends AbstractProposalRatingValue implements Serializable {

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

    public ProposalRatingValue(AbstractProposalRatingValue abstractProposalRatingValue) {
        super(abstractProposalRatingValue);
    }
}
