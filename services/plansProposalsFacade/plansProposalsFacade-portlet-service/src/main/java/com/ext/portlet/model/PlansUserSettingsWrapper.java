package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlansUserSettings}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansUserSettings
 * @generated
 */
public class PlansUserSettingsWrapper implements PlansUserSettings,
    ModelWrapper<PlansUserSettings> {
    private PlansUserSettings _plansUserSettings;

    public PlansUserSettingsWrapper(PlansUserSettings plansUserSettings) {
        _plansUserSettings = plansUserSettings;
    }

    @Override
    public Class<?> getModelClass() {
        return PlansUserSettings.class;
    }

    @Override
    public String getModelClassName() {
        return PlansUserSettings.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planUserSettingsId", getPlanUserSettingsId());
        attributes.put("userId", getUserId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("sortColumn", getSortColumn());
        attributes.put("sortDirection", getSortDirection());
        attributes.put("filterEnabled", getFilterEnabled());
        attributes.put("filterPositionsAll", getFilterPositionsAll());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planUserSettingsId = (Long) attributes.get("planUserSettingsId");

        if (planUserSettingsId != null) {
            setPlanUserSettingsId(planUserSettingsId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        String sortColumn = (String) attributes.get("sortColumn");

        if (sortColumn != null) {
            setSortColumn(sortColumn);
        }

        String sortDirection = (String) attributes.get("sortDirection");

        if (sortDirection != null) {
            setSortDirection(sortDirection);
        }

        Boolean filterEnabled = (Boolean) attributes.get("filterEnabled");

        if (filterEnabled != null) {
            setFilterEnabled(filterEnabled);
        }

        Boolean filterPositionsAll = (Boolean) attributes.get(
                "filterPositionsAll");

        if (filterPositionsAll != null) {
            setFilterPositionsAll(filterPositionsAll);
        }
    }

    /**
    * Returns the primary key of this plans user settings.
    *
    * @return the primary key of this plans user settings
    */
    @Override
    public long getPrimaryKey() {
        return _plansUserSettings.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plans user settings.
    *
    * @param primaryKey the primary key of this plans user settings
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _plansUserSettings.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan user settings ID of this plans user settings.
    *
    * @return the plan user settings ID of this plans user settings
    */
    @Override
    public long getPlanUserSettingsId() {
        return _plansUserSettings.getPlanUserSettingsId();
    }

    /**
    * Sets the plan user settings ID of this plans user settings.
    *
    * @param planUserSettingsId the plan user settings ID of this plans user settings
    */
    @Override
    public void setPlanUserSettingsId(long planUserSettingsId) {
        _plansUserSettings.setPlanUserSettingsId(planUserSettingsId);
    }

    /**
    * Returns the user ID of this plans user settings.
    *
    * @return the user ID of this plans user settings
    */
    @Override
    public long getUserId() {
        return _plansUserSettings.getUserId();
    }

    /**
    * Sets the user ID of this plans user settings.
    *
    * @param userId the user ID of this plans user settings
    */
    @Override
    public void setUserId(long userId) {
        _plansUserSettings.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plans user settings.
    *
    * @return the user uuid of this plans user settings
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansUserSettings.getUserUuid();
    }

    /**
    * Sets the user uuid of this plans user settings.
    *
    * @param userUuid the user uuid of this plans user settings
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _plansUserSettings.setUserUuid(userUuid);
    }

    /**
    * Returns the plan type ID of this plans user settings.
    *
    * @return the plan type ID of this plans user settings
    */
    @Override
    public long getPlanTypeId() {
        return _plansUserSettings.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plans user settings.
    *
    * @param planTypeId the plan type ID of this plans user settings
    */
    @Override
    public void setPlanTypeId(long planTypeId) {
        _plansUserSettings.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the sort column of this plans user settings.
    *
    * @return the sort column of this plans user settings
    */
    @Override
    public java.lang.String getSortColumn() {
        return _plansUserSettings.getSortColumn();
    }

    /**
    * Sets the sort column of this plans user settings.
    *
    * @param sortColumn the sort column of this plans user settings
    */
    @Override
    public void setSortColumn(java.lang.String sortColumn) {
        _plansUserSettings.setSortColumn(sortColumn);
    }

    /**
    * Returns the sort direction of this plans user settings.
    *
    * @return the sort direction of this plans user settings
    */
    @Override
    public java.lang.String getSortDirection() {
        return _plansUserSettings.getSortDirection();
    }

    /**
    * Sets the sort direction of this plans user settings.
    *
    * @param sortDirection the sort direction of this plans user settings
    */
    @Override
    public void setSortDirection(java.lang.String sortDirection) {
        _plansUserSettings.setSortDirection(sortDirection);
    }

    /**
    * Returns the filter enabled of this plans user settings.
    *
    * @return the filter enabled of this plans user settings
    */
    @Override
    public boolean getFilterEnabled() {
        return _plansUserSettings.getFilterEnabled();
    }

    /**
    * Returns <code>true</code> if this plans user settings is filter enabled.
    *
    * @return <code>true</code> if this plans user settings is filter enabled; <code>false</code> otherwise
    */
    @Override
    public boolean isFilterEnabled() {
        return _plansUserSettings.isFilterEnabled();
    }

    /**
    * Sets whether this plans user settings is filter enabled.
    *
    * @param filterEnabled the filter enabled of this plans user settings
    */
    @Override
    public void setFilterEnabled(boolean filterEnabled) {
        _plansUserSettings.setFilterEnabled(filterEnabled);
    }

    /**
    * Returns the filter positions all of this plans user settings.
    *
    * @return the filter positions all of this plans user settings
    */
    @Override
    public boolean getFilterPositionsAll() {
        return _plansUserSettings.getFilterPositionsAll();
    }

    /**
    * Returns <code>true</code> if this plans user settings is filter positions all.
    *
    * @return <code>true</code> if this plans user settings is filter positions all; <code>false</code> otherwise
    */
    @Override
    public boolean isFilterPositionsAll() {
        return _plansUserSettings.isFilterPositionsAll();
    }

    /**
    * Sets whether this plans user settings is filter positions all.
    *
    * @param filterPositionsAll the filter positions all of this plans user settings
    */
    @Override
    public void setFilterPositionsAll(boolean filterPositionsAll) {
        _plansUserSettings.setFilterPositionsAll(filterPositionsAll);
    }

    @Override
    public boolean isNew() {
        return _plansUserSettings.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _plansUserSettings.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _plansUserSettings.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _plansUserSettings.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _plansUserSettings.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _plansUserSettings.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _plansUserSettings.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _plansUserSettings.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _plansUserSettings.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _plansUserSettings.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _plansUserSettings.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlansUserSettingsWrapper((PlansUserSettings) _plansUserSettings.clone());
    }

    @Override
    public int compareTo(PlansUserSettings plansUserSettings) {
        return _plansUserSettings.compareTo(plansUserSettings);
    }

    @Override
    public int hashCode() {
        return _plansUserSettings.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlansUserSettings> toCacheModel() {
        return _plansUserSettings.toCacheModel();
    }

    @Override
    public PlansUserSettings toEscapedModel() {
        return new PlansUserSettingsWrapper(_plansUserSettings.toEscapedModel());
    }

    @Override
    public PlansUserSettings toUnescapedModel() {
        return new PlansUserSettingsWrapper(_plansUserSettings.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _plansUserSettings.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _plansUserSettings.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _plansUserSettings.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlansUserSettingsWrapper)) {
            return false;
        }

        PlansUserSettingsWrapper plansUserSettingsWrapper = (PlansUserSettingsWrapper) obj;

        if (Validator.equals(_plansUserSettings,
                    plansUserSettingsWrapper._plansUserSettings)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlansUserSettings getWrappedPlansUserSettings() {
        return _plansUserSettings;
    }

    @Override
    public PlansUserSettings getWrappedModel() {
        return _plansUserSettings;
    }

    @Override
    public void resetOriginalValues() {
        _plansUserSettings.resetOriginalValues();
    }
}
