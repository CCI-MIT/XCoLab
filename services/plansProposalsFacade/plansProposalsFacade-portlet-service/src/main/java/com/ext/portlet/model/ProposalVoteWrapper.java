package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalVote}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalVote
 * @generated
 */
public class ProposalVoteWrapper implements ProposalVote,
    ModelWrapper<ProposalVote> {
    private ProposalVote _proposalVote;

    public ProposalVoteWrapper(ProposalVote proposalVote) {
        _proposalVote = proposalVote;
    }

    public Class<?> getModelClass() {
        return ProposalVote.class;
    }

    public String getModelClassName() {
        return ProposalVote.class.getName();
    }

    /**
    * Returns the primary key of this proposal vote.
    *
    * @return the primary key of this proposal vote
    */
    public com.ext.portlet.service.persistence.ProposalVotePK getPrimaryKey() {
        return _proposalVote.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal vote.
    *
    * @param primaryKey the primary key of this proposal vote
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ProposalVotePK primaryKey) {
        _proposalVote.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal vote.
    *
    * @return the proposal ID of this proposal vote
    */
    public long getProposalId() {
        return _proposalVote.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal vote.
    *
    * @param proposalId the proposal ID of this proposal vote
    */
    public void setProposalId(long proposalId) {
        _proposalVote.setProposalId(proposalId);
    }

    /**
    * Returns the contest phase ID of this proposal vote.
    *
    * @return the contest phase ID of this proposal vote
    */
    public long getContestPhaseId() {
        return _proposalVote.getContestPhaseId();
    }

    /**
    * Sets the contest phase ID of this proposal vote.
    *
    * @param contestPhaseId the contest phase ID of this proposal vote
    */
    public void setContestPhaseId(long contestPhaseId) {
        _proposalVote.setContestPhaseId(contestPhaseId);
    }

    /**
    * Returns the user ID of this proposal vote.
    *
    * @return the user ID of this proposal vote
    */
    public long getUserId() {
        return _proposalVote.getUserId();
    }

    /**
    * Sets the user ID of this proposal vote.
    *
    * @param userId the user ID of this proposal vote
    */
    public void setUserId(long userId) {
        _proposalVote.setUserId(userId);
    }

    /**
    * Returns the user uuid of this proposal vote.
    *
    * @return the user uuid of this proposal vote
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalVote.getUserUuid();
    }

    /**
    * Sets the user uuid of this proposal vote.
    *
    * @param userUuid the user uuid of this proposal vote
    */
    public void setUserUuid(java.lang.String userUuid) {
        _proposalVote.setUserUuid(userUuid);
    }

    /**
    * Returns the create date of this proposal vote.
    *
    * @return the create date of this proposal vote
    */
    public java.util.Date getCreateDate() {
        return _proposalVote.getCreateDate();
    }

    /**
    * Sets the create date of this proposal vote.
    *
    * @param createDate the create date of this proposal vote
    */
    public void setCreateDate(java.util.Date createDate) {
        _proposalVote.setCreateDate(createDate);
    }

    public boolean isNew() {
        return _proposalVote.isNew();
    }

    public void setNew(boolean n) {
        _proposalVote.setNew(n);
    }

    public boolean isCachedModel() {
        return _proposalVote.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _proposalVote.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _proposalVote.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalVote.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalVote.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalVote.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalVote.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalVoteWrapper((ProposalVote) _proposalVote.clone());
    }

    public int compareTo(ProposalVote proposalVote) {
        return _proposalVote.compareTo(proposalVote);
    }

    @Override
    public int hashCode() {
        return _proposalVote.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ProposalVote> toCacheModel() {
        return _proposalVote.toCacheModel();
    }

    public ProposalVote toEscapedModel() {
        return new ProposalVoteWrapper(_proposalVote.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalVote.toString();
    }

    public java.lang.String toXmlString() {
        return _proposalVote.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalVote.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ProposalVote getWrappedProposalVote() {
        return _proposalVote;
    }

    public ProposalVote getWrappedModel() {
        return _proposalVote;
    }

    public void resetOriginalValues() {
        _proposalVote.resetOriginalValues();
    }
}
