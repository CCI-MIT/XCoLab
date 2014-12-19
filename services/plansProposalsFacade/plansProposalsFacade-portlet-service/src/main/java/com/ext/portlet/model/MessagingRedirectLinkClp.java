package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.MessagingRedirectLinkLocalServiceUtil;

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


public class MessagingRedirectLinkClp extends BaseModelImpl<MessagingRedirectLink>
    implements MessagingRedirectLink {
    private long _redirectId;
    private String _link;
    private long _messageId;
    private Date _createDate;
    private BaseModel<?> _messagingRedirectLinkRemoteModel;

    public MessagingRedirectLinkClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingRedirectLink.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingRedirectLink.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _redirectId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setRedirectId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _redirectId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("redirectId", getRedirectId());
        attributes.put("link", getLink());
        attributes.put("messageId", getMessageId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long redirectId = (Long) attributes.get("redirectId");

        if (redirectId != null) {
            setRedirectId(redirectId);
        }

        String link = (String) attributes.get("link");

        if (link != null) {
            setLink(link);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    @Override
    public long getRedirectId() {
        return _redirectId;
    }

    @Override
    public void setRedirectId(long redirectId) {
        _redirectId = redirectId;

        if (_messagingRedirectLinkRemoteModel != null) {
            try {
                Class<?> clazz = _messagingRedirectLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setRedirectId", long.class);

                method.invoke(_messagingRedirectLinkRemoteModel, redirectId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLink() {
        return _link;
    }

    @Override
    public void setLink(String link) {
        _link = link;

        if (_messagingRedirectLinkRemoteModel != null) {
            try {
                Class<?> clazz = _messagingRedirectLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setLink", String.class);

                method.invoke(_messagingRedirectLinkRemoteModel, link);
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

        if (_messagingRedirectLinkRemoteModel != null) {
            try {
                Class<?> clazz = _messagingRedirectLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setMessageId", long.class);

                method.invoke(_messagingRedirectLinkRemoteModel, messageId);
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

        if (_messagingRedirectLinkRemoteModel != null) {
            try {
                Class<?> clazz = _messagingRedirectLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_messagingRedirectLinkRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMessagingRedirectLinkRemoteModel() {
        return _messagingRedirectLinkRemoteModel;
    }

    public void setMessagingRedirectLinkRemoteModel(
        BaseModel<?> messagingRedirectLinkRemoteModel) {
        _messagingRedirectLinkRemoteModel = messagingRedirectLinkRemoteModel;
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

        Class<?> remoteModelClass = _messagingRedirectLinkRemoteModel.getClass();

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

        Object returnValue = method.invoke(_messagingRedirectLinkRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingRedirectLinkLocalServiceUtil.addMessagingRedirectLink(this);
        } else {
            MessagingRedirectLinkLocalServiceUtil.updateMessagingRedirectLink(this);
        }
    }

    @Override
    public MessagingRedirectLink toEscapedModel() {
        return (MessagingRedirectLink) ProxyUtil.newProxyInstance(MessagingRedirectLink.class.getClassLoader(),
            new Class[] { MessagingRedirectLink.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessagingRedirectLinkClp clone = new MessagingRedirectLinkClp();

        clone.setRedirectId(getRedirectId());
        clone.setLink(getLink());
        clone.setMessageId(getMessageId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    @Override
    public int compareTo(MessagingRedirectLink messagingRedirectLink) {
        long primaryKey = messagingRedirectLink.getPrimaryKey();

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

        if (!(obj instanceof MessagingRedirectLinkClp)) {
            return false;
        }

        MessagingRedirectLinkClp messagingRedirectLink = (MessagingRedirectLinkClp) obj;

        long primaryKey = messagingRedirectLink.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{redirectId=");
        sb.append(getRedirectId());
        sb.append(", link=");
        sb.append(getLink());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MessagingRedirectLink");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>redirectId</column-name><column-value><![CDATA[");
        sb.append(getRedirectId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>link</column-name><column-value><![CDATA[");
        sb.append(getLink());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
