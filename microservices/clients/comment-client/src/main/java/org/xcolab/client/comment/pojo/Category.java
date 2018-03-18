package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;

import java.sql.Timestamp;
import java.util.List;

public class Category extends AbstractCategory {

    public static final TypeProvider<CategoryDto> TYPES =
            new TypeProvider<>(CategoryDto.class, new ParameterizedTypeReference<List<CategoryDto>>() {});

    private final CategoryClient categoryClient;
    private final ThreadClient threadClient;

    public Category() {
        categoryClient = CategoryClientUtil.getClient();
        threadClient = ThreadClientUtil.getClient();
    }

    public Category(Long categoryId, Long groupId, Long authorId, String name, String description,
            Timestamp createDate, Timestamp deletedDate, Integer sort, Boolean isQuiet) {
        super(categoryId, groupId, authorId, name, description,
                createDate, deletedDate, sort, isQuiet);
        categoryClient = CategoryClientUtil.getClient();
        threadClient = ThreadClientUtil.getClient();
    }

    public Category(AbstractCategory abstractCategory, ServiceNamespace serviceNamespace) {
        super(abstractCategory);
        categoryClient = new CategoryClient(serviceNamespace);
        threadClient = new ThreadClient(serviceNamespace);
    }

    @JsonIgnore
    public List<CommentThread> getThreads(ThreadSortColumn sortColumn, boolean ascending) {
        return threadClient.listThreads(0, Integer.MAX_VALUE,
                getCategoryId(), null, sortColumn, ascending);
    }

    @JsonIgnore
    public CategoryGroup getCategoryGroup() {
        final Long groupId = getGroupId();
        if (groupId != null && groupId > 0) {
            try {
                return categoryClient.getCategoryGroup(groupId);
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
            return categoryGroup.getLinkUrl() + "/category/" + getCategoryId();
        }
        return "";
    }
}
