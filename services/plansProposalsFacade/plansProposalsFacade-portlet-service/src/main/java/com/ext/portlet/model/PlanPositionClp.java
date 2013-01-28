package com.ext.portlet.model;

import com.ext.portlet.service.PlanPositionLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanPositionPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanPositionClp extends BaseModelImpl<PlanPosition>
    implements PlanPosition {
    private long _planId;
    private long _positionId;
    private long _userId;
    private String _userUuid;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;

    public PlanPositionClp() {
    }

    public Class<?> getModelClass() {
        return PlanPosition.class;
    }

    public String getModelClassName() {
        return PlanPosition.class.getName();
    }

    public PlanPositionPK getPrimaryKey() {
        return new PlanPositionPK(_planId, _positionId);
    }

    public void setPrimaryKey(PlanPositionPK primaryKey) {
        setPlanId(primaryKey.planId);
        setPositionId(primaryKey.positionId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanPositionPK(_planId, _positionId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanPositionPK) primaryKeyObj);
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public long getPositionId() {
        return _positionId;
    }

    public void setPositionId(long positionId) {
        _positionId = positionId;
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

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanPositionLocalServiceUtil.addPlanPosition(this);
        } else {
            PlanPositionLocalServiceUtil.updatePlanPosition(this);
        }
    }

    @Override
    public PlanPosition toEscapedModel() {
        return (PlanPosition) Proxy.newProxyInstance(PlanPosition.class.getClassLoader(),
            new Class[] { PlanPosition.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanPositionClp clone = new PlanPositionClp();

        clone.setPlanId(getPlanId());
        clone.setPositionId(getPositionId());
        clone.setUserId(getUserId());
        clone.setUserName(getUserName());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    public int compareTo(PlanPosition planPosition) {
        int value = 0;

        if (getPlanId() < planPosition.getPlanId()) {
            value = -1;
        } else if (getPlanId() > planPosition.getPlanId()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (getPositionId() < planPosition.getPositionId()) {
            value = -1;
        } else if (getPositionId() > planPosition.getPositionId()) {
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

        PlanPositionClp planPosition = null;

        try {
            planPosition = (PlanPositionClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanPositionPK primaryKey = planPosition.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{planId=");
        sb.append(getPlanId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", userName=");
        sb.append(getUserName());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanPosition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userName</column-name><column-value><![CDATA[");
        sb.append(getUserName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
