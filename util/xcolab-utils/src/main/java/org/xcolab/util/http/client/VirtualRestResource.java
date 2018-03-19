package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.enums.ResourceEnum;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.interfaces.IdentifiableHttpResource;

/**
 * A virtual rest resource that does not require type information as it is never queried.
 *
 * This class serves as a placeholder for other services' entities in nested resource calls. These
 * resources can be used as a parent for a {@link RestResource2} and can then be used to resolve
 * the corresponding {@link QueryId} when a call is made to the nested resource.
 *
 * @param <ResourceT> The resource type of the nested object. Usually either an entity in another
 * service or {@link Void}.
 * @param <IdT> The type of the resource identifier. Often a {@link Long} or {@link String}.
 */
public class VirtualRestResource<ResourceT, IdT>
        implements IdentifiableHttpResource<ResourceT, IdT> {

    private final ResourceEnum resourceEnum;
    private final ServiceNamespace serviceNamespace;

    private VirtualRestResource(ResourceEnum resourceEnum, ServiceNamespace serviceNamespace) {
        this.resourceEnum = resourceEnum;
        this.serviceNamespace = serviceNamespace;
    }

    public static <ResourceT, IdT> VirtualRestResource<ResourceT, IdT> of(
            ResourceEnum resourceEnum) {
        return new VirtualRestResource<>(resourceEnum, ServiceNamespace.instance());
    }

    public static <ResourceT, IdT> VirtualRestResource<ResourceT, IdT> of(ResourceEnum resourceEnum,
            ServiceNamespace serviceNamespace) {
        return new VirtualRestResource<>(resourceEnum, serviceNamespace);
    }

    @Override
    public QueryId<ResourceT, IdT> id(IdT id) {
        return new QueryId<>(this, id);
    }

    @Override
    public UriBuilder getResourceUrl() {
        return getBaseUrl().resource(resourceEnum.getResourceName());
    }

    @Override
    public UriBuilder getResourceUrl(Object resourceId) {
        return getBaseUrl().resource(resourceEnum.getResourceName(), resourceId);
    }

    @Override
    public UriBuilder getBaseUrl() {
        return resourceEnum.getCoLabService().getBaseUrl(serviceNamespace);
    }
}
