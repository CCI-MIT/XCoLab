package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class GetQuery<T> {
    private final UriBuilder uriBuilder;
    private final Class<T> entityType;
    private String cacheIdentifier;

    public GetQuery(RestResource<T> restResource, long id, Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    public GetQuery(RestResource<T> restResource, String id, Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    public T executeUnchecked() {
        if (cacheIdentifier == null) {
            return RequestUtils.getUnchecked(uriBuilder, entityType);
        } else {
            return RequestUtils.getUnchecked(uriBuilder, entityType, cacheIdentifier);
        }
    }

    public T execute() throws EntityNotFoundException {
        if (cacheIdentifier == null) {
            return RequestUtils.get(uriBuilder, entityType);
        } else {
            return RequestUtils.get(uriBuilder, entityType, cacheIdentifier);
        }
    }

    public GetQuery<T> cacheIdentifier(String cacheIdentifier) {
        this.cacheIdentifier = cacheIdentifier;
        return this;
    }

    public GetQuery<T> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    public GetQuery<T> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
