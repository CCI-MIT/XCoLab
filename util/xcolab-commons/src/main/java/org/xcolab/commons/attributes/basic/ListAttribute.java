package org.xcolab.commons.attributes.basic;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.commons.attributes.AbstractAttributeGetter;
import org.xcolab.commons.attributes.Attribute;
import org.xcolab.commons.attributes.AttributeProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A getter that parses the output of {@link Attribute#getStringValue()} as a comma-separated
 * list of a specified type using a specified conversion function.
 */
public class ListAttribute<T> extends AbstractAttributeGetter<List<T>> {

    private final Function<String, T> conversionFunction;

    public ListAttribute(AttributeProvider<? extends Attribute> attributeProvider,
            Function<String, T> conversionFunction) {
        super(attributeProvider);

        this.conversionFunction = conversionFunction;
    }

    @Override
    protected List<T> extractValue(Attribute attribute) {
        String commaSeparated = attribute.getStringValue();
        if (StringUtils.isEmpty(commaSeparated)) {
            return Collections.emptyList();
        }
        String[] stringIds = commaSeparated.trim().split("\\s*,\\s*");
        return Arrays.stream(stringIds)
                .map(conversionFunction)
                .collect(Collectors.toList());
    }
}
