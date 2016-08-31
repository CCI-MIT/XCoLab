package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;

public class DeleteQuery<T> implements Query<T, Boolean> {

    private final UriBuilder uriBuilder;

    public DeleteQuery(RestResource<T> restResource, long id) {
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    @Override
    public Boolean execute() {
        return RequestUtils.delete(uriBuilder);
    }

    @Override
    public DeleteQuery<T> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public DeleteQuery<T> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
