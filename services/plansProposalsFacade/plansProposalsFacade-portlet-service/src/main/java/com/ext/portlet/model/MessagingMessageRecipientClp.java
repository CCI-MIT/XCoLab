package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.MessagingMessageRecipientLocalServiceUtil;

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


public class MessagingMessageRecipientClp extends BaseModelImpl<MessagingMessageRecipient>
    implements MessagingMessageRecipient {
    private long _recipientId;
    private long _messageId;
    private long _userId;
    private String _userUuid;
    private String _emailAddress;
    private BaseModel<?> _messagingMessageRecipientRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public MessagingMessageRecipientClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingMessageRecipient.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingMessageRecipient.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _recipientId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setRecipientId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _recipientId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("recipientId", getRecipientId());
        attributes.put("messageId", getMessageId());
        attributes.put("userId", getUserId());
        attributes.put("emailAddress", getEmailAddress());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long recipientId = (Long) attributes.get("recipientId");

        if (recipientId != null) {
            setRecipientId(recipientId);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String emailAddress = (String) attributes.get("emailAddress");

        if (emailAddress != null) {
            setEmailAddress(emailAddress);
        }
    }

    @Override
    public long getRecipientId() {
        return _recipientId;
    }

    @Override
    public void setRecipientId(long recipientId) {
        _recipientId = recipientId;

        if (_messagingMessageRecipientRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRecipientRemoteModel.getClass();

                Method method = clazz.getMethod("setRecipientId", long.class);

                method.invoke(_messagingMessageRecipientRemoteModel, recipientId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getMessageId() {
        return _messageId;
    }

    @Override
    public void setMessageId(long messageId) {
        _messageId = messageId;

        if (_messagingMessageRecipientRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRecipientRemoteModel.getClass();

                Method method = clazz.getMethod("setMessageId", long.class);

                method.invoke(_messagingMessageRecipientRemoteModel, messageId);
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

        if (_messagingMessageRecipientRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRecipientRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_messagingMessageRecipientRemoteModel, userId);
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
    public String getEmailAddress() {
        return _emailAddress;
    }

    @Override
    public void setEmailAddress(String emailAddress) {
        _emailAddress = emailAddress;

        if (_messagingMessageRecipientRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRecipientRemoteModel.getClass();

                Method method = clazz.getMethod("setEmailAddress", String.class);

                method.invoke(_messagingMessageRecipientRemoteModel,
                    emailAddress);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMessagingMessageRecipientRemoteModel() {
        return _messagingMessageRecipientRemoteModel;
    }

    public void setMessagingMessageRecipientRemoteModel(
        BaseModel<?> messagingMessageRecipientRemoteModel) {
        _messagingMessageRecipientRemoteModel = messagingMessageRecipientRemoteModel;
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

        Class<?> remoteModelClass = _messagingMessageRecipientRemoteModel.getClass();

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

        Object returnValue = method.invoke(_messagingMessageRecipientRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingMessageRecipientLocalServiceUtil.addMessagingMessageRecipient(this);
        } else {
            MessagingMessageRecipientLocalServiceUtil.updateMessagingMessageRecipient(this);
        }
    }

    @Override
    public MessagingMessageRecipient toEscapedModel() {
        return (MessagingMessageRecipient) ProxyUtil.newProxyInstance(MessagingMessageRecipient.class.getClassLoader(),
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

    @Override
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessagingMessageRecipientClp)) {
            return false;
        }

        MessagingMessageRecipientClp messagingMessageRecipient = (MessagingMessageRecipientClp) obj;

        long primaryKey = messagingMessageRecipient.getPrimaryKey();

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

    @Override
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
