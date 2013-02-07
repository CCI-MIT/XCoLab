package com.ext.portlet.model;

import com.ext.portlet.service.MessagingMessageConversionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class MessagingMessageConversionClp extends BaseModelImpl<MessagingMessageConversion>
    implements MessagingMessageConversion {
    private long _conversionId;
    private long _conversionTypeId;
    private long _messageId;
    private String _ipAddress;
    private String _extraData;
    private String _extraData2;
    private Date _createDate;

    public MessagingMessageConversionClp() {
    }

    public Class<?> getModelClass() {
        return MessagingMessageConversion.class;
    }

    public String getModelClassName() {
        return MessagingMessageConversion.class.getName();
    }

    public long getPrimaryKey() {
        return _conversionId;
    }

    public void setPrimaryKey(long primaryKey) {
        setConversionId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_conversionId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getConversionId() {
        return _conversionId;
    }

    public void setConversionId(long conversionId) {
        _conversionId = conversionId;
    }

    public long getConversionTypeId() {
        return _conversionTypeId;
    }

    public void setConversionTypeId(long conversionTypeId) {
        _conversionTypeId = conversionTypeId;
    }

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
    }

    public String getIpAddress() {
        return _ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        _ipAddress = ipAddress;
    }

    public String getExtraData() {
        return _extraData;
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }

    public String getExtraData2() {
        return _extraData2;
    }

    public void setExtraData2(String extraData2) {
        _extraData2 = extraData2;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingMessageConversionLocalServiceUtil.addMessagingMessageConversion(this);
        } else {
            MessagingMessageConversionLocalServiceUtil.updateMessagingMessageConversion(this);
        }
    }

    @Override
    public MessagingMessageConversion toEscapedModel() {
        return (MessagingMessageConversion) Proxy.newProxyInstance(MessagingMessageConversion.class.getClassLoader(),
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
        if (obj == null) {
            return false;
        }

        MessagingMessageConversionClp messagingMessageConversion = null;

        try {
            messagingMessageConversion = (MessagingMessageConversionClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

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
