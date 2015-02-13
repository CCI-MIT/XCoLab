package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestSchedule}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestSchedule
 * @generated
 */
public class ContestScheduleWrapper implements ContestSchedule,
    ModelWrapper<ContestSchedule> {
    private ContestSchedule _contestSchedule;

    public ContestScheduleWrapper(ContestSchedule contestSchedule) {
        _contestSchedule = contestSchedule;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestSchedule.class;
    }

    @Override
    public String getModelClassName() {
        return ContestSchedule.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ContestSchedulePK", getContestSchedulePK());
        attributes.put("ContestPK", getContestPK());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("status", getStatus());
        attributes.put("invisible", getInvisible());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long ContestSchedulePK = (Long) attributes.get("ContestSchedulePK");

        if (ContestSchedulePK != null) {
            setContestSchedulePK(ContestSchedulePK);
        }

        Long ContestPK = (Long) attributes.get("ContestPK");

        if (ContestPK != null) {
            setContestPK(ContestPK);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Boolean invisible = (Boolean) attributes.get("invisible");

        if (invisible != null) {
            setInvisible(invisible);
        }
    }

    /**
    * Returns the primary key of this contest schedule.
    *
    * @return the primary key of this contest schedule
    */
    @Override
    public long getPrimaryKey() {
        return _contestSchedule.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest schedule.
    *
    * @param primaryKey the primary key of this contest schedule
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contestSchedule.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contest schedule p k of this contest schedule.
    *
    * @return the contest schedule p k of this contest schedule
    */
    @Override
    public long getContestSchedulePK() {
        return _contestSchedule.getContestSchedulePK();
    }

    /**
    * Sets the contest schedule p k of this contest schedule.
    *
    * @param ContestSchedulePK the contest schedule p k of this contest schedule
    */
    @Override
    public void setContestSchedulePK(long ContestSchedulePK) {
        _contestSchedule.setContestSchedulePK(ContestSchedulePK);
    }

    /**
    * Returns the contest p k of this contest schedule.
    *
    * @return the contest p k of this contest schedule
    */
    @Override
    public long getContestPK() {
        return _contestSchedule.getContestPK();
    }

    /**
    * Sets the contest p k of this contest schedule.
    *
    * @param ContestPK the contest p k of this contest schedule
    */
    @Override
    public void setContestPK(long ContestPK) {
        _contestSchedule.setContestPK(ContestPK);
    }

    /**
    * Returns the name of this contest schedule.
    *
    * @return the name of this contest schedule
    */
    @Override
    public java.lang.String getName() {
        return _contestSchedule.getName();
    }

    /**
    * Sets the name of this contest schedule.
    *
    * @param name the name of this contest schedule
    */
    @Override
    public void setName(java.lang.String name) {
        _contestSchedule.setName(name);
    }

    /**
    * Returns the description of this contest schedule.
    *
    * @return the description of this contest schedule
    */
    @Override
    public java.lang.String getDescription() {
        return _contestSchedule.getDescription();
    }

    /**
    * Sets the description of this contest schedule.
    *
    * @param description the description of this contest schedule
    */
    @Override
    public void setDescription(java.lang.String description) {
        _contestSchedule.setDescription(description);
    }

    /**
    * Returns the status of this contest schedule.
    *
    * @return the status of this contest schedule
    */
    @Override
    public java.lang.String getStatus() {
        return _contestSchedule.getStatus();
    }

    /**
    * Sets the status of this contest schedule.
    *
    * @param status the status of this contest schedule
    */
    @Override
    public void setStatus(java.lang.String status) {
        _contestSchedule.setStatus(status);
    }

    /**
    * Returns the invisible of this contest schedule.
    *
    * @return the invisible of this contest schedule
    */
    @Override
    public boolean getInvisible() {
        return _contestSchedule.getInvisible();
    }

    /**
    * Returns <code>true</code> if this contest schedule is invisible.
    *
    * @return <code>true</code> if this contest schedule is invisible; <code>false</code> otherwise
    */
    @Override
    public boolean isInvisible() {
        return _contestSchedule.isInvisible();
    }

    /**
    * Sets whether this contest schedule is invisible.
    *
    * @param invisible the invisible of this contest schedule
    */
    @Override
    public void setInvisible(boolean invisible) {
        _contestSchedule.setInvisible(invisible);
    }

    @Override
    public boolean isNew() {
        return _contestSchedule.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestSchedule.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestSchedule.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestSchedule.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestSchedule.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestSchedule.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestSchedule.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestSchedule.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestSchedule.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestSchedule.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestSchedule.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestScheduleWrapper((ContestSchedule) _contestSchedule.clone());
    }

    @Override
    public int compareTo(ContestSchedule contestSchedule) {
        return _contestSchedule.compareTo(contestSchedule);
    }

    @Override
    public int hashCode() {
        return _contestSchedule.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ContestSchedule> toCacheModel() {
        return _contestSchedule.toCacheModel();
    }

    @Override
    public ContestSchedule toEscapedModel() {
        return new ContestScheduleWrapper(_contestSchedule.toEscapedModel());
    }

    @Override
    public ContestSchedule toUnescapedModel() {
        return new ContestScheduleWrapper(_contestSchedule.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestSchedule.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestSchedule.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestSchedule.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestScheduleWrapper)) {
            return false;
        }

        ContestScheduleWrapper contestScheduleWrapper = (ContestScheduleWrapper) obj;

        if (Validator.equals(_contestSchedule,
                    contestScheduleWrapper._contestSchedule)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestSchedule getWrappedContestSchedule() {
        return _contestSchedule;
    }

    @Override
    public ContestSchedule getWrappedModel() {
        return _contestSchedule;
    }

    @Override
    public void resetOriginalValues() {
        _contestSchedule.resetOriginalValues();
    }
}
