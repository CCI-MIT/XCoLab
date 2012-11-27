package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanPositionsClp extends BaseModelImpl<PlanPositions>
    implements PlanPositions {
    private Long _id;
    private Long _planId;
    private Long _planVersion;
    private Long _version;
    private Date _created;
    private Long _updateAuthorId;

    public PlanPositionsClp() {
    }

    public Class<?> getModelClass() {
        return PlanPositions.class;
    }

    public String getModelClassName() {
        return PlanPositions.class.getName();
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

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public Long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(Long planVersion) {
        _planVersion = planVersion;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public java.util.List<java.lang.Long> getPositionsIds() {
        throw new UnsupportedOperationException();
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public void setPositionsIds(java.util.List<java.lang.Long> positionsIds) {
        throw new UnsupportedOperationException();
    }

    public com.liferay.portal.model.User getUpdateAuthor() {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanPositionsLocalServiceUtil.addPlanPositions(this);
        } else {
            PlanPositionsLocalServiceUtil.updatePlanPositions(this);
        }
    }

    @Override
    public PlanPositions toEscapedModel() {
        return (PlanPositions) Proxy.newProxyInstance(PlanPositions.class.getClassLoader(),
            new Class[] { PlanPositions.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanPositionsClp clone = new PlanPositionsClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setPlanVersion(getPlanVersion());
        clone.setVersion(getVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    public int compareTo(PlanPositions planPositions) {
        int value = 0;

        if (getId() < planPositions.getId()) {
            value = -1;
        } else if (getId() > planPositions.getId()) {
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

        PlanPositionsClp planPositions = null;

        try {
            planPositions = (PlanPositionsClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = planPositions.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
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
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanPositions");
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
