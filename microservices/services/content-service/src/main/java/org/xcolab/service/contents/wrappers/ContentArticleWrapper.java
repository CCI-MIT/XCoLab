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


    public ContentArticleWrapper() {
    }

    public ContentArticleWrapper(
            Long contentArticleId, Long authorId, Timestamp createdAt, Long maxVersion,
            Long editRoleGroupId, Long viewRoleGroupId, Boolean visible,
            String title, Long folderId) {
        super(contentArticleId, authorId, createdAt, maxVersion, folderId,
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
        return "ContentArticleWrapper (" + getContentArticleId() +
                ", " + getAuthorId() +
                ", " + getCreatedAt() +
                ", " + getMaxVersionId() +
                ", " + getEditRoleGroupId() +
                ", " + getViewRoleGroupId() +
                ", " + getVisible() +
                ", " + title +
                ", " + getFolderId() +
                ")";
    }
}
