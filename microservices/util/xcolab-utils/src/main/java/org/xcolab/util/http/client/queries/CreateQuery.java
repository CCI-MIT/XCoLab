package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;

public class CreateQuery<T> {

    private final UriBuilder uriBuilder;
    private final T pojo;
    private final Class<T> entityType;

    public CreateQuery(RestResource<T> restResource, T pojo, Class<T> entityType) {
        this.pojo = pojo;
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl();
    }

    public T execute() {
        return RequestUtils.post(uriBuilder, pojo, entityType);
    }

    public CreateQuery<T> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    public CreateQuery<T> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }


}
