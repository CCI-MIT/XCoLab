package org.xcolab.util.http.client.queries;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;

import java.util.List;

public class ListQuery<T> {
    private final UriBuilder uriBuilder;
    private final ParameterizedTypeReference<List<T>> typeReference;
    private String cacheIdentifierValue;

    public ListQuery(RestResource<T> restResource,
            ParameterizedTypeReference<List<T>> typeReference) {
        this.typeReference = typeReference;
        this.uriBuilder = restResource.getResourceUrl();
    }

    public List<T> execute() {
        if (cacheIdentifierValue == null) {
            return RequestUtils.getList(uriBuilder, typeReference);
        } else {
            return RequestUtils.getList(uriBuilder, typeReference, cacheIdentifierValue);
        }
    }

    public ListResult<T> executeWithResult() {
        return new ListResult<>(this);
    }

    public ListQuery<T> addRange(int startRecord, int limitRecord) {
        uriBuilder.queryParam("startRecord", startRecord)
                .queryParam("limitRecord", limitRecord);
        return this;
    }

    public ListQuery<T> cacheIdentifier(String cacheIdentifier) {
        this.cacheIdentifierValue = cacheIdentifier;
        return this;
    }

    public ListQuery<T> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    public ListQuery<T> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }

    public static class ListResult<T> {
        private final ListQuery<T> query;

        private ListResult(ListQuery<T> query) {
            this.query = query;
        }

        public List<T> get() {
            return query.execute();
        }

        public T getOne() {
            //fetch two elements so we can check if the result is unique
            List<T> result = query.addRange(0, 2).execute();
            if (result.size() == 1) {
                return result.get(0);
            }
            throw new IndexOutOfBoundsException("Expected exactly one element, found (at least) "
                    + result.size());
        }

        public T getFirst() {
            List<T> result = query.addRange(0, 1).execute();
            if (!result.isEmpty()) {
                return result.get(0);
            }
            throw new IndexOutOfBoundsException("Can't get first element of empty list");
        }

        public T getFirstIfExists() {
            List<T> result = query.addRange(0, 1).execute();
            if (!result.isEmpty()) {
                return result.get(0);
            }
            return null;
        }
    }
}
