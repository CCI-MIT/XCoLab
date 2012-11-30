package com.ext.portlet.messaging.model;

import com.ext.portlet.messaging.service.MessageLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class MessageClp extends BaseModelImpl<Message> implements Message {
    private Long _messageId;
    private Long _fromId;
    private Long _repliesTo;
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

    public Long getPrimaryKey() {
        return _messageId;
    }

    public void setPrimaryKey(Long primaryKey) {
        setMessageId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_messageId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public Long getFromId() {
        return _fromId;
    }

    public void setFromId(Long fromId) {
        _fromId = fromId;
    }

    public Long getRepliesTo() {
        return _repliesTo;
    }

    public void setRepliesTo(Long repliesTo) {
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

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> getRecipients() {
        throw new UnsupportedOperationException();
    }

    public boolean hasReciever(long userid) {
        throw new UnsupportedOperationException();
    }

    public boolean isOpened(long userid) {
        throw new UnsupportedOperationException();
    }

    public void setOpened(long userid) {
        throw new UnsupportedOperationException();
    }

    public boolean isArchived(long userid) {
        throw new UnsupportedOperationException();
    }

    public void setArchived(long userid) {
        throw new UnsupportedOperationException();
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

        Long primaryKey = message.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
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
        sb.append("com.ext.portlet.messaging.model.Message");
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
