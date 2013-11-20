package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessagingIgnoredRecipients}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingIgnoredRecipients
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

    @Override
    public Class<?> getModelClass() {
        return MessagingIgnoredRecipients.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingIgnoredRecipients.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ignoredRecipientId", getIgnoredRecipientId());
        attributes.put("email", getEmail());
        attributes.put("name", getName());
        attributes.put("userId", getUserId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long ignoredRecipientId = (Long) attributes.get("ignoredRecipientId");

        if (ignoredRecipientId != null) {
            setIgnoredRecipientId(ignoredRecipientId);
        }

        String email = (String) attributes.get("email");

        if (email != null) {
            setEmail(email);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    /**
    * Returns the primary key of this messaging ignored recipients.
    *
    * @return the primary key of this messaging ignored recipients
    */
    @Override
    public long getPrimaryKey() {
        return _messagingIgnoredRecipients.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging ignored recipients.
    *
    * @param primaryKey the primary key of this messaging ignored recipients
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _messagingIgnoredRecipients.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ignored recipient ID of this messaging ignored recipients.
    *
    * @return the ignored recipient ID of this messaging ignored recipients
    */
    @Override
    public long getIgnoredRecipientId() {
        return _messagingIgnoredRecipients.getIgnoredRecipientId();
    }

    /**
    * Sets the ignored recipient ID of this messaging ignored recipients.
    *
    * @param ignoredRecipientId the ignored recipient ID of this messaging ignored recipients
    */
    @Override
    public void setIgnoredRecipientId(long ignoredRecipientId) {
        _messagingIgnoredRecipients.setIgnoredRecipientId(ignoredRecipientId);
    }

    /**
    * Returns the email of this messaging ignored recipients.
    *
    * @return the email of this messaging ignored recipients
    */
    @Override
    public java.lang.String getEmail() {
        return _messagingIgnoredRecipients.getEmail();
    }

    /**
    * Sets the email of this messaging ignored recipients.
    *
    * @param email the email of this messaging ignored recipients
    */
    @Override
    public void setEmail(java.lang.String email) {
        _messagingIgnoredRecipients.setEmail(email);
    }

    /**
    * Returns the name of this messaging ignored recipients.
    *
    * @return the name of this messaging ignored recipients
    */
    @Override
    public java.lang.String getName() {
        return _messagingIgnoredRecipients.getName();
    }

    /**
    * Sets the name of this messaging ignored recipients.
    *
    * @param name the name of this messaging ignored recipients
    */
    @Override
    public void setName(java.lang.String name) {
        _messagingIgnoredRecipients.setName(name);
    }

    /**
    * Returns the user ID of this messaging ignored recipients.
    *
    * @return the user ID of this messaging ignored recipients
    */
    @Override
    public long getUserId() {
        return _messagingIgnoredRecipients.getUserId();
    }

    /**
    * Sets the user ID of this messaging ignored recipients.
    *
    * @param userId the user ID of this messaging ignored recipients
    */
    @Override
    public void setUserId(long userId) {
        _messagingIgnoredRecipients.setUserId(userId);
    }

    /**
    * Returns the user uuid of this messaging ignored recipients.
    *
    * @return the user uuid of this messaging ignored recipients
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingIgnoredRecipients.getUserUuid();
    }

    /**
    * Sets the user uuid of this messaging ignored recipients.
    *
    * @param userUuid the user uuid of this messaging ignored recipients
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _messagingIgnoredRecipients.setUserUuid(userUuid);
    }

    /**
    * Returns the create date of this messaging ignored recipients.
    *
    * @return the create date of this messaging ignored recipients
    */
    @Override
    public java.util.Date getCreateDate() {
        return _messagingIgnoredRecipients.getCreateDate();
    }

    /**
    * Sets the create date of this messaging ignored recipients.
    *
    * @param createDate the create date of this messaging ignored recipients
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _messagingIgnoredRecipients.setCreateDate(createDate);
    }

    @Override
    public boolean isNew() {
        return _messagingIgnoredRecipients.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _messagingIgnoredRecipients.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _messagingIgnoredRecipients.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _messagingIgnoredRecipients.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _messagingIgnoredRecipients.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingIgnoredRecipients.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingIgnoredRecipients.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingIgnoredRecipients.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _messagingIgnoredRecipients.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _messagingIgnoredRecipients.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingIgnoredRecipients.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingIgnoredRecipientsWrapper((MessagingIgnoredRecipients) _messagingIgnoredRecipients.clone());
    }

    @Override
    public int compareTo(MessagingIgnoredRecipients messagingIgnoredRecipients) {
        return _messagingIgnoredRecipients.compareTo(messagingIgnoredRecipients);
    }

    @Override
    public int hashCode() {
        return _messagingIgnoredRecipients.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<MessagingIgnoredRecipients> toCacheModel() {
        return _messagingIgnoredRecipients.toCacheModel();
    }

    @Override
    public MessagingIgnoredRecipients toEscapedModel() {
        return new MessagingIgnoredRecipientsWrapper(_messagingIgnoredRecipients.toEscapedModel());
    }

    @Override
    public MessagingIgnoredRecipients toUnescapedModel() {
        return new MessagingIgnoredRecipientsWrapper(_messagingIgnoredRecipients.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingIgnoredRecipients.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _messagingIgnoredRecipients.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingIgnoredRecipients.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessagingIgnoredRecipientsWrapper)) {
            return false;
        }

        MessagingIgnoredRecipientsWrapper messagingIgnoredRecipientsWrapper = (MessagingIgnoredRecipientsWrapper) obj;

        if (Validator.equals(_messagingIgnoredRecipients,
                    messagingIgnoredRecipientsWrapper._messagingIgnoredRecipients)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MessagingIgnoredRecipients getWrappedMessagingIgnoredRecipients() {
        return _messagingIgnoredRecipients;
    }

    @Override
    public MessagingIgnoredRecipients getWrappedModel() {
        return _messagingIgnoredRecipients;
    }

    @Override
    public void resetOriginalValues() {
        _messagingIgnoredRecipients.resetOriginalValues();
    }
}
