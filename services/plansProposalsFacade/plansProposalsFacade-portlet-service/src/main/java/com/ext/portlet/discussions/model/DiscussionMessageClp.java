package com.ext.portlet.discussions.model;

import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


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

    public DiscussionMessageClp() {
    }

    public Class<?> getModelClass() {
        return DiscussionMessage.class;
    }

    public String getModelClassName() {
        return DiscussionMessage.class.getName();
    }

    public long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(long primaryKey) {
        setPk(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_pk);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getPk() {
        return _pk;
    }

    public void setPk(long pk) {
        _pk = pk;
    }

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
    }

    public String getSubject() {
        return _subject;
    }

    public void setSubject(String subject) {
        _subject = subject;
    }

    public String getBody() {
        return _body;
    }

    public void setBody(String body) {
        _body = body;
    }

    public long getThreadId() {
        return _threadId;
    }

    public void setThreadId(long threadId) {
        _threadId = threadId;
    }

    public long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(long categoryId) {
        _categoryId = categoryId;
    }

    public long getCategoryGroupId() {
        return _categoryGroupId;
    }

    public void setCategoryGroupId(long categoryGroupId) {
        _categoryGroupId = categoryGroupId;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public long getVersion() {
        return _version;
    }

    public void setVersion(long version) {
        _version = version;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }

    public int getResponsesCount() {
        return _responsesCount;
    }

    public void setResponsesCount(int responsesCount) {
        _responsesCount = responsesCount;
    }

    public Date getLastActivityDate() {
        return _lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        _lastActivityDate = lastActivityDate;
    }

    public long getLastActivityAuthorId() {
        return _lastActivityAuthorId;
    }

    public void setLastActivityAuthorId(long lastActivityAuthorId) {
        _lastActivityAuthorId = lastActivityAuthorId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            DiscussionMessageLocalServiceUtil.addDiscussionMessage(this);
        } else {
            DiscussionMessageLocalServiceUtil.updateDiscussionMessage(this);
        }
    }

    @Override
    public DiscussionMessage toEscapedModel() {
        return (DiscussionMessage) Proxy.newProxyInstance(DiscussionMessage.class.getClassLoader(),
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
        if (obj == null) {
            return false;
        }

        DiscussionMessageClp discussionMessage = null;

        try {
            discussionMessage = (DiscussionMessageClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

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

    public String toXmlString() {
        StringBundler sb = new StringBundler(46);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.discussions.model.DiscussionMessage");
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
