package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Proposal2Phase}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Proposal2Phase
 * @generated
 */
public class Proposal2PhaseWrapper implements Proposal2Phase,
    ModelWrapper<Proposal2Phase> {
    private Proposal2Phase _proposal2Phase;

    public Proposal2PhaseWrapper(Proposal2Phase proposal2Phase) {
        _proposal2Phase = proposal2Phase;
    }

    @Override
    public Class<?> getModelClass() {
        return Proposal2Phase.class;
    }

    @Override
    public String getModelClassName() {
        return Proposal2Phase.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("contestPhaseId", getContestPhaseId());
        attributes.put("versionFrom", getVersionFrom());
        attributes.put("versionTo", getVersionTo());
        attributes.put("sortWeight", getSortWeight());
        attributes.put("autopromoteCandidate", getAutopromoteCandidate());

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

        Integer versionFrom = (Integer) attributes.get("versionFrom");

        if (versionFrom != null) {
            setVersionFrom(versionFrom);
        }

        Integer versionTo = (Integer) attributes.get("versionTo");

        if (versionTo != null) {
            setVersionTo(versionTo);
        }

        Integer sortWeight = (Integer) attributes.get("sortWeight");

        if (sortWeight != null) {
            setSortWeight(sortWeight);
        }

        Boolean autopromoteCandidate = (Boolean) attributes.get(
                "autopromoteCandidate");

        if (autopromoteCandidate != null) {
            setAutopromoteCandidate(autopromoteCandidate);
        }
    }

    /**
    * Returns the primary key of this proposal2 phase.
    *
    * @return the primary key of this proposal2 phase
    */
    @Override
    public com.ext.portlet.service.persistence.Proposal2PhasePK getPrimaryKey() {
        return _proposal2Phase.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal2 phase.
    *
    * @param primaryKey the primary key of this proposal2 phase
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.Proposal2PhasePK primaryKey) {
        _proposal2Phase.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal2 phase.
    *
    * @return the proposal ID of this proposal2 phase
    */
    @Override
    public long getProposalId() {
        return _proposal2Phase.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal2 phase.
    *
    * @param proposalId the proposal ID of this proposal2 phase
    */
    @Override
    public void setProposalId(long proposalId) {
        _proposal2Phase.setProposalId(proposalId);
    }

    /**
    * Returns the contest phase ID of this proposal2 phase.
    *
    * @return the contest phase ID of this proposal2 phase
    */
    @Override
    public long getContestPhaseId() {
        return _proposal2Phase.getContestPhaseId();
    }

    /**
    * Sets the contest phase ID of this proposal2 phase.
    *
    * @param contestPhaseId the contest phase ID of this proposal2 phase
    */
    @Override
    public void setContestPhaseId(long contestPhaseId) {
        _proposal2Phase.setContestPhaseId(contestPhaseId);
    }

    /**
    * Returns the version from of this proposal2 phase.
    *
    * @return the version from of this proposal2 phase
    */
    @Override
    public int getVersionFrom() {
        return _proposal2Phase.getVersionFrom();
    }

    /**
    * Sets the version from of this proposal2 phase.
    *
    * @param versionFrom the version from of this proposal2 phase
    */
    @Override
    public void setVersionFrom(int versionFrom) {
        _proposal2Phase.setVersionFrom(versionFrom);
    }

    /**
    * Returns the version to of this proposal2 phase.
    *
    * @return the version to of this proposal2 phase
    */
    @Override
    public int getVersionTo() {
        return _proposal2Phase.getVersionTo();
    }

    /**
    * Sets the version to of this proposal2 phase.
    *
    * @param versionTo the version to of this proposal2 phase
    */
    @Override
    public void setVersionTo(int versionTo) {
        _proposal2Phase.setVersionTo(versionTo);
    }

    /**
    * Returns the sort weight of this proposal2 phase.
    *
    * @return the sort weight of this proposal2 phase
    */
    @Override
    public int getSortWeight() {
        return _proposal2Phase.getSortWeight();
    }

    /**
    * Sets the sort weight of this proposal2 phase.
    *
    * @param sortWeight the sort weight of this proposal2 phase
    */
    @Override
    public void setSortWeight(int sortWeight) {
        _proposal2Phase.setSortWeight(sortWeight);
    }

    /**
    * Returns the autopromote candidate of this proposal2 phase.
    *
    * @return the autopromote candidate of this proposal2 phase
    */
    @Override
    public boolean getAutopromoteCandidate() {
        return _proposal2Phase.getAutopromoteCandidate();
    }

    /**
    * Returns <code>true</code> if this proposal2 phase is autopromote candidate.
    *
    * @return <code>true</code> if this proposal2 phase is autopromote candidate; <code>false</code> otherwise
    */
    @Override
    public boolean isAutopromoteCandidate() {
        return _proposal2Phase.isAutopromoteCandidate();
    }

    /**
    * Sets whether this proposal2 phase is autopromote candidate.
    *
    * @param autopromoteCandidate the autopromote candidate of this proposal2 phase
    */
    @Override
    public void setAutopromoteCandidate(boolean autopromoteCandidate) {
        _proposal2Phase.setAutopromoteCandidate(autopromoteCandidate);
    }

    @Override
    public boolean isNew() {
        return _proposal2Phase.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposal2Phase.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposal2Phase.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposal2Phase.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposal2Phase.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposal2Phase.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposal2Phase.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposal2Phase.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposal2Phase.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposal2Phase.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposal2Phase.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new Proposal2PhaseWrapper((Proposal2Phase) _proposal2Phase.clone());
    }

    @Override
    public int compareTo(Proposal2Phase proposal2Phase) {
        return _proposal2Phase.compareTo(proposal2Phase);
    }

    @Override
    public int hashCode() {
        return _proposal2Phase.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<Proposal2Phase> toCacheModel() {
        return _proposal2Phase.toCacheModel();
    }

    @Override
    public Proposal2Phase toEscapedModel() {
        return new Proposal2PhaseWrapper(_proposal2Phase.toEscapedModel());
    }

    @Override
    public Proposal2Phase toUnescapedModel() {
        return new Proposal2PhaseWrapper(_proposal2Phase.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposal2Phase.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposal2Phase.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposal2Phase.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Proposal2PhaseWrapper)) {
            return false;
        }

        Proposal2PhaseWrapper proposal2PhaseWrapper = (Proposal2PhaseWrapper) obj;

        if (Validator.equals(_proposal2Phase,
                    proposal2PhaseWrapper._proposal2Phase)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Proposal2Phase getWrappedProposal2Phase() {
        return _proposal2Phase;
    }

    @Override
    public Proposal2Phase getWrappedModel() {
        return _proposal2Phase;
    }

    @Override
    public void resetOriginalValues() {
        _proposal2Phase.resetOriginalValues();
    }
}
