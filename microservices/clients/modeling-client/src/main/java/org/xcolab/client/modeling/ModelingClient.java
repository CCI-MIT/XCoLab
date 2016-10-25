package org.xcolab.client.modeling;

import org.xcolab.client.modeling.pojo.Model;
import org.xcolab.client.modeling.pojo.ModelGlobalPreference;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.RestService;

public final class ModelingClient {

    private static final RestService modelingService = new RestService("modeling-service");

    private static final RestResource1<Model, Long> modelResource = new RestResource1<>(
            modelingService, "models", Model.TYPES);

    private static final RestResource2L<Model, ModelGlobalPreference> modelPreferenceResource
            = new RestResource2L<>(modelResource, "preferences", ModelGlobalPreference.TYPES);

    private ModelingClient() {
    }

    public ModelGlobalPreference getModelPreference(long modelId) {
        return modelPreferenceResource.resolveParent(modelResource.id(modelId))
                .list()
                .executeWithResult()
                .getOneIfExists();
    }


}
