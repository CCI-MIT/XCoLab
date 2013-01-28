package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanType}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanType
 * @generated
 */
public class PlanTypeWrapper implements PlanType, ModelWrapper<PlanType> {
    private PlanType _planType;

    public PlanTypeWrapper(PlanType planType) {
        _planType = planType;
    }

    public Class<?> getModelClass() {
        return PlanType.class;
    }

    public String getModelClassName() {
        return PlanType.class.getName();
    }

    /**
    * Returns the primary key of this plan type.
    *
    * @return the primary key of this plan type
    */
    public long getPrimaryKey() {
        return _planType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan type.
    *
    * @param primaryKey the primary key of this plan type
    */
    public void setPrimaryKey(long primaryKey) {
        _planType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan type ID of this plan type.
    *
    * @return the plan type ID of this plan type
    */
    public long getPlanTypeId() {
        return _planType.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plan type.
    *
    * @param planTypeId the plan type ID of this plan type
    */
    public void setPlanTypeId(long planTypeId) {
        _planType.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the name of this plan type.
    *
    * @return the name of this plan type
    */
    public java.lang.String getName() {
        return _planType.getName();
    }

    /**
    * Sets the name of this plan type.
    *
    * @param name the name of this plan type
    */
    public void setName(java.lang.String name) {
        _planType.setName(name);
    }

    /**
    * Returns the description of this plan type.
    *
    * @return the description of this plan type
    */
    public java.lang.String getDescription() {
        return _planType.getDescription();
    }

    /**
    * Sets the description of this plan type.
    *
    * @param description the description of this plan type
    */
    public void setDescription(java.lang.String description) {
        _planType.setDescription(description);
    }

    /**
    * Returns the model ID of this plan type.
    *
    * @return the model ID of this plan type
    */
    public long getModelId() {
        return _planType.getModelId();
    }

    /**
    * Sets the model ID of this plan type.
    *
    * @param modelId the model ID of this plan type
    */
    public void setModelId(long modelId) {
        _planType.setModelId(modelId);
    }

    /**
    * Returns the model type name of this plan type.
    *
    * @return the model type name of this plan type
    */
    public java.lang.String getModelTypeName() {
        return _planType.getModelTypeName();
    }

    /**
    * Sets the model type name of this plan type.
    *
    * @param modelTypeName the model type name of this plan type
    */
    public void setModelTypeName(java.lang.String modelTypeName) {
        _planType.setModelTypeName(modelTypeName);
    }

    /**
    * Returns the published of this plan type.
    *
    * @return the published of this plan type
    */
    public boolean getPublished() {
        return _planType.getPublished();
    }

    /**
    * Returns <code>true</code> if this plan type is published.
    *
    * @return <code>true</code> if this plan type is published; <code>false</code> otherwise
    */
    public boolean isPublished() {
        return _planType.isPublished();
    }

    /**
    * Sets whether this plan type is published.
    *
    * @param published the published of this plan type
    */
    public void setPublished(boolean published) {
        _planType.setPublished(published);
    }

    /**
    * Returns the published counterpart ID of this plan type.
    *
    * @return the published counterpart ID of this plan type
    */
    public long getPublishedCounterpartId() {
        return _planType.getPublishedCounterpartId();
    }

    /**
    * Sets the published counterpart ID of this plan type.
    *
    * @param publishedCounterpartId the published counterpart ID of this plan type
    */
    public void setPublishedCounterpartId(long publishedCounterpartId) {
        _planType.setPublishedCounterpartId(publishedCounterpartId);
    }

    /**
    * Returns the is default of this plan type.
    *
    * @return the is default of this plan type
    */
    public boolean getIsDefault() {
        return _planType.getIsDefault();
    }

    /**
    * Returns <code>true</code> if this plan type is is default.
    *
    * @return <code>true</code> if this plan type is is default; <code>false</code> otherwise
    */
    public boolean isIsDefault() {
        return _planType.isIsDefault();
    }

    /**
    * Sets whether this plan type is is default.
    *
    * @param isDefault the is default of this plan type
    */
    public void setIsDefault(boolean isDefault) {
        _planType.setIsDefault(isDefault);
    }

    /**
    * Returns the default model ID of this plan type.
    *
    * @return the default model ID of this plan type
    */
    public long getDefaultModelId() {
        return _planType.getDefaultModelId();
    }

    /**
    * Sets the default model ID of this plan type.
    *
    * @param defaultModelId the default model ID of this plan type
    */
    public void setDefaultModelId(long defaultModelId) {
        _planType.setDefaultModelId(defaultModelId);
    }

    /**
    * Returns the default scenario ID of this plan type.
    *
    * @return the default scenario ID of this plan type
    */
    public long getDefaultScenarioId() {
        return _planType.getDefaultScenarioId();
    }

    /**
    * Sets the default scenario ID of this plan type.
    *
    * @param defaultScenarioId the default scenario ID of this plan type
    */
    public void setDefaultScenarioId(long defaultScenarioId) {
        _planType.setDefaultScenarioId(defaultScenarioId);
    }

    public boolean isNew() {
        return _planType.isNew();
    }

    public void setNew(boolean n) {
        _planType.setNew(n);
    }

    public boolean isCachedModel() {
        return _planType.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planType.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planType.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planType.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planType.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planType.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTypeWrapper((PlanType) _planType.clone());
    }

    public int compareTo(PlanType planType) {
        return _planType.compareTo(planType);
    }

    @Override
    public int hashCode() {
        return _planType.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanType> toCacheModel() {
        return _planType.toCacheModel();
    }

    public PlanType toEscapedModel() {
        return new PlanTypeWrapper(_planType.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planType.toString();
    }

    public java.lang.String toXmlString() {
        return _planType.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planType.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanType getWrappedPlanType() {
        return _planType;
    }

    public PlanType getWrappedModel() {
        return _planType;
    }

    public void resetOriginalValues() {
        _planType.resetOriginalValues();
    }
}
