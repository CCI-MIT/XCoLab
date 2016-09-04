package org.xcolab.client.proposals.exceptions;

public class ProposalAttributeNotFoundException extends Exception {
    public ProposalAttributeNotFoundException(long proposalAttributeId) {
        this("ProposalAttributeNotFoundException with id " + proposalAttributeId + " does not exist");
    }

    public ProposalAttributeNotFoundException(String msg) {
        super(msg);
    }
}
