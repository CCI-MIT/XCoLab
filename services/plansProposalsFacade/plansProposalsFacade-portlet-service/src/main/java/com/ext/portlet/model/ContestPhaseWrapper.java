package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhase}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhase
 * @generated
 */
public class ContestPhaseWrapper implements ContestPhase,
    ModelWrapper<ContestPhase> {
    private ContestPhase _contestPhase;

    public ContestPhaseWrapper(ContestPhase contestPhase) {
        _contestPhase = contestPhase;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestPhase.class;
    }

    @Override
    public String getModelClassName() {
        return ContestPhase.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ContestPhasePK", getContestPhasePK());
        attributes.put("ContestPK", getContestPK());
        attributes.put("ContestPhaseType", getContestPhaseType());
        attributes.put("fellowScreeningActive", getFellowScreeningActive());
        attributes.put("contestPhaseAutopromote", getContestPhaseAutopromote());
        attributes.put("ContestPhaseDescriptionOverride",
            getContestPhaseDescriptionOverride());
        attributes.put("phaseActiveOverride", getPhaseActiveOverride());
        attributes.put("phaseInactiveOverride", getPhaseInactiveOverride());
        attributes.put("PhaseStartDate", getPhaseStartDate());
        attributes.put("PhaseEndDate", getPhaseEndDate());
        attributes.put("nextStatus", getNextStatus());
        attributes.put("created", getCreated());
        attributes.put("updated", getUpdated());
        attributes.put("authorId", getAuthorId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long ContestPhasePK = (Long) attributes.get("ContestPhasePK");

        if (ContestPhasePK != null) {
            setContestPhasePK(ContestPhasePK);
        }

        Long ContestPK = (Long) attributes.get("ContestPK");

        if (ContestPK != null) {
            setContestPK(ContestPK);
        }

        Long ContestPhaseType = (Long) attributes.get("ContestPhaseType");

        if (ContestPhaseType != null) {
            setContestPhaseType(ContestPhaseType);
        }

        Boolean fellowScreeningActive = (Boolean) attributes.get(
                "fellowScreeningActive");

        if (fellowScreeningActive != null) {
            setFellowScreeningActive(fellowScreeningActive);
        }

        String contestPhaseAutopromote = (String) attributes.get(
                "contestPhaseAutopromote");

        if (contestPhaseAutopromote != null) {
            setContestPhaseAutopromote(contestPhaseAutopromote);
        }

        String ContestPhaseDescriptionOverride = (String) attributes.get(
                "ContestPhaseDescriptionOverride");

        if (ContestPhaseDescriptionOverride != null) {
            setContestPhaseDescriptionOverride(ContestPhaseDescriptionOverride);
        }

        Boolean phaseActiveOverride = (Boolean) attributes.get(
                "phaseActiveOverride");

        if (phaseActiveOverride != null) {
            setPhaseActiveOverride(phaseActiveOverride);
        }

        Boolean phaseInactiveOverride = (Boolean) attributes.get(
                "phaseInactiveOverride");

        if (phaseInactiveOverride != null) {
            setPhaseInactiveOverride(phaseInactiveOverride);
        }

        Date PhaseStartDate = (Date) attributes.get("PhaseStartDate");

        if (PhaseStartDate != null) {
            setPhaseStartDate(PhaseStartDate);
        }

        Date PhaseEndDate = (Date) attributes.get("PhaseEndDate");

        if (PhaseEndDate != null) {
            setPhaseEndDate(PhaseEndDate);
        }

        String nextStatus = (String) attributes.get("nextStatus");

        if (nextStatus != null) {
            setNextStatus(nextStatus);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Date updated = (Date) attributes.get("updated");

        if (updated != null) {
            setUpdated(updated);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }
    }

    /**
    * Returns the primary key of this contest phase.
    *
    * @return the primary key of this contest phase
    */
    @Override
    public long getPrimaryKey() {
        return _contestPhase.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest phase.
    *
    * @param primaryKey the primary key of this contest phase
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contestPhase.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contest phase p k of this contest phase.
    *
    * @return the contest phase p k of this contest phase
    */
    @Override
    public long getContestPhasePK() {
        return _contestPhase.getContestPhasePK();
    }

    /**
    * Sets the contest phase p k of this contest phase.
    *
    * @param ContestPhasePK the contest phase p k of this contest phase
    */
    @Override
    public void setContestPhasePK(long ContestPhasePK) {
        _contestPhase.setContestPhasePK(ContestPhasePK);
    }

    /**
    * Returns the contest p k of this contest phase.
    *
    * @return the contest p k of this contest phase
    */
    @Override
    public long getContestPK() {
        return _contestPhase.getContestPK();
    }

    /**
    * Sets the contest p k of this contest phase.
    *
    * @param ContestPK the contest p k of this contest phase
    */
    @Override
    public void setContestPK(long ContestPK) {
        _contestPhase.setContestPK(ContestPK);
    }

    /**
    * Returns the contest phase type of this contest phase.
    *
    * @return the contest phase type of this contest phase
    */
    @Override
    public long getContestPhaseType() {
        return _contestPhase.getContestPhaseType();
    }

    /**
    * Sets the contest phase type of this contest phase.
    *
    * @param ContestPhaseType the contest phase type of this contest phase
    */
    @Override
    public void setContestPhaseType(long ContestPhaseType) {
        _contestPhase.setContestPhaseType(ContestPhaseType);
    }

    /**
    * Returns the fellow screening active of this contest phase.
    *
    * @return the fellow screening active of this contest phase
    */
    @Override
    public boolean getFellowScreeningActive() {
        return _contestPhase.getFellowScreeningActive();
    }

    /**
    * Returns <code>true</code> if this contest phase is fellow screening active.
    *
    * @return <code>true</code> if this contest phase is fellow screening active; <code>false</code> otherwise
    */
    @Override
    public boolean isFellowScreeningActive() {
        return _contestPhase.isFellowScreeningActive();
    }

    /**
    * Sets whether this contest phase is fellow screening active.
    *
    * @param fellowScreeningActive the fellow screening active of this contest phase
    */
    @Override
    public void setFellowScreeningActive(boolean fellowScreeningActive) {
        _contestPhase.setFellowScreeningActive(fellowScreeningActive);
    }

    /**
    * Returns the contest phase autopromote of this contest phase.
    *
    * @return the contest phase autopromote of this contest phase
    */
    @Override
    public java.lang.String getContestPhaseAutopromote() {
        return _contestPhase.getContestPhaseAutopromote();
    }

    /**
    * Sets the contest phase autopromote of this contest phase.
    *
    * @param contestPhaseAutopromote the contest phase autopromote of this contest phase
    */
    @Override
    public void setContestPhaseAutopromote(
        java.lang.String contestPhaseAutopromote) {
        _contestPhase.setContestPhaseAutopromote(contestPhaseAutopromote);
    }

    /**
    * Returns the contest phase description override of this contest phase.
    *
    * @return the contest phase description override of this contest phase
    */
    @Override
    public java.lang.String getContestPhaseDescriptionOverride() {
        return _contestPhase.getContestPhaseDescriptionOverride();
    }

    /**
    * Sets the contest phase description override of this contest phase.
    *
    * @param ContestPhaseDescriptionOverride the contest phase description override of this contest phase
    */
    @Override
    public void setContestPhaseDescriptionOverride(
        java.lang.String ContestPhaseDescriptionOverride) {
        _contestPhase.setContestPhaseDescriptionOverride(ContestPhaseDescriptionOverride);
    }

    /**
    * Returns the phase active override of this contest phase.
    *
    * @return the phase active override of this contest phase
    */
    @Override
    public boolean getPhaseActiveOverride() {
        return _contestPhase.getPhaseActiveOverride();
    }

    /**
    * Returns <code>true</code> if this contest phase is phase active override.
    *
    * @return <code>true</code> if this contest phase is phase active override; <code>false</code> otherwise
    */
    @Override
    public boolean isPhaseActiveOverride() {
        return _contestPhase.isPhaseActiveOverride();
    }

    /**
    * Sets whether this contest phase is phase active override.
    *
    * @param phaseActiveOverride the phase active override of this contest phase
    */
    @Override
    public void setPhaseActiveOverride(boolean phaseActiveOverride) {
        _contestPhase.setPhaseActiveOverride(phaseActiveOverride);
    }

    /**
    * Returns the phase inactive override of this contest phase.
    *
    * @return the phase inactive override of this contest phase
    */
    @Override
    public boolean getPhaseInactiveOverride() {
        return _contestPhase.getPhaseInactiveOverride();
    }

    /**
    * Returns <code>true</code> if this contest phase is phase inactive override.
    *
    * @return <code>true</code> if this contest phase is phase inactive override; <code>false</code> otherwise
    */
    @Override
    public boolean isPhaseInactiveOverride() {
        return _contestPhase.isPhaseInactiveOverride();
    }

    /**
    * Sets whether this contest phase is phase inactive override.
    *
    * @param phaseInactiveOverride the phase inactive override of this contest phase
    */
    @Override
    public void setPhaseInactiveOverride(boolean phaseInactiveOverride) {
        _contestPhase.setPhaseInactiveOverride(phaseInactiveOverride);
    }

    /**
    * Returns the phase start date of this contest phase.
    *
    * @return the phase start date of this contest phase
    */
    @Override
    public java.util.Date getPhaseStartDate() {
        return _contestPhase.getPhaseStartDate();
    }

    /**
    * Sets the phase start date of this contest phase.
    *
    * @param PhaseStartDate the phase start date of this contest phase
    */
    @Override
    public void setPhaseStartDate(java.util.Date PhaseStartDate) {
        _contestPhase.setPhaseStartDate(PhaseStartDate);
    }

    /**
    * Returns the phase end date of this contest phase.
    *
    * @return the phase end date of this contest phase
    */
    @Override
    public java.util.Date getPhaseEndDate() {
        return _contestPhase.getPhaseEndDate();
    }

    /**
    * Sets the phase end date of this contest phase.
    *
    * @param PhaseEndDate the phase end date of this contest phase
    */
    @Override
    public void setPhaseEndDate(java.util.Date PhaseEndDate) {
        _contestPhase.setPhaseEndDate(PhaseEndDate);
    }

    /**
    * Returns the next status of this contest phase.
    *
    * @return the next status of this contest phase
    */
    @Override
    public java.lang.String getNextStatus() {
        return _contestPhase.getNextStatus();
    }

    /**
    * Sets the next status of this contest phase.
    *
    * @param nextStatus the next status of this contest phase
    */
    @Override
    public void setNextStatus(java.lang.String nextStatus) {
        _contestPhase.setNextStatus(nextStatus);
    }

    /**
    * Returns the created of this contest phase.
    *
    * @return the created of this contest phase
    */
    @Override
    public java.util.Date getCreated() {
        return _contestPhase.getCreated();
    }

    /**
    * Sets the created of this contest phase.
    *
    * @param created the created of this contest phase
    */
    @Override
    public void setCreated(java.util.Date created) {
        _contestPhase.setCreated(created);
    }

    /**
    * Returns the updated of this contest phase.
    *
    * @return the updated of this contest phase
    */
    @Override
    public java.util.Date getUpdated() {
        return _contestPhase.getUpdated();
    }

    /**
    * Sets the updated of this contest phase.
    *
    * @param updated the updated of this contest phase
    */
    @Override
    public void setUpdated(java.util.Date updated) {
        _contestPhase.setUpdated(updated);
    }

    /**
    * Returns the author ID of this contest phase.
    *
    * @return the author ID of this contest phase
    */
    @Override
    public long getAuthorId() {
        return _contestPhase.getAuthorId();
    }

    /**
    * Sets the author ID of this contest phase.
    *
    * @param authorId the author ID of this contest phase
    */
    @Override
    public void setAuthorId(long authorId) {
        _contestPhase.setAuthorId(authorId);
    }

    @Override
    public boolean isNew() {
        return _contestPhase.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestPhase.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestPhase.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestPhase.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestPhase.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestPhase.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestPhase.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestPhase.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestPhase.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestPhase.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestPhase.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestPhaseWrapper((ContestPhase) _contestPhase.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.ContestPhase contestPhase) {
        return _contestPhase.compareTo(contestPhase);
    }

    @Override
    public int hashCode() {
        return _contestPhase.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ContestPhase> toCacheModel() {
        return _contestPhase.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ContestPhase toEscapedModel() {
        return new ContestPhaseWrapper(_contestPhase.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ContestPhase toUnescapedModel() {
        return new ContestPhaseWrapper(_contestPhase.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestPhase.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestPhase.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhase.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestPhaseWrapper)) {
            return false;
        }

        ContestPhaseWrapper contestPhaseWrapper = (ContestPhaseWrapper) obj;

        if (Validator.equals(_contestPhase, contestPhaseWrapper._contestPhase)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestPhase getWrappedContestPhase() {
        return _contestPhase;
    }

    @Override
    public ContestPhase getWrappedModel() {
        return _contestPhase;
    }

    @Override
    public void resetOriginalValues() {
        _contestPhase.resetOriginalValues();
    }
}
