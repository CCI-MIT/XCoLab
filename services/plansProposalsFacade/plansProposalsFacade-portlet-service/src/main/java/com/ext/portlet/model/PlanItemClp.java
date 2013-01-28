package com.ext.portlet.model;

import com.ext.portlet.service.PlanItemLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanItemClp extends BaseModelImpl<PlanItem> implements PlanItem {
    private long _id;
    private long _planId;
    private String _state;
    private Date _updated;
    private long _updateAuthorId;
    private String _updateType;
    private long _version;

    public PlanItemClp() {
    }

    public Class<?> getModelClass() {
        return PlanItem.class;
    }

    public String getModelClassName() {
        return PlanItem.class.getName();
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

    public String getState() {
        return _state;
    }

    public void setState(String state) {
        _state = state;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public String getUpdateType() {
        return _updateType;
    }

    public void setUpdateType(String updateType) {
        _updateType = updateType;
    }

    public long getVersion() {
        return _version;
    }

    public void setVersion(long version) {
        _version = version;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanItemLocalServiceUtil.addPlanItem(this);
        } else {
            PlanItemLocalServiceUtil.updatePlanItem(this);
        }
    }

    @Override
    public PlanItem toEscapedModel() {
        return (PlanItem) Proxy.newProxyInstance(PlanItem.class.getClassLoader(),
            new Class[] { PlanItem.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanItemClp clone = new PlanItemClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setState(getState());
        clone.setUpdated(getUpdated());
        clone.setUpdateAuthorId(getUpdateAuthorId());
        clone.setUpdateType(getUpdateType());
        clone.setVersion(getVersion());

        return clone;
    }

    public int compareTo(PlanItem planItem) {
        int value = 0;

        if (getId() < planItem.getId()) {
            value = -1;
        } else if (getId() > planItem.getId()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

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

        PlanItemClp planItem = null;

        try {
            planItem = (PlanItemClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planItem.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", state=");
        sb.append(getState());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append(", updateType=");
        sb.append(getUpdateType());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>state</column-name><column-value><![CDATA[");
        sb.append(getState());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateType</column-name><column-value><![CDATA[");
        sb.append(getUpdateType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
