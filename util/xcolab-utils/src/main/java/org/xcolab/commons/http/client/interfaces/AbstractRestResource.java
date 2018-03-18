package org.xcolab.commons.http.client.interfaces;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.QueryId;
import org.xcolab.commons.http.client.RestResource;
import org.xcolab.commons.http.client.queries.CountQuery;
import org.xcolab.commons.http.client.queries.CreateQuery;
import org.xcolab.commons.http.client.queries.DeleteQuery;
import org.xcolab.commons.http.client.queries.GetQuery;
import org.xcolab.commons.http.client.queries.ListQuery;
import org.xcolab.commons.http.client.queries.UpdateQuery;
import org.xcolab.commons.http.client.types.TypeProvider;

import java.util.List;

public abstract class AbstractRestResource<ResourceT, IdT> extends AbstractServiceResource
        implements RestResource<ResourceT, IdT> {

    private final Class<ResourceT> entityType;
    private final ParameterizedTypeReference<List<ResourceT>> typeReference;

    public AbstractRestResource(String resourceName, TypeProvider<ResourceT> typeProvider) {
        super(resourceName);
        this.entityType = typeProvider.getEntityType();
        this.typeReference = typeProvider.getTypeReference();
    }

    @Override
    public CreateQuery<ResourceT> create(ResourceT pojo) {
        return new CreateQuery<>(this, pojo, entityType);
    }

    @Override
    public DeleteQuery<ResourceT, IdT> delete(IdT id) {
        return new DeleteQuery<>(this, entityType, id);
    }

    @Override
    public UpdateQuery<ResourceT, IdT> update(ResourceT pojo, IdT id) {
        return new UpdateQuery<>(this, entityType, id, pojo);
    }

    @Override
    public GetQuery<ResourceT, IdT> get(IdT id) {
        return new GetQuery<>(this, id, entityType);
    }

    @Override
    public ListQuery<ResourceT> list() {
        return new ListQuery<>(this, entityType, typeReference);
    }

    @Override
    public CountQuery<ResourceT> count() {
        return new CountQuery<>(this, entityType);
    }

    @Override
    public QueryId<ResourceT, IdT> id(IdT id) {
        return new QueryId<>(this, id);
    }
}
