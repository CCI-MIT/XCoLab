package com.ext.portlet.contests.model;

import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class ContestPhaseClp extends BaseModelImpl<ContestPhase>
    implements ContestPhase {
    private long _ContestPhasePK;
    private long _ContestPK;
    private String _ContestPhaseName;
    private String _ContestPhaseDescription;
    private String _ContestPhaseStatus;
    private Date _PhaseStartDate;
    private Date _PhaseEndDate;
    private String _nextStatus;
    private Date _created;
    private Date _updated;
    private long _authorId;

    public ContestPhaseClp() {
    }

    public Class<?> getModelClass() {
        return ContestPhase.class;
    }

    public String getModelClassName() {
        return ContestPhase.class.getName();
    }

    public long getPrimaryKey() {
        return _ContestPhasePK;
    }

    public void setPrimaryKey(long primaryKey) {
        setContestPhasePK(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_ContestPhasePK);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getContestPhasePK() {
        return _ContestPhasePK;
    }

    public void setContestPhasePK(long ContestPhasePK) {
        _ContestPhasePK = ContestPhasePK;
    }

    public long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(long ContestPK) {
        _ContestPK = ContestPK;
    }

    public String getContestPhaseName() {
        return _ContestPhaseName;
    }

    public void setContestPhaseName(String ContestPhaseName) {
        _ContestPhaseName = ContestPhaseName;
    }

    public String getContestPhaseDescription() {
        return _ContestPhaseDescription;
    }

    public void setContestPhaseDescription(String ContestPhaseDescription) {
        _ContestPhaseDescription = ContestPhaseDescription;
    }

    public String getContestPhaseStatus() {
        return _ContestPhaseStatus;
    }

    public void setContestPhaseStatus(String ContestPhaseStatus) {
        _ContestPhaseStatus = ContestPhaseStatus;
    }

    public Date getPhaseStartDate() {
        return _PhaseStartDate;
    }

    public void setPhaseStartDate(Date PhaseStartDate) {
        _PhaseStartDate = PhaseStartDate;
    }

    public Date getPhaseEndDate() {
        return _PhaseEndDate;
    }

    public void setPhaseEndDate(Date PhaseEndDate) {
        _PhaseEndDate = PhaseEndDate;
    }

    public String getNextStatus() {
        return _nextStatus;
    }

    public void setNextStatus(String nextStatus) {
        _nextStatus = nextStatus;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestPhaseLocalServiceUtil.addContestPhase(this);
        } else {
            ContestPhaseLocalServiceUtil.updateContestPhase(this);
        }
    }

    @Override
    public ContestPhase toEscapedModel() {
        return (ContestPhase) Proxy.newProxyInstance(ContestPhase.class.getClassLoader(),
            new Class[] { ContestPhase.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestPhaseClp clone = new ContestPhaseClp();

        clone.setContestPhasePK(getContestPhasePK());
        clone.setContestPK(getContestPK());
        clone.setContestPhaseName(getContestPhaseName());
        clone.setContestPhaseDescription(getContestPhaseDescription());
        clone.setContestPhaseStatus(getContestPhaseStatus());
        clone.setPhaseStartDate(getPhaseStartDate());
        clone.setPhaseEndDate(getPhaseEndDate());
        clone.setNextStatus(getNextStatus());
        clone.setCreated(getCreated());
        clone.setUpdated(getUpdated());
        clone.setAuthorId(getAuthorId());

        return clone;
    }

    public int compareTo(ContestPhase contestPhase) {
        int value = 0;

        value = DateUtil.compareTo(getPhaseStartDate(),
                contestPhase.getPhaseStartDate());

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

        ContestPhaseClp contestPhase = null;

        try {
            contestPhase = (ContestPhaseClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = contestPhase.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{ContestPhasePK=");
        sb.append(getContestPhasePK());
        sb.append(", ContestPK=");
        sb.append(getContestPK());
        sb.append(", ContestPhaseName=");
        sb.append(getContestPhaseName());
        sb.append(", ContestPhaseDescription=");
        sb.append(getContestPhaseDescription());
        sb.append(", ContestPhaseStatus=");
        sb.append(getContestPhaseStatus());
        sb.append(", PhaseStartDate=");
        sb.append(getPhaseStartDate());
        sb.append(", PhaseEndDate=");
        sb.append(getPhaseEndDate());
        sb.append(", nextStatus=");
        sb.append(getNextStatus());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.ContestPhase");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ContestPhasePK</column-name><column-value><![CDATA[");
        sb.append(getContestPhasePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPK</column-name><column-value><![CDATA[");
        sb.append(getContestPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPhaseName</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPhaseDescription</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPhaseStatus</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>PhaseStartDate</column-name><column-value><![CDATA[");
        sb.append(getPhaseStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>PhaseEndDate</column-name><column-value><![CDATA[");
        sb.append(getPhaseEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nextStatus</column-name><column-value><![CDATA[");
        sb.append(getNextStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
