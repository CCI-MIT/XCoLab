package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ModelCategory extends AbstractModelCategory {

    public static final TypeProvider<ModelCategory> TYPES = new TypeProvider<>(ModelCategory.class,
            new ParameterizedTypeReference<List<ModelCategory>>() {});

    public ModelCategory() {}

    public ModelCategory(ModelCategory value) {
        super(value);
    }

    public ModelCategory(AbstractModelCategory modelCategory, ServiceNamespace serviceNamespace) {
        super(modelCategory);
    }
}
