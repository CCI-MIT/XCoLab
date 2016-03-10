package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalUnversionedAttribute}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalUnversionedAttribute
 * @generated
 */
public class ProposalUnversionedAttributeWrapper
    implements ProposalUnversionedAttribute,
        ModelWrapper<ProposalUnversionedAttribute> {
    private ProposalUnversionedAttribute _proposalUnversionedAttribute;

    public ProposalUnversionedAttributeWrapper(
        ProposalUnversionedAttribute proposalUnversionedAttribute) {
        _proposalUnversionedAttribute = proposalUnversionedAttribute;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalUnversionedAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalUnversionedAttribute.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("proposalId", getProposalId());
        attributes.put("createAuthorId", getCreateAuthorId());
        attributes.put("lastAuthorId", getLastAuthorId());
        attributes.put("createDate", getCreateDate());
        attributes.put("lastUpdateDate", getLastUpdateDate());
        attributes.put("name", getName());
        attributes.put("addtionalId", getAddtionalId());
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

        Long createAuthorId = (Long) attributes.get("createAuthorId");

        if (createAuthorId != null) {
            setCreateAuthorId(createAuthorId);
        }

        Long lastAuthorId = (Long) attributes.get("lastAuthorId");

        if (lastAuthorId != null) {
            setLastAuthorId(lastAuthorId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date lastUpdateDate = (Date) attributes.get("lastUpdateDate");

        if (lastUpdateDate != null) {
            setLastUpdateDate(lastUpdateDate);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Integer addtionalId = (Integer) attributes.get("addtionalId");

        if (addtionalId != null) {
            setAddtionalId(addtionalId);
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
    * Returns the primary key of this proposal unversioned attribute.
    *
    * @return the primary key of this proposal unversioned attribute
    */
    @Override
    public long getPrimaryKey() {
        return _proposalUnversionedAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal unversioned attribute.
    *
    * @param primaryKey the primary key of this proposal unversioned attribute
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _proposalUnversionedAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this proposal unversioned attribute.
    *
    * @return the ID of this proposal unversioned attribute
    */
    @Override
    public long getId() {
        return _proposalUnversionedAttribute.getId();
    }

    /**
    * Sets the ID of this proposal unversioned attribute.
    *
    * @param id the ID of this proposal unversioned attribute
    */
    @Override
    public void setId(long id) {
        _proposalUnversionedAttribute.setId(id);
    }

    /**
    * Returns the proposal ID of this proposal unversioned attribute.
    *
    * @return the proposal ID of this proposal unversioned attribute
    */
    @Override
    public long getProposalId() {
        return _proposalUnversionedAttribute.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal unversioned attribute.
    *
    * @param proposalId the proposal ID of this proposal unversioned attribute
    */
    @Override
    public void setProposalId(long proposalId) {
        _proposalUnversionedAttribute.setProposalId(proposalId);
    }

    /**
    * Returns the create author ID of this proposal unversioned attribute.
    *
    * @return the create author ID of this proposal unversioned attribute
    */
    @Override
    public long getCreateAuthorId() {
        return _proposalUnversionedAttribute.getCreateAuthorId();
    }

    /**
    * Sets the create author ID of this proposal unversioned attribute.
    *
    * @param createAuthorId the create author ID of this proposal unversioned attribute
    */
    @Override
    public void setCreateAuthorId(long createAuthorId) {
        _proposalUnversionedAttribute.setCreateAuthorId(createAuthorId);
    }

    /**
    * Returns the last author ID of this proposal unversioned attribute.
    *
    * @return the last author ID of this proposal unversioned attribute
    */
    @Override
    public long getLastAuthorId() {
        return _proposalUnversionedAttribute.getLastAuthorId();
    }

    /**
    * Sets the last author ID of this proposal unversioned attribute.
    *
    * @param lastAuthorId the last author ID of this proposal unversioned attribute
    */
    @Override
    public void setLastAuthorId(long lastAuthorId) {
        _proposalUnversionedAttribute.setLastAuthorId(lastAuthorId);
    }

    /**
    * Returns the create date of this proposal unversioned attribute.
    *
    * @return the create date of this proposal unversioned attribute
    */
    @Override
    public java.util.Date getCreateDate() {
        return _proposalUnversionedAttribute.getCreateDate();
    }

    /**
    * Sets the create date of this proposal unversioned attribute.
    *
    * @param createDate the create date of this proposal unversioned attribute
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _proposalUnversionedAttribute.setCreateDate(createDate);
    }

    /**
    * Returns the last update date of this proposal unversioned attribute.
    *
    * @return the last update date of this proposal unversioned attribute
    */
    @Override
    public java.util.Date getLastUpdateDate() {
        return _proposalUnversionedAttribute.getLastUpdateDate();
    }

    /**
    * Sets the last update date of this proposal unversioned attribute.
    *
    * @param lastUpdateDate the last update date of this proposal unversioned attribute
    */
    @Override
    public void setLastUpdateDate(java.util.Date lastUpdateDate) {
        _proposalUnversionedAttribute.setLastUpdateDate(lastUpdateDate);
    }

    /**
    * Returns the name of this proposal unversioned attribute.
    *
    * @return the name of this proposal unversioned attribute
    */
    @Override
    public java.lang.String getName() {
        return _proposalUnversionedAttribute.getName();
    }

    /**
    * Sets the name of this proposal unversioned attribute.
    *
    * @param name the name of this proposal unversioned attribute
    */
    @Override
    public void setName(java.lang.String name) {
        _proposalUnversionedAttribute.setName(name);
    }

    /**
    * Returns the addtional ID of this proposal unversioned attribute.
    *
    * @return the addtional ID of this proposal unversioned attribute
    */
    @Override
    public int getAddtionalId() {
        return _proposalUnversionedAttribute.getAddtionalId();
    }

    /**
    * Sets the addtional ID of this proposal unversioned attribute.
    *
    * @param addtionalId the addtional ID of this proposal unversioned attribute
    */
    @Override
    public void setAddtionalId(int addtionalId) {
        _proposalUnversionedAttribute.setAddtionalId(addtionalId);
    }

    /**
    * Returns the numeric value of this proposal unversioned attribute.
    *
    * @return the numeric value of this proposal unversioned attribute
    */
    @Override
    public long getNumericValue() {
        return _proposalUnversionedAttribute.getNumericValue();
    }

    /**
    * Sets the numeric value of this proposal unversioned attribute.
    *
    * @param numericValue the numeric value of this proposal unversioned attribute
    */
    @Override
    public void setNumericValue(long numericValue) {
        _proposalUnversionedAttribute.setNumericValue(numericValue);
    }

    /**
    * Returns the string value of this proposal unversioned attribute.
    *
    * @return the string value of this proposal unversioned attribute
    */
    @Override
    public java.lang.String getStringValue() {
        return _proposalUnversionedAttribute.getStringValue();
    }

    /**
    * Sets the string value of this proposal unversioned attribute.
    *
    * @param stringValue the string value of this proposal unversioned attribute
    */
    @Override
    public void setStringValue(java.lang.String stringValue) {
        _proposalUnversionedAttribute.setStringValue(stringValue);
    }

    /**
    * Returns the real value of this proposal unversioned attribute.
    *
    * @return the real value of this proposal unversioned attribute
    */
    @Override
    public double getRealValue() {
        return _proposalUnversionedAttribute.getRealValue();
    }

    /**
    * Sets the real value of this proposal unversioned attribute.
    *
    * @param realValue the real value of this proposal unversioned attribute
    */
    @Override
    public void setRealValue(double realValue) {
        _proposalUnversionedAttribute.setRealValue(realValue);
    }

    @Override
    public boolean isNew() {
        return _proposalUnversionedAttribute.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalUnversionedAttribute.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalUnversionedAttribute.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalUnversionedAttribute.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalUnversionedAttribute.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalUnversionedAttribute.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalUnversionedAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalUnversionedAttribute.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalUnversionedAttribute.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalUnversionedAttribute.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalUnversionedAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalUnversionedAttributeWrapper((ProposalUnversionedAttribute) _proposalUnversionedAttribute.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return _proposalUnversionedAttribute.compareTo(proposalUnversionedAttribute);
    }

    @Override
    public int hashCode() {
        return _proposalUnversionedAttribute.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ProposalUnversionedAttribute> toCacheModel() {
        return _proposalUnversionedAttribute.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ProposalUnversionedAttribute toEscapedModel() {
        return new ProposalUnversionedAttributeWrapper(_proposalUnversionedAttribute.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ProposalUnversionedAttribute toUnescapedModel() {
        return new ProposalUnversionedAttributeWrapper(_proposalUnversionedAttribute.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalUnversionedAttribute.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalUnversionedAttribute.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalUnversionedAttribute.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalUnversionedAttributeWrapper)) {
            return false;
        }

        ProposalUnversionedAttributeWrapper proposalUnversionedAttributeWrapper = (ProposalUnversionedAttributeWrapper) obj;

        if (Validator.equals(_proposalUnversionedAttribute,
                    proposalUnversionedAttributeWrapper._proposalUnversionedAttribute)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalUnversionedAttribute getWrappedProposalUnversionedAttribute() {
        return _proposalUnversionedAttribute;
    }

    @Override
    public ProposalUnversionedAttribute getWrappedModel() {
        return _proposalUnversionedAttribute;
    }

    @Override
    public void resetOriginalValues() {
        _proposalUnversionedAttribute.resetOriginalValues();
    }
}
