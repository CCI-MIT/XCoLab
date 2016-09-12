package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;

public class DeleteQuery<ElementT> implements Query<ElementT, Boolean> {

    private final UriBuilder uriBuilder;

    public DeleteQuery(RestResource<ElementT> restResource, long id) {
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    @Override
    public Boolean execute() {
        return RequestUtils.delete(uriBuilder);
    }

    @Override
    public DeleteQuery<ElementT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public DeleteQuery<ElementT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public DeleteQuery<ElementT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
