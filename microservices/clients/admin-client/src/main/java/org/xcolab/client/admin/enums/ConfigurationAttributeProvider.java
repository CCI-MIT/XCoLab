package org.xcolab.client.admin.enums;

import org.springframework.util.Assert;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.util.attributes.AttributeProvider;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;

class ConfigurationAttributeProvider implements AttributeProvider<ConfigurationAttribute> {

    private final String name;

    ConfigurationAttributeProvider(String name) {
        Assert.notNull(name, "Name is required");
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

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ConfigurationAttributeProvider)) {
            return false;
        }

        final ConfigurationAttributeProvider other = (ConfigurationAttributeProvider) obj;
        return name.equals(other.name);
    }
}
