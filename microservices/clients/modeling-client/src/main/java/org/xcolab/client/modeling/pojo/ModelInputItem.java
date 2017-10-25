package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ModelInputItem extends AbstractModelInputItem {

    public ModelInputItem() {}

    public ModelInputItem(ModelInputItem value) {
        super(value);
    }

    public ModelInputItem(AbstractModelInputItem modelInputItem,
            ServiceNamespace serviceNamespace) {
        super(modelInputItem);
    }
}
