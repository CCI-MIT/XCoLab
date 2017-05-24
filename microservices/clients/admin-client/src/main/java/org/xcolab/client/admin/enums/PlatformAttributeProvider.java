package org.xcolab.client.admin.enums;

import org.springframework.core.env.PropertyResolver;

import org.xcolab.util.attributes.AttributeProvider;
import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;

public class PlatformAttributeProvider implements AttributeProvider<PlatformAttribute> {

    private final String key;
    private final PropertyResolver propertyResolver;

    public PlatformAttributeProvider(PropertyResolver propertyResolver, String key) {
        this.key = key;
        this.propertyResolver = propertyResolver;
    }

    @Override
    public String name() {
        return key;
    }

    @Override
    public PlatformAttribute get() {
        if (!propertyResolver.containsProperty(key)) {
            throw new AttributeNotFoundException("No PlatformAttribute with key " + key);
        }
        return new PlatformAttribute(propertyResolver.getProperty(key));
    }

    @Override
    public PlatformAttribute get(long additionalId) {
        if (additionalId == 0) {
            return get();
        }
        throw new UnsupportedOperationException("This attribute does not support additional Ids.");
    }
}
