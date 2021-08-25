package org.xcolab.commons.servlet.flash.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.commons.collections.TtlHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    private static final Logger log = LoggerFactory.getLogger(FlashMessageStore.class);

    private static final String FLASH_ATTRIBUTE_NAME = "xcolab_flash_messages";

    private final TtlHashMap<String, Object> mutexes = new TtlHashMap<>(5, TimeUnit.MINUTES);

    private Map<Class<?>, Object> getFlashMap(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        Map<Class<?>, Object> flashMap =
                (Map<Class<?>, Object>) request.getAttribute(FLASH_ATTRIBUTE_NAME);
        if (flashMap != null) {
            return flashMap;
        }
        flashMap = new HashMap<>();
        saveFlashMap(request, flashMap);
        return flashMap;
    }

    private void saveFlashMap(HttpServletRequest request, Map<Class<?>, Object> flashMap) {
        request.setAttribute(FLASH_ATTRIBUTE_NAME, flashMap);
    }

    private void put(HttpServletRequest request, Class<?> key, Object value) {
        final HttpSession session = request.getSession(true);
        synchronized (getMutex(session)) {
            final Map<Class<?>, Object> flashMap = getFlashMap(request);
            flashMap.put(key, value);
            saveFlashMap(request, flashMap);
        }
    }

    private Object get(HttpServletRequest request, Class<?> key) {
        final HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }
        final Map<Class<?>, Object> flashMap = getFlashMap(request);
        return flashMap.get(key);
    }

    private Object remove(HttpServletRequest request, Class<?> key) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        synchronized (getMutex(session)) {
            final Map<Class<?>, Object> flashMap = getFlashMap(request);
            final Object ret = flashMap.remove(key);
            saveFlashMap(request, flashMap);
            return ret;
        }
    }

    private Object getMutex(HttpSession session) {
        return mutexes.computeIfAbsent(session.getId(), k -> {
            log.trace("Adding new session mutex. Now storing {} mutexes.", mutexes.size() + 1);
            return new Object();
        });
    }

    public <T> T pop(HttpServletRequest request, Class<T> messageClass) {
        // retrieving is cheaper than removing - short circuit when possible
        if (get(request, messageClass) == null) {
            return null;
        }
        return messageClass.cast(remove(request, messageClass));
    }

    public <T> T peek(HttpServletRequest request, Class<T> messageClass) {
        return messageClass.cast(get(request, messageClass));
    }

    public <T> void put(HttpServletRequest request, T message) {
        put(request, message.getClass(), message);
    }
}
