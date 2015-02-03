package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Proposal}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Proposal
 * @generated
 */
public class ProposalWrapper implements Proposal, ModelWrapper<Proposal> {
    private Proposal _proposal;

    public ProposalWrapper(Proposal proposal) {
        _proposal = proposal;
    }

    @Override
    public Class<?> getModelClass() {
        return Proposal.class;
    }

    @Override
    public String getModelClassName() {
        return Proposal.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("createDate", getCreateDate());
        attributes.put("updatedDate", getUpdatedDate());
        attributes.put("currentVersion", getCurrentVersion());
        attributes.put("authorId", getAuthorId());
        attributes.put("visible", getVisible());
        attributes.put("discussionId", getDiscussionId());
        attributes.put("resultsDiscussionId", getResultsDiscussionId());
        attributes.put("judgeDiscussionId", getJudgeDiscussionId());
        attributes.put("fellowDiscussionId", getFellowDiscussionId());
        attributes.put("advisorDiscussionId", getAdvisorDiscussionId());
        attributes.put("groupId", getGroupId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date updatedDate = (Date) attributes.get("updatedDate");

        if (updatedDate != null) {
            setUpdatedDate(updatedDate);
        }

        Integer currentVersion = (Integer) attributes.get("currentVersion");

        if (currentVersion != null) {
            setCurrentVersion(currentVersion);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }

        Boolean visible = (Boolean) attributes.get("visible");

        if (visible != null) {
            setVisible(visible);
        }

        Long discussionId = (Long) attributes.get("discussionId");

        if (discussionId != null) {
            setDiscussionId(discussionId);
        }

        Long resultsDiscussionId = (Long) attributes.get("resultsDiscussionId");

        if (resultsDiscussionId != null) {
            setResultsDiscussionId(resultsDiscussionId);
        }

        Long judgeDiscussionId = (Long) attributes.get("judgeDiscussionId");

        if (judgeDiscussionId != null) {
            setJudgeDiscussionId(judgeDiscussionId);
        }

        Long fellowDiscussionId = (Long) attributes.get("fellowDiscussionId");

        if (fellowDiscussionId != null) {
            setFellowDiscussionId(fellowDiscussionId);
        }

        Long advisorDiscussionId = (Long) attributes.get("advisorDiscussionId");

        if (advisorDiscussionId != null) {
            setAdvisorDiscussionId(advisorDiscussionId);
        }

        Long groupId = (Long) attributes.get("groupId");

        if (groupId != null) {
            setGroupId(groupId);
        }
    }

    /**
    * Returns the primary key of this proposal.
    *
    * @return the primary key of this proposal
    */
    @Override
    public long getPrimaryKey() {
        return _proposal.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal.
    *
    * @param primaryKey the primary key of this proposal
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _proposal.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal.
    *
    * @return the proposal ID of this proposal
    */
    @Override
    public long getProposalId() {
        return _proposal.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal.
    *
    * @param proposalId the proposal ID of this proposal
    */
    @Override
    public void setProposalId(long proposalId) {
        _proposal.setProposalId(proposalId);
    }

    /**
    * Returns the create date of this proposal.
    *
    * @return the create date of this proposal
    */
    @Override
    public java.util.Date getCreateDate() {
        return _proposal.getCreateDate();
    }

    /**
    * Sets the create date of this proposal.
    *
    * @param createDate the create date of this proposal
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _proposal.setCreateDate(createDate);
    }

    /**
    * Returns the updated date of this proposal.
    *
    * @return the updated date of this proposal
    */
    @Override
    public java.util.Date getUpdatedDate() {
        return _proposal.getUpdatedDate();
    }

    /**
    * Sets the updated date of this proposal.
    *
    * @param updatedDate the updated date of this proposal
    */
    @Override
    public void setUpdatedDate(java.util.Date updatedDate) {
        _proposal.setUpdatedDate(updatedDate);
    }

    /**
    * Returns the current version of this proposal.
    *
    * @return the current version of this proposal
    */
    @Override
    public int getCurrentVersion() {
        return _proposal.getCurrentVersion();
    }

    /**
    * Sets the current version of this proposal.
    *
    * @param currentVersion the current version of this proposal
    */
    @Override
    public void setCurrentVersion(int currentVersion) {
        _proposal.setCurrentVersion(currentVersion);
    }

    /**
    * Returns the author ID of this proposal.
    *
    * @return the author ID of this proposal
    */
    @Override
    public long getAuthorId() {
        return _proposal.getAuthorId();
    }

    /**
    * Sets the author ID of this proposal.
    *
    * @param authorId the author ID of this proposal
    */
    @Override
    public void setAuthorId(long authorId) {
        _proposal.setAuthorId(authorId);
    }

    /**
    * Returns the visible of this proposal.
    *
    * @return the visible of this proposal
    */
    @Override
    public boolean getVisible() {
        return _proposal.getVisible();
    }

    /**
    * Returns <code>true</code> if this proposal is visible.
    *
    * @return <code>true</code> if this proposal is visible; <code>false</code> otherwise
    */
    @Override
    public boolean isVisible() {
        return _proposal.isVisible();
    }

    /**
    * Sets whether this proposal is visible.
    *
    * @param visible the visible of this proposal
    */
    @Override
    public void setVisible(boolean visible) {
        _proposal.setVisible(visible);
    }

    /**
    * Returns the discussion ID of this proposal.
    *
    * @return the discussion ID of this proposal
    */
    @Override
    public long getDiscussionId() {
        return _proposal.getDiscussionId();
    }

    /**
    * Sets the discussion ID of this proposal.
    *
    * @param discussionId the discussion ID of this proposal
    */
    @Override
    public void setDiscussionId(long discussionId) {
        _proposal.setDiscussionId(discussionId);
    }

    /**
    * Returns the results discussion ID of this proposal.
    *
    * @return the results discussion ID of this proposal
    */
    @Override
    public long getResultsDiscussionId() {
        return _proposal.getResultsDiscussionId();
    }

    /**
    * Sets the results discussion ID of this proposal.
    *
    * @param resultsDiscussionId the results discussion ID of this proposal
    */
    @Override
    public void setResultsDiscussionId(long resultsDiscussionId) {
        _proposal.setResultsDiscussionId(resultsDiscussionId);
    }

    /**
    * Returns the judge discussion ID of this proposal.
    *
    * @return the judge discussion ID of this proposal
    */
    @Override
    public long getJudgeDiscussionId() {
        return _proposal.getJudgeDiscussionId();
    }

    /**
    * Sets the judge discussion ID of this proposal.
    *
    * @param judgeDiscussionId the judge discussion ID of this proposal
    */
    @Override
    public void setJudgeDiscussionId(long judgeDiscussionId) {
        _proposal.setJudgeDiscussionId(judgeDiscussionId);
    }

    /**
    * Returns the fellow discussion ID of this proposal.
    *
    * @return the fellow discussion ID of this proposal
    */
    @Override
    public long getFellowDiscussionId() {
        return _proposal.getFellowDiscussionId();
    }

    /**
    * Sets the fellow discussion ID of this proposal.
    *
    * @param fellowDiscussionId the fellow discussion ID of this proposal
    */
    @Override
    public void setFellowDiscussionId(long fellowDiscussionId) {
        _proposal.setFellowDiscussionId(fellowDiscussionId);
    }

    /**
    * Returns the advisor discussion ID of this proposal.
    *
    * @return the advisor discussion ID of this proposal
    */
    @Override
    public long getAdvisorDiscussionId() {
        return _proposal.getAdvisorDiscussionId();
    }

    /**
    * Sets the advisor discussion ID of this proposal.
    *
    * @param advisorDiscussionId the advisor discussion ID of this proposal
    */
    @Override
    public void setAdvisorDiscussionId(long advisorDiscussionId) {
        _proposal.setAdvisorDiscussionId(advisorDiscussionId);
    }

    /**
    * Returns the group ID of this proposal.
    *
    * @return the group ID of this proposal
    */
    @Override
    public long getGroupId() {
        return _proposal.getGroupId();
    }

    /**
    * Sets the group ID of this proposal.
    *
    * @param groupId the group ID of this proposal
    */
    @Override
    public void setGroupId(long groupId) {
        _proposal.setGroupId(groupId);
    }

    @Override
    public boolean isNew() {
        return _proposal.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposal.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposal.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposal.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposal.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposal.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposal.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposal.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposal.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposal.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposal.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalWrapper((Proposal) _proposal.clone());
    }

    @Override
    public int compareTo(Proposal proposal) {
        return _proposal.compareTo(proposal);
    }

    @Override
    public int hashCode() {
        return _proposal.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<Proposal> toCacheModel() {
        return _proposal.toCacheModel();
    }

    @Override
    public Proposal toEscapedModel() {
        return new ProposalWrapper(_proposal.toEscapedModel());
    }

    @Override
    public Proposal toUnescapedModel() {
        return new ProposalWrapper(_proposal.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposal.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposal.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposal.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalWrapper)) {
            return false;
        }

        ProposalWrapper proposalWrapper = (ProposalWrapper) obj;

        if (Validator.equals(_proposal, proposalWrapper._proposal)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Proposal getWrappedProposal() {
        return _proposal;
    }

    @Override
    public Proposal getWrappedModel() {
        return _proposal;
    }

    @Override
    public void resetOriginalValues() {
        _proposal.resetOriginalValues();
    }
}
