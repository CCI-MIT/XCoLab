package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanTemplateSection}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplateSection
 * @generated
 */
public class PlanTemplateSectionWrapper implements PlanTemplateSection,
    ModelWrapper<PlanTemplateSection> {
    private PlanTemplateSection _planTemplateSection;

    public PlanTemplateSectionWrapper(PlanTemplateSection planTemplateSection) {
        _planTemplateSection = planTemplateSection;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanTemplateSection.class;
    }

    @Override
    public String getModelClassName() {
        return PlanTemplateSection.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planTemplateId", getPlanTemplateId());
        attributes.put("planSectionId", getPlanSectionId());
        attributes.put("weight", getWeight());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planTemplateId = (Long) attributes.get("planTemplateId");

        if (planTemplateId != null) {
            setPlanTemplateId(planTemplateId);
        }

        Long planSectionId = (Long) attributes.get("planSectionId");

        if (planSectionId != null) {
            setPlanSectionId(planSectionId);
        }

        Integer weight = (Integer) attributes.get("weight");

        if (weight != null) {
            setWeight(weight);
        }
    }

    /**
    * Returns the primary key of this plan template section.
    *
    * @return the primary key of this plan template section
    */
    @Override
    public com.ext.portlet.service.persistence.PlanTemplateSectionPK getPrimaryKey() {
        return _planTemplateSection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan template section.
    *
    * @param primaryKey the primary key of this plan template section
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlanTemplateSectionPK primaryKey) {
        _planTemplateSection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan template ID of this plan template section.
    *
    * @return the plan template ID of this plan template section
    */
    @Override
    public long getPlanTemplateId() {
        return _planTemplateSection.getPlanTemplateId();
    }

    /**
    * Sets the plan template ID of this plan template section.
    *
    * @param planTemplateId the plan template ID of this plan template section
    */
    @Override
    public void setPlanTemplateId(long planTemplateId) {
        _planTemplateSection.setPlanTemplateId(planTemplateId);
    }

    /**
    * Returns the plan section ID of this plan template section.
    *
    * @return the plan section ID of this plan template section
    */
    @Override
    public long getPlanSectionId() {
        return _planTemplateSection.getPlanSectionId();
    }

    /**
    * Sets the plan section ID of this plan template section.
    *
    * @param planSectionId the plan section ID of this plan template section
    */
    @Override
    public void setPlanSectionId(long planSectionId) {
        _planTemplateSection.setPlanSectionId(planSectionId);
    }

    /**
    * Returns the weight of this plan template section.
    *
    * @return the weight of this plan template section
    */
    @Override
    public int getWeight() {
        return _planTemplateSection.getWeight();
    }

    /**
    * Sets the weight of this plan template section.
    *
    * @param weight the weight of this plan template section
    */
    @Override
    public void setWeight(int weight) {
        _planTemplateSection.setWeight(weight);
    }

    @Override
    public boolean isNew() {
        return _planTemplateSection.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planTemplateSection.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planTemplateSection.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planTemplateSection.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planTemplateSection.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planTemplateSection.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planTemplateSection.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planTemplateSection.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planTemplateSection.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planTemplateSection.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planTemplateSection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTemplateSectionWrapper((PlanTemplateSection) _planTemplateSection.clone());
    }

    @Override
    public int compareTo(PlanTemplateSection planTemplateSection) {
        return _planTemplateSection.compareTo(planTemplateSection);
    }

    @Override
    public int hashCode() {
        return _planTemplateSection.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanTemplateSection> toCacheModel() {
        return _planTemplateSection.toCacheModel();
    }

    @Override
    public PlanTemplateSection toEscapedModel() {
        return new PlanTemplateSectionWrapper(_planTemplateSection.toEscapedModel());
    }

    @Override
    public PlanTemplateSection toUnescapedModel() {
        return new PlanTemplateSectionWrapper(_planTemplateSection.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planTemplateSection.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planTemplateSection.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTemplateSection.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanTemplateSectionWrapper)) {
            return false;
        }

        PlanTemplateSectionWrapper planTemplateSectionWrapper = (PlanTemplateSectionWrapper) obj;

        if (Validator.equals(_planTemplateSection,
                    planTemplateSectionWrapper._planTemplateSection)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanTemplateSection getWrappedPlanTemplateSection() {
        return _planTemplateSection;
    }

    @Override
    public PlanTemplateSection getWrappedModel() {
        return _planTemplateSection;
    }

    @Override
    public void resetOriginalValues() {
        _planTemplateSection.resetOriginalValues();
    }
}
