package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessage}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessage
 * @generated
 */
public class MessagingMessageWrapper implements MessagingMessage,
    ModelWrapper<MessagingMessage> {
    private MessagingMessage _messagingMessage;

    public MessagingMessageWrapper(MessagingMessage messagingMessage) {
        _messagingMessage = messagingMessage;
    }

    public Class<?> getModelClass() {
        return MessagingMessage.class;
    }

    public String getModelClassName() {
        return MessagingMessage.class.getName();
    }

    /**
    * Returns the primary key of this messaging message.
    *
    * @return the primary key of this messaging message
    */
    public long getPrimaryKey() {
        return _messagingMessage.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging message.
    *
    * @param primaryKey the primary key of this messaging message
    */
    public void setPrimaryKey(long primaryKey) {
        _messagingMessage.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the message ID of this messaging message.
    *
    * @return the message ID of this messaging message
    */
    public long getMessageId() {
        return _messagingMessage.getMessageId();
    }

    /**
    * Sets the message ID of this messaging message.
    *
    * @param messageId the message ID of this messaging message
    */
    public void setMessageId(long messageId) {
        _messagingMessage.setMessageId(messageId);
    }

    /**
    * Returns the name of this messaging message.
    *
    * @return the name of this messaging message
    */
    public java.lang.String getName() {
        return _messagingMessage.getName();
    }

    /**
    * Sets the name of this messaging message.
    *
    * @param name the name of this messaging message
    */
    public void setName(java.lang.String name) {
        _messagingMessage.setName(name);
    }

    /**
    * Returns the description of this messaging message.
    *
    * @return the description of this messaging message
    */
    public java.lang.String getDescription() {
        return _messagingMessage.getDescription();
    }

    /**
    * Sets the description of this messaging message.
    *
    * @param description the description of this messaging message
    */
    public void setDescription(java.lang.String description) {
        _messagingMessage.setDescription(description);
    }

    /**
    * Returns the subject of this messaging message.
    *
    * @return the subject of this messaging message
    */
    public java.lang.String getSubject() {
        return _messagingMessage.getSubject();
    }

    /**
    * Sets the subject of this messaging message.
    *
    * @param subject the subject of this messaging message
    */
    public void setSubject(java.lang.String subject) {
        _messagingMessage.setSubject(subject);
    }

    /**
    * Returns the body of this messaging message.
    *
    * @return the body of this messaging message
    */
    public java.lang.String getBody() {
        return _messagingMessage.getBody();
    }

    /**
    * Sets the body of this messaging message.
    *
    * @param body the body of this messaging message
    */
    public void setBody(java.lang.String body) {
        _messagingMessage.setBody(body);
    }

    /**
    * Returns the reply to of this messaging message.
    *
    * @return the reply to of this messaging message
    */
    public java.lang.String getReplyTo() {
        return _messagingMessage.getReplyTo();
    }

    /**
    * Sets the reply to of this messaging message.
    *
    * @param replyTo the reply to of this messaging message
    */
    public void setReplyTo(java.lang.String replyTo) {
        _messagingMessage.setReplyTo(replyTo);
    }

    /**
    * Returns the send to all of this messaging message.
    *
    * @return the send to all of this messaging message
    */
    public boolean getSendToAll() {
        return _messagingMessage.getSendToAll();
    }

    /**
    * Returns <code>true</code> if this messaging message is send to all.
    *
    * @return <code>true</code> if this messaging message is send to all; <code>false</code> otherwise
    */
    public boolean isSendToAll() {
        return _messagingMessage.isSendToAll();
    }

    /**
    * Sets whether this messaging message is send to all.
    *
    * @param sendToAll the send to all of this messaging message
    */
    public void setSendToAll(boolean sendToAll) {
        _messagingMessage.setSendToAll(sendToAll);
    }

    /**
    * Returns the conversion count of this messaging message.
    *
    * @return the conversion count of this messaging message
    */
    public long getConversionCount() {
        return _messagingMessage.getConversionCount();
    }

    /**
    * Sets the conversion count of this messaging message.
    *
    * @param conversionCount the conversion count of this messaging message
    */
    public void setConversionCount(long conversionCount) {
        _messagingMessage.setConversionCount(conversionCount);
    }

    /**
    * Returns the redirect u r l of this messaging message.
    *
    * @return the redirect u r l of this messaging message
    */
    public java.lang.String getRedirectURL() {
        return _messagingMessage.getRedirectURL();
    }

    /**
    * Sets the redirect u r l of this messaging message.
    *
    * @param redirectURL the redirect u r l of this messaging message
    */
    public void setRedirectURL(java.lang.String redirectURL) {
        _messagingMessage.setRedirectURL(redirectURL);
    }

    /**
    * Returns the creator ID of this messaging message.
    *
    * @return the creator ID of this messaging message
    */
    public long getCreatorId() {
        return _messagingMessage.getCreatorId();
    }

    /**
    * Sets the creator ID of this messaging message.
    *
    * @param creatorId the creator ID of this messaging message
    */
    public void setCreatorId(long creatorId) {
        _messagingMessage.setCreatorId(creatorId);
    }

    /**
    * Returns the create date of this messaging message.
    *
    * @return the create date of this messaging message
    */
    public java.util.Date getCreateDate() {
        return _messagingMessage.getCreateDate();
    }

    /**
    * Sets the create date of this messaging message.
    *
    * @param createDate the create date of this messaging message
    */
    public void setCreateDate(java.util.Date createDate) {
        _messagingMessage.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this messaging message.
    *
    * @return the modified date of this messaging message
    */
    public java.util.Date getModifiedDate() {
        return _messagingMessage.getModifiedDate();
    }

    /**
    * Sets the modified date of this messaging message.
    *
    * @param modifiedDate the modified date of this messaging message
    */
    public void setModifiedDate(java.util.Date modifiedDate) {
        _messagingMessage.setModifiedDate(modifiedDate);
    }

    public boolean isNew() {
        return _messagingMessage.isNew();
    }

    public void setNew(boolean n) {
        _messagingMessage.setNew(n);
    }

    public boolean isCachedModel() {
        return _messagingMessage.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _messagingMessage.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _messagingMessage.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingMessage.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingMessage.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingMessage.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingMessage.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingMessageWrapper((MessagingMessage) _messagingMessage.clone());
    }

    public int compareTo(MessagingMessage messagingMessage) {
        return _messagingMessage.compareTo(messagingMessage);
    }

    @Override
    public int hashCode() {
        return _messagingMessage.hashCode();
    }

    public com.liferay.portal.model.CacheModel<MessagingMessage> toCacheModel() {
        return _messagingMessage.toCacheModel();
    }

    public MessagingMessage toEscapedModel() {
        return new MessagingMessageWrapper(_messagingMessage.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingMessage.toString();
    }

    public java.lang.String toXmlString() {
        return _messagingMessage.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessage.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public MessagingMessage getWrappedMessagingMessage() {
        return _messagingMessage;
    }

    public MessagingMessage getWrappedModel() {
        return _messagingMessage;
    }

    public void resetOriginalValues() {
        _messagingMessage.resetOriginalValues();
    }
}
