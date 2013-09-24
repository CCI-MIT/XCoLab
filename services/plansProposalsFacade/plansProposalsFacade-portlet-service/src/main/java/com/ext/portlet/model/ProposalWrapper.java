package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Proposal}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Proposal
 * @generated
 */
public class ProposalWrapper implements Proposal, ModelWrapper<Proposal> {
    private Proposal _proposal;

    public ProposalWrapper(Proposal proposal) {
        _proposal = proposal;
    }

    public Class<?> getModelClass() {
        return Proposal.class;
    }

    public String getModelClassName() {
        return Proposal.class.getName();
    }

    /**
    * Returns the primary key of this proposal.
    *
    * @return the primary key of this proposal
    */
    public long getPrimaryKey() {
        return _proposal.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal.
    *
    * @param primaryKey the primary key of this proposal
    */
    public void setPrimaryKey(long primaryKey) {
        _proposal.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal.
    *
    * @return the proposal ID of this proposal
    */
    public long getProposalId() {
        return _proposal.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal.
    *
    * @param proposalId the proposal ID of this proposal
    */
    public void setProposalId(long proposalId) {
        _proposal.setProposalId(proposalId);
    }

    /**
    * Returns the create date of this proposal.
    *
    * @return the create date of this proposal
    */
    public java.util.Date getCreateDate() {
        return _proposal.getCreateDate();
    }

    /**
    * Sets the create date of this proposal.
    *
    * @param createDate the create date of this proposal
    */
    public void setCreateDate(java.util.Date createDate) {
        _proposal.setCreateDate(createDate);
    }

    /**
    * Returns the updated date of this proposal.
    *
    * @return the updated date of this proposal
    */
    public java.util.Date getUpdatedDate() {
        return _proposal.getUpdatedDate();
    }

    /**
    * Sets the updated date of this proposal.
    *
    * @param updatedDate the updated date of this proposal
    */
    public void setUpdatedDate(java.util.Date updatedDate) {
        _proposal.setUpdatedDate(updatedDate);
    }

    /**
    * Returns the current version of this proposal.
    *
    * @return the current version of this proposal
    */
    public int getCurrentVersion() {
        return _proposal.getCurrentVersion();
    }

    /**
    * Sets the current version of this proposal.
    *
    * @param currentVersion the current version of this proposal
    */
    public void setCurrentVersion(int currentVersion) {
        _proposal.setCurrentVersion(currentVersion);
    }

    /**
    * Returns the author ID of this proposal.
    *
    * @return the author ID of this proposal
    */
    public long getAuthorId() {
        return _proposal.getAuthorId();
    }

    /**
    * Sets the author ID of this proposal.
    *
    * @param authorId the author ID of this proposal
    */
    public void setAuthorId(long authorId) {
        _proposal.setAuthorId(authorId);
    }

    /**
    * Returns the visible of this proposal.
    *
    * @return the visible of this proposal
    */
    public boolean getVisible() {
        return _proposal.getVisible();
    }

    /**
    * Returns <code>true</code> if this proposal is visible.
    *
    * @return <code>true</code> if this proposal is visible; <code>false</code> otherwise
    */
    public boolean isVisible() {
        return _proposal.isVisible();
    }

    /**
    * Sets whether this proposal is visible.
    *
    * @param visible the visible of this proposal
    */
    public void setVisible(boolean visible) {
        _proposal.setVisible(visible);
    }

    /**
    * Returns the discussion ID of this proposal.
    *
    * @return the discussion ID of this proposal
    */
    public long getDiscussionId() {
        return _proposal.getDiscussionId();
    }

    /**
    * Sets the discussion ID of this proposal.
    *
    * @param discussionId the discussion ID of this proposal
    */
    public void setDiscussionId(long discussionId) {
        _proposal.setDiscussionId(discussionId);
    }

    /**
    * Returns the judge discussion ID of this proposal.
    *
    * @return the judge discussion ID of this proposal
    */
    public long getJudgeDiscussionId() {
        return _proposal.getJudgeDiscussionId();
    }

    /**
    * Sets the judge discussion ID of this proposal.
    *
    * @param judgeDiscussionId the judge discussion ID of this proposal
    */
    public void setJudgeDiscussionId(long judgeDiscussionId) {
        _proposal.setJudgeDiscussionId(judgeDiscussionId);
    }

    /**
    * Returns the fellow discussion ID of this proposal.
    *
    * @return the fellow discussion ID of this proposal
    */
    public long getFellowDiscussionId() {
        return _proposal.getFellowDiscussionId();
    }

    /**
    * Sets the fellow discussion ID of this proposal.
    *
    * @param fellowDiscussionId the fellow discussion ID of this proposal
    */
    public void setFellowDiscussionId(long fellowDiscussionId) {
        _proposal.setFellowDiscussionId(fellowDiscussionId);
    }

    /**
    * Returns the advisor discussion ID of this proposal.
    *
    * @return the advisor discussion ID of this proposal
    */
    public long getAdvisorDiscussionId() {
        return _proposal.getAdvisorDiscussionId();
    }

    /**
    * Sets the advisor discussion ID of this proposal.
    *
    * @param advisorDiscussionId the advisor discussion ID of this proposal
    */
    public void setAdvisorDiscussionId(long advisorDiscussionId) {
        _proposal.setAdvisorDiscussionId(advisorDiscussionId);
    }

    /**
    * Returns the group ID of this proposal.
    *
    * @return the group ID of this proposal
    */
    public long getGroupId() {
        return _proposal.getGroupId();
    }

    /**
    * Sets the group ID of this proposal.
    *
    * @param groupId the group ID of this proposal
    */
    public void setGroupId(long groupId) {
        _proposal.setGroupId(groupId);
    }

    public boolean isNew() {
        return _proposal.isNew();
    }

    public void setNew(boolean n) {
        _proposal.setNew(n);
    }

    public boolean isCachedModel() {
        return _proposal.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _proposal.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _proposal.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _proposal.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposal.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposal.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposal.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalWrapper((Proposal) _proposal.clone());
    }

    public int compareTo(Proposal proposal) {
        return _proposal.compareTo(proposal);
    }

    @Override
    public int hashCode() {
        return _proposal.hashCode();
    }

    public com.liferay.portal.model.CacheModel<Proposal> toCacheModel() {
        return _proposal.toCacheModel();
    }

    public Proposal toEscapedModel() {
        return new ProposalWrapper(_proposal.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposal.toString();
    }

    public java.lang.String toXmlString() {
        return _proposal.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposal.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public Proposal getWrappedProposal() {
        return _proposal;
    }

    public Proposal getWrappedModel() {
        return _proposal;
    }

    public void resetOriginalValues() {
        _proposal.resetOriginalValues();
    }
}
