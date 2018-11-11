package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryClient {

    // Default instance when used statically
    private static final CategoryClient INSTANCE = new CategoryClient();

    private final CommentServiceWrapper commentServiceWrapper = new CommentServiceWrapper();

    private static final Map<ServiceNamespace, CategoryClient> instances = new HashMap<>();

    public static CategoryClient instance() {
        return INSTANCE;
    }

    public List<Category> listCategories(int start, int last, long groupId) {
        return commentServiceWrapper
                .listCategories(start, last, "sort", null, groupId, CacheName.MISC_LONG);
    }

    public Category getCategory(long categoryId) throws CategoryNotFoundException {
        return commentServiceWrapper.getCategory(categoryId, CacheName.MISC_RUNTIME);
    }

    public boolean updateCategory(Category category) {
        return commentServiceWrapper.updateCategory(category);
    }

    public Category createCategory(Category category) {
        return commentServiceWrapper.createCategory(category);
    }

    public CategoryGroup getCategoryGroup(long groupId)
            throws CategoryGroupNotFoundException {
        return commentServiceWrapper.getCategoryGroup(groupId, CacheName.MISC_RUNTIME);
    }

    public static CategoryClient fromService(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace,
                serviceNamespace1 -> new CategoryClient());
    }
}
