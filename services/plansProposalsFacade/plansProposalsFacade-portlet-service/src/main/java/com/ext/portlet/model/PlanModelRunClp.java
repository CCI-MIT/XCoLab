package com.ext.portlet.model;

import com.ext.portlet.service.PlanModelRunLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanModelRunClp extends BaseModelImpl<PlanModelRun>
    implements PlanModelRun {
    private long _id;
    private long _planId;
    private long _scenarioId;
    private long _planVersion;
    private long _version;
    private Date _created;
    private long _updateAuthorId;

    public PlanModelRunClp() {
    }

    public Class<?> getModelClass() {
        return PlanModelRun.class;
    }

    public String getModelClassName() {
        return PlanModelRun.class.getName();
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

    public long getScenarioId() {
        return _scenarioId;
    }

    public void setScenarioId(long scenarioId) {
        _scenarioId = scenarioId;
    }

    public long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(long planVersion) {
        _planVersion = planVersion;
    }

    public long getVersion() {
        return _version;
    }

    public void setVersion(long version) {
        _version = version;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanModelRunLocalServiceUtil.addPlanModelRun(this);
        } else {
            PlanModelRunLocalServiceUtil.updatePlanModelRun(this);
        }
    }

    @Override
    public PlanModelRun toEscapedModel() {
        return (PlanModelRun) Proxy.newProxyInstance(PlanModelRun.class.getClassLoader(),
            new Class[] { PlanModelRun.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanModelRunClp clone = new PlanModelRunClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setScenarioId(getScenarioId());
        clone.setPlanVersion(getPlanVersion());
        clone.setVersion(getVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    public int compareTo(PlanModelRun planModelRun) {
        int value = 0;

        if (getId() < planModelRun.getId()) {
            value = -1;
        } else if (getId() > planModelRun.getId()) {
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

        PlanModelRunClp planModelRun = null;

        try {
            planModelRun = (PlanModelRunClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planModelRun.getPrimaryKey();

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
        sb.append(", scenarioId=");
        sb.append(getScenarioId());
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanModelRun");
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
            "<column><column-name>scenarioId</column-name><column-value><![CDATA[");
        sb.append(getScenarioId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planVersion</column-name><column-value><![CDATA[");
        sb.append(getPlanVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
