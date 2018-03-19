package org.xcolab.util.http.exceptions;

import org.xcolab.util.http.exceptions.translation.service.HttpServiceExceptionObject;

public class Http400BadRequestException extends HttpRuntimeException {
    public Http400BadRequestException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location, String serviceName) {
        super(httpServiceExceptionObject, location, serviceName);
    }
}
