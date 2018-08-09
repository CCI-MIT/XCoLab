package org.xcolab.client.comment.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

abstract class AbstractCommentThread implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long categoryId;
    private Long authorUserId;
    private String title;
    private Timestamp createdAt;
    private Timestamp deletedAt;
    private Boolean isQuiet;

    AbstractCommentThread() {
    }

    AbstractCommentThread(Long id, Long categoryId, Long authorUserId, String title,
            Timestamp createdAt, Timestamp deletedAt, Boolean isQuiet) {
        this.id = id;
        this.categoryId = categoryId;
        this.authorUserId = authorUserId;
        this.title = title;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.isQuiet = isQuiet;
    }

    AbstractCommentThread(AbstractCommentThread commentThread) {
        this.id = commentThread.id;
        this.categoryId = commentThread.categoryId;
        this.authorUserId = commentThread.authorUserId;
        this.title = commentThread.title;
        this.createdAt = commentThread.createdAt;
        this.deletedAt = commentThread.deletedAt;
        this.isQuiet = commentThread.isQuiet;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getAuthorUserId() {
        return this.authorUserId;
    }

    public void setAuthorUserId(Long authorUserId) {
        this.authorUserId = authorUserId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean getIsQuiet() {
        return this.isQuiet;
    }

    public void setIsQuiet(Boolean isQuiet) {
        this.isQuiet = isQuiet;
    }

    @Override
    public String toString() {

        return "Thread (" + id +
                ", " + categoryId +
                ", " + authorUserId +
                ", " + title +
                ", " + createdAt +
                ", " + deletedAt +
                ", " + isQuiet +
                ")";
    }
}
