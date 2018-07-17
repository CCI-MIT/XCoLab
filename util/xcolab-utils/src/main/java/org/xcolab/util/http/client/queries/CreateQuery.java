package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;

/**
 * Represents a POST request to a REST resource.
 *
 * A POST request to the rest resource typically creates a new member in the collection.
 *
 * The constructed HTTP request has the following basic format:
 * {@code POST /resourceName}.
 *
 * @param <ElementT> the type of the REST resource
 */
public class CreateQuery<ElementT> implements Query<ElementT, ElementT> {

    private final UriBuilder uriBuilder;
    private final ElementT pojo;
    private final Class<ElementT> entityType;

    /**
     * Creates a new instance of this query.
     *
     * In most cases, this method is not invoked directly but instead called from a convenience
     * method in the {@link RestResource} class.
     *
     * @see RestResource#create(Object)
     *
     * @param restResource the resource, on which this request is executed
     * @param pojo the element to be created
     * @param entityType the type of the resource being created
     */
    public CreateQuery(RestResource<ElementT, ?> restResource, ElementT pojo,
            Class<ElementT> entityType) {
        this.pojo = pojo;
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl();
    }

    @Override
    public ElementT execute() {
        return ServiceRequestUtils.post(uriBuilder, pojo, entityType);
    }

    @Override
    public CreateQuery<ElementT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public CreateQuery<ElementT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public CreateQuery<ElementT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
