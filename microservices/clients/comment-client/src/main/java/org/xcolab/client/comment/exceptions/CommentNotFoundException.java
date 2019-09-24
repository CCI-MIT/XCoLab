package org.xcolab.client.comment.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class CommentNotFoundException extends EntityNotFoundException {

    public CommentNotFoundException(long commentId) {
        super("Comment with id " + commentId + " not found.", CommentNotFoundException.class);
    }
}
