package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalAttribute}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttribute
 * @generated
 */
public class ProposalAttributeWrapper implements ProposalAttribute,
    ModelWrapper<ProposalAttribute> {
    private ProposalAttribute _proposalAttribute;

    public ProposalAttributeWrapper(ProposalAttribute proposalAttribute) {
        _proposalAttribute = proposalAttribute;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalAttribute.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("proposalId", getProposalId());
        attributes.put("version", getVersion());
        attributes.put("versionWhenCreated", getVersionWhenCreated());
        attributes.put("name", getName());
        attributes.put("additionalId", getAdditionalId());
        attributes.put("numericValue", getNumericValue());
        attributes.put("stringValue", getStringValue());
        attributes.put("realValue", getRealValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Integer version = (Integer) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Integer versionWhenCreated = (Integer) attributes.get(
                "versionWhenCreated");

        if (versionWhenCreated != null) {
            setVersionWhenCreated(versionWhenCreated);
        }

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
    * Returns the primary key of this proposal attribute.
    *
    * @return the primary key of this proposal attribute
    */
    @Override
    public long getPrimaryKey() {
        return _proposalAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal attribute.
    *
    * @param primaryKey the primary key of this proposal attribute
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _proposalAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this proposal attribute.
    *
    * @return the ID of this proposal attribute
    */
    @Override
    public long getId() {
        return _proposalAttribute.getId();
    }

    /**
    * Sets the ID of this proposal attribute.
    *
    * @param id the ID of this proposal attribute
    */
    @Override
    public void setId(long id) {
        _proposalAttribute.setId(id);
    }

    /**
    * Returns the proposal ID of this proposal attribute.
    *
    * @return the proposal ID of this proposal attribute
    */
    @Override
    public long getProposalId() {
        return _proposalAttribute.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal attribute.
    *
    * @param proposalId the proposal ID of this proposal attribute
    */
    @Override
    public void setProposalId(long proposalId) {
        _proposalAttribute.setProposalId(proposalId);
    }

    /**
    * Returns the version of this proposal attribute.
    *
    * @return the version of this proposal attribute
    */
    @Override
    public int getVersion() {
        return _proposalAttribute.getVersion();
    }

    /**
    * Sets the version of this proposal attribute.
    *
    * @param version the version of this proposal attribute
    */
    @Override
    public void setVersion(int version) {
        _proposalAttribute.setVersion(version);
    }

    /**
    * Returns the version when created of this proposal attribute.
    *
    * @return the version when created of this proposal attribute
    */
    @Override
    public int getVersionWhenCreated() {
        return _proposalAttribute.getVersionWhenCreated();
    }

    /**
    * Sets the version when created of this proposal attribute.
    *
    * @param versionWhenCreated the version when created of this proposal attribute
    */
    @Override
    public void setVersionWhenCreated(int versionWhenCreated) {
        _proposalAttribute.setVersionWhenCreated(versionWhenCreated);
    }

    /**
    * Returns the name of this proposal attribute.
    *
    * @return the name of this proposal attribute
    */
    @Override
    public java.lang.String getName() {
        return _proposalAttribute.getName();
    }

    /**
    * Sets the name of this proposal attribute.
    *
    * @param name the name of this proposal attribute
    */
    @Override
    public void setName(java.lang.String name) {
        _proposalAttribute.setName(name);
    }

    /**
    * Returns the additional ID of this proposal attribute.
    *
    * @return the additional ID of this proposal attribute
    */
    @Override
    public long getAdditionalId() {
        return _proposalAttribute.getAdditionalId();
    }

    /**
    * Sets the additional ID of this proposal attribute.
    *
    * @param additionalId the additional ID of this proposal attribute
    */
    @Override
    public void setAdditionalId(long additionalId) {
        _proposalAttribute.setAdditionalId(additionalId);
    }

    /**
    * Returns the numeric value of this proposal attribute.
    *
    * @return the numeric value of this proposal attribute
    */
    @Override
    public long getNumericValue() {
        return _proposalAttribute.getNumericValue();
    }

    /**
    * Sets the numeric value of this proposal attribute.
    *
    * @param numericValue the numeric value of this proposal attribute
    */
    @Override
    public void setNumericValue(long numericValue) {
        _proposalAttribute.setNumericValue(numericValue);
    }

    /**
    * Returns the string value of this proposal attribute.
    *
    * @return the string value of this proposal attribute
    */
    @Override
    public java.lang.String getStringValue() {
        return _proposalAttribute.getStringValue();
    }

    /**
    * Sets the string value of this proposal attribute.
    *
    * @param stringValue the string value of this proposal attribute
    */
    @Override
    public void setStringValue(java.lang.String stringValue) {
        _proposalAttribute.setStringValue(stringValue);
    }

    /**
    * Returns the real value of this proposal attribute.
    *
    * @return the real value of this proposal attribute
    */
    @Override
    public double getRealValue() {
        return _proposalAttribute.getRealValue();
    }

    /**
    * Sets the real value of this proposal attribute.
    *
    * @param realValue the real value of this proposal attribute
    */
    @Override
    public void setRealValue(double realValue) {
        _proposalAttribute.setRealValue(realValue);
    }

    @Override
    public boolean isNew() {
        return _proposalAttribute.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalAttribute.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalAttribute.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalAttribute.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalAttribute.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalAttribute.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalAttribute.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalAttribute.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalAttribute.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalAttributeWrapper((ProposalAttribute) _proposalAttribute.clone());
    }

    @Override
    public int compareTo(ProposalAttribute proposalAttribute) {
        return _proposalAttribute.compareTo(proposalAttribute);
    }

    @Override
    public int hashCode() {
        return _proposalAttribute.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ProposalAttribute> toCacheModel() {
        return _proposalAttribute.toCacheModel();
    }

    @Override
    public ProposalAttribute toEscapedModel() {
        return new ProposalAttributeWrapper(_proposalAttribute.toEscapedModel());
    }

    @Override
    public ProposalAttribute toUnescapedModel() {
        return new ProposalAttributeWrapper(_proposalAttribute.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalAttribute.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalAttribute.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalAttribute.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalAttributeWrapper)) {
            return false;
        }

        ProposalAttributeWrapper proposalAttributeWrapper = (ProposalAttributeWrapper) obj;

        if (Validator.equals(_proposalAttribute,
                    proposalAttributeWrapper._proposalAttribute)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalAttribute getWrappedProposalAttribute() {
        return _proposalAttribute;
    }

    @Override
    public ProposalAttribute getWrappedModel() {
        return _proposalAttribute;
    }

    @Override
    public void resetOriginalValues() {
        _proposalAttribute.resetOriginalValues();
    }
}
