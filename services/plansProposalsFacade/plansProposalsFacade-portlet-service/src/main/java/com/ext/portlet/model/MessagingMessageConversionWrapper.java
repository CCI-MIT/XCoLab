package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageConversion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversion
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

    @Override
    public Class<?> getModelClass() {
        return MessagingMessageConversion.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingMessageConversion.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("conversionId", getConversionId());
        attributes.put("conversionTypeId", getConversionTypeId());
        attributes.put("messageId", getMessageId());
        attributes.put("ipAddress", getIpAddress());
        attributes.put("extraData", getExtraData());
        attributes.put("extraData2", getExtraData2());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long conversionId = (Long) attributes.get("conversionId");

        if (conversionId != null) {
            setConversionId(conversionId);
        }

        Long conversionTypeId = (Long) attributes.get("conversionTypeId");

        if (conversionTypeId != null) {
            setConversionTypeId(conversionTypeId);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        String ipAddress = (String) attributes.get("ipAddress");

        if (ipAddress != null) {
            setIpAddress(ipAddress);
        }

        String extraData = (String) attributes.get("extraData");

        if (extraData != null) {
            setExtraData(extraData);
        }

        String extraData2 = (String) attributes.get("extraData2");

        if (extraData2 != null) {
            setExtraData2(extraData2);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    /**
    * Returns the primary key of this messaging message conversion.
    *
    * @return the primary key of this messaging message conversion
    */
    @Override
    public long getPrimaryKey() {
        return _messagingMessageConversion.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging message conversion.
    *
    * @param primaryKey the primary key of this messaging message conversion
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _messagingMessageConversion.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the conversion ID of this messaging message conversion.
    *
    * @return the conversion ID of this messaging message conversion
    */
    @Override
    public long getConversionId() {
        return _messagingMessageConversion.getConversionId();
    }

    /**
    * Sets the conversion ID of this messaging message conversion.
    *
    * @param conversionId the conversion ID of this messaging message conversion
    */
    @Override
    public void setConversionId(long conversionId) {
        _messagingMessageConversion.setConversionId(conversionId);
    }

    /**
    * Returns the conversion type ID of this messaging message conversion.
    *
    * @return the conversion type ID of this messaging message conversion
    */
    @Override
    public long getConversionTypeId() {
        return _messagingMessageConversion.getConversionTypeId();
    }

    /**
    * Sets the conversion type ID of this messaging message conversion.
    *
    * @param conversionTypeId the conversion type ID of this messaging message conversion
    */
    @Override
    public void setConversionTypeId(long conversionTypeId) {
        _messagingMessageConversion.setConversionTypeId(conversionTypeId);
    }

    /**
    * Returns the message ID of this messaging message conversion.
    *
    * @return the message ID of this messaging message conversion
    */
    @Override
    public long getMessageId() {
        return _messagingMessageConversion.getMessageId();
    }

    /**
    * Sets the message ID of this messaging message conversion.
    *
    * @param messageId the message ID of this messaging message conversion
    */
    @Override
    public void setMessageId(long messageId) {
        _messagingMessageConversion.setMessageId(messageId);
    }

    /**
    * Returns the ip address of this messaging message conversion.
    *
    * @return the ip address of this messaging message conversion
    */
    @Override
    public java.lang.String getIpAddress() {
        return _messagingMessageConversion.getIpAddress();
    }

    /**
    * Sets the ip address of this messaging message conversion.
    *
    * @param ipAddress the ip address of this messaging message conversion
    */
    @Override
    public void setIpAddress(java.lang.String ipAddress) {
        _messagingMessageConversion.setIpAddress(ipAddress);
    }

    /**
    * Returns the extra data of this messaging message conversion.
    *
    * @return the extra data of this messaging message conversion
    */
    @Override
    public java.lang.String getExtraData() {
        return _messagingMessageConversion.getExtraData();
    }

    /**
    * Sets the extra data of this messaging message conversion.
    *
    * @param extraData the extra data of this messaging message conversion
    */
    @Override
    public void setExtraData(java.lang.String extraData) {
        _messagingMessageConversion.setExtraData(extraData);
    }

    /**
    * Returns the extra data2 of this messaging message conversion.
    *
    * @return the extra data2 of this messaging message conversion
    */
    @Override
    public java.lang.String getExtraData2() {
        return _messagingMessageConversion.getExtraData2();
    }

    /**
    * Sets the extra data2 of this messaging message conversion.
    *
    * @param extraData2 the extra data2 of this messaging message conversion
    */
    @Override
    public void setExtraData2(java.lang.String extraData2) {
        _messagingMessageConversion.setExtraData2(extraData2);
    }

    /**
    * Returns the create date of this messaging message conversion.
    *
    * @return the create date of this messaging message conversion
    */
    @Override
    public java.util.Date getCreateDate() {
        return _messagingMessageConversion.getCreateDate();
    }

    /**
    * Sets the create date of this messaging message conversion.
    *
    * @param createDate the create date of this messaging message conversion
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _messagingMessageConversion.setCreateDate(createDate);
    }

    @Override
    public boolean isNew() {
        return _messagingMessageConversion.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _messagingMessageConversion.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _messagingMessageConversion.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _messagingMessageConversion.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _messagingMessageConversion.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingMessageConversion.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingMessageConversion.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingMessageConversion.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _messagingMessageConversion.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _messagingMessageConversion.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingMessageConversion.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingMessageConversionWrapper((MessagingMessageConversion) _messagingMessageConversion.clone());
    }

    @Override
    public int compareTo(MessagingMessageConversion messagingMessageConversion) {
        return _messagingMessageConversion.compareTo(messagingMessageConversion);
    }

    @Override
    public int hashCode() {
        return _messagingMessageConversion.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<MessagingMessageConversion> toCacheModel() {
        return _messagingMessageConversion.toCacheModel();
    }

    @Override
    public MessagingMessageConversion toEscapedModel() {
        return new MessagingMessageConversionWrapper(_messagingMessageConversion.toEscapedModel());
    }

    @Override
    public MessagingMessageConversion toUnescapedModel() {
        return new MessagingMessageConversionWrapper(_messagingMessageConversion.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingMessageConversion.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _messagingMessageConversion.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageConversion.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessagingMessageConversionWrapper)) {
            return false;
        }

        MessagingMessageConversionWrapper messagingMessageConversionWrapper = (MessagingMessageConversionWrapper) obj;

        if (Validator.equals(_messagingMessageConversion,
                    messagingMessageConversionWrapper._messagingMessageConversion)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MessagingMessageConversion getWrappedMessagingMessageConversion() {
        return _messagingMessageConversion;
    }

    @Override
    public MessagingMessageConversion getWrappedModel() {
        return _messagingMessageConversion;
    }

    @Override
    public void resetOriginalValues() {
        _messagingMessageConversion.resetOriginalValues();
    }
}
