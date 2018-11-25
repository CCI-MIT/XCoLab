package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ModelPosition extends AbstractModelPosition {

    public static final TypeProvider<ModelPosition> TYPES = new TypeProvider<>(ModelPosition.class,
            new ParameterizedTypeReference<List<ModelPosition>>() {});

    public ModelPosition() {}

    public ModelPosition(ModelPosition value) {
        super(value);
    }

    public ModelPosition(AbstractModelPosition modelPosition) {
        super(modelPosition);
    }
}
