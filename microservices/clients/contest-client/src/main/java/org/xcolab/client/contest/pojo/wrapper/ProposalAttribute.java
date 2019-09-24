package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalAttribute extends AbstractProposalAttribute implements Serializable {

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
            Double realvalue) {
        super(id, proposalid, version, name, additionalid, numericvalue, stringvalue, realvalue);
    }

    public ProposalAttribute(AbstractProposalAttribute abstractProposalAttribute) {
        super(abstractProposalAttribute);
    }
}
