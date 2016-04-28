package org.xcolab.client.contents.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentArticle implements Serializable {

    private static final long serialVersionUID = -2076774009;

    private Long contentArticleId;
    private Long authorId;
    private Timestamp createDate;
    private Long maxVersion;
    private Long editRoleGroupId;
    private Long viewRoleGroupId;
    private Boolean visible;

    public ContentArticle() {
    }

    public ContentArticle(Long contentArticleId, Long authorId, Timestamp createDate,
            Long maxVersion, Long editRoleGroupId, Long viewRoleGroupId, Boolean visible) {
        this.contentArticleId = contentArticleId;
        this.authorId = authorId;
        this.createDate = createDate;
        this.maxVersion = maxVersion;
        this.editRoleGroupId = editRoleGroupId;
        this.viewRoleGroupId = viewRoleGroupId;
        this.visible = visible;
    }

    public Long getContentArticleId() {
        return this.contentArticleId;
    }

    public void setContentArticleId(Long contentArticleId) {
        this.contentArticleId = contentArticleId;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Long getMaxVersion() {
        return this.maxVersion;
    }

    public void setMaxVersion(Long maxVersion) {
        this.maxVersion = maxVersion;
    }

    public Long getEditRoleGroupId() {
        return this.editRoleGroupId;
    }

    public void setEditRoleGroupId(Long editRoleGroupId) {
        this.editRoleGroupId = editRoleGroupId;
    }

    public Long getViewRoleGroupId() {
        return this.viewRoleGroupId;
    }

    public void setViewRoleGroupId(Long viewRoleGroupId) {
        this.viewRoleGroupId = viewRoleGroupId;
    }

    public Boolean getVisible() {
        return this.visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "ContentArticle (" + contentArticleId +
                ", " + authorId +
                ", " + createDate +
                ", " + maxVersion +
                ", " + editRoleGroupId +
                ", " + viewRoleGroupId +
                ", " + visible +
                ")";
    }
}
