package com.ext.portlet.messaging.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Message}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Message
 * @generated
 */
public class MessageWrapper implements Message, ModelWrapper<Message> {
    private Message _message;

    public MessageWrapper(Message message) {
        _message = message;
    }

    public Class<?> getModelClass() {
        return Message.class;
    }

    public String getModelClassName() {
        return Message.class.getName();
    }

    /**
    * Returns the primary key of this message.
    *
    * @return the primary key of this message
    */
    public java.lang.Long getPrimaryKey() {
        return _message.getPrimaryKey();
    }

    /**
    * Sets the primary key of this message.
    *
    * @param primaryKey the primary key of this message
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _message.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the message ID of this message.
    *
    * @return the message ID of this message
    */
    public java.lang.Long getMessageId() {
        return _message.getMessageId();
    }

    /**
    * Sets the message ID of this message.
    *
    * @param messageId the message ID of this message
    */
    public void setMessageId(java.lang.Long messageId) {
        _message.setMessageId(messageId);
    }

    /**
    * Returns the from ID of this message.
    *
    * @return the from ID of this message
    */
    public java.lang.Long getFromId() {
        return _message.getFromId();
    }

    /**
    * Sets the from ID of this message.
    *
    * @param fromId the from ID of this message
    */
    public void setFromId(java.lang.Long fromId) {
        _message.setFromId(fromId);
    }

    /**
    * Returns the replies to of this message.
    *
    * @return the replies to of this message
    */
    public java.lang.Long getRepliesTo() {
        return _message.getRepliesTo();
    }

    /**
    * Sets the replies to of this message.
    *
    * @param repliesTo the replies to of this message
    */
    public void setRepliesTo(java.lang.Long repliesTo) {
        _message.setRepliesTo(repliesTo);
    }

    /**
    * Returns the create date of this message.
    *
    * @return the create date of this message
    */
    public java.util.Date getCreateDate() {
        return _message.getCreateDate();
    }

    /**
    * Sets the create date of this message.
    *
    * @param createDate the create date of this message
    */
    public void setCreateDate(java.util.Date createDate) {
        _message.setCreateDate(createDate);
    }

    /**
    * Returns the subject of this message.
    *
    * @return the subject of this message
    */
    public java.lang.String getSubject() {
        return _message.getSubject();
    }

    /**
    * Sets the subject of this message.
    *
    * @param subject the subject of this message
    */
    public void setSubject(java.lang.String subject) {
        _message.setSubject(subject);
    }

    /**
    * Returns the content of this message.
    *
    * @return the content of this message
    */
    public java.lang.String getContent() {
        return _message.getContent();
    }

    /**
    * Sets the content of this message.
    *
    * @param content the content of this message
    */
    public void setContent(java.lang.String content) {
        _message.setContent(content);
    }

    public boolean isNew() {
        return _message.isNew();
    }

    public void setNew(boolean n) {
        _message.setNew(n);
    }

    public boolean isCachedModel() {
        return _message.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _message.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _message.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _message.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _message.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _message.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _message.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessageWrapper((Message) _message.clone());
    }

    public int compareTo(Message message) {
        return _message.compareTo(message);
    }

    @Override
    public int hashCode() {
        return _message.hashCode();
    }

    public com.liferay.portal.model.CacheModel<Message> toCacheModel() {
        return _message.toCacheModel();
    }

    public Message toEscapedModel() {
        return new MessageWrapper(_message.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _message.toString();
    }

    public java.lang.String toXmlString() {
        return _message.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _message.persist();
    }

    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> getRecipients()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _message.getRecipients();
    }

    public boolean hasReciever(long userid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _message.hasReciever(userid);
    }

    public boolean isOpened(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return _message.isOpened(userid);
    }

    public void setOpened(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        _message.setOpened(userid);
    }

    public boolean isArchived(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return _message.isArchived(userid);
    }

    public void setArchived(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        _message.setArchived(userid);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public Message getWrappedMessage() {
        return _message;
    }

    public Message getWrappedModel() {
        return _message;
    }

    public void resetOriginalValues() {
        _message.resetOriginalValues();
    }
}
