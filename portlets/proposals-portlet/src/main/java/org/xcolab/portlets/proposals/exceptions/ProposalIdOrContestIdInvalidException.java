package org.xcolab.portlets.proposals.exceptions;

public class ProposalIdOrContestIdInvalidException extends IllegalArgumentException {
    public ProposalIdOrContestIdInvalidException(Exception exception) {
        super (exception);
    }

    public ProposalIdOrContestIdInvalidException() {

    }
}
