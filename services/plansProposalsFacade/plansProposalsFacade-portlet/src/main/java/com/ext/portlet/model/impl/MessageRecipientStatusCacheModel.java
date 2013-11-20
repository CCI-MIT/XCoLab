package com.ext.portlet.model.impl;

import com.ext.portlet.model.MessageRecipientStatus;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MessageRecipientStatus in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientStatus
 * @generated
 */
public class MessageRecipientStatusCacheModel implements CacheModel<MessageRecipientStatus>,
    Externalizable {
    public long messageRecipientId;
    public long messageId;
    public long userId;
    public boolean opened;
    public boolean archived;

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

    @Override
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

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        messageRecipientId = objectInput.readLong();
        messageId = objectInput.readLong();
        userId = objectInput.readLong();
        opened = objectInput.readBoolean();
        archived = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(messageRecipientId);
        objectOutput.writeLong(messageId);
        objectOutput.writeLong(userId);
        objectOutput.writeBoolean(opened);
        objectOutput.writeBoolean(archived);
    }
}
