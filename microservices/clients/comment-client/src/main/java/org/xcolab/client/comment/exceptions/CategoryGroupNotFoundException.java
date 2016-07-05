package org.xcolab.client.comment.exceptions;

public class CategoryGroupNotFoundException extends Exception {
    public CategoryGroupNotFoundException(long groupId) {
        super("CategoryGroup with id " + groupId + " not found.");
    }
}
