package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class MessagingUserPreferencesClp extends BaseModelImpl<MessagingUserPreferences>
    implements MessagingUserPreferences {
    private long _messagingPreferencesId;
    private long _userId;
    private String _userUuid;
    private boolean _emailOnSend;
    private boolean _emailOnReceipt;
    private boolean _emailOnActivity;
    private boolean _emailActivityDailyDigest;
    private BaseModel<?> _messagingUserPreferencesRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public MessagingUserPreferencesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingUserPreferences.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingUserPreferences.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _messagingPreferencesId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setMessagingPreferencesId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _messagingPreferencesId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("messagingPreferencesId", getMessagingPreferencesId());
        attributes.put("userId", getUserId());
        attributes.put("emailOnSend", getEmailOnSend());
        attributes.put("emailOnReceipt", getEmailOnReceipt());
        attributes.put("emailOnActivity", getEmailOnActivity());
        attributes.put("emailActivityDailyDigest", getEmailActivityDailyDigest());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long messagingPreferencesId = (Long) attributes.get(
                "messagingPreferencesId");

        if (messagingPreferencesId != null) {
            setMessagingPreferencesId(messagingPreferencesId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Boolean emailOnSend = (Boolean) attributes.get("emailOnSend");

        if (emailOnSend != null) {
            setEmailOnSend(emailOnSend);
        }

        Boolean emailOnReceipt = (Boolean) attributes.get("emailOnReceipt");

        if (emailOnReceipt != null) {
            setEmailOnReceipt(emailOnReceipt);
        }

        Boolean emailOnActivity = (Boolean) attributes.get("emailOnActivity");

        if (emailOnActivity != null) {
            setEmailOnActivity(emailOnActivity);
        }

        Boolean emailActivityDailyDigest = (Boolean) attributes.get(
                "emailActivityDailyDigest");

        if (emailActivityDailyDigest != null) {
            setEmailActivityDailyDigest(emailActivityDailyDigest);
        }
    }

    @Override
    public long getMessagingPreferencesId() {
        return _messagingPreferencesId;
    }

    @Override
    public void setMessagingPreferencesId(long messagingPreferencesId) {
        _messagingPreferencesId = messagingPreferencesId;

        if (_messagingUserPreferencesRemoteModel != null) {
            try {
                Class<?> clazz = _messagingUserPreferencesRemoteModel.getClass();

                Method method = clazz.getMethod("setMessagingPreferencesId",
                        long.class);

                method.invoke(_messagingUserPreferencesRemoteModel,
                    messagingPreferencesId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_messagingUserPreferencesRemoteModel != null) {
            try {
                Class<?> clazz = _messagingUserPreferencesRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_messagingUserPreferencesRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    @Override
    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    @Override
    public boolean getEmailOnSend() {
        return _emailOnSend;
    }

    @Override
    public boolean isEmailOnSend() {
        return _emailOnSend;
    }

    @Override
    public void setEmailOnSend(boolean emailOnSend) {
        _emailOnSend = emailOnSend;

        if (_messagingUserPreferencesRemoteModel != null) {
            try {
                Class<?> clazz = _messagingUserPreferencesRemoteModel.getClass();

                Method method = clazz.getMethod("setEmailOnSend", boolean.class);

                method.invoke(_messagingUserPreferencesRemoteModel, emailOnSend);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getEmailOnReceipt() {
        return _emailOnReceipt;
    }

    @Override
    public boolean isEmailOnReceipt() {
        return _emailOnReceipt;
    }

    @Override
    public void setEmailOnReceipt(boolean emailOnReceipt) {
        _emailOnReceipt = emailOnReceipt;

        if (_messagingUserPreferencesRemoteModel != null) {
            try {
                Class<?> clazz = _messagingUserPreferencesRemoteModel.getClass();

                Method method = clazz.getMethod("setEmailOnReceipt",
                        boolean.class);

                method.invoke(_messagingUserPreferencesRemoteModel,
                    emailOnReceipt);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getEmailOnActivity() {
        return _emailOnActivity;
    }

    @Override
    public boolean isEmailOnActivity() {
        return _emailOnActivity;
    }

    @Override
    public void setEmailOnActivity(boolean emailOnActivity) {
        _emailOnActivity = emailOnActivity;

        if (_messagingUserPreferencesRemoteModel != null) {
            try {
                Class<?> clazz = _messagingUserPreferencesRemoteModel.getClass();

                Method method = clazz.getMethod("setEmailOnActivity",
                        boolean.class);

                method.invoke(_messagingUserPreferencesRemoteModel,
                    emailOnActivity);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getEmailActivityDailyDigest() {
        return _emailActivityDailyDigest;
    }

    @Override
    public boolean isEmailActivityDailyDigest() {
        return _emailActivityDailyDigest;
    }

    @Override
    public void setEmailActivityDailyDigest(boolean emailActivityDailyDigest) {
        _emailActivityDailyDigest = emailActivityDailyDigest;

        if (_messagingUserPreferencesRemoteModel != null) {
            try {
                Class<?> clazz = _messagingUserPreferencesRemoteModel.getClass();

                Method method = clazz.getMethod("setEmailActivityDailyDigest",
                        boolean.class);

                method.invoke(_messagingUserPreferencesRemoteModel,
                    emailActivityDailyDigest);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMessagingUserPreferencesRemoteModel() {
        return _messagingUserPreferencesRemoteModel;
    }

    public void setMessagingUserPreferencesRemoteModel(
        BaseModel<?> messagingUserPreferencesRemoteModel) {
        _messagingUserPreferencesRemoteModel = messagingUserPreferencesRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _messagingUserPreferencesRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_messagingUserPreferencesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingUserPreferencesLocalServiceUtil.addMessagingUserPreferences(this);
        } else {
            MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(this);
        }
    }

    @Override
    public MessagingUserPreferences toEscapedModel() {
        return (MessagingUserPreferences) ProxyUtil.newProxyInstance(MessagingUserPreferences.class.getClassLoader(),
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
        clone.setEmailActivityDailyDigest(getEmailActivityDailyDigest());

        return clone;
    }

    @Override
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessagingUserPreferencesClp)) {
            return false;
        }

        MessagingUserPreferencesClp messagingUserPreferences = (MessagingUserPreferencesClp) obj;

        long primaryKey = messagingUserPreferences.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    public Class<?> getClpSerializerClass() {
        return _clpSerializerClass;
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

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
        sb.append(", emailActivityDailyDigest=");
        sb.append(getEmailActivityDailyDigest());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MessagingUserPreferences");
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
        sb.append(
            "<column><column-name>emailActivityDailyDigest</column-name><column-value><![CDATA[");
        sb.append(getEmailActivityDailyDigest());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
