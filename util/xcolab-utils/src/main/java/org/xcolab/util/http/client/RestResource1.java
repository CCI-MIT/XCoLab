package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.enums.ResourceEnum;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.interfaces.AbstractRestResource;
import org.xcolab.util.http.client.types.TypeProvider;

/**
 * Represents a top-level REST resource that can be queried.
 *
 *
 * Using the query methods {@link #list()}, {@link #get(Object)}, {@link #create(Object)}
 * and {@link #update(Object, Object)} within this class, the resource can be easily queried and
 * modified in a RESTful manner.
 *
 * The class contains all information necessary to address this resource via a HTTP request. This
 * information is retrieved from a {@link ResourceEnum} representing the resource and the service
 * it belongs to, as well as the {@link ServiceNamespace}, which is used to distinguish between
 * services belonging to different xCoLabs (to facilitate data sharing between them).
 *
 * @param <ResourceT> the type of the resource element
 * @param <IdT> the type of the element identifier
 */
public class RestResource1<ResourceT, IdT> extends AbstractRestResource<ResourceT, IdT> {

    private final ResourceEnum resourceEnum;
    private final ServiceNamespace namespace;

    /**
     * Creates a new RestResource that represents one specific top-level resource.
     *
     * @param resourceEnum information about which resource to address and how to reach it
     * @param typeProvider a container for the resource's entity and list types
     */
    public RestResource1(ResourceEnum resourceEnum, TypeProvider<ResourceT> typeProvider) {
        this(resourceEnum, typeProvider, ServiceNamespace.instance());
    }

    /**
     * Creates a new RestResource that represents one specific top-level resource.
     *
     * @param resourceEnum information about which resource to address and how to reach it
     * @param typeProvider a container for the resource's entity and list types
     * @param namespace the namespace this resource should be addressed in
     */
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

    /**
     * Convenience method to return a nested resource.
     *
     * Use this method to access a nested resource, e.g. {@code /contests/123/contestPhase}.
     *
     * @param <ResourceT2> the element type of the nested resource
     * @param <IdT2> the type of the nested resource's element id
     * @param subResourceName the resource name of the nested resource
     * @param typeProvider a container for the nested resource's entity and list types
     * @return a nested resource
     */
    public <ResourceT2, IdT2> RestResource2<ResourceT, IdT, ResourceT2, IdT2> nestedResource(
            String subResourceName, TypeProvider<ResourceT2> typeProvider) {
        return new RestResource2<>(this, subResourceName, typeProvider);
    }

    /**
     * Convenience method to return a nested resource and immediately resolve its identifier.
     *
     * Use this method to access a nested resource, e.g. {@code /contests/123/contestPhase}.
     *
     * @param <ResourceT2> the element type of the nested resource
     * @param <IdT2> the type of the nested resource's element id
     * @param subResourceName the resource name of the nested resource
     * @param typeProvider a container for the nested resource's entity and list types
     * @param resourceId the identifier for the parent resource whose nested resource will be queried
     * @return a nested resource
     */
    public <ResourceT2, IdT2> RestResource<ResourceT2, IdT2> nestedResource(String subResourceName,
            TypeProvider<ResourceT2> typeProvider, final IdT resourceId) {

        return new RestResource2<ResourceT, IdT, ResourceT2, IdT2>(this, subResourceName,
                typeProvider).resolveParentId(id(resourceId));
    }
}
