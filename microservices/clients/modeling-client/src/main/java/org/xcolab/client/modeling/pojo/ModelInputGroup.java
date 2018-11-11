package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ModelInputGroup extends AbstractModelInputGroup {

    public static final TypeProvider<ModelInputGroup> TYPES = new TypeProvider<>(ModelInputGroup.class,
            new ParameterizedTypeReference<List<ModelInputGroup>>() {});

    public ModelInputGroup() {}

    public ModelInputGroup(ModelInputGroup value) {
        super(value);
    }

    public ModelInputGroup(AbstractModelInputGroup modelInputGroup,
            ServiceNamespace serviceNamespace) {
        super(modelInputGroup);
    }
}
