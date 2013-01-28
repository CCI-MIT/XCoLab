package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhase}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhase
 * @generated
 */
public class ContestPhaseWrapper implements ContestPhase,
    ModelWrapper<ContestPhase> {
    private ContestPhase _contestPhase;

    public ContestPhaseWrapper(ContestPhase contestPhase) {
        _contestPhase = contestPhase;
    }

    public Class<?> getModelClass() {
        return ContestPhase.class;
    }

    public String getModelClassName() {
        return ContestPhase.class.getName();
    }

    /**
    * Returns the primary key of this contest phase.
    *
    * @return the primary key of this contest phase
    */
    public long getPrimaryKey() {
        return _contestPhase.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest phase.
    *
    * @param primaryKey the primary key of this contest phase
    */
    public void setPrimaryKey(long primaryKey) {
        _contestPhase.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contest phase p k of this contest phase.
    *
    * @return the contest phase p k of this contest phase
    */
    public long getContestPhasePK() {
        return _contestPhase.getContestPhasePK();
    }

    /**
    * Sets the contest phase p k of this contest phase.
    *
    * @param ContestPhasePK the contest phase p k of this contest phase
    */
    public void setContestPhasePK(long ContestPhasePK) {
        _contestPhase.setContestPhasePK(ContestPhasePK);
    }

    /**
    * Returns the contest p k of this contest phase.
    *
    * @return the contest p k of this contest phase
    */
    public long getContestPK() {
        return _contestPhase.getContestPK();
    }

    /**
    * Sets the contest p k of this contest phase.
    *
    * @param ContestPK the contest p k of this contest phase
    */
    public void setContestPK(long ContestPK) {
        _contestPhase.setContestPK(ContestPK);
    }

    /**
    * Returns the contest phase type of this contest phase.
    *
    * @return the contest phase type of this contest phase
    */
    public long getContestPhaseType() {
        return _contestPhase.getContestPhaseType();
    }

    /**
    * Sets the contest phase type of this contest phase.
    *
    * @param ContestPhaseType the contest phase type of this contest phase
    */
    public void setContestPhaseType(long ContestPhaseType) {
        _contestPhase.setContestPhaseType(ContestPhaseType);
    }

    /**
    * Returns the contest phase description override of this contest phase.
    *
    * @return the contest phase description override of this contest phase
    */
    public java.lang.String getContestPhaseDescriptionOverride() {
        return _contestPhase.getContestPhaseDescriptionOverride();
    }

    /**
    * Sets the contest phase description override of this contest phase.
    *
    * @param ContestPhaseDescriptionOverride the contest phase description override of this contest phase
    */
    public void setContestPhaseDescriptionOverride(
        java.lang.String ContestPhaseDescriptionOverride) {
        _contestPhase.setContestPhaseDescriptionOverride(ContestPhaseDescriptionOverride);
    }

    /**
    * Returns the phase active override of this contest phase.
    *
    * @return the phase active override of this contest phase
    */
    public boolean getPhaseActiveOverride() {
        return _contestPhase.getPhaseActiveOverride();
    }

    /**
    * Returns <code>true</code> if this contest phase is phase active override.
    *
    * @return <code>true</code> if this contest phase is phase active override; <code>false</code> otherwise
    */
    public boolean isPhaseActiveOverride() {
        return _contestPhase.isPhaseActiveOverride();
    }

    /**
    * Sets whether this contest phase is phase active override.
    *
    * @param phaseActiveOverride the phase active override of this contest phase
    */
    public void setPhaseActiveOverride(boolean phaseActiveOverride) {
        _contestPhase.setPhaseActiveOverride(phaseActiveOverride);
    }

    /**
    * Returns the phase start date of this contest phase.
    *
    * @return the phase start date of this contest phase
    */
    public java.util.Date getPhaseStartDate() {
        return _contestPhase.getPhaseStartDate();
    }

    /**
    * Sets the phase start date of this contest phase.
    *
    * @param PhaseStartDate the phase start date of this contest phase
    */
    public void setPhaseStartDate(java.util.Date PhaseStartDate) {
        _contestPhase.setPhaseStartDate(PhaseStartDate);
    }

    /**
    * Returns the phase end date of this contest phase.
    *
    * @return the phase end date of this contest phase
    */
    public java.util.Date getPhaseEndDate() {
        return _contestPhase.getPhaseEndDate();
    }

    /**
    * Sets the phase end date of this contest phase.
    *
    * @param PhaseEndDate the phase end date of this contest phase
    */
    public void setPhaseEndDate(java.util.Date PhaseEndDate) {
        _contestPhase.setPhaseEndDate(PhaseEndDate);
    }

    /**
    * Returns the next status of this contest phase.
    *
    * @return the next status of this contest phase
    */
    public java.lang.String getNextStatus() {
        return _contestPhase.getNextStatus();
    }

    /**
    * Sets the next status of this contest phase.
    *
    * @param nextStatus the next status of this contest phase
    */
    public void setNextStatus(java.lang.String nextStatus) {
        _contestPhase.setNextStatus(nextStatus);
    }

    /**
    * Returns the created of this contest phase.
    *
    * @return the created of this contest phase
    */
    public java.util.Date getCreated() {
        return _contestPhase.getCreated();
    }

    /**
    * Sets the created of this contest phase.
    *
    * @param created the created of this contest phase
    */
    public void setCreated(java.util.Date created) {
        _contestPhase.setCreated(created);
    }

    /**
    * Returns the updated of this contest phase.
    *
    * @return the updated of this contest phase
    */
    public java.util.Date getUpdated() {
        return _contestPhase.getUpdated();
    }

    /**
    * Sets the updated of this contest phase.
    *
    * @param updated the updated of this contest phase
    */
    public void setUpdated(java.util.Date updated) {
        _contestPhase.setUpdated(updated);
    }

    /**
    * Returns the author ID of this contest phase.
    *
    * @return the author ID of this contest phase
    */
    public long getAuthorId() {
        return _contestPhase.getAuthorId();
    }

    /**
    * Sets the author ID of this contest phase.
    *
    * @param authorId the author ID of this contest phase
    */
    public void setAuthorId(long authorId) {
        _contestPhase.setAuthorId(authorId);
    }

    public boolean isNew() {
        return _contestPhase.isNew();
    }

    public void setNew(boolean n) {
        _contestPhase.setNew(n);
    }

    public boolean isCachedModel() {
        return _contestPhase.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _contestPhase.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _contestPhase.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _contestPhase.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestPhase.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestPhase.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestPhase.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestPhaseWrapper((ContestPhase) _contestPhase.clone());
    }

    public int compareTo(ContestPhase contestPhase) {
        return _contestPhase.compareTo(contestPhase);
    }

    @Override
    public int hashCode() {
        return _contestPhase.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ContestPhase> toCacheModel() {
        return _contestPhase.toCacheModel();
    }

    public ContestPhase toEscapedModel() {
        return new ContestPhaseWrapper(_contestPhase.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestPhase.toString();
    }

    public java.lang.String toXmlString() {
        return _contestPhase.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhase.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ContestPhase getWrappedContestPhase() {
        return _contestPhase;
    }

    public ContestPhase getWrappedModel() {
        return _contestPhase;
    }

    public void resetOriginalValues() {
        _contestPhase.resetOriginalValues();
    }
}
