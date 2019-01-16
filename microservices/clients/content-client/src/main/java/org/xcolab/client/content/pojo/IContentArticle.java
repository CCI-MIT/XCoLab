package org.xcolab.client.content.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.content.pojo.tables.pojos.ContentArticle;
import org.xcolab.client.user.PermissionsClient;
import org.xcolab.client.user.pojo.Member;

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

    Boolean getVisible();

    void setVisible(Boolean visible);

    @JsonIgnore
    default boolean canView(Member member) {
        return getViewRoleGroupId() == null || (member != null && PermissionsClient
                .hasRoleGroup(member.getId(), getViewRoleGroupId()));
    }
}
