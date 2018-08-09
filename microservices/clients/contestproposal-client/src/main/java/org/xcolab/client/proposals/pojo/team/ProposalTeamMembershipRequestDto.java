package org.xcolab.client.proposals.pojo.team;

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
public class ProposalTeamMembershipRequestDto extends AbstractProposalTeamMembershipRequest
        implements DataTransferObject<ProposalTeamMembershipRequest> {

    public static final TypeProvider<ProposalTeamMembershipRequestDto> TYPES =
            new TypeProvider<>(ProposalTeamMembershipRequestDto.class,
                    new ParameterizedTypeReference<List<ProposalTeamMembershipRequestDto>>() {
                    });

    public ProposalTeamMembershipRequestDto() {}

    public ProposalTeamMembershipRequestDto(AbstractProposalTeamMembershipRequest value) {
        super(value);
    }

    @Override
    public ProposalTeamMembershipRequest toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalTeamMembershipRequest(this, serviceNamespace);
    }
}
