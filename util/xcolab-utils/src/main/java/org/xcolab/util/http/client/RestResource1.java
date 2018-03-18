package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.enums.ResourceEnum;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.interfaces.AbstractRestResource;
import org.xcolab.util.http.client.types.TypeProvider;

public class RestResource1<ResourceT, IdT> extends AbstractRestResource<ResourceT, IdT> {

    private final ResourceEnum resourceEnum;
    private final ServiceNamespace namespace;

    public RestResource1(ResourceEnum resourceEnum, TypeProvider<ResourceT> typeProvider) {
        this(resourceEnum, typeProvider, ServiceNamespace.instance());
    }

    public RestResource1(ResourceEnum resourceEnum,
            TypeProvider<ResourceT> typeProvider, ServiceNamespace namespace) {
        super(resourceEnum.getResourceName(), typeProvider);
        this.resourceEnum = resourceEnum;
        this.namespace = namespace;
    }

    @Override
    public UriBuilder getBaseUrl() {
        return resourceEnum.getCoLabService().getBaseUrl(namespace);
    }

    public <ResourceT2, IdT2> RestResource<ResourceT2, IdT2> nestedResource(final IdT resourceId,
            String subResourceName, TypeProvider<ResourceT2> typeProvider) {

        return new RestResource2<ResourceT, IdT, ResourceT2, IdT2>(this, subResourceName,
                typeProvider).resolveParent(id(resourceId));
    }
}
