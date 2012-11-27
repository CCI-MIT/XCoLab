package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPropertyFilter}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPropertyFilter
 * @generated
 */
public class PlanPropertyFilterWrapper implements PlanPropertyFilter,
    ModelWrapper<PlanPropertyFilter> {
    private PlanPropertyFilter _planPropertyFilter;

    public PlanPropertyFilterWrapper(PlanPropertyFilter planPropertyFilter) {
        _planPropertyFilter = planPropertyFilter;
    }

    public Class<?> getModelClass() {
        return PlanPropertyFilter.class;
    }

    public String getModelClassName() {
        return PlanPropertyFilter.class.getName();
    }

    /**
    * Returns the primary key of this plan property filter.
    *
    * @return the primary key of this plan property filter
    */
    public java.lang.Long getPrimaryKey() {
        return _planPropertyFilter.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan property filter.
    *
    * @param primaryKey the primary key of this plan property filter
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _planPropertyFilter.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan property filter ID of this plan property filter.
    *
    * @return the plan property filter ID of this plan property filter
    */
    public java.lang.Long getPlanPropertyFilterId() {
        return _planPropertyFilter.getPlanPropertyFilterId();
    }

    /**
    * Sets the plan property filter ID of this plan property filter.
    *
    * @param planPropertyFilterId the plan property filter ID of this plan property filter
    */
    public void setPlanPropertyFilterId(java.lang.Long planPropertyFilterId) {
        _planPropertyFilter.setPlanPropertyFilterId(planPropertyFilterId);
    }

    /**
    * Returns the property name of this plan property filter.
    *
    * @return the property name of this plan property filter
    */
    public java.lang.String getPropertyName() {
        return _planPropertyFilter.getPropertyName();
    }

    /**
    * Sets the property name of this plan property filter.
    *
    * @param propertyName the property name of this plan property filter
    */
    public void setPropertyName(java.lang.String propertyName) {
        _planPropertyFilter.setPropertyName(propertyName);
    }

    /**
    * Returns the plan user settings ID of this plan property filter.
    *
    * @return the plan user settings ID of this plan property filter
    */
    public java.lang.Long getPlanUserSettingsId() {
        return _planPropertyFilter.getPlanUserSettingsId();
    }

    /**
    * Sets the plan user settings ID of this plan property filter.
    *
    * @param planUserSettingsId the plan user settings ID of this plan property filter
    */
    public void setPlanUserSettingsId(java.lang.Long planUserSettingsId) {
        _planPropertyFilter.setPlanUserSettingsId(planUserSettingsId);
    }

    /**
    * Returns the value of this plan property filter.
    *
    * @return the value of this plan property filter
    */
    public java.lang.String getValue() {
        return _planPropertyFilter.getValue();
    }

    /**
    * Sets the value of this plan property filter.
    *
    * @param value the value of this plan property filter
    */
    public void setValue(java.lang.String value) {
        _planPropertyFilter.setValue(value);
    }

    public boolean isNew() {
        return _planPropertyFilter.isNew();
    }

    public void setNew(boolean n) {
        _planPropertyFilter.setNew(n);
    }

    public boolean isCachedModel() {
        return _planPropertyFilter.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planPropertyFilter.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planPropertyFilter.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planPropertyFilter.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planPropertyFilter.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planPropertyFilter.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planPropertyFilter.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanPropertyFilterWrapper((PlanPropertyFilter) _planPropertyFilter.clone());
    }

    public int compareTo(PlanPropertyFilter planPropertyFilter) {
        return _planPropertyFilter.compareTo(planPropertyFilter);
    }

    @Override
    public int hashCode() {
        return _planPropertyFilter.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanPropertyFilter> toCacheModel() {
        return _planPropertyFilter.toCacheModel();
    }

    public PlanPropertyFilter toEscapedModel() {
        return new PlanPropertyFilterWrapper(_planPropertyFilter.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planPropertyFilter.toString();
    }

    public java.lang.String toXmlString() {
        return _planPropertyFilter.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPropertyFilter.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanPropertyFilter getWrappedPlanPropertyFilter() {
        return _planPropertyFilter;
    }

    public PlanPropertyFilter getWrappedModel() {
        return _planPropertyFilter;
    }

    public void resetOriginalValues() {
        _planPropertyFilter.resetOriginalValues();
    }
}
