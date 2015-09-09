package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Message}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Message
 * @generated
 */
public class MessageWrapper implements Message, ModelWrapper<Message> {
    private Message _message;

    public MessageWrapper(Message message) {
        _message = message;
    }

    @Override
    public Class<?> getModelClass() {
        return Message.class;
    }

    @Override
    public String getModelClassName() {
        return Message.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("messageId", getMessageId());
        attributes.put("fromId", getFromId());
        attributes.put("repliesTo", getRepliesTo());
        attributes.put("createDate", getCreateDate());
        attributes.put("subject", getSubject());
        attributes.put("content", getContent());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        Long fromId = (Long) attributes.get("fromId");

        if (fromId != null) {
            setFromId(fromId);
        }

        Long repliesTo = (Long) attributes.get("repliesTo");

        if (repliesTo != null) {
            setRepliesTo(repliesTo);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        String subject = (String) attributes.get("subject");

        if (subject != null) {
            setSubject(subject);
        }

        String content = (String) attributes.get("content");

        if (content != null) {
            setContent(content);
        }
    }

    /**
    * Returns the primary key of this message.
    *
    * @return the primary key of this message
    */
    @Override
    public long getPrimaryKey() {
        return _message.getPrimaryKey();
    }

    /**
    * Sets the primary key of this message.
    *
    * @param primaryKey the primary key of this message
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _message.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the message ID of this message.
    *
    * @return the message ID of this message
    */
    @Override
    public long getMessageId() {
        return _message.getMessageId();
    }

    /**
    * Sets the message ID of this message.
    *
    * @param messageId the message ID of this message
    */
    @Override
    public void setMessageId(long messageId) {
        _message.setMessageId(messageId);
    }

    /**
    * Returns the from ID of this message.
    *
    * @return the from ID of this message
    */
    @Override
    public long getFromId() {
        return _message.getFromId();
    }

    /**
    * Sets the from ID of this message.
    *
    * @param fromId the from ID of this message
    */
    @Override
    public void setFromId(long fromId) {
        _message.setFromId(fromId);
    }

    /**
    * Returns the replies to of this message.
    *
    * @return the replies to of this message
    */
    @Override
    public long getRepliesTo() {
        return _message.getRepliesTo();
    }

    /**
    * Sets the replies to of this message.
    *
    * @param repliesTo the replies to of this message
    */
    @Override
    public void setRepliesTo(long repliesTo) {
        _message.setRepliesTo(repliesTo);
    }

    /**
    * Returns the create date of this message.
    *
    * @return the create date of this message
    */
    @Override
    public java.util.Date getCreateDate() {
        return _message.getCreateDate();
    }

    /**
    * Sets the create date of this message.
    *
    * @param createDate the create date of this message
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _message.setCreateDate(createDate);
    }

    /**
    * Returns the subject of this message.
    *
    * @return the subject of this message
    */
    @Override
    public java.lang.String getSubject() {
        return _message.getSubject();
    }

    /**
    * Sets the subject of this message.
    *
    * @param subject the subject of this message
    */
    @Override
    public void setSubject(java.lang.String subject) {
        _message.setSubject(subject);
    }

    /**
    * Returns the content of this message.
    *
    * @return the content of this message
    */
    @Override
    public java.lang.String getContent() {
        return _message.getContent();
    }

    /**
    * Sets the content of this message.
    *
    * @param content the content of this message
    */
    @Override
    public void setContent(java.lang.String content) {
        _message.setContent(content);
    }

    @Override
    public boolean isNew() {
        return _message.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _message.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _message.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _message.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _message.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _message.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _message.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _message.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _message.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _message.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _message.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessageWrapper((Message) _message.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.Message message) {
        return _message.compareTo(message);
    }

    @Override
    public int hashCode() {
        return _message.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.Message> toCacheModel() {
        return _message.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.Message toEscapedModel() {
        return new MessageWrapper(_message.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.Message toUnescapedModel() {
        return new MessageWrapper(_message.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _message.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _message.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _message.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessageWrapper)) {
            return false;
        }

        MessageWrapper messageWrapper = (MessageWrapper) obj;

        if (Validator.equals(_message, messageWrapper._message)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Message getWrappedMessage() {
        return _message;
    }

    @Override
    public Message getWrappedModel() {
        return _message;
    }

    @Override
    public void resetOriginalValues() {
        _message.resetOriginalValues();
    }
}
