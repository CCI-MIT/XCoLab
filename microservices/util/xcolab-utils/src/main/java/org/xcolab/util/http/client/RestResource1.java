package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.UriProvider;
import org.xcolab.util.http.client.interfaces.AbstractRestResource;
import org.xcolab.util.http.client.interfaces.HttpEndpoint;
import org.xcolab.util.http.client.types.TypeProvider;

public class RestResource1<ResourceT, IdT> extends AbstractRestResource<ResourceT, IdT> {

    private final HttpEndpoint serviceOrParent;
    private final String resourceName;

    public RestResource1(HttpEndpoint serviceOrParent, String resourceName,
            TypeProvider<ResourceT> typeProvider) {
        super(typeProvider);
        this.serviceOrParent = serviceOrParent;
        this.resourceName = resourceName;
    }

    public <ResourceT2, IdT2> RestResource<ResourceT2, IdT2> nestedResource(final IdT resourceId,
            String subResourceName, TypeProvider<ResourceT2> typeProvider) {
        return new RestResource2<ResourceT, IdT, ResourceT2, IdT2>(this, subResourceName,
                typeProvider).resolveParent(id(resourceId));
    }

    @Override
    public UriProvider getBaseUrl() {
        return serviceOrParent.getBaseUrl();
    }

    @Override
    public UriBuilder getResourceUrl() {
        return getBaseUrl().cloneUriBuilder().resource(resourceName);
    }

    @Override
    public UriBuilder getResourceUrl(Object resourceId) {
        return getBaseUrl().cloneUriBuilder().resource(resourceName, resourceId);
    }
}
