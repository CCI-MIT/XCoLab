package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.RestService;

public class ModelDiscussion extends AbstractModelDiscussion {

    public ModelDiscussion() {}

    public ModelDiscussion(ModelDiscussion value) {
        super(value);
    }

    public ModelDiscussion(AbstractModelDiscussion modelDiscussion, RestService restService) {
        super(modelDiscussion);
    }
}
