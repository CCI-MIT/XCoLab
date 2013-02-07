package com.ext.portlet.model.impl;

import com.ext.portlet.model.MessagingIgnoredRecipients;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing MessagingIgnoredRecipients in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingIgnoredRecipients
 * @generated
 */
public class MessagingIgnoredRecipientsCacheModel implements CacheModel<MessagingIgnoredRecipients>,
    Serializable {
    public long ignoredRecipientId;
    public String email;
    public String name;
    public long userId;
    public long createDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{ignoredRecipientId=");
        sb.append(ignoredRecipientId);
        sb.append(", email=");
        sb.append(email);
        sb.append(", name=");
        sb.append(name);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append("}");

        return sb.toString();
    }

    public MessagingIgnoredRecipients toEntityModel() {
        MessagingIgnoredRecipientsImpl messagingIgnoredRecipientsImpl = new MessagingIgnoredRecipientsImpl();

        messagingIgnoredRecipientsImpl.setIgnoredRecipientId(ignoredRecipientId);

        if (email == null) {
            messagingIgnoredRecipientsImpl.setEmail(StringPool.BLANK);
        } else {
            messagingIgnoredRecipientsImpl.setEmail(email);
        }

        if (name == null) {
            messagingIgnoredRecipientsImpl.setName(StringPool.BLANK);
        } else {
            messagingIgnoredRecipientsImpl.setName(name);
        }

        messagingIgnoredRecipientsImpl.setUserId(userId);

        if (createDate == Long.MIN_VALUE) {
            messagingIgnoredRecipientsImpl.setCreateDate(null);
        } else {
            messagingIgnoredRecipientsImpl.setCreateDate(new Date(createDate));
        }

        messagingIgnoredRecipientsImpl.resetOriginalValues();

        return messagingIgnoredRecipientsImpl;
    }
}
