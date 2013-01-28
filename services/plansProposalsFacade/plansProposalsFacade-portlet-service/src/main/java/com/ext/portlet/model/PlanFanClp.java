package com.ext.portlet.model;

import com.ext.portlet.service.PlanFanLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanFanClp extends BaseModelImpl<PlanFan> implements PlanFan {
    private long _id;
    private long _userId;
    private String _userUuid;
    private long _planId;
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

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
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

        long primaryKey = planFan.getPrimaryKey();

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
        sb.append("com.ext.portlet.model.PlanFan");
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
