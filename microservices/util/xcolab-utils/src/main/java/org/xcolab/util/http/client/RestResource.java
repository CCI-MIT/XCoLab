package org.xcolab.util.http.client;

import org.xcolab.util.http.client.queries.CountQuery;
import org.xcolab.util.http.client.queries.CreateQuery;
import org.xcolab.util.http.client.queries.DeleteQuery;
import org.xcolab.util.http.client.queries.GetQuery;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.client.queries.UpdateQuery;

public interface RestResource<T> extends ServiceResource {

    CreateQuery<T> create(T pojo);

    DeleteQuery<T> delete(long id);

    UpdateQuery<T> update(T pojo, long id);

    UpdateQuery<T> update(T pojo, String id);

    GetQuery<T> get(long id);

    GetQuery<T> get(String id);

    ListQuery<T> list();

    CountQuery<T> count();
}
