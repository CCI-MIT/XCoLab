package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanTemplateLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanTemplateClp extends BaseModelImpl<PlanTemplate>
    implements PlanTemplate {
    private Long _id;
    private String _name;

    public PlanTemplateClp() {
    }

    public Class<?> getModelClass() {
        return PlanTemplate.class;
    }

    public String getModelClassName() {
        return PlanTemplate.class.getName();
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> getSections() {
        throw new UnsupportedOperationException();
    }

    public void addSection(
        com.ext.portlet.plans.model.PlanSectionDefinition section) {
        throw new UnsupportedOperationException();
    }

    public void removeSection(
        com.ext.portlet.plans.model.PlanSectionDefinition section) {
        throw new UnsupportedOperationException();
    }

    public void updateSectionWeight(
        com.ext.portlet.plans.model.PlanSectionDefinition section, int weight) {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTemplateLocalServiceUtil.addPlanTemplate(this);
        } else {
            PlanTemplateLocalServiceUtil.updatePlanTemplate(this);
        }
    }

    @Override
    public PlanTemplate toEscapedModel() {
        return (PlanTemplate) Proxy.newProxyInstance(PlanTemplate.class.getClassLoader(),
            new Class[] { PlanTemplate.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTemplateClp clone = new PlanTemplateClp();

        clone.setId(getId());
        clone.setName(getName());

        return clone;
    }

    public int compareTo(PlanTemplate planTemplate) {
        Long primaryKey = planTemplate.getPrimaryKey();

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

        PlanTemplateClp planTemplate = null;

        try {
            planTemplate = (PlanTemplateClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = planTemplate.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", name=");
        sb.append(getName());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanTemplate");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
