package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanSection}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSection
 * @generated
 */
public class PlanSectionWrapper implements PlanSection,
    ModelWrapper<PlanSection> {
    private PlanSection _planSection;

    public PlanSectionWrapper(PlanSection planSection) {
        _planSection = planSection;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanSection.class;
    }

    @Override
    public String getModelClassName() {
        return PlanSection.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("planSectionDefinitionId", getPlanSectionDefinitionId());
        attributes.put("planId", getPlanId());
        attributes.put("content", getContent());
        attributes.put("numericValue", getNumericValue());
        attributes.put("created", getCreated());
        attributes.put("version", getVersion());
        attributes.put("planVersion", getPlanVersion());
        attributes.put("updateAuthorId", getUpdateAuthorId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long planSectionDefinitionId = (Long) attributes.get(
                "planSectionDefinitionId");

        if (planSectionDefinitionId != null) {
            setPlanSectionDefinitionId(planSectionDefinitionId);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        String content = (String) attributes.get("content");

        if (content != null) {
            setContent(content);
        }

        Long numericValue = (Long) attributes.get("numericValue");

        if (numericValue != null) {
            setNumericValue(numericValue);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Long planVersion = (Long) attributes.get("planVersion");

        if (planVersion != null) {
            setPlanVersion(planVersion);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }
    }

    /**
    * Returns the primary key of this plan section.
    *
    * @return the primary key of this plan section
    */
    @Override
    public long getPrimaryKey() {
        return _planSection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan section.
    *
    * @param primaryKey the primary key of this plan section
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planSection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan section.
    *
    * @return the ID of this plan section
    */
    @Override
    public long getId() {
        return _planSection.getId();
    }

    /**
    * Sets the ID of this plan section.
    *
    * @param id the ID of this plan section
    */
    @Override
    public void setId(long id) {
        _planSection.setId(id);
    }

    /**
    * Returns the plan section definition ID of this plan section.
    *
    * @return the plan section definition ID of this plan section
    */
    @Override
    public long getPlanSectionDefinitionId() {
        return _planSection.getPlanSectionDefinitionId();
    }

    /**
    * Sets the plan section definition ID of this plan section.
    *
    * @param planSectionDefinitionId the plan section definition ID of this plan section
    */
    @Override
    public void setPlanSectionDefinitionId(long planSectionDefinitionId) {
        _planSection.setPlanSectionDefinitionId(planSectionDefinitionId);
    }

    /**
    * Returns the plan ID of this plan section.
    *
    * @return the plan ID of this plan section
    */
    @Override
    public long getPlanId() {
        return _planSection.getPlanId();
    }

    /**
    * Sets the plan ID of this plan section.
    *
    * @param planId the plan ID of this plan section
    */
    @Override
    public void setPlanId(long planId) {
        _planSection.setPlanId(planId);
    }

    /**
    * Returns the content of this plan section.
    *
    * @return the content of this plan section
    */
    @Override
    public java.lang.String getContent() {
        return _planSection.getContent();
    }

    /**
    * Sets the content of this plan section.
    *
    * @param content the content of this plan section
    */
    @Override
    public void setContent(java.lang.String content) {
        _planSection.setContent(content);
    }

    /**
    * Returns the numeric value of this plan section.
    *
    * @return the numeric value of this plan section
    */
    @Override
    public long getNumericValue() {
        return _planSection.getNumericValue();
    }

    /**
    * Sets the numeric value of this plan section.
    *
    * @param numericValue the numeric value of this plan section
    */
    @Override
    public void setNumericValue(long numericValue) {
        _planSection.setNumericValue(numericValue);
    }

    /**
    * Returns the created of this plan section.
    *
    * @return the created of this plan section
    */
    @Override
    public java.util.Date getCreated() {
        return _planSection.getCreated();
    }

    /**
    * Sets the created of this plan section.
    *
    * @param created the created of this plan section
    */
    @Override
    public void setCreated(java.util.Date created) {
        _planSection.setCreated(created);
    }

    /**
    * Returns the version of this plan section.
    *
    * @return the version of this plan section
    */
    @Override
    public long getVersion() {
        return _planSection.getVersion();
    }

    /**
    * Sets the version of this plan section.
    *
    * @param version the version of this plan section
    */
    @Override
    public void setVersion(long version) {
        _planSection.setVersion(version);
    }

    /**
    * Returns the plan version of this plan section.
    *
    * @return the plan version of this plan section
    */
    @Override
    public long getPlanVersion() {
        return _planSection.getPlanVersion();
    }

    /**
    * Sets the plan version of this plan section.
    *
    * @param planVersion the plan version of this plan section
    */
    @Override
    public void setPlanVersion(long planVersion) {
        _planSection.setPlanVersion(planVersion);
    }

    /**
    * Returns the update author ID of this plan section.
    *
    * @return the update author ID of this plan section
    */
    @Override
    public long getUpdateAuthorId() {
        return _planSection.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan section.
    *
    * @param updateAuthorId the update author ID of this plan section
    */
    @Override
    public void setUpdateAuthorId(long updateAuthorId) {
        _planSection.setUpdateAuthorId(updateAuthorId);
    }

    @Override
    public boolean isNew() {
        return _planSection.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planSection.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planSection.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planSection.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planSection.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planSection.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planSection.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planSection.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planSection.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planSection.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planSection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanSectionWrapper((PlanSection) _planSection.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.PlanSection planSection) {
        return _planSection.compareTo(planSection);
    }

    @Override
    public int hashCode() {
        return _planSection.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanSection> toCacheModel() {
        return _planSection.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanSection toEscapedModel() {
        return new PlanSectionWrapper(_planSection.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanSection toUnescapedModel() {
        return new PlanSectionWrapper(_planSection.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planSection.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planSection.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSection.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanSectionWrapper)) {
            return false;
        }

        PlanSectionWrapper planSectionWrapper = (PlanSectionWrapper) obj;

        if (Validator.equals(_planSection, planSectionWrapper._planSection)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanSection getWrappedPlanSection() {
        return _planSection;
    }

    @Override
    public PlanSection getWrappedModel() {
        return _planSection;
    }

    @Override
    public void resetOriginalValues() {
        _planSection.resetOriginalValues();
    }
}
