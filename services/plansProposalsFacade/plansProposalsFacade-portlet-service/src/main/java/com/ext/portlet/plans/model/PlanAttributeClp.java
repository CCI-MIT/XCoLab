package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanAttributeClp extends BaseModelImpl<PlanAttribute>
    implements PlanAttribute {
    private Long _attributeId;
    private Long _planId;
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

    public Long getPrimaryKey() {
        return _attributeId;
    }

    public void setPrimaryKey(Long primaryKey) {
        setAttributeId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_attributeId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getAttributeId() {
        return _attributeId;
    }

    public void setAttributeId(Long attributeId) {
        _attributeId = attributeId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
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


    public java.lang.Object getTypedValue() {
        throw new UnsupportedOperationException();
    }

    public void setAttributeValue(java.lang.String attributeValue) {
        throw new UnsupportedOperationException();
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
        Long primaryKey = planAttribute.getPrimaryKey();

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

        Long primaryKey = planAttribute.getPrimaryKey();

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
        sb.append("com.ext.portlet.plans.model.PlanAttribute");
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
