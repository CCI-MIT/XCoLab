package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanModelRun}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanModelRun
 * @generated
 */
public class PlanModelRunWrapper implements PlanModelRun,
    ModelWrapper<PlanModelRun> {
    private PlanModelRun _planModelRun;

    public PlanModelRunWrapper(PlanModelRun planModelRun) {
        _planModelRun = planModelRun;
    }

    public Class<?> getModelClass() {
        return PlanModelRun.class;
    }

    public String getModelClassName() {
        return PlanModelRun.class.getName();
    }

    /**
    * Returns the primary key of this plan model run.
    *
    * @return the primary key of this plan model run
    */
    public long getPrimaryKey() {
        return _planModelRun.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan model run.
    *
    * @param primaryKey the primary key of this plan model run
    */
    public void setPrimaryKey(long primaryKey) {
        _planModelRun.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan model run.
    *
    * @return the ID of this plan model run
    */
    public long getId() {
        return _planModelRun.getId();
    }

    /**
    * Sets the ID of this plan model run.
    *
    * @param id the ID of this plan model run
    */
    public void setId(long id) {
        _planModelRun.setId(id);
    }

    /**
    * Returns the plan ID of this plan model run.
    *
    * @return the plan ID of this plan model run
    */
    public long getPlanId() {
        return _planModelRun.getPlanId();
    }

    /**
    * Sets the plan ID of this plan model run.
    *
    * @param planId the plan ID of this plan model run
    */
    public void setPlanId(long planId) {
        _planModelRun.setPlanId(planId);
    }

    /**
    * Returns the scenario ID of this plan model run.
    *
    * @return the scenario ID of this plan model run
    */
    public long getScenarioId() {
        return _planModelRun.getScenarioId();
    }

    /**
    * Sets the scenario ID of this plan model run.
    *
    * @param scenarioId the scenario ID of this plan model run
    */
    public void setScenarioId(long scenarioId) {
        _planModelRun.setScenarioId(scenarioId);
    }

    /**
    * Returns the plan version of this plan model run.
    *
    * @return the plan version of this plan model run
    */
    public long getPlanVersion() {
        return _planModelRun.getPlanVersion();
    }

    /**
    * Sets the plan version of this plan model run.
    *
    * @param planVersion the plan version of this plan model run
    */
    public void setPlanVersion(long planVersion) {
        _planModelRun.setPlanVersion(planVersion);
    }

    /**
    * Returns the version of this plan model run.
    *
    * @return the version of this plan model run
    */
    public long getVersion() {
        return _planModelRun.getVersion();
    }

    /**
    * Sets the version of this plan model run.
    *
    * @param version the version of this plan model run
    */
    public void setVersion(long version) {
        _planModelRun.setVersion(version);
    }

    /**
    * Returns the created of this plan model run.
    *
    * @return the created of this plan model run
    */
    public java.util.Date getCreated() {
        return _planModelRun.getCreated();
    }

    /**
    * Sets the created of this plan model run.
    *
    * @param created the created of this plan model run
    */
    public void setCreated(java.util.Date created) {
        _planModelRun.setCreated(created);
    }

    /**
    * Returns the update author ID of this plan model run.
    *
    * @return the update author ID of this plan model run
    */
    public long getUpdateAuthorId() {
        return _planModelRun.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan model run.
    *
    * @param updateAuthorId the update author ID of this plan model run
    */
    public void setUpdateAuthorId(long updateAuthorId) {
        _planModelRun.setUpdateAuthorId(updateAuthorId);
    }

    public boolean isNew() {
        return _planModelRun.isNew();
    }

    public void setNew(boolean n) {
        _planModelRun.setNew(n);
    }

    public boolean isCachedModel() {
        return _planModelRun.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planModelRun.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planModelRun.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planModelRun.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planModelRun.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planModelRun.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planModelRun.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanModelRunWrapper((PlanModelRun) _planModelRun.clone());
    }

    public int compareTo(PlanModelRun planModelRun) {
        return _planModelRun.compareTo(planModelRun);
    }

    @Override
    public int hashCode() {
        return _planModelRun.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanModelRun> toCacheModel() {
        return _planModelRun.toCacheModel();
    }

    public PlanModelRun toEscapedModel() {
        return new PlanModelRunWrapper(_planModelRun.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planModelRun.toString();
    }

    public java.lang.String toXmlString() {
        return _planModelRun.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planModelRun.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanModelRun getWrappedPlanModelRun() {
        return _planModelRun;
    }

    public PlanModelRun getWrappedModel() {
        return _planModelRun;
    }

    public void resetOriginalValues() {
        _planModelRun.resetOriginalValues();
    }
}
