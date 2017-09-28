package org.xcolab.view.webhooks.sendgrid.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EventType {
    PROCESSED,
    DROPPED,
    DELIVERED,
    DEFERRED,
    BOUNCE,
    OPEN,
    CLICK,
    SPAMREPORT,
    UNSUBSCRIBE,
    GROUP_UNSUBSCRIBE,
    GROUP_RESUBSCRIBE;

    @JsonCreator
    public static EventType fromString(String key) {
        return key == null ? null : EventType.valueOf(key.toUpperCase());
    }
}
