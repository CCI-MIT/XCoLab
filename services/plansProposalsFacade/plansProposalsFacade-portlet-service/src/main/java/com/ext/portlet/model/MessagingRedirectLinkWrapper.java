package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingRedirectLink}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingRedirectLink
 * @generated
 */
public class MessagingRedirectLinkWrapper implements MessagingRedirectLink,
    ModelWrapper<MessagingRedirectLink> {
    private MessagingRedirectLink _messagingRedirectLink;

    public MessagingRedirectLinkWrapper(
        MessagingRedirectLink messagingRedirectLink) {
        _messagingRedirectLink = messagingRedirectLink;
    }

    public Class<?> getModelClass() {
        return MessagingRedirectLink.class;
    }

    public String getModelClassName() {
        return MessagingRedirectLink.class.getName();
    }

    /**
    * Returns the primary key of this messaging redirect link.
    *
    * @return the primary key of this messaging redirect link
    */
    public long getPrimaryKey() {
        return _messagingRedirectLink.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging redirect link.
    *
    * @param primaryKey the primary key of this messaging redirect link
    */
    public void setPrimaryKey(long primaryKey) {
        _messagingRedirectLink.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the redirect ID of this messaging redirect link.
    *
    * @return the redirect ID of this messaging redirect link
    */
    public long getRedirectId() {
        return _messagingRedirectLink.getRedirectId();
    }

    /**
    * Sets the redirect ID of this messaging redirect link.
    *
    * @param redirectId the redirect ID of this messaging redirect link
    */
    public void setRedirectId(long redirectId) {
        _messagingRedirectLink.setRedirectId(redirectId);
    }

    /**
    * Returns the link of this messaging redirect link.
    *
    * @return the link of this messaging redirect link
    */
    public java.lang.String getLink() {
        return _messagingRedirectLink.getLink();
    }

    /**
    * Sets the link of this messaging redirect link.
    *
    * @param link the link of this messaging redirect link
    */
    public void setLink(java.lang.String link) {
        _messagingRedirectLink.setLink(link);
    }

    /**
    * Returns the message ID of this messaging redirect link.
    *
    * @return the message ID of this messaging redirect link
    */
    public long getMessageId() {
        return _messagingRedirectLink.getMessageId();
    }

    /**
    * Sets the message ID of this messaging redirect link.
    *
    * @param messageId the message ID of this messaging redirect link
    */
    public void setMessageId(long messageId) {
        _messagingRedirectLink.setMessageId(messageId);
    }

    /**
    * Returns the create date of this messaging redirect link.
    *
    * @return the create date of this messaging redirect link
    */
    public java.util.Date getCreateDate() {
        return _messagingRedirectLink.getCreateDate();
    }

    /**
    * Sets the create date of this messaging redirect link.
    *
    * @param createDate the create date of this messaging redirect link
    */
    public void setCreateDate(java.util.Date createDate) {
        _messagingRedirectLink.setCreateDate(createDate);
    }

    public boolean isNew() {
        return _messagingRedirectLink.isNew();
    }

    public void setNew(boolean n) {
        _messagingRedirectLink.setNew(n);
    }

    public boolean isCachedModel() {
        return _messagingRedirectLink.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _messagingRedirectLink.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _messagingRedirectLink.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingRedirectLink.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingRedirectLink.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingRedirectLink.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingRedirectLink.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingRedirectLinkWrapper((MessagingRedirectLink) _messagingRedirectLink.clone());
    }

    public int compareTo(MessagingRedirectLink messagingRedirectLink) {
        return _messagingRedirectLink.compareTo(messagingRedirectLink);
    }

    @Override
    public int hashCode() {
        return _messagingRedirectLink.hashCode();
    }

    public com.liferay.portal.model.CacheModel<MessagingRedirectLink> toCacheModel() {
        return _messagingRedirectLink.toCacheModel();
    }

    public MessagingRedirectLink toEscapedModel() {
        return new MessagingRedirectLinkWrapper(_messagingRedirectLink.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingRedirectLink.toString();
    }

    public java.lang.String toXmlString() {
        return _messagingRedirectLink.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingRedirectLink.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public MessagingRedirectLink getWrappedMessagingRedirectLink() {
        return _messagingRedirectLink;
    }

    public MessagingRedirectLink getWrappedModel() {
        return _messagingRedirectLink;
    }

    public void resetOriginalValues() {
        _messagingRedirectLink.resetOriginalValues();
    }
}
