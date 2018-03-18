package org.xcolab.client.proposals.pojo.phases;

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
public class ProposalContestPhaseAttributeDto extends AbstractProposalContestPhaseAttribute
        implements DataTransferObject<ProposalContestPhaseAttribute> {

    public static final TypeProvider<ProposalContestPhaseAttributeDto> TYPES =
            new TypeProvider<>(ProposalContestPhaseAttributeDto.class,
                    new ParameterizedTypeReference<List<ProposalContestPhaseAttributeDto>>() {
                    });

    public ProposalContestPhaseAttributeDto() {}

    public ProposalContestPhaseAttributeDto(AbstractProposalContestPhaseAttribute value) {
        super(value);
    }

    @Override
    public ProposalContestPhaseAttribute toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalContestPhaseAttribute(this, serviceNamespace);
    }
}
