package org.xcolab.util.http.exceptions;

public class Http400BadRequestException extends HttpRuntimeException {
    public Http400BadRequestException(HttpServiceExceptionObject httpServiceExceptionObject) {
        super(httpServiceExceptionObject);
    }
}
