package org.xcolab.util.http.exceptions;

public class EurekaNotInitializedException extends RuntimeException {

    public EurekaNotInitializedException() {
        super("Eureka-enabled RestTemplate has not been initialized. "
                + "Service discovery does NOT work during startup phase! ");
    }
}
