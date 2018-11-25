package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ModelGlobalPreference extends AbstractModelGlobalPreference {

    public static final TypeProvider<ModelGlobalPreference> TYPES = new TypeProvider<>(ModelGlobalPreference.class,
            new ParameterizedTypeReference<List<ModelGlobalPreference>>() {});

    public ModelGlobalPreference() {}

    public ModelGlobalPreference(ModelGlobalPreference value) {
        super(value);
    }

    public ModelGlobalPreference(AbstractModelGlobalPreference modelGlobalPreference) {
        super(modelGlobalPreference);
    }
}
