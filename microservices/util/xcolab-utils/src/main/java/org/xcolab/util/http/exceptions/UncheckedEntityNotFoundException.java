package org.xcolab.util.http.exceptions;

public class UncheckedEntityNotFoundException extends HttpRuntimeException {

    public UncheckedEntityNotFoundException(HttpServiceExceptionObject httpServiceExceptionObject,
            String location) {
        super(httpServiceExceptionObject, location);
    }
}
