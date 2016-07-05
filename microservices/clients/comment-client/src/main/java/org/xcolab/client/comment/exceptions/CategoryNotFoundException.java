package org.xcolab.client.comment.exceptions;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(long categoryId) {
        super("Category with id " + categoryId + " not found.");
    }
}
