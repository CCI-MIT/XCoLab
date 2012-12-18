package com.ext.portlet.messaging.model;

import com.ext.portlet.messaging.service.MessagingUserPreferencesLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class MessagingUserPreferencesClp extends BaseModelImpl<MessagingUserPreferences>
    implements MessagingUserPreferences {
    private long _messagingPreferencesId;
    private long _userId;
    private String _userUuid;
    private boolean _emailOnSend;
    private boolean _emailOnReceipt;
    private boolean _emailOnActivity;

    public MessagingUserPreferencesClp() {
    }

    public Class<?> getModelClass() {
        return MessagingUserPreferences.class;
    }

    public String getModelClassName() {
        return MessagingUserPreferences.class.getName();
    }

    public long getPrimaryKey() {
        return _messagingPreferencesId;
    }

    public void setPrimaryKey(long primaryKey) {
        setMessagingPreferencesId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_messagingPreferencesId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getMessagingPreferencesId() {
        return _messagingPreferencesId;
    }

    public void setMessagingPreferencesId(long messagingPreferencesId) {
        _messagingPreferencesId = messagingPreferencesId;
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

    public boolean getEmailOnSend() {
        return _emailOnSend;
    }

    public boolean isEmailOnSend() {
        return _emailOnSend;
    }

    public void setEmailOnSend(boolean emailOnSend) {
        _emailOnSend = emailOnSend;
    }

    public boolean getEmailOnReceipt() {
        return _emailOnReceipt;
    }

    public boolean isEmailOnReceipt() {
        return _emailOnReceipt;
    }

    public void setEmailOnReceipt(boolean emailOnReceipt) {
        _emailOnReceipt = emailOnReceipt;
    }

    public boolean getEmailOnActivity() {
        return _emailOnActivity;
    }

    public boolean isEmailOnActivity() {
        return _emailOnActivity;
    }

    public void setEmailOnActivity(boolean emailOnActivity) {
        _emailOnActivity = emailOnActivity;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingUserPreferencesLocalServiceUtil.addMessagingUserPreferences(this);
        } else {
            MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(this);
        }
    }

    @Override
    public MessagingUserPreferences toEscapedModel() {
        return (MessagingUserPreferences) Proxy.newProxyInstance(MessagingUserPreferences.class.getClassLoader(),
            new Class[] { MessagingUserPreferences.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessagingUserPreferencesClp clone = new MessagingUserPreferencesClp();

        clone.setMessagingPreferencesId(getMessagingPreferencesId());
        clone.setUserId(getUserId());
        clone.setEmailOnSend(getEmailOnSend());
        clone.setEmailOnReceipt(getEmailOnReceipt());
        clone.setEmailOnActivity(getEmailOnActivity());

        return clone;
    }

    public int compareTo(MessagingUserPreferences messagingUserPreferences) {
        long primaryKey = messagingUserPreferences.getPrimaryKey();

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

        MessagingUserPreferencesClp messagingUserPreferences = null;

        try {
            messagingUserPreferences = (MessagingUserPreferencesClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = messagingUserPreferences.getPrimaryKey();

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

        sb.append("{messagingPreferencesId=");
        sb.append(getMessagingPreferencesId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", emailOnSend=");
        sb.append(getEmailOnSend());
        sb.append(", emailOnReceipt=");
        sb.append(getEmailOnReceipt());
        sb.append(", emailOnActivity=");
        sb.append(getEmailOnActivity());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.messaging.model.MessagingUserPreferences");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>messagingPreferencesId</column-name><column-value><![CDATA[");
        sb.append(getMessagingPreferencesId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailOnSend</column-name><column-value><![CDATA[");
        sb.append(getEmailOnSend());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailOnReceipt</column-name><column-value><![CDATA[");
        sb.append(getEmailOnReceipt());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailOnActivity</column-name><column-value><![CDATA[");
        sb.append(getEmailOnActivity());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
