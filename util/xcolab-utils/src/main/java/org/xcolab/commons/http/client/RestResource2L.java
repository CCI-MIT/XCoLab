package org.xcolab.commons.http.client;

import org.xcolab.commons.http.client.interfaces.IdentifiableHttpResource;
import org.xcolab.commons.http.client.types.TypeProvider;

public class RestResource2L<ResourceT1, ResourceT2>
        extends RestResource2<ResourceT1, Long, ResourceT2, Long> {

    public RestResource2L(IdentifiableHttpResource<ResourceT1, Long> parent, String resourceName,
            TypeProvider<ResourceT2> typeProvider) {
        super(parent, resourceName, typeProvider);
    }
}
