package com.ext.portlet.messaging.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingUserPreferences}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingUserPreferences
 * @generated
 */
public class MessagingUserPreferencesWrapper implements MessagingUserPreferences,
    ModelWrapper<MessagingUserPreferences> {
    private MessagingUserPreferences _messagingUserPreferences;

    public MessagingUserPreferencesWrapper(
        MessagingUserPreferences messagingUserPreferences) {
        _messagingUserPreferences = messagingUserPreferences;
    }

    public Class<?> getModelClass() {
        return MessagingUserPreferences.class;
    }

    public String getModelClassName() {
        return MessagingUserPreferences.class.getName();
    }

    /**
    * Returns the primary key of this messaging user preferences.
    *
    * @return the primary key of this messaging user preferences
    */
    public java.lang.Long getPrimaryKey() {
        return _messagingUserPreferences.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging user preferences.
    *
    * @param primaryKey the primary key of this messaging user preferences
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _messagingUserPreferences.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the messaging preferences ID of this messaging user preferences.
    *
    * @return the messaging preferences ID of this messaging user preferences
    */
    public java.lang.Long getMessagingPreferencesId() {
        return _messagingUserPreferences.getMessagingPreferencesId();
    }

    /**
    * Sets the messaging preferences ID of this messaging user preferences.
    *
    * @param messagingPreferencesId the messaging preferences ID of this messaging user preferences
    */
    public void setMessagingPreferencesId(java.lang.Long messagingPreferencesId) {
        _messagingUserPreferences.setMessagingPreferencesId(messagingPreferencesId);
    }

    /**
    * Returns the user ID of this messaging user preferences.
    *
    * @return the user ID of this messaging user preferences
    */
    public java.lang.Long getUserId() {
        return _messagingUserPreferences.getUserId();
    }

    /**
    * Sets the user ID of this messaging user preferences.
    *
    * @param userId the user ID of this messaging user preferences
    */
    public void setUserId(java.lang.Long userId) {
        _messagingUserPreferences.setUserId(userId);
    }

    /**
    * Returns the email on send of this messaging user preferences.
    *
    * @return the email on send of this messaging user preferences
    */
    public java.lang.Boolean getEmailOnSend() {
        return _messagingUserPreferences.getEmailOnSend();
    }

    /**
    * Sets the email on send of this messaging user preferences.
    *
    * @param emailOnSend the email on send of this messaging user preferences
    */
    public void setEmailOnSend(java.lang.Boolean emailOnSend) {
        _messagingUserPreferences.setEmailOnSend(emailOnSend);
    }

    /**
    * Returns the email on receipt of this messaging user preferences.
    *
    * @return the email on receipt of this messaging user preferences
    */
    public java.lang.Boolean getEmailOnReceipt() {
        return _messagingUserPreferences.getEmailOnReceipt();
    }

    /**
    * Sets the email on receipt of this messaging user preferences.
    *
    * @param emailOnReceipt the email on receipt of this messaging user preferences
    */
    public void setEmailOnReceipt(java.lang.Boolean emailOnReceipt) {
        _messagingUserPreferences.setEmailOnReceipt(emailOnReceipt);
    }

    /**
    * Returns the email on activity of this messaging user preferences.
    *
    * @return the email on activity of this messaging user preferences
    */
    public java.lang.Boolean getEmailOnActivity() {
        return _messagingUserPreferences.getEmailOnActivity();
    }

    /**
    * Sets the email on activity of this messaging user preferences.
    *
    * @param emailOnActivity the email on activity of this messaging user preferences
    */
    public void setEmailOnActivity(java.lang.Boolean emailOnActivity) {
        _messagingUserPreferences.setEmailOnActivity(emailOnActivity);
    }

    public boolean isNew() {
        return _messagingUserPreferences.isNew();
    }

    public void setNew(boolean n) {
        _messagingUserPreferences.setNew(n);
    }

    public boolean isCachedModel() {
        return _messagingUserPreferences.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _messagingUserPreferences.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _messagingUserPreferences.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingUserPreferences.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingUserPreferences.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingUserPreferences.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingUserPreferences.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingUserPreferencesWrapper((MessagingUserPreferences) _messagingUserPreferences.clone());
    }

    public int compareTo(MessagingUserPreferences messagingUserPreferences) {
        return _messagingUserPreferences.compareTo(messagingUserPreferences);
    }

    @Override
    public int hashCode() {
        return _messagingUserPreferences.hashCode();
    }

    public com.liferay.portal.model.CacheModel<MessagingUserPreferences> toCacheModel() {
        return _messagingUserPreferences.toCacheModel();
    }

    public MessagingUserPreferences toEscapedModel() {
        return new MessagingUserPreferencesWrapper(_messagingUserPreferences.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingUserPreferences.toString();
    }

    public java.lang.String toXmlString() {
        return _messagingUserPreferences.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingUserPreferences.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public MessagingUserPreferences getWrappedMessagingUserPreferences() {
        return _messagingUserPreferences;
    }

    public MessagingUserPreferences getWrappedModel() {
        return _messagingUserPreferences;
    }

    public void resetOriginalValues() {
        _messagingUserPreferences.resetOriginalValues();
    }
}
