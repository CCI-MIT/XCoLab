package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;

/**
 * Represents a DELETE request remove an element from a collection.
 *
 * The constructed HTTP request has the following basic format:
 * {@code DELETE /resourceName/{resourceId}}.
 *
 * @param <ElementT> the type of the REST resource
 * @param <IdT> the type of the resource's identifier
 */
public class DeleteQuery<ElementT, IdT> implements Query<ElementT, Boolean> {

    private final UriBuilder uriBuilder;
    private final Class<ElementT> entityType;
    private final IdT id;
    private CacheName cacheName;

    /**
     * Creates a new instance of this query.
     *
     * In most cases, this method is not invoked directly but instead called from a convenience
     * method in the {@link RestResource} class.
     *
     * @see RestResource#delete(Object)
     *
     * @param restResource the resource, on which this request is executed
     * @param entityType the type of the resource being deleted
     * @param id the id of the resource to be deleted
     */
    public DeleteQuery(RestResource<ElementT, IdT> restResource, Class<ElementT> entityType,
            IdT id) {
        this.uriBuilder = restResource.getResourceUrl(id);
        this.entityType = entityType;
        this.id = id;
    }

    @Override
    public Boolean execute() {
        if (cacheName == null) {
            return ServiceRequestUtils.delete(uriBuilder);
        } else {
            return ServiceRequestUtils.delete(uriBuilder, CacheKeys.of(entityType, id), cacheName);
        }
    }

    public DeleteQuery<ElementT, IdT> cacheName(CacheName cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    @Override
    public DeleteQuery<ElementT, IdT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public DeleteQuery<ElementT, IdT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public DeleteQuery<ElementT, IdT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
