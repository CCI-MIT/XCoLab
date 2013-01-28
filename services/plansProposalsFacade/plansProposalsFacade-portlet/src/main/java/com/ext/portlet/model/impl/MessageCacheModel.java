package com.ext.portlet.model.impl;

import com.ext.portlet.model.Message;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Message in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Message
 * @generated
 */
public class MessageCacheModel implements CacheModel<Message>, Serializable {
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
}
