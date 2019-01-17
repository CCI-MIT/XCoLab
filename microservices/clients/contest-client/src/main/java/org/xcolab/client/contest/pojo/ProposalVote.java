package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalVote extends AbstractProposalVote implements Serializable {

    public static final TypeProvider<ProposalVote> TYPES =
            new TypeProvider<>(ProposalVote.class,
                    new ParameterizedTypeReference<List<ProposalVote>>() {});

    public ProposalVote() {}

    public ProposalVote(ProposalVote value) {
        super(value);
    }

    public ProposalVote(AbstractProposalVote abstractProposalVote) {
        super(abstractProposalVote);
    }
}
