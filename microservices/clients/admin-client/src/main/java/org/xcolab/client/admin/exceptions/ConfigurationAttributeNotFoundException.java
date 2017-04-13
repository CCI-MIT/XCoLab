package org.xcolab.client.admin.exceptions;

import org.xcolab.client.admin.pojo.Notification;
import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;

public class ConfigurationAttributeNotFoundException extends AttributeNotFoundException {
    public ConfigurationAttributeNotFoundException(String name) {
        super("ConfigurationAttribute " + name + " could not be found");
    }
}
