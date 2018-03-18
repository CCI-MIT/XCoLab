package org.xcolab.commons.http.client.queries;

public interface Query<ElementT, ReturnT> {

    Query<ElementT, ReturnT> queryParam(String name, Object value);

    Query<ElementT, ReturnT> queryParam(String name, Object... value);

    Query<ElementT, ReturnT> optionalQueryParam(String name, Object value);

    ReturnT execute();
}
