package org.xcolab.util.http.client.queries;

/**
 * Provides the basic interface for sending queries to an HTTP endpoint.
 *
 * Subclasses
 *
 * @param <ElementT> the type of the element the query represents - in a RESTful query, this is the resource
 * @param <ReturnT> the type of the value returned when the query is executed
 */
public interface Query<ElementT, ReturnT> {

    /**
     * Adds a query parameter to the request.
     *
     * @param name the name of the query parameter
     * @param value the value of the query parameter
     * @return this object to allow chaining calls
     */
    Query<ElementT, ReturnT> queryParam(String name, Object value);

    /**
     * Adds an array of query parameters to the request.
     *
     * The individual values are converted to Strings and separated by commas.
     *
     * @param name the name of the query parameter
     * @param value the value of the query parameter
     * @return this object to allow chaining calls
     */
    Query<ElementT, ReturnT> queryParam(String name, Object... value);

    /**
     * Adds a query parameter only if the value is not null.
     *
     * @param name the name of the query parameter
     * @param value the value of the query parameter
     * @return this object to allow chaining calls
     */
    Query<ElementT, ReturnT> optionalQueryParam(String name, Object value);


    /**
     * Executes the query.
     *
     * @return the result of the query
     */
    ReturnT execute();
}
