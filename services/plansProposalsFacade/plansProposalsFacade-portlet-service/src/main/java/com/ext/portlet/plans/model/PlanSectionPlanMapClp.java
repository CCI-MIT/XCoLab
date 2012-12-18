package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanSectionPlanMapLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanSectionPlanMapClp extends BaseModelImpl<PlanSectionPlanMap>
    implements PlanSectionPlanMap {
    private long _sectionId;
    private long _relatedPlanId;

    public PlanSectionPlanMapClp() {
    }

    public Class<?> getModelClass() {
        return PlanSectionPlanMap.class;
    }

    public String getModelClassName() {
        return PlanSectionPlanMap.class.getName();
    }

    public PlanSectionPlanMapPK getPrimaryKey() {
        return new PlanSectionPlanMapPK(_sectionId, _relatedPlanId);
    }

    public void setPrimaryKey(PlanSectionPlanMapPK primaryKey) {
        setSectionId(primaryKey.sectionId);
        setRelatedPlanId(primaryKey.relatedPlanId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanSectionPlanMapPK(_sectionId, _relatedPlanId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanSectionPlanMapPK) primaryKeyObj);
    }

    public long getSectionId() {
        return _sectionId;
    }

    public void setSectionId(long sectionId) {
        _sectionId = sectionId;
    }

    public long getRelatedPlanId() {
        return _relatedPlanId;
    }

    public void setRelatedPlanId(long relatedPlanId) {
        _relatedPlanId = relatedPlanId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanSectionPlanMapLocalServiceUtil.addPlanSectionPlanMap(this);
        } else {
            PlanSectionPlanMapLocalServiceUtil.updatePlanSectionPlanMap(this);
        }
    }

    @Override
    public PlanSectionPlanMap toEscapedModel() {
        return (PlanSectionPlanMap) Proxy.newProxyInstance(PlanSectionPlanMap.class.getClassLoader(),
            new Class[] { PlanSectionPlanMap.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanSectionPlanMapClp clone = new PlanSectionPlanMapClp();

        clone.setSectionId(getSectionId());
        clone.setRelatedPlanId(getRelatedPlanId());

        return clone;
    }

    public int compareTo(PlanSectionPlanMap planSectionPlanMap) {
        PlanSectionPlanMapPK primaryKey = planSectionPlanMap.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanSectionPlanMapClp planSectionPlanMap = null;

        try {
            planSectionPlanMap = (PlanSectionPlanMapClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanSectionPlanMapPK primaryKey = planSectionPlanMap.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{sectionId=");
        sb.append(getSectionId());
        sb.append(", relatedPlanId=");
        sb.append(getRelatedPlanId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanSectionPlanMap");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>sectionId</column-name><column-value><![CDATA[");
        sb.append(getSectionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relatedPlanId</column-name><column-value><![CDATA[");
        sb.append(getRelatedPlanId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
