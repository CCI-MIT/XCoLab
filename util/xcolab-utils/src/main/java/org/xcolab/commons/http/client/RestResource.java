package org.xcolab.commons.http.client;

import org.xcolab.commons.http.client.interfaces.IdentifiableHttpResource;
import org.xcolab.commons.http.client.queries.CountQuery;
import org.xcolab.commons.http.client.queries.CreateQuery;
import org.xcolab.commons.http.client.queries.DeleteQuery;
import org.xcolab.commons.http.client.queries.GetQuery;
import org.xcolab.commons.http.client.queries.ListQuery;
import org.xcolab.commons.http.client.queries.UpdateQuery;

public interface RestResource<ResourceT, IdT>
        extends ServiceResource, IdentifiableHttpResource<ResourceT, IdT> {

    CreateQuery<ResourceT> create(ResourceT pojo);

    DeleteQuery<ResourceT, IdT> delete(IdT id);

    UpdateQuery<ResourceT, IdT> update(ResourceT pojo, IdT id);

    GetQuery<ResourceT, IdT> get(IdT id);

    ListQuery<ResourceT> list();

    CountQuery<ResourceT> count();
}
