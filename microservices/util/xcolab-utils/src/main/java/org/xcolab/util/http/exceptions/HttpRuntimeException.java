package org.xcolab.util.http.exceptions;

public class HttpRuntimeException extends RuntimeException {
    private final HttpServiceExceptionObject exceptionObject;

    public HttpRuntimeException(HttpServiceExceptionObject exceptionObject) {
        this(exceptionObject, null);
    }

    public HttpRuntimeException(HttpServiceExceptionObject exceptionObject, Throwable cause) {
        super(String.format("Path %s encountered http error %d %s: %s", exceptionObject.getPath(),
                exceptionObject.getStatus(), exceptionObject.getException(),
                exceptionObject.getMessage()), cause);
        this.exceptionObject = exceptionObject;
    }

    public HttpServiceExceptionObject getExceptionObject() {
        return exceptionObject;
    }
}
