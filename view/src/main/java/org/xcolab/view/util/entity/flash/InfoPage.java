package org.xcolab.view.util.entity.flash;

import org.xcolab.view.util.entity.flash.impl.FlashMessageStore;

import javax.servlet.http.HttpServletRequest;

public class InfoPage {

    private static final FlashMessageStore MESSAGE_STORE = new FlashMessageStore();
    private static final String MESSAGE_VIEW = "message";

    private final String title;
    private final String message;

    private InfoPage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public static InfoPage message(String message) {
        return new InfoPage("Message", message);
    }

    public InfoPage withTitle(String title) {
        return new InfoPage(title, message);
    }

    public static InfoPage extract(HttpServletRequest request) {
        return MESSAGE_STORE.pop(request, InfoPage.class);
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
