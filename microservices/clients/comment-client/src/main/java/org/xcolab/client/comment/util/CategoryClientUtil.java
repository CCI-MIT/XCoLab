package org.xcolab.client.comment.util;

import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;

public final class CategoryClientUtil {

    private static final CategoryClient categoryClient = new CategoryClient(
            ServiceNamespace.instance());

    private CategoryClientUtil() {
    }

    public static List<Category> listCategories(int start, int last, long groupId) {
        return categoryClient.listCategories(start, last, groupId);
    }

    public static Category getCategory(long categoryId) throws CategoryNotFoundException {
        return categoryClient.getCategory(categoryId);
    }

    public static boolean updateCategory(Category category) {
        return categoryClient.updateCategory(category);
    }

    public static Category createCategory(Category category) {
        return categoryClient.createCategory(category);
    }

    public static CategoryGroup getCategoryGroup(long groupId)
            throws CategoryGroupNotFoundException {
        return categoryClient.getCategoryGroup(groupId);
    }

    public static CategoryClient getClient() {
        return categoryClient;
    }
}
