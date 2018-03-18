package org.xcolab.commons.attributes;

/**
 * An interface to represent a generic attribute that can take numeric, string and real values.
 */
public interface Attribute {

    String getName();

    Long getAdditionalId();

    String getLocale();

    Long getNumericValue();

    String getStringValue();

    Double getRealValue();
}
