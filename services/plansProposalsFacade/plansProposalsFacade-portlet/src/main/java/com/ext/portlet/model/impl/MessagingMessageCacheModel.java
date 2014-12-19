package com.ext.portlet.model.impl;

import com.ext.portlet.model.MessagingMessage;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MessagingMessage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessage
 * @generated
 */
public class MessagingMessageCacheModel implements CacheModel<MessagingMessage>,
    Externalizable {
    public long messageId;
    public String name;
    public String description;
    public String subject;
    public String body;
    public String replyTo;
    public boolean sendToAll;
    public long conversionCount;
    public String redirectURL;
    public long creatorId;
    public long createDate;
    public long modifiedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{messageId=");
        sb.append(messageId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", subject=");
        sb.append(subject);
        sb.append(", body=");
        sb.append(body);
        sb.append(", replyTo=");
        sb.append(replyTo);
        sb.append(", sendToAll=");
        sb.append(sendToAll);
        sb.append(", conversionCount=");
        sb.append(conversionCount);
        sb.append(", redirectURL=");
        sb.append(redirectURL);
        sb.append(", creatorId=");
        sb.append(creatorId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MessagingMessage toEntityModel() {
        MessagingMessageImpl messagingMessageImpl = new MessagingMessageImpl();

        messagingMessageImpl.setMessageId(messageId);

        if (name == null) {
            messagingMessageImpl.setName(StringPool.BLANK);
        } else {
            messagingMessageImpl.setName(name);
        }

        if (description == null) {
            messagingMessageImpl.setDescription(StringPool.BLANK);
        } else {
            messagingMessageImpl.setDescription(description);
        }

        if (subject == null) {
            messagingMessageImpl.setSubject(StringPool.BLANK);
        } else {
            messagingMessageImpl.setSubject(subject);
        }

        if (body == null) {
            messagingMessageImpl.setBody(StringPool.BLANK);
        } else {
            messagingMessageImpl.setBody(body);
        }

        if (replyTo == null) {
            messagingMessageImpl.setReplyTo(StringPool.BLANK);
        } else {
            messagingMessageImpl.setReplyTo(replyTo);
        }

        messagingMessageImpl.setSendToAll(sendToAll);
        messagingMessageImpl.setConversionCount(conversionCount);

        if (redirectURL == null) {
            messagingMessageImpl.setRedirectURL(StringPool.BLANK);
        } else {
            messagingMessageImpl.setRedirectURL(redirectURL);
        }

        messagingMessageImpl.setCreatorId(creatorId);

        if (createDate == Long.MIN_VALUE) {
            messagingMessageImpl.setCreateDate(null);
        } else {
            messagingMessageImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            messagingMessageImpl.setModifiedDate(null);
        } else {
            messagingMessageImpl.setModifiedDate(new Date(modifiedDate));
        }

        messagingMessageImpl.resetOriginalValues();

        return messagingMessageImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        messageId = objectInput.readLong();
        name = objectInput.readUTF();
        description = objectInput.readUTF();
        subject = objectInput.readUTF();
        body = objectInput.readUTF();
        replyTo = objectInput.readUTF();
        sendToAll = objectInput.readBoolean();
        conversionCount = objectInput.readLong();
        redirectURL = objectInput.readUTF();
        creatorId = objectInput.readLong();
        createDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(messageId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        if (subject == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(subject);
        }

        if (body == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(body);
        }

        if (replyTo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(replyTo);
        }

        objectOutput.writeBoolean(sendToAll);
        objectOutput.writeLong(conversionCount);

        if (redirectURL == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(redirectURL);
        }

        objectOutput.writeLong(creatorId);
        objectOutput.writeLong(createDate);
        objectOutput.writeLong(modifiedDate);
    }
}
