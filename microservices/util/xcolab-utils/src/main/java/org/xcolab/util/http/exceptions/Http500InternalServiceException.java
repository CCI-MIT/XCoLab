package org.xcolab.util.http.exceptions;

public class Http500InternalServiceException extends HttpRuntimeException {
    public Http500InternalServiceException(HttpServiceExceptionObject httpServiceExceptionObject) {
        super(httpServiceExceptionObject);
    }
}
