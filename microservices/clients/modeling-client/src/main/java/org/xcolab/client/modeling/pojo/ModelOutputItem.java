package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.RestService;

public class ModelOutputItem extends AbstractModelOutputItem {

    public ModelOutputItem() {}

    public ModelOutputItem(ModelOutputItem value) {
        super(value);
    }

    public ModelOutputItem(AbstractModelOutputItem modelOutputItem, RestService restService) {
        super(modelOutputItem);
    }
}
