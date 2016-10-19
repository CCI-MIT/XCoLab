package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.RestService;

public class ModelPosition extends AbstractModelPosition {

    public ModelPosition() {}

    public ModelPosition(ModelPosition value) {
        super(value);
    }

    public ModelPosition(AbstractModelPosition modelPosition, RestService restService) {
        super(modelPosition);
    }
}
