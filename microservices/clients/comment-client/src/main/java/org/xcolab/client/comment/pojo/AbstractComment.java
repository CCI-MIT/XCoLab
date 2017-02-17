package org.xcolab.client.comment.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

abstract class AbstractComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long commentId;
    private Long threadId;
    private Long authorId;
    private Timestamp createDate;
    private Timestamp modifiedDate;
    private Timestamp deletedDate;
    private String content;

    AbstractComment() {}

    AbstractComment(Long commentId, Long threadId, Long authorId, Timestamp createDate,
            Timestamp modifiedDate, Timestamp deletedDate, String content) {
        this.commentId = commentId;
        this.threadId = threadId;
        this.authorId = authorId;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.deletedDate = deletedDate;
        this.content = content;
    }

    AbstractComment(AbstractComment comment) {
        this.commentId = comment.commentId;
        this.threadId = comment.threadId;
        this.authorId = comment.authorId;
        this.createDate = comment.createDate;
        this.modifiedDate = comment.modifiedDate;
        this.deletedDate = comment.deletedDate;
        this.content = comment.content;
    }

    public Long getCommentId() {
        return this.commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getThreadId() {
        return this.threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
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

    public Timestamp getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Timestamp getDeletedDate() {
        return this.deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
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
                " (" + commentId +
                ", " + threadId +
                ", " + authorId +
                ", " + createDate +
                ", " + modifiedDate +
                ", " + deletedDate +
                ", " + content +
                ")";
    }
}
