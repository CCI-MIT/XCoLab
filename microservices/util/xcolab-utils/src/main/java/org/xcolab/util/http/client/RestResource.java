package org.xcolab.util.http.client;

import org.xcolab.util.http.client.queries.CountQuery;
import org.xcolab.util.http.client.queries.CreateQuery;
import org.xcolab.util.http.client.queries.DeleteQuery;
import org.xcolab.util.http.client.queries.GetQuery;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.client.queries.UpdateQuery;

public interface RestResource<ResourceT, IdT> extends ServiceResource {

    CreateQuery<ResourceT> create(ResourceT pojo);

    DeleteQuery<ResourceT, IdT> delete(IdT id);

    UpdateQuery<ResourceT, IdT> update(ResourceT pojo, IdT id);

    GetQuery<ResourceT, IdT> get(IdT id);

    ListQuery<ResourceT> list();

    CountQuery<ResourceT> count();

    QueryId<ResourceT, IdT> id(IdT id);
}
