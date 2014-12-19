package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalContestPhaseAttribute}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttribute
 * @generated
 */
public class ProposalContestPhaseAttributeWrapper
    implements ProposalContestPhaseAttribute,
        ModelWrapper<ProposalContestPhaseAttribute> {
    private ProposalContestPhaseAttribute _proposalContestPhaseAttribute;

    public ProposalContestPhaseAttributeWrapper(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        _proposalContestPhaseAttribute = proposalContestPhaseAttribute;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalContestPhaseAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalContestPhaseAttribute.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("proposalId", getProposalId());
        attributes.put("contestPhaseId", getContestPhaseId());
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

        Long contestPhaseId = (Long) attributes.get("contestPhaseId");

        if (contestPhaseId != null) {
            setContestPhaseId(contestPhaseId);
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
    * Returns the primary key of this proposal contest phase attribute.
    *
    * @return the primary key of this proposal contest phase attribute
    */
    @Override
    public long getPrimaryKey() {
        return _proposalContestPhaseAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal contest phase attribute.
    *
    * @param primaryKey the primary key of this proposal contest phase attribute
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _proposalContestPhaseAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this proposal contest phase attribute.
    *
    * @return the ID of this proposal contest phase attribute
    */
    @Override
    public long getId() {
        return _proposalContestPhaseAttribute.getId();
    }

    /**
    * Sets the ID of this proposal contest phase attribute.
    *
    * @param id the ID of this proposal contest phase attribute
    */
    @Override
    public void setId(long id) {
        _proposalContestPhaseAttribute.setId(id);
    }

    /**
    * Returns the proposal ID of this proposal contest phase attribute.
    *
    * @return the proposal ID of this proposal contest phase attribute
    */
    @Override
    public long getProposalId() {
        return _proposalContestPhaseAttribute.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal contest phase attribute.
    *
    * @param proposalId the proposal ID of this proposal contest phase attribute
    */
    @Override
    public void setProposalId(long proposalId) {
        _proposalContestPhaseAttribute.setProposalId(proposalId);
    }

    /**
    * Returns the contest phase ID of this proposal contest phase attribute.
    *
    * @return the contest phase ID of this proposal contest phase attribute
    */
    @Override
    public long getContestPhaseId() {
        return _proposalContestPhaseAttribute.getContestPhaseId();
    }

    /**
    * Sets the contest phase ID of this proposal contest phase attribute.
    *
    * @param contestPhaseId the contest phase ID of this proposal contest phase attribute
    */
    @Override
    public void setContestPhaseId(long contestPhaseId) {
        _proposalContestPhaseAttribute.setContestPhaseId(contestPhaseId);
    }

    /**
    * Returns the name of this proposal contest phase attribute.
    *
    * @return the name of this proposal contest phase attribute
    */
    @Override
    public java.lang.String getName() {
        return _proposalContestPhaseAttribute.getName();
    }

    /**
    * Sets the name of this proposal contest phase attribute.
    *
    * @param name the name of this proposal contest phase attribute
    */
    @Override
    public void setName(java.lang.String name) {
        _proposalContestPhaseAttribute.setName(name);
    }

    /**
    * Returns the additional ID of this proposal contest phase attribute.
    *
    * @return the additional ID of this proposal contest phase attribute
    */
    @Override
    public long getAdditionalId() {
        return _proposalContestPhaseAttribute.getAdditionalId();
    }

    /**
    * Sets the additional ID of this proposal contest phase attribute.
    *
    * @param additionalId the additional ID of this proposal contest phase attribute
    */
    @Override
    public void setAdditionalId(long additionalId) {
        _proposalContestPhaseAttribute.setAdditionalId(additionalId);
    }

    /**
    * Returns the numeric value of this proposal contest phase attribute.
    *
    * @return the numeric value of this proposal contest phase attribute
    */
    @Override
    public long getNumericValue() {
        return _proposalContestPhaseAttribute.getNumericValue();
    }

    /**
    * Sets the numeric value of this proposal contest phase attribute.
    *
    * @param numericValue the numeric value of this proposal contest phase attribute
    */
    @Override
    public void setNumericValue(long numericValue) {
        _proposalContestPhaseAttribute.setNumericValue(numericValue);
    }

    /**
    * Returns the string value of this proposal contest phase attribute.
    *
    * @return the string value of this proposal contest phase attribute
    */
    @Override
    public java.lang.String getStringValue() {
        return _proposalContestPhaseAttribute.getStringValue();
    }

    /**
    * Sets the string value of this proposal contest phase attribute.
    *
    * @param stringValue the string value of this proposal contest phase attribute
    */
    @Override
    public void setStringValue(java.lang.String stringValue) {
        _proposalContestPhaseAttribute.setStringValue(stringValue);
    }

    /**
    * Returns the real value of this proposal contest phase attribute.
    *
    * @return the real value of this proposal contest phase attribute
    */
    @Override
    public double getRealValue() {
        return _proposalContestPhaseAttribute.getRealValue();
    }

    /**
    * Sets the real value of this proposal contest phase attribute.
    *
    * @param realValue the real value of this proposal contest phase attribute
    */
    @Override
    public void setRealValue(double realValue) {
        _proposalContestPhaseAttribute.setRealValue(realValue);
    }

    @Override
    public boolean isNew() {
        return _proposalContestPhaseAttribute.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalContestPhaseAttribute.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalContestPhaseAttribute.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalContestPhaseAttribute.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalContestPhaseAttribute.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalContestPhaseAttribute.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalContestPhaseAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalContestPhaseAttribute.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalContestPhaseAttribute.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalContestPhaseAttribute.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalContestPhaseAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalContestPhaseAttributeWrapper((ProposalContestPhaseAttribute) _proposalContestPhaseAttribute.clone());
    }

    @Override
    public int compareTo(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return _proposalContestPhaseAttribute.compareTo(proposalContestPhaseAttribute);
    }

    @Override
    public int hashCode() {
        return _proposalContestPhaseAttribute.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ProposalContestPhaseAttribute> toCacheModel() {
        return _proposalContestPhaseAttribute.toCacheModel();
    }

    @Override
    public ProposalContestPhaseAttribute toEscapedModel() {
        return new ProposalContestPhaseAttributeWrapper(_proposalContestPhaseAttribute.toEscapedModel());
    }

    @Override
    public ProposalContestPhaseAttribute toUnescapedModel() {
        return new ProposalContestPhaseAttributeWrapper(_proposalContestPhaseAttribute.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalContestPhaseAttribute.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalContestPhaseAttribute.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttribute.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalContestPhaseAttributeWrapper)) {
            return false;
        }

        ProposalContestPhaseAttributeWrapper proposalContestPhaseAttributeWrapper =
            (ProposalContestPhaseAttributeWrapper) obj;

        if (Validator.equals(_proposalContestPhaseAttribute,
                    proposalContestPhaseAttributeWrapper._proposalContestPhaseAttribute)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalContestPhaseAttribute getWrappedProposalContestPhaseAttribute() {
        return _proposalContestPhaseAttribute;
    }

    @Override
    public ProposalContestPhaseAttribute getWrappedModel() {
        return _proposalContestPhaseAttribute;
    }

    @Override
    public void resetOriginalValues() {
        _proposalContestPhaseAttribute.resetOriginalValues();
    }
}
