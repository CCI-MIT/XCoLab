package org.xcolab.commons.http.exceptions;

import org.xcolab.commons.http.exceptions.translation.service.HttpServiceExceptionObject;

public class Http400BadRequestException extends HttpRuntimeException {
    public Http400BadRequestException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location, String serviceName) {
        super(httpServiceExceptionObject, location, serviceName);
    }
}
