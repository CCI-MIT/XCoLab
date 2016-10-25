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
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.time.HumanTime;

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

    public CommentThread(Long threadId, Long categoryId, Long authorId, String title,
            Timestamp createDate, Timestamp deletedDate, Boolean isQuiet) {
        super(threadId, categoryId, authorId, title, createDate, deletedDate, isQuiet);
        commentClient = CommentClientUtil.getClient();
        threadClient = ThreadClientUtil.getClient();
        categoryClient = CategoryClientUtil.getClient();
    }

    public CommentThread(AbstractCommentThread abstractCommentThread, RestService commentService) {
        super(abstractCommentThread);

        commentClient = new CommentClient(commentService);
        threadClient = new ThreadClient(commentService);
        categoryClient = new CategoryClient(commentService);
    }

    @JsonIgnore
    public int getCommentsCount() {
        return CommentClientUtil.countComments(getThreadId());
    }

    @JsonIgnore
    public List<Comment> getComments() {
        return CommentClientUtil.listComments(0, Integer.MAX_VALUE, getThreadId());
    }

    @JsonIgnore
    public long getLastActivityAuthorId() {
        return ThreadClientUtil.getLastActivityAuthorId(getThreadId());
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
        return ThreadClientUtil.getLastActivityDate(getThreadId());
    }

    @JsonIgnore
    public String getLastActivityDateFormatted() {
        return HumanTime.exactly(new Date().getTime() - getLastActivityDate().getTime());
    }

    @JsonIgnore
    public Member getAuthor() {
        try {
            return MembersClient.getMember(getAuthorId());
        } catch (MemberNotFoundException e) {
            throw new KeyReferenceException(e);
        }
    }

    @JsonIgnore
    public Category getCategory() {
        final Long categoryId = getCategoryId();
        if (categoryId != null && categoryId > 0) {
            try {
                return CategoryClientUtil.getCategory(categoryId);
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
                return categoryGroup.getLinkUrl() + "/-/discussion/thread/" + getThreadId();
            }
        }
        //Long propId = CommentClientUtil.getProposalIdForThread(threadId);
        //TODO: handle proposal comments
        return "";
    }
}
