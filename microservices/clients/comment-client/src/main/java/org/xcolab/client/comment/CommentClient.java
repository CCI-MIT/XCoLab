package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestService;

import java.util.Date;
import java.util.List;

public final class CommentClient {

    private static final RestService commentService = new RestService("comment-service");

    private static final CommentClientRaw commentClientRaw = new CommentClientRaw(commentService);

    private CommentClient() {
    }

    public static List<Comment> listComments(int start, int last) {
        return listComments(start, last, null);
    }

    public static List<Comment> listComments(int start, int last, Long threadId) {
        return commentClientRaw.listComments(start, last, "createDate", null, threadId, null);
    }

    public static int countComments(long threadId) {
        return commentClientRaw.countComments(null, threadId, null);
    }

    public static int countCommentsByAuthor(long authorId) {
        return commentClientRaw.countComments(authorId, null, null);
    }

    public static int countCommentsInContestPhase(long contestPhaseId, long contestId) {
        return commentClientRaw.countCommentsInContestPhase(contestPhaseId, contestId,
                CacheRetention.SHORT);
    }

    public static Comment getComment(long commentId) throws CommentNotFoundException {
        return getComment(commentId, false);
    }

    public static Comment getComment(long commentId, boolean includeDeleted)
            throws CommentNotFoundException {
        return commentClientRaw.getComment(commentId, includeDeleted, CacheRetention.REQUEST);
    }

    public static boolean updateComment(Comment comment) {
        return commentClientRaw.updateComment(comment);
    }

    public static Comment createComment(Comment comment) {
        return commentClientRaw.createComment(comment);
    }

    public static boolean deleteComment(long commentId) {
        return commentClientRaw.deleteComment(commentId);
    }

//    Threads

    public static List<CommentThread> listThreads(int start, int last, Long categoryId,
            Long groupId, ThreadSortColumn sortColumn, boolean ascending) {
        return commentClientRaw.listThreads(start, last, sortColumn.getIdentifier(ascending),
                null, categoryId, groupId);
    }

    public static CommentThread getThread(long threadId) throws ThreadNotFoundException {
        return commentClientRaw.getThread(threadId, CacheRetention.MEDIUM);
    }

    public static Long getProposalIdForThread(long threadId) {
        return commentClientRaw.getProposalIdForThread(threadId, CacheRetention.RUNTIME);
    }

    public static boolean updateThread(CommentThread thread) {
        return commentClientRaw.updateThread(thread);
    }

    public static CommentThread createThread(CommentThread thread) {
        return commentClientRaw.createThread(thread);
    }

    public static Date getLastActivityDate(long threadId) {
        return commentClientRaw.getLastActivityDate(threadId, CacheRetention.REQUEST);
    }

    public static long getLastActivityAuthorId(long threadId) {
        return commentClientRaw.getLastActivityAuthorId(threadId, CacheRetention.REQUEST);
    }

    //    Category methods

    public static List<Category> listCategories(int start, int last, long groupId) {
        return commentClientRaw.listCategories(start, last, "sort", null, groupId, CacheRetention.LONG);
    }

    public static Category getCategory(long categoryId) throws CategoryNotFoundException {
        return commentClientRaw.getCategory(categoryId, CacheRetention.RUNTIME);
    }

    public static boolean updateCategory(Category category) {
        return commentClientRaw.updateCategory(category);
    }

    public static Category createCategory(Category category) {
        return commentClientRaw.createCategory(category);
    }

//    Category Group

    public static CategoryGroup getCategoryGroup(long groupId)
            throws CategoryGroupNotFoundException {
        return commentClientRaw.getCategoryGroup(groupId, CacheRetention.RUNTIME);
    }
}
