package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.LastActivityNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

class CommentServiceWrapper {

    private final RestResource<Comment, Long> commentResource;
    private final RestResource<CommentThread, Long> threadResource;
    private final RestResource<Category, Long> categoryResource;
    private final RestResource<CategoryGroup, Long> categoryGroupResource;

    CommentServiceWrapper() {
        commentResource = new RestResource1<>(CommentResource.COMMENT, Comment.TYPES);
        threadResource = new RestResource1<>(CommentResource.THREAD, CommentThread.TYPES);
        categoryResource = new RestResource1<>(CommentResource.CATEGORY, Category.TYPES);
        categoryGroupResource = new RestResource1<>(CommentResource.CATEGORY_GROUP,
                CategoryGroup.TYPES);
    }

    public List<Comment> listComments(Integer startRecord, Integer limitRecord, String sort,
            Long authorUserId, Long threadId, Boolean includeDeleted) {
        return commentResource.list()
                .addRange(startRecord, limitRecord)
                .optionalQueryParam("sort", sort)
                .optionalQueryParam("authorUserId", authorUserId)
                .optionalQueryParam("threadIds", threadId)
                .optionalQueryParam("includeDeleted", includeDeleted)
                .execute();
    }

    public int countComments(Long authorUserId, Collection<Long> threadIds) {
        return commentResource.count()
                .optionalQueryParam("authorUserId", authorUserId)
                .optionalQueryParam("threadIds", threadIds)
                .execute();
    }

    public Comment getComment(long commentId, boolean includeDeleted,
            CacheName cacheName)
            throws CommentNotFoundException {
        try {
            return commentResource.get(commentId)
                    .queryParam("includeDeleted", includeDeleted)
                    .withCache(CacheKeys.withClass(Comment.class)
                            .withParameter("id", commentId)
                            .withParameter("includeDeleted", includeDeleted).build(),
                            cacheName)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new CommentNotFoundException(commentId);
        }
    }

    public boolean updateComment(Comment comment) {
        return commentResource.update(comment, comment.getId()).execute();
    }

    public Comment createComment(Comment comment) {
        return commentResource.create(comment).execute();
    }

    public boolean deleteComment(long commentId) {
        return commentResource.delete(commentId).execute();
    }

//    Threads

    public List<CommentThread> listThreads(Integer startRecord, Integer limitRecord,
            String sort, Long authorUserId, Long categoryId, Long groupId) {
        return threadResource.list()
                .addRange(startRecord, limitRecord)
                .optionalQueryParam("categoryId", categoryId)
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("authorUserId", authorUserId)
                .optionalQueryParam("sort", sort)
                .execute();
    }

    public CommentThread getThread(long threadId, CacheName cacheName)
            throws ThreadNotFoundException {
        try {
            return threadResource.get(threadId)
                    .withCache(CacheKeys.of(CommentThread.class, threadId), cacheName)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ThreadNotFoundException(threadId);
        }
    }

    public boolean updateThread(CommentThread thread) {
        return threadResource.update(thread, thread.getId()).execute();
    }

    public CommentThread createThread(CommentThread thread) {
        return threadResource.create(thread).execute();
    }

    public void deleteThread(long threadId) {
        threadResource.delete(threadId);
    }

    public Date getLastActivityDate(long threadId, CacheName cacheName) {
        try {
            return threadResource.<CommentThread, Date>elementService(threadId, "lastActivityDate", Date.class)
                    .withCache(CacheKeys.withClass(CommentThread.class)
                            .withParameter("threadId", threadId)
                            .withParameter("date", "lastActivity")
                            .build(Date.class), cacheName)
                    .getChecked();
        } catch (EntityNotFoundException e) {
           throw new LastActivityNotFoundException(threadId);
        }
    }

    public long getLastActivityauthorUserId(long threadId, CacheName cacheName) {
        try {
            return threadResource.<CommentThread, Long>elementService(threadId, "lastActivityauthorUserId", Long.class)
                    .withCache(CacheKeys.withClass(CommentThread.class)
                            .withParameter("threadId", threadId)
                            .withParameter("author", "lastActivity")
                            .build(Long.class), cacheName)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            throw new LastActivityNotFoundException(threadId);
        }
    }

    //    Category methods

    public List<Category> listCategories(Integer startRecord, Integer limitRecord,
            String sort, Long authorUserId, Long groupId, CacheName cacheName) {
        return categoryResource.list()
                .addRange(startRecord, limitRecord)
                .optionalQueryParam("sort", sort)
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("authorUserId", authorUserId)
                .withCache(CacheKeys.withClass(Category.class)
                        .withParameter("groupId", groupId)
                        .withParameter("authorUserId", authorUserId)
                        .withParameter("sort", sort)
                        .asList(), cacheName)
                .execute();
    }

    public Category getCategory(long categoryId, CacheName cacheName)
            throws CategoryNotFoundException {
        try {
            return categoryResource.get(categoryId)
                    .withCache(CacheKeys.of(Category.class, categoryId), cacheName)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    public boolean updateCategory(Category category) {
        return categoryResource.update(category, category.getId()).execute();
    }

    public Category createCategory(Category category) {
        return categoryResource.create(category).execute();
    }

//    Category Group

    public CategoryGroup getCategoryGroup(long groupId, CacheName cacheName)
            throws CategoryGroupNotFoundException {
        try {
            return categoryGroupResource.get(groupId)
                    .withCache(CacheKeys.of(CategoryGroup.class, groupId), cacheName)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new CategoryGroupNotFoundException(groupId);
        }
    }
}
