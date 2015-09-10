package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessageRecipientStatus}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientStatus
 * @generated
 */
public class MessageRecipientStatusWrapper implements MessageRecipientStatus,
    ModelWrapper<MessageRecipientStatus> {
    private MessageRecipientStatus _messageRecipientStatus;

    public MessageRecipientStatusWrapper(
        MessageRecipientStatus messageRecipientStatus) {
        _messageRecipientStatus = messageRecipientStatus;
    }

    @Override
    public Class<?> getModelClass() {
        return MessageRecipientStatus.class;
    }

    @Override
    public String getModelClassName() {
        return MessageRecipientStatus.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("messageRecipientId", getMessageRecipientId());
        attributes.put("messageId", getMessageId());
        attributes.put("userId", getUserId());
        attributes.put("opened", getOpened());
        attributes.put("archived", getArchived());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long messageRecipientId = (Long) attributes.get("messageRecipientId");

        if (messageRecipientId != null) {
            setMessageRecipientId(messageRecipientId);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Boolean opened = (Boolean) attributes.get("opened");

        if (opened != null) {
            setOpened(opened);
        }

        Boolean archived = (Boolean) attributes.get("archived");

        if (archived != null) {
            setArchived(archived);
        }
    }

    /**
    * Returns the primary key of this message recipient status.
    *
    * @return the primary key of this message recipient status
    */
    @Override
    public long getPrimaryKey() {
        return _messageRecipientStatus.getPrimaryKey();
    }

    /**
    * Sets the primary key of this message recipient status.
    *
    * @param primaryKey the primary key of this message recipient status
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _messageRecipientStatus.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the message recipient ID of this message recipient status.
    *
    * @return the message recipient ID of this message recipient status
    */
    @Override
    public long getMessageRecipientId() {
        return _messageRecipientStatus.getMessageRecipientId();
    }

    /**
    * Sets the message recipient ID of this message recipient status.
    *
    * @param messageRecipientId the message recipient ID of this message recipient status
    */
    @Override
    public void setMessageRecipientId(long messageRecipientId) {
        _messageRecipientStatus.setMessageRecipientId(messageRecipientId);
    }

    /**
    * Returns the message ID of this message recipient status.
    *
    * @return the message ID of this message recipient status
    */
    @Override
    public long getMessageId() {
        return _messageRecipientStatus.getMessageId();
    }

    /**
    * Sets the message ID of this message recipient status.
    *
    * @param messageId the message ID of this message recipient status
    */
    @Override
    public void setMessageId(long messageId) {
        _messageRecipientStatus.setMessageId(messageId);
    }

    /**
    * Returns the user ID of this message recipient status.
    *
    * @return the user ID of this message recipient status
    */
    @Override
    public long getUserId() {
        return _messageRecipientStatus.getUserId();
    }

    /**
    * Sets the user ID of this message recipient status.
    *
    * @param userId the user ID of this message recipient status
    */
    @Override
    public void setUserId(long userId) {
        _messageRecipientStatus.setUserId(userId);
    }

    /**
    * Returns the user uuid of this message recipient status.
    *
    * @return the user uuid of this message recipient status
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatus.getUserUuid();
    }

    /**
    * Sets the user uuid of this message recipient status.
    *
    * @param userUuid the user uuid of this message recipient status
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _messageRecipientStatus.setUserUuid(userUuid);
    }

    /**
    * Returns the opened of this message recipient status.
    *
    * @return the opened of this message recipient status
    */
    @Override
    public boolean getOpened() {
        return _messageRecipientStatus.getOpened();
    }

    /**
    * Returns <code>true</code> if this message recipient status is opened.
    *
    * @return <code>true</code> if this message recipient status is opened; <code>false</code> otherwise
    */
    @Override
    public boolean isOpened() {
        return _messageRecipientStatus.isOpened();
    }

    /**
    * Sets whether this message recipient status is opened.
    *
    * @param opened the opened of this message recipient status
    */
    @Override
    public void setOpened(boolean opened) {
        _messageRecipientStatus.setOpened(opened);
    }

    /**
    * Returns the archived of this message recipient status.
    *
    * @return the archived of this message recipient status
    */
    @Override
    public boolean getArchived() {
        return _messageRecipientStatus.getArchived();
    }

    /**
    * Returns <code>true</code> if this message recipient status is archived.
    *
    * @return <code>true</code> if this message recipient status is archived; <code>false</code> otherwise
    */
    @Override
    public boolean isArchived() {
        return _messageRecipientStatus.isArchived();
    }

    /**
    * Sets whether this message recipient status is archived.
    *
    * @param archived the archived of this message recipient status
    */
    @Override
    public void setArchived(boolean archived) {
        _messageRecipientStatus.setArchived(archived);
    }

    @Override
    public boolean isNew() {
        return _messageRecipientStatus.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _messageRecipientStatus.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _messageRecipientStatus.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _messageRecipientStatus.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _messageRecipientStatus.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _messageRecipientStatus.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messageRecipientStatus.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messageRecipientStatus.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _messageRecipientStatus.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _messageRecipientStatus.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messageRecipientStatus.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessageRecipientStatusWrapper((MessageRecipientStatus) _messageRecipientStatus.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.MessageRecipientStatus messageRecipientStatus) {
        return _messageRecipientStatus.compareTo(messageRecipientStatus);
    }

    @Override
    public int hashCode() {
        return _messageRecipientStatus.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.MessageRecipientStatus> toCacheModel() {
        return _messageRecipientStatus.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.MessageRecipientStatus toEscapedModel() {
        return new MessageRecipientStatusWrapper(_messageRecipientStatus.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.MessageRecipientStatus toUnescapedModel() {
        return new MessageRecipientStatusWrapper(_messageRecipientStatus.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messageRecipientStatus.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _messageRecipientStatus.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messageRecipientStatus.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessageRecipientStatusWrapper)) {
            return false;
        }

        MessageRecipientStatusWrapper messageRecipientStatusWrapper = (MessageRecipientStatusWrapper) obj;

        if (Validator.equals(_messageRecipientStatus,
                    messageRecipientStatusWrapper._messageRecipientStatus)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MessageRecipientStatus getWrappedMessageRecipientStatus() {
        return _messageRecipientStatus;
    }

    @Override
    public MessageRecipientStatus getWrappedModel() {
        return _messageRecipientStatus;
    }

    @Override
    public void resetOriginalValues() {
        _messageRecipientStatus.resetOriginalValues();
    }
}
