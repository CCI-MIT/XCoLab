package org.xcolab.portlets.contestmanagement.entities;

/**
 * Created by Thomas on 10/23/2015.
 */
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
