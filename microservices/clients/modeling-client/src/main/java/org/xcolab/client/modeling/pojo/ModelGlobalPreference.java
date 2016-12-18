package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.RestService;

public class ModelGlobalPreference extends AbstractModelGlobalPreference {

    public ModelGlobalPreference() {}

    public ModelGlobalPreference(ModelGlobalPreference value) {
        super(value);
    }

    public ModelGlobalPreference(AbstractModelGlobalPreference modelGlobalPreference,
            RestService restService) {
        super(modelGlobalPreference);
    }
}
