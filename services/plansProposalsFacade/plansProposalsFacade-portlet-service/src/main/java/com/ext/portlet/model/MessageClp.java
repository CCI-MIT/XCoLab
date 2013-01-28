package com.ext.portlet.model;

import com.ext.portlet.service.MessageLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class MessageClp extends BaseModelImpl<Message> implements Message {
    private long _messageId;
    private long _fromId;
    private long _repliesTo;
    private Date _createDate;
    private String _subject;
    private String _content;

    public MessageClp() {
    }

    public Class<?> getModelClass() {
        return Message.class;
    }

    public String getModelClassName() {
        return Message.class.getName();
    }

    public long getPrimaryKey() {
        return _messageId;
    }

    public void setPrimaryKey(long primaryKey) {
        setMessageId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_messageId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
    }

    public long getFromId() {
        return _fromId;
    }

    public void setFromId(long fromId) {
        _fromId = fromId;
    }

    public long getRepliesTo() {
        return _repliesTo;
    }

    public void setRepliesTo(long repliesTo) {
        _repliesTo = repliesTo;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public String getSubject() {
        return _subject;
    }

    public void setSubject(String subject) {
        _subject = subject;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            MessageLocalServiceUtil.addMessage(this);
        } else {
            MessageLocalServiceUtil.updateMessage(this);
        }
    }

    @Override
    public Message toEscapedModel() {
        return (Message) Proxy.newProxyInstance(Message.class.getClassLoader(),
            new Class[] { Message.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessageClp clone = new MessageClp();

        clone.setMessageId(getMessageId());
        clone.setFromId(getFromId());
        clone.setRepliesTo(getRepliesTo());
        clone.setCreateDate(getCreateDate());
        clone.setSubject(getSubject());
        clone.setContent(getContent());

        return clone;
    }

    public int compareTo(Message message) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(), message.getCreateDate());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MessageClp message = null;

        try {
            message = (MessageClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = message.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{messageId=");
        sb.append(getMessageId());
        sb.append(", fromId=");
        sb.append(getFromId());
        sb.append(", repliesTo=");
        sb.append(getRepliesTo());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", subject=");
        sb.append(getSubject());
        sb.append(", content=");
        sb.append(getContent());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.Message");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fromId</column-name><column-value><![CDATA[");
        sb.append(getFromId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>repliesTo</column-name><column-value><![CDATA[");
        sb.append(getRepliesTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subject</column-name><column-value><![CDATA[");
        sb.append(getSubject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
