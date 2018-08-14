package org.xcolab.client.proposals.exceptions;

public class ProposalTemplateNotFoundException extends RuntimeException {

    public ProposalTemplateNotFoundException(long id) {
        super("ProposalTemplateNotFoundException with id " + id + " does not exist");
    }
}
