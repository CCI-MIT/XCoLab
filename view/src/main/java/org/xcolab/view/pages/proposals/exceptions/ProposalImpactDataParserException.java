package org.xcolab.view.pages.proposals.exceptions;

public class ProposalImpactDataParserException extends Exception {
    public ProposalImpactDataParserException(String message) {
        super(message);
    }

    public ProposalImpactDataParserException(Exception e) {
        super(e);
    }

    public ProposalImpactDataParserException(String message, Exception exception) {
        super (message, exception);
    }
}
