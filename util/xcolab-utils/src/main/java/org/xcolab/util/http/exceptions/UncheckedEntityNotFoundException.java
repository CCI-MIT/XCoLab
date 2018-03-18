package org.xcolab.util.http.exceptions;

import org.xcolab.util.http.exceptions.translation.service.HttpServiceExceptionObject;

public class UncheckedEntityNotFoundException extends HttpRuntimeException {

    public UncheckedEntityNotFoundException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location, String serviceName) {
        super(httpServiceExceptionObject, location, serviceName);
    }
}
