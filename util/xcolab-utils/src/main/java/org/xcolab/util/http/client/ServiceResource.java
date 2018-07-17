package org.xcolab.util.http.client;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.interfaces.HttpResource;
import org.xcolab.util.http.client.queries.ServiceQuery;

import java.util.List;

public interface ServiceResource extends HttpResource {

    <T, R> ServiceQuery<T, R> elementService(Object id, String serviceEndpoint,
            Class<R> returnType);

    <T, R> ServiceQuery<T, R> collectionService(String serviceEndpoint, Class<R> returnType);

    <T, R> ServiceQuery<T, R> elementQuery(Object id, Class<R> returnType);

    <T, R> ServiceQuery<T, R> collectionQuery(Class<R> returnType);

    <T, R> ServiceQuery<T, R> elementService(Object id, String serviceEndpoint,
            ParameterizedTypeReference<List<T>> typeReference);

    <T, R> ServiceQuery<T, R> collectionService(String serviceEndpoint,
            ParameterizedTypeReference<List<T>> typeReference);
}
