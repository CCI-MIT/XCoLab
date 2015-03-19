package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanTemplate}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplate
 * @generated
 */
public class PlanTemplateWrapper implements PlanTemplate,
    ModelWrapper<PlanTemplate> {
    private PlanTemplate _planTemplate;

    public PlanTemplateWrapper(PlanTemplate planTemplate) {
        _planTemplate = planTemplate;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanTemplate.class;
    }

    @Override
    public String getModelClassName() {
        return PlanTemplate.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("name", getName());
        attributes.put("baseTemplateId", getBaseTemplateId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Long baseTemplateId = (Long) attributes.get("baseTemplateId");

        if (baseTemplateId != null) {
            setBaseTemplateId(baseTemplateId);
        }
    }

    /**
    * Returns the primary key of this plan template.
    *
    * @return the primary key of this plan template
    */
    @Override
    public long getPrimaryKey() {
        return _planTemplate.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan template.
    *
    * @param primaryKey the primary key of this plan template
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planTemplate.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan template.
    *
    * @return the ID of this plan template
    */
    @Override
    public long getId() {
        return _planTemplate.getId();
    }

    /**
    * Sets the ID of this plan template.
    *
    * @param id the ID of this plan template
    */
    @Override
    public void setId(long id) {
        _planTemplate.setId(id);
    }

    /**
    * Returns the name of this plan template.
    *
    * @return the name of this plan template
    */
    @Override
    public java.lang.String getName() {
        return _planTemplate.getName();
    }

    /**
    * Sets the name of this plan template.
    *
    * @param name the name of this plan template
    */
    @Override
    public void setName(java.lang.String name) {
        _planTemplate.setName(name);
    }

    /**
    * Returns the base template ID of this plan template.
    *
    * @return the base template ID of this plan template
    */
    @Override
    public java.lang.Long getBaseTemplateId() {
        return _planTemplate.getBaseTemplateId();
    }

    /**
    * Sets the base template ID of this plan template.
    *
    * @param baseTemplateId the base template ID of this plan template
    */
    @Override
    public void setBaseTemplateId(java.lang.Long baseTemplateId) {
        _planTemplate.setBaseTemplateId(baseTemplateId);
    }

    @Override
    public boolean isNew() {
        return _planTemplate.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planTemplate.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planTemplate.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planTemplate.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planTemplate.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planTemplate.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planTemplate.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planTemplate.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planTemplate.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planTemplate.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planTemplate.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTemplateWrapper((PlanTemplate) _planTemplate.clone());
    }

    @Override
    public int compareTo(PlanTemplate planTemplate) {
        return _planTemplate.compareTo(planTemplate);
    }

    @Override
    public int hashCode() {
        return _planTemplate.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanTemplate> toCacheModel() {
        return _planTemplate.toCacheModel();
    }

    @Override
    public PlanTemplate toEscapedModel() {
        return new PlanTemplateWrapper(_planTemplate.toEscapedModel());
    }

    @Override
    public PlanTemplate toUnescapedModel() {
        return new PlanTemplateWrapper(_planTemplate.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planTemplate.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planTemplate.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTemplate.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanTemplateWrapper)) {
            return false;
        }

        PlanTemplateWrapper planTemplateWrapper = (PlanTemplateWrapper) obj;

        if (Validator.equals(_planTemplate, planTemplateWrapper._planTemplate)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanTemplate getWrappedPlanTemplate() {
        return _planTemplate;
    }

    @Override
    public PlanTemplate getWrappedModel() {
        return _planTemplate;
    }

    @Override
    public void resetOriginalValues() {
        _planTemplate.resetOriginalValues();
    }
}
