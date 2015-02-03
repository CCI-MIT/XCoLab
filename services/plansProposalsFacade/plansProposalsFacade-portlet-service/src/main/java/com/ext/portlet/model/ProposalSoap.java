package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ProposalServiceSoap
 * @generated
 */
public class ProposalSoap implements Serializable {
    private long _proposalId;
    private Date _createDate;
    private Date _updatedDate;
    private int _currentVersion;
    private long _authorId;
    private boolean _visible;
    private long _discussionId;
    private long _resultsDiscussionId;
    private long _judgeDiscussionId;
    private long _fellowDiscussionId;
    private long _advisorDiscussionId;
    private long _groupId;

    public ProposalSoap() {
    }

    public static ProposalSoap toSoapModel(Proposal model) {
        ProposalSoap soapModel = new ProposalSoap();

        soapModel.setProposalId(model.getProposalId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setUpdatedDate(model.getUpdatedDate());
        soapModel.setCurrentVersion(model.getCurrentVersion());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setVisible(model.getVisible());
        soapModel.setDiscussionId(model.getDiscussionId());
        soapModel.setResultsDiscussionId(model.getResultsDiscussionId());
        soapModel.setJudgeDiscussionId(model.getJudgeDiscussionId());
        soapModel.setFellowDiscussionId(model.getFellowDiscussionId());
        soapModel.setAdvisorDiscussionId(model.getAdvisorDiscussionId());
        soapModel.setGroupId(model.getGroupId());

        return soapModel;
    }

    public static ProposalSoap[] toSoapModels(Proposal[] models) {
        ProposalSoap[] soapModels = new ProposalSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalSoap[][] toSoapModels(Proposal[][] models) {
        ProposalSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalSoap[] toSoapModels(List<Proposal> models) {
        List<ProposalSoap> soapModels = new ArrayList<ProposalSoap>(models.size());

        for (Proposal model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _proposalId;
    }

    public void setPrimaryKey(long pk) {
        setProposalId(pk);
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

    public Date getUpdatedDate() {
        return _updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        _updatedDate = updatedDate;
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

    public long getResultsDiscussionId() {
        return _resultsDiscussionId;
    }

    public void setResultsDiscussionId(long resultsDiscussionId) {
        _resultsDiscussionId = resultsDiscussionId;
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
}
