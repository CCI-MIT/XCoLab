package org.xcolab.util.http.client.queries;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.CollectionUtils;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;

import java.util.List;

/**
 * Represents a GET request to retrieve all members of a collection.
 *
 * The constructed HTTP request has the following basic format:
 * {@code GET /resourceName}.
 *
 * @param <ElementT> the type of the REST resource
 */
public class ListQuery<ElementT> implements CacheableQuery<ElementT, List<ElementT>> {

    private final UriBuilder uriBuilder;
    private final Class<ElementT> entityType;
    private final ParameterizedTypeReference<List<ElementT>> typeReference;
    private CacheKey<ElementT, List<ElementT>> cacheKey;
    private CacheName cacheName;

    /**
     * Creates a new instance of this query.
     *
     * In most cases, this method is not invoked directly but instead called from a convenience
     * method in the {@link RestResource} class.
     *
     * @see RestResource#list()
     *
     * @param restResource the resource, on which this request is executed
     * @param entityType the type of the resource being retrieved
     * @param typeReference the list type of resource being retrieved
     */
    public ListQuery(RestResource<ElementT, ?> restResource, Class<ElementT> entityType,
            ParameterizedTypeReference<List<ElementT>> typeReference) {
        this.entityType = entityType;
        this.typeReference = typeReference;
        this.uriBuilder = restResource.getResourceUrl();
    }

    /**
     * Executes the list request and immediately returns the full list of elements.
     *
     * If you wish to only retrieve a part of the list, consider invoking {@link #executeWithResult()}
     * and using the convenience methods of {@link ListResult} instead.
     *
     * @see #executeWithResult()
     *
     * @return the list of elements in the collection matching the query
     */
    @Override
    public List<ElementT> execute() {
        if (cacheName == null) {
            return ServiceRequestUtils.getList(uriBuilder, typeReference);
        } else {
            if (cacheKey == null) {
                cacheKey = CacheKeys.withClass(entityType)
                        .withParameter("list-path", uriBuilder.getPathString())
                        .withParameter("list-uri-variables", uriBuilder.getUriVariableString())
                        .withParameter("list-parameters", uriBuilder.getParameterString()).asList();
            }
            return ServiceRequestUtils.getList(uriBuilder, typeReference, cacheKey, cacheName);
        }
    }

    /**
     * Returns a {@link ListResult} created from this query, allowing easy retrieval of a subset of the list.
     *
     * If you need the full list of results, you can simply invoke {@link #execute()} instead.
     *
     * Note: This method does not execute the query yet, the query is executed by the
     * convenience methods of the returned {@link ListResult} object.
     *
     * @see #execute()
     * @return an intermediary object that allows retrieving a subset of the result
     */
    public ListResult<ElementT> executeWithResult() {
        return new ListResult<>(this);
    }

    /**
     * Adds a condition to retrieve only a subset of the matching elements in the collection.
     *
     * This method is useful for paging or if you only need the first/last element(s).
     *
     * @param startRecord the index of the first record to be retrieved
     * @param limitRecord the index of the last record to be retrieved
     * @return this object to allow chaining of methods
     */
    public ListQuery<ElementT> addRange(int startRecord, int limitRecord) {
        uriBuilder.queryParam("startRecord", startRecord)
                .queryParam("limitRecord", limitRecord);
        return this;
    }

    @Override
    public ListQuery<ElementT> withCache(CacheKey<ElementT, List<ElementT>> cacheKey, CacheName cacheName) {
        this.cacheKey = cacheKey;
        this.cacheName = cacheName;
        return this;
    }

    @Override
    public ListQuery<ElementT> withCache(CacheName cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    @Override
    public ListQuery<ElementT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public ListQuery<ElementT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public ListQuery<ElementT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uriBuilder", uriBuilder.buildAndExpandString())
                .append("entityType", entityType)
                .append("typeReference", typeReference)
                .append("cacheKey", cacheKey)
                .append("cacheName", cacheName).toString();
    }

