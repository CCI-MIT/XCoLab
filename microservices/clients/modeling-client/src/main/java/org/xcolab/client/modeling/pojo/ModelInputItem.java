package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ModelInputItem extends AbstractModelInputItem {

    public static final TypeProvider<ModelInputItem> TYPES = new TypeProvider<>(ModelInputItem.class,
            new ParameterizedTypeReference<List<ModelInputItem>>() {});

    public ModelInputItem() {}

    public ModelInputItem(ModelInputItem value) {
        super(value);
    }

    public ModelInputItem(AbstractModelInputItem modelInputItem,
            ServiceNamespace serviceNamespace) {
        super(modelInputItem);
    }
}
