package org.xcolab.service.contents.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.model.tables.pojos.ContentArticle;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContentArticleWrapper extends ContentArticle {

    private static final long serialVersionUID = -2076774009;

    private String title;
    private Long folderId;

    public ContentArticleWrapper() {
    }

    public ContentArticleWrapper(
            Long contentArticleId, Long authorId, Timestamp createDate, Long maxVersion,
            Long editRoleGroupId, Long viewRoleGroupId, Boolean visible,
            String title, Long folderId) {
        super(contentArticleId, authorId, createDate, maxVersion,
                editRoleGroupId, viewRoleGroupId, visible);
        this.title = title;
        this.folderId = folderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getFolderId() {
        return folderId;
    }

    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }

    @Override
    public String toString() {
        return "ContentArticleWrapper (" + getContentArticleId() +
                ", " + getAuthorId() +
                ", " + getCreateDate() +
                ", " + getMaxVersion() +
                ", " + getEditRoleGroupId() +
                ", " + getViewRoleGroupId() +
                ", " + getVisible() +
                ", " + title +
                ", " + folderId +
                ")";
    }
}