    /**
     * Intermediary result object that allows  retrieving only a subset of the matching elements.
     *
     * @param <ResultT> the element type of the result
     */
    public static class ListResult<ResultT> {

        private final ListQuery<ResultT> query;

        private ListResult(ListQuery<ResultT> query) {
            this.query = query;
        }

        /**
         * Executes the query directly without modifying it.
         *
         * Equivalent to {@link ListQuery#execute()}.
         *
         * @return all elements matching the query
         */
        public List<ResultT> get() {
            return query.execute();
        }

        /**
         * Retrieves a single element from a result containing exactly one element.
         *
         * This method is used when you know exactly one element will match the query and want to
         * retrieve this element instead of a list. This method verifies that exactly one element
         * matches the query and throws an {@link IndexOutOfBoundsException} otherwise.
         *
         * If you are trying to retrieve at most one object, use {@link #getOneIfExists()} instead.
         * If you are trying to retrieve the first element of a collection, use {@link #getFirst()}.
         *
         * @return the single element that matched the query
         * @throws IndexOutOfBoundsException if the query matched less or more than one elements
         */
        public ResultT getOne() {
            //fetch two elements so we can check if the result is unique
            List<ResultT> result = checkNonNull(query.addRange(0, 2).execute());
            if (result.size() == 1) {
                return result.get(0);
            }
            throw new IndexOutOfBoundsException(
                    "Expected exactly one element, found " + result.size());
        }

        /**
         * Retrieves a single element from a result containing at most one element.
         *
         * This method is used when you expect either zero or one elements to match the query and
         * want to retrieve this element instead of a list. This method verifies that at most one
         * element matches the query and throws an {@link IndexOutOfBoundsException} otherwise.
         *
         * If you are trying to retrieve exactly one object, use {@link #getOne()} instead.
         * If you are trying to retrieve the first element of a collection,
         * use {@link #getFirstIfExists()} ()}.
         *
         * @return the single element that matched the query or null
         * @throws IndexOutOfBoundsException if the query matched more than one elements
         */
        public ResultT getOneIfExists() {
            //fetch two elements so we can check if the result is unique
            List<ResultT> result = query.addRange(0, 2).execute();
            if (CollectionUtils.isEmpty(result)) {
                return null;
            }
            if (result.size() == 1) {
                return result.get(0);
            }
            throw new IndexOutOfBoundsException("Expected at most one element, found 2 or more");
        }

        /**
         * Retrieves a first element from a result containing at least one element.
         *
         * This method is used when you know want only the first element of a collection and know
         * that the query will match at least one element. This method verifies that at least one
         * element matches the query and throws an {@link IndexOutOfBoundsException} otherwise.
         *
         * If you are unsure if the collections contains any elements, use
         * {@link #getFirstIfExists()} instead.
         *
         * @return the first element in the collection
         * @throws IndexOutOfBoundsException if the query matched less or more than one elements
         */
        public ResultT getFirst() {
            List<ResultT> result = checkNonNull(query.addRange(0, 1).execute());
            if (!result.isEmpty()) {
                return result.get(0);
            }
            throw new IndexOutOfBoundsException("Can't get first element of empty list");
        }

        /**
         * Retrieves a first element from a result.
         *
         * If you want to verify any invariants about the number of elements in the collection,
         * use one of the related methods in this class.
         *
         * @return the first element in the collection
         */
        public ResultT getFirstIfExists() {
            List<ResultT> result = query.addRange(0, 1).execute();
            if (CollectionUtils.isEmpty(result)) {
                return null;
            }
            return result.get(0);
        }

        private List<ResultT> checkNonNull(List<ResultT> result) {
            if (result == null) {
                throw new EmptyQueryResultException(query);
            }
            return result;
        }

        private static class EmptyQueryResultException extends IllegalStateException {

            EmptyQueryResultException(ListQuery<?> query) {
                super("Got empty result for " + query);
            }
        }
    }
}
