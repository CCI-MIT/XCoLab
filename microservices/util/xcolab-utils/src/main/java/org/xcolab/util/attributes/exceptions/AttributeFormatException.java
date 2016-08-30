package org.xcolab.util.attributes.exceptions;

public class AttributeFormatException extends RuntimeException {

    public AttributeFormatException(String message) {
        super(message);
    }

    public AttributeFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
