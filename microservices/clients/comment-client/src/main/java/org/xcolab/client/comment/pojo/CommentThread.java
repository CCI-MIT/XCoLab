package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.commons.time.DurationFormatter;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CommentThread extends AbstractCommentThread implements Serializable {

    public static final TypeProvider<CommentThread> TYPES = new TypeProvider<>(CommentThread.class,
                    new ParameterizedTypeReference<List<CommentThread>>() {});

    public CommentThread() {
    }

    public CommentThread(Long threadId, Long categoryId, Long authorUserId, String title,
            Timestamp createdAt, Timestamp deletedAt, Boolean isQuiet) {
        super(threadId, categoryId, authorUserId, title, createdAt, deletedAt, isQuiet);
    }

    @JsonIgnore
    public int getCommentsCount() {
        return CommentClient.instance().countComments(getId());
    }

    @JsonIgnore
    public List<Comment> getComments() {
        return CommentClient.instance().listComments(0, Integer.MAX_VALUE, getId());
    }

    @JsonIgnore
    public long getLastActivityauthorUserId() {
        return ThreadClient.instance().getLastActivityauthorUserId(getId());
    }

    @JsonIgnore
    public Member getLastActivityAuthor() {
        try {
            return MembersClient.getMember(getLastActivityauthorUserId());
        } catch (MemberNotFoundException e) {
            throw new KeyReferenceException(e);
        }
    }

    @JsonIgnore
    public Date getLastActivityDate() {
        return ThreadClient.instance().getLastActivityDate(getId());
    }

    @JsonIgnore
    public String getLastActivityDateFormatted() {
        return DurationFormatter.forRequestLocale().format(getLastActivityDate());
    }

    @JsonIgnore
    public Member getAuthor() {
        try {
            return MembersClient.getMember(getAuthorUserId());
        } catch (MemberNotFoundException e) {
            throw new KeyReferenceException(e);
        }
    }

    @JsonIgnore
    public Category getCategory() {
        final Long categoryId = getCategoryId();
        if (categoryId != null && categoryId > 0) {
            try {
                return CategoryClient.instance().getCategory(categoryId);
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
                return categoryGroup.getLinkUrl() + "/thread/" + getId();
            }
        }
        //TODO COLAB-2592: handle proposal comments

        return null;
    }
}
