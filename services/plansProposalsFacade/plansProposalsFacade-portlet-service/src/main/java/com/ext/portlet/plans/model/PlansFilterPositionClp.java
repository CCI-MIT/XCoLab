package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlansFilterPositionLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlansFilterPositionPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlansFilterPositionClp extends BaseModelImpl<PlansFilterPosition>
    implements PlansFilterPosition {
    private long _userId;
    private String _userUuid;
    private long _planTypeId;
    private long _positionId;

    public PlansFilterPositionClp() {
    }

    public Class<?> getModelClass() {
        return PlansFilterPosition.class;
    }

    public String getModelClassName() {
        return PlansFilterPosition.class.getName();
    }

    public PlansFilterPositionPK getPrimaryKey() {
        return new PlansFilterPositionPK(_userId, _planTypeId, _positionId);
    }

    public void setPrimaryKey(PlansFilterPositionPK primaryKey) {
        setUserId(primaryKey.userId);
        setPlanTypeId(primaryKey.planTypeId);
        setPositionId(primaryKey.positionId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlansFilterPositionPK(_userId, _planTypeId, _positionId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlansFilterPositionPK) primaryKeyObj);
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

    public long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;
    }

    public long getPositionId() {
        return _positionId;
    }

    public void setPositionId(long positionId) {
        _positionId = positionId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlansFilterPositionLocalServiceUtil.addPlansFilterPosition(this);
        } else {
            PlansFilterPositionLocalServiceUtil.updatePlansFilterPosition(this);
        }
    }

    @Override
    public PlansFilterPosition toEscapedModel() {
        return (PlansFilterPosition) Proxy.newProxyInstance(PlansFilterPosition.class.getClassLoader(),
            new Class[] { PlansFilterPosition.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlansFilterPositionClp clone = new PlansFilterPositionClp();

        clone.setUserId(getUserId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setPositionId(getPositionId());

        return clone;
    }

    public int compareTo(PlansFilterPosition plansFilterPosition) {
        PlansFilterPositionPK primaryKey = plansFilterPosition.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlansFilterPositionClp plansFilterPosition = null;

        try {
            plansFilterPosition = (PlansFilterPositionClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlansFilterPositionPK primaryKey = plansFilterPosition.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlansFilterPosition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
