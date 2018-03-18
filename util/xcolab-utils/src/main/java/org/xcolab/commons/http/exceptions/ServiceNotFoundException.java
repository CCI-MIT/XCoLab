package org.xcolab.commons.http.exceptions;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String msg) {
        super(msg);
    }
}
