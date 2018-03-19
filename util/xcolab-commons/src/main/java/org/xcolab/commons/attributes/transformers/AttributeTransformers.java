package org.xcolab.commons.attributes.transformers;

import java.util.function.Function;
import java.util.regex.Pattern;

public final class AttributeTransformers {

    private static final Pattern SCHEME_REGEX = Pattern.compile("^https?://.*");

    private AttributeTransformers() {
    }

    public static Function<String, String> addDefaultScheme() {
        return url -> SCHEME_REGEX.matcher(url).matches() ? url : "http://" + url;
    }

}
