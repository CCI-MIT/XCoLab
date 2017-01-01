package org.xcolab.util.http.exceptions;

public class Http403ForbiddenException extends HttpRuntimeException {
    public Http403ForbiddenException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location) {
        super(httpServiceExceptionObject, location);
    }
}
