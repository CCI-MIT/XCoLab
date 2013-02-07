package com.ext.portlet.model;

import com.ext.portlet.service.MessagingRedirectLinkLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class MessagingRedirectLinkClp extends BaseModelImpl<MessagingRedirectLink>
    implements MessagingRedirectLink {
    private long _redirectId;
    private String _link;
    private long _messageId;
    private Date _createDate;

    public MessagingRedirectLinkClp() {
    }

    public Class<?> getModelClass() {
        return MessagingRedirectLink.class;
    }

    public String getModelClassName() {
        return MessagingRedirectLink.class.getName();
    }

    public long getPrimaryKey() {
        return _redirectId;
    }

    public void setPrimaryKey(long primaryKey) {
        setRedirectId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_redirectId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getRedirectId() {
        return _redirectId;
    }

    public void setRedirectId(long redirectId) {
        _redirectId = redirectId;
    }

    public String getLink() {
        return _link;
    }

    public void setLink(String link) {
        _link = link;
    }

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingRedirectLinkLocalServiceUtil.addMessagingRedirectLink(this);
        } else {
            MessagingRedirectLinkLocalServiceUtil.updateMessagingRedirectLink(this);
        }
    }

    @Override
    public MessagingRedirectLink toEscapedModel() {
        return (MessagingRedirectLink) Proxy.newProxyInstance(MessagingRedirectLink.class.getClassLoader(),
            new Class[] { MessagingRedirectLink.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessagingRedirectLinkClp clone = new MessagingRedirectLinkClp();

        clone.setRedirectId(getRedirectId());
        clone.setLink(getLink());
        clone.setMessageId(getMessageId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    public int compareTo(MessagingRedirectLink messagingRedirectLink) {
        long primaryKey = messagingRedirectLink.getPrimaryKey();

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

        MessagingRedirectLinkClp messagingRedirectLink = null;

        try {
            messagingRedirectLink = (MessagingRedirectLinkClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = messagingRedirectLink.getPrimaryKey();

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

        sb.append("{redirectId=");
        sb.append(getRedirectId());
        sb.append(", link=");
        sb.append(getLink());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MessagingRedirectLink");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>redirectId</column-name><column-value><![CDATA[");
        sb.append(getRedirectId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>link</column-name><column-value><![CDATA[");
        sb.append(getLink());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
