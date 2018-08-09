package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.commons.time.DurationFormatter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CommentThread extends AbstractCommentThread {

    public static final TypeProvider<CommentThreadDto> TYPES = new TypeProvider<>(CommentThreadDto.class,
                    new ParameterizedTypeReference<List<CommentThreadDto>>() {});

    private final CommentClient commentClient;
    private final ThreadClient threadClient;
    private final CategoryClient categoryClient;

    public CommentThread() {
        commentClient = CommentClientUtil.getClient();
        threadClient = ThreadClientUtil.getClient();
        categoryClient = CategoryClientUtil.getClient();
    }

    public CommentThread(Long threadId, Long categoryId, Long authorUserId, String title,
            Timestamp createdAt, Timestamp deletedAt, Boolean isQuiet) {
        super(threadId, categoryId, authorUserId, title, createdAt, deletedAt, isQuiet);
        commentClient = CommentClientUtil.getClient();
        threadClient = ThreadClientUtil.getClient();
        categoryClient = CategoryClientUtil.getClient();
    }

    public CommentThread(AbstractCommentThread abstractCommentThread, ServiceNamespace serviceNamespace) {
        super(abstractCommentThread);

        commentClient = CommentClient.fromService(serviceNamespace);
        threadClient =  ThreadClient.fromService(serviceNamespace);
        categoryClient =  CategoryClient.fromService(serviceNamespace);
    }

    @JsonIgnore
    public int getCommentsCount() {
        return commentClient.countComments(getId());
    }

    @JsonIgnore
    public List<Comment> getComments() {
        return commentClient.listComments(0, Integer.MAX_VALUE, getId());
    }

    @JsonIgnore
    public long getLastActivityauthorUserId() {
        return threadClient.getLastActivityauthorUserId(getId());
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
        return threadClient.getLastActivityDate(getId());
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
                return categoryClient.getCategory(categoryId);
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
