package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalReference}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalReference
 * @generated
 */
public class ProposalReferenceWrapper implements ProposalReference,
    ModelWrapper<ProposalReference> {
    private ProposalReference _proposalReference;

    public ProposalReferenceWrapper(ProposalReference proposalReference) {
        _proposalReference = proposalReference;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalReference.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalReference.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("subProposalId", getSubProposalId());
        attributes.put("sectionAttributeId", getSectionAttributeId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Long subProposalId = (Long) attributes.get("subProposalId");

        if (subProposalId != null) {
            setSubProposalId(subProposalId);
        }

        Long sectionAttributeId = (Long) attributes.get("sectionAttributeId");

        if (sectionAttributeId != null) {
            setSectionAttributeId(sectionAttributeId);
        }
    }

    /**
    * Returns the primary key of this proposal reference.
    *
    * @return the primary key of this proposal reference
    */
    @Override
    public com.ext.portlet.service.persistence.ProposalReferencePK getPrimaryKey() {
        return _proposalReference.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal reference.
    *
    * @param primaryKey the primary key of this proposal reference
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ProposalReferencePK primaryKey) {
        _proposalReference.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal reference.
    *
    * @return the proposal ID of this proposal reference
    */
    @Override
    public long getProposalId() {
        return _proposalReference.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal reference.
    *
    * @param proposalId the proposal ID of this proposal reference
    */
    @Override
    public void setProposalId(long proposalId) {
        _proposalReference.setProposalId(proposalId);
    }

    /**
    * Returns the sub proposal ID of this proposal reference.
    *
    * @return the sub proposal ID of this proposal reference
    */
    @Override
    public long getSubProposalId() {
        return _proposalReference.getSubProposalId();
    }

    /**
    * Sets the sub proposal ID of this proposal reference.
    *
    * @param subProposalId the sub proposal ID of this proposal reference
    */
    @Override
    public void setSubProposalId(long subProposalId) {
        _proposalReference.setSubProposalId(subProposalId);
    }

    /**
    * Returns the section attribute ID of this proposal reference.
    *
    * @return the section attribute ID of this proposal reference
    */
    @Override
    public long getSectionAttributeId() {
        return _proposalReference.getSectionAttributeId();
    }

    /**
    * Sets the section attribute ID of this proposal reference.
    *
    * @param sectionAttributeId the section attribute ID of this proposal reference
    */
    @Override
    public void setSectionAttributeId(long sectionAttributeId) {
        _proposalReference.setSectionAttributeId(sectionAttributeId);
    }

    @Override
    public boolean isNew() {
        return _proposalReference.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalReference.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalReference.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalReference.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalReference.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalReference.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalReference.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalReference.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalReference.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalReference.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalReference.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalReferenceWrapper((ProposalReference) _proposalReference.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.ProposalReference proposalReference) {
        return _proposalReference.compareTo(proposalReference);
    }

    @Override
    public int hashCode() {
        return _proposalReference.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ProposalReference> toCacheModel() {
        return _proposalReference.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ProposalReference toEscapedModel() {
        return new ProposalReferenceWrapper(_proposalReference.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ProposalReference toUnescapedModel() {
        return new ProposalReferenceWrapper(_proposalReference.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalReference.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalReference.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalReference.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalReferenceWrapper)) {
            return false;
        }

        ProposalReferenceWrapper proposalReferenceWrapper = (ProposalReferenceWrapper) obj;

        if (Validator.equals(_proposalReference,
                    proposalReferenceWrapper._proposalReference)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalReference getWrappedProposalReference() {
        return _proposalReference;
    }

    @Override
    public ProposalReference getWrappedModel() {
        return _proposalReference;
    }

    @Override
    public void resetOriginalValues() {
        _proposalReference.resetOriginalValues();
    }
}
