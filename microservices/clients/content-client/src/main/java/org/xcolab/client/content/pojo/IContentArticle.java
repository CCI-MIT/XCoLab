package org.xcolab.client.content.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.content.pojo.tables.pojos.ContentArticle;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.sql.Timestamp;

@JsonDeserialize(as = ContentArticle.class)
public interface IContentArticle {

    Long getId();

    void setId(Long id);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Long getMaxVersionId();

    void setMaxVersionId(Long maxVersionId);

    Long getFolderId();

    void setFolderId(Long folderId);

    Long getEditRoleGroupId();

    void setEditRoleGroupId(Long editRoleGroupId);

    Long getViewRoleGroupId();

    void setViewRoleGroupId(Long viewRoleGroupId);

    Boolean isVisible();

    void setVisible(Boolean visible);

    @JsonIgnore
    default boolean canView(UserWrapper member) {
        return getViewRoleGroupId() == null || (member != null && StaticUserContext.getPermissionClient()
                .hasRoleGroup(member.getId(), getViewRoleGroupId()));
    }
}
