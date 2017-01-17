package org.xcolab.util.http.exceptions;

import org.xcolab.util.http.exceptions.translation.service.HttpServiceExceptionObject;

public class Http401UnauthorizedException extends HttpRuntimeException {
    public Http401UnauthorizedException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location) {
        super(httpServiceExceptionObject, location);
    }
}
