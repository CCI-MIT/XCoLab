package org.xcolab.client.proposals.pojo.points;


import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.http.client.RestService;

public class Points extends AbstractPoints {

    public Points(AbstractPoints abstractPoints, RestService restService) {
        super(abstractPoints);
    }

    public Proposal getBaseProposal(){
        return ProposalClientUtil.getProposal(this.getProposalId());
    }
    public Proposal getDirectProposal(){
        return ProposalClientUtil.getProposal(this.getOriginatingProposalId());
    }
}

