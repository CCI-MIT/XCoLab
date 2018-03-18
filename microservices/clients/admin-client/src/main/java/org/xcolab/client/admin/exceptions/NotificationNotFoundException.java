package org.xcolab.client.admin.exceptions;

import org.xcolab.commons.attributes.exceptions.AttributeNotFoundException;

public class NotificationNotFoundException extends AttributeNotFoundException {
    public NotificationNotFoundException(String name) {
        super("Notification " + name + " could not be found");
    }
}
