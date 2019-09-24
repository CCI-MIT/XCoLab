package org.xcolab.client.contest.proposals.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ProposalAttributeNotFoundException extends EntityNotFoundException {

    public ProposalAttributeNotFoundException(long proposalAttributeId) {
        this("ProposalAttributeNotFoundException with id " + proposalAttributeId
                + " does not exist");
    }

    public ProposalAttributeNotFoundException(String msg) {
        super(msg, ProposalAttributeNotFoundException.class);
    }
}
