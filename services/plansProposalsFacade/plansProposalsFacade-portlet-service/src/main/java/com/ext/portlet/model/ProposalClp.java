package com.ext.portlet.model;

import com.ext.portlet.service.ProposalLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class ProposalClp extends BaseModelImpl<Proposal> implements Proposal {
    private long _proposalId;
    private Date _createDate;
    private int _currentVersion;
    private long _authorId;
    private boolean _visible;
    private long _discussionId;
    private long _judgeDiscussionId;
    private long _fellowDiscussionId;
    private long _advisorDiscussionId;
    private long _groupId;

    public ProposalClp() {
    }

    public Class<?> getModelClass() {
        return Proposal.class;
    }

    public String getModelClassName() {
        return Proposal.class.getName();
    }

    public long getPrimaryKey() {
        return _proposalId;
    }

    public void setPrimaryKey(long primaryKey) {
        setProposalId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_proposalId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public int getCurrentVersion() {
        return _currentVersion;
    }

    public void setCurrentVersion(int currentVersion) {
        _currentVersion = currentVersion;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public boolean getVisible() {
        return _visible;
    }

    public boolean isVisible() {
        return _visible;
    }

    public void setVisible(boolean visible) {
        _visible = visible;
    }

    public long getDiscussionId() {
        return _discussionId;
    }

    public void setDiscussionId(long discussionId) {
        _discussionId = discussionId;
    }

    public long getJudgeDiscussionId() {
        return _judgeDiscussionId;
    }

    public void setJudgeDiscussionId(long judgeDiscussionId) {
        _judgeDiscussionId = judgeDiscussionId;
    }

    public long getFellowDiscussionId() {
        return _fellowDiscussionId;
    }

    public void setFellowDiscussionId(long fellowDiscussionId) {
        _fellowDiscussionId = fellowDiscussionId;
    }

    public long getAdvisorDiscussionId() {
        return _advisorDiscussionId;
    }

    public void setAdvisorDiscussionId(long advisorDiscussionId) {
        _advisorDiscussionId = advisorDiscussionId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalLocalServiceUtil.addProposal(this);
        } else {
            ProposalLocalServiceUtil.updateProposal(this);
        }
    }

    @Override
    public Proposal toEscapedModel() {
        return (Proposal) Proxy.newProxyInstance(Proposal.class.getClassLoader(),
            new Class[] { Proposal.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalClp clone = new ProposalClp();

        clone.setProposalId(getProposalId());
        clone.setCreateDate(getCreateDate());
        clone.setCurrentVersion(getCurrentVersion());
        clone.setAuthorId(getAuthorId());
        clone.setVisible(getVisible());
        clone.setDiscussionId(getDiscussionId());
        clone.setJudgeDiscussionId(getJudgeDiscussionId());
        clone.setFellowDiscussionId(getFellowDiscussionId());
        clone.setAdvisorDiscussionId(getAdvisorDiscussionId());
        clone.setGroupId(getGroupId());

        return clone;
    }

    public int compareTo(Proposal proposal) {
        long primaryKey = proposal.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ProposalClp proposal = null;

        try {
            proposal = (ProposalClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = proposal.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{proposalId=");
        sb.append(getProposalId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", currentVersion=");
        sb.append(getCurrentVersion());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", visible=");
        sb.append(getVisible());
        sb.append(", discussionId=");
        sb.append(getDiscussionId());
        sb.append(", judgeDiscussionId=");
        sb.append(getJudgeDiscussionId());
        sb.append(", fellowDiscussionId=");
        sb.append(getFellowDiscussionId());
        sb.append(", advisorDiscussionId=");
        sb.append(getAdvisorDiscussionId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.Proposal");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>currentVersion</column-name><column-value><![CDATA[");
        sb.append(getCurrentVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visible</column-name><column-value><![CDATA[");
        sb.append(getVisible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discussionId</column-name><column-value><![CDATA[");
        sb.append(getDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>judgeDiscussionId</column-name><column-value><![CDATA[");
        sb.append(getJudgeDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fellowDiscussionId</column-name><column-value><![CDATA[");
        sb.append(getFellowDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>advisorDiscussionId</column-name><column-value><![CDATA[");
        sb.append(getAdvisorDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
