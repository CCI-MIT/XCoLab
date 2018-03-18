package org.xcolab.commons.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class NullsafeJsonArrayBuilder implements JsonArrayBuilder {

    private final JsonArrayBuilder jsonArrayBuilder;

    private NullsafeJsonArrayBuilder(JsonArrayBuilder jsonArrayBuilder) {
        this.jsonArrayBuilder = jsonArrayBuilder;
    }

    public static NullsafeJsonArrayBuilder of(JsonArrayBuilder jsonArrayBuilder) {
        if (jsonArrayBuilder instanceof NullsafeJsonArrayBuilder) {
            return (NullsafeJsonArrayBuilder) jsonArrayBuilder;
        }
        return new NullsafeJsonArrayBuilder(jsonArrayBuilder);
    }

    @Override
    public NullsafeJsonArrayBuilder add(JsonValue jsonValue) {
        if (jsonValue != null) {
            return of(jsonArrayBuilder.add(jsonValue));
        }
        return this;
    }

    @Override
    public NullsafeJsonArrayBuilder add(String s) {
        if (s != null) {
            return of(jsonArrayBuilder.add(s));
        }
        return this;
    }

    @Override
    public NullsafeJsonArrayBuilder add(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return of(jsonArrayBuilder.add(bigDecimal));
        }
        return this;
    }

    @Override
    public NullsafeJsonArrayBuilder add(BigInteger bigInteger) {
        if (bigInteger != null) {
            return of(jsonArrayBuilder.add(bigInteger));
        }
        return this;
    }

    @Override
    public NullsafeJsonArrayBuilder add(int i) {
        return of(jsonArrayBuilder.add(i));
    }

    @Override
    public NullsafeJsonArrayBuilder add(long l) {
        return of(jsonArrayBuilder.add(l));
    }

    @Override
    public NullsafeJsonArrayBuilder add(double v) {
        return of(jsonArrayBuilder.add(v));
    }

    @Override
    public NullsafeJsonArrayBuilder add(boolean b) {
        return of(jsonArrayBuilder.add(b));
    }

    @Override
    public NullsafeJsonArrayBuilder addNull() {
        return of(jsonArrayBuilder.addNull());
    }

    @Override
    public NullsafeJsonArrayBuilder add(JsonObjectBuilder jsonObjectBuilder) {
        return of(jsonArrayBuilder.add(jsonObjectBuilder));
    }

    @Override
    public NullsafeJsonArrayBuilder add(JsonArrayBuilder jsonArrayBuilder) {
        return of(this.jsonArrayBuilder.add(jsonArrayBuilder));
    }

    public  NullsafeJsonArrayBuilder addArray(Collection<?> collection) {
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object obj: collection) {
            JsonUtil.addObject(arrayBuilder, obj);
        }
        jsonArrayBuilder.add(arrayBuilder);
        return this;
    }

    public NullsafeJsonArrayBuilder addArray(Object[] array) {
        return addArray(Arrays.asList(array));
    }

    @Override
    public JsonArray build() {
        return jsonArrayBuilder.build();
    }
}
