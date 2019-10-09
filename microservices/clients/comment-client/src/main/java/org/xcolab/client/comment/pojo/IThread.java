package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.client.comment.pojo.tables.pojos.Thread;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.time.DurationFormatter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@JsonDeserialize(as = Thread.class)
public interface IThread {

    Long getId();

    void setId(Long id);

    Long getCategoryId();

    void setCategoryId(Long categoryId);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);

    String getTitle();

    void setTitle(String title);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getDeletedAt();

    void setDeletedAt(Timestamp deletedAt);

    Boolean isIsQuiet();

    void setIsQuiet(Boolean isQuiet);

    @JsonIgnore
    default int getCommentsCount() {
        return StaticCommentContext.getCommentClient().countComments(getId());
    }

    @JsonIgnore
    default List<IComment> getComments() {
        return StaticCommentContext.getCommentClient().listComments(0, Integer.MAX_VALUE, getId());
    }

    @JsonIgnore
    default long getLastActivityAuthorUserId() {
        return StaticCommentContext.getThreadClient().getLastActivityAuthorUserId(getId());
    }

    @JsonIgnore
    default UserWrapper getLastActivityAuthor() {
        try {
            return StaticUserContext.getUserClient().getUser(getLastActivityAuthorUserId());
        } catch (MemberNotFoundException e) {
            throw new KeyReferenceException(e);
        }
    }

    @JsonIgnore
    default Date getLastActivityDate() {
        return StaticCommentContext.getThreadClient().getLastActivityDate(getId());
    }

    @JsonIgnore
    default String getLastActivityDateFormatted() {
        return DurationFormatter.forRequestLocale().format(getLastActivityDate());
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
    default ICategory getCategory() {
        final Long categoryId = getCategoryId();
        if (categoryId != null && categoryId > 0) {
            try {
                return StaticCommentContext.getCategoryClient().getCategory(categoryId);
            } catch (CategoryNotFoundException ignored) {
                //throw new KeyReferenceException(e);
            }
        }
        return null;
    }

    @JsonIgnore
    default String getLinkUrl() {
        final ICategory category = getCategory();
        if (category != null) {
            final ICategoryGroup categoryGroup = category.getCategoryGroup();
            if (categoryGroup != null) {
                return categoryGroup.getLinkUrl() + "/thread/" + getId();
            }
        }
        //TODO COLAB-2592: handle proposal comments
        return null;
    }
}
