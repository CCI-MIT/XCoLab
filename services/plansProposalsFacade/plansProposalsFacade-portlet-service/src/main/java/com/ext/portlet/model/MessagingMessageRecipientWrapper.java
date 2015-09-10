package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageRecipient}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageRecipient
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

    @Override
    public Class<?> getModelClass() {
        return MessagingMessageRecipient.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingMessageRecipient.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("recipientId", getRecipientId());
        attributes.put("messageId", getMessageId());
        attributes.put("userId", getUserId());
        attributes.put("emailAddress", getEmailAddress());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long recipientId = (Long) attributes.get("recipientId");

        if (recipientId != null) {
            setRecipientId(recipientId);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String emailAddress = (String) attributes.get("emailAddress");

        if (emailAddress != null) {
            setEmailAddress(emailAddress);
        }
    }

    /**
    * Returns the primary key of this messaging message recipient.
    *
    * @return the primary key of this messaging message recipient
    */
    @Override
    public long getPrimaryKey() {
        return _messagingMessageRecipient.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging message recipient.
    *
    * @param primaryKey the primary key of this messaging message recipient
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _messagingMessageRecipient.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the recipient ID of this messaging message recipient.
    *
    * @return the recipient ID of this messaging message recipient
    */
    @Override
    public long getRecipientId() {
        return _messagingMessageRecipient.getRecipientId();
    }

    /**
    * Sets the recipient ID of this messaging message recipient.
    *
    * @param recipientId the recipient ID of this messaging message recipient
    */
    @Override
    public void setRecipientId(long recipientId) {
        _messagingMessageRecipient.setRecipientId(recipientId);
    }

    /**
    * Returns the message ID of this messaging message recipient.
    *
    * @return the message ID of this messaging message recipient
    */
    @Override
    public long getMessageId() {
        return _messagingMessageRecipient.getMessageId();
    }

    /**
    * Sets the message ID of this messaging message recipient.
    *
    * @param messageId the message ID of this messaging message recipient
    */
    @Override
    public void setMessageId(long messageId) {
        _messagingMessageRecipient.setMessageId(messageId);
    }

    /**
    * Returns the user ID of this messaging message recipient.
    *
    * @return the user ID of this messaging message recipient
    */
    @Override
    public long getUserId() {
        return _messagingMessageRecipient.getUserId();
    }

    /**
    * Sets the user ID of this messaging message recipient.
    *
    * @param userId the user ID of this messaging message recipient
    */
    @Override
    public void setUserId(long userId) {
        _messagingMessageRecipient.setUserId(userId);
    }

    /**
    * Returns the user uuid of this messaging message recipient.
    *
    * @return the user uuid of this messaging message recipient
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingMessageRecipient.getUserUuid();
    }

    /**
    * Sets the user uuid of this messaging message recipient.
    *
    * @param userUuid the user uuid of this messaging message recipient
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _messagingMessageRecipient.setUserUuid(userUuid);
    }

    /**
    * Returns the email address of this messaging message recipient.
    *
    * @return the email address of this messaging message recipient
    */
    @Override
    public java.lang.String getEmailAddress() {
        return _messagingMessageRecipient.getEmailAddress();
    }

    /**
    * Sets the email address of this messaging message recipient.
    *
    * @param emailAddress the email address of this messaging message recipient
    */
    @Override
    public void setEmailAddress(java.lang.String emailAddress) {
        _messagingMessageRecipient.setEmailAddress(emailAddress);
    }

    @Override
    public boolean isNew() {
        return _messagingMessageRecipient.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _messagingMessageRecipient.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _messagingMessageRecipient.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _messagingMessageRecipient.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _messagingMessageRecipient.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingMessageRecipient.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingMessageRecipient.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingMessageRecipient.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _messagingMessageRecipient.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _messagingMessageRecipient.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingMessageRecipient.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingMessageRecipientWrapper((MessagingMessageRecipient) _messagingMessageRecipient.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient) {
        return _messagingMessageRecipient.compareTo(messagingMessageRecipient);
    }

    @Override
    public int hashCode() {
        return _messagingMessageRecipient.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.MessagingMessageRecipient> toCacheModel() {
        return _messagingMessageRecipient.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.MessagingMessageRecipient toEscapedModel() {
        return new MessagingMessageRecipientWrapper(_messagingMessageRecipient.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.MessagingMessageRecipient toUnescapedModel() {
        return new MessagingMessageRecipientWrapper(_messagingMessageRecipient.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingMessageRecipient.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _messagingMessageRecipient.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageRecipient.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessagingMessageRecipientWrapper)) {
            return false;
        }

        MessagingMessageRecipientWrapper messagingMessageRecipientWrapper = (MessagingMessageRecipientWrapper) obj;

        if (Validator.equals(_messagingMessageRecipient,
                    messagingMessageRecipientWrapper._messagingMessageRecipient)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MessagingMessageRecipient getWrappedMessagingMessageRecipient() {
        return _messagingMessageRecipient;
    }

    @Override
    public MessagingMessageRecipient getWrappedModel() {
        return _messagingMessageRecipient;
    }

    @Override
    public void resetOriginalValues() {
        _messagingMessageRecipient.resetOriginalValues();
    }
}
