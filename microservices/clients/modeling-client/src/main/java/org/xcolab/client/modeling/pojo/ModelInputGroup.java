package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ModelInputGroup extends AbstractModelInputGroup {

    public ModelInputGroup() {}

    public ModelInputGroup(ModelInputGroup value) {
        super(value);
    }

    public ModelInputGroup(AbstractModelInputGroup modelInputGroup,
            ServiceNamespace serviceNamespace) {
        super(modelInputGroup);
    }
}
