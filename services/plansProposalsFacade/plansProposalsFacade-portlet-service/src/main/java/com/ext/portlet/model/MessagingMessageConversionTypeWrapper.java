package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageConversionType}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessageConversionType
 * @generated
 */
public class MessagingMessageConversionTypeWrapper
    implements MessagingMessageConversionType,
        ModelWrapper<MessagingMessageConversionType> {
    private MessagingMessageConversionType _messagingMessageConversionType;

    public MessagingMessageConversionTypeWrapper(
        MessagingMessageConversionType messagingMessageConversionType) {
        _messagingMessageConversionType = messagingMessageConversionType;
    }

    public Class<?> getModelClass() {
        return MessagingMessageConversionType.class;
    }

    public String getModelClassName() {
        return MessagingMessageConversionType.class.getName();
    }

    /**
    * Returns the primary key of this messaging message conversion type.
    *
    * @return the primary key of this messaging message conversion type
    */
    public long getPrimaryKey() {
        return _messagingMessageConversionType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging message conversion type.
    *
    * @param primaryKey the primary key of this messaging message conversion type
    */
    public void setPrimaryKey(long primaryKey) {
        _messagingMessageConversionType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the type ID of this messaging message conversion type.
    *
    * @return the type ID of this messaging message conversion type
    */
    public long getTypeId() {
        return _messagingMessageConversionType.getTypeId();
    }

    /**
    * Sets the type ID of this messaging message conversion type.
    *
    * @param typeId the type ID of this messaging message conversion type
    */
    public void setTypeId(long typeId) {
        _messagingMessageConversionType.setTypeId(typeId);
    }

    /**
    * Returns the name of this messaging message conversion type.
    *
    * @return the name of this messaging message conversion type
    */
    public java.lang.String getName() {
        return _messagingMessageConversionType.getName();
    }

    /**
    * Sets the name of this messaging message conversion type.
    *
    * @param name the name of this messaging message conversion type
    */
    public void setName(java.lang.String name) {
        _messagingMessageConversionType.setName(name);
    }

    /**
    * Returns the description of this messaging message conversion type.
    *
    * @return the description of this messaging message conversion type
    */
    public java.lang.String getDescription() {
        return _messagingMessageConversionType.getDescription();
    }

    /**
    * Sets the description of this messaging message conversion type.
    *
    * @param description the description of this messaging message conversion type
    */
    public void setDescription(java.lang.String description) {
        _messagingMessageConversionType.setDescription(description);
    }

    public boolean isNew() {
        return _messagingMessageConversionType.isNew();
    }

    public void setNew(boolean n) {
        _messagingMessageConversionType.setNew(n);
    }

    public boolean isCachedModel() {
        return _messagingMessageConversionType.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _messagingMessageConversionType.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _messagingMessageConversionType.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingMessageConversionType.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingMessageConversionType.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingMessageConversionType.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingMessageConversionType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingMessageConversionTypeWrapper((MessagingMessageConversionType) _messagingMessageConversionType.clone());
    }

    public int compareTo(
        MessagingMessageConversionType messagingMessageConversionType) {
        return _messagingMessageConversionType.compareTo(messagingMessageConversionType);
    }

    @Override
    public int hashCode() {
        return _messagingMessageConversionType.hashCode();
    }

    public com.liferay.portal.model.CacheModel<MessagingMessageConversionType> toCacheModel() {
        return _messagingMessageConversionType.toCacheModel();
    }

    public MessagingMessageConversionType toEscapedModel() {
        return new MessagingMessageConversionTypeWrapper(_messagingMessageConversionType.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingMessageConversionType.toString();
    }

    public java.lang.String toXmlString() {
        return _messagingMessageConversionType.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageConversionType.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public MessagingMessageConversionType getWrappedMessagingMessageConversionType() {
        return _messagingMessageConversionType;
    }

    public MessagingMessageConversionType getWrappedModel() {
        return _messagingMessageConversionType;
    }

    public void resetOriginalValues() {
        _messagingMessageConversionType.resetOriginalValues();
    }
}
