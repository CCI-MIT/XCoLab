package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessagingRedirectLink}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingRedirectLink
 * @generated
 */
public class MessagingRedirectLinkWrapper implements MessagingRedirectLink,
    ModelWrapper<MessagingRedirectLink> {
    private MessagingRedirectLink _messagingRedirectLink;

    public MessagingRedirectLinkWrapper(
        MessagingRedirectLink messagingRedirectLink) {
        _messagingRedirectLink = messagingRedirectLink;
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingRedirectLink.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingRedirectLink.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("redirectId", getRedirectId());
        attributes.put("link", getLink());
        attributes.put("messageId", getMessageId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long redirectId = (Long) attributes.get("redirectId");

        if (redirectId != null) {
            setRedirectId(redirectId);
        }

        String link = (String) attributes.get("link");

        if (link != null) {
            setLink(link);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    /**
    * Returns the primary key of this messaging redirect link.
    *
    * @return the primary key of this messaging redirect link
    */
    @Override
    public long getPrimaryKey() {
        return _messagingRedirectLink.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging redirect link.
    *
    * @param primaryKey the primary key of this messaging redirect link
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _messagingRedirectLink.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the redirect ID of this messaging redirect link.
    *
    * @return the redirect ID of this messaging redirect link
    */
    @Override
    public long getRedirectId() {
        return _messagingRedirectLink.getRedirectId();
    }

    /**
    * Sets the redirect ID of this messaging redirect link.
    *
    * @param redirectId the redirect ID of this messaging redirect link
    */
    @Override
    public void setRedirectId(long redirectId) {
        _messagingRedirectLink.setRedirectId(redirectId);
    }

    /**
    * Returns the link of this messaging redirect link.
    *
    * @return the link of this messaging redirect link
    */
    @Override
    public java.lang.String getLink() {
        return _messagingRedirectLink.getLink();
    }

    /**
    * Sets the link of this messaging redirect link.
    *
    * @param link the link of this messaging redirect link
    */
    @Override
    public void setLink(java.lang.String link) {
        _messagingRedirectLink.setLink(link);
    }

    /**
    * Returns the message ID of this messaging redirect link.
    *
    * @return the message ID of this messaging redirect link
    */
    @Override
    public long getMessageId() {
        return _messagingRedirectLink.getMessageId();
    }

    /**
    * Sets the message ID of this messaging redirect link.
    *
    * @param messageId the message ID of this messaging redirect link
    */
    @Override
    public void setMessageId(long messageId) {
        _messagingRedirectLink.setMessageId(messageId);
    }

    /**
    * Returns the create date of this messaging redirect link.
    *
    * @return the create date of this messaging redirect link
    */
    @Override
    public java.util.Date getCreateDate() {
        return _messagingRedirectLink.getCreateDate();
    }

    /**
    * Sets the create date of this messaging redirect link.
    *
    * @param createDate the create date of this messaging redirect link
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _messagingRedirectLink.setCreateDate(createDate);
    }

    @Override
    public boolean isNew() {
        return _messagingRedirectLink.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _messagingRedirectLink.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _messagingRedirectLink.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _messagingRedirectLink.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _messagingRedirectLink.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingRedirectLink.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingRedirectLink.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingRedirectLink.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _messagingRedirectLink.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _messagingRedirectLink.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingRedirectLink.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingRedirectLinkWrapper((MessagingRedirectLink) _messagingRedirectLink.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.MessagingRedirectLink messagingRedirectLink) {
        return _messagingRedirectLink.compareTo(messagingRedirectLink);
    }

    @Override
    public int hashCode() {
        return _messagingRedirectLink.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.MessagingRedirectLink> toCacheModel() {
        return _messagingRedirectLink.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.MessagingRedirectLink toEscapedModel() {
        return new MessagingRedirectLinkWrapper(_messagingRedirectLink.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.MessagingRedirectLink toUnescapedModel() {
        return new MessagingRedirectLinkWrapper(_messagingRedirectLink.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingRedirectLink.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _messagingRedirectLink.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingRedirectLink.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessagingRedirectLinkWrapper)) {
            return false;
        }

        MessagingRedirectLinkWrapper messagingRedirectLinkWrapper = (MessagingRedirectLinkWrapper) obj;

        if (Validator.equals(_messagingRedirectLink,
                    messagingRedirectLinkWrapper._messagingRedirectLink)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MessagingRedirectLink getWrappedMessagingRedirectLink() {
        return _messagingRedirectLink;
    }

    @Override
    public MessagingRedirectLink getWrappedModel() {
        return _messagingRedirectLink;
    }

    @Override
    public void resetOriginalValues() {
        _messagingRedirectLink.resetOriginalValues();
    }
}
