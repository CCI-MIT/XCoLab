package com.ext.portlet.model;

import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class MessageRecipientStatusClp extends BaseModelImpl<MessageRecipientStatus>
    implements MessageRecipientStatus {
    private long _messageRecipientId;
    private long _messageId;
    private long _userId;
    private String _userUuid;
    private boolean _opened;
    private boolean _archived;

    public MessageRecipientStatusClp() {
    }

    public Class<?> getModelClass() {
        return MessageRecipientStatus.class;
    }

    public String getModelClassName() {
        return MessageRecipientStatus.class.getName();
    }

    public long getPrimaryKey() {
        return _messageRecipientId;
    }

    public void setPrimaryKey(long primaryKey) {
        setMessageRecipientId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_messageRecipientId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getMessageRecipientId() {
        return _messageRecipientId;
    }

    public void setMessageRecipientId(long messageRecipientId) {
        _messageRecipientId = messageRecipientId;
    }

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    public boolean getOpened() {
        return _opened;
    }

    public boolean isOpened() {
        return _opened;
    }

    public void setOpened(boolean opened) {
        _opened = opened;
    }

    public boolean getArchived() {
        return _archived;
    }

    public boolean isArchived() {
        return _archived;
    }

    public void setArchived(boolean archived) {
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

        long primaryKey = messageRecipientStatus.getPrimaryKey();

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
        sb.append("com.ext.portlet.model.MessageRecipientStatus");
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
