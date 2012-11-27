package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanFanLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanFanClp extends BaseModelImpl<PlanFan> implements PlanFan {
    private Long _id;
    private Long _userId;
    private Long _planId;
    private Date _created;
    private Date _deleted;

    public PlanFanClp() {
    }

    public Class<?> getModelClass() {
        return PlanFan.class;
    }

    public String getModelClassName() {
        return PlanFan.class.getName();
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

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public com.liferay.portal.model.User getUser() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanItem getPlan() {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanFanLocalServiceUtil.addPlanFan(this);
        } else {
            PlanFanLocalServiceUtil.updatePlanFan(this);
        }
    }

    @Override
    public PlanFan toEscapedModel() {
        return (PlanFan) Proxy.newProxyInstance(PlanFan.class.getClassLoader(),
            new Class[] { PlanFan.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanFanClp clone = new PlanFanClp();

        clone.setId(getId());
        clone.setUserId(getUserId());
        clone.setPlanId(getPlanId());
        clone.setCreated(getCreated());
        clone.setDeleted(getDeleted());

        return clone;
    }

    public int compareTo(PlanFan planFan) {
        int value = 0;

        if (getId() < planFan.getId()) {
            value = -1;
        } else if (getId() > planFan.getId()) {
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

        PlanFanClp planFan = null;

        try {
            planFan = (PlanFanClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = planFan.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", deleted=");
        sb.append(getDeleted());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanFan");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deleted</column-name><column-value><![CDATA[");
        sb.append(getDeleted());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
