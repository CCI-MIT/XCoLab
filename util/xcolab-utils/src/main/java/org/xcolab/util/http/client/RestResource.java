package org.xcolab.util.http.client;

import org.xcolab.util.http.client.interfaces.IdentifiableHttpResource;
import org.xcolab.util.http.client.queries.CountQuery;
import org.xcolab.util.http.client.queries.CreateQuery;
import org.xcolab.util.http.client.queries.DeleteQuery;
import org.xcolab.util.http.client.queries.GetQuery;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.client.queries.UpdateQuery;

/**
 * Represents a REST resource that can be queried.
 *
 * @param <ResourceT> the type of the resource elements
 * @param <IdT> the type of the element identifier
 */
public interface RestResource<ResourceT, IdT>
        extends ServiceResource, IdentifiableHttpResource<ResourceT, IdT> {

    /**
     * Starts a query to create a new element of this resource.
     *
     * The query can be further specified and then executed using {@link CreateQuery#execute()}.
     *
     * @param pojo The element to be created.
     * @return a query object
     */
    CreateQuery<ResourceT> create(ResourceT pojo);

    /**
     * Starts a query to delete an element from this resource's collection.
     *
     * The query can be further specified and then executed using {@link DeleteQuery#execute()}.
     *
     * @see DeleteQuery
     *
     * @param id the identifier of the element to be deleted
     * @return a query object
     */
    DeleteQuery<ResourceT, IdT> delete(IdT id);

    /**
     * Starts a query to update an element.
     *
     * The query can be further specified and then executed using {@link UpdateQuery#execute()}.
     *
     * @see UpdateQuery
     *
     * @param pojo the element to be updated
     * @param id the identifer of the element to be updated
     * @return a query object
     */
    UpdateQuery<ResourceT, IdT> update(ResourceT pojo, IdT id);

    /**
     * Starts a query to retrieve an element.
     *
     * The query can be further specified and then executed using {@link GetQuery#execute()}
     * or {@link GetQuery#executeChecked()}.
     *
     * @see GetQuery
     *
     * @param id the identifer of the element to be retrieved
     * @return a query object
     */
    GetQuery<ResourceT, IdT> get(IdT id);

    /**
     * Starts a query to retrieve a list of elements in this resource's collection.
     *
     * The query can be further specified, for example by adding query parameters, and then
     * executed using {@link ListQuery#execute()} or {@link ListQuery#executeWithResult()}.
     *
     * @see ListQuery
     *
     * @return a query object
     */
    ListQuery<ResourceT> list();

    /**
     * Starts a query to retrieve the count of elements in this resource's collections.
     *
     * The query can be further specified and then executed using {@link CountQuery#execute()}.
     *
     * @see CountQuery
     *
     * @return a query object
     */
    CountQuery<ResourceT> count();
}
