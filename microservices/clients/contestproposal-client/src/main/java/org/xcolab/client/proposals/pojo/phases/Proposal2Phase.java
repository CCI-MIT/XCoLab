package org.xcolab.client.proposals.pojo.phases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Proposal2Phase extends AbstractProposal2Phase implements Serializable {

    public static final TypeProvider<Proposal2Phase> TYPES =
            new TypeProvider<>(Proposal2Phase.class,
                    new ParameterizedTypeReference<List<Proposal2Phase>>() {});

    public Proposal2Phase() {}

    public Proposal2Phase(Proposal2Phase value) {
        super(value);
    }

    public Proposal2Phase(
            Long proposalid,
            Long contestphaseid,
            Integer versionfrom,
            Integer versionto,
            Integer sortweight,
            Boolean autopromotecandidate
    ) {
        super(proposalid, contestphaseid, versionfrom, versionto, sortweight, autopromotecandidate);
    }

    public Proposal2Phase(AbstractProposal2Phase abstractProposal2Phase) {
        super(abstractProposal2Phase);
    }
}
