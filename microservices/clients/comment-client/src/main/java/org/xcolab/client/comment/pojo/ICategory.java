package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.comment.pojo.tables.pojos.Category;
import org.xcolab.client.comment.util.ThreadSortColumn;

import java.sql.Timestamp;
import java.util.List;

@JsonDeserialize(as = Category.class)
public interface ICategory {

    Long getId();

    void setId(Long id);

    Long getGroupId();

    void setGroupId(Long groupId);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getDeletedAt();

    void setDeletedAt(Timestamp deletedAt);

    Integer getSort();

    void setSort(Integer sort);

    Boolean isIsQuiet();

    void setIsQuiet(Boolean isQuiet);

    @JsonIgnore
    default List<IThread> getThreads(ThreadSortColumn sortColumn, boolean ascending) {
        return StaticCommentContext.getThreadClient().listThreads(0, Integer.MAX_VALUE,
                getId(), null, sortColumn, ascending);
    }

    @JsonIgnore
    default ICategoryGroup getCategoryGroup() {
        final Long groupId = getGroupId();
        if (groupId != null && groupId > 0) {
            try {
                return StaticCommentContext.getCategoryClient().getCategoryGroup(groupId);
            } catch (CategoryGroupNotFoundException e) {
                throw new KeyReferenceException(e);
            }
        }
        return null;
    }

    @JsonIgnore
    default String getLinkUrl() {
        final ICategoryGroup categoryGroup = getCategoryGroup();
        if (categoryGroup != null) {
            return categoryGroup.getLinkUrl() + "/category/" + getId();
        }
        return "";
    }
}
