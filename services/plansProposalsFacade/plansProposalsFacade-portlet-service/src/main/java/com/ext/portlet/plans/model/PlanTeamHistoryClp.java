package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanTeamHistoryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanTeamHistoryClp extends BaseModelImpl<PlanTeamHistory>
    implements PlanTeamHistory {
    private long _id;
    private long _planId;
    private long _userId;
    private String _userUuid;
    private String _action;
    private String _payload;
    private Date _created;
    private long _updateAuthorId;

    public PlanTeamHistoryClp() {
    }

    public Class<?> getModelClass() {
        return PlanTeamHistory.class;
    }

    public String getModelClassName() {
        return PlanTeamHistory.class.getName();
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

    public String getAction() {
        return _action;
    }

    public void setAction(String action) {
        _action = action;
    }

    public String getPayload() {
        return _payload;
    }

    public void setPayload(String payload) {
        _payload = payload;
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
            PlanTeamHistoryLocalServiceUtil.addPlanTeamHistory(this);
        } else {
            PlanTeamHistoryLocalServiceUtil.updatePlanTeamHistory(this);
        }
    }

    @Override
    public PlanTeamHistory toEscapedModel() {
        return (PlanTeamHistory) Proxy.newProxyInstance(PlanTeamHistory.class.getClassLoader(),
            new Class[] { PlanTeamHistory.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTeamHistoryClp clone = new PlanTeamHistoryClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setUserId(getUserId());
        clone.setAction(getAction());
        clone.setPayload(getPayload());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    public int compareTo(PlanTeamHistory planTeamHistory) {
        int value = 0;

        if (getId() < planTeamHistory.getId()) {
            value = -1;
        } else if (getId() > planTeamHistory.getId()) {
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

        PlanTeamHistoryClp planTeamHistory = null;

        try {
            planTeamHistory = (PlanTeamHistoryClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planTeamHistory.getPrimaryKey();

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
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", action=");
        sb.append(getAction());
        sb.append(", payload=");
        sb.append(getPayload());
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
        sb.append("com.ext.portlet.plans.model.PlanTeamHistory");
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
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>action</column-name><column-value><![CDATA[");
        sb.append(getAction());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>payload</column-name><column-value><![CDATA[");
        sb.append(getPayload());
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
