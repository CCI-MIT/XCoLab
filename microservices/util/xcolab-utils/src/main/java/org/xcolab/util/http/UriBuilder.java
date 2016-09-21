package org.xcolab.util.http;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

public class UriBuilder {

    private final UriComponentsBuilder uriComponentsBuilder;

    public UriBuilder(UriComponentsBuilder uriComponentsBuilder) {
        this.uriComponentsBuilder = uriComponentsBuilder;
    }

    public UriBuilder cloneBuilder() {
        return new UriBuilder(uriComponentsBuilder.cloneBuilder());
    }

    public UriBuilder path(String path) {
        uriComponentsBuilder.path(path);
        return this;
    }

    public UriBuilder resource(String resourceName) {
        uriComponentsBuilder.path("/" + resourceName);
        return this;
    }

    public UriBuilder resource(String resourceName, Object id) {
        uriComponentsBuilder.path("/" + resourceName + "/" + id.toString());
        return this;
    }

    public UriBuilder addRange(int startRecord, int limitRecord) {
        uriComponentsBuilder
                .queryParam("startRecord", startRecord)
                .queryParam("limitRecord", limitRecord);
        return this;
    }

    public UriBuilder queryParam(String parameterName, Object parameter) {
        uriComponentsBuilder.queryParam(parameterName, parameter);
        return this;
    }

    public UriBuilder queryParam(String parameterName, Object... parameter) {
        uriComponentsBuilder.queryParam(parameterName, parameter);
        return this;
    }

    public UriBuilder optionalQueryParam(String parameterName, Object optionalParameter) {
        if (optionalParameter != null && isNotBlankString(optionalParameter)) {
            uriComponentsBuilder.queryParam(parameterName, optionalParameter);
        }
        return this;
    }

    private boolean isNotBlankString(Object potentialString) {
        return !(potentialString instanceof String
                && StringUtils.isBlank((String) potentialString));
    }

    public String buildString() {
        return uriComponentsBuilder.build().toString();
    }
}
