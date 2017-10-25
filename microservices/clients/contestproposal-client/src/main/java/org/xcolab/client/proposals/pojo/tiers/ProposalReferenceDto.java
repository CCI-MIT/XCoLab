package org.xcolab.client.proposals.pojo.tiers;

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
public class ProposalReferenceDto extends AbstractProposalReference
        implements DataTransferObject<ProposalReference> {

    public static final TypeProvider<ProposalReferenceDto> TYPES =
            new TypeProvider<>(ProposalReferenceDto.class,
                    new ParameterizedTypeReference<List<ProposalReferenceDto>>() {
                    });

    public ProposalReferenceDto() {}

    public ProposalReferenceDto(AbstractProposalReference value) {
        super(value);
    }

    @Override
    public ProposalReference toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalReference(this, serviceNamespace);
    }
}
