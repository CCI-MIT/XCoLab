package org.xcolab.commons.servlet.flash.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * A simple key value store that allows storing one value per class across requests.
 *
 * It is meant to pass a message from a controller to the next request, for example across
 * redirects. It is usually read and deleted (using the {@link #pop(HttpServletRequest, Class)}
 * method) the next time a page is rendered.
 */
public class FlashMessageStore {

    private static final String FLASH_ATTRIBUTE_NAME = "xcolab_flash_messages";

    private Map<Class<?>, Object> getFlashMap(HttpServletRequest request) {
        final HttpSession session = request.getSession(true);

        @SuppressWarnings("unchecked")
        Map<Class<?>, Object> attribute =
                (Map<Class<?>, Object>) session.getAttribute(FLASH_ATTRIBUTE_NAME);
        if (attribute != null) {
            return attribute;
        }
        attribute = new HashMap<>();
        session.setAttribute(FLASH_ATTRIBUTE_NAME, attribute);
        return attribute;
    }

    public <T> T pop(HttpServletRequest request, Class<T> messageClass) {
        final Map<Class<?>, Object> flashMap = getFlashMap(request);
        return messageClass.cast(flashMap.remove(messageClass));
    }

    public <T> T peek(HttpServletRequest request, Class<T> messageClass) {
        final Map<Class<?>, Object> flashMap = getFlashMap(request);
        return messageClass.cast(flashMap.get(messageClass));
    }

    public <T> void put(HttpServletRequest request, T message) {
        final Map<Class<?>, Object> flashMap = getFlashMap(request);
        flashMap.put(message.getClass(), message);
    }
}
