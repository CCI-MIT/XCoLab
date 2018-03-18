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
public class ProposalRatingValueDto extends AbstractProposalRatingValue
        implements DataTransferObject<ProposalRatingValue> {

    public static final TypeProvider<ProposalRatingValueDto> TYPES =
            new TypeProvider<>(ProposalRatingValueDto.class,
                    new ParameterizedTypeReference<List<ProposalRatingValueDto>>() {
                    });

    public ProposalRatingValueDto() {}

    public ProposalRatingValueDto(AbstractProposalRatingValue value) {
        super(value);
    }

    @Override
    public ProposalRatingValue toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalRatingValue(this, serviceNamespace);
    }
}
