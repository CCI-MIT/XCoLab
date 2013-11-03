package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalAttributeType}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalAttributeType
 * @generated
 */
public class ProposalAttributeTypeWrapper implements ProposalAttributeType,
    ModelWrapper<ProposalAttributeType> {
    private ProposalAttributeType _proposalAttributeType;

    public ProposalAttributeTypeWrapper(
        ProposalAttributeType proposalAttributeType) {
        _proposalAttributeType = proposalAttributeType;
    }

    public Class<?> getModelClass() {
        return ProposalAttributeType.class;
    }

    public String getModelClassName() {
        return ProposalAttributeType.class.getName();
    }

    /**
    * Returns the primary key of this proposal attribute type.
    *
    * @return the primary key of this proposal attribute type
    */
    public java.lang.String getPrimaryKey() {
        return _proposalAttributeType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal attribute type.
    *
    * @param primaryKey the primary key of this proposal attribute type
    */
    public void setPrimaryKey(java.lang.String primaryKey) {
        _proposalAttributeType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the name of this proposal attribute type.
    *
    * @return the name of this proposal attribute type
    */
    public java.lang.String getName() {
        return _proposalAttributeType.getName();
    }

    /**
    * Sets the name of this proposal attribute type.
    *
    * @param name the name of this proposal attribute type
    */
    public void setName(java.lang.String name) {
        _proposalAttributeType.setName(name);
    }

    /**
    * Returns the visible in version history of this proposal attribute type.
    *
    * @return the visible in version history of this proposal attribute type
    */
    public boolean getVisibleInVersionHistory() {
        return _proposalAttributeType.getVisibleInVersionHistory();
    }

    /**
    * Returns <code>true</code> if this proposal attribute type is visible in version history.
    *
    * @return <code>true</code> if this proposal attribute type is visible in version history; <code>false</code> otherwise
    */
    public boolean isVisibleInVersionHistory() {
        return _proposalAttributeType.isVisibleInVersionHistory();
    }

    /**
    * Sets whether this proposal attribute type is visible in version history.
    *
    * @param visibleInVersionHistory the visible in version history of this proposal attribute type
    */
    public void setVisibleInVersionHistory(boolean visibleInVersionHistory) {
        _proposalAttributeType.setVisibleInVersionHistory(visibleInVersionHistory);
    }

    /**
    * Returns the copy on promote of this proposal attribute type.
    *
    * @return the copy on promote of this proposal attribute type
    */
    public boolean getCopyOnPromote() {
        return _proposalAttributeType.getCopyOnPromote();
    }

    /**
    * Returns <code>true</code> if this proposal attribute type is copy on promote.
    *
    * @return <code>true</code> if this proposal attribute type is copy on promote; <code>false</code> otherwise
    */
    public boolean isCopyOnPromote() {
        return _proposalAttributeType.isCopyOnPromote();
    }

    /**
    * Sets whether this proposal attribute type is copy on promote.
    *
    * @param copyOnPromote the copy on promote of this proposal attribute type
    */
    public void setCopyOnPromote(boolean copyOnPromote) {
        _proposalAttributeType.setCopyOnPromote(copyOnPromote);
    }

    public boolean isNew() {
        return _proposalAttributeType.isNew();
    }

    public void setNew(boolean n) {
        _proposalAttributeType.setNew(n);
    }

    public boolean isCachedModel() {
        return _proposalAttributeType.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _proposalAttributeType.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _proposalAttributeType.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalAttributeType.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalAttributeType.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalAttributeType.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalAttributeType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalAttributeTypeWrapper((ProposalAttributeType) _proposalAttributeType.clone());
    }

    public int compareTo(ProposalAttributeType proposalAttributeType) {
        return _proposalAttributeType.compareTo(proposalAttributeType);
    }

    @Override
    public int hashCode() {
        return _proposalAttributeType.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ProposalAttributeType> toCacheModel() {
        return _proposalAttributeType.toCacheModel();
    }

    public ProposalAttributeType toEscapedModel() {
        return new ProposalAttributeTypeWrapper(_proposalAttributeType.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalAttributeType.toString();
    }

    public java.lang.String toXmlString() {
        return _proposalAttributeType.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalAttributeType.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ProposalAttributeType getWrappedProposalAttributeType() {
        return _proposalAttributeType;
    }

    public ProposalAttributeType getWrappedModel() {
        return _proposalAttributeType;
    }

    public void resetOriginalValues() {
        _proposalAttributeType.resetOriginalValues();
    }
}
