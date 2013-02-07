package com.ext.portlet.model;

import com.ext.portlet.service.MessagingMessageRecipientLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class MessagingMessageRecipientClp extends BaseModelImpl<MessagingMessageRecipient>
    implements MessagingMessageRecipient {
    private long _recipientId;
    private long _messageId;
    private long _userId;
    private String _userUuid;
    private String _emailAddress;

    public MessagingMessageRecipientClp() {
    }

    public Class<?> getModelClass() {
        return MessagingMessageRecipient.class;
    }

    public String getModelClassName() {
        return MessagingMessageRecipient.class.getName();
    }

    public long getPrimaryKey() {
        return _recipientId;
    }

    public void setPrimaryKey(long primaryKey) {
        setRecipientId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_recipientId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getRecipientId() {
        return _recipientId;
    }

    public void setRecipientId(long recipientId) {
        _recipientId = recipientId;
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

    public String getEmailAddress() {
        return _emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        _emailAddress = emailAddress;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingMessageRecipientLocalServiceUtil.addMessagingMessageRecipient(this);
        } else {
            MessagingMessageRecipientLocalServiceUtil.updateMessagingMessageRecipient(this);
        }
    }

    @Override
    public MessagingMessageRecipient toEscapedModel() {
        return (MessagingMessageRecipient) Proxy.newProxyInstance(MessagingMessageRecipient.class.getClassLoader(),
            new Class[] { MessagingMessageRecipient.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessagingMessageRecipientClp clone = new MessagingMessageRecipientClp();

        clone.setRecipientId(getRecipientId());
        clone.setMessageId(getMessageId());
        clone.setUserId(getUserId());
        clone.setEmailAddress(getEmailAddress());

        return clone;
    }

    public int compareTo(MessagingMessageRecipient messagingMessageRecipient) {
        long primaryKey = messagingMessageRecipient.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MessagingMessageRecipientClp messagingMessageRecipient = null;

        try {
            messagingMessageRecipient = (MessagingMessageRecipientClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = messagingMessageRecipient.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{recipientId=");
        sb.append(getRecipientId());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", emailAddress=");
        sb.append(getEmailAddress());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MessagingMessageRecipient");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>recipientId</column-name><column-value><![CDATA[");
        sb.append(getRecipientId());
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
            "<column><column-name>emailAddress</column-name><column-value><![CDATA[");
        sb.append(getEmailAddress());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
