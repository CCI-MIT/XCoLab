package com.ext.portlet.model;

import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class MessagingIgnoredRecipientsClp extends BaseModelImpl<MessagingIgnoredRecipients>
    implements MessagingIgnoredRecipients {
    private long _ignoredRecipientId;
    private String _email;
    private String _name;
    private long _userId;
    private String _userUuid;
    private Date _createDate;

    public MessagingIgnoredRecipientsClp() {
    }

    public Class<?> getModelClass() {
        return MessagingIgnoredRecipients.class;
    }

    public String getModelClassName() {
        return MessagingIgnoredRecipients.class.getName();
    }

    public long getPrimaryKey() {
        return _ignoredRecipientId;
    }

    public void setPrimaryKey(long primaryKey) {
        setIgnoredRecipientId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_ignoredRecipientId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getIgnoredRecipientId() {
        return _ignoredRecipientId;
    }

    public void setIgnoredRecipientId(long ignoredRecipientId) {
        _ignoredRecipientId = ignoredRecipientId;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
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

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingIgnoredRecipientsLocalServiceUtil.addMessagingIgnoredRecipients(this);
        } else {
            MessagingIgnoredRecipientsLocalServiceUtil.updateMessagingIgnoredRecipients(this);
        }
    }

    @Override
    public MessagingIgnoredRecipients toEscapedModel() {
        return (MessagingIgnoredRecipients) Proxy.newProxyInstance(MessagingIgnoredRecipients.class.getClassLoader(),
            new Class[] { MessagingIgnoredRecipients.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessagingIgnoredRecipientsClp clone = new MessagingIgnoredRecipientsClp();

        clone.setIgnoredRecipientId(getIgnoredRecipientId());
        clone.setEmail(getEmail());
        clone.setName(getName());
        clone.setUserId(getUserId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    public int compareTo(MessagingIgnoredRecipients messagingIgnoredRecipients) {
        int value = 0;

        value = getEmail().compareTo(messagingIgnoredRecipients.getEmail());

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

        MessagingIgnoredRecipientsClp messagingIgnoredRecipients = null;

        try {
            messagingIgnoredRecipients = (MessagingIgnoredRecipientsClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = messagingIgnoredRecipients.getPrimaryKey();

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

        sb.append("{ignoredRecipientId=");
        sb.append(getIgnoredRecipientId());
        sb.append(", email=");
        sb.append(getEmail());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MessagingIgnoredRecipients");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ignoredRecipientId</column-name><column-value><![CDATA[");
        sb.append(getIgnoredRecipientId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>email</column-name><column-value><![CDATA[");
        sb.append(getEmail());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
