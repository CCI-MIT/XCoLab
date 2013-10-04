package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Proposal2Phase}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Proposal2Phase
 * @generated
 */
public class Proposal2PhaseWrapper implements Proposal2Phase,
    ModelWrapper<Proposal2Phase> {
    private Proposal2Phase _proposal2Phase;

    public Proposal2PhaseWrapper(Proposal2Phase proposal2Phase) {
        _proposal2Phase = proposal2Phase;
    }

    public Class<?> getModelClass() {
        return Proposal2Phase.class;
    }

    public String getModelClassName() {
        return Proposal2Phase.class.getName();
    }

    /**
    * Returns the primary key of this proposal2 phase.
    *
    * @return the primary key of this proposal2 phase
    */
    public com.ext.portlet.service.persistence.Proposal2PhasePK getPrimaryKey() {
        return _proposal2Phase.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal2 phase.
    *
    * @param primaryKey the primary key of this proposal2 phase
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.Proposal2PhasePK primaryKey) {
        _proposal2Phase.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal2 phase.
    *
    * @return the proposal ID of this proposal2 phase
    */
    public long getProposalId() {
        return _proposal2Phase.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal2 phase.
    *
    * @param proposalId the proposal ID of this proposal2 phase
    */
    public void setProposalId(long proposalId) {
        _proposal2Phase.setProposalId(proposalId);
    }

    /**
    * Returns the contest phase ID of this proposal2 phase.
    *
    * @return the contest phase ID of this proposal2 phase
    */
    public long getContestPhaseId() {
        return _proposal2Phase.getContestPhaseId();
    }

    /**
    * Sets the contest phase ID of this proposal2 phase.
    *
    * @param contestPhaseId the contest phase ID of this proposal2 phase
    */
    public void setContestPhaseId(long contestPhaseId) {
        _proposal2Phase.setContestPhaseId(contestPhaseId);
    }

    /**
    * Returns the ribbon type ID of this proposal2 phase.
    *
    * @return the ribbon type ID of this proposal2 phase
    */
    public long getRibbonTypeId() {
        return _proposal2Phase.getRibbonTypeId();
    }

    /**
    * Sets the ribbon type ID of this proposal2 phase.
    *
    * @param ribbonTypeId the ribbon type ID of this proposal2 phase
    */
    public void setRibbonTypeId(long ribbonTypeId) {
        _proposal2Phase.setRibbonTypeId(ribbonTypeId);
    }

    /**
    * Returns the version from of this proposal2 phase.
    *
    * @return the version from of this proposal2 phase
    */
    public int getVersionFrom() {
        return _proposal2Phase.getVersionFrom();
    }

    /**
    * Sets the version from of this proposal2 phase.
    *
    * @param versionFrom the version from of this proposal2 phase
    */
    public void setVersionFrom(int versionFrom) {
        _proposal2Phase.setVersionFrom(versionFrom);
    }

    /**
    * Returns the version to of this proposal2 phase.
    *
    * @return the version to of this proposal2 phase
    */
    public int getVersionTo() {
        return _proposal2Phase.getVersionTo();
    }

    /**
    * Sets the version to of this proposal2 phase.
    *
    * @param versionTo the version to of this proposal2 phase
    */
    public void setVersionTo(int versionTo) {
        _proposal2Phase.setVersionTo(versionTo);
    }

    /**
    * Returns the sort weight of this proposal2 phase.
    *
    * @return the sort weight of this proposal2 phase
    */
    public int getSortWeight() {
        return _proposal2Phase.getSortWeight();
    }

    /**
    * Sets the sort weight of this proposal2 phase.
    *
    * @param sortWeight the sort weight of this proposal2 phase
    */
    public void setSortWeight(int sortWeight) {
        _proposal2Phase.setSortWeight(sortWeight);
    }

    /**
    * Returns the autopromote candidate of this proposal2 phase.
    *
    * @return the autopromote candidate of this proposal2 phase
    */
    public boolean getAutopromoteCandidate() {
        return _proposal2Phase.getAutopromoteCandidate();
    }

    /**
    * Returns <code>true</code> if this proposal2 phase is autopromote candidate.
    *
    * @return <code>true</code> if this proposal2 phase is autopromote candidate; <code>false</code> otherwise
    */
    public boolean isAutopromoteCandidate() {
        return _proposal2Phase.isAutopromoteCandidate();
    }

    /**
    * Sets whether this proposal2 phase is autopromote candidate.
    *
    * @param autopromoteCandidate the autopromote candidate of this proposal2 phase
    */
    public void setAutopromoteCandidate(boolean autopromoteCandidate) {
        _proposal2Phase.setAutopromoteCandidate(autopromoteCandidate);
    }

    public boolean isNew() {
        return _proposal2Phase.isNew();
    }

    public void setNew(boolean n) {
        _proposal2Phase.setNew(n);
    }

    public boolean isCachedModel() {
        return _proposal2Phase.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _proposal2Phase.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _proposal2Phase.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _proposal2Phase.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposal2Phase.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposal2Phase.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposal2Phase.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new Proposal2PhaseWrapper((Proposal2Phase) _proposal2Phase.clone());
    }

    public int compareTo(Proposal2Phase proposal2Phase) {
        return _proposal2Phase.compareTo(proposal2Phase);
    }

    @Override
    public int hashCode() {
        return _proposal2Phase.hashCode();
    }

    public com.liferay.portal.model.CacheModel<Proposal2Phase> toCacheModel() {
        return _proposal2Phase.toCacheModel();
    }

    public Proposal2Phase toEscapedModel() {
        return new Proposal2PhaseWrapper(_proposal2Phase.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposal2Phase.toString();
    }

    public java.lang.String toXmlString() {
        return _proposal2Phase.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposal2Phase.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public Proposal2Phase getWrappedProposal2Phase() {
        return _proposal2Phase;
    }

    public Proposal2Phase getWrappedModel() {
        return _proposal2Phase;
    }

    public void resetOriginalValues() {
        _proposal2Phase.resetOriginalValues();
    }
}
