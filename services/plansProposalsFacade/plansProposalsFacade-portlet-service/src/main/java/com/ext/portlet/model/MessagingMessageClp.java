package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.MessagingMessageLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MessagingMessageClp extends BaseModelImpl<MessagingMessage>
    implements MessagingMessage {
    private long _messageId;
    private String _name;
    private String _description;
    private String _subject;
    private String _body;
    private String _replyTo;
    private boolean _sendToAll;
    private long _conversionCount;
    private String _redirectURL;
    private long _creatorId;
    private Date _createDate;
    private Date _modifiedDate;
    private BaseModel<?> _messagingMessageRemoteModel;

    public MessagingMessageClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingMessage.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingMessage.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _messageId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setMessageId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _messageId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("messageId", getMessageId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("subject", getSubject());
        attributes.put("body", getBody());
        attributes.put("replyTo", getReplyTo());
        attributes.put("sendToAll", getSendToAll());
        attributes.put("conversionCount", getConversionCount());
        attributes.put("redirectURL", getRedirectURL());
        attributes.put("creatorId", getCreatorId());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String subject = (String) attributes.get("subject");

        if (subject != null) {
            setSubject(subject);
        }

        String body = (String) attributes.get("body");

        if (body != null) {
            setBody(body);
        }

        String replyTo = (String) attributes.get("replyTo");

        if (replyTo != null) {
            setReplyTo(replyTo);
        }

        Boolean sendToAll = (Boolean) attributes.get("sendToAll");

        if (sendToAll != null) {
            setSendToAll(sendToAll);
        }

        Long conversionCount = (Long) attributes.get("conversionCount");

        if (conversionCount != null) {
            setConversionCount(conversionCount);
        }

        String redirectURL = (String) attributes.get("redirectURL");

        if (redirectURL != null) {
            setRedirectURL(redirectURL);
        }

        Long creatorId = (Long) attributes.get("creatorId");

        if (creatorId != null) {
            setCreatorId(creatorId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    @Override
    public long getMessageId() {
        return _messageId;
    }

    @Override
    public void setMessageId(long messageId) {
        _messageId = messageId;

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setMessageId", long.class);

                method.invoke(_messagingMessageRemoteModel, messageId);
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

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_messagingMessageRemoteModel, name);
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

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_messagingMessageRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSubject() {
        return _subject;
    }

    @Override
    public void setSubject(String subject) {
        _subject = subject;

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setSubject", String.class);

                method.invoke(_messagingMessageRemoteModel, subject);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBody() {
        return _body;
    }

    @Override
    public void setBody(String body) {
        _body = body;

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setBody", String.class);

                method.invoke(_messagingMessageRemoteModel, body);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReplyTo() {
        return _replyTo;
    }

    @Override
    public void setReplyTo(String replyTo) {
        _replyTo = replyTo;

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setReplyTo", String.class);

                method.invoke(_messagingMessageRemoteModel, replyTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getSendToAll() {
        return _sendToAll;
    }

    @Override
    public boolean isSendToAll() {
        return _sendToAll;
    }

    @Override
    public void setSendToAll(boolean sendToAll) {
        _sendToAll = sendToAll;

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setSendToAll", boolean.class);

                method.invoke(_messagingMessageRemoteModel, sendToAll);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getConversionCount() {
        return _conversionCount;
    }

    @Override
    public void setConversionCount(long conversionCount) {
        _conversionCount = conversionCount;

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setConversionCount", long.class);

                method.invoke(_messagingMessageRemoteModel, conversionCount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRedirectURL() {
        return _redirectURL;
    }

    @Override
    public void setRedirectURL(String redirectURL) {
        _redirectURL = redirectURL;

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setRedirectURL", String.class);

                method.invoke(_messagingMessageRemoteModel, redirectURL);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCreatorId() {
        return _creatorId;
    }

    @Override
    public void setCreatorId(long creatorId) {
        _creatorId = creatorId;

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatorId", long.class);

                method.invoke(_messagingMessageRemoteModel, creatorId);
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

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_messagingMessageRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_messagingMessageRemoteModel != null) {
            try {
                Class<?> clazz = _messagingMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_messagingMessageRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMessagingMessageRemoteModel() {
        return _messagingMessageRemoteModel;
    }

    public void setMessagingMessageRemoteModel(
        BaseModel<?> messagingMessageRemoteModel) {
        _messagingMessageRemoteModel = messagingMessageRemoteModel;
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

        Class<?> remoteModelClass = _messagingMessageRemoteModel.getClass();

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

        Object returnValue = method.invoke(_messagingMessageRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MessagingMessageLocalServiceUtil.addMessagingMessage(this);
        } else {
            MessagingMessageLocalServiceUtil.updateMessagingMessage(this);
        }
    }

    @Override
    public MessagingMessage toEscapedModel() {
        return (MessagingMessage) ProxyUtil.newProxyInstance(MessagingMessage.class.getClassLoader(),
            new Class[] { MessagingMessage.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessagingMessageClp clone = new MessagingMessageClp();

        clone.setMessageId(getMessageId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setSubject(getSubject());
        clone.setBody(getBody());
        clone.setReplyTo(getReplyTo());
        clone.setSendToAll(getSendToAll());
        clone.setConversionCount(getConversionCount());
        clone.setRedirectURL(getRedirectURL());
        clone.setCreatorId(getCreatorId());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(MessagingMessage messagingMessage) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(),
                messagingMessage.getCreateDate());

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

        if (!(obj instanceof MessagingMessageClp)) {
            return false;
        }

        MessagingMessageClp messagingMessage = (MessagingMessageClp) obj;

        long primaryKey = messagingMessage.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{messageId=");
        sb.append(getMessageId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", subject=");
        sb.append(getSubject());
        sb.append(", body=");
        sb.append(getBody());
        sb.append(", replyTo=");
        sb.append(getReplyTo());
        sb.append(", sendToAll=");
        sb.append(getSendToAll());
        sb.append(", conversionCount=");
        sb.append(getConversionCount());
        sb.append(", redirectURL=");
        sb.append(getRedirectURL());
        sb.append(", creatorId=");
        sb.append(getCreatorId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MessagingMessage");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subject</column-name><column-value><![CDATA[");
        sb.append(getSubject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>body</column-name><column-value><![CDATA[");
        sb.append(getBody());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>replyTo</column-name><column-value><![CDATA[");
        sb.append(getReplyTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sendToAll</column-name><column-value><![CDATA[");
        sb.append(getSendToAll());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>conversionCount</column-name><column-value><![CDATA[");
        sb.append(getConversionCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>redirectURL</column-name><column-value><![CDATA[");
        sb.append(getRedirectURL());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>creatorId</column-name><column-value><![CDATA[");
        sb.append(getCreatorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
