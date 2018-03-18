package org.xcolab.commons.http.client.interfaces;

import org.xcolab.commons.http.UriBuilder;

public interface HttpResource {

    /**
     * Returns a new {@link UriBuilder} that is initialized with the endpoint's baseUrl.
     *
     * This method may not return the same {@link UriBuilder} in two separate invocations. If an
     * implementation stores the baseUrl's {@link UriBuilder}, it must make a copy using the
     * {@link UriBuilder#cloneBuilder()} method before returning it.
     *
     * @return a {@link UriBuilder} initialized with the baseUrl.
     */
    UriBuilder getBaseUrl();

    UriBuilder getResourceUrl();

    UriBuilder getResourceUrl(Object resourceId);
}
