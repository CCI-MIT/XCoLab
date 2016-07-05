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
}
