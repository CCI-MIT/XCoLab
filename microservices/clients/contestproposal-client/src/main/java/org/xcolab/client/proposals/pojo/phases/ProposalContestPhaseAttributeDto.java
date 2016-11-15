package org.xcolab.client.proposals.pojo.phases;

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
    public ProposalContestPhaseAttribute toPojo(RestService restService) {
        return new ProposalContestPhaseAttribute(this, restService);
    }
}
