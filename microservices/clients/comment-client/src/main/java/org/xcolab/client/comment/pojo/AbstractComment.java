package org.xcolab.client.comment.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

abstract class AbstractComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long threadId;
    private Long authorUserId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private String content;

    AbstractComment() {}

    AbstractComment(Long id, Long threadId, Long authorUserId, Timestamp createdAt,
            Timestamp updatedAt, Timestamp deletedAt, String content) {
        this.id = id;
        this.threadId = threadId;
        this.authorUserId = authorUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.content = content;
    }

    AbstractComment(AbstractComment comment) {
        this.id = comment.id;
        this.threadId = comment.threadId;
        this.authorUserId = comment.authorUserId;
        this.createdAt = comment.createdAt;
        this.updatedAt = comment.updatedAt;
        this.deletedAt = comment.deletedAt;
        this.content = comment.content;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getThreadId() {
        return this.threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public Long getAuthorUserId() {
        return this.authorUserId;
    }

    public void setAuthorUserId(Long authorUserId) {
        this.authorUserId = authorUserId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " (" + id +
                ", " + threadId +
                ", " + authorUserId +
                ", " + createdAt +
                ", " + updatedAt +
                ", " + deletedAt +
                ", " + content +
                ")";
    }
}
