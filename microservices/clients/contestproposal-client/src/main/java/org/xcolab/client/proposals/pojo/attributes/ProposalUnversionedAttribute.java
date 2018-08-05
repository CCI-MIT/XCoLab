package org.xcolab.client.proposals.pojo.attributes;

import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.sql.Timestamp;

public class ProposalUnversionedAttribute extends AbstractProposalUnversionedAttribute {

    public ProposalUnversionedAttribute() {}

    public ProposalUnversionedAttribute(ProposalUnversionedAttribute value) {
        super(value);
    }

    public ProposalUnversionedAttribute(
            Long id_,
            Long proposalid,
            Long createauthorid,
            Long lastauthorid,
            Timestamp createdAt,
            Timestamp lastupdatedate,
            String name,
            Integer addtionalid,
            Long numericvalue,
            String stringvalue,
            Double realvalue
    ) {
        super(id_, proposalid, createauthorid, lastauthorid, createdAt, lastupdatedate,
                name, addtionalid, numericvalue, stringvalue, realvalue);
    }

    public ProposalUnversionedAttribute(AbstractProposalUnversionedAttribute
            abstractProposalUnversionedAttribute,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalUnversionedAttribute);
    }
}
