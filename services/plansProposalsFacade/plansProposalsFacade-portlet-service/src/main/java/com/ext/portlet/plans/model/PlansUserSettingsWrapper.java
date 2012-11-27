package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlansUserSettings}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlansUserSettings
 * @generated
 */
public class PlansUserSettingsWrapper implements PlansUserSettings,
    ModelWrapper<PlansUserSettings> {
    private PlansUserSettings _plansUserSettings;

    public PlansUserSettingsWrapper(PlansUserSettings plansUserSettings) {
        _plansUserSettings = plansUserSettings;
    }

    public Class<?> getModelClass() {
        return PlansUserSettings.class;
    }

    public String getModelClassName() {
        return PlansUserSettings.class.getName();
    }

    /**
    * Returns the primary key of this plans user settings.
    *
    * @return the primary key of this plans user settings
    */
    public java.lang.Long getPrimaryKey() {
        return _plansUserSettings.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plans user settings.
    *
    * @param primaryKey the primary key of this plans user settings
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _plansUserSettings.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan user settings ID of this plans user settings.
    *
    * @return the plan user settings ID of this plans user settings
    */
    public java.lang.Long getPlanUserSettingsId() {
        return _plansUserSettings.getPlanUserSettingsId();
    }

    /**
    * Sets the plan user settings ID of this plans user settings.
    *
    * @param planUserSettingsId the plan user settings ID of this plans user settings
    */
    public void setPlanUserSettingsId(java.lang.Long planUserSettingsId) {
        _plansUserSettings.setPlanUserSettingsId(planUserSettingsId);
    }

    /**
    * Returns the user ID of this plans user settings.
    *
    * @return the user ID of this plans user settings
    */
    public java.lang.Long getUserId() {
        return _plansUserSettings.getUserId();
    }

    /**
    * Sets the user ID of this plans user settings.
    *
    * @param userId the user ID of this plans user settings
    */
    public void setUserId(java.lang.Long userId) {
        _plansUserSettings.setUserId(userId);
    }

    /**
    * Returns the plan type ID of this plans user settings.
    *
    * @return the plan type ID of this plans user settings
    */
    public java.lang.Long getPlanTypeId() {
        return _plansUserSettings.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plans user settings.
    *
    * @param planTypeId the plan type ID of this plans user settings
    */
    public void setPlanTypeId(java.lang.Long planTypeId) {
        _plansUserSettings.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the sort column of this plans user settings.
    *
    * @return the sort column of this plans user settings
    */
    public java.lang.String getSortColumn() {
        return _plansUserSettings.getSortColumn();
    }

    /**
    * Sets the sort column of this plans user settings.
    *
    * @param sortColumn the sort column of this plans user settings
    */
    public void setSortColumn(java.lang.String sortColumn) {
        _plansUserSettings.setSortColumn(sortColumn);
    }

    /**
    * Returns the sort direction of this plans user settings.
    *
    * @return the sort direction of this plans user settings
    */
    public java.lang.String getSortDirection() {
        return _plansUserSettings.getSortDirection();
    }

    /**
    * Sets the sort direction of this plans user settings.
    *
    * @param sortDirection the sort direction of this plans user settings
    */
    public void setSortDirection(java.lang.String sortDirection) {
        _plansUserSettings.setSortDirection(sortDirection);
    }

    /**
    * Returns the filter enabled of this plans user settings.
    *
    * @return the filter enabled of this plans user settings
    */
    public java.lang.Boolean getFilterEnabled() {
        return _plansUserSettings.getFilterEnabled();
    }

    /**
    * Sets the filter enabled of this plans user settings.
    *
    * @param filterEnabled the filter enabled of this plans user settings
    */
    public void setFilterEnabled(java.lang.Boolean filterEnabled) {
        _plansUserSettings.setFilterEnabled(filterEnabled);
    }

    /**
    * Returns the filter positions all of this plans user settings.
    *
    * @return the filter positions all of this plans user settings
    */
    public java.lang.Boolean getFilterPositionsAll() {
        return _plansUserSettings.getFilterPositionsAll();
    }

    /**
    * Sets the filter positions all of this plans user settings.
    *
    * @param filterPositionsAll the filter positions all of this plans user settings
    */
    public void setFilterPositionsAll(java.lang.Boolean filterPositionsAll) {
        _plansUserSettings.setFilterPositionsAll(filterPositionsAll);
    }

    public boolean isNew() {
        return _plansUserSettings.isNew();
    }

    public void setNew(boolean n) {
        _plansUserSettings.setNew(n);
    }

    public boolean isCachedModel() {
        return _plansUserSettings.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _plansUserSettings.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _plansUserSettings.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _plansUserSettings.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _plansUserSettings.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _plansUserSettings.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _plansUserSettings.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlansUserSettingsWrapper((PlansUserSettings) _plansUserSettings.clone());
    }

    public int compareTo(PlansUserSettings plansUserSettings) {
        return _plansUserSettings.compareTo(plansUserSettings);
    }

    @Override
    public int hashCode() {
        return _plansUserSettings.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlansUserSettings> toCacheModel() {
        return _plansUserSettings.toCacheModel();
    }

    public PlansUserSettings toEscapedModel() {
        return new PlansUserSettingsWrapper(_plansUserSettings.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _plansUserSettings.toString();
    }

    public java.lang.String toXmlString() {
        return _plansUserSettings.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _plansUserSettings.persist();
    }

    public com.ext.portlet.plans.model.PlanColumnSettings getColumnSettings(
        java.lang.String name)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettings.getColumnSettings(name);
    }

    public void addColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings settings) {
        _plansUserSettings.addColumnSettings(settings);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getUpdatedColumnSettings() {
        return _plansUserSettings.getUpdatedColumnSettings();
    }

    /**
    * Getter for positionsIds.
    *
    * @return the positionsIds
    */
    public java.util.List<java.lang.Long> getPositionsIds() {
        return _plansUserSettings.getPositionsIds();
    }

    /**
    * Setter for positionsIds.
    *
    * @param positionsIds the positionsIds to set
    */
    public void setPositionsIds(java.util.List<java.lang.Long> positionsIds) {
        _plansUserSettings.setPositionsIds(positionsIds);
    }

    public com.ext.portlet.plans.model.PlanAttributeFilter getAttributeFilter(
        java.lang.String name)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettings.getAttributeFilter(name);
    }

    public void addPlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter filter) {
        _plansUserSettings.addPlanAttributeFilter(filter);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getUpdatedPlanAttributeFilters() {
        return _plansUserSettings.getUpdatedPlanAttributeFilters();
    }

    public com.ext.portlet.plans.model.PlanPropertyFilter getPropertyFilter(
        java.lang.String name)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettings.getPropertyFilter(name);
    }

    public void addPlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter filter) {
        _plansUserSettings.addPlanPropertyFilter(filter);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> getUpdatedPlanPropertyFilters() {
        return _plansUserSettings.getUpdatedPlanPropertyFilters();
    }

    public boolean isFiltersDefined() {
        return _plansUserSettings.isFiltersDefined();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlansUserSettings getWrappedPlansUserSettings() {
        return _plansUserSettings;
    }

    public PlansUserSettings getWrappedModel() {
        return _plansUserSettings;
    }

    public void resetOriginalValues() {
        _plansUserSettings.resetOriginalValues();
    }
}
