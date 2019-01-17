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
public class ProposalReference extends AbstractProposalReference implements Serializable {

    public static final TypeProvider<ProposalReference> TYPES =
            new TypeProvider<>(ProposalReference.class,
                    new ParameterizedTypeReference<List<ProposalReference>>() {});

    public ProposalReference() {}

    public ProposalReference(ProposalReference value) {
        super(value);
    }

    public ProposalReference(
            Long proposalid,
            Long subproposalid,
            Long sectionattributeid
    ) {
        super(proposalid, subproposalid, sectionattributeid);
    }

    public ProposalReference(AbstractProposalReference abstractProposalReference) {
        super(abstractProposalReference);
    }
}
