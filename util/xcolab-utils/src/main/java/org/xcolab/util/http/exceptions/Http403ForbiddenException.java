package org.xcolab.util.http.exceptions;

import org.xcolab.util.http.exceptions.translation.service.HttpServiceExceptionObject;

public class Http403ForbiddenException extends HttpRuntimeException {
    public Http403ForbiddenException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location, String serviceName) {
        super(httpServiceExceptionObject, location, serviceName);
    }
}
