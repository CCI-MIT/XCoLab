package org.xcolab.util.http.exceptions;

public class UncheckedEntityNotFoundException extends RuntimeException {

    public UncheckedEntityNotFoundException(HttpServiceExceptionObject exceptionObject) {
        super("HTTP status 404: could not find resource at " + exceptionObject.getPath());
    }
}
