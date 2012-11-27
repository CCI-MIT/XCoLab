package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlansUserSettingsClp extends BaseModelImpl<PlansUserSettings>
    implements PlansUserSettings {
    private Long _planUserSettingsId;
    private Long _userId;
    private Long _planTypeId;
    private String _sortColumn;
    private String _sortDirection;
    private Boolean _filterEnabled;
    private Boolean _filterPositionsAll;

    public PlansUserSettingsClp() {
    }

    public Class<?> getModelClass() {
        return PlansUserSettings.class;
    }

    public String getModelClassName() {
        return PlansUserSettings.class.getName();
    }

    public Long getPrimaryKey() {
        return _planUserSettingsId;
    }

    public void setPrimaryKey(Long primaryKey) {
        setPlanUserSettingsId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_planUserSettingsId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    public void setPlanUserSettingsId(Long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public String getSortColumn() {
        return _sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        _sortColumn = sortColumn;
    }

    public String getSortDirection() {
        return _sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        _sortDirection = sortDirection;
    }

    public Boolean getFilterEnabled() {
        return _filterEnabled;
    }

    public void setFilterEnabled(Boolean filterEnabled) {
        _filterEnabled = filterEnabled;
    }

    public Boolean getFilterPositionsAll() {
        return _filterPositionsAll;
    }

    public void setFilterPositionsAll(Boolean filterPositionsAll) {
        _filterPositionsAll = filterPositionsAll;
    }

    public com.ext.portlet.plans.model.PlanColumnSettings getColumnSettings(
        java.lang.String name) {
        throw new UnsupportedOperationException();
    }

    public void addColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings settings) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getUpdatedColumnSettings() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<java.lang.Long> getPositionsIds() {
        throw new UnsupportedOperationException();
    }

    public void setPositionsIds(java.util.List<java.lang.Long> positionsIds) {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanAttributeFilter getAttributeFilter(
        java.lang.String name) {
        throw new UnsupportedOperationException();
    }

    public void addPlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter filter) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getUpdatedPlanAttributeFilters() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanPropertyFilter getPropertyFilter(
        java.lang.String name) {
        throw new UnsupportedOperationException();
    }

    public void addPlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter filter) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> getUpdatedPlanPropertyFilters() {
        throw new UnsupportedOperationException();
    }

    public boolean isFiltersDefined() {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlansUserSettingsLocalServiceUtil.addPlansUserSettings(this);
        } else {
            PlansUserSettingsLocalServiceUtil.updatePlansUserSettings(this);
        }
    }

    @Override
    public PlansUserSettings toEscapedModel() {
        return (PlansUserSettings) Proxy.newProxyInstance(PlansUserSettings.class.getClassLoader(),
            new Class[] { PlansUserSettings.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlansUserSettingsClp clone = new PlansUserSettingsClp();

        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setUserId(getUserId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setSortColumn(getSortColumn());
        clone.setSortDirection(getSortDirection());
        clone.setFilterEnabled(getFilterEnabled());
        clone.setFilterPositionsAll(getFilterPositionsAll());

        return clone;
    }

    public int compareTo(PlansUserSettings plansUserSettings) {
        Long primaryKey = plansUserSettings.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlansUserSettingsClp plansUserSettings = null;

        try {
            plansUserSettings = (PlansUserSettingsClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = plansUserSettings.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", sortColumn=");
        sb.append(getSortColumn());
        sb.append(", sortDirection=");
        sb.append(getSortDirection());
        sb.append(", filterEnabled=");
        sb.append(getFilterEnabled());
        sb.append(", filterPositionsAll=");
        sb.append(getFilterPositionsAll());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlansUserSettings");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sortColumn</column-name><column-value><![CDATA[");
        sb.append(getSortColumn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sortDirection</column-name><column-value><![CDATA[");
        sb.append(getSortDirection());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>filterEnabled</column-name><column-value><![CDATA[");
        sb.append(getFilterEnabled());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>filterPositionsAll</column-name><column-value><![CDATA[");
        sb.append(getFilterPositionsAll());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
