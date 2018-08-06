package org.xcolab.client.proposals.pojo.evaluation.members;

import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.sql.Timestamp;

public class ProposalSupporter extends AbstractProposalSupporter {

    public ProposalSupporter() {}

    public ProposalSupporter(ProposalSupporter value) {
        super(value);
    }

    public ProposalSupporter(
            Long proposalid,
            Long userid,
            Timestamp createdAt
    ) {
        super(proposalid, userid, createdAt);
    }

    public ProposalSupporter(AbstractProposalSupporter abstractProposalSupporter,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalSupporter);
    }
}
