package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MessagingIgnoredRecipientsClp extends BaseModelImpl<MessagingIgnoredRecipients>
    implements MessagingIgnoredRecipients {
    private long _ignoredRecipientId;
    private String _email;
    private String _name;
    private long _userId;
    private String _userUuid;
    private Date _createDate;
    private BaseModel<?> _messagingIgnoredRecipientsRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public MessagingIgnoredRecipientsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingIgnoredRecipients.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingIgnoredRecipients.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _ignoredRecipientId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setIgnoredRecipientId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ignoredRecipientId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ignoredRecipientId", getIgnoredRecipientId());
        attributes.put("email", getEmail());
        attributes.put("name", getName());
        attributes.put("userId", getUserId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long ignoredRecipientId = (Long) attributes.get("ignoredRecipientId");

        if (ignoredRecipientId != null) {
            setIgnoredRecipientId(ignoredRecipientId);
        }

        String email = (String) attributes.get("email");

        if (email != null) {
            setEmail(email);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    @Override
    public long getIgnoredRecipientId() {
        return _ignoredRecipientId;
    }

    @Override
    public void setIgnoredRecipientId(long ignoredRecipientId) {
        _ignoredRecipientId = ignoredRecipientId;

        if (_messagingIgnoredRecipientsRemoteModel != null) {
            try {
                Class<?> clazz = _messagingIgnoredRecipientsRemoteModel.getClass();

                Method method = clazz.getMethod("setIgnoredRecipientId",
                        long.class);

                method.invoke(_messagingIgnoredRecipientsRemoteModel,
                    ignoredRecipientId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEmail() {
        return _email;
    }

    @Override
    public void setEmail(String email) {
        _email = email;

        if (_messagingIgnoredRecipientsRemoteModel != null) {
            try {
                Class<?> clazz = _messagingIgnoredRecipientsRemoteModel.getClass();

                Method method = clazz.getMethod("setEmail", String.class);

                method.invoke(_messagingIgnoredRecipientsRemoteModel, email);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;

        if (_messagingIgnoredRecipientsRemoteModel != null) {
            try {
                Class<?> clazz = _messagingIgnoredRecipientsRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_messagingIgnoredRecipientsRemoteModel, name);
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

        if (_messagingIgnoredRecipientsRemoteModel != null) {
            try {
                Class<?> clazz = _messagingIgnoredRecipientsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_messagingIgnoredRecipientsRemoteModel, userId);
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
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _createDate = createDate;

        if (_messagingIgnoredRecipientsRemoteModel != null) {
            try {
                Class<?> clazz = _messagingIgnoredRecipientsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_messagingIgnoredRecipientsRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMessagingIgnoredRecipientsRemoteModel() {
        return _messagingIgnoredRecipientsRemoteModel;
    }

    public void setMessagingIgnoredRecipientsRemoteModel(
        BaseModel<?> messagingIgnoredRecipientsRemoteModel) {
        _messagingIgnoredRecipientsRemoteModel = messagingIgnoredRecipientsRemoteModel;
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

        Class<?> remoteModelClass = _messagingIgnoredRecipientsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_messagingIgnoredRecipientsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingIgnoredRecipientsLocalServiceUtil.addMessagingIgnoredRecipients(this);
        } else {
            MessagingIgnoredRecipientsLocalServiceUtil.updateMessagingIgnoredRecipients(this);
        }
    }

    @Override
    public MessagingIgnoredRecipients toEscapedModel() {
        return (MessagingIgnoredRecipients) ProxyUtil.newProxyInstance(MessagingIgnoredRecipients.class.getClassLoader(),
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

    @Override
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessagingIgnoredRecipientsClp)) {
            return false;
        }

        MessagingIgnoredRecipientsClp messagingIgnoredRecipients = (MessagingIgnoredRecipientsClp) obj;

        long primaryKey = messagingIgnoredRecipients.getPrimaryKey();

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

    @Override
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
