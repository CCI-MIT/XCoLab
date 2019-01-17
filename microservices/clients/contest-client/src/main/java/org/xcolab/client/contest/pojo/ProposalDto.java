package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalDto extends AbstractProposal implements Serializable {

    public static final TypeProvider<ProposalDto> TYPES = new TypeProvider<>(ProposalDto.class,
            new ParameterizedTypeReference<List<ProposalDto>>() {});

    public ProposalDto() {
    }

    public ProposalDto(AbstractProposal proposal) {
        super(proposal);
    }

    public Proposal toProposal() {
        return new Proposal(this);
    }

    public static List<Proposal> toProposals(List<ProposalDto> dtos) {
        return dtos.stream()
                .map(Proposal::new)
                .collect(Collectors.toList());
    }
}
