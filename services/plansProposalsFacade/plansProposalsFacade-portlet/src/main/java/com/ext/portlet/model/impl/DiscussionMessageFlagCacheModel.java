package com.ext.portlet.model.impl;

import com.ext.portlet.model.DiscussionMessageFlag;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing DiscussionMessageFlag in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlag
 * @generated
 */
public class DiscussionMessageFlagCacheModel implements CacheModel<DiscussionMessageFlag>,
    Serializable {
    public long pk;
    public long messageId;
    public String flagType;
    public String data;
    public long created;
    public long userId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{pk=");
        sb.append(pk);
        sb.append(", messageId=");
        sb.append(messageId);
        sb.append(", flagType=");
        sb.append(flagType);
        sb.append(", data=");
        sb.append(data);
        sb.append(", created=");
        sb.append(created);
        sb.append(", userId=");
        sb.append(userId);
        sb.append("}");

        return sb.toString();
    }

    public DiscussionMessageFlag toEntityModel() {
        DiscussionMessageFlagImpl discussionMessageFlagImpl = new DiscussionMessageFlagImpl();

        discussionMessageFlagImpl.setPk(pk);
        discussionMessageFlagImpl.setMessageId(messageId);

        if (flagType == null) {
            discussionMessageFlagImpl.setFlagType(StringPool.BLANK);
        } else {
            discussionMessageFlagImpl.setFlagType(flagType);
        }

        if (data == null) {
            discussionMessageFlagImpl.setData(StringPool.BLANK);
        } else {
            discussionMessageFlagImpl.setData(data);
        }

        if (created == Long.MIN_VALUE) {
            discussionMessageFlagImpl.setCreated(null);
        } else {
            discussionMessageFlagImpl.setCreated(new Date(created));
        }

        discussionMessageFlagImpl.setUserId(userId);

        discussionMessageFlagImpl.resetOriginalValues();

        return discussionMessageFlagImpl;
    }
}
