package com.ext.portlet.model;

import com.ext.portlet.service.MessagingMessageLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class MessagingMessageClp extends BaseModelImpl<MessagingMessage>
    implements MessagingMessage {
    private long _messageId;
    private String _name;
    private String _description;
    private String _subject;
    private String _body;
    private String _replyTo;
    private boolean _sendToAll;
    private long _conversionCount;
    private String _redirectURL;
    private long _creatorId;
    private Date _createDate;
    private Date _modifiedDate;

    public MessagingMessageClp() {
    }

    public Class<?> getModelClass() {
        return MessagingMessage.class;
    }

    public String getModelClassName() {
        return MessagingMessage.class.getName();
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

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getSubject() {
        return _subject;
    }

    public void setSubject(String subject) {
        _subject = subject;
    }

    public String getBody() {
        return _body;
    }

    public void setBody(String body) {
        _body = body;
    }

    public String getReplyTo() {
        return _replyTo;
    }

    public void setReplyTo(String replyTo) {
        _replyTo = replyTo;
    }

    public boolean getSendToAll() {
        return _sendToAll;
    }

    public boolean isSendToAll() {
        return _sendToAll;
    }

    public void setSendToAll(boolean sendToAll) {
        _sendToAll = sendToAll;
    }

    public long getConversionCount() {
        return _conversionCount;
    }

    public void setConversionCount(long conversionCount) {
        _conversionCount = conversionCount;
    }

    public String getRedirectURL() {
        return _redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        _redirectURL = redirectURL;
    }

    public long getCreatorId() {
        return _creatorId;
    }

    public void setCreatorId(long creatorId) {
        _creatorId = creatorId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingMessageLocalServiceUtil.addMessagingMessage(this);
        } else {
            MessagingMessageLocalServiceUtil.updateMessagingMessage(this);
        }
    }

    @Override
    public MessagingMessage toEscapedModel() {
        return (MessagingMessage) Proxy.newProxyInstance(MessagingMessage.class.getClassLoader(),
            new Class[] { MessagingMessage.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessagingMessageClp clone = new MessagingMessageClp();

        clone.setMessageId(getMessageId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setSubject(getSubject());
        clone.setBody(getBody());
        clone.setReplyTo(getReplyTo());
        clone.setSendToAll(getSendToAll());
        clone.setConversionCount(getConversionCount());
        clone.setRedirectURL(getRedirectURL());
        clone.setCreatorId(getCreatorId());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    public int compareTo(MessagingMessage messagingMessage) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(),
                messagingMessage.getCreateDate());

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

        MessagingMessageClp messagingMessage = null;

        try {
            messagingMessage = (MessagingMessageClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = messagingMessage.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{messageId=");
        sb.append(getMessageId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", subject=");
        sb.append(getSubject());
        sb.append(", body=");
        sb.append(getBody());
        sb.append(", replyTo=");
        sb.append(getReplyTo());
        sb.append(", sendToAll=");
        sb.append(getSendToAll());
        sb.append(", conversionCount=");
        sb.append(getConversionCount());
        sb.append(", redirectURL=");
        sb.append(getRedirectURL());
        sb.append(", creatorId=");
        sb.append(getCreatorId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MessagingMessage");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subject</column-name><column-value><![CDATA[");
        sb.append(getSubject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>body</column-name><column-value><![CDATA[");
        sb.append(getBody());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>replyTo</column-name><column-value><![CDATA[");
        sb.append(getReplyTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sendToAll</column-name><column-value><![CDATA[");
        sb.append(getSendToAll());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>conversionCount</column-name><column-value><![CDATA[");
        sb.append(getConversionCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>redirectURL</column-name><column-value><![CDATA[");
        sb.append(getRedirectURL());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>creatorId</column-name><column-value><![CDATA[");
        sb.append(getCreatorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
