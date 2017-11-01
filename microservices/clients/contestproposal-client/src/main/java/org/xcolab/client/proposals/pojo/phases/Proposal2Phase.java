package org.xcolab.client.proposals.pojo.phases;


import org.xcolab.util.http.client.enums.ServiceNamespace;

public class Proposal2Phase extends AbstractProposal2Phase {

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

    public Proposal2Phase(AbstractProposal2Phase abstractProposal2Phase, ServiceNamespace serviceNamespace) {
        super(abstractProposal2Phase);
    }
}
