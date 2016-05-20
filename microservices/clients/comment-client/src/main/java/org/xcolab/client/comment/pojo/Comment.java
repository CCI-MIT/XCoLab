package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.HtmlUtil;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Comment implements Serializable {

    private static final long serialVersionUID = 113982360;

    private Long commentid;
    private Long threadid;
    private Long authorid;
    private Timestamp createdate;
    private Timestamp modifieddate;
    private Timestamp deleteddate;
    private String title;
    private String content;

    public Comment() {
    }

    public Comment(Long commentid, Long threadid, Long authorid, Timestamp createdate,
            Timestamp modifieddate, Timestamp deleteddate, String title, String content) {
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

    @JsonIgnore
    public String getContentPlain() {
        return HtmlUtil.cleanAll(content);
    }

    @JsonIgnore
    public int getSpamReportCount() {
        //TODO: implement
        return 0;
    }

    @JsonIgnore
    public Member getAuthor() {
        try {
            return MembersClient.getMember(authorid);
        } catch (MemberNotFoundException e) {
            throw new KeyReferenceException(e);
        }
    }

    @JsonIgnore
    public CommentThread getThread() {
        if (threadid != null && threadid > 0) {
            try {
                return CommentClient.getThread(threadid);
            } catch (ThreadNotFoundException e) {
                throw new KeyReferenceException(e);
            }
        }
        return null;
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
