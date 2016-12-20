package org.xcolab.entity.utils;

import org.xcolab.entity.utils.portlet.PortletUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class GlobalMessagesUtil {

    public static final String GLOBAL_MESSAGES_SESSION_KEY = "xcolab_global_messages";

    private GlobalMessagesUtil() { }

    public static void addMessage(String message, PortletRequest request) {
        addMessage(message, PortletUtil.getHttpServletRequest(request));
    }

    public static void addMessage(String message, HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession(true);

        List<Message> messages = (List<Message>) session.getAttribute(GLOBAL_MESSAGES_SESSION_KEY);

        if (messages == null) {
            messages = new ArrayList<>();
            session.setAttribute(GLOBAL_MESSAGES_SESSION_KEY, messages);
        }

        messages.add(new Message(message, "normal"));
    }

    public static class Message {
        String message;
        String type;

        public Message(String message, String type) {
            super();
            this.message = message;
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return "Message [message=" + message + ", type=" + type + "]";
        }
    }
}
