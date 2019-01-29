package org.xcolab.client.contest.proposals.exceptions;

import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

public class ProposalTemplateNotFoundException extends RuntimeEntityNotFoundException {

    public ProposalTemplateNotFoundException(long id) {
        super("ProposalTemplateNotFoundException with id " + id + " does not exist",
                ProposalTemplateNotFoundException.class);
    }
}
