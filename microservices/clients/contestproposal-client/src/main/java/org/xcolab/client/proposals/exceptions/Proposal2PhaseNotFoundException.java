package org.xcolab.client.proposals.exceptions;

public class Proposal2PhaseNotFoundException extends RuntimeException {
    public Proposal2PhaseNotFoundException(long proposalAttributeId) {
        this("Proposal2PhaseNotFoundException with id " + proposalAttributeId + " does not exist");
    }

    public Proposal2PhaseNotFoundException(String msg) {
        super(msg);
    }
}
