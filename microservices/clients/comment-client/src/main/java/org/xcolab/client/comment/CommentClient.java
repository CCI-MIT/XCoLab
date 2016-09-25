package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentDto;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class CommentClient {

    private final CommentServiceWrapper commentServiceWrapper;
    private final RestService commentService;

    public CommentClient(RestService commentService) {
        commentServiceWrapper = CommentServiceWrapper.ofService(commentService);
        this.commentService = commentService;
    }

    public List<Comment> listComments(int start, int last) {
        return listComments(start, last, null);
    }

    public List<Comment> listComments(int start, int last, Long threadId) {
        return CommentDto.toPojos(
                commentServiceWrapper.listComments(start, last, "createDate", null, threadId, null),
                commentService);
    }

    public int countComments(long threadId) {
        return commentServiceWrapper.countComments(null, threadId, null);
    }

    public int countCommentsByAuthor(long authorId) {
        return commentServiceWrapper.countComments(authorId, null, null);
    }

    public int countCommentsInContestPhase(long contestPhaseId, long contestId) {
        return commentServiceWrapper.countCommentsInContestPhase(contestPhaseId, contestId,
                CacheRetention.SHORT);
    }

    public Comment getComment(long commentId) throws CommentNotFoundException {
        return getComment(commentId, false);
    }

    public Comment getComment(long commentId, boolean includeDeleted)
            throws CommentNotFoundException {
        return commentServiceWrapper.getComment(commentId, includeDeleted, CacheRetention.REQUEST)
                .toPojo(commentService);
    }

    public boolean updateComment(Comment comment) {
        return commentServiceWrapper.updateComment(new CommentDto(comment));
    }

    public Comment createComment(Comment comment) {
        return commentServiceWrapper.createComment(new CommentDto(comment)).toPojo(commentService);
    }

    public boolean deleteComment(long commentId) {
        return commentServiceWrapper.deleteComment(commentId);
    }
}
