package org.xcolab.view.pages.contestmanagement.entities;

public class MassActionRequiresConfirmationException extends Exception {

    private static final long serialVersionUID = 5826436793643253828L;

    public MassActionRequiresConfirmationException() {
        super();
    }

    public MassActionRequiresConfirmationException(String message) {
        super(message);
    }

    public MassActionRequiresConfirmationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MassActionRequiresConfirmationException(Throwable cause) {
        super(cause);
    }
}
