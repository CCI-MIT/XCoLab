package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class CategoryGroup extends AbstractCategoryGroup {

    public static final TypeProvider<CategoryGroup> TYPES = new TypeProvider<>(CategoryGroup.class,
                    new ParameterizedTypeReference<List<CategoryGroup>>() {});

    public CategoryGroup() {
    }

    public CategoryGroup(Long groupId, String description, String url, Boolean isQuiet) {
        super(groupId, description, url, isQuiet);
    }

    CategoryGroup(AbstractCategoryGroup abstractCategoryGroup) {
        super(abstractCategoryGroup);
    }

    @JsonIgnore
    public List<CommentThread> getThreads(ThreadSortColumn sortColumn, boolean ascending) {
        return ThreadClient.instance().listThreads(0, Integer.MAX_VALUE,
                null, getId(), sortColumn, ascending);
    }

    @JsonIgnore
    public List<Category> getCategories() {
        return CategoryClient.instance().listCategories(0, Integer.MAX_VALUE, getId());
    }

    @JsonIgnore
    public String getLinkUrl() {
        return getUrl();
    }
}
