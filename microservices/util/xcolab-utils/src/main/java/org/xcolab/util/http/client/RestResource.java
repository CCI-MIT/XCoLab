package org.xcolab.util.http.client;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.queries.CountQuery;
import org.xcolab.util.http.client.queries.CreateQuery;
import org.xcolab.util.http.client.queries.DeleteQuery;
import org.xcolab.util.http.client.queries.GetQuery;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.client.queries.ServiceQuery;
import org.xcolab.util.http.client.queries.UpdateQuery;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class RestResource<T> implements UrlProvider {

    private final UrlProvider urlProvider;
    private final String resourceName;
    private final Class<T> entityType;
    private final ParameterizedTypeReference<List<T>> typeReference;

    public RestResource(UrlProvider serviceOrParent, String resourceName,
            TypeProvider<T> typeProvider) {
        this.urlProvider = serviceOrParent;
        this.resourceName = resourceName;
        this.entityType = typeProvider.getEntityType();
        this.typeReference = typeProvider.getTypeReference();
    }

    @Override
    public UriBuilder getBaseUrl() {
        return urlProvider.getBaseUrl();
    }

    public UriBuilder getResourceUrl() {
        return urlProvider.getBaseUrl().path("/" + resourceName);
    }

    public UriBuilder getResourceUrl(long resourceId) {
        return urlProvider.getBaseUrl().path("/" + resourceName + "/" + resourceId);
    }

    public UriBuilder getResourceUrl(String resourceId) {
        return urlProvider.getBaseUrl().path("/" + resourceName + "/" + resourceId);
    }

    public <S> RestResource<S> getSubResource(long resourceId, String subResourceName,
            TypeProvider<S> typeProvider) {
        return new RestResource<>(
                new ParentResourceClient(this, resourceId),
                subResourceName, typeProvider);
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
        return new CountQuery<>(this, entityType);
    }

    public <O> ServiceQuery<T, O> service(long id, String serviceEndpoint, Class<O> returnType) {
        return new ServiceQuery<>(this, id, serviceEndpoint, returnType);
    }

    public <O> ServiceQuery<T, O> service(String id, String serviceEndpoint, Class<O> returnType) {
        return new ServiceQuery<>(this, id, serviceEndpoint, returnType);
    }

    public <O> ServiceQuery<T, O> service(String serviceEndpoint, Class<O> returnType) {
        return new ServiceQuery<>(this, serviceEndpoint, returnType);
    }

    private static class ParentResourceClient implements UrlProvider {

        private final RestResource parentRestResource;
        private final long parentResourceId;

        public ParentResourceClient(RestResource parentRestResource, long parentResourceId) {
            this.parentRestResource = parentRestResource;
            this.parentResourceId = parentResourceId;
        }

        @Override
        public UriBuilder getBaseUrl() {
            return parentRestResource.getResourceUrl(parentResourceId);
        }
    }
}
