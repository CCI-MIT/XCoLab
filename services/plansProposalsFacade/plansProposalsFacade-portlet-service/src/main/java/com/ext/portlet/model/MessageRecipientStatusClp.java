package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;

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


public class MessageRecipientStatusClp extends BaseModelImpl<MessageRecipientStatus>
    implements MessageRecipientStatus {
    private long _messageRecipientId;
    private long _messageId;
    private long _userId;
    private String _userUuid;
    private boolean _opened;
    private boolean _archived;
    private BaseModel<?> _messageRecipientStatusRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public MessageRecipientStatusClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MessageRecipientStatus.class;
    }

    @Override
    public String getModelClassName() {
        return MessageRecipientStatus.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _messageRecipientId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setMessageRecipientId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _messageRecipientId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("messageRecipientId", getMessageRecipientId());
        attributes.put("messageId", getMessageId());
        attributes.put("userId", getUserId());
        attributes.put("opened", getOpened());
        attributes.put("archived", getArchived());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long messageRecipientId = (Long) attributes.get("messageRecipientId");

        if (messageRecipientId != null) {
            setMessageRecipientId(messageRecipientId);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Boolean opened = (Boolean) attributes.get("opened");

        if (opened != null) {
            setOpened(opened);
        }

        Boolean archived = (Boolean) attributes.get("archived");

        if (archived != null) {
            setArchived(archived);
        }
    }

    @Override
    public long getMessageRecipientId() {
        return _messageRecipientId;
    }

    @Override
    public void setMessageRecipientId(long messageRecipientId) {
        _messageRecipientId = messageRecipientId;

        if (_messageRecipientStatusRemoteModel != null) {
            try {
                Class<?> clazz = _messageRecipientStatusRemoteModel.getClass();

                Method method = clazz.getMethod("setMessageRecipientId",
                        long.class);

                method.invoke(_messageRecipientStatusRemoteModel,
                    messageRecipientId);
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

        if (_messageRecipientStatusRemoteModel != null) {
            try {
                Class<?> clazz = _messageRecipientStatusRemoteModel.getClass();

                Method method = clazz.getMethod("setMessageId", long.class);

                method.invoke(_messageRecipientStatusRemoteModel, messageId);
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

        if (_messageRecipientStatusRemoteModel != null) {
            try {
                Class<?> clazz = _messageRecipientStatusRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_messageRecipientStatusRemoteModel, userId);
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
    public boolean getOpened() {
        return _opened;
    }

    @Override
    public boolean isOpened() {
        return _opened;
    }

    @Override
    public void setOpened(boolean opened) {
        _opened = opened;

        if (_messageRecipientStatusRemoteModel != null) {
            try {
                Class<?> clazz = _messageRecipientStatusRemoteModel.getClass();

                Method method = clazz.getMethod("setOpened", boolean.class);

                method.invoke(_messageRecipientStatusRemoteModel, opened);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getArchived() {
        return _archived;
    }

    @Override
    public boolean isArchived() {
        return _archived;
    }

    @Override
    public void setArchived(boolean archived) {
        _archived = archived;

        if (_messageRecipientStatusRemoteModel != null) {
            try {
                Class<?> clazz = _messageRecipientStatusRemoteModel.getClass();

                Method method = clazz.getMethod("setArchived", boolean.class);

                method.invoke(_messageRecipientStatusRemoteModel, archived);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMessageRecipientStatusRemoteModel() {
        return _messageRecipientStatusRemoteModel;
    }

    public void setMessageRecipientStatusRemoteModel(
        BaseModel<?> messageRecipientStatusRemoteModel) {
        _messageRecipientStatusRemoteModel = messageRecipientStatusRemoteModel;
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

        Class<?> remoteModelClass = _messageRecipientStatusRemoteModel.getClass();

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

        Object returnValue = method.invoke(_messageRecipientStatusRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MessageRecipientStatusLocalServiceUtil.addMessageRecipientStatus(this);
        } else {
            MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(this);
        }
    }

    @Override
    public MessageRecipientStatus toEscapedModel() {
        return (MessageRecipientStatus) ProxyUtil.newProxyInstance(MessageRecipientStatus.class.getClassLoader(),
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

    @Override
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessageRecipientStatusClp)) {
            return false;
        }

        MessageRecipientStatusClp messageRecipientStatus = (MessageRecipientStatusClp) obj;

        long primaryKey = messageRecipientStatus.getPrimaryKey();

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

    @Override
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
