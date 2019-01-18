package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalUnversionedAttribute extends AbstractProposalUnversionedAttribute
        implements Serializable {

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
            abstractProposalUnversionedAttribute) {
        super(abstractProposalUnversionedAttribute);
    }
}
