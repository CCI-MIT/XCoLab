package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class CountQuery<T> {
    private final UriBuilder uriBuilder;
    private final Class<T> entityType;
    private String cacheIdentifier;

    public CountQuery(RestResource<T> restResource, Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl();
    }

    public int execute() {
        if (cacheIdentifier == null) {
            return RequestUtils.getCount(uriBuilder);
        } else {
            return RequestUtils.getCount(uriBuilder, entityType, cacheIdentifier);
        }
    }

    public CountQuery<T> cacheIdentifier(String cacheIdentifier) {
        this.cacheIdentifier = cacheIdentifier;
        return this;
    }

    public CountQuery<T> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    public CountQuery<T> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
