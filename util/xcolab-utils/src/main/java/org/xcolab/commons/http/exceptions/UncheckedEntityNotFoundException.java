package org.xcolab.commons.http.exceptions;

import org.xcolab.commons.http.exceptions.translation.service.HttpServiceExceptionObject;

public class UncheckedEntityNotFoundException extends HttpRuntimeException {

    public UncheckedEntityNotFoundException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location, String serviceName) {
        super(httpServiceExceptionObject, location, serviceName);
    }
}
