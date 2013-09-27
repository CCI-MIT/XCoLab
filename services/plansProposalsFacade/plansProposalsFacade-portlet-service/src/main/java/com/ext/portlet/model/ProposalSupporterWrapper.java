package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalSupporter}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalSupporter
 * @generated
 */
public class ProposalSupporterWrapper implements ProposalSupporter,
    ModelWrapper<ProposalSupporter> {
    private ProposalSupporter _proposalSupporter;

    public ProposalSupporterWrapper(ProposalSupporter proposalSupporter) {
        _proposalSupporter = proposalSupporter;
    }

    public Class<?> getModelClass() {
        return ProposalSupporter.class;
    }

    public String getModelClassName() {
        return ProposalSupporter.class.getName();
    }

    /**
    * Returns the primary key of this proposal supporter.
    *
    * @return the primary key of this proposal supporter
    */
    public com.ext.portlet.service.persistence.ProposalSupporterPK getPrimaryKey() {
        return _proposalSupporter.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal supporter.
    *
    * @param primaryKey the primary key of this proposal supporter
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ProposalSupporterPK primaryKey) {
        _proposalSupporter.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal supporter.
    *
    * @return the proposal ID of this proposal supporter
    */
    public long getProposalId() {
        return _proposalSupporter.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal supporter.
    *
    * @param proposalId the proposal ID of this proposal supporter
    */
    public void setProposalId(long proposalId) {
        _proposalSupporter.setProposalId(proposalId);
    }

    /**
    * Returns the user ID of this proposal supporter.
    *
    * @return the user ID of this proposal supporter
    */
    public long getUserId() {
        return _proposalSupporter.getUserId();
    }

    /**
    * Sets the user ID of this proposal supporter.
    *
    * @param userId the user ID of this proposal supporter
    */
    public void setUserId(long userId) {
        _proposalSupporter.setUserId(userId);
    }

    /**
    * Returns the user uuid of this proposal supporter.
    *
    * @return the user uuid of this proposal supporter
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalSupporter.getUserUuid();
    }

    /**
    * Sets the user uuid of this proposal supporter.
    *
    * @param userUuid the user uuid of this proposal supporter
    */
    public void setUserUuid(java.lang.String userUuid) {
        _proposalSupporter.setUserUuid(userUuid);
    }

    /**
    * Returns the create date of this proposal supporter.
    *
    * @return the create date of this proposal supporter
    */
    public java.util.Date getCreateDate() {
        return _proposalSupporter.getCreateDate();
    }

    /**
    * Sets the create date of this proposal supporter.
    *
    * @param createDate the create date of this proposal supporter
    */
    public void setCreateDate(java.util.Date createDate) {
        _proposalSupporter.setCreateDate(createDate);
    }

    public boolean isNew() {
        return _proposalSupporter.isNew();
    }

    public void setNew(boolean n) {
        _proposalSupporter.setNew(n);
    }

    public boolean isCachedModel() {
        return _proposalSupporter.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _proposalSupporter.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _proposalSupporter.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalSupporter.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalSupporter.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalSupporter.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalSupporter.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalSupporterWrapper((ProposalSupporter) _proposalSupporter.clone());
    }

    public int compareTo(ProposalSupporter proposalSupporter) {
        return _proposalSupporter.compareTo(proposalSupporter);
    }

    @Override
    public int hashCode() {
        return _proposalSupporter.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ProposalSupporter> toCacheModel() {
        return _proposalSupporter.toCacheModel();
    }

    public ProposalSupporter toEscapedModel() {
        return new ProposalSupporterWrapper(_proposalSupporter.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalSupporter.toString();
    }

    public java.lang.String toXmlString() {
        return _proposalSupporter.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalSupporter.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ProposalSupporter getWrappedProposalSupporter() {
        return _proposalSupporter;
    }

    public ProposalSupporter getWrappedModel() {
        return _proposalSupporter;
    }

    public void resetOriginalValues() {
        _proposalSupporter.resetOriginalValues();
    }
}
