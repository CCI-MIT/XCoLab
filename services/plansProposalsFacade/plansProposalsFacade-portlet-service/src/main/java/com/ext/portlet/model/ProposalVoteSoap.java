package com.ext.portlet.model;

import com.ext.portlet.service.persistence.ProposalVotePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalVoteServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ProposalVoteServiceSoap
 * @generated
 */
public class ProposalVoteSoap implements Serializable {
    private long _proposalId;
    private long _contestPhaseId;
    private long _userId;
    private Date _createDate;
    private boolean _isValid;
    private Date _confirmationEmailSendDate;
    private String _confirmationToken;

    public ProposalVoteSoap() {
    }

    public static ProposalVoteSoap toSoapModel(ProposalVote model) {
        ProposalVoteSoap soapModel = new ProposalVoteSoap();

        soapModel.setProposalId(model.getProposalId());
        soapModel.setContestPhaseId(model.getContestPhaseId());
        soapModel.setUserId(model.getUserId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setIsValid(model.getIsValid());
        soapModel.setConfirmationEmailSendDate(model.getConfirmationEmailSendDate());
        soapModel.setConfirmationToken(model.getConfirmationToken());

        return soapModel;
    }

    public static ProposalVoteSoap[] toSoapModels(ProposalVote[] models) {
        ProposalVoteSoap[] soapModels = new ProposalVoteSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalVoteSoap[][] toSoapModels(ProposalVote[][] models) {
        ProposalVoteSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalVoteSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalVoteSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalVoteSoap[] toSoapModels(List<ProposalVote> models) {
        List<ProposalVoteSoap> soapModels = new ArrayList<ProposalVoteSoap>(models.size());

        for (ProposalVote model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalVoteSoap[soapModels.size()]);
    }

    public ProposalVotePK getPrimaryKey() {
        return new ProposalVotePK(_contestPhaseId, _userId);
    }

    public void setPrimaryKey(ProposalVotePK pk) {
        setContestPhaseId(pk.contestPhaseId);
        setUserId(pk.userId);
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

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public boolean getIsValid() {
        return _isValid;
    }

    public boolean isIsValid() {
        return _isValid;
    }

    public void setIsValid(boolean isValid) {
        _isValid = isValid;
    }

    public Date getConfirmationEmailSendDate() {
        return _confirmationEmailSendDate;
    }

    public void setConfirmationEmailSendDate(Date confirmationEmailSendDate) {
        _confirmationEmailSendDate = confirmationEmailSendDate;
    }

    public String getConfirmationToken() {
        return _confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        _confirmationToken = confirmationToken;
    }
}
