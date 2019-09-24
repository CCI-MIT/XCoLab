package org.xcolab.client.contest.proposals.exceptions;

import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

public class ProposalNotFoundException extends RuntimeEntityNotFoundException {

    public ProposalNotFoundException(long proposalId) {
        this("Proposal with id " + proposalId + " does not exist");
    }

    public ProposalNotFoundException(String msg) {
        super(msg, ProposalNotFoundException.class);
    }
}

