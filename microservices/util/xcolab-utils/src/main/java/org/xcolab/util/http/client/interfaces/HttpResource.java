package org.xcolab.util.http.client.interfaces;

import org.xcolab.util.http.UriBuilder;

public interface HttpResource extends HttpEndpoint {
    UriBuilder getResourceUrl();
    UriBuilder getResourceUrl(long resourceId);
    UriBuilder getResourceUrl(String resourceId);
}
