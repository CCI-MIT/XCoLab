package org.xcolab.client.admin.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.attributes.AbstractAttribute;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ConfigurationAttribute extends AbstractAttribute {

    public static final TypeProvider<ConfigurationAttribute> TYPES =
            new TypeProvider<>(ConfigurationAttribute.class,
                    new ParameterizedTypeReference<List<ConfigurationAttribute>>() {
                    });

    public ConfigurationAttribute() {
    }

    public ConfigurationAttribute(ConfigurationAttribute value) {
        super(value);
    }

    public ConfigurationAttribute(String name, long additionalId, Long numericValue,
            String stringValue, Double realValue) {
        super(name, additionalId, numericValue, stringValue, realValue);
    }
}
