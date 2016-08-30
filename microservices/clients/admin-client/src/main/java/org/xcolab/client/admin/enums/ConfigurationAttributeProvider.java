package org.xcolab.client.admin.enums;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.util.attributes.AttributeProvider;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;

class ConfigurationAttributeProvider implements AttributeProvider<ConfigurationAttribute> {

    private final String name;

    ConfigurationAttributeProvider(String name) {
        this.name = name;
    }

    @Override
    public ConfigurationAttribute get() {
        return AdminClient.getConfigurationAttribute(name);
    }

    @Override
    public ConfigurationAttribute get(long additionalId) {
        throw new UnsupportedOperationException("Configuration Attributes don't support additional ids");
    }
}
