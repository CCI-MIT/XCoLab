package org.xcolab.commons.servlet.flash;

import org.xcolab.commons.servlet.flash.impl.FlashMessageStore;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorPage implements Serializable {

    private static final FlashMessageStore MESSAGE_STORE = new FlashMessageStore();
    private static final String ERROR_URI = "/error";
    private static final String ERROR_VIEW = "error";

    private final String title;
    private final String message;

    private ErrorPage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public static ErrorPage error(String message) {
        return new ErrorPage("Error", message);
    }

    public ErrorPage withTitle(String title) {
        return new ErrorPage(title, message);
    }

    public static ErrorPage extract(HttpServletRequest request) {
        return MESSAGE_STORE.pop(request, ErrorPage.class);
    }

    public void flash(HttpServletRequest request) {
        MESSAGE_STORE.put(request, this);
    }

    public String flashAndReturnRedirect(HttpServletRequest request) {
        flash(request);
        return "redirect:" + ERROR_URI;
    }

    public void flashAndRedirect(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        flashAndRedirect(request, response, ERROR_URI);
    }

    public void flashAndRedirect(HttpServletRequest request, HttpServletResponse response,
            String location) throws IOException {
        flash(request);
        response.sendRedirect(location);
    }

    public String flashAndReturnView(HttpServletRequest request) {
        flash(request);
        return ERROR_VIEW;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
