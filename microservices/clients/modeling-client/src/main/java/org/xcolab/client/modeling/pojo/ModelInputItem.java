package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.RestService;

public class ModelInputItem extends AbstractModelInputItem {

    public ModelInputItem() {}

    public ModelInputItem(ModelInputItem value) {
        super(value);
    }

    public ModelInputItem(AbstractModelInputItem modelInputItem, RestService restService) {
        super(modelInputItem);
    }
}
