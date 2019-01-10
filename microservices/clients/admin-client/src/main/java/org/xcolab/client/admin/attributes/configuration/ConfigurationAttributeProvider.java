package org.xcolab.client.admin.attributes.configuration;

import org.springframework.util.Assert;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.pojo.IConfigurationAttribute;
import org.xcolab.commons.attributes.i18n.LocalizableAttributeProvider;

class ConfigurationAttributeProvider
        implements LocalizableAttributeProvider<IConfigurationAttribute> {

    private final String name;

    ConfigurationAttributeProvider(String name) {
        Assert.notNull(name, "Name is required");
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public IConfigurationAttribute get() {
        return AdminClient.getConfigurationAttribute(name, null);
    }

    @Override
    public IConfigurationAttribute get(long additionalId) {
        throw new UnsupportedOperationException("Configuration Attributes don't support additional ids");
    }

    @Override
    public IConfigurationAttribute get(String locale) {
        return AdminClient.getConfigurationAttribute(name, locale);
    }

    @Override
    public IConfigurationAttribute get(String locale, long additionalId) {
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
