package org.xcolab.view.util.entity.flash;

import javax.servlet.http.HttpServletRequest;

public class InfoMessage {

    private static final FlashMessageStore MESSAGE_STORE = new FlashMessageStore();
    private static final String MESSAGE_VIEW = "message";

    private final String title;
    private final String message;

    private InfoMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public static InfoMessage message(String message) {
        return new InfoMessage("Message", message);
    }

    public InfoMessage withTitle(String title) {
        return new InfoMessage(title, message);
    }

    public static InfoMessage extract(HttpServletRequest request) {
        return MESSAGE_STORE.pop(request, InfoMessage.class);
    }

    public void flash(HttpServletRequest request) {
        MESSAGE_STORE.put(request, this);
    }

    public String flashAndReturnView(HttpServletRequest request) {
        flash(request);
        return MESSAGE_VIEW;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
