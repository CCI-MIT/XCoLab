package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanSection}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanSection
 * @generated
 */
public class PlanSectionWrapper implements PlanSection,
    ModelWrapper<PlanSection> {
    private PlanSection _planSection;

    public PlanSectionWrapper(PlanSection planSection) {
        _planSection = planSection;
    }

    public Class<?> getModelClass() {
        return PlanSection.class;
    }

    public String getModelClassName() {
        return PlanSection.class.getName();
    }

    /**
    * Returns the primary key of this plan section.
    *
    * @return the primary key of this plan section
    */
    public java.lang.Long getPrimaryKey() {
        return _planSection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan section.
    *
    * @param primaryKey the primary key of this plan section
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _planSection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan section.
    *
    * @return the ID of this plan section
    */
    public java.lang.Long getId() {
        return _planSection.getId();
    }

    /**
    * Sets the ID of this plan section.
    *
    * @param id the ID of this plan section
    */
    public void setId(java.lang.Long id) {
        _planSection.setId(id);
    }

    /**
    * Returns the plan section definition ID of this plan section.
    *
    * @return the plan section definition ID of this plan section
    */
    public java.lang.Long getPlanSectionDefinitionId() {
        return _planSection.getPlanSectionDefinitionId();
    }

    /**
    * Sets the plan section definition ID of this plan section.
    *
    * @param planSectionDefinitionId the plan section definition ID of this plan section
    */
    public void setPlanSectionDefinitionId(
        java.lang.Long planSectionDefinitionId) {
        _planSection.setPlanSectionDefinitionId(planSectionDefinitionId);
    }

    /**
    * Returns the plan ID of this plan section.
    *
    * @return the plan ID of this plan section
    */
    public java.lang.Long getPlanId() {
        return _planSection.getPlanId();
    }

    /**
    * Sets the plan ID of this plan section.
    *
    * @param planId the plan ID of this plan section
    */
    public void setPlanId(java.lang.Long planId) {
        _planSection.setPlanId(planId);
    }

    /**
    * Returns the content of this plan section.
    *
    * @return the content of this plan section
    */
    public java.lang.String getContent() {
        return _planSection.getContent();
    }

    /**
    * Sets the content of this plan section.
    *
    * @param content the content of this plan section
    */
    public void setContent(java.lang.String content) {
        _planSection.setContent(content);
    }

    /**
    * Returns the created of this plan section.
    *
    * @return the created of this plan section
    */
    public java.util.Date getCreated() {
        return _planSection.getCreated();
    }

    /**
    * Sets the created of this plan section.
    *
    * @param created the created of this plan section
    */
    public void setCreated(java.util.Date created) {
        _planSection.setCreated(created);
    }

    /**
    * Returns the version of this plan section.
    *
    * @return the version of this plan section
    */
    public java.lang.Long getVersion() {
        return _planSection.getVersion();
    }

    /**
    * Sets the version of this plan section.
    *
    * @param version the version of this plan section
    */
    public void setVersion(java.lang.Long version) {
        _planSection.setVersion(version);
    }

    /**
    * Returns the plan version of this plan section.
    *
    * @return the plan version of this plan section
    */
    public java.lang.Long getPlanVersion() {
        return _planSection.getPlanVersion();
    }

    /**
    * Sets the plan version of this plan section.
    *
    * @param planVersion the plan version of this plan section
    */
    public void setPlanVersion(java.lang.Long planVersion) {
        _planSection.setPlanVersion(planVersion);
    }

    /**
    * Returns the update author ID of this plan section.
    *
    * @return the update author ID of this plan section
    */
    public java.lang.Long getUpdateAuthorId() {
        return _planSection.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan section.
    *
    * @param updateAuthorId the update author ID of this plan section
    */
    public void setUpdateAuthorId(java.lang.Long updateAuthorId) {
        _planSection.setUpdateAuthorId(updateAuthorId);
    }

    public boolean isNew() {
        return _planSection.isNew();
    }

    public void setNew(boolean n) {
        _planSection.setNew(n);
    }

    public boolean isCachedModel() {
        return _planSection.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planSection.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planSection.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planSection.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planSection.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planSection.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planSection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanSectionWrapper((PlanSection) _planSection.clone());
    }

    public int compareTo(PlanSection planSection) {
        return _planSection.compareTo(planSection);
    }

    @Override
    public int hashCode() {
        return _planSection.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanSection> toCacheModel() {
        return _planSection.toCacheModel();
    }

    public PlanSection toEscapedModel() {
        return new PlanSectionWrapper(_planSection.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planSection.toString();
    }

    public java.lang.String toXmlString() {
        return _planSection.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSection.persist();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSection.store();
    }

    public com.ext.portlet.plans.model.PlanSectionDefinition getDefinition()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSection.getDefinition();
    }

    public void addPlanReference(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSection.addPlanReference(planId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getReferencedPlans()
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSection.getReferencedPlans();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanSection getWrappedPlanSection() {
        return _planSection;
    }

    public PlanSection getWrappedModel() {
        return _planSection;
    }

    public void resetOriginalValues() {
        _planSection.resetOriginalValues();
    }
}
