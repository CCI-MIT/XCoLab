package org.xcolab.client.proposals.pojo.attributes;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ProposalAttribute extends AbstractProposalAttribute {

    public static final TypeProvider<ProposalAttribute> TYPES =
            new TypeProvider<>(ProposalAttribute.class,
                    new ParameterizedTypeReference<List<ProposalAttribute>>() {});

    public ProposalAttribute() {}

    public ProposalAttribute(ProposalAttribute value) {
        super(value);
    }

    public ProposalAttribute(
            Long id,
            Long proposalid,
            Integer version,
            String name,
            Long additionalid,
            Long numericvalue,
            String stringvalue,
            Double realvalue
    ) {
        super(id, proposalid, version, name, additionalid, numericvalue, stringvalue, realvalue);
    }

    public ProposalAttribute(AbstractProposalAttribute abstractProposalAttribute) {
        super(abstractProposalAttribute);
    }
}
