package com.ext.portlet.model;

import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanVotePK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanVoteClp extends BaseModelImpl<PlanVote> implements PlanVote {
    private long _userId;
    private String _userUuid;
    private long _contestId;
    private long _planId;
    private Date _createDate;

    public PlanVoteClp() {
    }

    public Class<?> getModelClass() {
        return PlanVote.class;
    }

    public String getModelClassName() {
        return PlanVote.class.getName();
    }

    public PlanVotePK getPrimaryKey() {
        return new PlanVotePK(_userId, _contestId);
    }

    public void setPrimaryKey(PlanVotePK primaryKey) {
        setUserId(primaryKey.userId);
        setContestId(primaryKey.contestId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanVotePK(_userId, _contestId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanVotePK) primaryKeyObj);
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

    public long getContestId() {
        return _contestId;
    }

    public void setContestId(long contestId) {
        _contestId = contestId;
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanVoteLocalServiceUtil.addPlanVote(this);
        } else {
            PlanVoteLocalServiceUtil.updatePlanVote(this);
        }
    }

    @Override
    public PlanVote toEscapedModel() {
        return (PlanVote) Proxy.newProxyInstance(PlanVote.class.getClassLoader(),
            new Class[] { PlanVote.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanVoteClp clone = new PlanVoteClp();

        clone.setUserId(getUserId());
        clone.setContestId(getContestId());
        clone.setPlanId(getPlanId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    public int compareTo(PlanVote planVote) {
        PlanVotePK primaryKey = planVote.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanVoteClp planVote = null;

        try {
            planVote = (PlanVoteClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanVotePK primaryKey = planVote.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", contestId=");
        sb.append(getContestId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanVote");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestId</column-name><column-value><![CDATA[");
        sb.append(getContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
