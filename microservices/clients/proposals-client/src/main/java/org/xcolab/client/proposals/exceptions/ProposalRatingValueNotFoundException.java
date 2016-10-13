package org.xcolab.client.proposals.exceptions;

public class ProposalRatingValueNotFoundException  extends Exception {
    public ProposalRatingValueNotFoundException(long proposalId) {
        this("ProposalRatingValue with id " + proposalId + " does not exist");
    }

    public ProposalRatingValueNotFoundException(String msg) {
        super(msg);
    }
}
