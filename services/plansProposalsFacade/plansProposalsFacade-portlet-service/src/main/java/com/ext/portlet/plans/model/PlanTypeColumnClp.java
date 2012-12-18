package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanTypeColumnLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanTypeColumnClp extends BaseModelImpl<PlanTypeColumn>
    implements PlanTypeColumn {
    private long _planTypeColumnId;
    private long _planTypeId;
    private int _weight;
    private String _columnName;
    private boolean _visibleByDefault;

    public PlanTypeColumnClp() {
    }

    public Class<?> getModelClass() {
        return PlanTypeColumn.class;
    }

    public String getModelClassName() {
        return PlanTypeColumn.class.getName();
    }

    public long getPrimaryKey() {
        return _planTypeColumnId;
    }

    public void setPrimaryKey(long primaryKey) {
        setPlanTypeColumnId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_planTypeColumnId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getPlanTypeColumnId() {
        return _planTypeColumnId;
    }

    public void setPlanTypeColumnId(long planTypeColumnId) {
        _planTypeColumnId = planTypeColumnId;
    }

    public long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;
    }

    public int getWeight() {
        return _weight;
    }

    public void setWeight(int weight) {
        _weight = weight;
    }

    public String getColumnName() {
        return _columnName;
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;
    }

    public boolean getVisibleByDefault() {
        return _visibleByDefault;
    }

    public boolean isVisibleByDefault() {
        return _visibleByDefault;
    }

    public void setVisibleByDefault(boolean visibleByDefault) {
        _visibleByDefault = visibleByDefault;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTypeColumnLocalServiceUtil.addPlanTypeColumn(this);
        } else {
            PlanTypeColumnLocalServiceUtil.updatePlanTypeColumn(this);
        }
    }

    @Override
    public PlanTypeColumn toEscapedModel() {
        return (PlanTypeColumn) Proxy.newProxyInstance(PlanTypeColumn.class.getClassLoader(),
            new Class[] { PlanTypeColumn.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTypeColumnClp clone = new PlanTypeColumnClp();

        clone.setPlanTypeColumnId(getPlanTypeColumnId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setWeight(getWeight());
        clone.setColumnName(getColumnName());
        clone.setVisibleByDefault(getVisibleByDefault());

        return clone;
    }

    public int compareTo(PlanTypeColumn planTypeColumn) {
        int value = 0;

        if (getWeight() < planTypeColumn.getWeight()) {
            value = -1;
        } else if (getWeight() > planTypeColumn.getWeight()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        value = getColumnName().toLowerCase()
                    .compareTo(planTypeColumn.getColumnName().toLowerCase());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanTypeColumnClp planTypeColumn = null;

        try {
            planTypeColumn = (PlanTypeColumnClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planTypeColumn.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{planTypeColumnId=");
        sb.append(getPlanTypeColumnId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append(", columnName=");
        sb.append(getColumnName());
        sb.append(", visibleByDefault=");
        sb.append(getVisibleByDefault());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanTypeColumn");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTypeColumnId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeColumnId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnName</column-name><column-value><![CDATA[");
        sb.append(getColumnName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visibleByDefault</column-name><column-value><![CDATA[");
        sb.append(getVisibleByDefault());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
