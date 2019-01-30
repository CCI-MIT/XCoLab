package org.xcolab.view.util.entity.flash;

import org.xcolab.client.user.pojo.IAnalyticsUserEvent;
import org.xcolab.commons.servlet.flash.impl.FlashMessageStore;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class AnalyticsAttribute {

    private static final FlashMessageStore MESSAGE_STORE = new FlashMessageStore();
    private final List<IAnalyticsUserEvent> events = new ArrayList<>();

    private AnalyticsAttribute() {
    }

    public static void add(HttpServletRequest request, IAnalyticsUserEvent event) {
        AnalyticsAttribute attribute = MESSAGE_STORE.peek(request, AnalyticsAttribute.class);
        if (attribute == null) {
            attribute = new AnalyticsAttribute();
            MESSAGE_STORE.put(request, attribute);
        }
        attribute.events.add(event);
    }

    public static AnalyticsAttribute extract(HttpServletRequest request) {
        return MESSAGE_STORE.pop(request, AnalyticsAttribute.class);
    }

    public void flash(HttpServletRequest request) {
        MESSAGE_STORE.put(request, this);
    }

    public List<IAnalyticsUserEvent> getEvents() {
        return events;
    }
}
