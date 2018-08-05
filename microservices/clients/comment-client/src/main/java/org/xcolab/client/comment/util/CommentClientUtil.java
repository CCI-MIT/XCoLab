package org.xcolab.client.comment.util;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;

public final class CommentClientUtil {

    private static final CommentClient commentClient = new CommentClient(
            ServiceNamespace.instance());

    private CommentClientUtil() {
    }

    public static List<Comment> listComments(int start, int last) {
        return listComments(start, last, null);
    }

    public static List<Comment> listComments(int start, int last, Long threadId) {
        return commentClient.listComments(start, last, threadId);
    }

    public static int countComments(long threadId) {
        return commentClient.countComments(threadId);
    }

    public static int countCommentsByAuthor(long authorUserid) {
        return commentClient.countCommentsByAuthor(authorUserid);
    }

    public static Comment getComment(long commentId) throws CommentNotFoundException {
        return getComment(commentId, false);
    }

    public static Comment getComment(long commentId, boolean includeDeleted)
            throws CommentNotFoundException {
        return commentClient.getComment(commentId, includeDeleted);
    }

    public static boolean updateComment(Comment comment) {
        return commentClient.updateComment(comment);
    }

    public static Comment createComment(Comment comment) {
        return commentClient.createComment(comment);
    }

    public static boolean deleteComment(long commentId) {
        return commentClient.deleteComment(commentId);
    }

    public static CommentClient getClient() {
        return commentClient;
    }
}
