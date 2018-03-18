package org.xcolab.commons.json;

import java.util.Collection;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

public final class JsonUtil {

    private JsonUtil() {
    }

    public static NullsafeJsonObjectBuilder nullsafe(JsonObjectBuilder jsonObjectBuilder) {
        return NullsafeJsonObjectBuilder.of(jsonObjectBuilder);
    }

    public static NullsafeJsonArrayBuilder nullsafe(JsonArrayBuilder jsonObjectBuilder) {
        return NullsafeJsonArrayBuilder.of(jsonObjectBuilder);
    }

    static void addObject(JsonArrayBuilder arrayBuilder, Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Collection<?>) {
            NullsafeJsonArrayBuilder.of(arrayBuilder).addArray((Collection<?>) obj);
        }
        else if (obj.getClass().isArray()) {
            NullsafeJsonArrayBuilder.of(arrayBuilder).addArray((Object[]) obj);
        }
        else if (obj instanceof Integer) {
            arrayBuilder.add((Integer) obj);
        }
        else if (obj instanceof Long) {
            arrayBuilder.add((Long) obj);
        }
        else if (obj instanceof Double) {
            arrayBuilder.add((Double) obj);
        }
        else if (obj instanceof Boolean) {
            arrayBuilder.add((Boolean) obj);
        }
        else if (obj instanceof Class<?>) {
            arrayBuilder.add(((Class<?>) obj).getName());
        }
        else {
            arrayBuilder.add(String.valueOf(obj));
        }
    }
}
