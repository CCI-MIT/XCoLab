package org.xcolab.client.contest.proposals.exceptions;

import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

public class Proposal2PhaseNotFoundException extends RuntimeEntityNotFoundException {

    public Proposal2PhaseNotFoundException(long proposalId, long phaseId) {
        this("Proposal2PhaseNotFoundException with proposalId " + proposalId + " and phaseId "
                + phaseId + " does not exist");
    }

    public Proposal2PhaseNotFoundException(String msg) {
        super(msg, Proposal2PhaseNotFoundException.class);
    }
}
