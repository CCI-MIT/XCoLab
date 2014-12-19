package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanSectionPlanMap}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPlanMap
 * @generated
 */
public class PlanSectionPlanMapWrapper implements PlanSectionPlanMap,
    ModelWrapper<PlanSectionPlanMap> {
    private PlanSectionPlanMap _planSectionPlanMap;

    public PlanSectionPlanMapWrapper(PlanSectionPlanMap planSectionPlanMap) {
        _planSectionPlanMap = planSectionPlanMap;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanSectionPlanMap.class;
    }

    @Override
    public String getModelClassName() {
        return PlanSectionPlanMap.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("sectionId", getSectionId());
        attributes.put("relatedPlanId", getRelatedPlanId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long sectionId = (Long) attributes.get("sectionId");

        if (sectionId != null) {
            setSectionId(sectionId);
        }

        Long relatedPlanId = (Long) attributes.get("relatedPlanId");

        if (relatedPlanId != null) {
            setRelatedPlanId(relatedPlanId);
        }
    }

    /**
    * Returns the primary key of this plan section plan map.
    *
    * @return the primary key of this plan section plan map
    */
    @Override
    public com.ext.portlet.service.persistence.PlanSectionPlanMapPK getPrimaryKey() {
        return _planSectionPlanMap.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan section plan map.
    *
    * @param primaryKey the primary key of this plan section plan map
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlanSectionPlanMapPK primaryKey) {
        _planSectionPlanMap.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the section ID of this plan section plan map.
    *
    * @return the section ID of this plan section plan map
    */
    @Override
    public long getSectionId() {
        return _planSectionPlanMap.getSectionId();
    }

    /**
    * Sets the section ID of this plan section plan map.
    *
    * @param sectionId the section ID of this plan section plan map
    */
    @Override
    public void setSectionId(long sectionId) {
        _planSectionPlanMap.setSectionId(sectionId);
    }

    /**
    * Returns the related plan ID of this plan section plan map.
    *
    * @return the related plan ID of this plan section plan map
    */
    @Override
    public long getRelatedPlanId() {
        return _planSectionPlanMap.getRelatedPlanId();
    }

    /**
    * Sets the related plan ID of this plan section plan map.
    *
    * @param relatedPlanId the related plan ID of this plan section plan map
    */
    @Override
    public void setRelatedPlanId(long relatedPlanId) {
        _planSectionPlanMap.setRelatedPlanId(relatedPlanId);
    }

    @Override
    public boolean isNew() {
        return _planSectionPlanMap.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planSectionPlanMap.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planSectionPlanMap.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planSectionPlanMap.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planSectionPlanMap.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planSectionPlanMap.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planSectionPlanMap.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planSectionPlanMap.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planSectionPlanMap.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planSectionPlanMap.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planSectionPlanMap.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanSectionPlanMapWrapper((PlanSectionPlanMap) _planSectionPlanMap.clone());
    }

    @Override
    public int compareTo(PlanSectionPlanMap planSectionPlanMap) {
        return _planSectionPlanMap.compareTo(planSectionPlanMap);
    }

    @Override
    public int hashCode() {
        return _planSectionPlanMap.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanSectionPlanMap> toCacheModel() {
        return _planSectionPlanMap.toCacheModel();
    }

    @Override
    public PlanSectionPlanMap toEscapedModel() {
        return new PlanSectionPlanMapWrapper(_planSectionPlanMap.toEscapedModel());
    }

    @Override
    public PlanSectionPlanMap toUnescapedModel() {
        return new PlanSectionPlanMapWrapper(_planSectionPlanMap.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planSectionPlanMap.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planSectionPlanMap.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSectionPlanMap.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanSectionPlanMapWrapper)) {
            return false;
        }

        PlanSectionPlanMapWrapper planSectionPlanMapWrapper = (PlanSectionPlanMapWrapper) obj;

        if (Validator.equals(_planSectionPlanMap,
                    planSectionPlanMapWrapper._planSectionPlanMap)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanSectionPlanMap getWrappedPlanSectionPlanMap() {
        return _planSectionPlanMap;
    }

    @Override
    public PlanSectionPlanMap getWrappedModel() {
        return _planSectionPlanMap;
    }

    @Override
    public void resetOriginalValues() {
        _planSectionPlanMap.resetOriginalValues();
    }
}
