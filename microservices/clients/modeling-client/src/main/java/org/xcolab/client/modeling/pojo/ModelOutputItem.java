package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ModelOutputItem extends AbstractModelOutputItem {

    public static final TypeProvider<ModelOutputItem> TYPES = new TypeProvider<>(ModelOutputItem.class,
            new ParameterizedTypeReference<List<ModelOutputItem>>() {});

    public ModelOutputItem() {}

    public ModelOutputItem(ModelOutputItem value) {
        super(value);
    }

    public ModelOutputItem(AbstractModelOutputItem modelOutputItem,
            ServiceNamespace serviceNamespace) {
        super(modelOutputItem);
    }
}
