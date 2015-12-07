package org.xcolab.utils;

import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class GlobalMessagesUtil {

    public static final String GLOBAL_MESSAGES_SESSION_KEY = "xcolab_global_messages";

    public static void addMessage(String message, PortletRequest request) {
        addMessage(message, PortalUtil.getHttpServletRequest(request));
    }

    public static void addMessage(String message, HttpServletRequest portalRequest) {
        HttpSession session = portalRequest.getSession(true);

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
