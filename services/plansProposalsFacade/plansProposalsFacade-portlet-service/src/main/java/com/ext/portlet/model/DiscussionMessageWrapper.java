package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionMessage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessage
 * @generated
 */
public class DiscussionMessageWrapper implements DiscussionMessage,
    ModelWrapper<DiscussionMessage> {
    private DiscussionMessage _discussionMessage;

    public DiscussionMessageWrapper(DiscussionMessage discussionMessage) {
        _discussionMessage = discussionMessage;
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

    /**
    * Returns the primary key of this discussion message.
    *
    * @return the primary key of this discussion message
    */
    @Override
    public long getPrimaryKey() {
        return _discussionMessage.getPrimaryKey();
    }

    /**
    * Sets the primary key of this discussion message.
    *
    * @param primaryKey the primary key of this discussion message
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _discussionMessage.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pk of this discussion message.
    *
    * @return the pk of this discussion message
    */
    @Override
    public long getPk() {
        return _discussionMessage.getPk();
    }

    /**
    * Sets the pk of this discussion message.
    *
    * @param pk the pk of this discussion message
    */
    @Override
    public void setPk(long pk) {
        _discussionMessage.setPk(pk);
    }

    /**
    * Returns the message ID of this discussion message.
    *
    * @return the message ID of this discussion message
    */
    @Override
    public long getMessageId() {
        return _discussionMessage.getMessageId();
    }

    /**
    * Sets the message ID of this discussion message.
    *
    * @param messageId the message ID of this discussion message
    */
    @Override
    public void setMessageId(long messageId) {
        _discussionMessage.setMessageId(messageId);
    }

    /**
    * Returns the subject of this discussion message.
    *
    * @return the subject of this discussion message
    */
    @Override
    public java.lang.String getSubject() {
        return _discussionMessage.getSubject();
    }

    /**
    * Sets the subject of this discussion message.
    *
    * @param subject the subject of this discussion message
    */
    @Override
    public void setSubject(java.lang.String subject) {
        _discussionMessage.setSubject(subject);
    }

    /**
    * Returns the body of this discussion message.
    *
    * @return the body of this discussion message
    */
    @Override
    public java.lang.String getBody() {
        return _discussionMessage.getBody();
    }

    /**
    * Sets the body of this discussion message.
    *
    * @param body the body of this discussion message
    */
    @Override
    public void setBody(java.lang.String body) {
        _discussionMessage.setBody(body);
    }

    /**
    * Returns the thread ID of this discussion message.
    *
    * @return the thread ID of this discussion message
    */
    @Override
    public long getThreadId() {
        return _discussionMessage.getThreadId();
    }

    /**
    * Sets the thread ID of this discussion message.
    *
    * @param threadId the thread ID of this discussion message
    */
    @Override
    public void setThreadId(long threadId) {
        _discussionMessage.setThreadId(threadId);
    }

    /**
    * Returns the category ID of this discussion message.
    *
    * @return the category ID of this discussion message
    */
    @Override
    public long getCategoryId() {
        return _discussionMessage.getCategoryId();
    }

    /**
    * Sets the category ID of this discussion message.
    *
    * @param categoryId the category ID of this discussion message
    */
    @Override
    public void setCategoryId(long categoryId) {
        _discussionMessage.setCategoryId(categoryId);
    }

    /**
    * Returns the category group ID of this discussion message.
    *
    * @return the category group ID of this discussion message
    */
    @Override
    public long getCategoryGroupId() {
        return _discussionMessage.getCategoryGroupId();
    }

    /**
    * Sets the category group ID of this discussion message.
    *
    * @param categoryGroupId the category group ID of this discussion message
    */
    @Override
    public void setCategoryGroupId(long categoryGroupId) {
        _discussionMessage.setCategoryGroupId(categoryGroupId);
    }

    /**
    * Returns the author ID of this discussion message.
    *
    * @return the author ID of this discussion message
    */
    @Override
    public long getAuthorId() {
        return _discussionMessage.getAuthorId();
    }

    /**
    * Sets the author ID of this discussion message.
    *
    * @param authorId the author ID of this discussion message
    */
    @Override
    public void setAuthorId(long authorId) {
        _discussionMessage.setAuthorId(authorId);
    }

    /**
    * Returns the create date of this discussion message.
    *
    * @return the create date of this discussion message
    */
    @Override
    public java.util.Date getCreateDate() {
        return _discussionMessage.getCreateDate();
    }

    /**
    * Sets the create date of this discussion message.
    *
    * @param createDate the create date of this discussion message
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _discussionMessage.setCreateDate(createDate);
    }

    /**
    * Returns the version of this discussion message.
    *
    * @return the version of this discussion message
    */
    @Override
    public long getVersion() {
        return _discussionMessage.getVersion();
    }

    /**
    * Sets the version of this discussion message.
    *
    * @param version the version of this discussion message
    */
    @Override
    public void setVersion(long version) {
        _discussionMessage.setVersion(version);
    }

    /**
    * Returns the deleted of this discussion message.
    *
    * @return the deleted of this discussion message
    */
    @Override
    public java.util.Date getDeleted() {
        return _discussionMessage.getDeleted();
    }

    /**
    * Sets the deleted of this discussion message.
    *
    * @param deleted the deleted of this discussion message
    */
    @Override
    public void setDeleted(java.util.Date deleted) {
        _discussionMessage.setDeleted(deleted);
    }

    /**
    * Returns the responses count of this discussion message.
    *
    * @return the responses count of this discussion message
    */
    @Override
    public int getResponsesCount() {
        return _discussionMessage.getResponsesCount();
    }

    /**
    * Sets the responses count of this discussion message.
    *
    * @param responsesCount the responses count of this discussion message
    */
    @Override
    public void setResponsesCount(int responsesCount) {
        _discussionMessage.setResponsesCount(responsesCount);
    }

    /**
    * Returns the last activity date of this discussion message.
    *
    * @return the last activity date of this discussion message
    */
    @Override
    public java.util.Date getLastActivityDate() {
        return _discussionMessage.getLastActivityDate();
    }

    /**
    * Sets the last activity date of this discussion message.
    *
    * @param lastActivityDate the last activity date of this discussion message
    */
    @Override
    public void setLastActivityDate(java.util.Date lastActivityDate) {
        _discussionMessage.setLastActivityDate(lastActivityDate);
    }

    /**
    * Returns the last activity author ID of this discussion message.
    *
    * @return the last activity author ID of this discussion message
    */
    @Override
    public long getLastActivityAuthorId() {
        return _discussionMessage.getLastActivityAuthorId();
    }

    /**
    * Sets the last activity author ID of this discussion message.
    *
    * @param lastActivityAuthorId the last activity author ID of this discussion message
    */
    @Override
    public void setLastActivityAuthorId(long lastActivityAuthorId) {
        _discussionMessage.setLastActivityAuthorId(lastActivityAuthorId);
    }

    @Override
    public boolean isNew() {
        return _discussionMessage.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _discussionMessage.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _discussionMessage.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _discussionMessage.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _discussionMessage.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _discussionMessage.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _discussionMessage.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _discussionMessage.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _discussionMessage.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _discussionMessage.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _discussionMessage.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DiscussionMessageWrapper((DiscussionMessage) _discussionMessage.clone());
    }

    @Override
    public int compareTo(DiscussionMessage discussionMessage) {
        return _discussionMessage.compareTo(discussionMessage);
    }

    @Override
    public int hashCode() {
        return _discussionMessage.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<DiscussionMessage> toCacheModel() {
        return _discussionMessage.toCacheModel();
    }

    @Override
    public DiscussionMessage toEscapedModel() {
        return new DiscussionMessageWrapper(_discussionMessage.toEscapedModel());
    }

    @Override
    public DiscussionMessage toUnescapedModel() {
        return new DiscussionMessageWrapper(_discussionMessage.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _discussionMessage.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _discussionMessage.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionMessage.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DiscussionMessageWrapper)) {
            return false;
        }

        DiscussionMessageWrapper discussionMessageWrapper = (DiscussionMessageWrapper) obj;

        if (Validator.equals(_discussionMessage,
                    discussionMessageWrapper._discussionMessage)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DiscussionMessage getWrappedDiscussionMessage() {
        return _discussionMessage;
    }

    @Override
    public DiscussionMessage getWrappedModel() {
        return _discussionMessage;
    }

    @Override
    public void resetOriginalValues() {
        _discussionMessage.resetOriginalValues();
    }
}
