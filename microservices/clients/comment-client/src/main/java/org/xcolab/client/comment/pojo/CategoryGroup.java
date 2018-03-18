package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;

import java.util.List;

public class CategoryGroup extends AbstractCategoryGroup {

    public static final TypeProvider<CategoryGroupDto> TYPES = new TypeProvider<>(CategoryGroupDto.class,
                    new ParameterizedTypeReference<List<CategoryGroupDto>>() {});

    private final ThreadClient threadClient;
    private final CategoryClient categoryClient;

    public CategoryGroup() {
        threadClient = ThreadClientUtil.getClient();
        categoryClient = CategoryClientUtil.getClient();
    }

    public CategoryGroup(Long groupId, String description, String url, Boolean isQuiet) {
        super(groupId, description, url, isQuiet);
        threadClient = ThreadClientUtil.getClient();
        categoryClient = CategoryClientUtil.getClient();
    }

    CategoryGroup(AbstractCategoryGroup abstractCategoryGroup, ServiceNamespace serviceNamespace) {
        super(abstractCategoryGroup);
        threadClient = new ThreadClient(serviceNamespace);
        categoryClient = new CategoryClient(serviceNamespace);
    }

    @JsonIgnore
    public List<CommentThread> getThreads(ThreadSortColumn sortColumn, boolean ascending) {
        return threadClient.listThreads(0, Integer.MAX_VALUE,
                null, getGroupId(), sortColumn, ascending);
    }

    @JsonIgnore
    public List<Category> getCategories() {
        return categoryClient.listCategories(0, Integer.MAX_VALUE, getGroupId());
    }

    @JsonIgnore
    public String getLinkUrl() {
        return getUrl();
    }
}
