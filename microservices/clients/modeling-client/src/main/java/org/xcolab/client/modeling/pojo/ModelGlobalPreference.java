package org.xcolab.client.modeling.pojo;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class ModelGlobalPreference extends AbstractModelGlobalPreference {

    public ModelGlobalPreference() {}

    public ModelGlobalPreference(ModelGlobalPreference value) {
        super(value);
    }

    public ModelGlobalPreference(AbstractModelGlobalPreference modelGlobalPreference,
            ServiceNamespace serviceNamespace) {
        super(modelGlobalPreference);
    }
}
