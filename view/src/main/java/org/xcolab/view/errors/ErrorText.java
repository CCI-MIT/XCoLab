package org.xcolab.view.errors;

import org.xcolab.view.util.entity.flash.ErrorPage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum ErrorText {
    ACCESS_DENIED(ErrorPage.error("You are not logged into your account or do not have "
            + "the correct permissions to access this page. "
            + "Please login and try again.<br /><br /> Please note that you "
            + "may be automatically logged out of your account after 2 hours (120 minutes).")
            .withTitle("Access Denied")),
    PAGE_NOT_FOUND(ErrorPage.error("The page you are looking for does not exist.")
            .withTitle("Page not found")),
    NOT_FOUND(ErrorPage.error("The content you are looking for does not exist.")
            .withTitle("Content not found")),
    ILLEGAL_SCHEDULE_CHANGE(ErrorPage.error("If you tried to edit a contest schedule make sure"
            + " that it is not used by any contests that have started.\n"
            + "        If you tried changing a contest's schedule, make sure no proposals have "
            + "been submitted to that contest.")
            .withTitle("Illegal schedule change"));

    public static final String ERROR_VIEW = "error";
    private final ErrorPage errorMessage;

    ErrorText(ErrorPage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorPage getErrorMessage() {
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
