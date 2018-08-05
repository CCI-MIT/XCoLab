package org.xcolab.client.comment.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.sql.Timestamp;
import java.util.List;

public class Comment extends AbstractComment {

    public static final TypeProvider<CommentDto> TYPES = new TypeProvider<>(CommentDto.class,
                    new ParameterizedTypeReference<List<CommentDto>>() {});

    private final ThreadClient threadClient;

    public Comment() {
        super();
        threadClient = ThreadClientUtil.getClient();
    }

    public Comment(Long commentId, Long threadId, Long authorId, Timestamp createdAt,
            Timestamp updatedAt, Timestamp deletedAt, String content) {
        super(commentId, threadId, authorId, createdAt, updatedAt, deletedAt, content);
        threadClient = ThreadClientUtil.getClient();
    }

    public Comment(Comment comment) {
        super(comment);
        threadClient = comment.threadClient;
    }

    Comment(AbstractComment abstractComment, ServiceNamespace serviceNamespace) {
        super(abstractComment);
        this.threadClient = new ThreadClient(serviceNamespace);
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
                return threadClient.getThread(threadId);
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
