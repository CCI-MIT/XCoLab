package org.xcolab.util.http.client;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.UriProvider;
import org.xcolab.util.http.client.interfaces.HttpEndpoint;
import org.xcolab.util.http.client.interfaces.HttpResource;
import org.xcolab.util.http.client.queries.CountQuery;
import org.xcolab.util.http.client.queries.CreateQuery;
import org.xcolab.util.http.client.queries.DeleteQuery;
import org.xcolab.util.http.client.queries.GetQuery;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.client.queries.UpdateQuery;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class RestResource<T> extends ServiceResource implements HttpResource {
    private final Class<T> entityType;
    private final ParameterizedTypeReference<List<T>> typeReference;

    public RestResource(HttpEndpoint serviceOrParent, String resourceName,
            TypeProvider<T> typeProvider) {
        super(serviceOrParent, resourceName);
        this.entityType = typeProvider.getEntityType();
        this.typeReference = typeProvider.getTypeReference();
    }

    public RestResource(HttpEndpoint serviceOrParent, String resourceName) {
        super(serviceOrParent, resourceName);
        this.entityType = null;
        this.typeReference = null;
    }

    public <S> RestResource<S> getSubRestResource(final long resourceId, String subResourceName,
            TypeProvider<S> typeProvider) {
        return new RestResource<>(new HttpEndpoint() {
            private final UriProvider baseUrl
                    = new UriProvider(RestResource.this.getResourceUrl(resourceId));
            @Override
            public UriProvider getBaseUrl() {
                return baseUrl;
            }
        }, subResourceName, typeProvider);
    }

    public CreateQuery<T> create(T pojo) {
        return new CreateQuery<>(this, pojo, entityType);
    }

    public DeleteQuery<T> delete(long id) {
        return new DeleteQuery<>(this, id);
    }

    public UpdateQuery<T> update(T pojo, long id) {
        return new UpdateQuery<>(this, id, pojo);
    }

    public UpdateQuery<T> update(T pojo, String id) {
        return new UpdateQuery<>(this, id, pojo);
    }

    public GetQuery<T> get(long id) {
        return new GetQuery<>(this, id, entityType);
    }

    public GetQuery<T> get(String id) {
        return new GetQuery<>(this, id, entityType);
    }

    public ListQuery<T> list() {
        return new ListQuery<>(this, typeReference);
    }

    public CountQuery<T> count() {
        return new CountQuery<>(this);
    }
}
