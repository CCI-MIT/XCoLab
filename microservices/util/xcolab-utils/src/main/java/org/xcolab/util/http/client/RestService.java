package org.xcolab.util.http.client;

import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
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
    public UriBuilder getBaseUrl() {
        return new UriBuilder(UriComponentsBuilder
                .fromHttpUrl(SCHEMA + HOST + ":" + PORT + "/" + serviceName));
    }
}
