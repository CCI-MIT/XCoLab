package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessage
 * @generated
 */
public class MessagingMessageWrapper implements MessagingMessage,
    ModelWrapper<MessagingMessage> {
    private MessagingMessage _messagingMessage;

    public MessagingMessageWrapper(MessagingMessage messagingMessage) {
        _messagingMessage = messagingMessage;
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingMessage.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingMessage.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("messageId", getMessageId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("subject", getSubject());
        attributes.put("body", getBody());
        attributes.put("replyTo", getReplyTo());
        attributes.put("sendToAll", getSendToAll());
        attributes.put("conversionCount", getConversionCount());
        attributes.put("redirectURL", getRedirectURL());
        attributes.put("creatorId", getCreatorId());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String subject = (String) attributes.get("subject");

        if (subject != null) {
            setSubject(subject);
        }

        String body = (String) attributes.get("body");

        if (body != null) {
            setBody(body);
        }

        String replyTo = (String) attributes.get("replyTo");

        if (replyTo != null) {
            setReplyTo(replyTo);
        }

        Boolean sendToAll = (Boolean) attributes.get("sendToAll");

        if (sendToAll != null) {
            setSendToAll(sendToAll);
        }

        Long conversionCount = (Long) attributes.get("conversionCount");

        if (conversionCount != null) {
            setConversionCount(conversionCount);
        }

        String redirectURL = (String) attributes.get("redirectURL");

        if (redirectURL != null) {
            setRedirectURL(redirectURL);
        }

        Long creatorId = (Long) attributes.get("creatorId");

        if (creatorId != null) {
            setCreatorId(creatorId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this messaging message.
    *
    * @return the primary key of this messaging message
    */
    @Override
    public long getPrimaryKey() {
        return _messagingMessage.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging message.
    *
    * @param primaryKey the primary key of this messaging message
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _messagingMessage.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the message ID of this messaging message.
    *
    * @return the message ID of this messaging message
    */
    @Override
    public long getMessageId() {
        return _messagingMessage.getMessageId();
    }

    /**
    * Sets the message ID of this messaging message.
    *
    * @param messageId the message ID of this messaging message
    */
    @Override
    public void setMessageId(long messageId) {
        _messagingMessage.setMessageId(messageId);
    }

    /**
    * Returns the name of this messaging message.
    *
    * @return the name of this messaging message
    */
    @Override
    public java.lang.String getName() {
        return _messagingMessage.getName();
    }

    /**
    * Sets the name of this messaging message.
    *
    * @param name the name of this messaging message
    */
    @Override
    public void setName(java.lang.String name) {
        _messagingMessage.setName(name);
    }

    /**
    * Returns the description of this messaging message.
    *
    * @return the description of this messaging message
    */
    @Override
    public java.lang.String getDescription() {
        return _messagingMessage.getDescription();
    }

    /**
    * Sets the description of this messaging message.
    *
    * @param description the description of this messaging message
    */
    @Override
    public void setDescription(java.lang.String description) {
        _messagingMessage.setDescription(description);
    }

    /**
    * Returns the subject of this messaging message.
    *
    * @return the subject of this messaging message
    */
    @Override
    public java.lang.String getSubject() {
        return _messagingMessage.getSubject();
    }

    /**
    * Sets the subject of this messaging message.
    *
    * @param subject the subject of this messaging message
    */
    @Override
    public void setSubject(java.lang.String subject) {
        _messagingMessage.setSubject(subject);
    }

    /**
    * Returns the body of this messaging message.
    *
    * @return the body of this messaging message
    */
    @Override
    public java.lang.String getBody() {
        return _messagingMessage.getBody();
    }

    /**
    * Sets the body of this messaging message.
    *
    * @param body the body of this messaging message
    */
    @Override
    public void setBody(java.lang.String body) {
        _messagingMessage.setBody(body);
    }

    /**
    * Returns the reply to of this messaging message.
    *
    * @return the reply to of this messaging message
    */
    @Override
    public java.lang.String getReplyTo() {
        return _messagingMessage.getReplyTo();
    }

    /**
    * Sets the reply to of this messaging message.
    *
    * @param replyTo the reply to of this messaging message
    */
    @Override
    public void setReplyTo(java.lang.String replyTo) {
        _messagingMessage.setReplyTo(replyTo);
    }

    /**
    * Returns the send to all of this messaging message.
    *
    * @return the send to all of this messaging message
    */
    @Override
    public boolean getSendToAll() {
        return _messagingMessage.getSendToAll();
    }

    /**
    * Returns <code>true</code> if this messaging message is send to all.
    *
    * @return <code>true</code> if this messaging message is send to all; <code>false</code> otherwise
    */
    @Override
    public boolean isSendToAll() {
        return _messagingMessage.isSendToAll();
    }

    /**
    * Sets whether this messaging message is send to all.
    *
    * @param sendToAll the send to all of this messaging message
    */
    @Override
    public void setSendToAll(boolean sendToAll) {
        _messagingMessage.setSendToAll(sendToAll);
    }

    /**
    * Returns the conversion count of this messaging message.
    *
    * @return the conversion count of this messaging message
    */
    @Override
    public long getConversionCount() {
        return _messagingMessage.getConversionCount();
    }

    /**
    * Sets the conversion count of this messaging message.
    *
    * @param conversionCount the conversion count of this messaging message
    */
    @Override
    public void setConversionCount(long conversionCount) {
        _messagingMessage.setConversionCount(conversionCount);
    }

    /**
    * Returns the redirect u r l of this messaging message.
    *
    * @return the redirect u r l of this messaging message
    */
    @Override
    public java.lang.String getRedirectURL() {
        return _messagingMessage.getRedirectURL();
    }

    /**
    * Sets the redirect u r l of this messaging message.
    *
    * @param redirectURL the redirect u r l of this messaging message
    */
    @Override
    public void setRedirectURL(java.lang.String redirectURL) {
        _messagingMessage.setRedirectURL(redirectURL);
    }

    /**
    * Returns the creator ID of this messaging message.
    *
    * @return the creator ID of this messaging message
    */
    @Override
    public long getCreatorId() {
        return _messagingMessage.getCreatorId();
    }

    /**
    * Sets the creator ID of this messaging message.
    *
    * @param creatorId the creator ID of this messaging message
    */
    @Override
    public void setCreatorId(long creatorId) {
        _messagingMessage.setCreatorId(creatorId);
    }

    /**
    * Returns the create date of this messaging message.
    *
    * @return the create date of this messaging message
    */
    @Override
    public java.util.Date getCreateDate() {
        return _messagingMessage.getCreateDate();
    }

    /**
    * Sets the create date of this messaging message.
    *
    * @param createDate the create date of this messaging message
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _messagingMessage.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this messaging message.
    *
    * @return the modified date of this messaging message
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _messagingMessage.getModifiedDate();
    }

    /**
    * Sets the modified date of this messaging message.
    *
    * @param modifiedDate the modified date of this messaging message
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _messagingMessage.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _messagingMessage.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _messagingMessage.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _messagingMessage.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _messagingMessage.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _messagingMessage.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingMessage.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingMessage.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingMessage.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _messagingMessage.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _messagingMessage.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingMessage.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingMessageWrapper((MessagingMessage) _messagingMessage.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.MessagingMessage messagingMessage) {
        return _messagingMessage.compareTo(messagingMessage);
    }

    @Override
    public int hashCode() {
        return _messagingMessage.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.MessagingMessage> toCacheModel() {
        return _messagingMessage.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.MessagingMessage toEscapedModel() {
        return new MessagingMessageWrapper(_messagingMessage.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.MessagingMessage toUnescapedModel() {
        return new MessagingMessageWrapper(_messagingMessage.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingMessage.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _messagingMessage.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessage.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessagingMessageWrapper)) {
            return false;
        }

        MessagingMessageWrapper messagingMessageWrapper = (MessagingMessageWrapper) obj;

        if (Validator.equals(_messagingMessage,
                    messagingMessageWrapper._messagingMessage)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MessagingMessage getWrappedMessagingMessage() {
        return _messagingMessage;
    }

    @Override
    public MessagingMessage getWrappedModel() {
        return _messagingMessage;
    }

    @Override
    public void resetOriginalValues() {
        _messagingMessage.resetOriginalValues();
    }
}
