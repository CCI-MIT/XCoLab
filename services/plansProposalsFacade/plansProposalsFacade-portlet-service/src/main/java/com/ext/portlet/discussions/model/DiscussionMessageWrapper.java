package com.ext.portlet.discussions.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionMessage}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DiscussionMessage
 * @generated
 */
public class DiscussionMessageWrapper implements DiscussionMessage,
    ModelWrapper<DiscussionMessage> {
    private DiscussionMessage _discussionMessage;

    public DiscussionMessageWrapper(DiscussionMessage discussionMessage) {
        _discussionMessage = discussionMessage;
    }

    public Class<?> getModelClass() {
        return DiscussionMessage.class;
    }

    public String getModelClassName() {
        return DiscussionMessage.class.getName();
    }

    /**
    * Returns the primary key of this discussion message.
    *
    * @return the primary key of this discussion message
    */
    public long getPrimaryKey() {
        return _discussionMessage.getPrimaryKey();
    }

    /**
    * Sets the primary key of this discussion message.
    *
    * @param primaryKey the primary key of this discussion message
    */
    public void setPrimaryKey(long primaryKey) {
        _discussionMessage.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pk of this discussion message.
    *
    * @return the pk of this discussion message
    */
    public long getPk() {
        return _discussionMessage.getPk();
    }

    /**
    * Sets the pk of this discussion message.
    *
    * @param pk the pk of this discussion message
    */
    public void setPk(long pk) {
        _discussionMessage.setPk(pk);
    }

    /**
    * Returns the message ID of this discussion message.
    *
    * @return the message ID of this discussion message
    */
    public long getMessageId() {
        return _discussionMessage.getMessageId();
    }

    /**
    * Sets the message ID of this discussion message.
    *
    * @param messageId the message ID of this discussion message
    */
    public void setMessageId(long messageId) {
        _discussionMessage.setMessageId(messageId);
    }

    /**
    * Returns the subject of this discussion message.
    *
    * @return the subject of this discussion message
    */
    public java.lang.String getSubject() {
        return _discussionMessage.getSubject();
    }

    /**
    * Sets the subject of this discussion message.
    *
    * @param subject the subject of this discussion message
    */
    public void setSubject(java.lang.String subject) {
        _discussionMessage.setSubject(subject);
    }

    /**
    * Returns the body of this discussion message.
    *
    * @return the body of this discussion message
    */
    public java.lang.String getBody() {
        return _discussionMessage.getBody();
    }

    /**
    * Sets the body of this discussion message.
    *
    * @param body the body of this discussion message
    */
    public void setBody(java.lang.String body) {
        _discussionMessage.setBody(body);
    }

    /**
    * Returns the thread ID of this discussion message.
    *
    * @return the thread ID of this discussion message
    */
    public long getThreadId() {
        return _discussionMessage.getThreadId();
    }

    /**
    * Sets the thread ID of this discussion message.
    *
    * @param threadId the thread ID of this discussion message
    */
    public void setThreadId(long threadId) {
        _discussionMessage.setThreadId(threadId);
    }

    /**
    * Returns the category ID of this discussion message.
    *
    * @return the category ID of this discussion message
    */
    public long getCategoryId() {
        return _discussionMessage.getCategoryId();
    }

    /**
    * Sets the category ID of this discussion message.
    *
    * @param categoryId the category ID of this discussion message
    */
    public void setCategoryId(long categoryId) {
        _discussionMessage.setCategoryId(categoryId);
    }

    /**
    * Returns the category group ID of this discussion message.
    *
    * @return the category group ID of this discussion message
    */
    public long getCategoryGroupId() {
        return _discussionMessage.getCategoryGroupId();
    }

    /**
    * Sets the category group ID of this discussion message.
    *
    * @param categoryGroupId the category group ID of this discussion message
    */
    public void setCategoryGroupId(long categoryGroupId) {
        _discussionMessage.setCategoryGroupId(categoryGroupId);
    }

    /**
    * Returns the author ID of this discussion message.
    *
    * @return the author ID of this discussion message
    */
    public long getAuthorId() {
        return _discussionMessage.getAuthorId();
    }

    /**
    * Sets the author ID of this discussion message.
    *
    * @param authorId the author ID of this discussion message
    */
    public void setAuthorId(long authorId) {
        _discussionMessage.setAuthorId(authorId);
    }

    /**
    * Returns the create date of this discussion message.
    *
    * @return the create date of this discussion message
    */
    public java.util.Date getCreateDate() {
        return _discussionMessage.getCreateDate();
    }

    /**
    * Sets the create date of this discussion message.
    *
    * @param createDate the create date of this discussion message
    */
    public void setCreateDate(java.util.Date createDate) {
        _discussionMessage.setCreateDate(createDate);
    }

    /**
    * Returns the version of this discussion message.
    *
    * @return the version of this discussion message
    */
    public long getVersion() {
        return _discussionMessage.getVersion();
    }

    /**
    * Sets the version of this discussion message.
    *
    * @param version the version of this discussion message
    */
    public void setVersion(long version) {
        _discussionMessage.setVersion(version);
    }

    /**
    * Returns the deleted of this discussion message.
    *
    * @return the deleted of this discussion message
    */
    public java.util.Date getDeleted() {
        return _discussionMessage.getDeleted();
    }

    /**
    * Sets the deleted of this discussion message.
    *
    * @param deleted the deleted of this discussion message
    */
    public void setDeleted(java.util.Date deleted) {
        _discussionMessage.setDeleted(deleted);
    }

    /**
    * Returns the responses count of this discussion message.
    *
    * @return the responses count of this discussion message
    */
    public int getResponsesCount() {
        return _discussionMessage.getResponsesCount();
    }

    /**
    * Sets the responses count of this discussion message.
    *
    * @param responsesCount the responses count of this discussion message
    */
    public void setResponsesCount(int responsesCount) {
        _discussionMessage.setResponsesCount(responsesCount);
    }

    /**
    * Returns the last activity date of this discussion message.
    *
    * @return the last activity date of this discussion message
    */
    public java.util.Date getLastActivityDate() {
        return _discussionMessage.getLastActivityDate();
    }

    /**
    * Sets the last activity date of this discussion message.
    *
    * @param lastActivityDate the last activity date of this discussion message
    */
    public void setLastActivityDate(java.util.Date lastActivityDate) {
        _discussionMessage.setLastActivityDate(lastActivityDate);
    }

    /**
    * Returns the last activity author ID of this discussion message.
    *
    * @return the last activity author ID of this discussion message
    */
    public long getLastActivityAuthorId() {
        return _discussionMessage.getLastActivityAuthorId();
    }

    /**
    * Sets the last activity author ID of this discussion message.
    *
    * @param lastActivityAuthorId the last activity author ID of this discussion message
    */
    public void setLastActivityAuthorId(long lastActivityAuthorId) {
        _discussionMessage.setLastActivityAuthorId(lastActivityAuthorId);
    }

    public boolean isNew() {
        return _discussionMessage.isNew();
    }

    public void setNew(boolean n) {
        _discussionMessage.setNew(n);
    }

    public boolean isCachedModel() {
        return _discussionMessage.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _discussionMessage.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _discussionMessage.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _discussionMessage.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _discussionMessage.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _discussionMessage.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _discussionMessage.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DiscussionMessageWrapper((DiscussionMessage) _discussionMessage.clone());
    }

    public int compareTo(DiscussionMessage discussionMessage) {
        return _discussionMessage.compareTo(discussionMessage);
    }

    @Override
    public int hashCode() {
        return _discussionMessage.hashCode();
    }

    public com.liferay.portal.model.CacheModel<DiscussionMessage> toCacheModel() {
        return _discussionMessage.toCacheModel();
    }

    public DiscussionMessage toEscapedModel() {
        return new DiscussionMessageWrapper(_discussionMessage.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _discussionMessage.toString();
    }

    public java.lang.String toXmlString() {
        return _discussionMessage.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionMessage.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public DiscussionMessage getWrappedDiscussionMessage() {
        return _discussionMessage;
    }

    public DiscussionMessage getWrappedModel() {
        return _discussionMessage;
    }

    public void resetOriginalValues() {
        _discussionMessage.resetOriginalValues();
    }
}
