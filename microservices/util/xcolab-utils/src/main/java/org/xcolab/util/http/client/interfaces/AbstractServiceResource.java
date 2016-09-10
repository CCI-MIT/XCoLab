package org.xcolab.util.http.client.interfaces;

import org.xcolab.util.http.client.ServiceResource;
import org.xcolab.util.http.client.queries.ServiceQuery;

public abstract class AbstractServiceResource implements ServiceResource {

    @Override
    public <T, R> ServiceQuery<T, R> service(long id, String serviceEndpoint, Class<R> returnType) {
        return new ServiceQuery<>(this, id, serviceEndpoint, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> service(String id, String serviceEndpoint, Class<R> returnType) {
        return new ServiceQuery<>(this, id, serviceEndpoint, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> service(String serviceEndpoint, Class<R> returnType) {
        return new ServiceQuery<>(this, serviceEndpoint, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> query(long id, Class<R> returnType) {
        return new ServiceQuery<>(this, id, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> query(String id, Class<R> returnType) {
        return new ServiceQuery<>(this, id, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> query(Class<R> returnType) {
        return new ServiceQuery<>(this, returnType);
    }
}
