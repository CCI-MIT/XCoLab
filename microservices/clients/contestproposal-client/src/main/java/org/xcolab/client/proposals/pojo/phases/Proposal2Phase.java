package org.xcolab.client.proposals.pojo.phases;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class Proposal2Phase extends AbstractProposal2Phase {

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
