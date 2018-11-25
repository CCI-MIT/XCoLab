package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ModelDiscussion extends AbstractModelDiscussion {

    public static final TypeProvider<ModelDiscussion> TYPES = new TypeProvider<>(ModelDiscussion.class,
            new ParameterizedTypeReference<List<ModelDiscussion>>() {});

    public ModelDiscussion() {}

    public ModelDiscussion(ModelDiscussion value) {
        super(value);
    }

    public ModelDiscussion(AbstractModelDiscussion modelDiscussion) {
        super(modelDiscussion);
    }
}
