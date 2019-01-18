package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.pojo.tables.pojos.Proposal;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalDto extends Proposal implements Serializable {

    public static final TypeProvider<ProposalDto> TYPES = new TypeProvider<>(ProposalDto.class,
            new ParameterizedTypeReference<List<ProposalDto>>() {});

    public ProposalDto() {
    }

    public ProposalDto(Proposal proposal) {
        super(proposal);
    }

    public ProposalWrapper toProposal() {
        return new ProposalWrapper(this);
    }

    public static List<ProposalWrapper> toProposals(List<ProposalDto> dtos) {
        return dtos.stream()
                .map(ProposalWrapper::new)
                .collect(Collectors.toList());
    }
}
