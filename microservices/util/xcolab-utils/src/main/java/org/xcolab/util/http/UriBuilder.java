package org.xcolab.util.http;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

public class UriBuilder {

    private final UriComponentsBuilder uriComponentsBuilder;

    public UriBuilder(UriComponentsBuilder uriComponentsBuilder) {
        this.uriComponentsBuilder = uriComponentsBuilder;
    }

    public UriBuilder path(String path) {
        uriComponentsBuilder.path(path);
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
        if (optionalParameter != null) {
            if (!(optionalParameter instanceof String
                    && StringUtils.isBlank((String) optionalParameter))) {
                uriComponentsBuilder.queryParam(parameterName, optionalParameter);
            }
        }
        return this;
    }

    public String buildString() {
        return uriComponentsBuilder.build().toString();
    }
}
