package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryDto;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.commons.http.caching.CacheName;
import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.dto.DtoUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryClient {

    private final CommentServiceWrapper commentServiceWrapper;
    private final ServiceNamespace serviceNamespace;

    private static final Map<ServiceNamespace, CategoryClient> instances = new HashMap<>();

    public CategoryClient(ServiceNamespace serviceNamespace) {
        commentServiceWrapper = CommentServiceWrapper.fromService(serviceNamespace);
        this.serviceNamespace = serviceNamespace;
    }

    public List<Category> listCategories(int start, int last, long groupId) {
        return DtoUtil.toPojos(commentServiceWrapper
                .listCategories(start, last, "sort", null, groupId, CacheName.MISC_LONG),
                serviceNamespace);
    }

    public Category getCategory(long categoryId) throws CategoryNotFoundException {
        return commentServiceWrapper.getCategory(categoryId, CacheName.MISC_RUNTIME)
                .toPojo(serviceNamespace);
    }

    public boolean updateCategory(Category category) {
        return commentServiceWrapper.updateCategory(new CategoryDto(category));
    }

    public Category createCategory(Category category) {
        return commentServiceWrapper.createCategory(new CategoryDto(category))
                .toPojo(serviceNamespace);
    }

    public CategoryGroup getCategoryGroup(long groupId)
            throws CategoryGroupNotFoundException {
        return commentServiceWrapper.getCategoryGroup(groupId, CacheName.MISC_RUNTIME)
                .toPojo(serviceNamespace);
    }

    public static CategoryClient fromService(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, CategoryClient::new);
    }
}
