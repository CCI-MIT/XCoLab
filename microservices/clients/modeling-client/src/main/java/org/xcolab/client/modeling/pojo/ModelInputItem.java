package org.xcolab.client.modeling.pojo;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

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
