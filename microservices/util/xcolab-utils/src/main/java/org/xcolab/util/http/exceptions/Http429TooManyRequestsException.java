package org.xcolab.util.http.exceptions;

public class Http429TooManyRequestsException extends HttpRuntimeException {

    public Http429TooManyRequestsException(HttpServiceExceptionObject httpServiceExceptionObject) {
        super(httpServiceExceptionObject);
    }
}
