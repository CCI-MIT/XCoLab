package org.xcolab.client.modeling.pojo;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class ModelCategory extends AbstractModelCategory {

    public ModelCategory() {}

    public ModelCategory(ModelCategory value) {
        super(value);
    }

    public ModelCategory(AbstractModelCategory modelCategory, ServiceNamespace serviceNamespace) {
        super(modelCategory);
    }
}
