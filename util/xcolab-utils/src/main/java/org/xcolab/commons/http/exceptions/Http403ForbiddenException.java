package org.xcolab.commons.http.exceptions;

import org.xcolab.commons.http.exceptions.translation.service.HttpServiceExceptionObject;

public class Http403ForbiddenException extends HttpRuntimeException {
    public Http403ForbiddenException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location, String serviceName) {
        super(httpServiceExceptionObject, location, serviceName);
    }
}
