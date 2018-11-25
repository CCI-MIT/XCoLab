package org.xcolab.client.proposals.pojo.tiers;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ProposalReference extends AbstractProposalReference {

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
