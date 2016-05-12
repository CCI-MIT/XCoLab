package org.xcolab.client.comment.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommentThread implements Serializable {

    private static final long serialVersionUID = 1553344927;

    private Long      threadid;
    private Long      categoryid;
    private Long      authorid;
    private String    title;
    private Timestamp createdate;
    private Timestamp deleteddate;
    private Boolean   isquiet;

    public CommentThread() {}

    public CommentThread(CommentThread value) {
        this.threadid = value.threadid;
        this.categoryid = value.categoryid;
        this.authorid = value.authorid;
        this.title = value.title;
        this.createdate = value.createdate;
        this.deleteddate = value.deleteddate;
        this.isquiet = value.isquiet;
    }

    public CommentThread(
        Long      threadid,
        Long      categoryid,
        Long      authorid,
        String    title,
        Timestamp createdate,
        Timestamp deleteddate,
        Boolean   isquiet
    ) {
        this.threadid = threadid;
        this.categoryid = categoryid;
        this.authorid = authorid;
        this.title = title;
        this.createdate = createdate;
        this.deleteddate = deleteddate;
        this.isquiet = isquiet;
    }

    public Long getThreadId() {
        return this.threadid;
    }

    public void setThreadId(Long threadid) {
        this.threadid = threadid;
    }

    public Long getCategoryId() {
        return this.categoryid;
    }

    public void setCategoryId(Long categoryid) {
        this.categoryid = categoryid;
    }

    public Long getAuthorId() {
        return this.authorid;
    }

    public void setAuthorId(Long authorid) {
        this.authorid = authorid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Timestamp getDeletedDate() {
        return this.deleteddate;
    }

    public void setDeletedDate(Timestamp deleteddate) {
        this.deleteddate = deleteddate;
    }

    public Boolean getIsQuiet() {
        return this.isquiet;
    }

    public void setIsQuiet(Boolean isquiet) {
        this.isquiet = isquiet;
    }

    @Override
    public String toString() {

        return "Thread (" + threadid +
                ", " + categoryid +
                ", " + authorid +
                ", " + title +
                ", " + createdate +
                ", " + deleteddate +
                ", " + isquiet +
                ")";
    }
}
