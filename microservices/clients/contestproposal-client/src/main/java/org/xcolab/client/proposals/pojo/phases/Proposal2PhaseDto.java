package org.xcolab.client.proposals.pojo.phases;

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
public class Proposal2PhaseDto extends AbstractProposal2Phase
        implements DataTransferObject<Proposal2Phase> {

    public static final TypeProvider<Proposal2PhaseDto> TYPES =
            new TypeProvider<>(Proposal2PhaseDto.class,
                    new ParameterizedTypeReference<List<Proposal2PhaseDto>>() {
                    });


    public Proposal2PhaseDto() {}

    public Proposal2PhaseDto(AbstractProposal2Phase value) {
        super(value);
    }

    @Override
    public Proposal2Phase toPojo(ServiceNamespace serviceNamespace) {
        return new Proposal2Phase(this, serviceNamespace);
    }
}
