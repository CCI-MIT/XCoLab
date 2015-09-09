package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessagingUserPreferences}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingUserPreferences
 * @generated
 */
public class MessagingUserPreferencesWrapper implements MessagingUserPreferences,
    ModelWrapper<MessagingUserPreferences> {
    private MessagingUserPreferences _messagingUserPreferences;

    public MessagingUserPreferencesWrapper(
        MessagingUserPreferences messagingUserPreferences) {
        _messagingUserPreferences = messagingUserPreferences;
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingUserPreferences.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingUserPreferences.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("messagingPreferencesId", getMessagingPreferencesId());
        attributes.put("userId", getUserId());
        attributes.put("emailOnSend", getEmailOnSend());
        attributes.put("emailOnReceipt", getEmailOnReceipt());
        attributes.put("emailOnActivity", getEmailOnActivity());
        attributes.put("emailActivityDailyDigest", getEmailActivityDailyDigest());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long messagingPreferencesId = (Long) attributes.get(
                "messagingPreferencesId");

        if (messagingPreferencesId != null) {
            setMessagingPreferencesId(messagingPreferencesId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Boolean emailOnSend = (Boolean) attributes.get("emailOnSend");

        if (emailOnSend != null) {
            setEmailOnSend(emailOnSend);
        }

        Boolean emailOnReceipt = (Boolean) attributes.get("emailOnReceipt");

        if (emailOnReceipt != null) {
            setEmailOnReceipt(emailOnReceipt);
        }

        Boolean emailOnActivity = (Boolean) attributes.get("emailOnActivity");

        if (emailOnActivity != null) {
            setEmailOnActivity(emailOnActivity);
        }

        Boolean emailActivityDailyDigest = (Boolean) attributes.get(
                "emailActivityDailyDigest");

        if (emailActivityDailyDigest != null) {
            setEmailActivityDailyDigest(emailActivityDailyDigest);
        }
    }

    /**
    * Returns the primary key of this messaging user preferences.
    *
    * @return the primary key of this messaging user preferences
    */
    @Override
    public long getPrimaryKey() {
        return _messagingUserPreferences.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging user preferences.
    *
    * @param primaryKey the primary key of this messaging user preferences
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _messagingUserPreferences.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the messaging preferences ID of this messaging user preferences.
    *
    * @return the messaging preferences ID of this messaging user preferences
    */
    @Override
    public long getMessagingPreferencesId() {
        return _messagingUserPreferences.getMessagingPreferencesId();
    }

    /**
    * Sets the messaging preferences ID of this messaging user preferences.
    *
    * @param messagingPreferencesId the messaging preferences ID of this messaging user preferences
    */
    @Override
    public void setMessagingPreferencesId(long messagingPreferencesId) {
        _messagingUserPreferences.setMessagingPreferencesId(messagingPreferencesId);
    }

    /**
    * Returns the user ID of this messaging user preferences.
    *
    * @return the user ID of this messaging user preferences
    */
    @Override
    public long getUserId() {
        return _messagingUserPreferences.getUserId();
    }

    /**
    * Sets the user ID of this messaging user preferences.
    *
    * @param userId the user ID of this messaging user preferences
    */
    @Override
    public void setUserId(long userId) {
        _messagingUserPreferences.setUserId(userId);
    }

    /**
    * Returns the user uuid of this messaging user preferences.
    *
    * @return the user uuid of this messaging user preferences
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messagingUserPreferences.getUserUuid();
    }

    /**
    * Sets the user uuid of this messaging user preferences.
    *
    * @param userUuid the user uuid of this messaging user preferences
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _messagingUserPreferences.setUserUuid(userUuid);
    }

    /**
    * Returns the email on send of this messaging user preferences.
    *
    * @return the email on send of this messaging user preferences
    */
    @Override
    public boolean getEmailOnSend() {
        return _messagingUserPreferences.getEmailOnSend();
    }

    /**
    * Returns <code>true</code> if this messaging user preferences is email on send.
    *
    * @return <code>true</code> if this messaging user preferences is email on send; <code>false</code> otherwise
    */
    @Override
    public boolean isEmailOnSend() {
        return _messagingUserPreferences.isEmailOnSend();
    }

    /**
    * Sets whether this messaging user preferences is email on send.
    *
    * @param emailOnSend the email on send of this messaging user preferences
    */
    @Override
    public void setEmailOnSend(boolean emailOnSend) {
        _messagingUserPreferences.setEmailOnSend(emailOnSend);
    }

    /**
    * Returns the email on receipt of this messaging user preferences.
    *
    * @return the email on receipt of this messaging user preferences
    */
    @Override
    public boolean getEmailOnReceipt() {
        return _messagingUserPreferences.getEmailOnReceipt();
    }

    /**
    * Returns <code>true</code> if this messaging user preferences is email on receipt.
    *
    * @return <code>true</code> if this messaging user preferences is email on receipt; <code>false</code> otherwise
    */
    @Override
    public boolean isEmailOnReceipt() {
        return _messagingUserPreferences.isEmailOnReceipt();
    }

    /**
    * Sets whether this messaging user preferences is email on receipt.
    *
    * @param emailOnReceipt the email on receipt of this messaging user preferences
    */
    @Override
    public void setEmailOnReceipt(boolean emailOnReceipt) {
        _messagingUserPreferences.setEmailOnReceipt(emailOnReceipt);
    }

    /**
    * Returns the email on activity of this messaging user preferences.
    *
    * @return the email on activity of this messaging user preferences
    */
    @Override
    public boolean getEmailOnActivity() {
        return _messagingUserPreferences.getEmailOnActivity();
    }

    /**
    * Returns <code>true</code> if this messaging user preferences is email on activity.
    *
    * @return <code>true</code> if this messaging user preferences is email on activity; <code>false</code> otherwise
    */
    @Override
    public boolean isEmailOnActivity() {
        return _messagingUserPreferences.isEmailOnActivity();
    }

    /**
    * Sets whether this messaging user preferences is email on activity.
    *
    * @param emailOnActivity the email on activity of this messaging user preferences
    */
    @Override
    public void setEmailOnActivity(boolean emailOnActivity) {
        _messagingUserPreferences.setEmailOnActivity(emailOnActivity);
    }

    /**
    * Returns the email activity daily digest of this messaging user preferences.
    *
    * @return the email activity daily digest of this messaging user preferences
    */
    @Override
    public boolean getEmailActivityDailyDigest() {
        return _messagingUserPreferences.getEmailActivityDailyDigest();
    }

    /**
    * Returns <code>true</code> if this messaging user preferences is email activity daily digest.
    *
    * @return <code>true</code> if this messaging user preferences is email activity daily digest; <code>false</code> otherwise
    */
    @Override
    public boolean isEmailActivityDailyDigest() {
        return _messagingUserPreferences.isEmailActivityDailyDigest();
    }

    /**
    * Sets whether this messaging user preferences is email activity daily digest.
    *
    * @param emailActivityDailyDigest the email activity daily digest of this messaging user preferences
    */
    @Override
    public void setEmailActivityDailyDigest(boolean emailActivityDailyDigest) {
        _messagingUserPreferences.setEmailActivityDailyDigest(emailActivityDailyDigest);
    }

    @Override
    public boolean isNew() {
        return _messagingUserPreferences.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _messagingUserPreferences.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _messagingUserPreferences.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _messagingUserPreferences.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _messagingUserPreferences.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingUserPreferences.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingUserPreferences.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingUserPreferences.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _messagingUserPreferences.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _messagingUserPreferences.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingUserPreferences.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingUserPreferencesWrapper((MessagingUserPreferences) _messagingUserPreferences.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.MessagingUserPreferences messagingUserPreferences) {
        return _messagingUserPreferences.compareTo(messagingUserPreferences);
    }

    @Override
    public int hashCode() {
        return _messagingUserPreferences.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.MessagingUserPreferences> toCacheModel() {
        return _messagingUserPreferences.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.MessagingUserPreferences toEscapedModel() {
        return new MessagingUserPreferencesWrapper(_messagingUserPreferences.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.MessagingUserPreferences toUnescapedModel() {
        return new MessagingUserPreferencesWrapper(_messagingUserPreferences.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingUserPreferences.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _messagingUserPreferences.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingUserPreferences.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessagingUserPreferencesWrapper)) {
            return false;
        }

        MessagingUserPreferencesWrapper messagingUserPreferencesWrapper = (MessagingUserPreferencesWrapper) obj;

        if (Validator.equals(_messagingUserPreferences,
                    messagingUserPreferencesWrapper._messagingUserPreferences)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MessagingUserPreferences getWrappedMessagingUserPreferences() {
        return _messagingUserPreferences;
    }

    @Override
    public MessagingUserPreferences getWrappedModel() {
        return _messagingUserPreferences;
    }

    @Override
    public void resetOriginalValues() {
        _messagingUserPreferences.resetOriginalValues();
    }
}
