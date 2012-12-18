package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanColumnSettings}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanColumnSettings
 * @generated
 */
public class PlanColumnSettingsWrapper implements PlanColumnSettings,
    ModelWrapper<PlanColumnSettings> {
    private PlanColumnSettings _planColumnSettings;

    public PlanColumnSettingsWrapper(PlanColumnSettings planColumnSettings) {
        _planColumnSettings = planColumnSettings;
    }

    public Class<?> getModelClass() {
        return PlanColumnSettings.class;
    }

    public String getModelClassName() {
        return PlanColumnSettings.class.getName();
    }

    /**
    * Returns the primary key of this plan column settings.
    *
    * @return the primary key of this plan column settings
    */
    public long getPrimaryKey() {
        return _planColumnSettings.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan column settings.
    *
    * @param primaryKey the primary key of this plan column settings
    */
    public void setPrimaryKey(long primaryKey) {
        _planColumnSettings.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan column settings ID of this plan column settings.
    *
    * @return the plan column settings ID of this plan column settings
    */
    public long getPlanColumnSettingsId() {
        return _planColumnSettings.getPlanColumnSettingsId();
    }

    /**
    * Sets the plan column settings ID of this plan column settings.
    *
    * @param planColumnSettingsId the plan column settings ID of this plan column settings
    */
    public void setPlanColumnSettingsId(long planColumnSettingsId) {
        _planColumnSettings.setPlanColumnSettingsId(planColumnSettingsId);
    }

    /**
    * Returns the column name of this plan column settings.
    *
    * @return the column name of this plan column settings
    */
    public java.lang.String getColumnName() {
        return _planColumnSettings.getColumnName();
    }

    /**
    * Sets the column name of this plan column settings.
    *
    * @param columnName the column name of this plan column settings
    */
    public void setColumnName(java.lang.String columnName) {
        _planColumnSettings.setColumnName(columnName);
    }

    /**
    * Returns the plan user settings ID of this plan column settings.
    *
    * @return the plan user settings ID of this plan column settings
    */
    public long getPlanUserSettingsId() {
        return _planColumnSettings.getPlanUserSettingsId();
    }

    /**
    * Sets the plan user settings ID of this plan column settings.
    *
    * @param planUserSettingsId the plan user settings ID of this plan column settings
    */
    public void setPlanUserSettingsId(long planUserSettingsId) {
        _planColumnSettings.setPlanUserSettingsId(planUserSettingsId);
    }

    /**
    * Returns the visible of this plan column settings.
    *
    * @return the visible of this plan column settings
    */
    public boolean getVisible() {
        return _planColumnSettings.getVisible();
    }

    /**
    * Returns <code>true</code> if this plan column settings is visible.
    *
    * @return <code>true</code> if this plan column settings is visible; <code>false</code> otherwise
    */
    public boolean isVisible() {
        return _planColumnSettings.isVisible();
    }

    /**
    * Sets whether this plan column settings is visible.
    *
    * @param visible the visible of this plan column settings
    */
    public void setVisible(boolean visible) {
        _planColumnSettings.setVisible(visible);
    }

    public boolean isNew() {
        return _planColumnSettings.isNew();
    }

    public void setNew(boolean n) {
        _planColumnSettings.setNew(n);
    }

    public boolean isCachedModel() {
        return _planColumnSettings.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planColumnSettings.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planColumnSettings.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planColumnSettings.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planColumnSettings.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planColumnSettings.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planColumnSettings.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanColumnSettingsWrapper((PlanColumnSettings) _planColumnSettings.clone());
    }

    public int compareTo(PlanColumnSettings planColumnSettings) {
        return _planColumnSettings.compareTo(planColumnSettings);
    }

    @Override
    public int hashCode() {
        return _planColumnSettings.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanColumnSettings> toCacheModel() {
        return _planColumnSettings.toCacheModel();
    }

    public PlanColumnSettings toEscapedModel() {
        return new PlanColumnSettingsWrapper(_planColumnSettings.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planColumnSettings.toString();
    }

    public java.lang.String toXmlString() {
        return _planColumnSettings.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planColumnSettings.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanColumnSettings getWrappedPlanColumnSettings() {
        return _planColumnSettings;
    }

    public PlanColumnSettings getWrappedModel() {
        return _planColumnSettings;
    }

    public void resetOriginalValues() {
        _planColumnSettings.resetOriginalValues();
    }
}
