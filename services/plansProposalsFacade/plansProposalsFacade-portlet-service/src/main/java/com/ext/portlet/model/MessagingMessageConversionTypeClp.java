package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.MessagingMessageConversionTypeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class MessagingMessageConversionTypeClp extends BaseModelImpl<MessagingMessageConversionType>
    implements MessagingMessageConversionType {
    private long _typeId;
    private String _name;
    private String _description;
    private BaseModel<?> _messagingMessageConversionTypeRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public MessagingMessageConversionTypeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingMessageConversionType.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingMessageConversionType.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _typeId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setTypeId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _typeId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("typeId", getTypeId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long typeId = (Long) attributes.get("typeId");

        if (typeId != null) {
            setTypeId(typeId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }
    }

    @Override
    public long getTypeId() {
        return _typeId;
    }

    @Override
    public void setTypeId(long typeId) {
        _typeId = typeId;

        if (_messagingMessageConversionTypeRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageConversionTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setTypeId", long.class);

                method.invoke(_messagingMessageConversionTypeRemoteModel, typeId);
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

        if (_messagingMessageConversionTypeRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageConversionTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_messagingMessageConversionTypeRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_messagingMessageConversionTypeRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageConversionTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_messagingMessageConversionTypeRemoteModel,
                    description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMessagingMessageConversionTypeRemoteModel() {
        return _messagingMessageConversionTypeRemoteModel;
    }

    public void setMessagingMessageConversionTypeRemoteModel(
        BaseModel<?> messagingMessageConversionTypeRemoteModel) {
        _messagingMessageConversionTypeRemoteModel = messagingMessageConversionTypeRemoteModel;
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

        Class<?> remoteModelClass = _messagingMessageConversionTypeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_messagingMessageConversionTypeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingMessageConversionTypeLocalServiceUtil.addMessagingMessageConversionType(this);
        } else {
            MessagingMessageConversionTypeLocalServiceUtil.updateMessagingMessageConversionType(this);
        }
    }

    @Override
    public MessagingMessageConversionType toEscapedModel() {
        return (MessagingMessageConversionType) ProxyUtil.newProxyInstance(MessagingMessageConversionType.class.getClassLoader(),
            new Class[] { MessagingMessageConversionType.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessagingMessageConversionTypeClp clone = new MessagingMessageConversionTypeClp();

        clone.setTypeId(getTypeId());
        clone.setName(getName());
        clone.setDescription(getDescription());

        return clone;
    }

    @Override
    public int compareTo(
        MessagingMessageConversionType messagingMessageConversionType) {
        long primaryKey = messagingMessageConversionType.getPrimaryKey();

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

        if (!(obj instanceof MessagingMessageConversionTypeClp)) {
            return false;
        }

        MessagingMessageConversionTypeClp messagingMessageConversionType = (MessagingMessageConversionTypeClp) obj;

        long primaryKey = messagingMessageConversionType.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{typeId=");
        sb.append(getTypeId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MessagingMessageConversionType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>typeId</column-name><column-value><![CDATA[");
        sb.append(getTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
