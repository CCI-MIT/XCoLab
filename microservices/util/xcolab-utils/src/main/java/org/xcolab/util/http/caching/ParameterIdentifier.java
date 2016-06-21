package org.xcolab.util.http.caching;

import java.util.Map;
import java.util.TreeMap;

public class ParameterIdentifier {

    private final StringBuilder identifier = new StringBuilder();

    private ParameterIdentifier(Builder builder) {
        if (builder.id != null) {
            identifier.append("id_").append(builder.id);
        }

        for (Map.Entry<String, String> entry : builder.parameters.entrySet()) {
            identifier.append(entry.getKey()).append("_").append(entry.getValue());
        }
    }

    public String getIdentifier() {
        return identifier.toString();
    }

    public static class Builder {
        private Long id;
        private final Map<String, String> parameters = new TreeMap<>();

        public Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder parameter(String name, String value) {
            parameters.put(name, value);
            return this;
        }

        public ParameterIdentifier build() {
            return new ParameterIdentifier(this);
        }
    }
}
