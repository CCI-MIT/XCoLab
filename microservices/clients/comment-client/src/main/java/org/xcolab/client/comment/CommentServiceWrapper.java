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
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CommentServiceWrapper {

    private static final Map<ServiceNamespace, CommentServiceWrapper> instances = new HashMap<>();

    private final RestResource<CommentDto, Long> commentResource;
    private final RestResource<CommentThreadDto, Long> threadResource;
    private final RestResource<CategoryDto, Long> categoryResource;
    private final RestResource<CategoryGroupDto, Long> categoryGroupResource;

    private CommentServiceWrapper(ServiceNamespace serviceNamespace) {
        Assert.notNull(serviceNamespace, "Service namespace is required");
        commentResource = new RestResource1<>(CommentResource.COMMENT, Comment.TYPES,
                serviceNamespace);
        threadResource = new RestResource1<>(CommentResource.THREAD, CommentThread.TYPES,
                serviceNamespace);
        categoryResource = new RestResource1<>(CommentResource.CATEGORY, Category.TYPES,
                serviceNamespace);
        categoryGroupResource = new RestResource1<>(CommentResource.CATEGORY_GROUP,
                CategoryGroup.TYPES, serviceNamespace);
    }

    static CommentServiceWrapper fromService(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, CommentServiceWrapper::new);
    }

    public List<CommentDto> listComments(Integer startRecord, Integer limitRecord, String sort,
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

    public CommentDto getComment(long commentId, boolean includeDeleted,
            CacheName cacheName)
            throws CommentNotFoundException {
        try {
            return commentResource.get(commentId)
                    .queryParam("includeDeleted", includeDeleted)
                    .withCache(CacheKeys.withClass(CommentDto.class)
                            .withParameter("id", commentId)
                            .withParameter("includeDeleted", includeDeleted).build(),
                            cacheName)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new CommentNotFoundException(commentId);
        }
    }

    public boolean updateComment(CommentDto comment) {
        return commentResource.update(comment, comment.getId()).execute();
    }

    public CommentDto createComment(CommentDto comment) {
        return commentResource.create(comment).execute();
    }

    public boolean deleteComment(long commentId) {
        return commentResource.delete(commentId).execute();
    }

//    Threads

    public List<CommentThreadDto> listThreads(Integer startRecord, Integer limitRecord,
            String sort, Long authorUserId, Long categoryId, Long groupId) {
        return threadResource.list()
                .addRange(startRecord, limitRecord)
                .optionalQueryParam("categoryId", categoryId)
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("authorUserId", authorUserId)
                .optionalQueryParam("sort", sort)
                .execute();
    }

    public CommentThreadDto getThread(long threadId, CacheName cacheName)
            throws ThreadNotFoundException {
        try {
            return threadResource.get(threadId)
                    .withCache(CacheKeys.of(CommentThreadDto.class, threadId), cacheName)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ThreadNotFoundException(threadId);
        }
    }

    public boolean updateThread(CommentThreadDto thread) {
        return threadResource.update(thread, thread.getId()).execute();
    }

    public CommentThreadDto createThread(CommentThreadDto thread) {
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

    public List<CategoryDto> listCategories(Integer startRecord, Integer limitRecord,
            String sort, Long authorUserId, Long groupId, CacheName cacheName) {
        return categoryResource.list()
                .addRange(startRecord, limitRecord)
                .optionalQueryParam("sort", sort)
                .optionalQueryParam("groupId", groupId)
                .optionalQueryParam("authorUserId", authorUserId)
                .withCache(CacheKeys.withClass(CategoryDto.class)
                        .withParameter("groupId", groupId)
                        .withParameter("authorUserId", authorUserId)
                        .withParameter("sort", sort)
                        .asList(), cacheName)
                .execute();
    }

    public CategoryDto getCategory(long categoryId, CacheName cacheName)
            throws CategoryNotFoundException {
        try {
            return categoryResource.get(categoryId)
                    .withCache(CacheKeys.of(CategoryDto.class, categoryId), cacheName)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    public boolean updateCategory(CategoryDto category) {
        return categoryResource.update(category, category.getId()).execute();
    }

    public CategoryDto createCategory(CategoryDto category) {
        return categoryResource.create(category).execute();
    }

//    Category Group

    public CategoryGroupDto getCategoryGroup(long groupId, CacheName cacheName)
            throws CategoryGroupNotFoundException {
        try {
            return categoryGroupResource.get(groupId)
                    .withCache(CacheKeys.of(CategoryGroupDto.class, groupId), cacheName)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new CategoryGroupNotFoundException(groupId);
        }
    }

    private static String convertListToGetParameter(List<Long> list, String parameterName) {
        if(list.isEmpty()){
            return "";
        }
        String parameterList = "";
        for(int i=0; i<list.size()-2; i++) {
            parameterList += list.get(i) + "&" + parameterName + "=";
        }
        parameterList += list.get(list.size()-1);
        return parameterList;
    }
}
