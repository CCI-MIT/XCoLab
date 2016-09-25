package org.xcolab.client.comment;

import org.springframework.util.Assert;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.LastActivityNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryDto;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.CategoryGroupDto;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentDto;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.pojo.CommentThreadDto;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CommentServiceWrapper {

    private static final Map<RestService, CommentServiceWrapper> instances = new HashMap<>();

    private final RestResource<CommentDto, Long> commentResource;
    private final RestResource<CommentThreadDto, Long> threadResource;
    private final RestResource<CategoryDto, Long> categoryResource;
    private final RestResource<CategoryGroupDto, Long> categoryGroupResource;

    private CommentServiceWrapper(RestService commentService) {
        Assert.notNull(commentService, "Comment service is required");
        commentResource = new RestResource1<>(commentService, "comments", Comment.TYPES);
        threadResource = new RestResource1<>(commentService, "threads", CommentThread.TYPES);
        categoryResource = new RestResource1<>(commentService, "categories", Category.TYPES);
        categoryGroupResource = new RestResource1<>(commentService, "groups", CategoryGroup.TYPES);
    }

    static CommentServiceWrapper ofService(RestService commentService) {
        CommentServiceWrapper serviceWrapper = instances.get(commentService);
        if (serviceWrapper == null) {
            serviceWrapper = new CommentServiceWrapper(commentService);
            instances.put(commentService, serviceWrapper);
        }
        return serviceWrapper;
    }

    public List<CommentDto> listComments(Integer startRecord, Integer limitRecord, String sort,
            Long authorId, Long threadId, Boolean includeDeleted) {
        return commentResource.list()
                .addRange(startRecord, limitRecord)
                .optionalQueryParam("sort", sort)
                .optionalQueryParam("authorId", authorId)
                .optionalQueryParam("threadId", threadId)
                .optionalQueryParam("includeDeleted", includeDeleted)
                .execute();
    }

    public int countComments(
            Long authorId, Long threadId, Boolean includeDeleted) {
        return commentResource.count()
                .optionalQueryParam("authorId", authorId)
                .optionalQueryParam("threadId", threadId)
                .optionalQueryParam("includeDeleted", includeDeleted)
                .execute();
    }


    public int countCommentsInContestPhase(long contestPhaseId, long contestId,
            CacheRetention cacheRetention) {
        try {
            return commentResource.<Comment, Integer>service("countCommentsInContestPhase", Integer.class)
                    .queryParam("contestPhaseId", contestPhaseId)
                    .queryParam("contestId", contestId)
                    .withCache(CacheKeys.withClass(Comment.class)
                            .withParameter("contestPhaseId", contestPhaseId)
                            .withParameter("contestId", contestId)
                            .asCount(), cacheRetention)
                    .getChecked();
        } catch(EntityNotFoundException ignored) {
            return 0;
        }
    }

    public CommentDto getComment(long commentId, boolean includeDeleted,
            CacheRetention cacheRetention)
            throws CommentNotFoundException {
        try {
            return commentResource.get(commentId)
                    .queryParam("includeDeleted", includeDeleted)
                    .withCache(CacheKeys.withClass(CommentDto.class)
                            .withParameter("id", commentId)
                            .withParameter("includeDeleted", includeDeleted).build(),
                            cacheRetention)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new CommentNotFoundException(commentId);
        }
    }

    public boolean updateComment(CommentDto comment) {
        return commentResource.update(comment, comment.getCommentId()).execute();
    }

    public CommentDto createComment(CommentDto comment) {
        return commentResource.create(comment).execute();
    }

    public boolean deleteComment(long commentId) {
        return commentResource.delete(commentId).execute();
    }

//    Threads

    public List<CommentThreadDto> listThreads(Integer startRecord, Integer limitRecord,
            String sort, Long authorId, Long categoryId, Long groupId) {
        return threadResource.list()
                .addRange(startRecord, limitRecord)
                .optionalQueryParam("categoryId", categoryId)
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("authorId", authorId)
                .optionalQueryParam("sort", sort)
                .execute();
    }

    public CommentThreadDto getThread(long threadId, CacheRetention cacheRetention)
            throws ThreadNotFoundException {
        try {
            return threadResource.get(threadId)
                    .withCache(CacheKeys.of(CommentThreadDto.class, threadId), cacheRetention)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ThreadNotFoundException(threadId);
        }
    }

    public Long getProposalIdForThread(long threadId, CacheRetention cacheRetention) {
        try {
            return threadResource.<CommentThread, Long>service(threadId, "getProposalIdForThread", Long.class)
                    .withCache(CacheKeys.withClass(CommentThread.class)
                            .withParameter("threadId", threadId)
                            .withParameter("service", "getProposalIdForThread")
                            .build(Long.class), cacheRetention)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public boolean updateThread(CommentThreadDto thread) {
        return threadResource.update(thread, thread.getThreadId()).execute();
    }

    public CommentThreadDto createThread(CommentThreadDto thread) {
        return threadResource.create(thread).execute();
    }

    public Date getLastActivityDate(long threadId, CacheRetention cacheRetention) {
        try {
            return threadResource.<CommentThread, Date>service(threadId, "lastActivityDate", Date.class)
                    .withCache(CacheKeys.withClass(CommentThread.class)
                            .withParameter("threadId", threadId)
                            .withParameter("date", "lastActivity")
                            .build(Date.class), cacheRetention)
                    .getChecked();
        } catch (EntityNotFoundException e) {
           throw new LastActivityNotFoundException(threadId);
        }
    }

    public long getLastActivityAuthorId(long threadId, CacheRetention cacheRetention) {
        try {
            return threadResource.<CommentThread, Long>service(threadId, "lastActivityAuthorId", Long.class)
                    .withCache(CacheKeys.withClass(CommentThread.class)
                            .withParameter("threadId", threadId)
                            .withParameter("author", "lastActivity")
                            .build(Long.class), cacheRetention)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            throw new LastActivityNotFoundException(threadId);
        }
    }

    //    Category methods

    public List<CategoryDto> listCategories(Integer startRecord, Integer limitRecord,
            String sort, Long authorId, Long groupId, CacheRetention cacheRetention) {
        return categoryResource.list()
                .addRange(startRecord, limitRecord)
                .optionalQueryParam("sort", sort)
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("authorId", authorId)
                .withCache(CacheKeys.withClass(CategoryDto.class)
                        .withParameter("groupId", groupId)
                        .withParameter("authorId", authorId)
                        .withParameter("sort", sort)
                        .asList(), cacheRetention)
                .execute();
    }

    public CategoryDto getCategory(long categoryId, CacheRetention cacheRetention)
            throws CategoryNotFoundException {
        try {
            return categoryResource.get(categoryId)
                    .withCache(CacheKeys.of(CategoryDto.class, categoryId), cacheRetention)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    public boolean updateCategory(CategoryDto category) {
        return categoryResource.update(category, category.getCategoryId()).execute();
    }

    public CategoryDto createCategory(CategoryDto category) {
        return categoryResource.create(category).execute();
    }

//    Category Group

    public CategoryGroupDto getCategoryGroup(long groupId, CacheRetention cacheRetention)
            throws CategoryGroupNotFoundException {
        try {
            return categoryGroupResource.get(groupId)
                    .withCache(CacheKeys.of(CategoryGroupDto.class, groupId), cacheRetention)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new CategoryGroupNotFoundException(groupId);
        }
    }
}
