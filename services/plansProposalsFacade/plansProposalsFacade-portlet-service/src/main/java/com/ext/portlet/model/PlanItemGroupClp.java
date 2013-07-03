package com.ext.portlet.model;

import com.ext.portlet.service.PlanItemGroupLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanItemGroupClp extends BaseModelImpl<PlanItemGroup>
    implements PlanItemGroup {
    private long _planId;
    private long _groupId;

    public PlanItemGroupClp() {
    }

    public Class<?> getModelClass() {
        return PlanItemGroup.class;
    }

    public String getModelClassName() {
        return PlanItemGroup.class.getName();
    }

    public long getPrimaryKey() {
        return _planId;
    }

    public void setPrimaryKey(long primaryKey) {
        setPlanId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_planId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanItemGroupLocalServiceUtil.addPlanItemGroup(this);
        } else {
            PlanItemGroupLocalServiceUtil.updatePlanItemGroup(this);
        }
    }

    @Override
    public PlanItemGroup toEscapedModel() {
        return (PlanItemGroup) Proxy.newProxyInstance(PlanItemGroup.class.getClassLoader(),
            new Class[] { PlanItemGroup.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanItemGroupClp clone = new PlanItemGroupClp();

        clone.setPlanId(getPlanId());
        clone.setGroupId(getGroupId());

        return clone;
    }

    public int compareTo(PlanItemGroup planItemGroup) {
        long primaryKey = planItemGroup.getPrimaryKey();

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

        PlanItemGroupClp planItemGroup = null;

        try {
            planItemGroup = (PlanItemGroupClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planItemGroup.getPrimaryKey();

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

        sb.append("{planId=");
        sb.append(getPlanId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanItemGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
