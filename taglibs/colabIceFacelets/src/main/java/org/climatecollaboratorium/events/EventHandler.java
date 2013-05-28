package org.climatecollaboratorium.events;

import java.io.Serializable;

public interface EventHandler<R extends Event> extends Serializable {
    void onEvent(R event);
}
