package org.xcolab.util.http.client.interfaces;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.queries.CountQuery;
import org.xcolab.util.http.client.queries.CreateQuery;
import org.xcolab.util.http.client.queries.DeleteQuery;
import org.xcolab.util.http.client.queries.GetQuery;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.client.queries.UpdateQuery;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public abstract class AbstractRestResource<T> extends AbstractServiceResource implements RestResource<T> {

    private final Class<T> entityType;
    private final ParameterizedTypeReference<List<T>> typeReference;

    public AbstractRestResource(TypeProvider<T> typeProvider) {
        this.entityType = typeProvider.getEntityType();
        this.typeReference = typeProvider.getTypeReference();
    }

    @Override
    public CreateQuery<T> create(T pojo) {
        return new CreateQuery<>(this, pojo, entityType);
    }

    @Override
    public DeleteQuery<T> delete(long id) {
        return new DeleteQuery<>(this, id);
    }

    @Override
    public UpdateQuery<T> update(T pojo, long id) {
        return new UpdateQuery<>(this, id, pojo);
    }

    @Override
    public UpdateQuery<T> update(T pojo, String id) {
        return new UpdateQuery<>(this, id, pojo);
    }

    @Override
    public GetQuery<T> get(long id) {
        return new GetQuery<>(this, id, entityType);
    }

    @Override
    public GetQuery<T> get(String id) {
        return new GetQuery<>(this, id, entityType);
    }

    @Override
    public ListQuery<T> list() {
        return new ListQuery<>(this, typeReference);
    }

    @Override
    public CountQuery<T> count() {
        return new CountQuery<>(this);
    }
}
