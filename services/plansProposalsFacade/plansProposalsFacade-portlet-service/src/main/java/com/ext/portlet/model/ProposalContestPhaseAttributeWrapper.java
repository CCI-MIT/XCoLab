package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalContestPhaseAttribute}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalContestPhaseAttribute
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

    public Class<?> getModelClass() {
        return ProposalContestPhaseAttribute.class;
    }

    public String getModelClassName() {
        return ProposalContestPhaseAttribute.class.getName();
    }

    /**
    * Returns the primary key of this proposal contest phase attribute.
    *
    * @return the primary key of this proposal contest phase attribute
    */
    public long getPrimaryKey() {
        return _proposalContestPhaseAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal contest phase attribute.
    *
    * @param primaryKey the primary key of this proposal contest phase attribute
    */
    public void setPrimaryKey(long primaryKey) {
        _proposalContestPhaseAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this proposal contest phase attribute.
    *
    * @return the ID of this proposal contest phase attribute
    */
    public long getId() {
        return _proposalContestPhaseAttribute.getId();
    }

    /**
    * Sets the ID of this proposal contest phase attribute.
    *
    * @param id the ID of this proposal contest phase attribute
    */
    public void setId(long id) {
        _proposalContestPhaseAttribute.setId(id);
    }

    /**
    * Returns the proposal ID of this proposal contest phase attribute.
    *
    * @return the proposal ID of this proposal contest phase attribute
    */
    public long getProposalId() {
        return _proposalContestPhaseAttribute.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal contest phase attribute.
    *
    * @param proposalId the proposal ID of this proposal contest phase attribute
    */
    public void setProposalId(long proposalId) {
        _proposalContestPhaseAttribute.setProposalId(proposalId);
    }

    /**
    * Returns the contest phase ID of this proposal contest phase attribute.
    *
    * @return the contest phase ID of this proposal contest phase attribute
    */
    public long getContestPhaseId() {
        return _proposalContestPhaseAttribute.getContestPhaseId();
    }

    /**
    * Sets the contest phase ID of this proposal contest phase attribute.
    *
    * @param contestPhaseId the contest phase ID of this proposal contest phase attribute
    */
    public void setContestPhaseId(long contestPhaseId) {
        _proposalContestPhaseAttribute.setContestPhaseId(contestPhaseId);
    }

    /**
    * Returns the name of this proposal contest phase attribute.
    *
    * @return the name of this proposal contest phase attribute
    */
    public java.lang.String getName() {
        return _proposalContestPhaseAttribute.getName();
    }

    /**
    * Sets the name of this proposal contest phase attribute.
    *
    * @param name the name of this proposal contest phase attribute
    */
    public void setName(java.lang.String name) {
        _proposalContestPhaseAttribute.setName(name);
    }

    /**
    * Returns the additional ID of this proposal contest phase attribute.
    *
    * @return the additional ID of this proposal contest phase attribute
    */
    public long getAdditionalId() {
        return _proposalContestPhaseAttribute.getAdditionalId();
    }

    /**
    * Sets the additional ID of this proposal contest phase attribute.
    *
    * @param additionalId the additional ID of this proposal contest phase attribute
    */
    public void setAdditionalId(long additionalId) {
        _proposalContestPhaseAttribute.setAdditionalId(additionalId);
    }

    /**
    * Returns the numeric value of this proposal contest phase attribute.
    *
    * @return the numeric value of this proposal contest phase attribute
    */
    public long getNumericValue() {
        return _proposalContestPhaseAttribute.getNumericValue();
    }

    /**
    * Sets the numeric value of this proposal contest phase attribute.
    *
    * @param numericValue the numeric value of this proposal contest phase attribute
    */
    public void setNumericValue(long numericValue) {
        _proposalContestPhaseAttribute.setNumericValue(numericValue);
    }

    /**
    * Returns the string value of this proposal contest phase attribute.
    *
    * @return the string value of this proposal contest phase attribute
    */
    public java.lang.String getStringValue() {
        return _proposalContestPhaseAttribute.getStringValue();
    }

    /**
    * Sets the string value of this proposal contest phase attribute.
    *
    * @param stringValue the string value of this proposal contest phase attribute
    */
    public void setStringValue(java.lang.String stringValue) {
        _proposalContestPhaseAttribute.setStringValue(stringValue);
    }

    /**
    * Returns the real value of this proposal contest phase attribute.
    *
    * @return the real value of this proposal contest phase attribute
    */
    public double getRealValue() {
        return _proposalContestPhaseAttribute.getRealValue();
    }

    /**
    * Sets the real value of this proposal contest phase attribute.
    *
    * @param realValue the real value of this proposal contest phase attribute
    */
    public void setRealValue(double realValue) {
        _proposalContestPhaseAttribute.setRealValue(realValue);
    }

    public boolean isNew() {
        return _proposalContestPhaseAttribute.isNew();
    }

    public void setNew(boolean n) {
        _proposalContestPhaseAttribute.setNew(n);
    }

    public boolean isCachedModel() {
        return _proposalContestPhaseAttribute.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _proposalContestPhaseAttribute.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _proposalContestPhaseAttribute.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalContestPhaseAttribute.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalContestPhaseAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalContestPhaseAttribute.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalContestPhaseAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalContestPhaseAttributeWrapper((ProposalContestPhaseAttribute) _proposalContestPhaseAttribute.clone());
    }

    public int compareTo(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return _proposalContestPhaseAttribute.compareTo(proposalContestPhaseAttribute);
    }

    @Override
    public int hashCode() {
        return _proposalContestPhaseAttribute.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ProposalContestPhaseAttribute> toCacheModel() {
        return _proposalContestPhaseAttribute.toCacheModel();
    }

    public ProposalContestPhaseAttribute toEscapedModel() {
        return new ProposalContestPhaseAttributeWrapper(_proposalContestPhaseAttribute.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalContestPhaseAttribute.toString();
    }

    public java.lang.String toXmlString() {
        return _proposalContestPhaseAttribute.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttribute.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ProposalContestPhaseAttribute getWrappedProposalContestPhaseAttribute() {
        return _proposalContestPhaseAttribute;
    }

    public ProposalContestPhaseAttribute getWrappedModel() {
        return _proposalContestPhaseAttribute;
    }

    public void resetOriginalValues() {
        _proposalContestPhaseAttribute.resetOriginalValues();
    }
}
