package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Plan2Proposal}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Plan2Proposal
 * @generated
 */
public class Plan2ProposalWrapper implements Plan2Proposal,
    ModelWrapper<Plan2Proposal> {
    private Plan2Proposal _plan2Proposal;

    public Plan2ProposalWrapper(Plan2Proposal plan2Proposal) {
        _plan2Proposal = plan2Proposal;
    }

    public Class<?> getModelClass() {
        return Plan2Proposal.class;
    }

    public String getModelClassName() {
        return Plan2Proposal.class.getName();
    }

    /**
    * Returns the primary key of this plan2 proposal.
    *
    * @return the primary key of this plan2 proposal
    */
    public long getPrimaryKey() {
        return _plan2Proposal.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan2 proposal.
    *
    * @param primaryKey the primary key of this plan2 proposal
    */
    public void setPrimaryKey(long primaryKey) {
        _plan2Proposal.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan ID of this plan2 proposal.
    *
    * @return the plan ID of this plan2 proposal
    */
    public long getPlanId() {
        return _plan2Proposal.getPlanId();
    }

    /**
    * Sets the plan ID of this plan2 proposal.
    *
    * @param planId the plan ID of this plan2 proposal
    */
    public void setPlanId(long planId) {
        _plan2Proposal.setPlanId(planId);
    }

    /**
    * Returns the proposal ID of this plan2 proposal.
    *
    * @return the proposal ID of this plan2 proposal
    */
    public long getProposalId() {
        return _plan2Proposal.getProposalId();
    }

    /**
    * Sets the proposal ID of this plan2 proposal.
    *
    * @param proposalId the proposal ID of this plan2 proposal
    */
    public void setProposalId(long proposalId) {
        _plan2Proposal.setProposalId(proposalId);
    }

    public boolean isNew() {
        return _plan2Proposal.isNew();
    }

    public void setNew(boolean n) {
        _plan2Proposal.setNew(n);
    }

    public boolean isCachedModel() {
        return _plan2Proposal.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _plan2Proposal.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _plan2Proposal.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _plan2Proposal.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _plan2Proposal.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _plan2Proposal.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _plan2Proposal.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new Plan2ProposalWrapper((Plan2Proposal) _plan2Proposal.clone());
    }

    public int compareTo(Plan2Proposal plan2Proposal) {
        return _plan2Proposal.compareTo(plan2Proposal);
    }

    @Override
    public int hashCode() {
        return _plan2Proposal.hashCode();
    }

    public com.liferay.portal.model.CacheModel<Plan2Proposal> toCacheModel() {
        return _plan2Proposal.toCacheModel();
    }

    public Plan2Proposal toEscapedModel() {
        return new Plan2ProposalWrapper(_plan2Proposal.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _plan2Proposal.toString();
    }

    public java.lang.String toXmlString() {
        return _plan2Proposal.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _plan2Proposal.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public Plan2Proposal getWrappedPlan2Proposal() {
        return _plan2Proposal;
    }

    public Plan2Proposal getWrappedModel() {
        return _plan2Proposal;
    }

    public void resetOriginalValues() {
        _plan2Proposal.resetOriginalValues();
    }
}
