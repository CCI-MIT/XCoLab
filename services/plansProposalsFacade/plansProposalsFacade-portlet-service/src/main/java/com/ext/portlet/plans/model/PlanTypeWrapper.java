package com.ext.portlet.plans.model;

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
    public java.lang.Long getPrimaryKey() {
        return _planType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan type.
    *
    * @param primaryKey the primary key of this plan type
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _planType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan type ID of this plan type.
    *
    * @return the plan type ID of this plan type
    */
    public java.lang.Long getPlanTypeId() {
        return _planType.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plan type.
    *
    * @param planTypeId the plan type ID of this plan type
    */
    public void setPlanTypeId(java.lang.Long planTypeId) {
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
    public java.lang.Long getModelId() {
        return _planType.getModelId();
    }

    /**
    * Sets the model ID of this plan type.
    *
    * @param modelId the model ID of this plan type
    */
    public void setModelId(java.lang.Long modelId) {
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
    public java.lang.Boolean getPublished() {
        return _planType.getPublished();
    }

    /**
    * Sets the published of this plan type.
    *
    * @param published the published of this plan type
    */
    public void setPublished(java.lang.Boolean published) {
        _planType.setPublished(published);
    }

    /**
    * Returns the published counterpart ID of this plan type.
    *
    * @return the published counterpart ID of this plan type
    */
    public java.lang.Long getPublishedCounterpartId() {
        return _planType.getPublishedCounterpartId();
    }

    /**
    * Sets the published counterpart ID of this plan type.
    *
    * @param publishedCounterpartId the published counterpart ID of this plan type
    */
    public void setPublishedCounterpartId(java.lang.Long publishedCounterpartId) {
        _planType.setPublishedCounterpartId(publishedCounterpartId);
    }

    /**
    * Returns the is default of this plan type.
    *
    * @return the is default of this plan type
    */
    public java.lang.Boolean getIsDefault() {
        return _planType.getIsDefault();
    }

    /**
    * Sets the is default of this plan type.
    *
    * @param isDefault the is default of this plan type
    */
    public void setIsDefault(java.lang.Boolean isDefault) {
        _planType.setIsDefault(isDefault);
    }

    /**
    * Returns the default model ID of this plan type.
    *
    * @return the default model ID of this plan type
    */
    public java.lang.Long getDefaultModelId() {
        return _planType.getDefaultModelId();
    }

    /**
    * Sets the default model ID of this plan type.
    *
    * @param defaultModelId the default model ID of this plan type
    */
    public void setDefaultModelId(java.lang.Long defaultModelId) {
        _planType.setDefaultModelId(defaultModelId);
    }

    /**
    * Returns the default scenario ID of this plan type.
    *
    * @return the default scenario ID of this plan type
    */
    public java.lang.Long getDefaultScenarioId() {
        return _planType.getDefaultScenarioId();
    }

    /**
    * Sets the default scenario ID of this plan type.
    *
    * @param defaultScenarioId the default scenario ID of this plan type
    */
    public void setDefaultScenarioId(java.lang.Long defaultScenarioId) {
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

    public java.util.List<edu.mit.cci.simulation.client.Simulation> getAvailableModels()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planType.getAvailableModels();
    }

    public edu.mit.cci.simulation.client.Simulation getDefaultModel()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planType.getDefaultModel();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getColumns()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planType.getColumns();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getAttributes()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planType.getAttributes();
    }

    public boolean isRegional()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planType.isRegional();
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
