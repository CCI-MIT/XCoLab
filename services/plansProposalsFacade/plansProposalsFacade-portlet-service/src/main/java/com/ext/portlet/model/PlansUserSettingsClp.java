package com.ext.portlet.model;

import com.ext.portlet.service.PlansUserSettingsLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlansUserSettingsClp extends BaseModelImpl<PlansUserSettings>
    implements PlansUserSettings {
    private long _planUserSettingsId;
    private long _userId;
    private String _userUuid;
    private long _planTypeId;
    private String _sortColumn;
    private String _sortDirection;
    private boolean _filterEnabled;
    private boolean _filterPositionsAll;

    public PlansUserSettingsClp() {
    }

    public Class<?> getModelClass() {
        return PlansUserSettings.class;
    }

    public String getModelClassName() {
        return PlansUserSettings.class.getName();
    }

    public long getPrimaryKey() {
        return _planUserSettingsId;
    }

    public void setPrimaryKey(long primaryKey) {
        setPlanUserSettingsId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_planUserSettingsId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    public void setPlanUserSettingsId(long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    public long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
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

    public boolean getFilterEnabled() {
        return _filterEnabled;
    }

    public boolean isFilterEnabled() {
        return _filterEnabled;
    }

    public void setFilterEnabled(boolean filterEnabled) {
        _filterEnabled = filterEnabled;
    }

    public boolean getFilterPositionsAll() {
        return _filterPositionsAll;
    }

    public boolean isFilterPositionsAll() {
        return _filterPositionsAll;
    }

    public void setFilterPositionsAll(boolean filterPositionsAll) {
        _filterPositionsAll = filterPositionsAll;
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
        long primaryKey = plansUserSettings.getPrimaryKey();

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

        long primaryKey = plansUserSettings.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
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
        sb.append("com.ext.portlet.model.PlansUserSettings");
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
