package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanTemplateSectionClp extends BaseModelImpl<PlanTemplateSection>
    implements PlanTemplateSection {
    private Long _planTemplateId;
    private Long _planSectionId;
    private Integer _weight;

    public PlanTemplateSectionClp() {
    }

    public Class<?> getModelClass() {
        return PlanTemplateSection.class;
    }

    public String getModelClassName() {
        return PlanTemplateSection.class.getName();
    }

    public PlanTemplateSectionPK getPrimaryKey() {
        return new PlanTemplateSectionPK(_planTemplateId, _planSectionId);
    }

    public void setPrimaryKey(PlanTemplateSectionPK primaryKey) {
        setPlanTemplateId(primaryKey.planTemplateId);
        setPlanSectionId(primaryKey.planSectionId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanTemplateSectionPK(_planTemplateId, _planSectionId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanTemplateSectionPK) primaryKeyObj);
    }

    public Long getPlanTemplateId() {
        return _planTemplateId;
    }

    public void setPlanTemplateId(Long planTemplateId) {
        _planTemplateId = planTemplateId;
    }

    public Long getPlanSectionId() {
        return _planSectionId;
    }

    public void setPlanSectionId(Long planSectionId) {
        _planSectionId = planSectionId;
    }

    public Integer getWeight() {
        return _weight;
    }

    public void setWeight(Integer weight) {
        _weight = weight;
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTemplateSectionLocalServiceUtil.addPlanTemplateSection(this);
        } else {
            PlanTemplateSectionLocalServiceUtil.updatePlanTemplateSection(this);
        }
    }

    @Override
    public PlanTemplateSection toEscapedModel() {
        return (PlanTemplateSection) Proxy.newProxyInstance(PlanTemplateSection.class.getClassLoader(),
            new Class[] { PlanTemplateSection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTemplateSectionClp clone = new PlanTemplateSectionClp();

        clone.setPlanTemplateId(getPlanTemplateId());
        clone.setPlanSectionId(getPlanSectionId());
        clone.setWeight(getWeight());

        return clone;
    }

    public int compareTo(PlanTemplateSection planTemplateSection) {
        int value = 0;

        if (getWeight() < planTemplateSection.getWeight()) {
            value = -1;
        } else if (getWeight() > planTemplateSection.getWeight()) {
            value = 1;
        } else {
            value = 0;
        }

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

        PlanTemplateSectionClp planTemplateSection = null;

        try {
            planTemplateSection = (PlanTemplateSectionClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanTemplateSectionPK primaryKey = planTemplateSection.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
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
        StringBundler sb = new StringBundler(7);

        sb.append("{planTemplateId=");
        sb.append(getPlanTemplateId());
        sb.append(", planSectionId=");
        sb.append(getPlanSectionId());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanTemplateSection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTemplateId</column-name><column-value><![CDATA[");
        sb.append(getPlanTemplateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planSectionId</column-name><column-value><![CDATA[");
        sb.append(getPlanSectionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
