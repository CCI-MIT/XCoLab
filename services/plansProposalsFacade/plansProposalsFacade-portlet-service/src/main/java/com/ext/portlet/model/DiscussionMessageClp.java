package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;

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


public class DiscussionMessageClp extends BaseModelImpl<DiscussionMessage>
    implements DiscussionMessage {
    private long _pk;
    private long _messageId;
    private String _subject;
    private String _body;
    private long _threadId;
    private long _categoryId;
    private long _categoryGroupId;
    private long _authorId;
    private Date _createDate;
    private long _version;
    private Date _deleted;
    private int _responsesCount;
    private Date _lastActivityDate;
    private long _lastActivityAuthorId;
    private BaseModel<?> _discussionMessageRemoteModel;

    public DiscussionMessageClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DiscussionMessage.class;
    }

    @Override
    public String getModelClassName() {
        return DiscussionMessage.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _pk;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPk(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _pk;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("pk", getPk());
        attributes.put("messageId", getMessageId());
        attributes.put("subject", getSubject());
        attributes.put("body", getBody());
        attributes.put("threadId", getThreadId());
        attributes.put("categoryId", getCategoryId());
        attributes.put("categoryGroupId", getCategoryGroupId());
        attributes.put("authorId", getAuthorId());
        attributes.put("createDate", getCreateDate());
        attributes.put("version", getVersion());
        attributes.put("deleted", getDeleted());
        attributes.put("responsesCount", getResponsesCount());
        attributes.put("lastActivityDate", getLastActivityDate());
        attributes.put("lastActivityAuthorId", getLastActivityAuthorId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long pk = (Long) attributes.get("pk");

        if (pk != null) {
            setPk(pk);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        String subject = (String) attributes.get("subject");

        if (subject != null) {
            setSubject(subject);
        }

        String body = (String) attributes.get("body");

        if (body != null) {
            setBody(body);
        }

        Long threadId = (Long) attributes.get("threadId");

        if (threadId != null) {
            setThreadId(threadId);
        }

        Long categoryId = (Long) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        Long categoryGroupId = (Long) attributes.get("categoryGroupId");

        if (categoryGroupId != null) {
            setCategoryGroupId(categoryGroupId);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Date deleted = (Date) attributes.get("deleted");

        if (deleted != null) {
            setDeleted(deleted);
        }

        Integer responsesCount = (Integer) attributes.get("responsesCount");

        if (responsesCount != null) {
            setResponsesCount(responsesCount);
        }

        Date lastActivityDate = (Date) attributes.get("lastActivityDate");

        if (lastActivityDate != null) {
            setLastActivityDate(lastActivityDate);
        }

        Long lastActivityAuthorId = (Long) attributes.get(
                "lastActivityAuthorId");

        if (lastActivityAuthorId != null) {
            setLastActivityAuthorId(lastActivityAuthorId);
        }
    }

    @Override
    public long getPk() {
        return _pk;
    }

    @Override
    public void setPk(long pk) {
        _pk = pk;

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setPk", long.class);

                method.invoke(_discussionMessageRemoteModel, pk);
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

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setMessageId", long.class);

                method.invoke(_discussionMessageRemoteModel, messageId);
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

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setSubject", String.class);

                method.invoke(_discussionMessageRemoteModel, subject);
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

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setBody", String.class);

                method.invoke(_discussionMessageRemoteModel, body);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getThreadId() {
        return _threadId;
    }

    @Override
    public void setThreadId(long threadId) {
        _threadId = threadId;

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setThreadId", long.class);

                method.invoke(_discussionMessageRemoteModel, threadId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCategoryId() {
        return _categoryId;
    }

    @Override
    public void setCategoryId(long categoryId) {
        _categoryId = categoryId;

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryId", long.class);

                method.invoke(_discussionMessageRemoteModel, categoryId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCategoryGroupId() {
        return _categoryGroupId;
    }

    @Override
    public void setCategoryGroupId(long categoryGroupId) {
        _categoryGroupId = categoryGroupId;

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryGroupId", long.class);

                method.invoke(_discussionMessageRemoteModel, categoryGroupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getAuthorId() {
        return _authorId;
    }

    @Override
    public void setAuthorId(long authorId) {
        _authorId = authorId;

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorId", long.class);

                method.invoke(_discussionMessageRemoteModel, authorId);
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

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_discussionMessageRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getVersion() {
        return _version;
    }

    @Override
    public void setVersion(long version) {
        _version = version;

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setVersion", long.class);

                method.invoke(_discussionMessageRemoteModel, version);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getDeleted() {
        return _deleted;
    }

    @Override
    public void setDeleted(Date deleted) {
        _deleted = deleted;

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setDeleted", Date.class);

                method.invoke(_discussionMessageRemoteModel, deleted);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResponsesCount() {
        return _responsesCount;
    }

    @Override
    public void setResponsesCount(int responsesCount) {
        _responsesCount = responsesCount;

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setResponsesCount", int.class);

                method.invoke(_discussionMessageRemoteModel, responsesCount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastActivityDate() {
        return _lastActivityDate;
    }

    @Override
    public void setLastActivityDate(Date lastActivityDate) {
        _lastActivityDate = lastActivityDate;

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setLastActivityDate",
                        Date.class);

                method.invoke(_discussionMessageRemoteModel, lastActivityDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getLastActivityAuthorId() {
        return _lastActivityAuthorId;
    }

    @Override
    public void setLastActivityAuthorId(long lastActivityAuthorId) {
        _lastActivityAuthorId = lastActivityAuthorId;

        if (_discussionMessageRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageRemoteModel.getClass();

                Method method = clazz.getMethod("setLastActivityAuthorId",
                        long.class);

                method.invoke(_discussionMessageRemoteModel,
                    lastActivityAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDiscussionMessageRemoteModel() {
        return _discussionMessageRemoteModel;
    }

    public void setDiscussionMessageRemoteModel(
        BaseModel<?> discussionMessageRemoteModel) {
        _discussionMessageRemoteModel = discussionMessageRemoteModel;
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

        Class<?> remoteModelClass = _discussionMessageRemoteModel.getClass();

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

        Object returnValue = method.invoke(_discussionMessageRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DiscussionMessageLocalServiceUtil.addDiscussionMessage(this);
        } else {
            DiscussionMessageLocalServiceUtil.updateDiscussionMessage(this);
        }
    }

    @Override
    public DiscussionMessage toEscapedModel() {
        return (DiscussionMessage) ProxyUtil.newProxyInstance(DiscussionMessage.class.getClassLoader(),
            new Class[] { DiscussionMessage.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DiscussionMessageClp clone = new DiscussionMessageClp();

        clone.setPk(getPk());
        clone.setMessageId(getMessageId());
        clone.setSubject(getSubject());
        clone.setBody(getBody());
        clone.setThreadId(getThreadId());
        clone.setCategoryId(getCategoryId());
        clone.setCategoryGroupId(getCategoryGroupId());
        clone.setAuthorId(getAuthorId());
        clone.setCreateDate(getCreateDate());
        clone.setVersion(getVersion());
        clone.setDeleted(getDeleted());
        clone.setResponsesCount(getResponsesCount());
        clone.setLastActivityDate(getLastActivityDate());
        clone.setLastActivityAuthorId(getLastActivityAuthorId());

        return clone;
    }

    @Override
    public int compareTo(DiscussionMessage discussionMessage) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(),
                discussionMessage.getCreateDate());

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

        if (!(obj instanceof DiscussionMessageClp)) {
            return false;
        }

        DiscussionMessageClp discussionMessage = (DiscussionMessageClp) obj;

        long primaryKey = discussionMessage.getPrimaryKey();

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
        StringBundler sb = new StringBundler(29);

        sb.append("{pk=");
        sb.append(getPk());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", subject=");
        sb.append(getSubject());
        sb.append(", body=");
        sb.append(getBody());
        sb.append(", threadId=");
        sb.append(getThreadId());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append(", categoryGroupId=");
        sb.append(getCategoryGroupId());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", deleted=");
        sb.append(getDeleted());
        sb.append(", responsesCount=");
        sb.append(getResponsesCount());
        sb.append(", lastActivityDate=");
        sb.append(getLastActivityDate());
        sb.append(", lastActivityAuthorId=");
        sb.append(getLastActivityAuthorId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(46);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.DiscussionMessage");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>pk</column-name><column-value><![CDATA[");
        sb.append(getPk());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
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
            "<column><column-name>threadId</column-name><column-value><![CDATA[");
        sb.append(getThreadId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryGroupId</column-name><column-value><![CDATA[");
        sb.append(getCategoryGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deleted</column-name><column-value><![CDATA[");
        sb.append(getDeleted());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>responsesCount</column-name><column-value><![CDATA[");
        sb.append(getResponsesCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastActivityDate</column-name><column-value><![CDATA[");
        sb.append(getLastActivityDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastActivityAuthorId</column-name><column-value><![CDATA[");
        sb.append(getLastActivityAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
