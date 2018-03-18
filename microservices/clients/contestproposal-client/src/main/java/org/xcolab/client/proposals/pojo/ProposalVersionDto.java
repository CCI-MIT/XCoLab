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
public class ProposalVersionDto extends AbstractProposalVersion
        implements DataTransferObject<ProposalVersion> {

    public static final TypeProvider<ProposalVersionDto> TYPES =
            new TypeProvider<>(ProposalVersionDto.class,
                    new ParameterizedTypeReference<List<ProposalVersionDto>>() {
                    });

    public ProposalVersionDto() {}

    public ProposalVersionDto(AbstractProposalVersion value) {
        super(value);
    }

    @Override
    public ProposalVersion toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalVersion(this, serviceNamespace);
    }
}
