package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingIgnoredRecipients}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingIgnoredRecipients
 * @generated
 */
public class MessagingIgnoredRecipientsWrapper
    implements MessagingIgnoredRecipients,
        ModelWrapper<MessagingIgnoredRecipients> {
    private MessagingIgnoredRecipients _messagingIgnoredRecipients;

    public MessagingIgnoredRecipientsWrapper(
        MessagingIgnoredRecipients messagingIgnoredRecipients) {
        _messagingIgnoredRecipients = messagingIgnoredRecipients;
    }

    public Class<?> getModelClass() {
        return MessagingIgnoredRecipients.class;
    }

    public String getModelClassName() {
        return MessagingIgnoredRecipients.class.getName();
    }

    /**
    * Returns the primary key of this messaging ignored recipients.
    *
    * @return the primary key of this messaging ignored recipients
    */
    public long getPrimaryKey() {
        return _messagingIgnoredRecipients.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging ignored recipients.
    *
    * @param primaryKey the primary key of this messaging ignored recipients
    */
    public void setPrimaryKey(long primaryKey) {
        _messagingIgnoredRecipients.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ignored recipient ID of this messaging ignored recipients.
    *
    * @return the ignored recipient ID of this messaging ignored recipients
    */
    public long getIgnoredRecipientId() {
        return _messagingIgnoredRecipients.getIgnoredRecipientId();
    }

    /**
    * Sets the ignored recipient ID of this messaging ignored recipients.
    *
    * @param ignoredRecipientId the ignored recipient ID of this messaging ignored recipients
    */
    public void setIgnoredRecipientId(long ignoredRecipientId) {
        _messagingIgnoredRecipients.setIgnoredRecipientId(ignoredRecipientId);
    }

    /**
    * Returns the email of this messaging ignored recipients.
    *
    * @return the email of this messaging ignored recipients
    */
    public java.lang.String getEmail() {
        return _messagingIgnoredRecipients.getEmail();
    }

    /**
    * Sets the email of this messaging ignored recipients.
    *
    * @param email the email of this messaging ignored recipients
    */
    public void setEmail(java.lang.String email) {
        _messagingIgnoredRecipients.setEmail(email);
    }

    /**
    * Returns the name of this messaging ignored recipients.
    *
    * @return the name of this messaging ignored recipients
    */
    public java.lang.String getName() {
        return _messagingIgnoredRecipients.getName();
    }

    /**
    * Sets the name of this messaging ignored recipients.
    *
    * @param name the name of this messaging ignored recipients
    */
    public void setName(java.lang.String name) {
        _messagingIgnoredRecipients.setName(name);
    }

    /**
    * Returns the user ID of this messaging ignored recipients.
    *
    * @return the user ID of this messaging ignored recipients
    */
    public long getUserId() {
        return _messagingIgnoredRecipients.getUserId();
    }

    /**
    * Sets the user ID of this messaging ignored recipients.
    *
    * @param userId the user ID of this messaging ignored recipients
    */
    public void setUserId(long userId) {
        _messagingIgnoredRecipients.setUserId(userId);
    }

    /**
    * Returns the user uuid of this messaging ignored recipients.
    *
    * @return the user uuid of this messaging ignored recipients
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingIgnoredRecipients.getUserUuid();
    }

    /**
    * Sets the user uuid of this messaging ignored recipients.
    *
    * @param userUuid the user uuid of this messaging ignored recipients
    */
    public void setUserUuid(java.lang.String userUuid) {
        _messagingIgnoredRecipients.setUserUuid(userUuid);
    }

    /**
    * Returns the create date of this messaging ignored recipients.
    *
    * @return the create date of this messaging ignored recipients
    */
    public java.util.Date getCreateDate() {
        return _messagingIgnoredRecipients.getCreateDate();
    }

    /**
    * Sets the create date of this messaging ignored recipients.
    *
    * @param createDate the create date of this messaging ignored recipients
    */
    public void setCreateDate(java.util.Date createDate) {
        _messagingIgnoredRecipients.setCreateDate(createDate);
    }

    public boolean isNew() {
        return _messagingIgnoredRecipients.isNew();
    }

    public void setNew(boolean n) {
        _messagingIgnoredRecipients.setNew(n);
    }

    public boolean isCachedModel() {
        return _messagingIgnoredRecipients.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _messagingIgnoredRecipients.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _messagingIgnoredRecipients.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingIgnoredRecipients.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingIgnoredRecipients.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingIgnoredRecipients.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingIgnoredRecipients.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingIgnoredRecipientsWrapper((MessagingIgnoredRecipients) _messagingIgnoredRecipients.clone());
    }

    public int compareTo(MessagingIgnoredRecipients messagingIgnoredRecipients) {
        return _messagingIgnoredRecipients.compareTo(messagingIgnoredRecipients);
    }

    @Override
    public int hashCode() {
        return _messagingIgnoredRecipients.hashCode();
    }

    public com.liferay.portal.model.CacheModel<MessagingIgnoredRecipients> toCacheModel() {
        return _messagingIgnoredRecipients.toCacheModel();
    }

    public MessagingIgnoredRecipients toEscapedModel() {
        return new MessagingIgnoredRecipientsWrapper(_messagingIgnoredRecipients.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingIgnoredRecipients.toString();
    }

    public java.lang.String toXmlString() {
        return _messagingIgnoredRecipients.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingIgnoredRecipients.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public MessagingIgnoredRecipients getWrappedMessagingIgnoredRecipients() {
        return _messagingIgnoredRecipients;
    }

    public MessagingIgnoredRecipients getWrappedModel() {
        return _messagingIgnoredRecipients;
    }

    public void resetOriginalValues() {
        _messagingIgnoredRecipients.resetOriginalValues();
    }
}
