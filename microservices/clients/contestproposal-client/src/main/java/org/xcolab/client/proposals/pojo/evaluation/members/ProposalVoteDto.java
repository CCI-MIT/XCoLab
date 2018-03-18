package org.xcolab.client.proposals.pojo.evaluation.members;

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
public class ProposalVoteDto extends AbstractProposalVote
        implements DataTransferObject<ProposalVote> {

    public static final TypeProvider<ProposalVoteDto> TYPES =
            new TypeProvider<>(ProposalVoteDto.class,
                    new ParameterizedTypeReference<List<ProposalVoteDto>>() {
                    });

    public ProposalVoteDto() {}

    public ProposalVoteDto(AbstractProposalVote value) {
        super(value);
    }

    @Override
    public ProposalVote toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalVote(this, serviceNamespace);
    }
}
