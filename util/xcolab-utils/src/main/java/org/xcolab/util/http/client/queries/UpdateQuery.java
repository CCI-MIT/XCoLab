package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;

/**
 * Represents a PUT request update an element in a collection.
 *
 * The constructed HTTP request has the following basic format:
 * {@code PUT /resourceName/{resourceId}}.
 *
 * @param <ElementT> the type of the REST resource
 * @param <IdT> the type of the resource's identifier
 */
public class UpdateQuery<ElementT, IdT> implements Query<ElementT, Boolean> {

    private final UriBuilder uriBuilder;
    private final Class<ElementT> entityType;
    private final IdT id;
    private final ElementT pojo;
    private CacheKey<ElementT, ElementT> cacheKey;
    private CacheName cacheName;

    /**
     * Creates a new instance of this query.
     *
     * In most cases, this method is not invoked directly but instead called from a convenience
     * method in the {@link RestResource} class.
     *
     * @see RestResource#update(Object, Object)
     *
     * @param restResource the resource, on which this request is executed
     * @param entityType the type of the resource being updated
     * @param id the id of the resource to be updated
     */
    public UpdateQuery(RestResource<ElementT, IdT> restResource, Class<ElementT> entityType, IdT id,
            ElementT pojo) {
        this.entityType = entityType;
        this.id = id;
        this.pojo = pojo;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    @Override
    public Boolean execute() {
        if (cacheName == null) {
            if (cacheKey != null) {
                return ServiceRequestUtils.put(uriBuilder, pojo, cacheKey, CacheName.MISC_REQUEST);
            } else {
                return ServiceRequestUtils.put(uriBuilder, pojo);
            }
        } else {
            if (cacheKey == null) {
                cacheKey = CacheKeys.of(entityType, id);
            }
            return ServiceRequestUtils.put(uriBuilder, pojo, cacheKey, cacheName);
        }
    }

    public UpdateQuery<ElementT, IdT> cacheKey(CacheKey<ElementT, ElementT> cacheKey) {
        this.cacheKey = cacheKey;
        return this;
    }

    public UpdateQuery<ElementT, IdT> cacheName(CacheName cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    @Override
    public UpdateQuery<ElementT, IdT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public UpdateQuery<ElementT, IdT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public UpdateQuery<ElementT, IdT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
