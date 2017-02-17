package org.xcolab.client.comment.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

abstract class AbstractCommentThread implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long threadId;
    private Long categoryId;
    private Long authorId;
    private String title;
    private Timestamp createDate;
    private Timestamp deletedDate;
    private Boolean isQuiet;

    AbstractCommentThread() {
    }

    AbstractCommentThread(Long threadId, Long categoryId, Long authorId, String title,
            Timestamp createDate, Timestamp deletedDate, Boolean isQuiet) {
        this.threadId = threadId;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.title = title;
        this.createDate = createDate;
        this.deletedDate = deletedDate;
        this.isQuiet = isQuiet;
    }

    AbstractCommentThread(AbstractCommentThread commentThread) {
        this.threadId = commentThread.threadId;
        this.categoryId = commentThread.categoryId;
        this.authorId = commentThread.authorId;
        this.title = commentThread.title;
        this.createDate = commentThread.createDate;
        this.deletedDate = commentThread.deletedDate;
        this.isQuiet = commentThread.isQuiet;
    }

    public Long getThreadId() {
        return this.threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getDeletedDate() {
        return this.deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Boolean getIsQuiet() {
        return this.isQuiet;
    }

    public void setIsQuiet(Boolean isQuiet) {
        this.isQuiet = isQuiet;
    }

    @Override
    public String toString() {

        return "Thread (" + threadId +
                ", " + categoryId +
                ", " + authorId +
                ", " + title +
                ", " + createDate +
                ", " + deletedDate +
                ", " + isQuiet +
                ")";
    }
}
