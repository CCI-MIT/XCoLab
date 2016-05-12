package org.xcolab.client.comment.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {

    private static final long serialVersionUID = 113982360;

    private Long      commentid;
    private Long      threadid;
    private Long      authorid;
    private Timestamp createdate;
    private Timestamp modifieddate;
    private Timestamp deleteddate;
    private String    title;
    private String    content;

    public Comment() {}

    public Comment(Comment value) {
        this.commentid = value.commentid;
        this.threadid = value.threadid;
        this.authorid = value.authorid;
        this.createdate = value.createdate;
        this.modifieddate = value.modifieddate;
        this.deleteddate = value.deleteddate;
        this.title = value.title;
        this.content = value.content;
    }

    public Comment(
        Long      commentid,
        Long      threadid,
        Long      authorid,
        Timestamp createdate,
        Timestamp modifieddate,
        Timestamp deleteddate,
        String    title,
        String    content
    ) {
        this.commentid = commentid;
        this.threadid = threadid;
        this.authorid = authorid;
        this.createdate = createdate;
        this.modifieddate = modifieddate;
        this.deleteddate = deleteddate;
        this.title = title;
        this.content = content;
    }

    public Long getCommentId() {
        return this.commentid;
    }

    public void setCommentId(Long commentid) {
        this.commentid = commentid;
    }

    public Long getThreadId() {
        return this.threadid;
    }

    public void setThreadId(Long threadid) {
        this.threadid = threadid;
    }

    public Long getAuthorId() {
        return this.authorid;
    }

    public void setAuthorId(Long authorid) {
        this.authorid = authorid;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Timestamp getModifiedDate() {
        return this.modifieddate;
    }

    public void setModifiedDate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Timestamp getDeletedDate() {
        return this.deleteddate;
    }

    public void setDeletedDate(Timestamp deleteddate) {
        this.deleteddate = deleteddate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {

        return "Comment (" + commentid +
                ", " + threadid +
                ", " + authorid +
                ", " + createdate +
                ", " + modifieddate +
                ", " + deleteddate +
                ", " + title +
                ", " + content +
                ")";
    }
}
