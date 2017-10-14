package org.xcolab.client.proposals.pojo.evaluation.members;

import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.sql.Timestamp;

public class ProposalVote extends AbstractProposalVote {

    public ProposalVote() {}

    public ProposalVote(ProposalVote value) {
        super(value);
    }

    public ProposalVote(
            Long proposalid,
            Long contestphaseid,
            Long userid,
            Timestamp createdate,
            Boolean isvalid,
            Timestamp confirmationemailsenddate,
            String confirmationtoken
    ) {
        super(proposalid, contestphaseid, userid, createdate, isvalid,
                confirmationemailsenddate, confirmationtoken);
    }

    public ProposalVote(AbstractProposalVote abstractProposalVote,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalVote);
    }
}
