package org.xcolab.util.attributes;

/**
 * An interface to represent a generic attribute that can take numeric, string and real values.
 */
public interface Attribute {

    String getName();

    long getAdditionalId();

    Long getNumericValue();

    String getStringValue();

    Double getRealValue();
}
