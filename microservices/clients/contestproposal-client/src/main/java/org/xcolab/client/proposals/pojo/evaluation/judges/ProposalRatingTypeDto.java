package org.xcolab.client.proposals.pojo.evaluation.judges;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalRatingTypeDto extends AbstractProposalRatingType
        implements DataTransferObject<ProposalRatingType> {

    public static final TypeProvider<ProposalRatingTypeDto> TYPES =
            new TypeProvider<>(ProposalRatingTypeDto.class,
                    new ParameterizedTypeReference<List<ProposalRatingTypeDto>>() {
                    });

    public ProposalRatingTypeDto() {}

    public ProposalRatingTypeDto(AbstractProposalRatingType value) {
        super(value);
    }

    @Override
    public ProposalRatingType toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalRatingType(this, serviceNamespace);
    }
}
