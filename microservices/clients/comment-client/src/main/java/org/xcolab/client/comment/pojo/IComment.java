package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.tables.pojos.Comment;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.HtmlUtil;

import java.sql.Timestamp;

@JsonDeserialize(as = Comment.class)
public interface IComment {

    Long getId();

    void setId(Long id);

    Long getThreadId();

    void setThreadId(Long threadId);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);

    Timestamp getDeletedAt();

    void setDeletedAt(Timestamp deletedAt);

    String getContent();

    void setContent(String content);

    @JsonIgnore
    default String getContentPlain() {
        return HtmlUtil.cleanAll(getContent());
    }

    @JsonIgnore
    default int getSpamReportCount() {
        //TODO COLAB-2591: retrieve spam count
        return 0;
    }

    @JsonIgnore
    default UserWrapper getAuthor() {
        try {
            return StaticUserContext.getUserClient().getUser(getAuthorUserId());
        } catch (MemberNotFoundException e) {
            throw new KeyReferenceException(e);
        }
    }

    @JsonIgnore
    default IThread getThread() {
        final Long threadId = getThreadId();
        if (threadId != null && threadId > 0) {
            try {
                return StaticCommentContext.getThreadClient().getThread(threadId);
            } catch (ThreadNotFoundException e) {
                throw new KeyReferenceException(e);
            }
        }
        return null;
    }

    @JsonIgnore
    default String getLinkUrl() {
        return getThread().getLinkUrl() + "#message_" + getId();
    }
}
