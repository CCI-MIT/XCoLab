package org.xcolab.commons.http.exceptions;

import org.xcolab.commons.http.exceptions.translation.service.HttpServiceExceptionObject;

public class Http401UnauthorizedException extends HttpRuntimeException {
    public Http401UnauthorizedException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location, String serviceName) {
        super(httpServiceExceptionObject, location, serviceName);
    }
}
