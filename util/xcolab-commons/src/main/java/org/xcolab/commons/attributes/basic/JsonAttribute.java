package org.xcolab.commons.attributes.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import org.xcolab.commons.attributes.AbstractAttributeGetter;
import org.xcolab.commons.attributes.Attribute;
import org.xcolab.commons.attributes.AttributeProvider;
import org.xcolab.commons.attributes.exceptions.AttributeFormatException;

import java.io.IOException;

public class JsonAttribute<T> extends AbstractAttributeGetter<T> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> entityClass;
    private final ObjectReader reader;

    public JsonAttribute(AttributeProvider<? extends Attribute> attributeProvider,
            Class<T> entityClass) {
        super(attributeProvider);
        this.entityClass = entityClass;
        reader = objectMapper.readerFor(entityClass);
    }

    @Override
    protected T extractValue(Attribute attribute) {
        final String jsonString = attribute.getStringValue();
        try {
            return reader.readValue(jsonString);
        } catch (IOException e) {
            throw new AttributeFormatException(
                    "Cannot read json value for class " + entityClass.getName(), e);
        }
    }
}
