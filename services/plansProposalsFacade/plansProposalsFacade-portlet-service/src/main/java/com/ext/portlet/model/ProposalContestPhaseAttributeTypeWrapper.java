package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalContestPhaseAttributeType}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalContestPhaseAttributeType
 * @generated
 */
public class ProposalContestPhaseAttributeTypeWrapper
    implements ProposalContestPhaseAttributeType,
        ModelWrapper<ProposalContestPhaseAttributeType> {
    private ProposalContestPhaseAttributeType _proposalContestPhaseAttributeType;

    public ProposalContestPhaseAttributeTypeWrapper(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        _proposalContestPhaseAttributeType = proposalContestPhaseAttributeType;
    }

    public Class<?> getModelClass() {
        return ProposalContestPhaseAttributeType.class;
    }

    public String getModelClassName() {
        return ProposalContestPhaseAttributeType.class.getName();
    }

    /**
    * Returns the primary key of this proposal contest phase attribute type.
    *
    * @return the primary key of this proposal contest phase attribute type
    */
    public long getPrimaryKey() {
        return _proposalContestPhaseAttributeType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal contest phase attribute type.
    *
    * @param primaryKey the primary key of this proposal contest phase attribute type
    */
    public void setPrimaryKey(long primaryKey) {
        _proposalContestPhaseAttributeType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this proposal contest phase attribute type.
    *
    * @return the ID of this proposal contest phase attribute type
    */
    public long getId() {
        return _proposalContestPhaseAttributeType.getId();
    }

    /**
    * Sets the ID of this proposal contest phase attribute type.
    *
    * @param id the ID of this proposal contest phase attribute type
    */
    public void setId(long id) {
        _proposalContestPhaseAttributeType.setId(id);
    }

    /**
    * Returns the ribbon of this proposal contest phase attribute type.
    *
    * @return the ribbon of this proposal contest phase attribute type
    */
    public java.lang.String getRibbon() {
        return _proposalContestPhaseAttributeType.getRibbon();
    }

    /**
    * Sets the ribbon of this proposal contest phase attribute type.
    *
    * @param ribbon the ribbon of this proposal contest phase attribute type
    */
    public void setRibbon(java.lang.String ribbon) {
        _proposalContestPhaseAttributeType.setRibbon(ribbon);
    }

    /**
    * Returns the hover text of this proposal contest phase attribute type.
    *
    * @return the hover text of this proposal contest phase attribute type
    */
    public java.lang.String getHoverText() {
        return _proposalContestPhaseAttributeType.getHoverText();
    }

    /**
    * Sets the hover text of this proposal contest phase attribute type.
    *
    * @param hoverText the hover text of this proposal contest phase attribute type
    */
    public void setHoverText(java.lang.String hoverText) {
        _proposalContestPhaseAttributeType.setHoverText(hoverText);
    }

    /**
    * Returns the description of this proposal contest phase attribute type.
    *
    * @return the description of this proposal contest phase attribute type
    */
    public java.lang.String getDescription() {
        return _proposalContestPhaseAttributeType.getDescription();
    }

    /**
    * Sets the description of this proposal contest phase attribute type.
    *
    * @param description the description of this proposal contest phase attribute type
    */
    public void setDescription(java.lang.String description) {
        _proposalContestPhaseAttributeType.setDescription(description);
    }

    /**
    * Returns the copy on promote of this proposal contest phase attribute type.
    *
    * @return the copy on promote of this proposal contest phase attribute type
    */
    public boolean getCopyOnPromote() {
        return _proposalContestPhaseAttributeType.getCopyOnPromote();
    }

    /**
    * Returns <code>true</code> if this proposal contest phase attribute type is copy on promote.
    *
    * @return <code>true</code> if this proposal contest phase attribute type is copy on promote; <code>false</code> otherwise
    */
    public boolean isCopyOnPromote() {
        return _proposalContestPhaseAttributeType.isCopyOnPromote();
    }

    /**
    * Sets whether this proposal contest phase attribute type is copy on promote.
    *
    * @param copyOnPromote the copy on promote of this proposal contest phase attribute type
    */
    public void setCopyOnPromote(boolean copyOnPromote) {
        _proposalContestPhaseAttributeType.setCopyOnPromote(copyOnPromote);
    }

    public boolean isNew() {
        return _proposalContestPhaseAttributeType.isNew();
    }

    public void setNew(boolean n) {
        _proposalContestPhaseAttributeType.setNew(n);
    }

    public boolean isCachedModel() {
        return _proposalContestPhaseAttributeType.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _proposalContestPhaseAttributeType.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _proposalContestPhaseAttributeType.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalContestPhaseAttributeType.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalContestPhaseAttributeType.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalContestPhaseAttributeType.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalContestPhaseAttributeType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalContestPhaseAttributeTypeWrapper((ProposalContestPhaseAttributeType) _proposalContestPhaseAttributeType.clone());
    }

    public int compareTo(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        return _proposalContestPhaseAttributeType.compareTo(proposalContestPhaseAttributeType);
    }

    @Override
    public int hashCode() {
        return _proposalContestPhaseAttributeType.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ProposalContestPhaseAttributeType> toCacheModel() {
        return _proposalContestPhaseAttributeType.toCacheModel();
    }

    public ProposalContestPhaseAttributeType toEscapedModel() {
        return new ProposalContestPhaseAttributeTypeWrapper(_proposalContestPhaseAttributeType.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalContestPhaseAttributeType.toString();
    }

    public java.lang.String toXmlString() {
        return _proposalContestPhaseAttributeType.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttributeType.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ProposalContestPhaseAttributeType getWrappedProposalContestPhaseAttributeType() {
        return _proposalContestPhaseAttributeType;
    }

    public ProposalContestPhaseAttributeType getWrappedModel() {
        return _proposalContestPhaseAttributeType;
    }

    public void resetOriginalValues() {
        _proposalContestPhaseAttributeType.resetOriginalValues();
    }
}
