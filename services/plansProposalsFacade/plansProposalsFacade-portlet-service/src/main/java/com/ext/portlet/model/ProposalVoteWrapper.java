package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalVote}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVote
 * @generated
 */
public class ProposalVoteWrapper implements ProposalVote,
    ModelWrapper<ProposalVote> {
    private ProposalVote _proposalVote;

    public ProposalVoteWrapper(ProposalVote proposalVote) {
        _proposalVote = proposalVote;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalVote.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalVote.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("contestPhaseId", getContestPhaseId());
        attributes.put("userId", getUserId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Long contestPhaseId = (Long) attributes.get("contestPhaseId");

        if (contestPhaseId != null) {
            setContestPhaseId(contestPhaseId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    /**
    * Returns the primary key of this proposal vote.
    *
    * @return the primary key of this proposal vote
    */
    @Override
    public com.ext.portlet.service.persistence.ProposalVotePK getPrimaryKey() {
        return _proposalVote.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal vote.
    *
    * @param primaryKey the primary key of this proposal vote
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ProposalVotePK primaryKey) {
        _proposalVote.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal vote.
    *
    * @return the proposal ID of this proposal vote
    */
    @Override
    public long getProposalId() {
        return _proposalVote.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal vote.
    *
    * @param proposalId the proposal ID of this proposal vote
    */
    @Override
    public void setProposalId(long proposalId) {
        _proposalVote.setProposalId(proposalId);
    }

    /**
    * Returns the contest phase ID of this proposal vote.
    *
    * @return the contest phase ID of this proposal vote
    */
    @Override
    public long getContestPhaseId() {
        return _proposalVote.getContestPhaseId();
    }

    /**
    * Sets the contest phase ID of this proposal vote.
    *
    * @param contestPhaseId the contest phase ID of this proposal vote
    */
    @Override
    public void setContestPhaseId(long contestPhaseId) {
        _proposalVote.setContestPhaseId(contestPhaseId);
    }

    /**
    * Returns the user ID of this proposal vote.
    *
    * @return the user ID of this proposal vote
    */
    @Override
    public long getUserId() {
        return _proposalVote.getUserId();
    }

    /**
    * Sets the user ID of this proposal vote.
    *
    * @param userId the user ID of this proposal vote
    */
    @Override
    public void setUserId(long userId) {
        _proposalVote.setUserId(userId);
    }

    /**
    * Returns the user uuid of this proposal vote.
    *
    * @return the user uuid of this proposal vote
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalVote.getUserUuid();
    }

    /**
    * Sets the user uuid of this proposal vote.
    *
    * @param userUuid the user uuid of this proposal vote
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _proposalVote.setUserUuid(userUuid);
    }

    /**
    * Returns the create date of this proposal vote.
    *
    * @return the create date of this proposal vote
    */
    @Override
    public java.util.Date getCreateDate() {
        return _proposalVote.getCreateDate();
    }

    /**
    * Sets the create date of this proposal vote.
    *
    * @param createDate the create date of this proposal vote
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _proposalVote.setCreateDate(createDate);
    }

    @Override
    public boolean isNew() {
        return _proposalVote.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalVote.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalVote.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalVote.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalVote.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalVote.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalVote.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalVote.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalVote.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalVote.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalVote.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalVoteWrapper((ProposalVote) _proposalVote.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.ProposalVote proposalVote) {
        return _proposalVote.compareTo(proposalVote);
    }

    @Override
    public int hashCode() {
        return _proposalVote.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ProposalVote> toCacheModel() {
        return _proposalVote.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ProposalVote toEscapedModel() {
        return new ProposalVoteWrapper(_proposalVote.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ProposalVote toUnescapedModel() {
        return new ProposalVoteWrapper(_proposalVote.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalVote.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalVote.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalVote.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalVoteWrapper)) {
            return false;
        }

        ProposalVoteWrapper proposalVoteWrapper = (ProposalVoteWrapper) obj;

        if (Validator.equals(_proposalVote, proposalVoteWrapper._proposalVote)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalVote getWrappedProposalVote() {
        return _proposalVote;
    }

    @Override
    public ProposalVote getWrappedModel() {
        return _proposalVote;
    }

    @Override
    public void resetOriginalValues() {
        _proposalVote.resetOriginalValues();
    }
}
