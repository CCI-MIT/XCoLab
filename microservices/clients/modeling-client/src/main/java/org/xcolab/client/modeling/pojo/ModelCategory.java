package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.RestService;

public class ModelCategory extends AbstractModelCategory {

    public ModelCategory() {}

    public ModelCategory(ModelCategory value) {
        super(value);
    }

    public ModelCategory(AbstractModelCategory modelCategory, RestService restService) {
        super(modelCategory);
    }
}
