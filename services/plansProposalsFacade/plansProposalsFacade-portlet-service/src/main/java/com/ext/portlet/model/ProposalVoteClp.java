package com.ext.portlet.model;

import com.ext.portlet.service.ProposalVoteLocalServiceUtil;
import com.ext.portlet.service.persistence.ProposalVotePK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class ProposalVoteClp extends BaseModelImpl<ProposalVote>
    implements ProposalVote {
    private long _proposalId;
    private long _contestPhaseId;
    private long _userId;
    private String _userUuid;
    private Date _createDate;

    public ProposalVoteClp() {
    }

    public Class<?> getModelClass() {
        return ProposalVote.class;
    }

    public String getModelClassName() {
        return ProposalVote.class.getName();
    }

    public ProposalVotePK getPrimaryKey() {
        return new ProposalVotePK(_contestPhaseId, _userId);
    }

    public void setPrimaryKey(ProposalVotePK primaryKey) {
        setContestPhaseId(primaryKey.contestPhaseId);
        setUserId(primaryKey.userId);
    }

    public Serializable getPrimaryKeyObj() {
        return new ProposalVotePK(_contestPhaseId, _userId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ProposalVotePK) primaryKeyObj);
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public long getContestPhaseId() {
        return _contestPhaseId;
    }

    public void setContestPhaseId(long contestPhaseId) {
        _contestPhaseId = contestPhaseId;
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

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalVoteLocalServiceUtil.addProposalVote(this);
        } else {
            ProposalVoteLocalServiceUtil.updateProposalVote(this);
        }
    }

    @Override
    public ProposalVote toEscapedModel() {
        return (ProposalVote) Proxy.newProxyInstance(ProposalVote.class.getClassLoader(),
            new Class[] { ProposalVote.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalVoteClp clone = new ProposalVoteClp();

        clone.setProposalId(getProposalId());
        clone.setContestPhaseId(getContestPhaseId());
        clone.setUserId(getUserId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    public int compareTo(ProposalVote proposalVote) {
        ProposalVotePK primaryKey = proposalVote.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ProposalVoteClp proposalVote = null;

        try {
            proposalVote = (ProposalVoteClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        ProposalVotePK primaryKey = proposalVote.getPrimaryKey();

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

        sb.append("{proposalId=");
        sb.append(getProposalId());
        sb.append(", contestPhaseId=");
        sb.append(getContestPhaseId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalVote");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestPhaseId</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
