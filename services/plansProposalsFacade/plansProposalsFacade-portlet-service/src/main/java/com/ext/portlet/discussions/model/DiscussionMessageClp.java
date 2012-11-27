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
    private Long _pk;
    private Long _messageId;
    private String _subject;
    private String _body;
    private Long _threadId;
    private Long _categoryId;
    private Long _categoryGroupId;
    private Long _authorId;
    private Date _createDate;
    private Long _version;
    private Date _deleted;
    private Integer _responsesCount;
    private Date _lastActivityDate;
    private Long _lastActivityAuthorId;

    public DiscussionMessageClp() {
    }

    public Class<?> getModelClass() {
        return DiscussionMessage.class;
    }

    public String getModelClassName() {
        return DiscussionMessage.class.getName();
    }

    public Long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(Long primaryKey) {
        setPk(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_pk);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getPk() {
        return _pk;
    }

    public void setPk(Long pk) {
        _pk = pk;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
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

    public Long getThreadId() {
        return _threadId;
    }

    public void setThreadId(Long threadId) {
        _threadId = threadId;
    }

    public Long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(Long categoryId) {
        _categoryId = categoryId;
    }

    public Long getCategoryGroupId() {
        return _categoryGroupId;
    }

    public void setCategoryGroupId(Long categoryGroupId) {
        _categoryGroupId = categoryGroupId;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }

    public Integer getResponsesCount() {
        return _responsesCount;
    }

    public void setResponsesCount(Integer responsesCount) {
        _responsesCount = responsesCount;
    }

    public Date getLastActivityDate() {
        return _lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        _lastActivityDate = lastActivityDate;
    }

    public Long getLastActivityAuthorId() {
        return _lastActivityAuthorId;
    }

    public void setLastActivityAuthorId(Long lastActivityAuthorId) {
        _lastActivityAuthorId = lastActivityAuthorId;
    }

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadMessages() {
        throw new UnsupportedOperationException();
    }

    public int getThreadMessagesCount() {
        throw new UnsupportedOperationException();
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.discussions.model.DiscussionMessage addThreadMessage(
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author) {
        throw new UnsupportedOperationException();
    }

    public com.liferay.portal.model.User getAuthor() {
        throw new UnsupportedOperationException();
    }

    public com.liferay.portal.model.User getLastActivityAuthor() {
        throw new UnsupportedOperationException();
    }

    public void delete() {
        throw new UnsupportedOperationException();
    }

    public void update(java.lang.String subject, java.lang.String body) {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.discussions.model.DiscussionCategory getCategory() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getCategoryGroup() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.discussions.model.DiscussionMessage getThread() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> getFlags() {
        throw new UnsupportedOperationException();
    }

    public void addFlag(java.lang.String flagType, java.lang.String data,
        com.liferay.portal.model.User user) {
        throw new UnsupportedOperationException();
    }

    public void removeFlag(java.lang.String flagType) {
        throw new UnsupportedOperationException();
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

        Long primaryKey = discussionMessage.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
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
