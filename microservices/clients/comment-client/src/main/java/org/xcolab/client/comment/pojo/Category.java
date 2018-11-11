package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.util.http.client.types.TypeProvider;

import java.sql.Timestamp;
import java.util.List;

public class Category extends AbstractCategory {

    public static final TypeProvider<Category> TYPES =
            new TypeProvider<>(Category.class, new ParameterizedTypeReference<List<Category>>() {});

    public Category() {
    }

    public Category(Long categoryId, Long groupId, Long authorUserId, String name, String description,
            Timestamp createdAt, Timestamp deletedAt, Integer sort, Boolean isQuiet) {
        super(categoryId, groupId, authorUserId, name, description,
                createdAt, deletedAt, sort, isQuiet);
    }

    public Category(AbstractCategory abstractCategory) {
        super(abstractCategory);
    }

    @JsonIgnore
    public List<CommentThread> getThreads(ThreadSortColumn sortColumn, boolean ascending) {
        return ThreadClient.instance().listThreads(0, Integer.MAX_VALUE,
                getId(), null, sortColumn, ascending);
    }

    @JsonIgnore
    public CategoryGroup getCategoryGroup() {
        final Long groupId = getGroupId();
        if (groupId != null && groupId > 0) {
            try {
                return CategoryClient.instance().getCategoryGroup(groupId);
            } catch (CategoryGroupNotFoundException e) {
                throw new KeyReferenceException(e);
            }
        }
        return null;
    }

    @JsonIgnore
    public String getLinkUrl() {
        final CategoryGroup categoryGroup = getCategoryGroup();
        if (categoryGroup != null) {
            return categoryGroup.getLinkUrl() + "/category/" + getId();
        }
        return "";
    }
}
