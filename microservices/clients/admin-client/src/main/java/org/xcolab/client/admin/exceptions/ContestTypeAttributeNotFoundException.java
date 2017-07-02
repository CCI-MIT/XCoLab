package org.xcolab.client.admin.exceptions;

import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;

public class ContestTypeAttributeNotFoundException extends AttributeNotFoundException {

    public ContestTypeAttributeNotFoundException(String name) {
        super("ContestTypeAttribute " + name + " could not be found");
    }

}
