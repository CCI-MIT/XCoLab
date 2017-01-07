package org.xcolab.util.http.exceptions;

import org.xcolab.util.http.exceptions.translation.service.HttpServiceExceptionObject;

public class HttpRuntimeException extends RuntimeException {
    private final HttpServiceExceptionObject exceptionObject;

    public HttpRuntimeException(HttpServiceExceptionObject exceptionObject, String location) {
        this(exceptionObject, location, null);
    }

    public HttpRuntimeException(HttpServiceExceptionObject exceptionObject,
            String location, Throwable cause) {
        super(String.format("%s encountered http error %d %s: %s", location,
                exceptionObject.getStatus(), exceptionObject.getException(),
                exceptionObject.getMessage()), cause);
        this.exceptionObject = exceptionObject;
    }

    public HttpServiceExceptionObject getExceptionObject() {
        return exceptionObject;
    }
}
