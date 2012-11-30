package com.ext.portlet.messaging.model;

import com.ext.portlet.messaging.service.MessageRecipientStatusLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class MessageRecipientStatusClp extends BaseModelImpl<MessageRecipientStatus>
    implements MessageRecipientStatus {
    private Long _messageRecipientId;
    private Long _messageId;
    private Long _userId;
    private Boolean _opened;
    private Boolean _archived;

    public MessageRecipientStatusClp() {
    }

    public Class<?> getModelClass() {
        return MessageRecipientStatus.class;
    }

    public String getModelClassName() {
        return MessageRecipientStatus.class.getName();
    }

    public Long getPrimaryKey() {
        return _messageRecipientId;
    }

    public void setPrimaryKey(Long primaryKey) {
        setMessageRecipientId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_messageRecipientId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getMessageRecipientId() {
        return _messageRecipientId;
    }

    public void setMessageRecipientId(Long messageRecipientId) {
        _messageRecipientId = messageRecipientId;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Boolean getOpened() {
        return _opened;
    }

    public void setOpened(Boolean opened) {
        _opened = opened;
    }

    public Boolean getArchived() {
        return _archived;
    }

    public void setArchived(Boolean archived) {
        _archived = archived;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            MessageRecipientStatusLocalServiceUtil.addMessageRecipientStatus(this);
        } else {
            MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(this);
        }
    }

    @Override
    public MessageRecipientStatus toEscapedModel() {
        return (MessageRecipientStatus) Proxy.newProxyInstance(MessageRecipientStatus.class.getClassLoader(),
            new Class[] { MessageRecipientStatus.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessageRecipientStatusClp clone = new MessageRecipientStatusClp();

        clone.setMessageRecipientId(getMessageRecipientId());
        clone.setMessageId(getMessageId());
        clone.setUserId(getUserId());
        clone.setOpened(getOpened());
        clone.setArchived(getArchived());

        return clone;
    }

    public int compareTo(MessageRecipientStatus messageRecipientStatus) {
        int value = 0;

        if (getMessageId() < messageRecipientStatus.getMessageId()) {
            value = -1;
        } else if (getMessageId() > messageRecipientStatus.getMessageId()) {
            value = 1;
        } else {
            value = 0;
        }

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

        MessageRecipientStatusClp messageRecipientStatus = null;

        try {
            messageRecipientStatus = (MessageRecipientStatusClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = messageRecipientStatus.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{messageRecipientId=");
        sb.append(getMessageRecipientId());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", opened=");
        sb.append(getOpened());
        sb.append(", archived=");
        sb.append(getArchived());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.messaging.model.MessageRecipientStatus");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>messageRecipientId</column-name><column-value><![CDATA[");
        sb.append(getMessageRecipientId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>opened</column-name><column-value><![CDATA[");
        sb.append(getOpened());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>archived</column-name><column-value><![CDATA[");
        sb.append(getArchived());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
