package org.xcolab.entity.utils.flash;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorMessage {

    private static final FlashMessageStore MESSAGE_STORE = new FlashMessageStore();
    public static final String ERROR_URI = "/error";

    private final String title;
    private final String message;

    private ErrorMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public static ErrorMessage error(String message) {
        return new ErrorMessage("Error", message);
    }

    public ErrorMessage withTitle(String title) {
        return new ErrorMessage(title, message);
    }

    public static ErrorMessage extract(HttpServletRequest request) {
        return MESSAGE_STORE.pop(request, ErrorMessage.class);
    }

    public void flash(HttpServletRequest request) {
        MESSAGE_STORE.put(request, this);
    }

    public String flashAndReturnRedirect(HttpServletRequest request)
            throws IOException {
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

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
