package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ConfigurationAttribute}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConfigurationAttribute
 * @generated
 */
public class ConfigurationAttributeWrapper implements ConfigurationAttribute,
    ModelWrapper<ConfigurationAttribute> {
    private ConfigurationAttribute _configurationAttribute;

    public ConfigurationAttributeWrapper(
        ConfigurationAttribute configurationAttribute) {
        _configurationAttribute = configurationAttribute;
    }

    @Override
    public Class<?> getModelClass() {
        return ConfigurationAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return ConfigurationAttribute.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("name", getName());
        attributes.put("additionalId", getAdditionalId());
        attributes.put("numericValue", getNumericValue());
        attributes.put("stringValue", getStringValue());
        attributes.put("realValue", getRealValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Long additionalId = (Long) attributes.get("additionalId");

        if (additionalId != null) {
            setAdditionalId(additionalId);
        }

        Long numericValue = (Long) attributes.get("numericValue");

        if (numericValue != null) {
            setNumericValue(numericValue);
        }

        String stringValue = (String) attributes.get("stringValue");

        if (stringValue != null) {
            setStringValue(stringValue);
        }

        Double realValue = (Double) attributes.get("realValue");

        if (realValue != null) {
            setRealValue(realValue);
        }
    }

    /**
    * Returns the primary key of this configuration attribute.
    *
    * @return the primary key of this configuration attribute
    */
    @Override
    public com.ext.portlet.service.persistence.ConfigurationAttributePK getPrimaryKey() {
        return _configurationAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this configuration attribute.
    *
    * @param primaryKey the primary key of this configuration attribute
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ConfigurationAttributePK primaryKey) {
        _configurationAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the name of this configuration attribute.
    *
    * @return the name of this configuration attribute
    */
    @Override
    public java.lang.String getName() {
        return _configurationAttribute.getName();
    }

    /**
    * Sets the name of this configuration attribute.
    *
    * @param name the name of this configuration attribute
    */
    @Override
    public void setName(java.lang.String name) {
        _configurationAttribute.setName(name);
    }

    /**
    * Returns the additional ID of this configuration attribute.
    *
    * @return the additional ID of this configuration attribute
    */
    @Override
    public long getAdditionalId() {
        return _configurationAttribute.getAdditionalId();
    }

    /**
    * Sets the additional ID of this configuration attribute.
    *
    * @param additionalId the additional ID of this configuration attribute
    */
    @Override
    public void setAdditionalId(long additionalId) {
        _configurationAttribute.setAdditionalId(additionalId);
    }

    /**
    * Returns the numeric value of this configuration attribute.
    *
    * @return the numeric value of this configuration attribute
    */
    @Override
    public long getNumericValue() {
        return _configurationAttribute.getNumericValue();
    }

    /**
    * Sets the numeric value of this configuration attribute.
    *
    * @param numericValue the numeric value of this configuration attribute
    */
    @Override
    public void setNumericValue(long numericValue) {
        _configurationAttribute.setNumericValue(numericValue);
    }

    /**
    * Returns the string value of this configuration attribute.
    *
    * @return the string value of this configuration attribute
    */
    @Override
    public java.lang.String getStringValue() {
        return _configurationAttribute.getStringValue();
    }

    /**
    * Sets the string value of this configuration attribute.
    *
    * @param stringValue the string value of this configuration attribute
    */
    @Override
    public void setStringValue(java.lang.String stringValue) {
        _configurationAttribute.setStringValue(stringValue);
    }

    /**
    * Returns the real value of this configuration attribute.
    *
    * @return the real value of this configuration attribute
    */
    @Override
    public double getRealValue() {
        return _configurationAttribute.getRealValue();
    }

    /**
    * Sets the real value of this configuration attribute.
    *
    * @param realValue the real value of this configuration attribute
    */
    @Override
    public void setRealValue(double realValue) {
        _configurationAttribute.setRealValue(realValue);
    }

    @Override
    public boolean isNew() {
        return _configurationAttribute.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _configurationAttribute.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _configurationAttribute.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _configurationAttribute.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _configurationAttribute.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _configurationAttribute.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _configurationAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _configurationAttribute.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _configurationAttribute.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _configurationAttribute.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _configurationAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ConfigurationAttributeWrapper((ConfigurationAttribute) _configurationAttribute.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.ConfigurationAttribute configurationAttribute) {
        return _configurationAttribute.compareTo(configurationAttribute);
    }

    @Override
    public int hashCode() {
        return _configurationAttribute.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ConfigurationAttribute> toCacheModel() {
        return _configurationAttribute.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ConfigurationAttribute toEscapedModel() {
        return new ConfigurationAttributeWrapper(_configurationAttribute.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ConfigurationAttribute toUnescapedModel() {
        return new ConfigurationAttributeWrapper(_configurationAttribute.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _configurationAttribute.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _configurationAttribute.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _configurationAttribute.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ConfigurationAttributeWrapper)) {
            return false;
        }

        ConfigurationAttributeWrapper configurationAttributeWrapper = (ConfigurationAttributeWrapper) obj;

        if (Validator.equals(_configurationAttribute,
                    configurationAttributeWrapper._configurationAttribute)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ConfigurationAttribute getWrappedConfigurationAttribute() {
        return _configurationAttribute;
    }

    @Override
    public ConfigurationAttribute getWrappedModel() {
        return _configurationAttribute;
    }

    @Override
    public void resetOriginalValues() {
        _configurationAttribute.resetOriginalValues();
    }
}
