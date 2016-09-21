package org.xcolab.util.http;

import org.springframework.web.util.UriComponentsBuilder;

public class UriProvider {

    private final UriBuilder uriBuilder;

    public UriProvider(String httpUrl) {
        this.uriBuilder = new UriBuilder(UriComponentsBuilder.fromHttpUrl(httpUrl));
    }

    public UriBuilder cloneUriBuilder() {
        return uriBuilder.cloneBuilder();
    }
}
