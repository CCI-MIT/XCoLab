package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageRecipient}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessageRecipient
 * @generated
 */
public class MessagingMessageRecipientWrapper
    implements MessagingMessageRecipient,
        ModelWrapper<MessagingMessageRecipient> {
    private MessagingMessageRecipient _messagingMessageRecipient;

    public MessagingMessageRecipientWrapper(
        MessagingMessageRecipient messagingMessageRecipient) {
        _messagingMessageRecipient = messagingMessageRecipient;
    }

    public Class<?> getModelClass() {
        return MessagingMessageRecipient.class;
    }

    public String getModelClassName() {
        return MessagingMessageRecipient.class.getName();
    }

    /**
    * Returns the primary key of this messaging message recipient.
    *
    * @return the primary key of this messaging message recipient
    */
    public long getPrimaryKey() {
        return _messagingMessageRecipient.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging message recipient.
    *
    * @param primaryKey the primary key of this messaging message recipient
    */
    public void setPrimaryKey(long primaryKey) {
        _messagingMessageRecipient.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the recipient ID of this messaging message recipient.
    *
    * @return the recipient ID of this messaging message recipient
    */
    public long getRecipientId() {
        return _messagingMessageRecipient.getRecipientId();
    }

    /**
    * Sets the recipient ID of this messaging message recipient.
    *
    * @param recipientId the recipient ID of this messaging message recipient
    */
    public void setRecipientId(long recipientId) {
        _messagingMessageRecipient.setRecipientId(recipientId);
    }

    /**
    * Returns the message ID of this messaging message recipient.
    *
    * @return the message ID of this messaging message recipient
    */
    public long getMessageId() {
        return _messagingMessageRecipient.getMessageId();
    }

    /**
    * Sets the message ID of this messaging message recipient.
    *
    * @param messageId the message ID of this messaging message recipient
    */
    public void setMessageId(long messageId) {
        _messagingMessageRecipient.setMessageId(messageId);
    }

    /**
    * Returns the user ID of this messaging message recipient.
    *
    * @return the user ID of this messaging message recipient
    */
    public long getUserId() {
        return _messagingMessageRecipient.getUserId();
    }

    /**
    * Sets the user ID of this messaging message recipient.
    *
    * @param userId the user ID of this messaging message recipient
    */
    public void setUserId(long userId) {
        _messagingMessageRecipient.setUserId(userId);
    }

    /**
    * Returns the user uuid of this messaging message recipient.
    *
    * @return the user uuid of this messaging message recipient
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipient.getUserUuid();
    }

    /**
    * Sets the user uuid of this messaging message recipient.
    *
    * @param userUuid the user uuid of this messaging message recipient
    */
    public void setUserUuid(java.lang.String userUuid) {
        _messagingMessageRecipient.setUserUuid(userUuid);
    }

    /**
    * Returns the email address of this messaging message recipient.
    *
    * @return the email address of this messaging message recipient
    */
    public java.lang.String getEmailAddress() {
        return _messagingMessageRecipient.getEmailAddress();
    }

    /**
    * Sets the email address of this messaging message recipient.
    *
    * @param emailAddress the email address of this messaging message recipient
    */
    public void setEmailAddress(java.lang.String emailAddress) {
        _messagingMessageRecipient.setEmailAddress(emailAddress);
    }

    public boolean isNew() {
        return _messagingMessageRecipient.isNew();
    }

    public void setNew(boolean n) {
        _messagingMessageRecipient.setNew(n);
    }

    public boolean isCachedModel() {
        return _messagingMessageRecipient.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _messagingMessageRecipient.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _messagingMessageRecipient.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingMessageRecipient.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingMessageRecipient.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingMessageRecipient.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingMessageRecipient.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingMessageRecipientWrapper((MessagingMessageRecipient) _messagingMessageRecipient.clone());
    }

    public int compareTo(MessagingMessageRecipient messagingMessageRecipient) {
        return _messagingMessageRecipient.compareTo(messagingMessageRecipient);
    }

    @Override
    public int hashCode() {
        return _messagingMessageRecipient.hashCode();
    }

    public com.liferay.portal.model.CacheModel<MessagingMessageRecipient> toCacheModel() {
        return _messagingMessageRecipient.toCacheModel();
    }

    public MessagingMessageRecipient toEscapedModel() {
        return new MessagingMessageRecipientWrapper(_messagingMessageRecipient.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingMessageRecipient.toString();
    }

    public java.lang.String toXmlString() {
        return _messagingMessageRecipient.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageRecipient.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public MessagingMessageRecipient getWrappedMessagingMessageRecipient() {
        return _messagingMessageRecipient;
    }

    public MessagingMessageRecipient getWrappedModel() {
        return _messagingMessageRecipient;
    }

    public void resetOriginalValues() {
        _messagingMessageRecipient.resetOriginalValues();
    }
}
