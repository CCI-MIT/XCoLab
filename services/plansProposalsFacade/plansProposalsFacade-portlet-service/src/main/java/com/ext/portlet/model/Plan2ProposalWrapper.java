package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Plan2Proposal}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Plan2Proposal
 * @generated
 */
public class Plan2ProposalWrapper implements Plan2Proposal,
    ModelWrapper<Plan2Proposal> {
    private Plan2Proposal _plan2Proposal;

    public Plan2ProposalWrapper(Plan2Proposal plan2Proposal) {
        _plan2Proposal = plan2Proposal;
    }

    @Override
    public Class<?> getModelClass() {
        return Plan2Proposal.class;
    }

    @Override
    public String getModelClassName() {
        return Plan2Proposal.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planId", getPlanId());
        attributes.put("proposalId", getProposalId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }
    }

    /**
    * Returns the primary key of this plan2 proposal.
    *
    * @return the primary key of this plan2 proposal
    */
    @Override
    public long getPrimaryKey() {
        return _plan2Proposal.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan2 proposal.
    *
    * @param primaryKey the primary key of this plan2 proposal
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _plan2Proposal.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan ID of this plan2 proposal.
    *
    * @return the plan ID of this plan2 proposal
    */
    @Override
    public long getPlanId() {
        return _plan2Proposal.getPlanId();
    }

    /**
    * Sets the plan ID of this plan2 proposal.
    *
    * @param planId the plan ID of this plan2 proposal
    */
    @Override
    public void setPlanId(long planId) {
        _plan2Proposal.setPlanId(planId);
    }

    /**
    * Returns the proposal ID of this plan2 proposal.
    *
    * @return the proposal ID of this plan2 proposal
    */
    @Override
    public long getProposalId() {
        return _plan2Proposal.getProposalId();
    }

    /**
    * Sets the proposal ID of this plan2 proposal.
    *
    * @param proposalId the proposal ID of this plan2 proposal
    */
    @Override
    public void setProposalId(long proposalId) {
        _plan2Proposal.setProposalId(proposalId);
    }

    @Override
    public boolean isNew() {
        return _plan2Proposal.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _plan2Proposal.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _plan2Proposal.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _plan2Proposal.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _plan2Proposal.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _plan2Proposal.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _plan2Proposal.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _plan2Proposal.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _plan2Proposal.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _plan2Proposal.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _plan2Proposal.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new Plan2ProposalWrapper((Plan2Proposal) _plan2Proposal.clone());
    }

    @Override
    public int compareTo(Plan2Proposal plan2Proposal) {
        return _plan2Proposal.compareTo(plan2Proposal);
    }

    @Override
    public int hashCode() {
        return _plan2Proposal.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<Plan2Proposal> toCacheModel() {
        return _plan2Proposal.toCacheModel();
    }

    @Override
    public Plan2Proposal toEscapedModel() {
        return new Plan2ProposalWrapper(_plan2Proposal.toEscapedModel());
    }

    @Override
    public Plan2Proposal toUnescapedModel() {
        return new Plan2ProposalWrapper(_plan2Proposal.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _plan2Proposal.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _plan2Proposal.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _plan2Proposal.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Plan2ProposalWrapper)) {
            return false;
        }

        Plan2ProposalWrapper plan2ProposalWrapper = (Plan2ProposalWrapper) obj;

        if (Validator.equals(_plan2Proposal, plan2ProposalWrapper._plan2Proposal)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Plan2Proposal getWrappedPlan2Proposal() {
        return _plan2Proposal;
    }

    @Override
    public Plan2Proposal getWrappedModel() {
        return _plan2Proposal;
    }

    @Override
    public void resetOriginalValues() {
        _plan2Proposal.resetOriginalValues();
    }
}
