package com.ext.portlet.discussions.model.impl;

import com.ext.portlet.discussions.model.DiscussionCategory;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing DiscussionCategory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategory
 * @generated
 */
public class DiscussionCategoryCacheModel implements CacheModel<DiscussionCategory>,
    Serializable {
    public Long pk;
    public Long categoryId;
    public Long categoryGroupId;
    public Long authorId;
    public String name;
    public String description;
    public long createDate;
    public long deleted;
    public Integer threadsCount;
    public long lastActivityDate;
    public Long lastActivityAuthorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{pk=");
        sb.append(pk);
        sb.append(", categoryId=");
        sb.append(categoryId);
        sb.append(", categoryGroupId=");
        sb.append(categoryGroupId);
        sb.append(", authorId=");
        sb.append(authorId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", deleted=");
        sb.append(deleted);
        sb.append(", threadsCount=");
        sb.append(threadsCount);
        sb.append(", lastActivityDate=");
        sb.append(lastActivityDate);
        sb.append(", lastActivityAuthorId=");
        sb.append(lastActivityAuthorId);
        sb.append("}");

        return sb.toString();
    }

    public DiscussionCategory toEntityModel() {
        DiscussionCategoryImpl discussionCategoryImpl = new DiscussionCategoryImpl();

        discussionCategoryImpl.setPk(pk);
        discussionCategoryImpl.setCategoryId(categoryId);
        discussionCategoryImpl.setCategoryGroupId(categoryGroupId);
        discussionCategoryImpl.setAuthorId(authorId);

        if (name == null) {
            discussionCategoryImpl.setName(StringPool.BLANK);
        } else {
            discussionCategoryImpl.setName(name);
        }

        if (description == null) {
            discussionCategoryImpl.setDescription(StringPool.BLANK);
        } else {
            discussionCategoryImpl.setDescription(description);
        }

        if (createDate == Long.MIN_VALUE) {
            discussionCategoryImpl.setCreateDate(null);
        } else {
            discussionCategoryImpl.setCreateDate(new Date(createDate));
        }

        if (deleted == Long.MIN_VALUE) {
            discussionCategoryImpl.setDeleted(null);
        } else {
            discussionCategoryImpl.setDeleted(new Date(deleted));
        }

        discussionCategoryImpl.setThreadsCount(threadsCount);

        if (lastActivityDate == Long.MIN_VALUE) {
            discussionCategoryImpl.setLastActivityDate(null);
        } else {
            discussionCategoryImpl.setLastActivityDate(new Date(
                    lastActivityDate));
        }

        discussionCategoryImpl.setLastActivityAuthorId(lastActivityAuthorId);

        discussionCategoryImpl.resetOriginalValues();

        return discussionCategoryImpl;
    }
}
