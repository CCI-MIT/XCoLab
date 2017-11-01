package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ModelOutputItem extends AbstractModelOutputItem {

    public ModelOutputItem() {}

    public ModelOutputItem(ModelOutputItem value) {
        super(value);
    }

    public ModelOutputItem(AbstractModelOutputItem modelOutputItem,
            ServiceNamespace serviceNamespace) {
        super(modelOutputItem);
    }
}
