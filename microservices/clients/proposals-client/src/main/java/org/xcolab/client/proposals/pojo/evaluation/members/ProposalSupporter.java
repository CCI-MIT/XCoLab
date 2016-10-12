package org.xcolab.client.proposals.pojo.evaluation.members;

import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;

public class ProposalSupporter extends AbstractProposalSupporter {

    public ProposalSupporter() {}

    public ProposalSupporter(ProposalSupporter value) {
        super(value);
    }

    public ProposalSupporter(
            Long proposalid,
            Long userid,
            Timestamp createdate
    ) {
        super(proposalid, userid, createdate);
    }

    public ProposalSupporter(AbstractProposalSupporter abstractProposalSupporter,
            RestService restService) {
        super(abstractProposalSupporter);
    }
}
