package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageConversion}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessageConversion
 * @generated
 */
public class MessagingMessageConversionWrapper
    implements MessagingMessageConversion,
        ModelWrapper<MessagingMessageConversion> {
    private MessagingMessageConversion _messagingMessageConversion;

    public MessagingMessageConversionWrapper(
        MessagingMessageConversion messagingMessageConversion) {
        _messagingMessageConversion = messagingMessageConversion;
    }

    public Class<?> getModelClass() {
        return MessagingMessageConversion.class;
    }

    public String getModelClassName() {
        return MessagingMessageConversion.class.getName();
    }

    /**
    * Returns the primary key of this messaging message conversion.
    *
    * @return the primary key of this messaging message conversion
    */
    public long getPrimaryKey() {
        return _messagingMessageConversion.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging message conversion.
    *
    * @param primaryKey the primary key of this messaging message conversion
    */
    public void setPrimaryKey(long primaryKey) {
        _messagingMessageConversion.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the conversion ID of this messaging message conversion.
    *
    * @return the conversion ID of this messaging message conversion
    */
    public long getConversionId() {
        return _messagingMessageConversion.getConversionId();
    }

    /**
    * Sets the conversion ID of this messaging message conversion.
    *
    * @param conversionId the conversion ID of this messaging message conversion
    */
    public void setConversionId(long conversionId) {
        _messagingMessageConversion.setConversionId(conversionId);
    }

    /**
    * Returns the conversion type ID of this messaging message conversion.
    *
    * @return the conversion type ID of this messaging message conversion
    */
    public long getConversionTypeId() {
        return _messagingMessageConversion.getConversionTypeId();
    }

    /**
    * Sets the conversion type ID of this messaging message conversion.
    *
    * @param conversionTypeId the conversion type ID of this messaging message conversion
    */
    public void setConversionTypeId(long conversionTypeId) {
        _messagingMessageConversion.setConversionTypeId(conversionTypeId);
    }

    /**
    * Returns the message ID of this messaging message conversion.
    *
    * @return the message ID of this messaging message conversion
    */
    public long getMessageId() {
        return _messagingMessageConversion.getMessageId();
    }

    /**
    * Sets the message ID of this messaging message conversion.
    *
    * @param messageId the message ID of this messaging message conversion
    */
    public void setMessageId(long messageId) {
        _messagingMessageConversion.setMessageId(messageId);
    }

    /**
    * Returns the ip address of this messaging message conversion.
    *
    * @return the ip address of this messaging message conversion
    */
    public java.lang.String getIpAddress() {
        return _messagingMessageConversion.getIpAddress();
    }

    /**
    * Sets the ip address of this messaging message conversion.
    *
    * @param ipAddress the ip address of this messaging message conversion
    */
    public void setIpAddress(java.lang.String ipAddress) {
        _messagingMessageConversion.setIpAddress(ipAddress);
    }

    /**
    * Returns the extra data of this messaging message conversion.
    *
    * @return the extra data of this messaging message conversion
    */
    public java.lang.String getExtraData() {
        return _messagingMessageConversion.getExtraData();
    }

    /**
    * Sets the extra data of this messaging message conversion.
    *
    * @param extraData the extra data of this messaging message conversion
    */
    public void setExtraData(java.lang.String extraData) {
        _messagingMessageConversion.setExtraData(extraData);
    }

    /**
    * Returns the extra data2 of this messaging message conversion.
    *
    * @return the extra data2 of this messaging message conversion
    */
    public java.lang.String getExtraData2() {
        return _messagingMessageConversion.getExtraData2();
    }

    /**
    * Sets the extra data2 of this messaging message conversion.
    *
    * @param extraData2 the extra data2 of this messaging message conversion
    */
    public void setExtraData2(java.lang.String extraData2) {
        _messagingMessageConversion.setExtraData2(extraData2);
    }

    /**
    * Returns the create date of this messaging message conversion.
    *
    * @return the create date of this messaging message conversion
    */
    public java.util.Date getCreateDate() {
        return _messagingMessageConversion.getCreateDate();
    }

    /**
    * Sets the create date of this messaging message conversion.
    *
    * @param createDate the create date of this messaging message conversion
    */
    public void setCreateDate(java.util.Date createDate) {
        _messagingMessageConversion.setCreateDate(createDate);
    }

    public boolean isNew() {
        return _messagingMessageConversion.isNew();
    }

    public void setNew(boolean n) {
        _messagingMessageConversion.setNew(n);
    }

    public boolean isCachedModel() {
        return _messagingMessageConversion.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _messagingMessageConversion.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _messagingMessageConversion.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingMessageConversion.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingMessageConversion.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingMessageConversion.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingMessageConversion.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingMessageConversionWrapper((MessagingMessageConversion) _messagingMessageConversion.clone());
    }

    public int compareTo(MessagingMessageConversion messagingMessageConversion) {
        return _messagingMessageConversion.compareTo(messagingMessageConversion);
    }

    @Override
    public int hashCode() {
        return _messagingMessageConversion.hashCode();
    }

    public com.liferay.portal.model.CacheModel<MessagingMessageConversion> toCacheModel() {
        return _messagingMessageConversion.toCacheModel();
    }

    public MessagingMessageConversion toEscapedModel() {
        return new MessagingMessageConversionWrapper(_messagingMessageConversion.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingMessageConversion.toString();
    }

    public java.lang.String toXmlString() {
        return _messagingMessageConversion.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageConversion.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public MessagingMessageConversion getWrappedMessagingMessageConversion() {
        return _messagingMessageConversion;
    }

    public MessagingMessageConversion getWrappedModel() {
        return _messagingMessageConversion;
    }

    public void resetOriginalValues() {
        _messagingMessageConversion.resetOriginalValues();
    }
}
