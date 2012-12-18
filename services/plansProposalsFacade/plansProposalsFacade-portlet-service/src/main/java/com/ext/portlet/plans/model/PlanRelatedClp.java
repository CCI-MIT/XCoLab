package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanRelatedLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanRelatedPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanRelatedClp extends BaseModelImpl<PlanRelated>
    implements PlanRelated {
    private long _sectionId;
    private long _relatedPlanId;

    public PlanRelatedClp() {
    }

    public Class<?> getModelClass() {
        return PlanRelated.class;
    }

    public String getModelClassName() {
        return PlanRelated.class.getName();
    }

    public PlanRelatedPK getPrimaryKey() {
        return new PlanRelatedPK(_sectionId, _relatedPlanId);
    }

    public void setPrimaryKey(PlanRelatedPK primaryKey) {
        setSectionId(primaryKey.sectionId);
        setRelatedPlanId(primaryKey.relatedPlanId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanRelatedPK(_sectionId, _relatedPlanId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanRelatedPK) primaryKeyObj);
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
            PlanRelatedLocalServiceUtil.addPlanRelated(this);
        } else {
            PlanRelatedLocalServiceUtil.updatePlanRelated(this);
        }
    }

    @Override
    public PlanRelated toEscapedModel() {
        return (PlanRelated) Proxy.newProxyInstance(PlanRelated.class.getClassLoader(),
            new Class[] { PlanRelated.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanRelatedClp clone = new PlanRelatedClp();

        clone.setSectionId(getSectionId());
        clone.setRelatedPlanId(getRelatedPlanId());

        return clone;
    }

    public int compareTo(PlanRelated planRelated) {
        PlanRelatedPK primaryKey = planRelated.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanRelatedClp planRelated = null;

        try {
            planRelated = (PlanRelatedClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanRelatedPK primaryKey = planRelated.getPrimaryKey();

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
        sb.append("com.ext.portlet.plans.model.PlanRelated");
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
