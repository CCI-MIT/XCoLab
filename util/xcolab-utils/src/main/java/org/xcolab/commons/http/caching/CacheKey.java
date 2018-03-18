package org.xcolab.commons.http.caching;

import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CacheKey<T, R> {

    private final Class<T> elementType;
    private final Class<R> returnType;
    private final Map<String, String> parameters;
    private String stringKey;

    CacheKey(Class<T> elementType, Map<String, String> parameters) {
        Assert.notNull(elementType, "Element type may not be null");
        Assert.notEmpty(parameters, "Parameters map may not be empty");
        this.elementType = elementType;
        returnType = null;
        this.parameters = parameters;
        initStringKey();
    }

    private CacheKey(Class<T> elementType, Class<R> returnType, Map<String, String> parameters) {
        Assert.notNull(elementType, "Element type may not be null");
        Assert.notEmpty(parameters, "Parameters map may not be empty");
        this.elementType = elementType;
        this.returnType = returnType;
        this.parameters = parameters;
        initStringKey();
    }

    private void initStringKey() {
        StringBuilder sb = new StringBuilder(elementType.getSimpleName());
        for (Entry<String, String> parameter : parameters.entrySet()) {
            sb.append("_").append(parameter.getKey())
                    .append("_").append(parameter.getValue().replaceAll("\\s", "+"));
        }
        if (returnType != null) {
            sb.append("_as").append(returnType.getSimpleName());
        }
        stringKey = sb.toString();
    }

    public boolean isPresent() {
        return true;
    }

    public String stringKey() {
        return stringKey;
    }

    public Class<T> getElementType() {
        return elementType;
    }

    @Override
    public String toString() {
        return "CacheKey [" + stringKey() + "]";
    }

    public static class Builder<T> {

        private final Class<T> elementType;
        private final Map<String, String> parameters = new TreeMap<>();

        Builder(Class<T> elementType) {
            this.elementType = elementType;
        }

        private Builder(Builder<T> value) {
            this.elementType = value.elementType;
            this.parameters.putAll(value.parameters);
        }

        public PopulatedBuilder<T> withParameter(String name, Object value) {
            addParameter(name, value);
            return new PopulatedBuilder<>(this);
        }

        protected void addParameter(String name, Object value) {
            parameters.put(name, value == null ? "null" : value.toString());
        }

        private CacheKey<T, T> build() {
            return new CacheKey<>(elementType, parameters);
        }

        private <R> CacheKey<T, R> build(Class<R> returnType) {
            return new CacheKey<>(elementType, returnType, parameters);
        }

        public CacheKey<T, List<T>> asList() {
            parameters.put("type", "list");
            return new CacheKey<>(elementType, parameters);
        }

        public CacheKey<T, List<T>> asSingletonList(String selection) {
            parameters.put("type", "singletonList-" + selection);
            return new CacheKey<>(elementType, parameters);
        }

        public CacheKey<T, Integer> asCount() {
            parameters.put("type", "count");
            return new CacheKey<>(elementType, parameters);
        }
    }

    public static class PopulatedBuilder<T> extends Builder<T> {

        private PopulatedBuilder(Builder<T> builder) {
            super(builder);
        }

        @Override
        public PopulatedBuilder<T> withParameter(String name, Object value) {
            addParameter(name, value);
            return this;
        }

        public CacheKey<T, T> build() {
            return super.build();
        }

        public <R> CacheKey<T, R> build(Class<R> returnType) {
            return super.build(returnType);
        }
    }
}
