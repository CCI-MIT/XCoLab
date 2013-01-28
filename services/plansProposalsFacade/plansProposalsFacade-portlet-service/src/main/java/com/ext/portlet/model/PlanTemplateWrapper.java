package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTemplate}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTemplate
 * @generated
 */
public class PlanTemplateWrapper implements PlanTemplate,
    ModelWrapper<PlanTemplate> {
    private PlanTemplate _planTemplate;

    public PlanTemplateWrapper(PlanTemplate planTemplate) {
        _planTemplate = planTemplate;
    }

    public Class<?> getModelClass() {
        return PlanTemplate.class;
    }

    public String getModelClassName() {
        return PlanTemplate.class.getName();
    }

    /**
    * Returns the primary key of this plan template.
    *
    * @return the primary key of this plan template
    */
    public long getPrimaryKey() {
        return _planTemplate.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan template.
    *
    * @param primaryKey the primary key of this plan template
    */
    public void setPrimaryKey(long primaryKey) {
        _planTemplate.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan template.
    *
    * @return the ID of this plan template
    */
    public long getId() {
        return _planTemplate.getId();
    }

    /**
    * Sets the ID of this plan template.
    *
    * @param id the ID of this plan template
    */
    public void setId(long id) {
        _planTemplate.setId(id);
    }

    /**
    * Returns the name of this plan template.
    *
    * @return the name of this plan template
    */
    public java.lang.String getName() {
        return _planTemplate.getName();
    }

    /**
    * Sets the name of this plan template.
    *
    * @param name the name of this plan template
    */
    public void setName(java.lang.String name) {
        _planTemplate.setName(name);
    }

    public boolean isNew() {
        return _planTemplate.isNew();
    }

    public void setNew(boolean n) {
        _planTemplate.setNew(n);
    }

    public boolean isCachedModel() {
        return _planTemplate.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planTemplate.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planTemplate.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planTemplate.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planTemplate.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planTemplate.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planTemplate.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTemplateWrapper((PlanTemplate) _planTemplate.clone());
    }

    public int compareTo(PlanTemplate planTemplate) {
        return _planTemplate.compareTo(planTemplate);
    }

    @Override
    public int hashCode() {
        return _planTemplate.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanTemplate> toCacheModel() {
        return _planTemplate.toCacheModel();
    }

    public PlanTemplate toEscapedModel() {
        return new PlanTemplateWrapper(_planTemplate.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planTemplate.toString();
    }

    public java.lang.String toXmlString() {
        return _planTemplate.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTemplate.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanTemplate getWrappedPlanTemplate() {
        return _planTemplate;
    }

    public PlanTemplate getWrappedModel() {
        return _planTemplate;
    }

    public void resetOriginalValues() {
        _planTemplate.resetOriginalValues();
    }
}
