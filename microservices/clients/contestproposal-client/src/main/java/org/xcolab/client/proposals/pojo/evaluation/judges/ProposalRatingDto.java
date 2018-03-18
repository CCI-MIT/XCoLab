package org.xcolab.client.proposals.pojo.evaluation.judges;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;
import org.xcolab.commons.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalRatingDto extends AbstractProposalRating
        implements DataTransferObject<ProposalRating> {

    public static final TypeProvider<ProposalRatingDto> TYPES =
            new TypeProvider<>(ProposalRatingDto.class,
                    new ParameterizedTypeReference<List<ProposalRatingDto>>() {
                    });

    public ProposalRatingDto() {}

    public ProposalRatingDto(AbstractProposalRating value) {
        super(value);
    }

    @Override
    public ProposalRating toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalRating(this, serviceNamespace);
    }
}
