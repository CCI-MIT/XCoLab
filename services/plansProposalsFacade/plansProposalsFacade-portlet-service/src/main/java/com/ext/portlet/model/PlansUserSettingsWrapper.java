package com.ext.portlet.model;

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
    public long getPrimaryKey() {
        return _plansUserSettings.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plans user settings.
    *
    * @param primaryKey the primary key of this plans user settings
    */
    public void setPrimaryKey(long primaryKey) {
        _plansUserSettings.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan user settings ID of this plans user settings.
    *
    * @return the plan user settings ID of this plans user settings
    */
    public long getPlanUserSettingsId() {
        return _plansUserSettings.getPlanUserSettingsId();
    }

    /**
    * Sets the plan user settings ID of this plans user settings.
    *
    * @param planUserSettingsId the plan user settings ID of this plans user settings
    */
    public void setPlanUserSettingsId(long planUserSettingsId) {
        _plansUserSettings.setPlanUserSettingsId(planUserSettingsId);
    }

    /**
    * Returns the user ID of this plans user settings.
    *
    * @return the user ID of this plans user settings
    */
    public long getUserId() {
        return _plansUserSettings.getUserId();
    }

    /**
    * Sets the user ID of this plans user settings.
    *
    * @param userId the user ID of this plans user settings
    */
    public void setUserId(long userId) {
        _plansUserSettings.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plans user settings.
    *
    * @return the user uuid of this plans user settings
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettings.getUserUuid();
    }

    /**
    * Sets the user uuid of this plans user settings.
    *
    * @param userUuid the user uuid of this plans user settings
    */
    public void setUserUuid(java.lang.String userUuid) {
        _plansUserSettings.setUserUuid(userUuid);
    }

    /**
    * Returns the plan type ID of this plans user settings.
    *
    * @return the plan type ID of this plans user settings
    */
    public long getPlanTypeId() {
        return _plansUserSettings.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plans user settings.
    *
    * @param planTypeId the plan type ID of this plans user settings
    */
    public void setPlanTypeId(long planTypeId) {
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
    public boolean getFilterEnabled() {
        return _plansUserSettings.getFilterEnabled();
    }

    /**
    * Returns <code>true</code> if this plans user settings is filter enabled.
    *
    * @return <code>true</code> if this plans user settings is filter enabled; <code>false</code> otherwise
    */
    public boolean isFilterEnabled() {
        return _plansUserSettings.isFilterEnabled();
    }

    /**
    * Sets whether this plans user settings is filter enabled.
    *
    * @param filterEnabled the filter enabled of this plans user settings
    */
    public void setFilterEnabled(boolean filterEnabled) {
        _plansUserSettings.setFilterEnabled(filterEnabled);
    }

    /**
    * Returns the filter positions all of this plans user settings.
    *
    * @return the filter positions all of this plans user settings
    */
    public boolean getFilterPositionsAll() {
        return _plansUserSettings.getFilterPositionsAll();
    }

    /**
    * Returns <code>true</code> if this plans user settings is filter positions all.
    *
    * @return <code>true</code> if this plans user settings is filter positions all; <code>false</code> otherwise
    */
    public boolean isFilterPositionsAll() {
        return _plansUserSettings.isFilterPositionsAll();
    }

    /**
    * Sets whether this plans user settings is filter positions all.
    *
    * @param filterPositionsAll the filter positions all of this plans user settings
    */
    public void setFilterPositionsAll(boolean filterPositionsAll) {
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
