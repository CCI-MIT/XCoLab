package com.ext.portlet.model;

import com.ext.portlet.service.PlanItemPhaseMapLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanItemPhaseMapClp extends BaseModelImpl<PlanItemPhaseMap>
    implements PlanItemPhaseMap {
    private long _id;
    private long _planId;

    public PlanItemPhaseMapClp() {
    }

    public Class<?> getModelClass() {
        return PlanItemPhaseMap.class;
    }

    public String getModelClassName() {
        return PlanItemPhaseMap.class.getName();
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanItemPhaseMapLocalServiceUtil.addPlanItemPhaseMap(this);
        } else {
            PlanItemPhaseMapLocalServiceUtil.updatePlanItemPhaseMap(this);
        }
    }

    @Override
    public PlanItemPhaseMap toEscapedModel() {
        return (PlanItemPhaseMap) Proxy.newProxyInstance(PlanItemPhaseMap.class.getClassLoader(),
            new Class[] { PlanItemPhaseMap.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanItemPhaseMapClp clone = new PlanItemPhaseMapClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());

        return clone;
    }

    public int compareTo(PlanItemPhaseMap planItemPhaseMap) {
        long primaryKey = planItemPhaseMap.getPrimaryKey();

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

        PlanItemPhaseMapClp planItemPhaseMap = null;

        try {
            planItemPhaseMap = (PlanItemPhaseMapClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planItemPhaseMap.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanItemPhaseMap");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
