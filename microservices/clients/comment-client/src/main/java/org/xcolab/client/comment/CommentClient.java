package org.xcolab.client.comment;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public final class CommentClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:"+RequestUtils.getServicesPort()+"/comment-service";

    private CommentClient() {
    }

    public static List<Comment> listComments(int start, int last, long threadId) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/comments")
                        .queryParam("startRecord", start)
                        .queryParam("limitRecord", last)
                        .queryParam("threadId", threadId)
                        .queryParam("sort", "createDate");

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Comment>>() {
        });
    }

    public static int countComments(long threadId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/comments")
                        .queryParam("threadId", threadId);
        return RequestUtils.getCount(uriBuilder, Comment.class, "threadId_" + threadId);
    }

    public static Comment getComment(long commentId) throws CommentNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/comments/" + commentId);
        try {
            return RequestUtils.get(uriBuilder, Comment.class, "commentId_" + commentId);
        } catch (EntityNotFoundException e) {
            throw new CommentNotFoundException("Comment with id " + commentId + " not found.");
        }
    }

    public static void updateComment(Comment comment) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/comments/" + comment.getCommentId());

        RequestUtils.put(uriBuilder, comment);
    }

    public static Comment createComment(Comment comment) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/comments");
        return RequestUtils.post(uriBuilder, comment, Comment.class);
    }

    public static void deleteComment(long commentId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/comments/" + commentId);
        RequestUtils.delete(uriBuilder);
    }

//    Threads

    public static List<CommentThread> listThreads(int start, int last, Long categoryId,
            Long groupId, ThreadSortColumn sortColumn, boolean ascending) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/threads")
                        .queryParam("startRecord", start)
                        .queryParam("limitRecord", last);
        if (categoryId != null) {
            uriBuilder.queryParam("categoryId", categoryId);
        }
        if (groupId != null) {
            uriBuilder.queryParam("groupId", groupId);
        }

        if (sortColumn == null) {
            uriBuilder.queryParam("sort", "createDate");
        } else {
            uriBuilder.queryParam("sort", sortColumn.getIdentifier(ascending));
        }

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<CommentThread>>() {
        });
    }

    public static CommentThread getThread(long threadId) throws ThreadNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/threads/" + threadId);
        try {
            return RequestUtils.get(uriBuilder, CommentThread.class, "threadId_" + threadId);
        } catch (EntityNotFoundException e) {
            throw new ThreadNotFoundException("Thread with id " + threadId + " not found.");
        }
    }

    public static void updateThread(CommentThread thread) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/threads/" + thread.getThreadId());

        RequestUtils.put(uriBuilder, thread);
    }

    public static CommentThread createThread(CommentThread thread) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/threads");
        return RequestUtils.post(uriBuilder, thread, CommentThread.class);
    }

    public static Date getLastActivityDate(long threadId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/threads/" + threadId + "/lastActivityDate");
        return RequestUtils.getUnchecked(uriBuilder, Date.class,
                "lastActivityDate_threadId_" + threadId);
//        try {
//            return new DateFormatter("yyyy-MM-dd HH:mm:ss").parse(dateFormat, Locale.US);
//        } catch (ParseException e) {
//            return new Date(0);
//        }
    }

    public static long getLastActivityAuthorId(long threadId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/threads/" + threadId + "/lastActivityAuthorId");
        return RequestUtils.getUnchecked(uriBuilder, Long.class,
                "lastActivityAuthorId_threadId_" + threadId);
    }

    //    Category methods

    public static List<Category> listCategories(int start, int last, long groupId) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/categories")
                        .queryParam("startRecord", start)
                        .queryParam("limitRecord", last)
                        .queryParam("groupId", groupId)
                        .queryParam("sort", "sort");

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Category>>() {
        });
    }

    public static Category getCategory(long categoryId) throws CategoryNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/categories/" + categoryId);
        try {
            return RequestUtils.get(uriBuilder, Category.class, "categoryId_" + categoryId);
        } catch (EntityNotFoundException e) {
            throw new CategoryNotFoundException("Category with id " + categoryId + " not found.");
        }
    }

    public static void updateCategory(Category category) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/categories/" + category.getCategoryId());

        RequestUtils.put(uriBuilder, category);
    }

    public static Category createCategory(Category category) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/categories");
        return RequestUtils.post(uriBuilder, category, Category.class);
    }

//    Category Group

    public static CategoryGroup getCategoryGroup(long groupId) throws CategoryGroupNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/groups/" + groupId);
        try {
            return RequestUtils.get(uriBuilder, CategoryGroup.class, "groupId" + groupId);
        } catch (EntityNotFoundException e) {
            throw new CategoryGroupNotFoundException("CategoryGroup with id " + groupId + " not found.");
        }
    }
}
