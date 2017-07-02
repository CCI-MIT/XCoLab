package org.xcolab.client.admin.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.attributes.AbstractAttribute;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

public class ContestTypeAttribute extends AbstractAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final TypeProvider<ContestTypeAttribute> TYPES =
            new TypeProvider<>(ContestTypeAttribute.class,
                    new ParameterizedTypeReference<List<ContestTypeAttribute>>() {
                    });

    public ContestTypeAttribute() {
    }

    public ContestTypeAttribute(ConfigurationAttribute value) {
        super(value);
    }
}

