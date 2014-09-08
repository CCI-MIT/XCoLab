package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanModelRun}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanModelRun
 * @generated
 */
public class PlanModelRunWrapper implements PlanModelRun,
    ModelWrapper<PlanModelRun> {
    private PlanModelRun _planModelRun;

    public PlanModelRunWrapper(PlanModelRun planModelRun) {
        _planModelRun = planModelRun;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanModelRun.class;
    }

    @Override
    public String getModelClassName() {
        return PlanModelRun.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("planId", getPlanId());
        attributes.put("scenarioId", getScenarioId());
        attributes.put("planVersion", getPlanVersion());
        attributes.put("version", getVersion());
        attributes.put("created", getCreated());
        attributes.put("updateAuthorId", getUpdateAuthorId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Long scenarioId = (Long) attributes.get("scenarioId");

        if (scenarioId != null) {
            setScenarioId(scenarioId);
        }

        Long planVersion = (Long) attributes.get("planVersion");

        if (planVersion != null) {
            setPlanVersion(planVersion);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }
    }

    /**
    * Returns the primary key of this plan model run.
    *
    * @return the primary key of this plan model run
    */
    @Override
    public long getPrimaryKey() {
        return _planModelRun.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan model run.
    *
    * @param primaryKey the primary key of this plan model run
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planModelRun.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan model run.
    *
    * @return the ID of this plan model run
    */
    @Override
    public long getId() {
        return _planModelRun.getId();
    }

    /**
    * Sets the ID of this plan model run.
    *
    * @param id the ID of this plan model run
    */
    @Override
    public void setId(long id) {
        _planModelRun.setId(id);
    }

    /**
    * Returns the plan ID of this plan model run.
    *
    * @return the plan ID of this plan model run
    */
    @Override
    public long getPlanId() {
        return _planModelRun.getPlanId();
    }

    /**
    * Sets the plan ID of this plan model run.
    *
    * @param planId the plan ID of this plan model run
    */
    @Override
    public void setPlanId(long planId) {
        _planModelRun.setPlanId(planId);
    }

    /**
    * Returns the scenario ID of this plan model run.
    *
    * @return the scenario ID of this plan model run
    */
    @Override
    public long getScenarioId() {
        return _planModelRun.getScenarioId();
    }

    /**
    * Sets the scenario ID of this plan model run.
    *
    * @param scenarioId the scenario ID of this plan model run
    */
    @Override
    public void setScenarioId(long scenarioId) {
        _planModelRun.setScenarioId(scenarioId);
    }

    /**
    * Returns the plan version of this plan model run.
    *
    * @return the plan version of this plan model run
    */
    @Override
    public long getPlanVersion() {
        return _planModelRun.getPlanVersion();
    }

    /**
    * Sets the plan version of this plan model run.
    *
    * @param planVersion the plan version of this plan model run
    */
    @Override
    public void setPlanVersion(long planVersion) {
        _planModelRun.setPlanVersion(planVersion);
    }

    /**
    * Returns the version of this plan model run.
    *
    * @return the version of this plan model run
    */
    @Override
    public long getVersion() {
        return _planModelRun.getVersion();
    }

    /**
    * Sets the version of this plan model run.
    *
    * @param version the version of this plan model run
    */
    @Override
    public void setVersion(long version) {
        _planModelRun.setVersion(version);
    }

    /**
    * Returns the created of this plan model run.
    *
    * @return the created of this plan model run
    */
    @Override
    public java.util.Date getCreated() {
        return _planModelRun.getCreated();
    }

    /**
    * Sets the created of this plan model run.
    *
    * @param created the created of this plan model run
    */
    @Override
    public void setCreated(java.util.Date created) {
        _planModelRun.setCreated(created);
    }

    /**
    * Returns the update author ID of this plan model run.
    *
    * @return the update author ID of this plan model run
    */
    @Override
    public long getUpdateAuthorId() {
        return _planModelRun.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan model run.
    *
    * @param updateAuthorId the update author ID of this plan model run
    */
    @Override
    public void setUpdateAuthorId(long updateAuthorId) {
        _planModelRun.setUpdateAuthorId(updateAuthorId);
    }

    @Override
    public boolean isNew() {
        return _planModelRun.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planModelRun.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planModelRun.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planModelRun.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planModelRun.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planModelRun.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planModelRun.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planModelRun.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planModelRun.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planModelRun.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planModelRun.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanModelRunWrapper((PlanModelRun) _planModelRun.clone());
    }

    @Override
    public int compareTo(PlanModelRun planModelRun) {
        return _planModelRun.compareTo(planModelRun);
    }

    @Override
    public int hashCode() {
        return _planModelRun.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanModelRun> toCacheModel() {
        return _planModelRun.toCacheModel();
    }

    @Override
    public PlanModelRun toEscapedModel() {
        return new PlanModelRunWrapper(_planModelRun.toEscapedModel());
    }

    @Override
    public PlanModelRun toUnescapedModel() {
        return new PlanModelRunWrapper(_planModelRun.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planModelRun.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planModelRun.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planModelRun.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanModelRunWrapper)) {
            return false;
        }

        PlanModelRunWrapper planModelRunWrapper = (PlanModelRunWrapper) obj;

        if (Validator.equals(_planModelRun, planModelRunWrapper._planModelRun)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanModelRun getWrappedPlanModelRun() {
        return _planModelRun;
    }

    @Override
    public PlanModelRun getWrappedModel() {
        return _planModelRun;
    }

    @Override
    public void resetOriginalValues() {
        _planModelRun.resetOriginalValues();
    }
}
