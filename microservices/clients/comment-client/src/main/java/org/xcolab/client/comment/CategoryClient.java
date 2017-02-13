package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryDto;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryClient {

    private final CommentServiceWrapper commentServiceWrapper;
    private final RestService commentService;

    private static final Map<RestService, CategoryClient> instances = new HashMap<>();

    public CategoryClient(RestService commentService) {
        commentServiceWrapper = CommentServiceWrapper.fromService(commentService);
        this.commentService = commentService;
    }

    public List<Category> listCategories(int start, int last, long groupId) {
        return CategoryDto.toPojos(commentServiceWrapper
                .listCategories(start, last, "sort", null, groupId, CacheName.MISC_LONG),
                commentService);
    }

    public Category getCategory(long categoryId) throws CategoryNotFoundException {
        return commentServiceWrapper.getCategory(categoryId, CacheName.MISC_RUNTIME)
                .toPojo(commentService);
    }

    public boolean updateCategory(Category category) {
        return commentServiceWrapper.updateCategory(new CategoryDto(category));
    }

    public Category createCategory(Category category) {
        return commentServiceWrapper.createCategory(new CategoryDto(category))
                .toPojo(commentService);
    }

    public CategoryGroup getCategoryGroup(long groupId)
            throws CategoryGroupNotFoundException {
        return commentServiceWrapper.getCategoryGroup(groupId, CacheName.MISC_RUNTIME)
                .toPojo(commentService);
    }

    public static CategoryClient fromService(RestService contestService) {
        CategoryClient client = instances.get(contestService);
        if (client == null) {
            client = new CategoryClient(contestService);
            instances.put(contestService, client);
        }
        return client;
    }

}
