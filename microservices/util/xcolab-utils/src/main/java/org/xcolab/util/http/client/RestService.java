package org.xcolab.util.http.client;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriProvider;
import org.xcolab.util.http.client.interfaces.HttpEndpoint;

public class RestService implements HttpEndpoint {

    private static final String SCHEMA = "HTTP://";
    private static final String HOST = "localhost";
    private static final String PORT = RequestUtils.getServicesPort();
    private final String serviceName;

    public RestService(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public UriProvider getBaseUrl() {
        return new UriProvider(SCHEMA + HOST + ":" + PORT + "/" + serviceName);
    }
}
