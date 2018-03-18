package org.xcolab.commons.attributes;

/**
 * This interface should be implemented by classes that know how to retrieve a specific
 * {@link Attribute} from the persistence layer.
 *
 * @param <AttributeT> the implementation of the {@link Attribute} interface to be retrieved
 */
public interface AttributeProvider<AttributeT extends Attribute> {

    String name();

    AttributeT get();

    AttributeT get(long additionalId);
}
