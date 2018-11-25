package org.xcolab.client.proposals.pojo.attributes;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.sql.Timestamp;
import java.util.List;

public class ProposalUnversionedAttribute extends AbstractProposalUnversionedAttribute {

    public static final TypeProvider<ProposalUnversionedAttribute> TYPES =
            new TypeProvider<>(ProposalUnversionedAttribute.class,
                    new ParameterizedTypeReference<List<ProposalUnversionedAttribute>>() {});

    public ProposalUnversionedAttribute() {}

    public ProposalUnversionedAttribute(ProposalUnversionedAttribute value) {
        super(value);
    }

    public ProposalUnversionedAttribute(
            Long id,
            Long proposalid,
            Long createauthorUserId,
            Long lastauthorUserId,
            Timestamp createdAt,
            Timestamp updatedAt,
            String name,
            Integer addtionalid,
            Long numericvalue,
            String stringvalue,
            Double realvalue
    ) {
        super(id, proposalid, createauthorUserId, lastauthorUserId, createdAt, updatedAt,
                name, addtionalid, numericvalue, stringvalue, realvalue);
    }

    public ProposalUnversionedAttribute(AbstractProposalUnversionedAttribute
            abstractProposalUnversionedAttribute,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalUnversionedAttribute);
    }
}
