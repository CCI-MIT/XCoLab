package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.MessagingMessageConversionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MessagingMessageConversionClp extends BaseModelImpl<MessagingMessageConversion>
    implements MessagingMessageConversion {
    private long _conversionId;
    private long _conversionTypeId;
    private long _messageId;
    private String _ipAddress;
    private String _extraData;
    private String _extraData2;
    private Date _createDate;
    private BaseModel<?> _messagingMessageConversionRemoteModel;

    public MessagingMessageConversionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingMessageConversion.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingMessageConversion.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _conversionId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setConversionId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _conversionId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("conversionId", getConversionId());
        attributes.put("conversionTypeId", getConversionTypeId());
        attributes.put("messageId", getMessageId());
        attributes.put("ipAddress", getIpAddress());
        attributes.put("extraData", getExtraData());
        attributes.put("extraData2", getExtraData2());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long conversionId = (Long) attributes.get("conversionId");

        if (conversionId != null) {
            setConversionId(conversionId);
        }

        Long conversionTypeId = (Long) attributes.get("conversionTypeId");

        if (conversionTypeId != null) {
            setConversionTypeId(conversionTypeId);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        String ipAddress = (String) attributes.get("ipAddress");

        if (ipAddress != null) {
            setIpAddress(ipAddress);
        }

        String extraData = (String) attributes.get("extraData");

        if (extraData != null) {
            setExtraData(extraData);
        }

        String extraData2 = (String) attributes.get("extraData2");

        if (extraData2 != null) {
            setExtraData2(extraData2);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    @Override
    public long getConversionId() {
        return _conversionId;
    }

    @Override
    public void setConversionId(long conversionId) {
        _conversionId = conversionId;

        if (_messagingMessageConversionRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageConversionRemoteModel.getClass();

                Method method = clazz.getMethod("setConversionId", long.class);

                method.invoke(_messagingMessageConversionRemoteModel,
                    conversionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getConversionTypeId() {
        return _conversionTypeId;
    }

    @Override
    public void setConversionTypeId(long conversionTypeId) {
        _conversionTypeId = conversionTypeId;

        if (_messagingMessageConversionRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageConversionRemoteModel.getClass();

                Method method = clazz.getMethod("setConversionTypeId",
                        long.class);

                method.invoke(_messagingMessageConversionRemoteModel,
                    conversionTypeId);
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

        if (_messagingMessageConversionRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageConversionRemoteModel.getClass();

                Method method = clazz.getMethod("setMessageId", long.class);

                method.invoke(_messagingMessageConversionRemoteModel, messageId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIpAddress() {
        return _ipAddress;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        _ipAddress = ipAddress;

        if (_messagingMessageConversionRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageConversionRemoteModel.getClass();

                Method method = clazz.getMethod("setIpAddress", String.class);

                method.invoke(_messagingMessageConversionRemoteModel, ipAddress);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getExtraData() {
        return _extraData;
    }

    @Override
    public void setExtraData(String extraData) {
        _extraData = extraData;

        if (_messagingMessageConversionRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageConversionRemoteModel.getClass();

                Method method = clazz.getMethod("setExtraData", String.class);

                method.invoke(_messagingMessageConversionRemoteModel, extraData);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getExtraData2() {
        return _extraData2;
    }

    @Override
    public void setExtraData2(String extraData2) {
        _extraData2 = extraData2;

        if (_messagingMessageConversionRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageConversionRemoteModel.getClass();

                Method method = clazz.getMethod("setExtraData2", String.class);

                method.invoke(_messagingMessageConversionRemoteModel, extraData2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _createDate = createDate;

        if (_messagingMessageConversionRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageConversionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_messagingMessageConversionRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMessagingMessageConversionRemoteModel() {
        return _messagingMessageConversionRemoteModel;
    }

    public void setMessagingMessageConversionRemoteModel(
        BaseModel<?> messagingMessageConversionRemoteModel) {
        _messagingMessageConversionRemoteModel = messagingMessageConversionRemoteModel;
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

        Class<?> remoteModelClass = _messagingMessageConversionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_messagingMessageConversionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingMessageConversionLocalServiceUtil.addMessagingMessageConversion(this);
        } else {
            MessagingMessageConversionLocalServiceUtil.updateMessagingMessageConversion(this);
        }
    }

    @Override
    public MessagingMessageConversion toEscapedModel() {
        return (MessagingMessageConversion) ProxyUtil.newProxyInstance(MessagingMessageConversion.class.getClassLoader(),
            new Class[] { MessagingMessageConversion.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessagingMessageConversionClp clone = new MessagingMessageConversionClp();

        clone.setConversionId(getConversionId());
        clone.setConversionTypeId(getConversionTypeId());
        clone.setMessageId(getMessageId());
        clone.setIpAddress(getIpAddress());
        clone.setExtraData(getExtraData());
        clone.setExtraData2(getExtraData2());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    @Override
    public int compareTo(MessagingMessageConversion messagingMessageConversion) {
        long primaryKey = messagingMessageConversion.getPrimaryKey();

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

        if (!(obj instanceof MessagingMessageConversionClp)) {
            return false;
        }

        MessagingMessageConversionClp messagingMessageConversion = (MessagingMessageConversionClp) obj;

        long primaryKey = messagingMessageConversion.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{conversionId=");
        sb.append(getConversionId());
        sb.append(", conversionTypeId=");
        sb.append(getConversionTypeId());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", ipAddress=");
        sb.append(getIpAddress());
        sb.append(", extraData=");
        sb.append(getExtraData());
        sb.append(", extraData2=");
        sb.append(getExtraData2());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MessagingMessageConversion");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>conversionId</column-name><column-value><![CDATA[");
        sb.append(getConversionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>conversionTypeId</column-name><column-value><![CDATA[");
        sb.append(getConversionTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ipAddress</column-name><column-value><![CDATA[");
        sb.append(getIpAddress());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>extraData</column-name><column-value><![CDATA[");
        sb.append(getExtraData());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>extraData2</column-name><column-value><![CDATA[");
        sb.append(getExtraData2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
