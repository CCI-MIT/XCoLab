package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTemplateSection}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTemplateSection
 * @generated
 */
public class PlanTemplateSectionWrapper implements PlanTemplateSection,
    ModelWrapper<PlanTemplateSection> {
    private PlanTemplateSection _planTemplateSection;

    public PlanTemplateSectionWrapper(PlanTemplateSection planTemplateSection) {
        _planTemplateSection = planTemplateSection;
    }

    public Class<?> getModelClass() {
        return PlanTemplateSection.class;
    }

    public String getModelClassName() {
        return PlanTemplateSection.class.getName();
    }

    /**
    * Returns the primary key of this plan template section.
    *
    * @return the primary key of this plan template section
    */
    public com.ext.portlet.service.persistence.PlanTemplateSectionPK getPrimaryKey() {
        return _planTemplateSection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan template section.
    *
    * @param primaryKey the primary key of this plan template section
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlanTemplateSectionPK primaryKey) {
        _planTemplateSection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan template ID of this plan template section.
    *
    * @return the plan template ID of this plan template section
    */
    public long getPlanTemplateId() {
        return _planTemplateSection.getPlanTemplateId();
    }

    /**
    * Sets the plan template ID of this plan template section.
    *
    * @param planTemplateId the plan template ID of this plan template section
    */
    public void setPlanTemplateId(long planTemplateId) {
        _planTemplateSection.setPlanTemplateId(planTemplateId);
    }

    /**
    * Returns the plan section ID of this plan template section.
    *
    * @return the plan section ID of this plan template section
    */
    public long getPlanSectionId() {
        return _planTemplateSection.getPlanSectionId();
    }

    /**
    * Sets the plan section ID of this plan template section.
    *
    * @param planSectionId the plan section ID of this plan template section
    */
    public void setPlanSectionId(long planSectionId) {
        _planTemplateSection.setPlanSectionId(planSectionId);
    }

    /**
    * Returns the weight of this plan template section.
    *
    * @return the weight of this plan template section
    */
    public int getWeight() {
        return _planTemplateSection.getWeight();
    }

    /**
    * Sets the weight of this plan template section.
    *
    * @param weight the weight of this plan template section
    */
    public void setWeight(int weight) {
        _planTemplateSection.setWeight(weight);
    }

    public boolean isNew() {
        return _planTemplateSection.isNew();
    }

    public void setNew(boolean n) {
        _planTemplateSection.setNew(n);
    }

    public boolean isCachedModel() {
        return _planTemplateSection.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planTemplateSection.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planTemplateSection.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planTemplateSection.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planTemplateSection.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planTemplateSection.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planTemplateSection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTemplateSectionWrapper((PlanTemplateSection) _planTemplateSection.clone());
    }

    public int compareTo(PlanTemplateSection planTemplateSection) {
        return _planTemplateSection.compareTo(planTemplateSection);
    }

    @Override
    public int hashCode() {
        return _planTemplateSection.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanTemplateSection> toCacheModel() {
        return _planTemplateSection.toCacheModel();
    }

    public PlanTemplateSection toEscapedModel() {
        return new PlanTemplateSectionWrapper(_planTemplateSection.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planTemplateSection.toString();
    }

    public java.lang.String toXmlString() {
        return _planTemplateSection.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTemplateSection.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanTemplateSection getWrappedPlanTemplateSection() {
        return _planTemplateSection;
    }

    public PlanTemplateSection getWrappedModel() {
        return _planTemplateSection;
    }

    public void resetOriginalValues() {
        _planTemplateSection.resetOriginalValues();
    }
}
