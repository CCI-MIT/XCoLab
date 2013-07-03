package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanItemPhaseMap}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanItemPhaseMap
 * @generated
 */
public class PlanItemPhaseMapWrapper implements PlanItemPhaseMap,
    ModelWrapper<PlanItemPhaseMap> {
    private PlanItemPhaseMap _planItemPhaseMap;

    public PlanItemPhaseMapWrapper(PlanItemPhaseMap planItemPhaseMap) {
        _planItemPhaseMap = planItemPhaseMap;
    }

    public Class<?> getModelClass() {
        return PlanItemPhaseMap.class;
    }

    public String getModelClassName() {
        return PlanItemPhaseMap.class.getName();
    }

    /**
    * Returns the primary key of this plan item phase map.
    *
    * @return the primary key of this plan item phase map
    */
    public long getPrimaryKey() {
        return _planItemPhaseMap.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan item phase map.
    *
    * @param primaryKey the primary key of this plan item phase map
    */
    public void setPrimaryKey(long primaryKey) {
        _planItemPhaseMap.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan item phase map.
    *
    * @return the ID of this plan item phase map
    */
    public long getId() {
        return _planItemPhaseMap.getId();
    }

    /**
    * Sets the ID of this plan item phase map.
    *
    * @param id the ID of this plan item phase map
    */
    public void setId(long id) {
        _planItemPhaseMap.setId(id);
    }

    /**
    * Returns the plan ID of this plan item phase map.
    *
    * @return the plan ID of this plan item phase map
    */
    public long getPlanId() {
        return _planItemPhaseMap.getPlanId();
    }

    /**
    * Sets the plan ID of this plan item phase map.
    *
    * @param planId the plan ID of this plan item phase map
    */
    public void setPlanId(long planId) {
        _planItemPhaseMap.setPlanId(planId);
    }

    public boolean isNew() {
        return _planItemPhaseMap.isNew();
    }

    public void setNew(boolean n) {
        _planItemPhaseMap.setNew(n);
    }

    public boolean isCachedModel() {
        return _planItemPhaseMap.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planItemPhaseMap.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planItemPhaseMap.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planItemPhaseMap.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planItemPhaseMap.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planItemPhaseMap.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planItemPhaseMap.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanItemPhaseMapWrapper((PlanItemPhaseMap) _planItemPhaseMap.clone());
    }

    public int compareTo(PlanItemPhaseMap planItemPhaseMap) {
        return _planItemPhaseMap.compareTo(planItemPhaseMap);
    }

    @Override
    public int hashCode() {
        return _planItemPhaseMap.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanItemPhaseMap> toCacheModel() {
        return _planItemPhaseMap.toCacheModel();
    }

    public PlanItemPhaseMap toEscapedModel() {
        return new PlanItemPhaseMapWrapper(_planItemPhaseMap.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planItemPhaseMap.toString();
    }

    public java.lang.String toXmlString() {
        return _planItemPhaseMap.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemPhaseMap.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanItemPhaseMap getWrappedPlanItemPhaseMap() {
        return _planItemPhaseMap;
    }

    public PlanItemPhaseMap getWrappedModel() {
        return _planItemPhaseMap;
    }

    public void resetOriginalValues() {
        _planItemPhaseMap.resetOriginalValues();
    }
}
