package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.AttributeProvider;
import org.xcolab.util.attributes.exceptions.AttributeFormatException;

/**
 * A getter for values of a specified enum type.
 *
 * This getter interprets the result of {@link Attribute#getStringValue()} as the value of the
 * specified enum type.
 *
 * @param <EnumT> the enum type to be resolved
 */
public class EnumAttribute<EnumT extends Enum<EnumT>> implements AttributeGetter<EnumT> {

    private final AttributeProvider<? extends Attribute> attributeProvider;
    private final Class<EnumT> enumType;

    public EnumAttribute(AttributeProvider<? extends Attribute> attributeProvider, Class<EnumT> enumType) {
        this.attributeProvider = attributeProvider;
        this.enumType = enumType;
    }

    /**
     * Retrieves an enum value from the {@link Attribute}'s string value.
     *
     * @return The enum constant of type {@code EnumT} represented by the string value
     * @throws AttributeFormatException if no enum constant with the given name exists
     */
    @Override
    public EnumT get() {
        return getEnumInstance(attributeProvider.get());
    }

    /**
     * Retrieves an enum value from the {@link Attribute}'s string value.
     *
     * @param additionalId additional ID value to identify the attribute
     * @return The enum constant of type {@code EnumT} represented by the string value
     * @throws AttributeFormatException if no enum constant with the given name exists
     */
    @Override
    public EnumT get(long additionalId) {
        return getEnumInstance(attributeProvider.get(additionalId));
    }

    private EnumT getEnumInstance(Attribute attribute) {
        final String stringValue = attribute.getStringValue();
        try {
            return Enum.valueOf(enumType, stringValue);
        } catch (IllegalArgumentException e) {
            throw new AttributeFormatException("EnumAttribute for " + enumType.getCanonicalName()
                    + " can't resolve element " + stringValue);
        }
    }
}
