package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalVersion}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalVersion
 * @generated
 */
public class ProposalVersionWrapper implements ProposalVersion,
    ModelWrapper<ProposalVersion> {
    private ProposalVersion _proposalVersion;

    public ProposalVersionWrapper(ProposalVersion proposalVersion) {
        _proposalVersion = proposalVersion;
    }

    public Class<?> getModelClass() {
        return ProposalVersion.class;
    }

    public String getModelClassName() {
        return ProposalVersion.class.getName();
    }

    /**
    * Returns the primary key of this proposal version.
    *
    * @return the primary key of this proposal version
    */
    public com.ext.portlet.service.persistence.ProposalVersionPK getPrimaryKey() {
        return _proposalVersion.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal version.
    *
    * @param primaryKey the primary key of this proposal version
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ProposalVersionPK primaryKey) {
        _proposalVersion.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal version.
    *
    * @return the proposal ID of this proposal version
    */
    public long getProposalId() {
        return _proposalVersion.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal version.
    *
    * @param proposalId the proposal ID of this proposal version
    */
    public void setProposalId(long proposalId) {
        _proposalVersion.setProposalId(proposalId);
    }

    /**
    * Returns the version of this proposal version.
    *
    * @return the version of this proposal version
    */
    public int getVersion() {
        return _proposalVersion.getVersion();
    }

    /**
    * Sets the version of this proposal version.
    *
    * @param version the version of this proposal version
    */
    public void setVersion(int version) {
        _proposalVersion.setVersion(version);
    }

    /**
    * Returns the author ID of this proposal version.
    *
    * @return the author ID of this proposal version
    */
    public long getAuthorId() {
        return _proposalVersion.getAuthorId();
    }

    /**
    * Sets the author ID of this proposal version.
    *
    * @param authorId the author ID of this proposal version
    */
    public void setAuthorId(long authorId) {
        _proposalVersion.setAuthorId(authorId);
    }

    /**
    * Returns the create date of this proposal version.
    *
    * @return the create date of this proposal version
    */
    public java.util.Date getCreateDate() {
        return _proposalVersion.getCreateDate();
    }

    /**
    * Sets the create date of this proposal version.
    *
    * @param createDate the create date of this proposal version
    */
    public void setCreateDate(java.util.Date createDate) {
        _proposalVersion.setCreateDate(createDate);
    }

    /**
    * Returns the update type of this proposal version.
    *
    * @return the update type of this proposal version
    */
    public java.lang.String getUpdateType() {
        return _proposalVersion.getUpdateType();
    }

    /**
    * Sets the update type of this proposal version.
    *
    * @param updateType the update type of this proposal version
    */
    public void setUpdateType(java.lang.String updateType) {
        _proposalVersion.setUpdateType(updateType);
    }

    /**
    * Returns the update additional ID of this proposal version.
    *
    * @return the update additional ID of this proposal version
    */
    public long getUpdateAdditionalId() {
        return _proposalVersion.getUpdateAdditionalId();
    }

    /**
    * Sets the update additional ID of this proposal version.
    *
    * @param updateAdditionalId the update additional ID of this proposal version
    */
    public void setUpdateAdditionalId(long updateAdditionalId) {
        _proposalVersion.setUpdateAdditionalId(updateAdditionalId);
    }

    public boolean isNew() {
        return _proposalVersion.isNew();
    }

    public void setNew(boolean n) {
        _proposalVersion.setNew(n);
    }

    public boolean isCachedModel() {
        return _proposalVersion.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _proposalVersion.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _proposalVersion.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalVersion.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalVersion.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalVersion.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalVersion.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalVersionWrapper((ProposalVersion) _proposalVersion.clone());
    }

    public int compareTo(ProposalVersion proposalVersion) {
        return _proposalVersion.compareTo(proposalVersion);
    }

    @Override
    public int hashCode() {
        return _proposalVersion.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ProposalVersion> toCacheModel() {
        return _proposalVersion.toCacheModel();
    }

    public ProposalVersion toEscapedModel() {
        return new ProposalVersionWrapper(_proposalVersion.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalVersion.toString();
    }

    public java.lang.String toXmlString() {
        return _proposalVersion.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalVersion.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ProposalVersion getWrappedProposalVersion() {
        return _proposalVersion;
    }

    public ProposalVersion getWrappedModel() {
        return _proposalVersion;
    }

    public void resetOriginalValues() {
        _proposalVersion.resetOriginalValues();
    }
}
