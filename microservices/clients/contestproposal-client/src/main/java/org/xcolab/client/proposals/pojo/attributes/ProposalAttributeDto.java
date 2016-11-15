package org.xcolab.client.proposals.pojo.attributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalAttributeDto extends AbstractProposalAttribute
        implements DataTransferObject<ProposalAttribute> {

    public static final TypeProvider<ProposalAttributeDto> TYPES =
            new TypeProvider<>(ProposalAttributeDto.class,
                    new ParameterizedTypeReference<List<ProposalAttributeDto>>() {
                    });

    public ProposalAttributeDto() {}

    public ProposalAttributeDto(AbstractProposalAttribute value) {
        super(value);
    }

    @Override
    public ProposalAttribute toPojo(RestService restService) {
        return new ProposalAttribute(this, restService);
    }
}
