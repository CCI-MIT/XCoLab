package org.xcolab.commons.http.exceptions;

import org.xcolab.commons.http.exceptions.translation.service.HttpServiceExceptionObject;

public class Http500InternalServiceException extends HttpRuntimeException {
    public Http500InternalServiceException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location, String serviceName) {
        super(httpServiceExceptionObject, location, serviceName);
    }
}
