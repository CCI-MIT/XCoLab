package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanColumnSettingsLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanColumnSettingsClp extends BaseModelImpl<PlanColumnSettings>
    implements PlanColumnSettings {
    private Long _planColumnSettingsId;
    private String _columnName;
    private Long _planUserSettingsId;
    private Boolean _visible;

    public PlanColumnSettingsClp() {
    }

    public Class<?> getModelClass() {
        return PlanColumnSettings.class;
    }

    public String getModelClassName() {
        return PlanColumnSettings.class.getName();
    }

    public Long getPrimaryKey() {
        return _planColumnSettingsId;
    }

    public void setPrimaryKey(Long primaryKey) {
        setPlanColumnSettingsId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_planColumnSettingsId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getPlanColumnSettingsId() {
        return _planColumnSettingsId;
    }

    public void setPlanColumnSettingsId(Long planColumnSettingsId) {
        _planColumnSettingsId = planColumnSettingsId;
    }

    public String getColumnName() {
        return _columnName;
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;
    }

    public Long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    public void setPlanUserSettingsId(Long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;
    }

    public Boolean getVisible() {
        return _visible;
    }

    public void setVisible(Boolean visible) {
        _visible = visible;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanColumnSettingsLocalServiceUtil.addPlanColumnSettings(this);
        } else {
            PlanColumnSettingsLocalServiceUtil.updatePlanColumnSettings(this);
        }
    }

    @Override
    public PlanColumnSettings toEscapedModel() {
        return (PlanColumnSettings) Proxy.newProxyInstance(PlanColumnSettings.class.getClassLoader(),
            new Class[] { PlanColumnSettings.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanColumnSettingsClp clone = new PlanColumnSettingsClp();

        clone.setPlanColumnSettingsId(getPlanColumnSettingsId());
        clone.setColumnName(getColumnName());
        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setVisible(getVisible());

        return clone;
    }

    public int compareTo(PlanColumnSettings planColumnSettings) {
        Long primaryKey = planColumnSettings.getPrimaryKey();

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

        PlanColumnSettingsClp planColumnSettings = null;

        try {
            planColumnSettings = (PlanColumnSettingsClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = planColumnSettings.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{planColumnSettingsId=");
        sb.append(getPlanColumnSettingsId());
        sb.append(", columnName=");
        sb.append(getColumnName());
        sb.append(", planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", visible=");
        sb.append(getVisible());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanColumnSettings");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planColumnSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanColumnSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnName</column-name><column-value><![CDATA[");
        sb.append(getColumnName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visible</column-name><column-value><![CDATA[");
        sb.append(getVisible());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
