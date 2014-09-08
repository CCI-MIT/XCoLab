package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageConversionType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionType
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

    @Override
    public Class<?> getModelClass() {
        return MessagingMessageConversionType.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingMessageConversionType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("typeId", getTypeId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long typeId = (Long) attributes.get("typeId");

        if (typeId != null) {
            setTypeId(typeId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }
    }

    /**
    * Returns the primary key of this messaging message conversion type.
    *
    * @return the primary key of this messaging message conversion type
    */
    @Override
    public long getPrimaryKey() {
        return _messagingMessageConversionType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this messaging message conversion type.
    *
    * @param primaryKey the primary key of this messaging message conversion type
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _messagingMessageConversionType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the type ID of this messaging message conversion type.
    *
    * @return the type ID of this messaging message conversion type
    */
    @Override
    public long getTypeId() {
        return _messagingMessageConversionType.getTypeId();
    }

    /**
    * Sets the type ID of this messaging message conversion type.
    *
    * @param typeId the type ID of this messaging message conversion type
    */
    @Override
    public void setTypeId(long typeId) {
        _messagingMessageConversionType.setTypeId(typeId);
    }

    /**
    * Returns the name of this messaging message conversion type.
    *
    * @return the name of this messaging message conversion type
    */
    @Override
    public java.lang.String getName() {
        return _messagingMessageConversionType.getName();
    }

    /**
    * Sets the name of this messaging message conversion type.
    *
    * @param name the name of this messaging message conversion type
    */
    @Override
    public void setName(java.lang.String name) {
        _messagingMessageConversionType.setName(name);
    }

    /**
    * Returns the description of this messaging message conversion type.
    *
    * @return the description of this messaging message conversion type
    */
    @Override
    public java.lang.String getDescription() {
        return _messagingMessageConversionType.getDescription();
    }

    /**
    * Sets the description of this messaging message conversion type.
    *
    * @param description the description of this messaging message conversion type
    */
    @Override
    public void setDescription(java.lang.String description) {
        _messagingMessageConversionType.setDescription(description);
    }

    @Override
    public boolean isNew() {
        return _messagingMessageConversionType.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _messagingMessageConversionType.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _messagingMessageConversionType.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _messagingMessageConversionType.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _messagingMessageConversionType.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _messagingMessageConversionType.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _messagingMessageConversionType.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _messagingMessageConversionType.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _messagingMessageConversionType.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _messagingMessageConversionType.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _messagingMessageConversionType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MessagingMessageConversionTypeWrapper((MessagingMessageConversionType) _messagingMessageConversionType.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType) {
        return _messagingMessageConversionType.compareTo(messagingMessageConversionType);
    }

    @Override
    public int hashCode() {
        return _messagingMessageConversionType.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.MessagingMessageConversionType> toCacheModel() {
        return _messagingMessageConversionType.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.MessagingMessageConversionType toEscapedModel() {
        return new MessagingMessageConversionTypeWrapper(_messagingMessageConversionType.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.MessagingMessageConversionType toUnescapedModel() {
        return new MessagingMessageConversionTypeWrapper(_messagingMessageConversionType.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _messagingMessageConversionType.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _messagingMessageConversionType.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _messagingMessageConversionType.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MessagingMessageConversionTypeWrapper)) {
            return false;
        }

        MessagingMessageConversionTypeWrapper messagingMessageConversionTypeWrapper =
            (MessagingMessageConversionTypeWrapper) obj;

        if (Validator.equals(_messagingMessageConversionType,
                    messagingMessageConversionTypeWrapper._messagingMessageConversionType)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MessagingMessageConversionType getWrappedMessagingMessageConversionType() {
        return _messagingMessageConversionType;
    }

    @Override
    public MessagingMessageConversionType getWrappedModel() {
        return _messagingMessageConversionType;
    }

    @Override
    public void resetOriginalValues() {
        _messagingMessageConversionType.resetOriginalValues();
    }
}
