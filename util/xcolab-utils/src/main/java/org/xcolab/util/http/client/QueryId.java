package org.xcolab.util.http.client;

import org.xcolab.util.http.client.interfaces.IdentifiableHttpResource;

/**
 * Encapsulates an element's identifier together with information about the element.
 *
 * This generic, type-safe container allows binding an indentifier of a generic class,
 * e.g. a {@code Long} or a {@code String}, to information about which element it identifies.
 *
 * @param <ElementT> the type of the identified element
 * @param <IdT> the type of the identifier
 */
public class QueryId<ElementT, IdT> {

    private final IdentifiableHttpResource<ElementT, IdT> resource;
    private final IdT id;

    /**
     * Creates a new identifier for the one of the specified resource's elements.
     *
     * @param resource the resource whose element is to be identified
     * @param id the identifier
     */
    public QueryId(IdentifiableHttpResource<ElementT, IdT> resource, IdT id) {
        this.resource = resource;
        this.id = id;
    }

    /**
     * @return the raw identifier
     */
    public IdT getId() {
        return id;
    }

    @Override
    public String toString() {
        return "QueryId[ id = " + id + ", resource = " + resource + " ]";
    }
}
