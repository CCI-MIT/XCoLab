package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.RestService;

public class ModelInputGroup extends AbstractModelInputGroup {

    public ModelInputGroup() {}

    public ModelInputGroup(ModelInputGroup value) {
        super(value);
    }

    public ModelInputGroup(AbstractModelInputGroup modelInputGroup, RestService restService) {
        super(modelInputGroup);
    }
}
