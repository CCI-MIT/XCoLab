package org.xcolab.view.pages.proposals.exceptions;

public class ProposalsAuthorizationException extends Exception {

    public ProposalsAuthorizationException() {
    }

    public ProposalsAuthorizationException(String message) {
        super(message);
    }

    public ProposalsAuthorizationException(Throwable cause) {
        super(cause);
    }

    public ProposalsAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProposalsAuthorizationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
