package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.MessageLocalServiceUtil;

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


public class MessageClp extends BaseModelImpl<Message> implements Message {
    private long _messageId;
    private long _fromId;
    private long _repliesTo;
    private Date _createDate;
    private String _subject;
    private String _content;
    private BaseModel<?> _messageRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public MessageClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return Message.class;
    }

    @Override
    public String getModelClassName() {
        return Message.class.getName();
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
        attributes.put("fromId", getFromId());
        attributes.put("repliesTo", getRepliesTo());
        attributes.put("createDate", getCreateDate());
        attributes.put("subject", getSubject());
        attributes.put("content", getContent());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        Long fromId = (Long) attributes.get("fromId");

        if (fromId != null) {
            setFromId(fromId);
        }

        Long repliesTo = (Long) attributes.get("repliesTo");

        if (repliesTo != null) {
            setRepliesTo(repliesTo);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        String subject = (String) attributes.get("subject");

        if (subject != null) {
            setSubject(subject);
        }

        String content = (String) attributes.get("content");

        if (content != null) {
            setContent(content);
        }
    }

    @Override
    public long getMessageId() {
        return _messageId;
    }

    @Override
    public void setMessageId(long messageId) {
        _messageId = messageId;

        if (_messageRemoteModel != null) {
            try {
                Class<?> clazz = _messageRemoteModel.getClass();

                Method method = clazz.getMethod("setMessageId", long.class);

                method.invoke(_messageRemoteModel, messageId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getFromId() {
        return _fromId;
    }

    @Override
    public void setFromId(long fromId) {
        _fromId = fromId;

        if (_messageRemoteModel != null) {
            try {
                Class<?> clazz = _messageRemoteModel.getClass();

                Method method = clazz.getMethod("setFromId", long.class);

                method.invoke(_messageRemoteModel, fromId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getRepliesTo() {
        return _repliesTo;
    }

    @Override
    public void setRepliesTo(long repliesTo) {
        _repliesTo = repliesTo;

        if (_messageRemoteModel != null) {
            try {
                Class<?> clazz = _messageRemoteModel.getClass();

                Method method = clazz.getMethod("setRepliesTo", long.class);

                method.invoke(_messageRemoteModel, repliesTo);
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

        if (_messageRemoteModel != null) {
            try {
                Class<?> clazz = _messageRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_messageRemoteModel, createDate);
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

        if (_messageRemoteModel != null) {
            try {
                Class<?> clazz = _messageRemoteModel.getClass();

                Method method = clazz.getMethod("setSubject", String.class);

                method.invoke(_messageRemoteModel, subject);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContent() {
        return _content;
    }

    @Override
    public void setContent(String content) {
        _content = content;

        if (_messageRemoteModel != null) {
            try {
                Class<?> clazz = _messageRemoteModel.getClass();

                Method method = clazz.getMethod("setContent", String.class);

                method.invoke(_messageRemoteModel, content);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMessageRemoteModel() {
        return _messageRemoteModel;
    }

    public void setMessageRemoteModel(BaseModel<?> messageRemoteModel) {
        _messageRemoteModel = messageRemoteModel;
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

        Class<?> remoteModelClass = _messageRemoteModel.getClass();

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

        Object returnValue = method.invoke(_messageRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MessageLocalServiceUtil.addMessage(this);
        } else {
            MessageLocalServiceUtil.updateMessage(this);
        }
    }

    @Override
    public Message toEscapedModel() {
        return (Message) ProxyUtil.newProxyInstance(Message.class.getClassLoader(),
            new Class[] { Message.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MessageClp clone = new MessageClp();

        clone.setMessageId(getMessageId());
        clone.setFromId(getFromId());
        clone.setRepliesTo(getRepliesTo());
        clone.setCreateDate(getCreateDate());
        clone.setSubject(getSubject());
        clone.setContent(getContent());

        return clone;
    }

    @Override
    public int compareTo(Message message) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(), message.getCreateDate());

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

        if (!(obj instanceof MessageClp)) {
            return false;
        }

        MessageClp message = (MessageClp) obj;

        long primaryKey = message.getPrimaryKey();

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

        sb.append("{messageId=");
        sb.append(getMessageId());
        sb.append(", fromId=");
        sb.append(getFromId());
        sb.append(", repliesTo=");
        sb.append(getRepliesTo());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", subject=");
        sb.append(getSubject());
        sb.append(", content=");
        sb.append(getContent());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.Message");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fromId</column-name><column-value><![CDATA[");
        sb.append(getFromId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>repliesTo</column-name><column-value><![CDATA[");
        sb.append(getRepliesTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subject</column-name><column-value><![CDATA[");
        sb.append(getSubject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
