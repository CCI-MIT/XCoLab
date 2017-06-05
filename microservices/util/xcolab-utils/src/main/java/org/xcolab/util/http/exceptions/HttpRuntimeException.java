package org.xcolab.util.http.exceptions;

import org.xcolab.util.http.exceptions.translation.service.HttpServiceExceptionObject;

public class HttpRuntimeException extends RuntimeException {
    private final HttpServiceExceptionObject exceptionObject;
    private final String serviceName;

    public HttpRuntimeException(HttpServiceExceptionObject exceptionObject, String location,
            String serviceName) {
        this(exceptionObject, location, serviceName, null);
    }

    public HttpRuntimeException(HttpServiceExceptionObject exceptionObject, String location,
            String serviceName, Throwable cause) {
        super(String.format("%s (%s) encountered http error %d %s: %s", serviceName, location,
                exceptionObject.getStatus(), exceptionObject.getException(),
                exceptionObject.getMessage()), cause);
        this.exceptionObject = exceptionObject;
        this.serviceName = serviceName;
    }

    public HttpServiceExceptionObject getExceptionObject() {
        return exceptionObject;
    }

    public String getServiceName() {
        return serviceName;
    }
}
