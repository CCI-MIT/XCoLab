package org.xcolab.client.content.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ContentNotFoundException extends EntityNotFoundException {

    public ContentNotFoundException(String msg) {
        super(msg, ContentNotFoundException.class);
    }
}
