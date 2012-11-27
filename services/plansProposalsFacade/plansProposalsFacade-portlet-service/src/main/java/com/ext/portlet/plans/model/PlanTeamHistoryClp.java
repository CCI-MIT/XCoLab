package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanTeamHistoryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanTeamHistoryClp extends BaseModelImpl<PlanTeamHistory>
    implements PlanTeamHistory {
    private Long _id;
    private Long _planId;
    private Long _userId;
    private String _action;
    private String _payload;
    private Date _created;
    private Long _updateAuthorId;

    public PlanTeamHistoryClp() {
    }

    public Class<?> getModelClass() {
        return PlanTeamHistory.class;
    }

    public String getModelClassName() {
        return PlanTeamHistory.class.getName();
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

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
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

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
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

        Long primaryKey = planTeamHistory.getPrimaryKey();

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
