package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.AbstractAttributeGetter;
import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeProvider;
import org.xcolab.util.attributes.exceptions.AttributeFormatException;
import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;

/**
 * A getter for values of a specified enum type.
 *
 * This getter interprets the result of {@link Attribute#getStringValue()} as the value of the
 * specified enum type.
 *
 * The {@link #get()} and {@link #get(long)} methods throw an {@link AttributeFormatException}
 * if no enum constant with the given name exists.
 *
 * @param <EnumT> the enum type to be resolved
 */
public class EnumAttribute<EnumT extends Enum<EnumT>> extends AbstractAttributeGetter<EnumT> {

    private final Class<EnumT> enumType;

    public EnumAttribute(AttributeProvider<? extends Attribute> attributeProvider, Class<EnumT> enumType) {
        super(attributeProvider);
        this.enumType = enumType;
    }

    @Override
    protected EnumT extractValue(Attribute attribute) {
        final String stringValue = attribute.getStringValue();
        if (stringValue == null) {
            throw new AttributeNotFoundException("No EnumAttribute found for "
                    + enumType.getCanonicalName());
        }
        try {
            return Enum.valueOf(enumType, stringValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AttributeFormatException("EnumAttribute for " + enumType.getCanonicalName()
                    + " can't resolve element " + stringValue);
        }
    }
}
