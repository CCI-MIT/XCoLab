package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public final class CommentClient {

    private static final RestService commentService = new RestService("comment-service");
    private static final RestResource<Comment> commentResource = new RestResource<>(commentService,
            "comments", Comment.TYPES);
    private static final RestResource<CommentThread> threadResource = new RestResource<>(
            commentService,
            "threads", CommentThread.TYPES);
    private static final RestResource<Category> categoryResource = new RestResource<>(
            commentService,
            "categories", Category.TYPES);
    private static final RestResource<CategoryGroup> categoryGroupResource = new RestResource<>(
            commentService,
            "categoryGroups", CategoryGroup.TYPES);

    private CommentClient() {
    }

    public static List<Comment> listComments(int start, int last, long threadId) {
        return commentResource.list()
                .addRange(start, last)
                .queryParam("threadId", threadId)
                .queryParam("sort", "createDate")
                .execute();
    }

    public static int countComments(long threadId) {
        return commentResource.count()
                .queryParam("threadId", threadId)
                .execute();
    }

    public static Comment getComment(long commentId) throws CommentNotFoundException {
        return getComment(commentId, false);
    }

    public static Comment getComment(long commentId, boolean includeDeleted)
            throws CommentNotFoundException {
        try {
            return commentResource.get(commentId)
                    .queryParam("includeDeleted", includeDeleted)
                    .cacheIdentifier("commentId_" + commentId + "_includeDeleted_" + includeDeleted)
                    .execute();
        } catch (EntityNotFoundException e) {
            throw new CommentNotFoundException(commentId);
        }
    }

    public static boolean updateComment(Comment comment) {
        return commentResource.update(comment, comment.getCommentId()).execute();
    }

    public static Comment createComment(Comment comment) {
        return commentResource.create(comment).execute();
    }

    public static boolean deleteComment(long commentId) {
        return commentResource.delete(commentId).execute();
    }

//    Threads

    public static List<CommentThread> listThreads(int start, int last, Long categoryId,
            Long groupId, ThreadSortColumn sortColumn, boolean ascending) {
        return threadResource.list()
                .addRange(start, last)
                .optionalQueryParam("categoryId", categoryId)
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("sort", "createDate")
                .optionalQueryParam("sort", sortColumn.getIdentifier(ascending))
                .execute();
    }

    public static CommentThread getThread(long threadId) throws ThreadNotFoundException {

        try {
            return threadResource.get(threadId)
                    .cacheIdentifier("threadId_" + threadId)
                    .execute();
        } catch (EntityNotFoundException e) {
            throw new ThreadNotFoundException(threadId);
        }
    }

    public static Long getProposalIdForThread(long threadId) throws ThreadNotFoundException {
        try {
            return threadResource.service(threadId, "getProposalIdForThread", Long.class).get();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public static boolean updateThread(CommentThread thread) {
        return threadResource.update(thread, thread.getThreadId()).execute();
    }

    public static CommentThread createThread(CommentThread thread) {
        return threadResource.create(thread).execute();
    }

    public static Date getLastActivityDate(long threadId) {
        return threadResource.service(threadId, "lastActivityDate", Date.class)
                .cacheIdentifier("lastActivityDate_threadId_" + threadId)
                .getUnchecked();
    }

    public static long getLastActivityAuthorId(long threadId) {
        return threadResource.service(threadId, "lastActivityAuthorId", Long.class)
                .cacheIdentifier("lastActivityAuthorId_threadId_" + threadId)
                .getUnchecked();
    }

    //    Category methods

    public static List<Category> listCategories(int start, int last, long groupId) {
        return categoryResource.list()
                .addRange(start, last)
                .queryParam("groupId", groupId)
                .queryParam("sort", "sort")
                .execute();
    }

    public static Category getCategory(long categoryId) throws CategoryNotFoundException {
        try {
            return categoryResource.get(categoryId)
                    .cacheIdentifier("categoryId_" + categoryId)
                    .execute();
        } catch (EntityNotFoundException e) {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    public static boolean updateCategory(Category category) {
        return categoryResource.update(category, category.getCategoryId()).execute();
    }

    public static Category createCategory(Category category) {
        return categoryResource.create(category).execute();
    }

//    Category Group

    public static CategoryGroup getCategoryGroup(long groupId)
            throws CategoryGroupNotFoundException {
        try {
            return categoryGroupResource.get(groupId)
                    .cacheIdentifier("groupId" + groupId)
                    .execute();
        } catch (EntityNotFoundException e) {
            throw new CategoryGroupNotFoundException(groupId);
        }
    }
}
