package com.ext.portlet.model;

import com.ext.portlet.service.MessagingMessageConversionTypeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class MessagingMessageConversionTypeClp extends BaseModelImpl<MessagingMessageConversionType>
    implements MessagingMessageConversionType {
    private long _typeId;
    private String _name;
    private String _description;

    public MessagingMessageConversionTypeClp() {
    }

    public Class<?> getModelClass() {
        return MessagingMessageConversionType.class;
    }

    public String getModelClassName() {
        return MessagingMessageConversionType.class.getName();
    }

    public long getPrimaryKey() {
        return _typeId;
    }

    public void setPrimaryKey(long primaryKey) {
        setTypeId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_typeId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getTypeId() {
        return _typeId;
    }

    public void setTypeId(long typeId) {
        _typeId = typeId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingMessageConversionTypeLocalServiceUtil.addMessagingMessageConversionType(this);
        } else {
            MessagingMessageConversionTypeLocalServiceUtil.updateMessagingMessageConversionType(this);
        }
    }

    @Override
    public MessagingMessageConversionType toEscapedModel() {
        return (MessagingMessageConversionType) Proxy.newProxyInstance(MessagingMessageConversionType.class.getClassLoader(),
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
        if (obj == null) {
            return false;
        }

        MessagingMessageConversionTypeClp messagingMessageConversionType = null;

        try {
            messagingMessageConversionType = (MessagingMessageConversionTypeClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = messagingMessageConversionType.getPrimaryKey();

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
