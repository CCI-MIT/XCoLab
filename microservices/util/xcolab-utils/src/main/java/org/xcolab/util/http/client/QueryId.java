package org.xcolab.util.http.client;

public class QueryId<ElementT, IdT> {

    private final RestResource<ElementT, IdT> resource;
    private final IdT id;

    public QueryId(RestResource<ElementT, IdT> resource, IdT id) {
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
