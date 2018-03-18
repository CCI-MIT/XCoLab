package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ModelDiscussion extends AbstractModelDiscussion {

    public ModelDiscussion() {}

    public ModelDiscussion(ModelDiscussion value) {
        super(value);
    }

    public ModelDiscussion(AbstractModelDiscussion modelDiscussion,
            ServiceNamespace serviceNamespace) {
        super(modelDiscussion);
    }
}
