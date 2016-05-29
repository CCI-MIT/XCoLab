package org.xcolab.util.exceptions;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String msg) {
        super(msg);
    }
}
