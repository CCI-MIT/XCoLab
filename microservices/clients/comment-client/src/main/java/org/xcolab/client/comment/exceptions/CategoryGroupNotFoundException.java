package org.xcolab.client.comment.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class CategoryGroupNotFoundException extends EntityNotFoundException {

    public CategoryGroupNotFoundException(long groupId) {
        super("CategoryGroup with id " + groupId + " not found.",
                CategoryGroupNotFoundException.class);
    }
}
