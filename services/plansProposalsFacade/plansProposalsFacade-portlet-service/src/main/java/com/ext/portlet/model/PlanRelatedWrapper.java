package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanRelated}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanRelated
 * @generated
 */
public class PlanRelatedWrapper implements PlanRelated,
    ModelWrapper<PlanRelated> {
    private PlanRelated _planRelated;

    public PlanRelatedWrapper(PlanRelated planRelated) {
        _planRelated = planRelated;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanRelated.class;
    }

    @Override
    public String getModelClassName() {
        return PlanRelated.class.getName();
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
    * Returns the primary key of this plan related.
    *
    * @return the primary key of this plan related
    */
    @Override
    public com.ext.portlet.service.persistence.PlanRelatedPK getPrimaryKey() {
        return _planRelated.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan related.
    *
    * @param primaryKey the primary key of this plan related
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlanRelatedPK primaryKey) {
        _planRelated.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the section ID of this plan related.
    *
    * @return the section ID of this plan related
    */
    @Override
    public long getSectionId() {
        return _planRelated.getSectionId();
    }

    /**
    * Sets the section ID of this plan related.
    *
    * @param sectionId the section ID of this plan related
    */
    @Override
    public void setSectionId(long sectionId) {
        _planRelated.setSectionId(sectionId);
    }

    /**
    * Returns the related plan ID of this plan related.
    *
    * @return the related plan ID of this plan related
    */
    @Override
    public long getRelatedPlanId() {
        return _planRelated.getRelatedPlanId();
    }

    /**
    * Sets the related plan ID of this plan related.
    *
    * @param relatedPlanId the related plan ID of this plan related
    */
    @Override
    public void setRelatedPlanId(long relatedPlanId) {
        _planRelated.setRelatedPlanId(relatedPlanId);
    }

    @Override
    public boolean isNew() {
        return _planRelated.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planRelated.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planRelated.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planRelated.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planRelated.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planRelated.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planRelated.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planRelated.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planRelated.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planRelated.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planRelated.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanRelatedWrapper((PlanRelated) _planRelated.clone());
    }

    @Override
    public int compareTo(PlanRelated planRelated) {
        return _planRelated.compareTo(planRelated);
    }

    @Override
    public int hashCode() {
        return _planRelated.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanRelated> toCacheModel() {
        return _planRelated.toCacheModel();
    }

    @Override
    public PlanRelated toEscapedModel() {
        return new PlanRelatedWrapper(_planRelated.toEscapedModel());
    }

    @Override
    public PlanRelated toUnescapedModel() {
        return new PlanRelatedWrapper(_planRelated.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planRelated.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planRelated.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planRelated.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanRelatedWrapper)) {
            return false;
        }

        PlanRelatedWrapper planRelatedWrapper = (PlanRelatedWrapper) obj;

        if (Validator.equals(_planRelated, planRelatedWrapper._planRelated)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanRelated getWrappedPlanRelated() {
        return _planRelated;
    }

    @Override
    public PlanRelated getWrappedModel() {
        return _planRelated;
    }

    @Override
    public void resetOriginalValues() {
        _planRelated.resetOriginalValues();
    }
}
