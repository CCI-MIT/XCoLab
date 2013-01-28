package com.ext.portlet.model;

import com.ext.portlet.service.PlanAttributeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanAttributeClp extends BaseModelImpl<PlanAttribute>
    implements PlanAttribute {
    private long _attributeId;
    private long _planId;
    private String _attributeName;
    private String _attributeValue;

    public PlanAttributeClp() {
    }

    public Class<?> getModelClass() {
        return PlanAttribute.class;
    }

    public String getModelClassName() {
        return PlanAttribute.class.getName();
    }

    public long getPrimaryKey() {
        return _attributeId;
    }

    public void setPrimaryKey(long primaryKey) {
        setAttributeId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_attributeId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getAttributeId() {
        return _attributeId;
    }

    public void setAttributeId(long attributeId) {
        _attributeId = attributeId;
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public String getAttributeName() {
        return _attributeName;
    }

    public void setAttributeName(String attributeName) {
        _attributeName = attributeName;
    }

    public String getAttributeValue() {
        return _attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        _attributeValue = attributeValue;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanAttributeLocalServiceUtil.addPlanAttribute(this);
        } else {
            PlanAttributeLocalServiceUtil.updatePlanAttribute(this);
        }
    }

    @Override
    public PlanAttribute toEscapedModel() {
        return (PlanAttribute) Proxy.newProxyInstance(PlanAttribute.class.getClassLoader(),
            new Class[] { PlanAttribute.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanAttributeClp clone = new PlanAttributeClp();

        clone.setAttributeId(getAttributeId());
        clone.setPlanId(getPlanId());
        clone.setAttributeName(getAttributeName());
        clone.setAttributeValue(getAttributeValue());

        return clone;
    }

    public int compareTo(PlanAttribute planAttribute) {
        long primaryKey = planAttribute.getPrimaryKey();

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

        PlanAttributeClp planAttribute = null;

        try {
            planAttribute = (PlanAttributeClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planAttribute.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{attributeId=");
        sb.append(getAttributeId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", attributeName=");
        sb.append(getAttributeName());
        sb.append(", attributeValue=");
        sb.append(getAttributeValue());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanAttribute");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>attributeId</column-name><column-value><![CDATA[");
        sb.append(getAttributeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeName</column-name><column-value><![CDATA[");
        sb.append(getAttributeName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeValue</column-name><column-value><![CDATA[");
        sb.append(getAttributeValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
