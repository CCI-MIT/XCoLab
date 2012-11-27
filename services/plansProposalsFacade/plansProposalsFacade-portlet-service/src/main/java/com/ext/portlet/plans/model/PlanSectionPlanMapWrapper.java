package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanSectionPlanMap}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanSectionPlanMap
 * @generated
 */
public class PlanSectionPlanMapWrapper implements PlanSectionPlanMap,
    ModelWrapper<PlanSectionPlanMap> {
    private PlanSectionPlanMap _planSectionPlanMap;

    public PlanSectionPlanMapWrapper(PlanSectionPlanMap planSectionPlanMap) {
        _planSectionPlanMap = planSectionPlanMap;
    }

    public Class<?> getModelClass() {
        return PlanSectionPlanMap.class;
    }

    public String getModelClassName() {
        return PlanSectionPlanMap.class.getName();
    }

    /**
    * Returns the primary key of this plan section plan map.
    *
    * @return the primary key of this plan section plan map
    */
    public com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK getPrimaryKey() {
        return _planSectionPlanMap.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan section plan map.
    *
    * @param primaryKey the primary key of this plan section plan map
    */
    public void setPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK primaryKey) {
        _planSectionPlanMap.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the section ID of this plan section plan map.
    *
    * @return the section ID of this plan section plan map
    */
    public java.lang.Long getSectionId() {
        return _planSectionPlanMap.getSectionId();
    }

    /**
    * Sets the section ID of this plan section plan map.
    *
    * @param sectionId the section ID of this plan section plan map
    */
    public void setSectionId(java.lang.Long sectionId) {
        _planSectionPlanMap.setSectionId(sectionId);
    }

    /**
    * Returns the related plan ID of this plan section plan map.
    *
    * @return the related plan ID of this plan section plan map
    */
    public java.lang.Long getRelatedPlanId() {
        return _planSectionPlanMap.getRelatedPlanId();
    }

    /**
    * Sets the related plan ID of this plan section plan map.
    *
    * @param relatedPlanId the related plan ID of this plan section plan map
    */
    public void setRelatedPlanId(java.lang.Long relatedPlanId) {
        _planSectionPlanMap.setRelatedPlanId(relatedPlanId);
    }

    public boolean isNew() {
        return _planSectionPlanMap.isNew();
    }

    public void setNew(boolean n) {
        _planSectionPlanMap.setNew(n);
    }

    public boolean isCachedModel() {
        return _planSectionPlanMap.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planSectionPlanMap.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planSectionPlanMap.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planSectionPlanMap.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planSectionPlanMap.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planSectionPlanMap.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planSectionPlanMap.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanSectionPlanMapWrapper((PlanSectionPlanMap) _planSectionPlanMap.clone());
    }

    public int compareTo(PlanSectionPlanMap planSectionPlanMap) {
        return _planSectionPlanMap.compareTo(planSectionPlanMap);
    }

    @Override
    public int hashCode() {
        return _planSectionPlanMap.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanSectionPlanMap> toCacheModel() {
        return _planSectionPlanMap.toCacheModel();
    }

    public PlanSectionPlanMap toEscapedModel() {
        return new PlanSectionPlanMapWrapper(_planSectionPlanMap.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planSectionPlanMap.toString();
    }

    public java.lang.String toXmlString() {
        return _planSectionPlanMap.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSectionPlanMap.persist();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSectionPlanMap.store();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanSectionPlanMap getWrappedPlanSectionPlanMap() {
        return _planSectionPlanMap;
    }

    public PlanSectionPlanMap getWrappedModel() {
        return _planSectionPlanMap;
    }

    public void resetOriginalValues() {
        _planSectionPlanMap.resetOriginalValues();
    }
}
