package org.xcolab.service.content.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.model.tables.pojos.ContentArticleImpl;

import java.sql.Timestamp;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentArticleWrapper extends ContentArticleImpl {

    private static final long serialVersionUID = -2076774009;

    private String title;

    public ContentArticleWrapper() {
    }

    public ContentArticleWrapper(
            Long contentArticleId, Long authorUserId, Timestamp createdAt, Long maxVersion,
            Long editRoleGroupId, Long viewRoleGroupId, Boolean visible,
            String title, Long folderId) {
        super(contentArticleId, authorUserId, createdAt, maxVersion, folderId,
                editRoleGroupId, viewRoleGroupId, visible);
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ContentArticleWrapper (" + getId() +
                ", " + getAuthorUserId() +
                ", " + getCreatedAt() +
                ", " + getMaxVersionId() +
                ", " + getEditRoleGroupId() +
                ", " + getViewRoleGroupId() +
                ", " + isVisible() +
                ", " + title +
                ", " + getFolderId() +
                ")";
    }
}
