package org.xcolab.commons.http.client;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.interfaces.HttpResource;
import org.xcolab.commons.http.client.queries.ServiceQuery;

import java.util.List;

public interface ServiceResource extends HttpResource {

    <T, R> ServiceQuery<T, R> service(long id, String serviceEndpoint, Class<R> returnType);

    <T, R> ServiceQuery<T, R> service(String id, String serviceEndpoint, Class<R> returnType);

    <T, R> ServiceQuery<T, R> service(String serviceEndpoint, Class<R> returnType);

    <T, R> ServiceQuery<T, R> query(long id, Class<R> returnType);

    <T, R> ServiceQuery<T, R> query(String id, Class<R> returnType);

    <T, R> ServiceQuery<T, R> query(Class<R> returnType);

    <T, R> ServiceQuery<T, R> service(long id, String serviceEndpoint,
            ParameterizedTypeReference<List<T>> typeReference);

    <T, R> ServiceQuery<T, R> service(String id, String serviceEndpoint,
            ParameterizedTypeReference<List<T>> typeReference);

    <T, R> ServiceQuery<T, R> service(String serviceEndpoint,
            ParameterizedTypeReference<List<T>> typeReference);
}
