package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanType
 * @generated
 */
public class PlanTypeWrapper implements PlanType, ModelWrapper<PlanType> {
    private PlanType _planType;

    public PlanTypeWrapper(PlanType planType) {
        _planType = planType;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanType.class;
    }

    @Override
    public String getModelClassName() {
        return PlanType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("modelId", getModelId());
        attributes.put("modelTypeName", getModelTypeName());
        attributes.put("published", getPublished());
        attributes.put("publishedCounterpartId", getPublishedCounterpartId());
        attributes.put("isDefault", getIsDefault());
        attributes.put("defaultModelId", getDefaultModelId());
        attributes.put("defaultScenarioId", getDefaultScenarioId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        String modelTypeName = (String) attributes.get("modelTypeName");

        if (modelTypeName != null) {
            setModelTypeName(modelTypeName);
        }

        Boolean published = (Boolean) attributes.get("published");

        if (published != null) {
            setPublished(published);
        }

        Long publishedCounterpartId = (Long) attributes.get(
                "publishedCounterpartId");

        if (publishedCounterpartId != null) {
            setPublishedCounterpartId(publishedCounterpartId);
        }

        Boolean isDefault = (Boolean) attributes.get("isDefault");

        if (isDefault != null) {
            setIsDefault(isDefault);
        }

        Long defaultModelId = (Long) attributes.get("defaultModelId");

        if (defaultModelId != null) {
            setDefaultModelId(defaultModelId);
        }

        Long defaultScenarioId = (Long) attributes.get("defaultScenarioId");

        if (defaultScenarioId != null) {
            setDefaultScenarioId(defaultScenarioId);
        }
    }

    /**
    * Returns the primary key of this plan type.
    *
    * @return the primary key of this plan type
    */
    @Override
    public long getPrimaryKey() {
        return _planType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan type.
    *
    * @param primaryKey the primary key of this plan type
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan type ID of this plan type.
    *
    * @return the plan type ID of this plan type
    */
    @Override
    public long getPlanTypeId() {
        return _planType.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plan type.
    *
    * @param planTypeId the plan type ID of this plan type
    */
    @Override
    public void setPlanTypeId(long planTypeId) {
        _planType.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the name of this plan type.
    *
    * @return the name of this plan type
    */
    @Override
    public java.lang.String getName() {
        return _planType.getName();
    }

    /**
    * Sets the name of this plan type.
    *
    * @param name the name of this plan type
    */
    @Override
    public void setName(java.lang.String name) {
        _planType.setName(name);
    }

    /**
    * Returns the description of this plan type.
    *
    * @return the description of this plan type
    */
    @Override
    public java.lang.String getDescription() {
        return _planType.getDescription();
    }

    /**
    * Sets the description of this plan type.
    *
    * @param description the description of this plan type
    */
    @Override
    public void setDescription(java.lang.String description) {
        _planType.setDescription(description);
    }

    /**
    * Returns the model ID of this plan type.
    *
    * @return the model ID of this plan type
    */
    @Override
    public long getModelId() {
        return _planType.getModelId();
    }

    /**
    * Sets the model ID of this plan type.
    *
    * @param modelId the model ID of this plan type
    */
    @Override
    public void setModelId(long modelId) {
        _planType.setModelId(modelId);
    }

    /**
    * Returns the model type name of this plan type.
    *
    * @return the model type name of this plan type
    */
    @Override
    public java.lang.String getModelTypeName() {
        return _planType.getModelTypeName();
    }

    /**
    * Sets the model type name of this plan type.
    *
    * @param modelTypeName the model type name of this plan type
    */
    @Override
    public void setModelTypeName(java.lang.String modelTypeName) {
        _planType.setModelTypeName(modelTypeName);
    }

    /**
    * Returns the published of this plan type.
    *
    * @return the published of this plan type
    */
    @Override
    public boolean getPublished() {
        return _planType.getPublished();
    }

    /**
    * Returns <code>true</code> if this plan type is published.
    *
    * @return <code>true</code> if this plan type is published; <code>false</code> otherwise
    */
    @Override
    public boolean isPublished() {
        return _planType.isPublished();
    }

    /**
    * Sets whether this plan type is published.
    *
    * @param published the published of this plan type
    */
    @Override
    public void setPublished(boolean published) {
        _planType.setPublished(published);
    }

    /**
    * Returns the published counterpart ID of this plan type.
    *
    * @return the published counterpart ID of this plan type
    */
    @Override
    public long getPublishedCounterpartId() {
        return _planType.getPublishedCounterpartId();
    }

    /**
    * Sets the published counterpart ID of this plan type.
    *
    * @param publishedCounterpartId the published counterpart ID of this plan type
    */
    @Override
    public void setPublishedCounterpartId(long publishedCounterpartId) {
        _planType.setPublishedCounterpartId(publishedCounterpartId);
    }

    /**
    * Returns the is default of this plan type.
    *
    * @return the is default of this plan type
    */
    @Override
    public boolean getIsDefault() {
        return _planType.getIsDefault();
    }

    /**
    * Returns <code>true</code> if this plan type is is default.
    *
    * @return <code>true</code> if this plan type is is default; <code>false</code> otherwise
    */
    @Override
    public boolean isIsDefault() {
        return _planType.isIsDefault();
    }

    /**
    * Sets whether this plan type is is default.
    *
    * @param isDefault the is default of this plan type
    */
    @Override
    public void setIsDefault(boolean isDefault) {
        _planType.setIsDefault(isDefault);
    }

    /**
    * Returns the default model ID of this plan type.
    *
    * @return the default model ID of this plan type
    */
    @Override
    public long getDefaultModelId() {
        return _planType.getDefaultModelId();
    }

    /**
    * Sets the default model ID of this plan type.
    *
    * @param defaultModelId the default model ID of this plan type
    */
    @Override
    public void setDefaultModelId(long defaultModelId) {
        _planType.setDefaultModelId(defaultModelId);
    }

    /**
    * Returns the default scenario ID of this plan type.
    *
    * @return the default scenario ID of this plan type
    */
    @Override
    public long getDefaultScenarioId() {
        return _planType.getDefaultScenarioId();
    }

    /**
    * Sets the default scenario ID of this plan type.
    *
    * @param defaultScenarioId the default scenario ID of this plan type
    */
    @Override
    public void setDefaultScenarioId(long defaultScenarioId) {
        _planType.setDefaultScenarioId(defaultScenarioId);
    }

    @Override
    public boolean isNew() {
        return _planType.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planType.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planType.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planType.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planType.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planType.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planType.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planType.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planType.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planType.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTypeWrapper((PlanType) _planType.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.PlanType planType) {
        return _planType.compareTo(planType);
    }

    @Override
    public int hashCode() {
        return _planType.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanType> toCacheModel() {
        return _planType.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanType toEscapedModel() {
        return new PlanTypeWrapper(_planType.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanType toUnescapedModel() {
        return new PlanTypeWrapper(_planType.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planType.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planType.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planType.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanTypeWrapper)) {
            return false;
        }

        PlanTypeWrapper planTypeWrapper = (PlanTypeWrapper) obj;

        if (Validator.equals(_planType, planTypeWrapper._planType)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanType getWrappedPlanType() {
        return _planType;
    }

    @Override
    public PlanType getWrappedModel() {
        return _planType;
    }

    @Override
    public void resetOriginalValues() {
        _planType.resetOriginalValues();
    }
}
