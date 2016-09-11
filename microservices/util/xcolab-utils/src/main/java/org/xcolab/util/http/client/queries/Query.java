package org.xcolab.util.http.client.queries;

public interface Query<T, R> {

    Query<T, R> queryParam(String name, Object value);

    Query<T, R> optionalQueryParam(String name, Object value);

    R execute();
}
