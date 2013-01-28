package com.ext.portlet.model;

import com.ext.portlet.service.PlanTypeAttributeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanTypeAttributeClp extends BaseModelImpl<PlanTypeAttribute>
    implements PlanTypeAttribute {
    private long _planTypeAttributeId;
    private long _planTypeId;
    private String _attributeName;

    public PlanTypeAttributeClp() {
    }

    public Class<?> getModelClass() {
        return PlanTypeAttribute.class;
    }

    public String getModelClassName() {
        return PlanTypeAttribute.class.getName();
    }

    public long getPrimaryKey() {
        return _planTypeAttributeId;
    }

    public void setPrimaryKey(long primaryKey) {
        setPlanTypeAttributeId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_planTypeAttributeId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getPlanTypeAttributeId() {
        return _planTypeAttributeId;
    }

    public void setPlanTypeAttributeId(long planTypeAttributeId) {
        _planTypeAttributeId = planTypeAttributeId;
    }

    public long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;
    }

    public String getAttributeName() {
        return _attributeName;
    }

    public void setAttributeName(String attributeName) {
        _attributeName = attributeName;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTypeAttributeLocalServiceUtil.addPlanTypeAttribute(this);
        } else {
            PlanTypeAttributeLocalServiceUtil.updatePlanTypeAttribute(this);
        }
    }

    @Override
    public PlanTypeAttribute toEscapedModel() {
        return (PlanTypeAttribute) Proxy.newProxyInstance(PlanTypeAttribute.class.getClassLoader(),
            new Class[] { PlanTypeAttribute.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTypeAttributeClp clone = new PlanTypeAttributeClp();

        clone.setPlanTypeAttributeId(getPlanTypeAttributeId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setAttributeName(getAttributeName());

        return clone;
    }

    public int compareTo(PlanTypeAttribute planTypeAttribute) {
        long primaryKey = planTypeAttribute.getPrimaryKey();

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

        PlanTypeAttributeClp planTypeAttribute = null;

        try {
            planTypeAttribute = (PlanTypeAttributeClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planTypeAttribute.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{planTypeAttributeId=");
        sb.append(getPlanTypeAttributeId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", attributeName=");
        sb.append(getAttributeName());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanTypeAttribute");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTypeAttributeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeAttributeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeName</column-name><column-value><![CDATA[");
        sb.append(getAttributeName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
