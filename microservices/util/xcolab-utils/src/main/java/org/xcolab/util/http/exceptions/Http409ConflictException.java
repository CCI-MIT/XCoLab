package org.xcolab.util.http.exceptions;

import org.xcolab.util.http.exceptions.translation.service.HttpServiceExceptionObject;

public class Http409ConflictException extends HttpRuntimeException {
    public Http409ConflictException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location) {
        super(httpServiceExceptionObject, location);
    }
}