package org.xcolab.commons.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class NullsafeJsonObjectBuilder implements JsonObjectBuilder {

    private final JsonObjectBuilder jsonObjectBuilder;

    private NullsafeJsonObjectBuilder(JsonObjectBuilder jsonObjectBuilder) {
        this.jsonObjectBuilder = jsonObjectBuilder;
    }

    public static NullsafeJsonObjectBuilder of(JsonObjectBuilder jsonObjectBuilder) {
        if (jsonObjectBuilder instanceof NullsafeJsonObjectBuilder) {
            return (NullsafeJsonObjectBuilder) jsonObjectBuilder;
        }
        return new NullsafeJsonObjectBuilder(jsonObjectBuilder);
    }

    @Override
    public NullsafeJsonObjectBuilder add(String s, JsonValue jsonValue) {
        if (jsonValue != null) {
            return of(jsonObjectBuilder.add(s, jsonValue));
        }
        return this;
    }

    @Override
    public NullsafeJsonObjectBuilder add(String s, String s1) {
        if (s1 != null) {
            return of(jsonObjectBuilder.add(s, s1));
        }
        return this;
    }

    @Override
    public NullsafeJsonObjectBuilder add(String s, BigInteger bigInteger) {
        if (bigInteger != null) {
            return of(jsonObjectBuilder.add(s, bigInteger));
        }
        return this;
    }

    @Override
    public NullsafeJsonObjectBuilder add(String s, BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return of(jsonObjectBuilder.add(s, bigDecimal));
        }
        return this;
    }

    @Override
    public NullsafeJsonObjectBuilder add(String s, int i) {
        return of(jsonObjectBuilder.add(s, i));
    }

    @Override
    public NullsafeJsonObjectBuilder add(String s, long l) {
        return of(jsonObjectBuilder.add(s, l));
    }

    @Override
    public NullsafeJsonObjectBuilder add(String s, double v) {
        return of(jsonObjectBuilder.add(s, v));
    }

    @Override
    public NullsafeJsonObjectBuilder add(String s, boolean b) {
        return of(jsonObjectBuilder.add(s, b));
    }

    public NullsafeJsonObjectBuilder add(String s, Integer i) {
        if (i != null) {
            return of(jsonObjectBuilder.add(s, i));
        }
        return this;
    }

    public NullsafeJsonObjectBuilder add(String s, Long l) {
        if (l != null) {
            return of(jsonObjectBuilder.add(s, l));
        }
        return this;
    }

    public NullsafeJsonObjectBuilder add(String s, Double v) {
        if (v != null) {
            return of(jsonObjectBuilder.add(s, v));
        }
        return this;
    }

    public NullsafeJsonObjectBuilder add(String s, Boolean b) {
        if (b != null) {
            return of(jsonObjectBuilder.add(s, b));
        }
        return this;
    }

    @Override
    public NullsafeJsonObjectBuilder addNull(String s) {
        return of(jsonObjectBuilder.addNull(s));
    }

    @Override
    public NullsafeJsonObjectBuilder add(String s, JsonObjectBuilder jsonObjectBuilder) {
        if (jsonObjectBuilder != null) {
            return of(this.jsonObjectBuilder.add(s, jsonObjectBuilder));
        }
        return this;
    }

    @Override
    public NullsafeJsonObjectBuilder add(String s, JsonArrayBuilder jsonArrayBuilder) {
        if (jsonArrayBuilder != null) {
            return of(jsonObjectBuilder.add(s, jsonArrayBuilder));
        }
        return this;
    }

    @Override
    public JsonObject build() {
        return jsonObjectBuilder.build();
    }

    public  NullsafeJsonObjectBuilder addArray(String name, Collection<?> collection) {
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object obj: collection) {
            JsonUtil.addObject(arrayBuilder, obj);
        }
        jsonObjectBuilder.add(name, arrayBuilder);
        return this;
    }

    public NullsafeJsonObjectBuilder addArray(String name, Object[] array) {
        return addArray(name, Arrays.asList(array));
    }
}
