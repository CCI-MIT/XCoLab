package com.ext.portlet.model.impl;

import com.ext.portlet.model.Message;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Message in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Message
 * @generated
 */
public class MessageCacheModel implements CacheModel<Message>, Externalizable {
    public long messageId;
    public long fromId;
    public long repliesTo;
    public long createDate;
    public String subject;
    public String content;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{messageId=");
        sb.append(messageId);
        sb.append(", fromId=");
        sb.append(fromId);
        sb.append(", repliesTo=");
        sb.append(repliesTo);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", subject=");
        sb.append(subject);
        sb.append(", content=");
        sb.append(content);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Message toEntityModel() {
        MessageImpl messageImpl = new MessageImpl();

        messageImpl.setMessageId(messageId);
        messageImpl.setFromId(fromId);
        messageImpl.setRepliesTo(repliesTo);

        if (createDate == Long.MIN_VALUE) {
            messageImpl.setCreateDate(null);
        } else {
            messageImpl.setCreateDate(new Date(createDate));
        }

        if (subject == null) {
            messageImpl.setSubject(StringPool.BLANK);
        } else {
            messageImpl.setSubject(subject);
        }

        if (content == null) {
            messageImpl.setContent(StringPool.BLANK);
        } else {
            messageImpl.setContent(content);
        }

        messageImpl.resetOriginalValues();

        return messageImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        messageId = objectInput.readLong();
        fromId = objectInput.readLong();
        repliesTo = objectInput.readLong();
        createDate = objectInput.readLong();
        subject = objectInput.readUTF();
        content = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(messageId);
        objectOutput.writeLong(fromId);
        objectOutput.writeLong(repliesTo);
        objectOutput.writeLong(createDate);

        if (subject == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(subject);
        }

        if (content == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(content);
        }
    }
}
