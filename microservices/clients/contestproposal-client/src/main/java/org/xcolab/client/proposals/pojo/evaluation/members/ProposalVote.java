package org.xcolab.client.proposals.pojo.evaluation.members;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ProposalVote extends AbstractProposalVote {

    public ProposalVote() {}

    public ProposalVote(ProposalVote value) {
        super(value);
    }

    public ProposalVote(AbstractProposalVote abstractProposalVote,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalVote);
    }
}
