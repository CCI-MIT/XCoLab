package org.climatecollaboratorium.events;

public interface EventHandler<R extends Event> {
    void onEvent(R event);
}
