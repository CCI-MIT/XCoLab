package com.ext.portlet.model.impl;

import com.ext.portlet.model.MessagingMessageRecipient;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing MessagingMessageRecipient in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageRecipient
 * @generated
 */
public class MessagingMessageRecipientCacheModel implements CacheModel<MessagingMessageRecipient>,
    Serializable {
    public long recipientId;
    public long messageId;
    public long userId;
    public String emailAddress;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{recipientId=");
        sb.append(recipientId);
        sb.append(", messageId=");
        sb.append(messageId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", emailAddress=");
        sb.append(emailAddress);
        sb.append("}");

        return sb.toString();
    }

    public MessagingMessageRecipient toEntityModel() {
        MessagingMessageRecipientImpl messagingMessageRecipientImpl = new MessagingMessageRecipientImpl();

        messagingMessageRecipientImpl.setRecipientId(recipientId);
        messagingMessageRecipientImpl.setMessageId(messageId);
        messagingMessageRecipientImpl.setUserId(userId);

        if (emailAddress == null) {
            messagingMessageRecipientImpl.setEmailAddress(StringPool.BLANK);
        } else {
            messagingMessageRecipientImpl.setEmailAddress(emailAddress);
        }

        messagingMessageRecipientImpl.resetOriginalValues();

        return messagingMessageRecipientImpl;
    }
}
