package org.xcolab.client.admin.exceptions;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;

public class ConfigurationAttributeNotFoundException extends RuntimeException {
    public ConfigurationAttributeNotFoundException(ConfigurationAttributeKey attribute) {
        super("ConfigurationAttribute " + attribute.name() + " could not be found");
    }
}
