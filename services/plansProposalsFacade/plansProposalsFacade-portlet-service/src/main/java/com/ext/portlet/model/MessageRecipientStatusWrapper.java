package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessageRecipientStatus}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessageRecipientStatus
 * @generated
 */
public class MessageRecipientStatusWrapper implements MessageRecipientStatus,
    ModelWrapper<MessageRecipientStatus> {
    private MessageRecipientStatus _messageRecipientStatus;

    public MessageRecipientStatusWrapper(
        MessageRecipientStatus messageRecipientStatus) {
        _messageRecipientStatus = messageRecipientStatus;
    }

    public Class<?> getModelClass() {
        return MessageRecipientStatus.class;
    }

    public String getModelClassName() {
        return MessageRecipientStatus.class.getName();
    }

    /**
    * Returns the primary key of this message recipient status.
    *
    * @return the primary key of this message recipient status
    */
    public long getPrimaryKey() {
        return _messageRecipientStatus.getPrimaryKey();
    }

    /**
    * Sets the primary key of this message recipient status.
    *
    * @param primaryKey the primary key of this message recipient status
    */
    public void setPrimaryKey(long primaryKey) {
        _messageRecipientStatus.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the message recipient ID of this message recipient status.
    *
    * @return the message recipient ID of this message recipient status
    */
    public long getMessageRecipientId() {
        return _messageRecipientStatus.getMessageRecipientId();
    }

    /**
    * Sets the message recipient ID of this message recipient status.
    *
    * @param messageRecipientId the message recipient ID of this message recipient status
    */
    public void setMessageRecipientId(long messageRecipientId) {
        _messageRecipientStatus.setMessageRecipientId(messageRecipientId);
    }

    /**
    * Returns the message ID of this message recipient status.
    *
    * @return the message ID of this message recipient status
    */
    public long getMessageId() {
        return _messageRecipientStatus.getMessageId();
    }

    /**
    * Sets the message ID of this message recipient status.
    *
    * @param messageId the message ID of this message recipient status
    */
    public void setMessageId(long messageId) {
        _messageRecipientStatus.setMessageId(messageId);
    }

    /**
    * Returns the user ID of this message recipient status.
    *
    * @return the user ID of this message recipient status
    */
    public long getUserId() {
        return _messageRecipientStatus.getUserId();
    }

    /**
    * Sets the user ID of this message recipient status.
    *
    * @param userId the user ID of this message recipient status
    */
    public void setUserId(long userId) {
        _messageRecipientStatus.setUserId(userId);
    }

    /**
    * Returns the user uuid of this message recipient status.
    *
    * @return the user uuid of this message recipient status
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatus.getUserUuid();
    }

    /**
    * Sets the user uuid of this message recipient status.
    *
    * @param userUuid the user uuid of this message recipient status
    */
    public void setUserUuid(java.lang.String userUuid) {
        _messageRecipientStatus.setUserUuid(userUuid);
    }

    /**
    * Returns the opened of this message recipient status.
    *
    * @return the opened of this message recipient status
    */
    public boolean getOpened() {
        return _messageRecipientStatus.getOpened();
    }

    /**
    * Returns <code>true</code> if this message recipient status is opened.
    *
    * @return <code>true</code> if this message recipient status is opened; <code>false</code> otherwise
    */
    public boolean isOpened() {
        return _messageRecipientStatus.isOpened();
    }

    /**
    * Sets whether this message recipient status is opened.
    *
    * @param opened the opened of this message recipient status
    */
    public void setOpened(boolean opened) {
        _messageRecipientStatus.setOpened(opened);
    }

    /**
    * Returns the archived of this message recipient status.
    *
    * @return the archived of this message recipient status
    */
    public boolean getArchived() {
        return _messageRecipientStatus.getArchived();
    }

    /**
    * Returns <code>true</code> if this message recipient status is archived.
    *
    * @return <code>true</code> if this message recipient status is archived; <code>false</code> otherwise
    */
    public boolean isArchived() {
        return _messageRecipientStatus.isArchived();
    }

    /**
    * Sets whether this message recipient status is archived.
    *
    * @param archived the archived of this message recipient status
    */
    public void setArchived(boolean archived) {
        _messageRecipientStatus.setArchived(archived);
    }

    public boolean isNew() {
        return _messageRecipientStatus.isNew();
    }

    public void setNew(boolean n) {
        _messageRecipientStatus.setNew(n);
    }

    public boolean isCachedModel() {
        return _messageRecipientStatus.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _messageRecipientStatus.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _messageRecipientStatus.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _messageRecipientStatus.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messageRecipientStatus.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messageRecipientStatus.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messageRecipientStatus.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessageRecipientStatusWrapper((MessageRecipientStatus) _messageRecipientStatus.clone());
    }

    public int compareTo(MessageRecipientStatus messageRecipientStatus) {
        return _messageRecipientStatus.compareTo(messageRecipientStatus);
    }

    @Override
    public int hashCode() {
        return _messageRecipientStatus.hashCode();
    }

    public com.liferay.portal.model.CacheModel<MessageRecipientStatus> toCacheModel() {
        return _messageRecipientStatus.toCacheModel();
    }

    public MessageRecipientStatus toEscapedModel() {
        return new MessageRecipientStatusWrapper(_messageRecipientStatus.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messageRecipientStatus.toString();
    }

    public java.lang.String toXmlString() {
        return _messageRecipientStatus.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messageRecipientStatus.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public MessageRecipientStatus getWrappedMessageRecipientStatus() {
        return _messageRecipientStatus;
    }

    public MessageRecipientStatus getWrappedModel() {
        return _messageRecipientStatus;
    }

    public void resetOriginalValues() {
        _messageRecipientStatus.resetOriginalValues();
    }
}
