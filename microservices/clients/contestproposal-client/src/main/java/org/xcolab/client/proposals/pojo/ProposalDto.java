package org.xcolab.client.proposals.pojo;

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
public class ProposalDto extends AbstractProposal
        implements DataTransferObject<Proposal> {

    private static final long serialVersionUID = 1;

    public static final TypeProvider<ProposalDto> TYPES =
            new TypeProvider<>(ProposalDto.class,
                    new ParameterizedTypeReference<List<ProposalDto>>() {
                    });

    public ProposalDto() {}

    public ProposalDto(AbstractProposal value) {
        super(value);
    }

    @Override
    public Proposal toPojo(ServiceNamespace serviceNamespace) {
        return new Proposal(this, serviceNamespace);
    }
}
