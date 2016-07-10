package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.time.HumanTime;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CommentThread implements Serializable {
    public static final TypeProvider<CommentThread> TYPES =
            new TypeProvider<>(CommentThread.class,
                    new ParameterizedTypeReference<List<CommentThread>>() {
                    });

    private static final long serialVersionUID = 1553344927;

    private Long threadid;
    private Long categoryid;
    private Long authorid;
    private String title;
    private Timestamp createdate;
    private Timestamp deleteddate;
    private Boolean isquiet;

    public CommentThread() {
    }

    public CommentThread(Long threadid, Long categoryid, Long authorid, String title,
            Timestamp createdate, Timestamp deleteddate, Boolean isquiet) {
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

    @JsonIgnore
    public int getCommentsCount() {
        return CommentClient.countComments(threadid);
    }

    @JsonIgnore
    public List<Comment> getComments() {
        return CommentClient.listComments(0, Integer.MAX_VALUE, threadid);
    }

    @JsonIgnore
    public long getLastActivityAuthorId() {
        return CommentClient.getLastActivityAuthorId(threadid);
    }

    @JsonIgnore
    public Member getLastActivityAuthor() {
        try {
            return MembersClient.getMember(getLastActivityAuthorId());
        } catch (MemberNotFoundException e) {
            throw new KeyReferenceException(e);
        }
    }

    @JsonIgnore
    public Date getLastActivityDate() {
        return CommentClient.getLastActivityDate(threadid);
    }

    @JsonIgnore
    public String getLastActivityDateFormatted() {
        return HumanTime.exactly(new Date().getTime() - getLastActivityDate().getTime());
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
    public Category getCategory() {
        if (categoryid != null && categoryid > 0) {
            try {
                return CommentClient.getCategory(categoryid);
            } catch (CategoryNotFoundException ignored) {
                //throw new KeyReferenceException(e);
            }
        }
        return null;
    }

    @JsonIgnore
    public String getLinkUrl() {
        final Category category = getCategory();
        if(category!=null) {
            final CategoryGroup categoryGroup = category.getCategoryGroup();
            if (categoryGroup != null) {
                return categoryGroup.getLinkUrl() + "/-/discussion/thread/" + threadid;
            }
        }
        //Long propId = CommentClient.getProposalIdForThread(threadid);
        //TODO: handle proposal comments
        return "";
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
