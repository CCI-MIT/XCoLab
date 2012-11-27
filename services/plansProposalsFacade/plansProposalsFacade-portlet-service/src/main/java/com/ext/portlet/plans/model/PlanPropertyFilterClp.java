package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanPropertyFilterLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanPropertyFilterClp extends BaseModelImpl<PlanPropertyFilter>
    implements PlanPropertyFilter {
    private Long _planPropertyFilterId;
    private String _propertyName;
    private Long _planUserSettingsId;
    private String _value;

    public PlanPropertyFilterClp() {
    }

    public Class<?> getModelClass() {
        return PlanPropertyFilter.class;
    }

    public String getModelClassName() {
        return PlanPropertyFilter.class.getName();
    }

    public Long getPrimaryKey() {
        return _planPropertyFilterId;
    }

    public void setPrimaryKey(Long primaryKey) {
        setPlanPropertyFilterId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_planPropertyFilterId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getPlanPropertyFilterId() {
        return _planPropertyFilterId;
    }

    public void setPlanPropertyFilterId(Long planPropertyFilterId) {
        _planPropertyFilterId = planPropertyFilterId;
    }

    public String getPropertyName() {
        return _propertyName;
    }

    public void setPropertyName(String propertyName) {
        _propertyName = propertyName;
    }

    public Long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    public void setPlanUserSettingsId(Long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;
    }

    public String getValue() {
        return _value;
    }

    public void setValue(String value) {
        _value = value;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanPropertyFilterLocalServiceUtil.addPlanPropertyFilter(this);
        } else {
            PlanPropertyFilterLocalServiceUtil.updatePlanPropertyFilter(this);
        }
    }

    @Override
    public PlanPropertyFilter toEscapedModel() {
        return (PlanPropertyFilter) Proxy.newProxyInstance(PlanPropertyFilter.class.getClassLoader(),
            new Class[] { PlanPropertyFilter.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanPropertyFilterClp clone = new PlanPropertyFilterClp();

        clone.setPlanPropertyFilterId(getPlanPropertyFilterId());
        clone.setPropertyName(getPropertyName());
        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setValue(getValue());

        return clone;
    }

    public int compareTo(PlanPropertyFilter planPropertyFilter) {
        Long primaryKey = planPropertyFilter.getPrimaryKey();

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

        PlanPropertyFilterClp planPropertyFilter = null;

        try {
            planPropertyFilter = (PlanPropertyFilterClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = planPropertyFilter.getPrimaryKey();

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

        sb.append("{planPropertyFilterId=");
        sb.append(getPlanPropertyFilterId());
        sb.append(", propertyName=");
        sb.append(getPropertyName());
        sb.append(", planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", value=");
        sb.append(getValue());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanPropertyFilter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planPropertyFilterId</column-name><column-value><![CDATA[");
        sb.append(getPlanPropertyFilterId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>propertyName</column-name><column-value><![CDATA[");
        sb.append(getPropertyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>value</column-name><column-value><![CDATA[");
        sb.append(getValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
