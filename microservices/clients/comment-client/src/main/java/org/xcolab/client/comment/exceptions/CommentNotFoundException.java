package org.xcolab.client.comment.exceptions;

public class CommentNotFoundException extends Exception {
    public CommentNotFoundException(long commentId) {
        super("Comment with id " + commentId + " not found.");
    }
}
