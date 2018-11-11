package org.xcolab.client.comment.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.util.http.client.types.TypeProvider;

import java.sql.Timestamp;
import java.util.List;

public class Comment extends AbstractComment {

    public static final TypeProvider<Comment> TYPES = new TypeProvider<>(Comment.class,
                    new ParameterizedTypeReference<List<Comment>>() {});

    public Comment() {
        super();
    }

    public Comment(Long commentId, Long threadId, Long authorUserId, Timestamp createdAt,
            Timestamp updatedAt, Timestamp deletedAt, String content) {
        super(commentId, threadId, authorUserId, createdAt, updatedAt, deletedAt, content);
    }

    public Comment(Comment comment) {
        super(comment);
    }

    Comment(AbstractComment abstractComment) {
        super(abstractComment);
    }

    public String getContentPlain() {
        return HtmlUtil.cleanAll(getContent());
    }

    public int getSpamReportCount() {
        //TODO COLAB-2591: retrieve spam count
        return 0;
    }

    public Member getAuthor() {
        try {
            return MembersClient.getMember(getAuthorUserId());
        } catch (MemberNotFoundException e) {
            throw new KeyReferenceException(e);
        }
    }

    public CommentThread getThread() {
        final Long threadId = getThreadId();
        if (threadId != null && threadId > 0) {
            try {
                return ThreadClient.instance().getThread(threadId);
            } catch (ThreadNotFoundException e) {
                throw new KeyReferenceException(e);
            }
        }
        return null;
    }

    public String getLinkUrl() {
        return getThread().getLinkUrl() + "#message_" + getId();
    }
}
