package com.ext.portlet.contests.model;

import com.ext.portlet.contests.service.ContestTeamMemberLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ContestTeamMemberClp extends BaseModelImpl<ContestTeamMember>
    implements ContestTeamMember {
    private long _id;
    private long _contestId;
    private long _userId;
    private String _userUuid;
    private String _role;

    public ContestTeamMemberClp() {
    }

    public Class<?> getModelClass() {
        return ContestTeamMember.class;
    }

    public String getModelClassName() {
        return ContestTeamMember.class.getName();
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

    public long getContestId() {
        return _contestId;
    }

    public void setContestId(long contestId) {
        _contestId = contestId;
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

    public String getRole() {
        return _role;
    }

    public void setRole(String role) {
        _role = role;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestTeamMemberLocalServiceUtil.addContestTeamMember(this);
        } else {
            ContestTeamMemberLocalServiceUtil.updateContestTeamMember(this);
        }
    }

    @Override
    public ContestTeamMember toEscapedModel() {
        return (ContestTeamMember) Proxy.newProxyInstance(ContestTeamMember.class.getClassLoader(),
            new Class[] { ContestTeamMember.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestTeamMemberClp clone = new ContestTeamMemberClp();

        clone.setId(getId());
        clone.setContestId(getContestId());
        clone.setUserId(getUserId());
        clone.setRole(getRole());

        return clone;
    }

    public int compareTo(ContestTeamMember contestTeamMember) {
        int value = 0;

        if (getId() < contestTeamMember.getId()) {
            value = -1;
        } else if (getId() > contestTeamMember.getId()) {
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

        ContestTeamMemberClp contestTeamMember = null;

        try {
            contestTeamMember = (ContestTeamMemberClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = contestTeamMember.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", contestId=");
        sb.append(getContestId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", role=");
        sb.append(getRole());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.ContestTeamMember");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestId</column-name><column-value><![CDATA[");
        sb.append(getContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>role</column-name><column-value><![CDATA[");
        sb.append(getRole());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
