package org.xcolab.util.http.client;

import org.xcolab.util.http.client.interfaces.IdentifiableHttpResource;

public class QueryId<ElementT, IdT> {

    private final IdentifiableHttpResource<ElementT, IdT> resource;
    private final IdT id;

    public QueryId(IdentifiableHttpResource<ElementT, IdT> resource, IdT id) {
        this.resource = resource;
        this.id = id;
    }

    public IdT getId() {
        return id;
    }

    @Override
    public String toString() {
        return "QueryId[ id = " + id + ", resource = " + resource + " ]";
    }
}
