package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanColumnSettings}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettings
 * @generated
 */
public class PlanColumnSettingsWrapper implements PlanColumnSettings,
    ModelWrapper<PlanColumnSettings> {
    private PlanColumnSettings _planColumnSettings;

    public PlanColumnSettingsWrapper(PlanColumnSettings planColumnSettings) {
        _planColumnSettings = planColumnSettings;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanColumnSettings.class;
    }

    @Override
    public String getModelClassName() {
        return PlanColumnSettings.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planColumnSettingsId", getPlanColumnSettingsId());
        attributes.put("columnName", getColumnName());
        attributes.put("planUserSettingsId", getPlanUserSettingsId());
        attributes.put("visible", getVisible());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planColumnSettingsId = (Long) attributes.get(
                "planColumnSettingsId");

        if (planColumnSettingsId != null) {
            setPlanColumnSettingsId(planColumnSettingsId);
        }

        String columnName = (String) attributes.get("columnName");

        if (columnName != null) {
            setColumnName(columnName);
        }

        Long planUserSettingsId = (Long) attributes.get("planUserSettingsId");

        if (planUserSettingsId != null) {
            setPlanUserSettingsId(planUserSettingsId);
        }

        Boolean visible = (Boolean) attributes.get("visible");

        if (visible != null) {
            setVisible(visible);
        }
    }

    /**
    * Returns the primary key of this plan column settings.
    *
    * @return the primary key of this plan column settings
    */
    @Override
    public long getPrimaryKey() {
        return _planColumnSettings.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan column settings.
    *
    * @param primaryKey the primary key of this plan column settings
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planColumnSettings.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan column settings ID of this plan column settings.
    *
    * @return the plan column settings ID of this plan column settings
    */
    @Override
    public long getPlanColumnSettingsId() {
        return _planColumnSettings.getPlanColumnSettingsId();
    }

    /**
    * Sets the plan column settings ID of this plan column settings.
    *
    * @param planColumnSettingsId the plan column settings ID of this plan column settings
    */
    @Override
    public void setPlanColumnSettingsId(long planColumnSettingsId) {
        _planColumnSettings.setPlanColumnSettingsId(planColumnSettingsId);
    }

    /**
    * Returns the column name of this plan column settings.
    *
    * @return the column name of this plan column settings
    */
    @Override
    public java.lang.String getColumnName() {
        return _planColumnSettings.getColumnName();
    }

    /**
    * Sets the column name of this plan column settings.
    *
    * @param columnName the column name of this plan column settings
    */
    @Override
    public void setColumnName(java.lang.String columnName) {
        _planColumnSettings.setColumnName(columnName);
    }

    /**
    * Returns the plan user settings ID of this plan column settings.
    *
    * @return the plan user settings ID of this plan column settings
    */
    @Override
    public long getPlanUserSettingsId() {
        return _planColumnSettings.getPlanUserSettingsId();
    }

    /**
    * Sets the plan user settings ID of this plan column settings.
    *
    * @param planUserSettingsId the plan user settings ID of this plan column settings
    */
    @Override
    public void setPlanUserSettingsId(long planUserSettingsId) {
        _planColumnSettings.setPlanUserSettingsId(planUserSettingsId);
    }

    /**
    * Returns the visible of this plan column settings.
    *
    * @return the visible of this plan column settings
    */
    @Override
    public boolean getVisible() {
        return _planColumnSettings.getVisible();
    }

    /**
    * Returns <code>true</code> if this plan column settings is visible.
    *
    * @return <code>true</code> if this plan column settings is visible; <code>false</code> otherwise
    */
    @Override
    public boolean isVisible() {
        return _planColumnSettings.isVisible();
    }

    /**
    * Sets whether this plan column settings is visible.
    *
    * @param visible the visible of this plan column settings
    */
    @Override
    public void setVisible(boolean visible) {
        _planColumnSettings.setVisible(visible);
    }

    @Override
    public boolean isNew() {
        return _planColumnSettings.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planColumnSettings.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planColumnSettings.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planColumnSettings.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planColumnSettings.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planColumnSettings.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planColumnSettings.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planColumnSettings.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planColumnSettings.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planColumnSettings.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planColumnSettings.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanColumnSettingsWrapper((PlanColumnSettings) _planColumnSettings.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.PlanColumnSettings planColumnSettings) {
        return _planColumnSettings.compareTo(planColumnSettings);
    }

    @Override
    public int hashCode() {
        return _planColumnSettings.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanColumnSettings> toCacheModel() {
        return _planColumnSettings.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanColumnSettings toEscapedModel() {
        return new PlanColumnSettingsWrapper(_planColumnSettings.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanColumnSettings toUnescapedModel() {
        return new PlanColumnSettingsWrapper(_planColumnSettings.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planColumnSettings.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planColumnSettings.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planColumnSettings.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanColumnSettingsWrapper)) {
            return false;
        }

        PlanColumnSettingsWrapper planColumnSettingsWrapper = (PlanColumnSettingsWrapper) obj;

        if (Validator.equals(_planColumnSettings,
                    planColumnSettingsWrapper._planColumnSettings)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanColumnSettings getWrappedPlanColumnSettings() {
        return _planColumnSettings;
    }

    @Override
    public PlanColumnSettings getWrappedModel() {
        return _planColumnSettings;
    }

    @Override
    public void resetOriginalValues() {
        _planColumnSettings.resetOriginalValues();
    }
}
