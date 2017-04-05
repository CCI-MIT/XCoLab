package org.xcolab.client.admin.exceptions;

import org.xcolab.client.admin.pojo.Notification;
import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;

public class NotificationNotFoundException extends AttributeNotFoundException {
    public NotificationNotFoundException(String name) {
        super("Notification " + name + " could not be found");
    }
}
