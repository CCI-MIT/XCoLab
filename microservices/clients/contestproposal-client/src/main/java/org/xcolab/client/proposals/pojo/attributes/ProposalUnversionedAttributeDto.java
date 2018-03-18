package org.xcolab.client.proposals.pojo.attributes;

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
public class ProposalUnversionedAttributeDto extends AbstractProposalUnversionedAttribute
        implements DataTransferObject<ProposalUnversionedAttribute> {

    public static final TypeProvider<ProposalUnversionedAttributeDto> TYPES =
            new TypeProvider<>(ProposalUnversionedAttributeDto.class,
                    new ParameterizedTypeReference<List<ProposalUnversionedAttributeDto>>() {
                    });

    public ProposalUnversionedAttributeDto() {}

    public ProposalUnversionedAttributeDto(AbstractProposalUnversionedAttribute value) {
        super(value);
    }

    @Override
    public ProposalUnversionedAttribute toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalUnversionedAttribute(this, serviceNamespace);
    }
}
