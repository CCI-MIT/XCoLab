package org.xcolab.util.http.client;

import org.springframework.util.Assert;

import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriProvider;
import org.xcolab.util.http.client.interfaces.HttpEndpoint;

public class RestService implements HttpEndpoint {

    private static final String SCHEMA = "HTTP://";
    private static final String DEFAULT_HOST_NAME = "localhost";
    private static final String DEFAULT_PORT = ServiceRequestUtils.getServicesPort();

    private final String serviceName;
    private final UriProvider uriProvider;
    public RestService(CoLabService serviceName) {
        this(serviceName.getServiceName());
    }
    public RestService(String serviceName) {
        Assert.notNull(serviceName, "Service name is required");
        this.serviceName = serviceName;
        uriProvider = getBaseUrl(DEFAULT_HOST_NAME, DEFAULT_PORT);
    }

    /**
     * Creates a new RestService instance that is identical to this one except for the new service name.
     *
     * Has to be overridden in child classes to copy all other internal state, if present.
     *
     * @param serviceName The new service name
     * @return a copy of this instance with a different service name
     */
    public RestService withServiceName(String serviceName) {
        return new RestService(serviceName);
    }

    @Override
    public UriProvider getBaseUrl() {
        return uriProvider;
    }

    public UriProvider getBaseUrl(String hostName, String port) {
        return new UriProvider(SCHEMA + hostName + ":" + port + "/" + serviceName);
    }

    @Override
    public int hashCode() {
        return serviceName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RestService)) {
            return false;
        }
        final RestService other = (RestService) obj;
        return serviceName.equals(other.serviceName);
    }
}
