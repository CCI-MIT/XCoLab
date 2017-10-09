package org.xcolab.util.http.client.interfaces;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.QueryId;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.queries.CountQuery;
import org.xcolab.util.http.client.queries.CreateQuery;
import org.xcolab.util.http.client.queries.DeleteQuery;
import org.xcolab.util.http.client.queries.GetQuery;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.client.queries.UpdateQuery;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public abstract class AbstractRestResource<ResourceT, IdT> extends AbstractServiceResource
        implements RestResource<ResourceT, IdT> {

    private final Class<ResourceT> entityType;
    private final ParameterizedTypeReference<List<ResourceT>> typeReference;

    public AbstractRestResource(TypeProvider<ResourceT> typeProvider) {
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
