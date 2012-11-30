package com.ext.portlet.messaging.model.impl;

import com.ext.portlet.messaging.model.MessageRecipientStatus;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing MessageRecipientStatus in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientStatus
 * @generated
 */
public class MessageRecipientStatusCacheModel implements CacheModel<MessageRecipientStatus>,
    Serializable {
    public Long messageRecipientId;
    public Long messageId;
    public Long userId;
    public Boolean opened;
    public Boolean archived;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{messageRecipientId=");
        sb.append(messageRecipientId);
        sb.append(", messageId=");
        sb.append(messageId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", opened=");
        sb.append(opened);
        sb.append(", archived=");
        sb.append(archived);
        sb.append("}");

        return sb.toString();
    }

    public MessageRecipientStatus toEntityModel() {
        MessageRecipientStatusImpl messageRecipientStatusImpl = new MessageRecipientStatusImpl();

        messageRecipientStatusImpl.setMessageRecipientId(messageRecipientId);
        messageRecipientStatusImpl.setMessageId(messageId);
        messageRecipientStatusImpl.setUserId(userId);
        messageRecipientStatusImpl.setOpened(opened);
        messageRecipientStatusImpl.setArchived(archived);

        messageRecipientStatusImpl.resetOriginalValues();

        return messageRecipientStatusImpl;
    }
}
