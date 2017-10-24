package org.xcolab.client.proposals.pojo.evaluation.members;

import org.xcolab.util.http.client.RestService;

public class ProposalVote extends AbstractProposalVote {

    public ProposalVote() {}

    public ProposalVote(ProposalVote value) {
        super(value);
    }

    public ProposalVote(AbstractProposalVote abstractProposalVote, RestService restService) {
        super(abstractProposalVote);
    }
}
