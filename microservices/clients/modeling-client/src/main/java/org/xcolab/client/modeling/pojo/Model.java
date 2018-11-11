package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class Model {

    public static final TypeProvider<Model> TYPES = new TypeProvider<>(Model.class,
            new ParameterizedTypeReference<List<Model>>() {});

    private Long modelId;

    public Model() {
    }

    public Model(Long modelId) {
        modelId = modelId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
}
