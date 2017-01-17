package org.xcolab.view.errors;

import org.xcolab.entity.utils.flash.ErrorMessage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum ErrorText {
    ACCESS_DENIED(
            ErrorMessage.error("You are not logged into your account or do not have "
                    + "the correct permissions to access this page. "
                    + "Please login and try again.")
                .withTitle("Access Denied")),
    NOT_FOUND(
            ErrorMessage.error("The content you are looking for does not exist.")
                            .withTitle("Content not found")),
    ILLEGAL_SCHEDULE_CHANGE(ErrorMessage.error("If you tried to edit a contest schedule make sure"
            + " that it is not used by any contests that have started.\n"
            + "        If you tried changing a contest's schedule, make sure no proposals have "
            + "been submitted to that contest.")
            .withTitle("Illegal schedule change"))
    ;


    //    public static final String ERROR_PATH = "/error";
        public static final String ERROR_VIEW = "error";
    private final ErrorMessage errorMessage;

    ErrorText(ErrorMessage errorMessage) {

        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void flashAndRedirect(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        errorMessage.flashAndRedirect(request, response);
    }

    public String flashAndReturnRedirect(HttpServletRequest request) throws IOException {
        return errorMessage.flashAndReturnRedirect(request);
    }

    public String flashAndReturnView(HttpServletRequest request) {
        errorMessage.flash(request);
        return ERROR_VIEW;
    }
}
