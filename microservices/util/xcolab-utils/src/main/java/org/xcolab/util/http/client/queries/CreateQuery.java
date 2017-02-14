package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;

public class CreateQuery<ElementT> implements Query<ElementT, ElementT> {

    private final UriBuilder uriBuilder;
    private final ElementT pojo;
    private final Class<ElementT> entityType;

    public CreateQuery(RestResource<ElementT, ?> restResource, ElementT pojo, Class<ElementT> entityType) {
        this.pojo = pojo;
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl();
    }

    @Override
    public ElementT execute() {
        return ServiceRequestUtils.post(uriBuilder, pojo, entityType);
    }

    @Override
    public CreateQuery<ElementT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public CreateQuery<ElementT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public CreateQuery<ElementT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
