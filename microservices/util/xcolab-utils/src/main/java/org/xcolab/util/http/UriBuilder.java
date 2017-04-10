package org.xcolab.util.http;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class UriBuilder {

    private final UriComponentsBuilder uriComponentsBuilder;
    private final Map<String, Object> sortedParameters;
    private final StringBuilder pathBuilder;
    private final Map<String, Object> uriVariables;

    public UriBuilder(UriComponentsBuilder uriComponentsBuilder) {
        this.uriComponentsBuilder = uriComponentsBuilder;
        uriVariables = new TreeMap<>();
        pathBuilder = new StringBuilder();
        sortedParameters = new TreeMap<>();
    }

    public UriBuilder(UriBuilder uriBuilderToClone) {
        this.uriComponentsBuilder = uriBuilderToClone.uriComponentsBuilder.cloneBuilder();
        this.uriVariables = new TreeMap<>(uriBuilderToClone.uriVariables);
        this.pathBuilder = new StringBuilder(uriBuilderToClone.pathBuilder);
        this.sortedParameters = new TreeMap<>(uriBuilderToClone.sortedParameters);
    }

    public UriBuilder cloneBuilder() {
        return new UriBuilder(this);
    }

    public UriBuilder path(String path) {
        uriComponentsBuilder.path(path);
        pathBuilder.append(path);
        return this;
    }

    public UriBuilder resource(String resourceName) {
        return path("/" + resourceName);
    }

    public UriBuilder resource(String resourceName, Object id) {
        Assert.notNull(id, "id cannot be null");
        final String pathVariableName = resourceName + "Id";
        uriVariables.put(pathVariableName, id.toString());
        return path("/" + resourceName + "/{" + pathVariableName + "}");
    }

    public UriBuilder addRange(int startRecord, int limitRecord) {
        queryParam("startRecord", startRecord);
        return queryParam("limitRecord", limitRecord);
    }

    public UriBuilder queryParam(String parameterName, Object parameter) {
        uriComponentsBuilder.queryParam(parameterName, parameter);
        sortedParameters.put(parameterName, parameter);
        return this;
    }

    public UriBuilder queryParam(String parameterName, Object... parameter) {
        uriComponentsBuilder.queryParam(parameterName, parameter);
        sortedParameters.put(parameterName, parameter);
        return this;
    }

    public UriBuilder optionalQueryParam(String parameterName, Object optionalParameter) {
        if (optionalParameter != null && isNotBlankString(optionalParameter)) {
            queryParam(parameterName, optionalParameter);
        }
        return this;
    }

    public String getPathString() {
        return pathBuilder.toString();
    }

    public String getPathVariableString() {
        return uriVariables.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue().toString())
                .collect(Collectors.joining("&"));
    }

    public String getParameterString() {
        return sortedParameters.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue().toString())
                .collect(Collectors.joining("&"));
    }

    private boolean isNotBlankString(Object potentialString) {
        return !(potentialString instanceof String
                && StringUtils.isBlank((String) potentialString));
    }

    public String buildString() {
        return uriComponentsBuilder.buildAndExpand(uriVariables).toString();
    }

    public URI buildUri() {
        return uriComponentsBuilder.buildAndExpand(uriVariables).toUri();
    }
}
