package com.ext.portlet.model.impl;

import com.ext.portlet.model.DiscussionMessage;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing DiscussionMessage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessage
 * @generated
 */
public class DiscussionMessageCacheModel implements CacheModel<DiscussionMessage>,
    Serializable {
    public long pk;
    public long messageId;
    public String subject;
    public String body;
    public long threadId;
    public long categoryId;
    public long categoryGroupId;
    public long authorId;
    public long createDate;
    public long version;
    public long deleted;
    public int responsesCount;
    public long lastActivityDate;
    public long lastActivityAuthorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(29);

        sb.append("{pk=");
        sb.append(pk);
        sb.append(", messageId=");
        sb.append(messageId);
        sb.append(", subject=");
        sb.append(subject);
        sb.append(", body=");
        sb.append(body);
        sb.append(", threadId=");
        sb.append(threadId);
        sb.append(", categoryId=");
        sb.append(categoryId);
        sb.append(", categoryGroupId=");
        sb.append(categoryGroupId);
        sb.append(", authorId=");
        sb.append(authorId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", version=");
        sb.append(version);
        sb.append(", deleted=");
        sb.append(deleted);
        sb.append(", responsesCount=");
        sb.append(responsesCount);
        sb.append(", lastActivityDate=");
        sb.append(lastActivityDate);
        sb.append(", lastActivityAuthorId=");
        sb.append(lastActivityAuthorId);
        sb.append("}");

        return sb.toString();
    }

    public DiscussionMessage toEntityModel() {
        DiscussionMessageImpl discussionMessageImpl = new DiscussionMessageImpl();

        discussionMessageImpl.setPk(pk);
        discussionMessageImpl.setMessageId(messageId);

        if (subject == null) {
            discussionMessageImpl.setSubject(StringPool.BLANK);
        } else {
            discussionMessageImpl.setSubject(subject);
        }

        if (body == null) {
            discussionMessageImpl.setBody(StringPool.BLANK);
        } else {
            discussionMessageImpl.setBody(body);
        }

        discussionMessageImpl.setThreadId(threadId);
        discussionMessageImpl.setCategoryId(categoryId);
        discussionMessageImpl.setCategoryGroupId(categoryGroupId);
        discussionMessageImpl.setAuthorId(authorId);

        if (createDate == Long.MIN_VALUE) {
            discussionMessageImpl.setCreateDate(null);
        } else {
            discussionMessageImpl.setCreateDate(new Date(createDate));
        }

        discussionMessageImpl.setVersion(version);

        if (deleted == Long.MIN_VALUE) {
            discussionMessageImpl.setDeleted(null);
        } else {
            discussionMessageImpl.setDeleted(new Date(deleted));
        }

        discussionMessageImpl.setResponsesCount(responsesCount);

        if (lastActivityDate == Long.MIN_VALUE) {
            discussionMessageImpl.setLastActivityDate(null);
        } else {
            discussionMessageImpl.setLastActivityDate(new Date(lastActivityDate));
        }

        discussionMessageImpl.setLastActivityAuthorId(lastActivityAuthorId);

        discussionMessageImpl.resetOriginalValues();

        return discussionMessageImpl;
    }
}
