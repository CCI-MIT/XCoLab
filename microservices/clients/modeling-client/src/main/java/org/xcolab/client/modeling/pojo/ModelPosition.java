package org.xcolab.client.modeling.pojo;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class ModelPosition extends AbstractModelPosition {

    public ModelPosition() {}

    public ModelPosition(ModelPosition value) {
        super(value);
    }

    public ModelPosition(AbstractModelPosition modelPosition,
            ServiceNamespace serviceNamespace) {
        super(modelPosition);
    }
}
