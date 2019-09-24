package org.xcolab.client.comment.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException(long categoryId) {
        super("Category with id " + categoryId + " not found.", CategoryNotFoundException.class);
    }
}
