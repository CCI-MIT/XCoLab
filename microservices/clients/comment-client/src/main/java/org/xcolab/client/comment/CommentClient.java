package org.xcolab.client.comment;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public final class CommentClient {

    private static final RestService commentService = new RestService("comment-service");
    private static final RestResource commentResource = new RestResource(commentService,
            "comments");
    private static final RestResource threadResource = new RestResource(commentService,
            "threads");
    private static final RestResource categoryResource = new RestResource(commentService,
            "categories");
    private static final RestResource categoryGroupResource = new RestResource(commentService,
            "categoryGroups");

    private CommentClient() {
    }

    public static List<Comment> listComments(int start, int last, long threadId) {
        final UriBuilder uriBuilder = commentResource.getResourceUrl()
                .addRange(start, last)
                .queryParam("threadId", threadId)
                .queryParam("sort", "createDate");

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Comment>>() {
        });
    }

    public static int countComments(long threadId) {
        final UriBuilder uriBuilder = commentResource.getResourceUrl()
                .queryParam("threadId", threadId);
        return RequestUtils.getCount(uriBuilder, Comment.class, "threadId_" + threadId);
    }

    public static Comment getComment(long commentId) throws CommentNotFoundException {
        return getComment(commentId, false);
    }

    public static Comment getComment(long commentId, boolean includeDeleted)
            throws CommentNotFoundException {
        final UriBuilder uriBuilder = commentResource.getResourceUrl(commentId)
                .queryParam("includeDeleted", includeDeleted);
        try {
            return RequestUtils.get(uriBuilder, Comment.class,
                    "commentId_" + commentId + "_includeDeleted_" + includeDeleted);
        } catch (EntityNotFoundException e) {
            throw new CommentNotFoundException("Comment with id " + commentId + " not found.");
        }
    }

    public static void updateComment(Comment comment) {
        final UriBuilder uriBuilder = commentResource.getResourceUrl(comment.getCommentId());
        RequestUtils.put(uriBuilder, comment);
    }

    public static Comment createComment(Comment comment) {
        final UriBuilder uriBuilder = commentResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, comment, Comment.class);
    }

    public static boolean deleteComment(long commentId) {
        final UriBuilder uriBuilder = commentResource.getResourceUrl(commentId);
        return RequestUtils.delete(uriBuilder);
    }

//    Threads

    public static List<CommentThread> listThreads(int start, int last, Long categoryId,
            Long groupId, ThreadSortColumn sortColumn, boolean ascending) {
        final UriBuilder uriBuilder = threadResource.getResourceUrl()
                .addRange(start, last)
                .optionalQueryParam("categoryId", categoryId)
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("sort", "createDate")
                .optionalQueryParam("sort", sortColumn.getIdentifier(ascending));

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<CommentThread>>() {
        });
    }

    public static CommentThread getThread(long threadId) throws ThreadNotFoundException {
        final UriBuilder uriBuilder = threadResource.getResourceUrl(threadId);
        try {
            return RequestUtils.get(uriBuilder, CommentThread.class, "threadId_" + threadId);
        } catch (EntityNotFoundException e) {
            throw new ThreadNotFoundException("Thread with id " + threadId + " not found.");
        }
    }

    public static Long getProposalIdForThread(long threadId) throws ThreadNotFoundException {
        final UriBuilder uriBuilder = threadResource.getResourceUrl(threadId)
                .path("/getProposalIdForThread");
        try {
            return RequestUtils.get(uriBuilder, Long.class);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public static void updateThread(CommentThread thread) {
        final UriBuilder uriBuilder = threadResource.getResourceUrl(thread.getThreadId());
        RequestUtils.put(uriBuilder, thread);
    }

    public static CommentThread createThread(CommentThread thread) {
        final UriBuilder uriBuilder = threadResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, thread, CommentThread.class);
    }

    public static Date getLastActivityDate(long threadId) {
        final UriBuilder uriBuilder = threadResource.getResourceUrl(threadId)
                .path("/lastActivityDate");
        return RequestUtils.getUnchecked(uriBuilder, Date.class,
                "lastActivityDate_threadId_" + threadId);
    }

    public static long getLastActivityAuthorId(long threadId) {
        final UriBuilder uriBuilder = threadResource.getResourceUrl(threadId)
                .path("/lastActivityAuthorId");
        return RequestUtils.getUnchecked(uriBuilder, Long.class,
                "lastActivityAuthorId_threadId_" + threadId);
    }

    //    Category methods

    public static List<Category> listCategories(int start, int last, long groupId) {
        final UriBuilder uriBuilder = categoryResource.getResourceUrl()
                .addRange(start, last)
                .queryParam("groupId", groupId)
                .queryParam("sort", "sort");

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Category>>() {
        });
    }

    public static Category getCategory(long categoryId) throws CategoryNotFoundException {
        final UriBuilder uriBuilder = categoryResource.getResourceUrl(categoryId);
        try {
            return RequestUtils.get(uriBuilder, Category.class, "categoryId_" + categoryId);
        } catch (EntityNotFoundException e) {
            throw new CategoryNotFoundException("Category with id " + categoryId + " not found.");
        }
    }

    public static void updateCategory(Category category) {
        final UriBuilder uriBuilder = categoryResource.getResourceUrl(category.getCategoryId());
        RequestUtils.put(uriBuilder, category);
    }

    public static Category createCategory(Category category) {
        final UriBuilder uriBuilder = categoryResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, category, Category.class);
    }

//    Category Group

    public static CategoryGroup getCategoryGroup(long groupId)
            throws CategoryGroupNotFoundException {
        final UriBuilder uriBuilder = categoryGroupResource.getResourceUrl(groupId);
        try {
            return RequestUtils.get(uriBuilder, CategoryGroup.class, "groupId" + groupId);
        } catch (EntityNotFoundException e) {
            throw new CategoryGroupNotFoundException("CategoryGroup with id " + groupId + " not found.");
        }
    }
}
